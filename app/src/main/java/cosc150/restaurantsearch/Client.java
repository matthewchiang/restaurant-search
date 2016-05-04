package cosc150.restaurantsearch;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String hostname = "52.90.92.72";
    private int portNumber = 40001;
    private Socket connection = null;
    private ArrayList<String> toRequest;
    private BPlusTree bPlusTreeToInsert;

    Client(ArrayList<String> toRequest, BPlusTree bPlusTreeToInsert) throws IOException{
        this.toRequest = toRequest;
        this.bPlusTreeToInsert = bPlusTreeToInsert;
        new messaging(this.hostname, this.portNumber, toRequest, bPlusTreeToInsert);
    }

    public void close_connection() throws IOException{
        this.connection.close();
    }

    class messaging extends Thread{
        String hostname;
        int portNumber;
        Socket connectedSocket = null;
        ArrayList<String> requested;
        BPlusTree bPlusTree;

        // Thread constructor
        messaging(String ip, int port, ArrayList<String> requested, BPlusTree bPlusTree){
            this.hostname = ip;
            this.portNumber = port;
            this.requested = requested;
            this.bPlusTree = bPlusTree;
            start();
        }

        @Override
        public void run(){
            try{
                this.connectedSocket = new Socket(hostname, portNumber);
                // Outgoing socket byte stream
                PrintWriter out = new PrintWriter(connectedSocket.getOutputStream(), true);
                // Incoming socket byte stream
                BufferedReader in = new BufferedReader(new InputStreamReader(connectedSocket.getInputStream() ) );

                // Will infinitely loop looking for incoming information
                // until the connection with the socket has been closed
                while(true){
                    if(in.ready()){
                        // Checks to see that there's something to read from socket.
                        String message = in.readLine();

                        // Check if file is incoming.
                        if(message.charAt(0) == '$'){
                            // Parse message and begin recieving file.
                            message = message.substring(1);
                            int fileSize = Integer.parseInt(message.substring(0, message.indexOf('$')));
                            String fileName = message.substring(message.lastIndexOf('$') + 1);

                            int BLOCKSIZE = 1024;
                            byte[] byteSize = new byte[BLOCKSIZE];
                            // Byte stream connection from socket
                            InputStream incoming = connectedSocket.getInputStream();
                            // New file directory
                            ByteArrayOutputStream outputData = new ByteArrayOutputStream();
                            // Stream to extract file from byte stream
                            BufferedOutputStream output = new BufferedOutputStream(outputData);

                            try{
                                int i = BLOCKSIZE, j = 0;
                                while (i < fileSize){
                                    while (incoming.available() < BLOCKSIZE)
                                        continue;
                                    incoming.read(byteSize, 0, BLOCKSIZE);
                                    output.write(byteSize, 0, BLOCKSIZE);
                                    i += BLOCKSIZE;
                                }
                                i -= BLOCKSIZE;
                                while (i + j < fileSize){
                                    incoming.read(byteSize, 0, 1);
                                    output.write(byteSize, 0, 1);
                                    j++;
                                }
                                String allData = outputData.toString();
                                Scanner scanner = new Scanner(allData);
                                while (scanner.hasNextLine()) {
                                    String toInsert = scanner.nextLine();
                                    String categoryToInsert = toInsert.substring(0, toInsert.indexOf('&'));
                                    String nameToInsert = toInsert.substring(toInsert.indexOf('&') + 1, toInsert.lastIndexOf('&'));
                                    String descriptionToInsert = toInsert.substring(toInsert.lastIndexOf('&'), toInsert.length() - 3);
                                    descriptionToInsert = descriptionToInsert.trim();
                                    double ratingToInsert = Double.valueOf(toInsert.substring(toInsert.length()-3));
                                    bPlusTree.insert(new Restaurant(categoryToInsert, nameToInsert, descriptionToInsert, ratingToInsert));
                                }
                                scanner.close();

                            }
                            finally{
                                output.flush();
                                output.close();
                            }
                        }

                    }

                    // Check if message is ready to be sent.
                    String toSend = "";
                    while(requested.size() > 0){
                        // Convert message to message protocol.
                        toSend += requested.get(0) + '%';
                        requested.remove(0);
                    }
                    out.println(toSend);
                }

            }catch(IOException e) {
                // Exception handling derived from examples provided in class
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }











    }
