package com.tombeadman.tdesk.control;

import org.zendesk.client.v2.model.Group;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 21:35
 */
public class GroupManager extends ModelManager
{
  public Group getGroup( final String name )
  {
    Group thisGroup = null;
    List<Group> groups = getCache().getGroups();
    for ( Group group : groups )
    {
      if ( group.getName().equals( name ) )
      {
        thisGroup = group;
      }
    }
    return thisGroup;
  }

  public Group getGroup(final long id)
  {
    return getCache().getGroupMap().get( id );
  }
}
