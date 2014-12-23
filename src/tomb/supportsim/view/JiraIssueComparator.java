package tomb.supportsim.view;

import tomb.supportsim.models.jira.IssueShell;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 23/12/14 Time: 12:16
 */
public class JiraIssueComparator extends NaturalOrderComparator
{

  public int compare( final Object o1, final Object o2 )
  {
    return super.compare( ( (IssueShell) o1 ).getKey(), ( (IssueShell) o2 ).getKey() );

  }
}
