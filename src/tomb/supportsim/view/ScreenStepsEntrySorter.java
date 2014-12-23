package tomb.supportsim.view;

import tomb.supportsim.control.Cache;
import tomb.supportsim.models.ScreenstepsTableEntry;
import tomb.supportsim.models.jira.IssueShell;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 23/12/14 Time: 13:24
 */
public class ScreenStepsEntrySorter
{
  public static void run()
  {
    List<ScreenstepsTableEntry> screenstepsEntryList = Cache.getInstance().getScreenstepsEntryList();
    Collections.sort( screenstepsEntryList, new ScreenStepsEntryComparator() );
  }
}
