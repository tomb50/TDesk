package tomb.supportsim.schedule;

import tomb.supportsim.util.TimeModel;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class TicketAssignmentTask
{
 // @Scheduled(cron = PropertyKeys.CRON_ASSIGNMENT)
  public void assignTickets()
  {
    System.out.println( "Scheduled Assignment Poller" + new Timestamp( TimeModel.getTime() ) );
    AssignmentPoller assignmentPoller = new AssignmentPoller();
    assignmentPoller.run();
  }
}
