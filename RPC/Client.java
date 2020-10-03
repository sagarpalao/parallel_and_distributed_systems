import java.rmi.*;

public class Client {
	public static void main(String...args){
    	try{
        	Interface i = (Interface)Naming.lookup("datetimeserver");
        	System.out.println("Current date from server...");
        	System.out.println(i.displayDate());
        	System.out.println(i.displayTime());
    	}
    	catch(Exception e){
        	e.printStackTrace();
    	}
	}
}
