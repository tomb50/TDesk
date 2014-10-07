package tomb.supportsim.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import tomb.supportsim.util.PropertyKeys;
import tomb.supportsim.util.TimeModel;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class TicketUpdateTask
{


  @Scheduled(cron=PropertyKeys.CRON_UPDATE)
  public void updateTickets()
  {
    System.out.println( "Scheduled Updating Poller" + new Timestamp( TimeModel.getTime() ) );
    UpdatePoller updatePoller = new UpdatePoller();
    updatePoller.run();
  }
}
