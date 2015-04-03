package resources;

import utility.Clock;

public class SodaMachine extends Machine{
	
	private boolean isBusy;
	
	private static SodaMachine sodaMachine;
	
	private SodaMachine(){
		
	}
	
	public static SodaMachine getInstance(){
		if(sodaMachine == null){
			sodaMachine = new SodaMachine();
		}
		
		return sodaMachine;
	}
	
	public synchronized void fillCoke(int dinerId){
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
		System.out.println("At time "+ startTime + " Soda Machine being used for Diner's "+dinerId+" order");
		while(startTime + 1 > Clock.getInstance().getCurrentTime()){
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
