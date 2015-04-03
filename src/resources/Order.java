package resources;

public class Order {

	public Order(int dinerId,int noOfBurgers, int noOfFries, int noOfCoke) {
		this.dinerId = dinerId;
		this.noOfBurgers = noOfBurgers;
		this.noOfFriesOrder = noOfFries;
		if(noOfCoke == 1){
			isCokeOrdered = true;
		}
		this.isComplete = false;
	}
	
	private int dinerId;
	private int noOfBurgers;
	private int noOfFriesOrder;
	private boolean isCokeOrdered;
	private boolean isComplete;
	
	public int getDinerId() {
		return dinerId;
	}
	
	public int getNoOfBurgers() {
		return noOfBurgers;
	}
	
	public int getNoOfFriesOrder() {
		return noOfFriesOrder;
	}

	public boolean isCokeOrdered() {
		return isCokeOrdered;
	}

	public boolean isComplete() {
		// TODO Auto-generated method stub
		return isComplete;
	}

	public void setComplete(boolean b) {
		isComplete = b;
		
	}

}
