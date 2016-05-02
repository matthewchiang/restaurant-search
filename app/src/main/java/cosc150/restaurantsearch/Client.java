package cosc150.restaurantsearch;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String hostname = "52.90.92.72";
    private int portNumber = 40001;
    private Socket connection = null;

    Client() throws IOException{
        new messaging(this.hostname,this.portNumber);
    }

    public void close_connection() throws IOException{
        this.connection.close();
    }

    class messaging extends Thread{
        String hostname;
        int portNumber;
        Socket connectedSocket = null;

        // Thread constructor
        messaging(String ip, int port){
            this.hostname = ip;
            this.portNumber = port;
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
                            InputStream incoming = connection.getInputStream();
                            // New file directory
                            FileOutputStream getFile = new FileOutputStream("this should the file destination");
                            // Stream to extract file from byte stream
                            BufferedOutputStream output = new BufferedOutputStream(getFile);

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
                            }
                            finally{
                                output.flush();
                                output.close();
                            }
                        }

                    }

                    /* This should be modified to be called when a button is pressed for
                        search. It should just send a string.
                    // Check if message is ready to be sent.
                    if(driverProgram.messageToSend != ""){
                        // Convert message to message protocol.
                        String toSend = driverProgram.name;
                        while(toSend.length() < 20)
                            toSend = toSend + '%';
                        toSend = toSend + driverProgram.messageToSend;
                        out.println(toSend);
                        driverProgram.messageToSend = "";
                    }
                    */
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
