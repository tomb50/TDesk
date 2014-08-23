package tomb.supportsim.controllers;

import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.util.TimeModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 22:30
 */
public class UpdateController
{
  public void closeTickets( final List<SupportTicket> wipTickets )
  {
    final TicketManager ticketManager = new TicketManager();
    for ( SupportTicket ticket : wipTickets )
    {
      final Long timeAssigned = ticket.getTimeAssigned().getTime();
      final Long estimatedCompTime = ticket.getEstimatedCompletionTime().longValue() * 60 * 60 * 1000;
      final Long now = TimeModel.getTime();
      if ( ( timeAssigned + estimatedCompTime < now ) ) //todo time interface
      {
        ticketManager.closeTicket( ticket.getId() );
      }
    }
  }
}
