package tomb.supportsim.view;

import tomb.supportsim.models.ZDTopic;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 11/10/2014 Time: 22:49
 */
public class TopicForumComparator implements Comparator<ZDTopic>
{

  @Override
  public int compare( final ZDTopic o1, final ZDTopic o2 )
  {
    {
      final int i;
      if ( o1 == null || o1.getForumId() == null )
      {
        i = Integer.MIN_VALUE;
      }
      else if ( o2 == null || o2.getForumId() == null )
      {
        i = Integer.MAX_VALUE;
      }
      else
      {
        i = ViewHelper.getForumName( o1.getForumId() ).compareTo(
          ViewHelper.getForumName( o2.getForumId() ) );
      }
      return i;
    }
  }
}
