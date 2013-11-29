package com.netcracker.CuratorWork.library.factory;

import com.netcracker.CuratorWork.library.issue.Book;
import com.netcracker.CuratorWork.library.issue.Issue;

public class BookFactory implements IssueFactory{

	@Override
	public Issue createIssue(String title, int pages) {
		Issue i=new Book();
		i.setTitle(title);
		i.setPages(pages);
		return i;
	}

}
