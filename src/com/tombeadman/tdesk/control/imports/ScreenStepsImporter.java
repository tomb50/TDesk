package com.tombeadman.tdesk.control.imports;

import com.tombeadman.screensteps.ScreenSteps;
import com.tombeadman.screensteps.model.Asset;
import com.tombeadman.screensteps.model.Manual;
import com.tombeadman.screensteps.model.Space;
import com.tombeadman.screensteps.model.Spaces;
import com.tombeadman.tdesk.datastore.Cache;

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
    setActive( true );
    long time = System.currentTimeMillis();
    importScreenstepsData();
    logger.info( "All data imported from ScreenSteps" );
    logger.info( "ScreenSteps - Time taken: " + ( System.currentTimeMillis() - time ) / 1000 );
    setActive( false );
  }

  public void importScreenstepsData()
  {
    //persist Spaces
    logger.info( "Importing Spaces from Screensteps" );
    saveSpaces();

    //persist Manuals
    logger.info( "Importing Manuals from Screensteps" );
    saveManuals();

    //testMethod();
    //persist Lessons
    //Logger.getLogger(getClass().getName()).log( Level.INFO, "Importing Lessons from Screensteps" );
    //saveLessons();
    //Logger.getLogger(getClass().getName()).log( Level.INFO, "Spaces saved" );
    //Logger.getLogger(getClass().getName()).log( Level.INFO, ( System.currentTimeMillis() - time ) / 1000 );
    //time = System.currentTimeMillis();
  }

  /*private void testMethod()
  {
    Map<String ,Manual> manualMap = Cache.getInstance().getManualMap();

    Logger.getLogger(getClass().getName()).log( Level.INFO,manualMap.size());
    for(Map.Entry <String,Manual> entry : manualMap.entrySet())
    {
      for( Chapter chapter : entry.getValue().getChapters())
      {
        for (Lesson lesson : chapter.getLessons())
        {
          Logger.getLogger(getClass().getName()).log( Level.INFO,lesson.getTitle() + "-" + lesson.getUrl());
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
