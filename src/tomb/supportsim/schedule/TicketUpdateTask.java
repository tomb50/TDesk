package tomb.supportsim.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import tomb.supportsim.util.PropertyKeys;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class TicketUpdateTask
{

  @Scheduled(cron= PropertyKeys.CRON_UPDATE_TICKETS)
  public void updateTickets()
  {
    UpdatePoller updatePoller = new UpdatePoller();
    updatePoller.run();
  }
}
