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
import java.net.Socket;

/**
 *
 * @author sagar
 */
public class PC2 {

    private static Socket socket;
    
    static int current_time = 40;
    
    public static void main(String[] args)
    {
        try
        {
            String host = "localhost";
            int port = 25000;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Clock value received from server : " +message);
            System.out.println("Current Clock Value: "+ current_time);
            double serverclock = Double.parseDouble(message);
            
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            String number = String.valueOf((current_time));
 
            String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Difference in system clock value sent to server: " + sendMessage);
 
            InputStream is2 = socket.getInputStream();
            InputStreamReader isr2 = new InputStreamReader(is2);
            BufferedReader br2 = new BufferedReader(isr2);
            String message2 = br2.readLine();
            String msg[] = message2.split("@");
            System.out.println("Updated Displacement value received from server : " +msg[0]);
            System.out.println("New system clock value: "+ msg[1]);
            
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
