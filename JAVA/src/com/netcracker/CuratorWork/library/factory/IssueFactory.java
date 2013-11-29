package com.netcracker.CuratorWork.library.factory;

import com.netcracker.CuratorWork.library.issue.Issue;

public interface IssueFactory {
	public Issue createIssue(String title, int pages);
}
