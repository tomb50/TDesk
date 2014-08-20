package tomb.supportsim.schedule;

import tomb.supportsim.controllers.AnalystReporter;
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
    TicketReporter ticketReporter = new TicketReporter();
    List<SupportTicket> unasignedTickets = ticketReporter.getAllUnassignedTickets();
    if ( unasignedTickets != null )
    {
      AnalystReporter analystReporter = new AnalystReporter();
      AssignmentController assignmentController = new AssignmentController( AssignmentMethodEnum.RANDOM );
      for ( SupportTicket ticket : unasignedTickets )
      {
        Analyst analyst = assignmentController.getAnalyst( ticket.getType() );
        assignmentController.assignTicket( ticket, analyst );
      }
    }
  }
}
