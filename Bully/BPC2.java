/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sagar
 */
public class BPC2 {

    private static Socket socket;

    static int MYPOS = 1;
    
    public static void main(String[] args)
    {
        try
        {
           
            int port[] = {25001,25002,25003,25004};
            ServerSocket serverSocket = new ServerSocket(port[1]);
            System.out.println("Listening to the port "+ port[1]);
            
            String host = "localhost";
            InetAddress address = InetAddress.getByName(host);
            
            Socket ssocket;
            
            Socket socket[] = new Socket[4];
            
            int  i = MYPOS + 1;
                
            socket[1] = serverSocket.accept();
            InputStream is = socket[1].getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String number = br.readLine();
            System.out.println("Message received is "+number);

            String returnMessage = "OK\n";

            OutputStream os = socket[1].getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(returnMessage);
            System.out.println("Message sent is "+returnMessage);
            bw.flush();
            
            try{
                Thread.sleep(5000);
            }catch(Exception e){}
            
            i = MYPOS + 1;
            while(i < 3)
            {
                String msgE = "E\n";
                socket[i] = new Socket(address, port[i]);
                os = socket[i].getOutputStream();
                osw = new OutputStreamWriter(os);
                bw = new BufferedWriter(osw);
                bw.write(msgE);
                System.out.println("Message sent to the PC " + i + " is "+msgE);
                bw.flush();  
               
                is = socket[i].getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                number = br.readLine();
                System.out.println("Message received from client " + i +" is "+number);
                i++;
            }
            
            socket[1] = serverSocket.accept();
            is = socket[1].getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            number = br.readLine();
            String msg[] = number.split("@");
            System.out.println("Message received from client " + msg[0] +" is "+msg[1]);
            System.out.println("Elected: " + msg[0]);
            
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
