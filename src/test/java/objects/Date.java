package objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {


	private String[] months=new String[]{"january","february","march","april","may","june","july","august","september","october","november","december"};
	private   String currentDate  = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	private	  int currentDay = Integer.parseInt(currentDate.substring(0, 2));
	private    int currentMonth = Integer.parseInt(currentDate.substring(3, 5));
	private    int currentYear = Integer.parseInt(currentDate.substring(6));
	private    int vacation_length=14;
	private    int monthSize;
	

	
	
	//getters
	public String[] getMonths() {
		return months;
	}

	public int getCurrentDay() {
		return currentDay;
	}


	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public int getVacation_length() {
		return vacation_length;
	}

	
	public int getMonthSize() {
		return monthSize;
	}


	//methods
	public int setDesireYear() {
		return (int) Math.round(Math.random()*2)+2022;
	}
	public int setDesireMonth() {
		return (int)Math.ceil(Math.random()*12);
	}
	public int setDesireDay() {
		
	    switch(currentMonth) {
	     
			    case 2 : 
			    	monthSize=29;
			    	return (int)Math.round(Math.random()*29);
			    	
			    case 4 : 
			    	monthSize=30;
			    	return (int)Math.round(Math.random()*30);
			    	
			    case 6 : 
			    	monthSize=30;
			    	return (int)Math.round(Math.random()*30);
			    	
			    case 9 : 
			    	monthSize=30;
			    	return (int)Math.round(Math.random()*30);
			    
			    case 11 : 
			    	monthSize=30;
			    	return (int)Math.round(Math.random()*30);
			    	
			    default:
			    	monthSize=31;
			    	return (int)Math.round(Math.random()*31);
	    }
    }

	
}
