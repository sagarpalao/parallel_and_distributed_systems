/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds;

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
public class BPC3 {
    
    private static Socket socket;

    static int MYPOS = 2;
    
    public static void main(String[] args)
    {
        try
        {
           
            int port[] = {25001,25002,25003,25004};
            ServerSocket serverSocket = new ServerSocket(port[2]);
            System.out.println("Listening to the port "+ port[2]);
            
            String host = "localhost";
            InetAddress address = InetAddress.getByName(host);
            
            Socket ssocket;
            
            Socket socket[] = new Socket[4];
            
            int  i = MYPOS + 1;
                
            socket[2] = serverSocket.accept();
            InputStream is = socket[2].getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String number = br.readLine();
            System.out.println("Message received is "+number);

            String returnMessage = "OK\n";

            OutputStream os = socket[2].getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(returnMessage);
            System.out.println("Message sent is "+returnMessage);
            bw.flush();
            
            socket[2] = serverSocket.accept();
            is = socket[2].getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            number = br.readLine();
            System.out.println("Message received is "+number);

            returnMessage = "OK\n";

            os = socket[2].getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            bw.write(returnMessage);
            System.out.println("Message sent is "+returnMessage);
            bw.flush();
            
            String msgC = "";
            i = 0;
            while(i < 2)
            {                
                msgC = "3@C\n";
                socket[i] = new Socket(address, port[i]);
                os = socket[i].getOutputStream();
                osw = new OutputStreamWriter(os);
                bw = new BufferedWriter(osw);
                bw.write(msgC);
                System.out.println("Message sent to the PC " + i + " is "+msgC);
                bw.flush();
                i++;
            }
            String msg[] = msgC.split("@"); 
            System.out.println("Elected: "+ msg[0]);
            
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
