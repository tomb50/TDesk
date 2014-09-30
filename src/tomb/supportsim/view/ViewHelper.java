package tomb.supportsim.view;

import tomb.supportsim.controllers.AnalystReporter;
import tomb.supportsim.controllers.CustomerReporter;
import tomb.supportsim.controllers.DescriptionTemplateReporter;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.Customer;
import tomb.supportsim.models.DescriptionTemplate;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    return AnalystReporter.getAllAnalysts();
  }

  public static int getNoAnalystAtttributes()
  {
    return getAnalystAttributes().size();
  }

  public static List<SupportTicket> getAllTickets()
  {
    return TicketReporter.getAllTickets();
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
    return DescriptionTemplateReporter.getAllDescriptionTemplates();
  }

  public static List<List<Analyst>> getActiveAnalystLol()
  {
    return AnalystReporter.getActiveLoL();
  }

  public static Map getTicketCountByTypeMap()
  {
    final Map map = new HashMap();
    for ( TicketTypeEnum ticketTypeEnum : TicketTypeEnum.values() )
    {
      map.put( ticketTypeEnum, TicketReporter.getTicketCountByType( ticketTypeEnum ) );
    }
    return map;
  }

  public static Integer getTicketCountByType( final TicketTypeEnum ticketTypeEnum )
  {
    return TicketReporter.getTicketCountByType( ticketTypeEnum );
  }

  public static List getJoinedDetailsForAllTickets()
  {
    return TicketReporter.getJoinedDetailsForAllTickets();
  }


  public static Integer getTotalTicketCount()
  {
    return TicketReporter.getTotalTicketCount();
  }

  public static Integer getTotalClosedTicketCount()
  {
    return TicketReporter.getClosedTicketCount();
  }

  public static Map<Analyst, Integer> getClosedTicketCountByAnalystMap()
  {
    Map map = new HashMap<Analyst, Integer>();
    List<Analyst> analystList = AnalystReporter.getAllAnalysts();
    for ( Analyst analyst : analystList )
    {
      List<SupportTicket> ticketList = TicketReporter.getClosedTicketsByAnalyst( analyst );
      map.put( analyst, ticketList.size() );
    }

    return map;
  }
}
