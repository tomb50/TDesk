package tomb.supportsim.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import tomb.supportsim.controllers.TicketManager;
import tomb.supportsim.util.TimeModel;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 17:28
 */
public class ScheduledTasks
{
  @Scheduled(cron="${poller.assignment.cron}")
  public void createTicket()
  {
    System.out.println( "Scheduled Ticket Creation Poller" + new Timestamp( TimeModel.getTime() ) );
    TicketManager ticketManager = new TicketManager();
    ticketManager.potentiallyCreateNewTicket();
  }

  @Scheduled(cron="${poller.assignment.cron}")
  public void assignTickets()
  {
    System.out.println( "Scheduled Assignment Poller" + new Timestamp( TimeModel.getTime() ) );
    AssignmentPoller assignmentPoller = new AssignmentPoller();
    assignmentPoller.run();
  }

  @Scheduled(cron="${poller.update.cron}")
  public void updateTickets()
  {
    System.out.println( "Scheduled Updating Poller" + new Timestamp( TimeModel.getTime() ) );
    UpdatePoller updatePoller = new UpdatePoller();
    updatePoller.run();
  }

  public void clockAnalysts()
  {
    //todo implement
  }
}
