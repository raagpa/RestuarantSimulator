package processes;

import resources.FriesMachine;

public class FriesMaker extends Thread {
	
	private int noOfFries;
	private int dinerId;
	
	public FriesMaker(int noOfFries,int dinerId){
		this.noOfFries = noOfFries;
		this.dinerId = dinerId;
	}
	
	public void run(){
		
		FriesMachine friesMachine = FriesMachine.getInstance();
		for(int i=0 ;i< noOfFries;i++){
			friesMachine.makeFries(dinerId);
		}
		
	}

}
