package com.netcracker.CuratorWork.library.issue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Newspaper extends Issue {
	private String title;
	private String number;
	private int pages;
	private String date;

	public Newspaper() {
		super();
	}

	public Newspaper(String title, String number, int pages, Date date) {
		super(title,pages);
		this.setNumber(number);
		this.setDate(date);
	}

	@Override
	public void getFullInformation() {
		System.out.println("Title: " + getTitle() + ", number: "+ getNumber() + ", pages: " + getPages() + ", date: "+ getDate()+" Newspaper.");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.date = dateFormat.format(date);
	}

}

