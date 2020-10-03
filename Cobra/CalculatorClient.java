import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class CalculatorClient
{
  static Calculator CalculatorImpl;

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "Calculator";
        CalculatorImpl = CalculatorHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtained a handle on server object: " + CalculatorImpl);
        System.out.println("Add 5 + 4: " + CalculatorImpl.add(5,4));
		System.out.println("Add 5 - 4: " + CalculatorImpl.sub(5,4));
		System.out.println("Add 5 * 4: " + CalculatorImpl.multiply(5,4));
        CalculatorImpl.shutdown();

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }
}
