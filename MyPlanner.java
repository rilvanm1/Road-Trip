import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MyPlanner {

	//Index is the number of itineries, calculated from permutaions genertated
	public static int index = 0;
	
	public static void main(String[] args) {
		 //fileName, Startcity are extracted from commandline
		 String fileName = "textfile.txt";
		 String startCity ="name";
		 String endCity ="end";
		 if (args.length > 0) { 
		 fileName = args[0];
		 startCity = args[1];
		 }
		  
		
		 String [] cities= new String [10];//Contains names of the cities
		 String [] travelOrder = new String [2];//Order to travel cities
		 int [][] distance = new int [10][10];//Distance between cities
		 String[] travelCities = new String[3628800];//All posssible itineries
		 double[] sumDistance = new double[3628800];//Sum of each possible itineries
		 int orderCity1=0;
		 int orderCity2=0;
		 boolean orderCondition = false;
		 int numCities=0;
		 int startCityIndex=0;

		//reading text file(Extracting cities and distance from the text file)	
		 try {
			numCities=ReadFileIntoArray(fileName,distance,cities, travelOrder);
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		
		 startCityIndex=findCityIndex(startCity,cities);//finding the index of the starting city extracted from text file
		 
		 //checking whether order matters or not
		 if(!travelOrder[0].equals("none")){
			 orderCity1 = findCityIndex(travelOrder[0],cities);
			 orderCity2 = findCityIndex(travelOrder[1],cities);
			 orderCondition = true;//if their is an order
		 }
		 
		 //Number of permutations according to the number of cities
		 int[] permutationArray = new int[numCities];
		 for(int f=0; f<permutationArray.length;f++){
			 permutationArray[f]=f;
		 }
	     
		 long starttime = System.nanoTime();
		 long estimatedTime;
		 permute(permutationArray, 0,distance,cities,travelCities, sumDistance,orderCondition,orderCity1,orderCity2,startCityIndex );
			
		 //index is number of itineries
		 if(index==0){
			System.out.println("No possible itinary");
		 }
		//Finding the minimum disytance of travel
		 else{
			int minimumloc = findminimum(sumDistance);
			System.out.println(travelCities[minimumloc]+"; Total Distance: "+ sumDistance[minimumloc] + " miles ");
			estimatedTime = System.nanoTime() - starttime; 
			//System.out.println(estimatedTime/1000000000);
		 }
		 
	}
	
	/* The following method extracts data from TravelCities.txt*/
	public static int ReadFileIntoArray(String file,int[][] distance, String[]cities, String[]travelOrder) throws IOException
	{
		String dataFileName = file;
		int line =0;
		int line1;
		
	
	     
	    int count = 0;
		Scanner TextFile = new Scanner(new File(dataFileName));
		  	 
		while (TextFile.hasNext())
		{	
		  	if(line==0){
		  			 
		  	for(int i=0; i<cities.length;i++){
		  		cities[i]=TextFile.next();
		  	}
		  	line++;
		  		
		  	}
		  	else
		  	if(line ==1){
		  		for(int j=0; j<travelOrder.length; j++){
		  		    travelOrder[j]=TextFile.next();
		  	    }
		  		line++;
		  	}
		  		 
		  	else{
		  		 for(int i=0; i<cities.length;i++){
		  		     distance[count][i] = TextFile.nextInt();
		  		 }
		  		 count++;
		  		 }
		  	}
				//  end of file detected
		    TextFile.close();
		  	return count;
	}
	
	/*Following method finds the index of the city from the array cities
	 * (which contains cities of travel extracted
	 * from TravelCities.txt
	 */
	 public static int findCityIndex(String city, String[]cities){
		int num=0;
		for(int r=0; r<cities.length;r++){
			if(city.equals(cities[r])){
				num=r;
				break;
		    }
		}
		return num;
	}
	 
	 /*Following method finds the itineries according to the permutations generated*/
	 public static void trip(int []a, int[][]distance,String[] cities, String[]travelcities, double[] sumDistance,boolean 
				condition, int city1, int city2,int start) {

		//condition means whether there is an order in travel or not
		//travelCities array stores all possible itineries as a string
		boolean permuted = false;
		double sum = 0;
		int prev=0;
		int cur;
		int startDes=start;
		prev=startDes;
		boolean ordercond = false;
		
		for (int i = 0; i< a.length; i++) {
			if(a[0]==startDes){
			
			if(condition==false){
				ordercond=true;
			}
				
			else if(i+1<a.length){
			if(a[i]==city1 && a[i+1]==city2){
				ordercond =true;
			}
			}
			if(a[i]==startDes){
				travelcities[index] = (cities[a[i]])+" ";
			}
			
			 else{
				 sum = sum+ distance[prev][a[i]];
				 travelcities[index]=travelcities[index].concat(distance[prev][a[i]]+ " miles to "+(cities[a[i]]) + " "+ ", from " +cities[a[i]]+" ");
				 prev=a[i];	
			 }
			permuted = true;
			
			}
		}
		
		if(permuted==true && ordercond==true){
			sum = sum + distance[prev][startDes];
			sumDistance[index]=sum;
			travelcities[index]=travelcities[index].concat( distance[prev][startDes]+ " miles to "+cities[startDes]);
			index = index+1;
			
		}
	 }	
	 /*following method generates permutations*/
	 public static void permute(int []a,int k, int[][] distance, String[] cities, String[]travelcities, double[] sumDistance,boolean 
				condition, int city1, int city2,int start) {
		
		if(k==a.length){
			trip(a,distance,cities,travelcities,sumDistance,condition,city1,city2,start);
		}
		else
		for (int i = k; i < a.length; i++) {
		int temp=a[k];
		a[k]=a[i];
		a[i]=temp;
		permute(a,k+1,distance,cities,travelcities,sumDistance,
				condition, city1, city2,start);
		temp=a[k];
		a[k]=a[i];
		a[i]=temp;
		}
	}
	 
	/*following method find the minimum traveling distance*/
	 public static int findminimum(double[] sumDistance){
			double minimum = sumDistance[0];
			int location = 0;
			for(int k=0; k<index;k++){
				if(sumDistance[k]<minimum){
					minimum=sumDistance[k];
					location = k;
				}
			}
			return location;
	}

			
	

}
