package tomb.supportsim.models;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 23/12/14 Time: 13:15
 */

/*
 * Model for convenience, this represents a table row on the Screensteps Page
 */
public class ScreenstepsTableEntry
{
  private String spaceName;
  private String manualName;
  private String chapterName;
  private String lessonName;
  private String url;

  public ScreenstepsTableEntry( final String spaceName, final String manualName, final String chapterName,
                                final String lessonName,
                                final String url )
  {
    this.spaceName = spaceName;
    this.manualName = manualName;
    this.chapterName = chapterName;
    this.lessonName = lessonName;
    this.url = url;
  }

  public String getSpaceName()
  {
    return spaceName;
  }

  public void setSpaceName( final String spaceName )
  {
    this.spaceName = spaceName;
  }

  public String getManualName()
  {
    return manualName;
  }

  public void setManualName( final String manualName )
  {
    this.manualName = manualName;
  }

  public String getChapterName()
  {
    return chapterName;
  }

  public void setChapterName( final String chapterName )
  {
    this.chapterName = chapterName;
  }

  public String getLessonName()
  {
    return lessonName;
  }

  public void setLessonName( final String lessonName )
  {
    this.lessonName = lessonName;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl( final String url )
  {
    this.url = url;
  }
}
