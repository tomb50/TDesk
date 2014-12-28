package com.tombeadman.tdesk.schedule;

import org.zendesk.client.v2.model.IncrementalTicket;
import com.tombeadman.tdesk.app.TDeskApp;
import com.tombeadman.tdesk.datastore.Cache;
import com.tombeadman.tdesk.util.ConvertUtil;
import com.tombeadman.tdesk.models.zendesk.ZDTicket;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 20:04
 */
class ZendeskTicketUpdatePoller
{
  private static final int minutesUpdated = 5; // Minimum 5
  private static final Logger logger = Logger.getLogger( ZendeskTicketUpdatePoller.class.getSimpleName() );

  public void run()
  {
    logger.info( "Polling for recently updated tickets" );
    /*Cut off for date updated, This is the minimum value Zendesk will accept, IE it will return any tickets that have
    been updated in the past five minutes */
    Long cutoffTime = ( System.currentTimeMillis() / 1000L ) - ( 60 * minutesUpdated );

    final List<IncrementalTicket> recentIncTickets = TDeskApp.getInstance().getZd().getRecentTickets( cutoffTime );
    for ( IncrementalTicket incTicket : recentIncTickets )
    {
      final ZDTicket ticket = ConvertUtil.toTicket( TDeskApp.getInstance().getZd().getTicket( incTicket.getId() ) );
      logger.info( "Inserting/Updating ticket: " + ticket.getId() );
      Cache.getInstance().getTicketMap().put( ticket.getId(), ticket );
    }
  }
}
