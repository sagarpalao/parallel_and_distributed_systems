import java.rmi.*;
public interface Interface extends Remote{
	public String displayDate() throws RemoteException;
	public String displayTime() throws RemoteException;
}
