package tomb.supportsim.schedule;

import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.controllers.UpdateController;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 20:04
 */
class UpdatePoller
{

  public void run()
  {

    List<SupportTicket> wipTickets = getWIPTickets();
    if ( wipTickets != null )
    {
      closeTickets( wipTickets );
    }
  }

  private void closeTickets( final List<SupportTicket> wipTickets )
  {
    UpdateController updateController = new UpdateController();
    updateController.closeTickets( wipTickets );
  }

  private List getWIPTickets()
  {
    return TicketReporter.getTicketsByState( TicketStateEnum.WIP );
  }
}
