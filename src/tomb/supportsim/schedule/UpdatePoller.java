package tomb.supportsim.schedule;

import org.zendesk.client.v2.model.IncrementalTicket;
import tomb.supportsim.app.TDeskApp;
import tomb.supportsim.control.Cache;
import tomb.supportsim.models.ConvertUtil;
import tomb.supportsim.models.ZDTicket;
import tomb.supportsim.view.ViewHelper;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 20:04
 */
class UpdatePoller
{
  //todo Implement pull poller
  public void run()
  {
    Date date = new Date(  );
    System.out.println(date);
    System.out.println("Polling for recently updated tickets");
    Long cutoffTime = System.currentTimeMillis()/1000L;
    cutoffTime -= (60*5);

    List<IncrementalTicket> recentIncTickets = TDeskApp.getInstance().getZd().getRecentTickets( cutoffTime );
    for(IncrementalTicket incTicket : recentIncTickets)
    {
      ZDTicket ticket = ConvertUtil.toTicket( TDeskApp.getInstance().getZd().getTicket( incTicket.getId() ));
      System.out.println("Inserting/Updating ticket: " + ticket.getId());
      Cache.getInstance().getTicketMap().put( ticket.getId(),ticket );
    }

  }

}
