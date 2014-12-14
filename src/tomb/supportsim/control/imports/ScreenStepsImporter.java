package tomb.supportsim.control.imports;

import com.tombeadman.screensteps.ScreenSteps;
import com.tombeadman.screensteps.model.Asset;
import com.tombeadman.screensteps.model.Manual;
import com.tombeadman.screensteps.model.Space;
import com.tombeadman.screensteps.model.Spaces;
import tomb.supportsim.control.Cache;

import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 14/12/14 Time: 14:53
 */
public class ScreenStepsImporter extends Importer
{
  final ScreenSteps screenSteps;

  public ScreenStepsImporter( final ScreenSteps screenSteps )
  {
    this.screenSteps = screenSteps;
  }

  @Override
  public void run()
  {
    importScreenstepsData();
  }

  public void importScreenstepsData()
  {
    //persist Spaces
    long time = System.currentTimeMillis();
    System.out.println( "Importing Spaces from Screensteps" );
    saveSpaces();
    System.out.println( "Spaces saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Manuals
    System.out.println( "Importing Manuals from Screensteps" );
    saveManuals();
    System.out.println( "Spaces saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );


    //testMethod();

    //persist Lessons
    //System.out.println( "Importing Lessons from Screensteps" );
    //saveLessons();
    //System.out.println( "Spaces saved" );
    //System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    //time = System.currentTimeMillis();
  }

  /*private void testMethod()
  {
    Map<String ,Manual> manualMap = Cache.getInstance().getManualMap();

    System.out.println(manualMap.size());
    for(Map.Entry <String,Manual> entry : manualMap.entrySet())
    {
      for( Chapter chapter : entry.getValue().getChapters())
      {
        for (Lesson lesson : chapter.getLessons())
        {
          System.out.println(lesson.getTitle() + "-" + lesson.getUrl());
        }
    }


  }

}   */

  /*
   * This methods pulles back all Manual data for each space. Space data is pulled from the Cache
    * i.e. Space data must be pulled before this is executed
   */
  private void saveManuals()
  {
    for ( Map.Entry<String, Space> entry : Cache.getInstance().getSpaceMap().entrySet() )
    {
      Space space = entry.getValue();
      {
        for ( Asset asset : space.getAssets() )
        {
          if ( asset.getAssetType().equals( "Manual" ) )
          {
            System.out.println( "saving" + space.getId() + "-" + asset.toString() );
            final Manual manual = screenSteps.getManual( space.getId(), asset.getId() );
            Cache.getInstance().insertManual( manual );
          }
        }
      }
    }
  }

  /*
  * This call is in two parts due to Screensteps API, the first returns a list of Space header info.
  * We then use the retrieved Space Id to retrieve the full data for each Space.
   */
  private void saveSpaces()
  {
    final Spaces spaces = screenSteps.getSpaces();
    for ( Space space : spaces.getSpaces() )
    {
      Space fullSpace = screenSteps.getSpace( space.getId() );
      Cache.getInstance().insertSpace( fullSpace );
    }
  }
}
