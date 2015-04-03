package resources;

import utility.Clock;

public class FriesMachine extends Machine{
	
	private boolean isBusy;
	
	private static FriesMachine friesMachine;
	
	private FriesMachine(){
		
	}
	
	public static FriesMachine getInstance(){
		if(friesMachine == null){
			friesMachine = new FriesMachine();
		}
		
		return friesMachine;
	}
	
	public synchronized void makeFries(int dinerId){
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
		System.out.println("At time " +startTime +" Fries Machine being used for Diner's "+dinerId+" order");
		
		while(startTime + 3 > Clock.getInstance().getCurrentTime()){
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
