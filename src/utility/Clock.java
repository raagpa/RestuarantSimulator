package utility;

public class Clock extends Thread{
	
	private static int time = 1;
	private static Clock clock;
	
	private Clock(){
		
	}
	
	public static Clock getInstance(){
		if(clock == null){
			clock  = new Clock();
		}
		
		return clock;
	}
	
	public void run(){
		try {
			while(true){
				this.sleep(1000);
				time += 1;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCurrentTime(){
		return time;
	}
	
	
	

}
