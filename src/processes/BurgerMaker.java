package processes;

import resources.BurgerMachine;

public class BurgerMaker extends Thread{
	
	int noOfBurgers;
	int dinerId;
	
	public BurgerMaker(int noOfBurgers, int dinerId){
		this.noOfBurgers = noOfBurgers;
		this.dinerId = dinerId;
	}
	
	public void run(){
		
		BurgerMachine burgerMachine = BurgerMachine.getInstance();
		for(int i=0 ;i< noOfBurgers;i++){
			burgerMachine.makeBurger(dinerId);
		}
		
	}

}
