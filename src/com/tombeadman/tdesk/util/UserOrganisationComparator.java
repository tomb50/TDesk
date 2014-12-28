package com.tombeadman.tdesk.util;

import com.tombeadman.tdesk.models.zendesk.ZDUser;
import com.tombeadman.tdesk.view.ViewHelper;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 11/10/2014 Time: 01:07
 */
public class UserOrganisationComparator implements Comparator<ZDUser>
{
  @Override
  public int compare( final ZDUser o1, final ZDUser o2 )
  {
    final int i;
    if ( o1 == null || o1.getOrganizationId() == null )
    {
      i = Integer.MIN_VALUE;
    }
    else if ( o2 == null || o2.getOrganizationId() == null )
    {
      i = Integer.MAX_VALUE;
    }
    else
    {
      i = ViewHelper.getOrganisation( o1.getOrganizationId() ).getName().compareTo(
        ViewHelper.getOrganisation( o2.getOrganizationId() ).getName() );
    }
    return i;
  }
}
