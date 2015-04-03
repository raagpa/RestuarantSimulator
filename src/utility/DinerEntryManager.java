package utility;

import application.Restaurant;
import processes.Diner;


public class DinerEntryManager extends Thread {
	
	private Diner diner[];
	
	public DinerEntryManager(Diner[] diner){
		this.diner = diner;
	}
	
	public void run(){
		manageDinerEntry();
	}
	
	public  void manageDinerEntry(){
		
		int j=0;
		while(Clock.getInstance().getCurrentTime() <= 120 &&
				j < Restaurant.getNoOfDiners()){
			for(int i=j;i< Restaurant.getNoOfDiners(); i++){
				if(diner[i].getTimeArrived() == Clock.getInstance().getCurrentTime() ){		
					
					diner[i].start();
					j++;
				}
			}
		
		}
	}

}
