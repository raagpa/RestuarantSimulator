package resources;

import utility.Clock;

public class BurgerMachine extends Machine {
	
	private boolean isBusy;
	
	private static BurgerMachine burgerMachine;
	
	private BurgerMachine(){
		
	}
	
	public static BurgerMachine getInstance(){
		if(burgerMachine == null){
			burgerMachine = new BurgerMachine();
		}
		
		return burgerMachine;
	}
	
	public synchronized void makeBurger(int dinerId){
		while(isBusy){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		isBusy = true;
		
		int startTime = Clock.getInstance().getCurrentTime();
		System.out.println("At time " + startTime +" Burger Machine being used for Diner's "+dinerId+" order ");
		while(startTime + 5 > Clock.getInstance().getCurrentTime()){
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		isBusy = false;
		notifyAll();
	}

}
