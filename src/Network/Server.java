package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import android.util.Log;

public class Server implements Runnable {
    
	boolean keepRunning = true;
	int port;
	
	ServerSocket serverSocket;
	Socket clientSocket;
	
    public Server(int port) {
    	this.port = port;
        Thread thread = new Thread(this);
        thread.start();
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
	        BufferedReader reader = new BufferedReader(
	                new InputStreamReader(
	                        clientSocket.getInputStream()));
	        BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(
	                        clientSocket.getOutputStream()));
	
	
	        while (keepRunning) {
	
	            try {
	                String s = reader.readLine();
	                if(s != null) {
	                	keepRunning = false;
	                }
	                Log.i("ss12", "read in " + String.valueOf(s));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	
	        clientSocket.close();
	        serverSocket.close();
	    } catch (Exception e) {
	    	try {
		    	if(clientSocket != null) {
		    		clientSocket.close();
		    	}
		    	if(serverSocket != null) {
					serverSocket.close();
		    	}
	    	} catch(Exception f) {
	    		f.printStackTrace();
	    	}
	        e.printStackTrace();
	    }
    }

}
