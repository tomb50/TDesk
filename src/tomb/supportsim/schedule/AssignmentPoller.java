package tomb.supportsim.schedule;

import tomb.supportsim.controllers.AssignmentController;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.AssignmentMethodEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 01:50
 */
public class AssignmentPoller
{

  public void run()
  {
    final List<SupportTicket> unasignedTickets = TicketReporter.getAllUnassignedTickets();
    if ( unasignedTickets != null )
    {
      AssignmentController assignmentController = new AssignmentController( AssignmentMethodEnum.RANDOM );
      for ( SupportTicket ticket : unasignedTickets )
      {
        final Analyst analyst = assignmentController.getAnalyst( ticket.getType() );
        assignmentController.assignTicket( ticket, analyst );
      }
    }
  }
}
