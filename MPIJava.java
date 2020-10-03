import mpi.*;  
    
  
public class MPIJava {  
 
     public static void main(String[] args) throws Exception { 
 
         MPI.Init(args); 
		 
		 long start_t,end_t,total_t;
         int rank = MPI.COMM_WORLD.Rank() ; //The current process.
         int size = MPI.COMM_WORLD.Size() ; //Total number of processes
         int peer ; 
		 int i;
 
         double buffer [] = new double[10]; 
         int len = 10 ;
         int tag = 100 ; 
 
         if(rank == 0) { 
 
             for(i = 0; i< buffer.length; i++){
				buffer[i] = Math.random()*1000;				
			 }				
             peer = 1 ; 
             MPI.COMM_WORLD.Send(buffer, 0, len, MPI.DOUBLE, peer, tag) ; 
             System.out.println("process <"+rank+"> sent a msg to "+ "process <"+peer+">") ; 
 
         } 
		 else if(rank == 1) { 
            
			 start_t = System.currentTimeMillis();
			 
			 peer = 0 ; 
             Status status = MPI.COMM_WORLD.Recv(buffer, 0, buffer.length, MPI.DOUBLE, peer, tag); 
             System.out.println("process <"+rank+"> recv'ed a msg\n"+"\tsource <"+status.source+"> \n"+ "\ttag    <"+status.tag   +"> \n"+ "\tcount  <"+status.count +">") ; 
			 System.out.println("\nData Received: ");
			 for(i = 0; i < buffer.length; i++){
				 System.out.println(buffer[i]);
			 }
			 System.out.println("\n\nSquares: ");
			 for(i = 0; i < buffer.length; i++){
				 System.out.println(Math.pow(buffer[i],2));
			 }
			 
			 end_t = System.currentTimeMillis();
			 total_t = (long)(end_t - start_t);
			 System.out.print("\n Total time taken by MPI in milli seconds: " +  (long)(total_t ) );
			 
         }
         MPI.Finalize(); 
     }  
 }
