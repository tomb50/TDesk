package com.tombeadman.tdesk.control;

import org.zendesk.client.v2.model.Status;
import com.tombeadman.tdesk.models.zendesk.ZDTicket;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:55
 */
public class TicketManager extends ModelManager
{

  public Integer getTotalTicketCount()
  {
    return getCache().getTicketMap().size();
  }

  public List<ZDTicket> getTicketByState(final Status status)
  {
    return getCache().getTickets( status );
  }

  public List<ZDTicket> getOpenUnassignedTickets()
  {
    return getCache().getUnassignedTickets( Status.OPEN );

  }

  public List<ZDTicket> getOpenTicketsForAnalystFromCache( long analystid )
  {
    return getCache().getTickets( Status.OPEN, analystid );
  }
}
