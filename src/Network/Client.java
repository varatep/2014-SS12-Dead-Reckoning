package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import com.example.deadreckoning.LocateActivity;

import android.util.Log;

public class Client implements Runnable {

	boolean keepRunning = true;
	boolean success = false;
	
	Queue<String> outgoingCommandQueue;
	int port;
	String ip;
	Socket socket;
	PrintWriter writer;
	BufferedReader reader;
	
    public Client(int port, String ip) {
    	this.ip = ip;
    	this.port = port;
    	outgoingCommandQueue = new LinkedList<String>();
    	
    	Thread thread = new Thread(this);
    	thread.start();
    }
    
    public synchronized void shutDown() {
    	keepRunning = false;
    }
    
    public synchronized void setSuccessful() {
    	success = true;
    }


    @Override
    public void run() {
        try {
        	Log.i("ss12", "before connection");
        	socket = new Socket();
        	socket.bind(null);
            socket.connect((new InetSocketAddress(ip, port)), 0);
            socket.setReuseAddress(true);
            socket.setKeepAlive(true);

            Log.i("ss12", "connection established");
            
            writer = new PrintWriter(
                            socket.getOutputStream());
            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            
            while (keepRunning) {
            	
            	//client tries to read first
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
            	
            	//client sends coordinates to server
            	String direction = LocateActivity.direction + "\r\n";
	        	writer.write(direction);
	        	writer.flush();
	        	
	        	//if the users have reached each other
	        	if(success) {
	        		writer.write("Thank you for using the Dead Reckoning App\r\n");
	        		writer.flush();
	        		break;
	        	}
            }
            
            reader.close();
            writer.close();
            socket.close();


        } catch (Exception e) {
        	//close sockets and readers/writers if an error occurs
        	if(socket != null) {
        		try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        	if(writer != null) {
        		writer.close();
        	}
        	if(reader != null) {
        		try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
            e.printStackTrace();
        }
    }
}
