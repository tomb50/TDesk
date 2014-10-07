package tomb.supportsim.schedule;

import tomb.supportsim.app.SupportSimApp;
import tomb.supportsim.controllers.AssignmentController;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.AssignmentMethodEnum;
import tomb.supportsim.util.EnumUtil;
import tomb.supportsim.util.PropertyKeys;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 01:50
 */
class AssignmentPoller
{
  private static  AssignmentController assignmentController;
  private final static AssignmentMethodEnum DEFAULT_ASSIGNMENT_METHOD = AssignmentMethodEnum.FREE_RANDOM;

  public void run()
  {
    final List<SupportTicket> unassignedTickets = TicketReporter.getAllUnassignedTickets();
    if ( unassignedTickets != null )
    {
      for ( SupportTicket ticket : unassignedTickets )
      {
        final Analyst analyst = getAssignmentController().getAnalyst( ticket.getType() );
        getAssignmentController().assignTicket( ticket, analyst );
      }
    }
  }

  private AssignmentController getAssignmentController()
  {
    if ( assignmentController == null )
    {
      final AssignmentMethodEnum assignmentMethodEnum = getAssignmentMethodEnum();
      assignmentController = new AssignmentController( assignmentMethodEnum );
    }
    return assignmentController;
  }

  private AssignmentMethodEnum getAssignmentMethodEnum()
  {
    AssignmentMethodEnum assignmentMethodEnum = null;
    final String amValue = SupportSimApp.getInstance().getProperties().getProperty( PropertyKeys.ASSIGNMENT_METHOD );
    if ( amValue == null )
    {
      System.out.println(
        "AssignmentPoller: " + PropertyKeys.ASSIGNMENT_METHOD + "property is missing, using default: " +
          AssignmentMethodEnum.FREE_RANDOM );
    }
    else
    {
      assignmentMethodEnum = EnumUtil.fromString( amValue.trim().toUpperCase() );
    }
    return assignmentMethodEnum != null ? assignmentMethodEnum : DEFAULT_ASSIGNMENT_METHOD;
  }
}
