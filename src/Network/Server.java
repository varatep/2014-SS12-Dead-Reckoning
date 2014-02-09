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
	
	
	        writer.write("hello");
	        
	        //while (keepRunning) {
	        	/*String direction = "";
	        	Log.i("ss12", "before - " + LocateActivity.direction);
	        	if(!LocateActivity.direction.equals(direction)) {
	        		Log.i("ss12", "after - " + LocateActivity.direction);
	        		direction = LocateActivity.direction;
                    writer.write("hello");
                    //clientSocket.shutdownOutput();
                    writer.flush();
	        	}
	        	try {
	                String s = reader.readLine();
	                clientSocket.shutdownInput();
	                Log.i("ss12", "read in " + String.valueOf(s));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        	Thread.sleep(50);*/
	        //}
	
	        clientSocket.close();
	        serverSocket.close();
	        writer.close();
	        reader.close();
	    } catch (Exception e) {
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
