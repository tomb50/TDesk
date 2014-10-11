package tomb.supportsim.schedule;

import tomb.supportsim.controllers.TicketManager;
import tomb.supportsim.util.TimeModel;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class TicketCreationTask
{
 // @Scheduled(cron= PropertyKeys.CRON_CREATION)
  public void createTicket()
  {
    System.out.println( "Scheduled Ticket Creation Poller" + new Timestamp( TimeModel.getTime() ) );
    TicketManager ticketManager = new TicketManager();
    ticketManager.potentiallyCreateNewTicket();
  }
}
