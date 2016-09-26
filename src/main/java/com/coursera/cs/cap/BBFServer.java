package com.coursera.cs.cap;

import com.coursera.cs.cap.net.TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * BBFServer
 *
 */
public class BBFServer
{
    public static void main(String argv[])
    {
        String adminPassword;

        String clientInput;
        TCPServer server;

        if(argv.length < 1 || argv.length > 2){
            System.out.println("A port is a required parameter");
            System.out.println("Usage: java server.jar [PORT] <admin_password>");
            System.exit(1);
        }

        Integer port = 0;

        try {
            port = new Integer(argv[0]);

            if(port < 1024 || port > 65535)
                throw new NumberFormatException();

        } catch (NumberFormatException nfe){
            System.out.println("Invalid port value: " + argv[0]);
            System.exit(1);
        }

        adminPassword = argv.length == 2? argv[1]:"admin";


        server = new TCPServer(port);


        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Failed to start the server. Msg: " + e.getMessage());
            System.exit(1);
        }


        try {
            server.listen();
        } catch (Exception e) {
            System.out.println("An exception was thrown. Msg:" + e.getMessage());
        }


    }
}
