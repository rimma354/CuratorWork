package com.netcracker.CuratorWork.library.factory;

import com.netcracker.CuratorWork.library.issue.Issue;
import com.netcracker.CuratorWork.library.issue.Newspaper;

public class NewspaperFactory implements IssueFactory{

	@Override
	public Issue createIssue(String title, int pages) {
		Issue i=new Newspaper();
		i.setTitle(title);
		i.setPages(pages);
		return i;
	}

}
