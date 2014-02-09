package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import com.example.deadreckoning.LocateActivity;

import android.util.Log;

public class Client implements Runnable {

	boolean keepRunning = true;
	Queue<String> outgoingCommandQueue;
	int port;
	String ip;
	Socket socket;
	BufferedWriter writer;
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
            
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            
            while (keepRunning) {
            	try {
	                String s = reader.readLine();
	                Log.i("ss12", "read in " + String.valueOf(s));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        	String direction = "";
	        	Log.i("ss12", "before - " + LocateActivity.direction);
	        	if(!LocateActivity.direction.equals(direction)) {
	        		Log.i("ss12", "after - " + LocateActivity.direction);
	        		direction = LocateActivity.direction;
                    writer.write(direction);
                    writer.flush();
	        	}
	        }
            socket.close();
            writer.close();


        } catch (Exception e) {
        	if(socket != null) {
        		try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        	if(writer != null) {
        		try {
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
