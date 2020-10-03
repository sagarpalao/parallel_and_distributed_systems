import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Server extends UnicastRemoteObject implements Interface{
    
	public Server() throws RemoteException{
    	System.out.println("Server id ready to serve...");
	}
	public String displayDate() throws RemoteException{
    	Date d = new Date();
    	return d.toString();
	}
	public String displayTime() throws RemoteException{
    	Date d = new Date();
    	return d.getHours() + " : " + d.getMinutes() + " : " + d.getSeconds();
	}
	public static void main(String...args){
    	try{
        	Server s = new Server();
        	Naming.rebind("datetimeserver", s);	 
    	}
    	catch(Exception e){
        	e.printStackTrace();
    	}	 
	}    
}
