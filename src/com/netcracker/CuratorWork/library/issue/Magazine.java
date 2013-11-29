package com.netcracker.CuratorWork.library.issue;

public class Magazine extends Issue{

	private String number;
	private String category;
	
	public Magazine(){
		super();
	}
	
	public Magazine(String title, String number, String category, int pages){
		super(title,pages);
		this.setNumber(number);
		this.setCategory(category);
	}
	
	@Override
	public void getFullInformation() {
		System.out.println("Title: " + getTitle() + ", number: "+ getNumber() + ", category: " + getCategory() + ", pages: "	+ getPages()+" Magazine.");

	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	

}
