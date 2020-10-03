/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sagar
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class ServerC
{
 
    private static Socket socket;
 
    public static void main(String[] args)
    {
        try
        {
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
         
            while(true)
            {             
                socket = serverSocket.accept();
                Worker worker = new Worker(socket);
                worker.start();   
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
    
    static class Operate{
        
        synchronized public void operate(Socket socket) throws Exception {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String number = br.readLine();
            System.out.println("Message received from client is "+number);

            String returnMessage;
            try
            {
                int numberInIntFormat = Integer.parseInt(number);
                int returnValue = numberInIntFormat*2;
                returnMessage = String.valueOf(returnValue) + "\n";
            }
            catch(NumberFormatException e)
            {
                returnMessage = "Please send a proper number\n";
            }

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(returnMessage);
            System.out.println("Message sent to the client is "+returnMessage);
            bw.flush();
        }
    }
    
    static class Worker extends Thread{
        Socket socket;
        Operate o;
        
        Worker(Socket socket){
            this.socket = socket;
            o = new Operate();
        }
        
        public void run(){
            try
            {
                synchronized(o){
                   o.operate(socket); 
                }
                              
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    socket.close();
                }
                catch(Exception e){}
            }
       } 
    }
}
