package tomb.supportsim.schedule;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class TicketUpdateTask
{


  //@Scheduled(cron=PropertyKeys.CRON_UPDATE)
  public void updateTickets()
  {
    System.out.println( "Scheduled Updating Poller" + new Timestamp( System.currentTimeMillis() ) );
    UpdatePoller updatePoller = new UpdatePoller();
    updatePoller.run();
  }
}
