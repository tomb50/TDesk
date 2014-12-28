package com.tombeadman.tdesk.schedule;

import com.tombeadman.tdesk.util.PropertyKeys;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 13:44
 */
public class UpdateZendeskTicketsTask extends UpdateTask
{
  final ZendeskTicketUpdatePoller zendeskTicketUpdatePoller = new ZendeskTicketUpdatePoller();

  @Scheduled(cron= PropertyKeys.CRON_UPDATE_TICKETS)
  public void updateTickets()
  {
    zendeskTicketUpdatePoller.run();
  }
}
