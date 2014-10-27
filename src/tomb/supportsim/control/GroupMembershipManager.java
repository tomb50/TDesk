package tomb.supportsim.control;

import org.zendesk.client.v2.model.GroupMembership;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 21:38
 */
public class GroupMembershipManager extends ModelManager
{
  public List<GroupMembership> getGroupMemberships( final Long id )
  {
    return getCache().getGroupMemberships(id);
  }


}
