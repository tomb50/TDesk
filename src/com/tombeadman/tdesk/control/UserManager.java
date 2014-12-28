package com.tombeadman.tdesk.control;

import com.tombeadman.tdesk.models.ZDUser;
import com.tombeadman.tdesk.util.UserOrganisationComparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:55
 */
public class UserManager extends ModelManager
{
  public ZDUser getUser( final Long userId )
  {
    return getCache().getUserMap().get( userId );
  }


  public List<ZDUser> getCustomers()
  {
    return getCache().getUsers();
  }



  public List<ZDUser> getOrderedCustomers()
  {
    List<ZDUser> users = getCustomers();
    for ( Iterator it = users.iterator(); it.hasNext(); )
    {
      ZDUser user = (ZDUser) it.next();
      if(user.getOrganizationId() == null) it.remove();
    }
    Collections.sort( users, new UserOrganisationComparator() );
    return users;
  }
}
