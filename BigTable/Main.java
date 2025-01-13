
// startup code for programming assignment P2 (csi2110/csi2510 2024)
// the use of this startup code is optional
// this code helps you parsing the input


import java.io.*;
import java.util.*;


class Main  {

	static int L;//Ferry length
	static int n;// Number of cars
	static int bestK;//best Maximum Cars in Ferry 
	static int[] currX;
	static int[] bestX;
	static boolean[][] state;//Visted or not 
	static ArrayList<Integer> carL;//length of cars List

    

	
    //**** many other member variables will be added by you ******/
	//**** many other member methods will be added by you  *******/
	//**** you are allowed to create a new class, but it must be added to this file  as the online judge accepts a single java file **/



   //currK numbers of cars that have already been placed
   //currS remaing space on the port(left ) side of the ferry 
	static void  BackTrackSolve(int currK,int currS) {
         
		//update the best Solution 
        if(currK>bestK){
			bestK=currK;
			bestX = new int[bestK];

			for(int i=0; i<bestK; i++)
			{					
				bestX[i] = currX[i]; 
			}
		}

		if(currK<n && currS>=0){
         
			//Total Car placed 

			int totalCarPlaced=0;
            for(int i=0;i<currK;i++){
                totalCarPlaced=totalCarPlaced+carL.get(i);
			}

			//remaining right
			int remainingRight=L-(totalCarPlaced-(L-currS));

			//add Car to left 
			if(currS>=carL.get(currK) && state[currK+1][currS-(carL.get(currK))]==false){
				currX[currK]=1;
				int newS=currS-carL.get(currK);
				BackTrackSolve(currK+1, newS);
				state[currK+1][newS] = true;
			}

			//add Car to Right 

			if(remainingRight>=carL.get(currK) && state[currK+1][currS] == false){
                currX[currK]=0;
				BackTrackSolve(currK+1, currS);
				state[currK+1][currS] = true;
}
}
		
}
	
	// reads each problem from input file and call method to solve and print output   
	public void process() throws FileNotFoundException {
	    
		Scanner scanner = new Scanner(System.in);
	
		if (scanner.hasNextInt()) {
	        int numTests=scanner.nextInt(); // reads the number of test cases
		    for (int i=0; i< numTests; i++) {
		       if (i>0) System.out.println(); // printing line to standard output to separate outputs for problems
			   carL=new ArrayList<Integer>();
			   //handle incomplete testCases

			   if (scanner.hasNextInt()) {
		       int cNum=scanner.nextInt(); // this line contains the length of the ferry (L) in meters
			   L=0;
		       if (cNum !=0) { 
		    	   L=cNum*100; // convert L from meters to centimeters
       		       while ((cNum=scanner.nextInt()) !=0) // this reads final line containing 0
		    	       carL.add(cNum); // this reads the length of each car
		       }
		      
		       // **** call a method do solve the current problem: 
			   // at this point L contains the length of the ferry and integers contains the length of the cars
		       // **** call a method to print solution of the current problem
			   //

               if (carL.isEmpty() || L<carL.get(0)) {
				System.out.println("0");
				continue; // Skip this test case
			}

			   n=carL.size();//number of cars 
			   currX=new int[n];
			   bestX=new int[n];
			   state = new boolean[n + 1][L + 1]; // Initialize state table
               bestK=0;

			   BackTrackSolve(0,L);


			   printSolution();
		    }	 
			else{
				System.out.println("0");
                break;
			}   
	
        }
		
	}
}

	static void printSolution() {

         System.out.println(bestK);

		 for(int i = 0; i < bestK; i++){
			if(bestX[i] == 1){
				System.out.println("port");
			}
			else{
				System.out.println("starboard");
		 }
}
}


	
	
	public static void main(String[] args) {
	
        Main solver= new Main();
        
        try {
        solver.process();
        }
        catch(FileNotFoundException e) {
        	System.err.println("Error file not found!");
        }
		catch(Exception e) {
			System.err.println("Some error exception found!");
		}
		
	}

}

