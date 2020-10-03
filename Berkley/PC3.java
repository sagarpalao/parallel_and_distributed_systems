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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sagar
 */
public class PC3 {

    private static Socket socket[] = new Socket[2];
    
    static double current_time[] = {0,0,0};
 
    public static void main(String[] args)
    {
        current_time[0] = 42;
        try
        {
 
            int port[] = {25001,25000};
            ServerSocket serverSocket[] = new ServerSocket[2];
            serverSocket[0] = new ServerSocket(port[0]);
            serverSocket[1] = new ServerSocket(port[1]);
            System.out.println("Server Started and listening to the port 25000 and 25001");
 
            int i = 1;
            while(i != 3){              
                socket[i - 1] = serverSocket[i - 1].accept();
                
                OutputStream os = socket[i - 1].getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                String msg1 = String.valueOf(current_time[0]) + "\n";
                bw.write(msg1);
                System.out.print("\nMessage sent to the client " + i + " is " + msg1);
                bw.flush();
                
                InputStream is = socket[i - 1].getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                System.out.println("Clock received from client" + i + " is "+number);
                current_time[i] = Double.parseDouble(number);
                i++;
                             
            }
            
            i = 0;
            double d[] = new double[3];
            
            for(i=0;i<3;i++){
                d[i] = current_time[i] - current_time[0];
            }
            
            double sum = 0;
            for(i=0;i<3;i++){
                sum = sum + d[i];
            }
            double avg = sum/3.0;
            
            d[0] = avg;
            current_time[0]= d[0] + current_time[0];
            d[1] = current_time[0] - current_time[1];
            d[2] = current_time[0] - current_time[2];
            
            System.out.println("\nNew System clock of server: " + current_time[0]+"\n");
            
            i = 1;
            String returnMessage;
            while(i!=3){
                returnMessage = String.valueOf(d[i]+"@"+String.valueOf(current_time[i] + d[i]));
                OutputStream os = socket[i - 1].getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
                i++;
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
                int i=0;
                for(i=0;i<2;i++)
                    socket[i].close();
            }
            catch(Exception e){}
        }
    }
}
