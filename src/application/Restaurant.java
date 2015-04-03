package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import processes.Cook;
import processes.Diner;
import resources.BurgerMachine;
import resources.FriesMachine;
import resources.Order;
import resources.SodaMachine;
import resources.Table;
import resources.TableArray;
import utility.Clock;
import utility.DinerEntryManager;


public class Restaurant {
	
	private static Cook cook[];
	
	
	BurgerMachine burgerMachine;
	SodaMachine sodaMachine;
	FriesMachine friesMachine;
	
	private static int noOfDiners;

	private static int noOfCooks;
	
	public static int getNoOfDiners() {
		return noOfDiners;
	}


	public static int getNoOfCooks() {
		return noOfCooks;
	}

	
	
	
	/**
	 * main() method 
	 * 
	 * @param String args[]
	 * @return void
	 */
	public static void main(String[] args) {

		String userDir =System.getProperty("user.dir");
		Diner [] dinersList = Restaurant.readInput(userDir+"/bin/test1");

		Clock clock  = Clock.getInstance();
		clock.start();
		
		Cook cook;
		for(int i=0 ;i < Restaurant.getNoOfCooks() ;i++){
			cook = new Cook(i+1);
			cook.start();
			
		}
		
		DinerEntryManager entryManager = new DinerEntryManager(dinersList);
		entryManager.start();
		
		


	}
	
	/**
	 * reads the input from a specified file or Std in.
	 * @param String file
	 * @return void
	 */
	private static Diner[] readInput(String file)
	{
		BufferedReader reader = null;
		Diner[] dinersList = null;
		
		try{
			if(null == file){
				reader = new BufferedReader(new InputStreamReader(System.in));
			}else{
				reader = new BufferedReader(new FileReader(file));
			}
			
	
			if(reader != null){
				
				Restaurant restaurant = new Restaurant();				
				Restaurant.setNoOfDiners(reader);				
				TableArray tableArray = Restaurant.instantiateTableArray(reader);	
				Restaurant.setNoOfCooks(reader);
				
				
				dinersList = restaurant.populateDiners(reader , tableArray);
				
				
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dinersList;
	}

	private Diner[] populateDiners(BufferedReader reader , TableArray tableArray) throws IOException {
		String line = "";
		Diner[] dinersList = new Diner[noOfDiners];
		int i=0;
		while((line = reader.readLine()) != null){
			
			if(line != null){
				String input[] = line.split(",");
				int timeArrived = Integer.parseInt(input[0]);
				int noOfBurgers = Integer.parseInt(input[1]);
				int noOfFries = Integer.parseInt(input[2]);
				int noOfCoke = Integer.parseInt(input[3]);
				
				Order order  = new Order(i+1,noOfBurgers,noOfFries,noOfCoke);
				
				dinersList[i] = new Diner(i+1,timeArrived,order,tableArray);
				i++;
				
			}
			
		}
		
		return dinersList;
	}

	private static TableArray instantiateTableArray(BufferedReader reader)
			throws IOException {
		String input;
		
		if((input = reader.readLine() )!=null){
			return new TableArray(Integer.parseInt(input));
		}
		
		return null;
	
	}

	private static void setNoOfCooks(BufferedReader reader)
			throws IOException {
		String input;
	
		if((input = reader.readLine() )!=null){
			noOfCooks = Integer.parseInt(input);
		}
		
	}

	private static void setNoOfDiners(BufferedReader reader)
			throws IOException {
		String input;
		
		if((input = reader.readLine() )!=null){
			noOfDiners = Integer.parseInt(input);
		}
		
	}

	

	
	public static Cook[] getCook() {
		return cook;
	}

}
