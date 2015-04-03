package processes;


import resources.Order;
import resources.PlacedOrders;
import utility.Clock;

public class Cook extends Thread{

	private int cookId;
	//private boolean isBusy = false;
	
	
	
	public Cook(int id){
		cookId = id;
	
	}
	
	
	public void run(){
		while(Clock.getInstance().getCurrentTime() < 120){
			
			Order order = takeOrder();
			System.out.println("At time "+ Clock.getInstance().getCurrentTime() + " Cook " + cookId + " started working on order of Diner "+ order.getDinerId() );
			prepareFood(order);
			
		}
	}


	private synchronized void prepareFood(Order order) {
		
		BurgerMaker burgerMaker = new BurgerMaker(order.getNoOfBurgers(), order.getDinerId());
		burgerMaker.start();
		
		int noOfFries = order.getNoOfFriesOrder();
		
		
		FriesMaker friesMaker = null; ;
		if(noOfFries >0){
			friesMaker = new FriesMaker(noOfFries,order.getDinerId());
			friesMaker.start();
		}
		
		SodaFiller sodaFiller = null;
		if(order.isCokeOrdered()){
			sodaFiller = new SodaFiller(order.getDinerId());
			sodaFiller.start();
			
		}
		
		
		try {
			burgerMaker.join();
		
			if(noOfFries >0){
				friesMaker.join();
			}
			
			if(order.isCokeOrdered()){
				sodaFiller.join();
			}
			synchronized(order){
				System.out.println("At time " + Clock.getInstance().getCurrentTime() + " Diner "+order.getDinerId() +" 's order is complete");
				order.setComplete(true);
				order.notify();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
	}


	private  Order takeOrder() {
		Order order = PlacedOrders.getInstance().takeOrder();
		return order;
		
	}
	
	
	
}
