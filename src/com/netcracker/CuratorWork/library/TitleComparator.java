package com.netcracker.CuratorWork.library;

import java.util.Comparator;

import com.netcracker.CuratorWork.library.issue.Issue;

public class TitleComparator implements Comparator<Issue>{

	@Override
	public int compare(Issue o1, Issue o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}
	
}
