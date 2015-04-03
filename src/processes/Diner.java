package processes;

import application.Restaurant;
import resources.Order;
import resources.PlacedOrders;
import resources.Table;
import resources.TableArray;
import utility.Clock;

public class Diner extends Thread{
	
	private Order order;
	private Cook assignedCook;
	private Table assignedTable;
	private TableArray tableArray;
	private int timeArrived;
	private int dinerId;
	
	private static int noOfDinersProcessed;
	

	public Diner(int dinerId, int timeArrived, Order order, TableArray tableArray) {
		this.dinerId = dinerId;
		this.timeArrived = timeArrived;
		this.order = order;
		this.tableArray = tableArray;
		//this.setPriority(dinerId);
		
	}
	
	public void run(){
		
		enterRestaurant();
		getSeated();
		placeOrder();
		eat();
		leaveRestaurant();	
		
		
	}
	
	private void getSeated() {
		assignedTable = tableArray.assignTable(dinerId);
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Cook getAssignedCook() {
		return assignedCook;
	}

	public void setAssignedCook(Cook assignedCook) {
		this.assignedCook = assignedCook;
	}

	public Table getAssignedTable() {
		return assignedTable;
	}

	public void setAssignedTable(Table assignedTable) {
		this.assignedTable = assignedTable;
	}

	public int getTimeArrived() {
		return timeArrived;
	}

	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}

	public int getDinerId() {
		return dinerId;
	}

	public void setDinerId(int dinerId) {
		this.dinerId = dinerId;
	}


	public void enterRestaurant(){
		System.out.println("At time " + Clock.getInstance().getCurrentTime() + " Diner "+ dinerId + " entered the restaurant");
	}
	
	public void leaveRestaurant(){
		System.out.println("At time " + Clock.getInstance().getCurrentTime() + " Diner "+ dinerId + " left the restaurant");
		
		tableArray.releaseTable(assignedTable);
		
		if(dinerId == Restaurant.getNoOfDiners() -1){
			System.exit(0);
		}
		
		
	}
	
	public void placeOrder(){
		System.out.println("At time " + Clock.getInstance().getCurrentTime() + " Diner "+ dinerId + " got seated on table "
				+ assignedTable.getId() + " and placed the order");
			PlacedOrders.getInstance().addOrder(order);
			
			synchronized(order){
				while(!order.isComplete()){
					try {
						
						order.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	
	
	
	public void eat(){
		int startEatTime = Clock.getInstance().getCurrentTime();
		System.out.println("At time " + startEatTime + " Diner "+ dinerId + " starts eating");
//		try {
//			sleep(31000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		while(startEatTime + 30 > Clock.getInstance().getCurrentTime()){
			try {
				sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static int getNumberOfDinersProcessed() {
		
		return noOfDinersProcessed;
	}
	
	

}
