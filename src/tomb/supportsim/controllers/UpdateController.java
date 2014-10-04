package tomb.supportsim.controllers;

import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.util.TimeModel;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 22:30
 */
public class UpdateController
{

  final TicketManager ticketManager = new TicketManager();

  public void closeTickets( final List<SupportTicket> wipTickets )
  {

    for ( SupportTicket ticket : wipTickets )
    {
      final Long timeAssigned = ticket.getTimeWIPStarted().getTime();
      final Long estimatedCompTime = ticket.getEstimatedCompletionTime().longValue() * 60 * 60 * 1000;
      final Long now = TimeModel.getTime();
      if ( ( timeAssigned + estimatedCompTime < now ) ) //todo time interface
      {
        ticketManager.closeTicket( ticket.getId() );
        updateAnalystsQueue( ticket );
      }
    }
  }

  private void updateAnalystsQueue( final SupportTicket ticket )
  {
    final List queuedTickets = TicketReporter.getTickets( ticket.getAssigneeId(), TicketStateEnum.QUEUED );
    if ( queuedTickets.size() > 0 )
    {
      final SupportTicket ticket1 = (SupportTicket) queuedTickets.get( new Random().nextInt( queuedTickets.size() ) );
      ticketManager.assignTicketToWIP( ticket1.getId(), ticket1.getAssigneeId() );
    }
  }
}
