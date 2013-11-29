package com.netcracker.CuratorWork.library.issue;

public class Encyclopedia extends Issue {
	private String volume;
	private int year;
	
	public Encyclopedia(){
		super();
	}
	
	public Encyclopedia(String title, int pages,String volume,int year){
		super(title,pages);
		this.setVolume(volume);
		this.setYear(year);
	}
	
	@Override
	public void getFullInformation() {
		System.out.println("Title: " + getTitle() + ", pages: " + getPages() + ", volume : "+ getVolume () + ", year: " + getYear()+" Encyclopedia.");
		
	}

	
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
