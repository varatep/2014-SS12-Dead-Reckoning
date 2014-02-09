package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;

public class Client implements Runnable {

	boolean keepRunning = true;
	Queue<String> outgoingCommandQueue;
	int port;
	String ip;
	
    public Client(int port, String ip) {
    	this.ip = ip;
    	this.port = port;
    	outgoingCommandQueue = new LinkedList<String>();
    	
    	Thread thread = new Thread(this);
    	thread.start();
    }


    @Override
    public void run() {
        try {
        	Log.i("ss12", "before connection");
        	Socket socket = new Socket();
        	socket.bind(null);
            socket.connect((new InetSocketAddress(ip, port)), 0);
            socket.setReuseAddress(true);
            socket.setKeepAlive(true);

            Log.i("ss12", "connection established");
            
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            //while (keepRunning) {

                //if (outgoingCommandQueue.size() > 0) {
                    //final String data = outgoingCommandQueue.remove();
            		String data = "I'm talking!!";
                    writer.write(data);
                    writer.flush();
                    socket.close();
                //}

               // try {
               //     final String s = reader.readLine();

                //} catch (Exception e) {
                	
                //}

            //}
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
