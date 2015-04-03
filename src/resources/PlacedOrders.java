package resources;

import java.util.ArrayList;

public class PlacedOrders {
	
	private ArrayList <Order>placedOrderList;
	private ArrayList <Order>inProcessOrderList;
	
	private static PlacedOrders instance;
	
	private PlacedOrders(){
		placedOrderList = new ArrayList<Order>();
		inProcessOrderList =  new ArrayList<Order>();
	}
	
	public static PlacedOrders getInstance(){
		if(instance==null){
			instance = new PlacedOrders();
		}
		
		return instance;
	}
	
	public synchronized void addOrder(Order order){
		placedOrderList.add(order);
		notifyAll();
		
		
	}
	
	public synchronized Order takeOrder(){
		
		while(placedOrderList.size() == 0){
			try {
				//System.out.println("Waiting for order");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Order order = placedOrderList.get(0);
		placedOrderList.remove(0);
		return order;
		
	}
	
	
	
	
	
	
	

}
