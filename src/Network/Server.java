package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Server {
	
    ServerThread serverThread;
    
    public Server(int port, CommsHandlerInterface commsHandlerInterface) {
        super(port, commsHandlerInterface);
        serverThread = new ServerThread();
    }

    public void establishConnection() {
    }

    public void writeData(String data) {
    }

    @Override
    public void closeConnection() {
        serverThread.shutDown();
    }

    public class ServerThread extends Thread {
        boolean keepRunning = true;

        synchronized public void shutDown() {
            keepRunning = false;
        }


        @Override
        public void run() {
            super.run();
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                serverSocket.setReuseAddress(true);
                Log.i("workshop", "waiting for connection");

                Socket clientSocket = serverSocket.accept();
                clientSocket.setKeepAlive(true);
                clientSocket.setTcpNoDelay(true);

                clientSocket.setSoTimeout(10);

                Log.i("workshop", "connection established");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                clientSocket.getOutputStream()));


                while (keepRunning) {

                    try {
                        String s = reader.readLine();
                        Log.i("workshop", "read in " + String.valueOf(s));
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

}
