package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Server implements Runnable {
    
	boolean keepRunning = true;
	
    public Server(int port) {
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
	    try {
	        ServerSocket serverSocket = new ServerSocket(12345);
	        serverSocket.setReuseAddress(true);
	        Log.i("ss12", "waiting for connection");
	
	        Socket clientSocket = serverSocket.accept();
	        clientSocket.setKeepAlive(true);
	        clientSocket.setTcpNoDelay(true);
	
	        clientSocket.setSoTimeout(10);
	
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
	                Log.i("ss12", "read in " + String.valueOf(s));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	//
	
	        }
	
	        clientSocket.close();
	        serverSocket.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }

}
