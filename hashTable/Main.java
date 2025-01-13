
// startup code for programming assignment P2 (csi2110/csi2510 2024)
// the use of this startup code is optional
// this code helps you parsing the input


import java.io.*;
import java.util.*;


class Main  {

	static int L;//Length of the ferry 
	static ArrayList<Integer> carL;
	static int n;
	static int bestK;//best Maximum Cars in Ferry 
	static int[] currX;
	static int[] bestX;
	static int hashTableSize;//size of HashTable
	static int[] hashTable;//hashTable




	
	static int hashFuntion(int key, int value){
		return (key + value) % hashTableSize;
	}

	static void insert(int key, int value){
		int hashValue = key + value;
		int hashIndex = hashFuntion(key, value);
		while(hashTable[hashIndex] != -1)
		{
			++hashIndex;
			hashIndex%=hashTableSize; 
		}
		hashTable[hashIndex] = hashValue;
	}

	static boolean find(int key, int value)
	{
		int hashValue = key + value;
		int hashIndex = hashFuntion(key, value);

		while(hashTable[hashIndex] != -1)
		{
			if(hashTable[hashIndex] == hashValue)
			{
				return true;
			}
			
			++hashIndex;
			hashIndex= hashIndex% hashTableSize; 
		}
		return false;
	}



	//currK numbers of cars that have already been placed
   //currS remaing space on the port(left ) side of the ferry 
   static void  BackTrackSolve(int currK,int currS) {
         
	//update the best Solution 
	if(currK>bestK){
		bestK=currK;

		System.arraycopy(currX,0,bestX,0,currK);
	}

	if(currK<n && currS>=0){
	 
		//Total Car placed 

		int totalCarPlaced=0;
		for(int i=0;i<currK;i++){
			totalCarPlaced=totalCarPlaced+carL.get(i);
		}

		//remaining right
		int remainingRight=L-(totalCarPlaced-(L-currS));
        
		int key=currK+1;
	    int newS=currS-carL.get(currK);
		//add Car to left 
		if(currS>=carL.get(currK) && !find(key,newS)){
			currX[currK]=1;
			BackTrackSolve(currK+1, newS);
			insert(currK+1, newS);
		}

		//add Car to Right 

		if(remainingRight>=carL.get(currK) &&  !find(key,currS)){
			currX[currK]=0;
			BackTrackSolve(currK+1, currS);
			insert(currK+1, currS);

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
				////handle incomplete testCases
				if (scanner.hasNextInt()) {
		       int cNum=scanner.nextInt(); // this line contains the length of the ferry (L) in meters
			   L=0;
		       if (cNum !=0) { 
		    	   L=cNum*100; // convert L from meters to centimeters
       		       while ((cNum=scanner.nextInt()) !=0) // this reads final line containing 0
		    	       carL.add(cNum); // this reads the length of each car
		       }


			   if (carL.isEmpty() || L<carL.get(0)) {
				System.out.println("0");
				continue; // Skip this test case
			}
		      
		       // **** call a method do solve the current problem: 
			   // at this point L contains the length of the ferry and integers contains the length of the cars
		       // **** call a method to print solution of the current problem
			   //
               
			   n=carL.size();
			   hashTableSize=n+L;
			   hashTable=new int[hashTableSize];
			   Arrays.fill(hashTable, -1);
			   currX=new int[n];
			   bestX=new int[n];
                
               bestK=-1;

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
