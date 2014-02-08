package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Client {
	
    ClientThread clientThread;

    public void establishConnection() {
    	
    }

    public void closeConnection() {
        clientThread.shutDown();
    }

    public void writeData(String data) {

    }

    public Client(int port, String ip) {
        clientThread = new ClientThread(ip);
    }

    public class ClientThread extends Thread {
        boolean keepRunning = true;
        String ip;
        Queue<String> outgoingCommandQueue;


        public ClientThread(String ip) {
        	this.ip = ip;
        	outgoingCommandQueue = new LinkedList<String>();
        }
        
        synchronized public void shutDown() {
            keepRunning = false;
        }

        @Override
        public void run() {
            super.run();
            try {
                Socket socket = new Socket(ip, 12345);
                socket.setReuseAddress(true);
                socket.setKeepAlive(true);

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                while (keepRunning) {

                    if (outgoingCommandQueue.size() > 0) {
                        final String data = outgoingCommandQueue.remove();
                        writer.write(data);
                        writer.flush();
                    }

                    try {
                        final String s = reader.readLine();

                    } catch (Exception e) {
                    }

                }
                writer.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
