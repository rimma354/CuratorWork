package com.netcracker.CuratorWork.library.observer;

import com.netcracker.CuratorWork.library.LibraryStorage;
import com.netcracker.CuratorWork.library.issue.Issue;


public class ConcreteObserver  implements Observer{
	private LibraryStorage subject;
	
	public ConcreteObserver(LibraryStorage lib){
		this.subject=lib;
	}
	@Override
	public void issueAdded(Issue i) {
		System.out.println("New issue was added");
		i.getFullInformation();
	}

}
