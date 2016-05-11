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
    private RestaurantSearch restaurantSearch;

    Client(RestaurantSearch restaurantSearch) throws IOException{
        this.restaurantSearch = restaurantSearch;
        new messaging(this.hostname, this.portNumber, restaurantSearch);
    }

    public void close_connection() throws IOException{
        this.connection.close();
    }

    class messaging extends Thread{
        String hostname;
        int portNumber;
        Socket connectedSocket = null;
        RestaurantSearch restaurantSearch;

        // Thread constructor
        messaging(String ip, int port, RestaurantSearch restaurantSearch){
            this.hostname = ip;
            this.portNumber = port;
            this.restaurantSearch = restaurantSearch;
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
                            System.out.println("Recieved message!");
                            // Parse message and begin recieving file.
                            message = message.substring(1);
                            int fileSize = Integer.parseInt(message.substring(0, message.indexOf('$')));

                            System.out.println(fileSize);

                            byte[] byteSize = new byte[fileSize * 5];
                            // Byte stream connection from socket
                            InputStream incoming = connectedSocket.getInputStream();
                            // New file directory
                            ByteArrayOutputStream outputData = new ByteArrayOutputStream();
                            // Stream to extract file from byte stream
                            BufferedOutputStream output = new BufferedOutputStream(outputData);

                            int left = fileSize;
                            int renew = fileSize;

                            try{
                                long timeStart = System.currentTimeMillis();
                                int i = 0, j = 0;
                               // while (i < fileSize) { // && System.currentTimeMillis() - timeStart < 10000){
//                                    while (incoming.available() < BLOCKSIZE)
//                                        continue;

                                    while(incoming.available() < fileSize && System.currentTimeMillis() - timeStart < 20000);

                                    int actualRead = incoming.read(byteSize, i, left);

                                    output.write(byteSize, i, actualRead);
                                    //i += actualRead;
                                    //left -= actualRead;
                                    //System.out.println(i);
                                    //System.out.println(new String(byteSize));
                                    //System.out.println("inside while loop");
                                //}

                                //System.out.println(new String(byteSize));


                                System.out.println("here");

                                String allData = new String(byteSize);
                                //System.out.print(allData);
                                Scanner scanner = new Scanner(allData);
                                int failed = 0;

                                int counter = 0;
                                while (scanner.hasNextLine()) {
                                    try {
                                        counter = 0;
                                        String toInsert = scanner.nextLine();

                                        // Ignore a line when it's incomplete
                                        if(toInsert.contains("�")) {continue;}

                                        for(int m = 0; m < toInsert.length(); m++){
                                            if(toInsert.charAt(m) == '@')
                                                counter = counter + 1;
                                        }

                                        if(counter < 2) continue;

                                        System.out.println(toInsert);

                                            //System.out.print(toInsert);
                                        String categoryToInsert = toInsert.substring(0, toInsert.indexOf('@'));
                                        String nameToInsert = toInsert.substring(toInsert.indexOf('@') + 1, toInsert.lastIndexOf('@'));
                                        String descriptionToInsert = toInsert.substring(toInsert.lastIndexOf('@') + 1, toInsert.length() - 3);
                                        descriptionToInsert = descriptionToInsert.trim();
                                        double ratingToInsert = Double.valueOf(toInsert.substring(toInsert.length() - 3));
                                        restaurantSearch.allRestaurants.insert(new Restaurant(categoryToInsert, nameToInsert, descriptionToInsert, ratingToInsert));

                                    } catch (NumberFormatException e){
                                        failed++;
                                        System.out.println("Failed input row: " + failed);
                                    } catch (StringIndexOutOfBoundsException e){
                                        failed++;
                                        System.out.println("Failed input row: " + failed);
                                    }
                                }
                                restaurantSearch.readyToDisplay = new Boolean(true);
                                scanner.close();
                                System.out.println("Exiting message receive!");
                            }
                            finally{
                                output.flush();
                                output.close();
                            }
                        }

                    }

                    // Check if message is ready to be sent.
                    String toSend = "";
                    boolean requestReady = false;
                    while(restaurantSearch.categoriesToSearch.size() > 0){
                        // Convert message to message protocol.
                        toSend += restaurantSearch.categoriesToSearch.get(0) + '%';
                        restaurantSearch.categoriesToSearch.remove(0);
                        requestReady = true;
                    }
                    if (requestReady) {
                        out.println(toSend);
                        requestReady = false;
                    }
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
