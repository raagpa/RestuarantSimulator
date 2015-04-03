package resources;

public class Table {

	private int id;
	private boolean isBusy;
	
	public Table(int id, boolean isBusy){
		this.id = id;
		this.isBusy = isBusy;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
		
}
