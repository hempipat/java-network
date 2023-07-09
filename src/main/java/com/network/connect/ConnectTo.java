package com.network.connect;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectTo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectTo.class); 

    public static void main(String[] args) {
        LOGGER.info("Running the main method");
        try (Socket socket = new Socket("www.google.com", 80);
            OutputStream os = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String request = "GET / HTTP/1.1\r\n"
                + "Host: www.google.com\r\n\r\n";

            os.write(request.getBytes());
            os.flush();


            // Read the response 
            StringBuilder responseBuilder = new StringBuilder();
            String line; 
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }

            String response = responseBuilder.toString();
            LOGGER.info("Received response: {}", response);
        } catch (IOException e) {
            LOGGER.error("An error occurred during the HTTP request: {}", e.getMessage());
        }
    }
}
