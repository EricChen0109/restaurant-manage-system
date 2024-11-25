package GUI;

public class Staff {
	String name;
	String jobPosition;
	String pay;
	String entryTime;
	String acount;
	String passwd;
	
	public Staff(String name, String jobPosition, String pay, String entryTime, String acount, String passwd) {
		this.name = name;
		this.jobPosition = jobPosition;
		this.pay = pay;
		this.entryTime = entryTime;
		this.acount = acount;
		this.passwd = passwd;
	}
	
	public String getName() {
		return name;
	}
	
	public String getJobPosition() {
		return jobPosition;
	}
	
	public String getPay() {
		return pay;
	}
	
	public String getEntryTime() {
		return entryTime;
	}
	
	public String getAcount() {
		return acount;
	}
	
	public String getPasswd() {
		return passwd;
	}
}
