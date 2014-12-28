package com.tombeadman.tdesk.view;

import com.tombeadman.tdesk.control.Cache;
import com.tombeadman.tdesk.models.ScreenstepsTableEntry;

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
