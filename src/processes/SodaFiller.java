package processes;

import resources.SodaMachine;

public class SodaFiller extends Thread {
	
	private int dinerId;
	
	public SodaFiller(int dinerId){
		this.dinerId = dinerId;
	}

	public void run(){
			
			SodaMachine sodaMachine = SodaMachine.getInstance();
			sodaMachine.fillCoke(dinerId);
			
			
		}
}
