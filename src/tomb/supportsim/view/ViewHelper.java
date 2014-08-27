package tomb.supportsim.view;

import tomb.supportsim.controllers.*;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.Customer;
import tomb.supportsim.models.DescriptionTemplate;
import tomb.supportsim.models.SupportTicket;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 25/08/2014 Time: 17:53
 */
public class ViewHelper
{
  public static List getAnalystAttributes()
  {
    return Arrays.asList( Analyst.class.getDeclaredFields() );
  }

  public static List<Analyst> getAnalysts()
  {
    AnalystReporter analystReporter = new AnalystReporter();
    return analystReporter.getAllAnalysts();
  }

  public static int getNoAnalystAtttributes()
  {
    return getAnalystAttributes().size();
  }

  public static List<SupportTicket> getAllTickets()
  {
    final TicketReporter ticketReporter = new TicketReporter();
    return ticketReporter.getAllTickets();
  }


  public static List<Field> getTicketAttributes()
  {
    return Arrays.asList( SupportTicket.class.getDeclaredFields() );
  }

  public static List<Customer> getAllCustomers()
  {
    final CustomerReporter customerReporter = new CustomerReporter();
    return customerReporter.getAllCustomers();
  }

  public static List<Field> getCustomerAttributes()
  {
    return Arrays.asList( Customer.class.getDeclaredFields() );
  }

  public static List<Field> getDescriptionTemplateAttributes()
  {
    return Arrays.asList( DescriptionTemplate.class.getDeclaredFields() );
  }

  public static List<DescriptionTemplate> getAllDescriptionTemplates()
  {
    final DescriptionTemplateReporter descriptionTemplateReporter = new DescriptionTemplateReporter();
    return descriptionTemplateReporter.getAllDescriptionTemplates();

  }
}
