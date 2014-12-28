package com.tombeadman.tdesk.view;

import com.tombeadman.tdesk.control.Cache;
import com.tombeadman.tdesk.models.jira.IssueShell;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 23/12/14 Time: 12:11
 */


/*
 * Dedicated class to sort the JiraIssue List by id, to be run proactively after the cache maps has been updated.
 */
public class JiraIssueSorter
{
  public static void run()
  {
    List<IssueShell> jiraIssues = Cache.getInstance().getJiraIssues();
    Collections.sort( jiraIssues,new JiraIssueComparator() );
  }

}
