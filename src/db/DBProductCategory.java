package db;

public class DBProductCategory implements IFDBProductCategory {
	 private DataAccess _da;
	 public DBProductCategory()
	 {	
		 _da = DataAccess.getInstance();
	 }
	 
}
