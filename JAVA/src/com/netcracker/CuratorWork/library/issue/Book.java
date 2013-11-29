package com.netcracker.CuratorWork.library.issue;


public class Book extends Issue {
	private String author;
	private String publisher;
	private int year;

	public Book() {
		super();
	}

	public Book(String title, String author, int pages, String publisher,int year) {
		super(title,pages);
		this.setAuthor(author);
		this.setPublisher(publisher);
		this.setYear(year);
	
	}

	@Override
	public void getFullInformation() {
		System.out.println("Title: " + getTitle() + ", author: "+ getAuthor() + ", pages: " + getPages() + ", publisher: "+ getPublisher() + ", year: " + getYear()+" Book.");
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
