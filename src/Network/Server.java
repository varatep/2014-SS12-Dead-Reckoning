package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.deadreckoning.LocateActivity;

import android.util.Log;

public class Server implements Runnable {
    
	boolean keepRunning = true;
	boolean success = false;
	
	int port;
	
	ServerSocket serverSocket;
	Socket clientSocket;
	
	BufferedReader reader;
	PrintWriter writer;
	
    public Server(int port) {
    	this.port = port;
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void shutDown() {
    	keepRunning = false;
    }
    
    public synchronized void setSuccessful() {
    	success = true;
    }
    
    public void run() {
	    try {
	        serverSocket = new ServerSocket();
	        serverSocket.setReuseAddress(true);
	        serverSocket.bind(new InetSocketAddress(port));
	        Log.i("ss12", "waiting for connection");
	
	        clientSocket = serverSocket.accept();
	        clientSocket.setKeepAlive(true);
	        clientSocket.setTcpNoDelay(true);
	
	        clientSocket.setSoTimeout(0);
	
	        Log.i("ss12", "connection established");
	        reader = new BufferedReader(
	                new InputStreamReader(
	                        clientSocket.getInputStream()));
	        writer = new PrintWriter(
	                        clientSocket.getOutputStream());
	
	        while(keepRunning) {
	        	//server writes to client first
	        	String direction = LocateActivity.direction + "\r\n";
	        	writer.write(direction);
	        	writer.flush();
	        	
	        	//if the users have reached each other
	        	if(success) {
	        		writer.write("Thank you for using the Dead Reckoning App\r\n");
	        		writer.flush();
	        		break;
	        	}
	        	
	        	//server tries to read from client
	        	try {
	                String s = reader.readLine();
	                Log.i("ss12", "read in " + String.valueOf(s) + "\n");
	                //if the server receives notification that we are done
	                if(s.contains("Thank you for using")) {
	                	break;
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	
	        writer.close();
	        reader.close();
	        clientSocket.close();
	        serverSocket.close();
	    } catch (Exception e) {
	    	
	    	//close sockets and readers/writers if an error occurs
	    	try {
		    	if(clientSocket != null) {
		    		clientSocket.close();
		    	}
		    	if(serverSocket != null) {
					serverSocket.close();
		    	}
		    	if(writer != null) {
		    		writer.close();
		    	}
		    	if(reader != null) {
		    		reader.close();
		    	}
	    	} catch(Exception f) {
	    		f.printStackTrace();
	    	}
	        e.printStackTrace();
	    }
    }

}
