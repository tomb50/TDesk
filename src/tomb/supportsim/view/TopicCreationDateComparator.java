package tomb.supportsim.view;

import tomb.supportsim.models.ZDTopic;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 11/10/2014 Time: 23:11
 */
public class TopicCreationDateComparator implements Comparator<ZDTopic>
{
  @Override
  public int compare( final ZDTopic o1, final ZDTopic o2 )
  {
    {
      final int i;
      if ( o1 == null || o1.getCreatedAt() == null )
      {
        i = Integer.MIN_VALUE;
      }
      else if ( o2 == null || o2.getCreatedAt() == null )
      {
        i = Integer.MAX_VALUE;
      }
      else
      {
        i = o1.getCreatedAt().compareTo(
           o2.getCreatedAt()  );
      }
      return i;
    }
  }
}
