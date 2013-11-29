package com.netcracker.CuratorWork.library.factory;


import com.netcracker.CuratorWork.library.issue.Issue;
import com.netcracker.CuratorWork.library.issue.Magazine;

public class MagazineFactory implements IssueFactory {

	@Override
	public Issue createIssue(String title, int pages) {
		Issue i=new Magazine();
		i.setTitle(title);
		i.setPages(pages);
		return i;
	}

}
