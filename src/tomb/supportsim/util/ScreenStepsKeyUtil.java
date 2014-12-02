package tomb.supportsim.util;

import com.tombeadman.screensteps.model.Lesson;
import com.tombeadman.screensteps.model.Manual;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 29/11/14 Time: 17:12
 */

/*
* Utility class to generate Pks for ScreenStep entities.
* The basic relationship is Space has many Manuals (via Asset) which have many Lessons which have many Steps.
*
 */
public class ScreenStepsKeyUtil
{


  public static String getLessonPK(final Lesson lesson)
  {
    return lesson.getSpace().getId() + "-" + lesson.getManual().getId() + "-" + lesson.getId();
  }

  public static String getManualPK(final Manual manual)
  {
    return manual.getSpace().getId() + "-'" + manual.getId();
  }

}
