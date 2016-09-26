package com.coursera.cs.cap.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cscenginer
 *
 */
public class TCPServer {


    private ServerSocket serverSocket;
    private Integer port;


    public TCPServer(Integer port){

        this.port = port;
    }


    public void start() throws IOException {

        serverSocket = new ServerSocket(port);
    }



    public void listen() throws Exception{

        if(serverSocket == null)
            throw new Exception("Server not started");

        while(true) {

            Socket clientSocket = serverSocket.accept();

            String[] dataRead = readFromSocket(clientSocket);

            // for the time being just return the number of lines received
            // as input. we will need to inject the parser
            writeToSocket(clientSocket,"Message received. Lines: " + dataRead.length);

            // if the client doesn't close the connection but the expected
            // command comes through the code then we need to close the connection
            clientSocket.close();
        }
    }



    protected String[] readFromSocket(Socket socket) throws IOException {

        BufferedReader clientReader =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String inputLine;
        List<String> clientData = new ArrayList<String>();


        // we are making an assumption that input coming from the client is
        // terminate with newline character. this will need to be confirmed
        while (!(inputLine = clientReader.readLine().trim()).equalsIgnoreCase("***"))
        {
            clientData.add(inputLine);
        }

        return clientData.toArray(new String[clientData.size()]);

    }

    protected void writeToSocket(Socket socket, String msg) throws IOException {


        DataOutputStream clientWriter = new DataOutputStream(socket.getOutputStream());


        clientWriter.writeBytes(msg + '\n');


    }



}
