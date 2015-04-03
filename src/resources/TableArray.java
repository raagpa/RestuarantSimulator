package resources;

import application.Restaurant;

public class TableArray {

	
	private Table table[];
	private static int noOfTables;
	
	public TableArray(int noOfTables){
		TableArray.noOfTables= noOfTables;
		table = new Table[noOfTables];
		
		for(int t=0 ;t <TableArray.noOfTables;t++){
			table[t] = new Table(t+1,false);
		}
	}
	
	public  synchronized Table  assignTable(int dinerId) {
		
		int tableId = -1;
		
		while((tableId = returnFreeTable()) == -1){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return table[tableId];
		
	}

	private int returnFreeTable() {
		for(int i=0 ; i<TableArray.noOfTables ;i++){
			if(!table[i].isBusy()){
				table[i].setBusy(true);
				return i;
			}
		}
		
		return -1;
	}

	public  synchronized void releaseTable(Table assignedTable) {
		assignedTable.setBusy(false);
		notify();
		
	}

	
	public static void setNoOfTables(int noOfTables) {
		TableArray.noOfTables = noOfTables;
	}

}
