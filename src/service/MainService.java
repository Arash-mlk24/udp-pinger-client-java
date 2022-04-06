package service;

import model.ServiceResult;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainService implements AppService {

    private final int port;
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public MainService(int port) {
        this.port = port;
    }

    @Override
    public ServiceResult<String> start() {
        System.out.println("Starting connection...");
        try {

            socket = new Socket("127.0.0.1", port);
            System.out.println("Connection established!");

//            input = new DataInputStream(System.in);
//            output = new DataOutputStream(socket.getOutputStream());

            return new ServiceResult<String>("Connection Established!");

        } catch(IOException e) {

            System.out.println("Error occurred while connecting to server: " + e);
            return new ServiceResult<String>("", true, "Connection Failed!");

        }

    }

    public ServiceResult<String> sendMessage() {
        System.out.println("Sending message...");
        try {

            socket = new Socket("127.0.0.1", port);
            System.out.println("Connection established!");

//            input = new DataInputStream(System.in);
//            output = new DataOutputStream(socket.getOutputStream());

        } catch(IOException e) {

            System.out.println("Error occurred while connecting to server: " + e);
        }

        String line = "";

        while (!line.equals("Over")) {
            try {

                line = input.readLine();
                output.writeUTF(line);

            } catch(IOException e) {

                System.out.println("Error occurred while reading message: " + e);

            }
        }

        // close the connection
        try {
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }

        ServiceResult<String> result = new ServiceResult<>("Done");
        System.out.println("returning result: " + result);
        return result;

    }



}
