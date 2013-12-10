package com.netcracker.CuratorWork.library.factory;

import com.netcracker.CuratorWork.library.issue.Encyclopedia;
import com.netcracker.CuratorWork.library.issue.Issue;

public class EncyclopediaFactory implements IssueFactory{

	@Override
	public Issue createIssue(String title, int pages) {
		Issue i=new Encyclopedia();
		i.setTitle(title);
		i.setPages(pages);
		return i;
	}

}
