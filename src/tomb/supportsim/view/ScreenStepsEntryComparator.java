package tomb.supportsim.view;

import tomb.supportsim.models.ScreenstepsTableEntry;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 23/12/14 Time: 13:25
 */
public class ScreenStepsEntryComparator
  implements java.util.Comparator<ScreenstepsTableEntry>
{
  @Override
  public int compare( final ScreenstepsTableEntry o1, final ScreenstepsTableEntry o2 )
  {
    int i = o1.getSpaceName().compareTo( o2.getSpaceName() );
    if ( i != 0 ) return i;

    i = o1.getManualName().compareTo( o2.getManualName() );
    if ( i != 0 ) return i;

    i = o1.getChapterName().compareTo( o2.getChapterName() );
    if ( i != 0 ) return i;

    return o1.getLessonName().compareTo( o2.getLessonName() );
  }
}
