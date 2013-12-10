package com.netcracker.CuratorWork.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.netcracker.CuratorWork.library.factory.BookFactory;
import com.netcracker.CuratorWork.library.factory.IssueFactory;
import com.netcracker.CuratorWork.library.issue.Book;
import com.netcracker.CuratorWork.library.issue.Encyclopedia;
import com.netcracker.CuratorWork.library.issue.Issue;
import com.netcracker.CuratorWork.library.issue.Magazine;
import com.netcracker.CuratorWork.library.issue.Newspaper;
import com.netcracker.CuratorWork.library.observer.ConcreteObserver;
import com.netcracker.CuratorWork.library.observer.Observer;



public class LibraryStorage {
	private IssueFactory factory;
	private ArrayList<Issue> issues=new ArrayList<Issue>();
	private ArrayList<Observer> observers=new ArrayList<Observer>();
	
	public  void setIssueFactory(IssueFactory factory) {
		this.factory=factory;
	}
	
	public IssueFactory getFactory() {
		return factory;
	}
	
	public void sortByTitle (){
		Collections.sort(issues,new TitleComparator());
		System.out.println("Sorted issues by title:");
		for (Issue is: issues){
			is.getFullInformation();
		}
	}
	public void addNewIssue(Issue i){
		this.issues.add(i);
		notifyAddedIssue(i);
	}
	
	public static void main(String[] args) {
		LibraryStorage myLib=new LibraryStorage();
		myLib.registerObserver(new ConcreteObserver(myLib));
		
		Issue book1=new Book("Thinking in Java","Bruce Eckel",1120,"Prentice Hall Ptr",2002);
		myLib.addNewIssue(book1);
		
		Issue book2=new Book("Head First Java","Kathy Sierra",688,"O'Reilly Media",2005);
		myLib.addNewIssue(book2);
		
		Issue magazine1=new Magazine("CHIP","11-2013","IT",155);
		myLib.addNewIssue(magazine1);
		
		
		Issue newspaper1=new Newspaper("Daily press","11-2013",15,new Date());
		myLib.addNewIssue(newspaper1);
		
		Issue encyclopedia1=new Encyclopedia("World",1270,"Geography",2011);
		myLib.addNewIssue(encyclopedia1);
		
		myLib.setIssueFactory(new BookFactory());
		Issue book3=myLib.getFactory().createIssue("Some book", 100);
		myLib.addNewIssue(book3);
		
		myLib.sortByTitle();
		
		
	}

	    public void notifyAddedIssue(Issue i){
	    	for (Observer obs:observers){
				obs.issueAdded(i);
			}
	     }
	    
	    public void registerObserver(Observer obs){
	    	observers.add(obs);
	    }
	    
	    public void removeObserver(Observer obs){
	    	int i=observers.indexOf(obs);
	    	if(i>=0){
	    		observers.remove(i);
	    	}
	    }
	

}
