package com.netcracker.CuratorWork.library.issue;

import com.netcracker.CuratorWork.library.Library;

public abstract class Issue implements Library{
    private String title;
    private int pages;
	
	public abstract void getFullInformation();

	public Issue(){
		
	}
	public Issue(String title, int pages){
		setTitle(title);
		setPages(pages);
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
