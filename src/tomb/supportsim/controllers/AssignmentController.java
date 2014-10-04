package tomb.supportsim.controllers;

import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.AssignmentMethodEnum;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 02:09
 */
public class AssignmentController
{
  private final AssignmentMethodEnum assignmentMethodEnum;
  Random randomGenerator = new Random();

  public AssignmentController( AssignmentMethodEnum assignmentMethodEnum )
  {
    this.assignmentMethodEnum = assignmentMethodEnum;
  }

  public Analyst getAnalyst(final TicketTypeEnum ticketTypeEnum)
  {
    final Analyst analyst;

    switch ( assignmentMethodEnum )
    {
      case FULL_RANDOM:
        analyst = fullRandom();
        break;

      case RANDOM:
      default:
        analyst = random( ticketTypeEnum );
        break;

      case FREE_RANDOM:
        analyst = freeRandom( ticketTypeEnum );
        break;

      case LOWEST_WORKLOAD:
        analyst = lowestWorkload();
        break;

      case REAL_WORLD:
        analyst = realWorld();
        break;
    }
    return analyst;
  }

  //TODO Don't use until re-assignment poller is implemented
  private Analyst fullRandom()
  {
    final List<Analyst> analysts = AnalystReporter.getAllAnalysts();
    return getRandomAnalystFromList( analysts );
  }

  private Analyst random( final TicketTypeEnum ticketTypeEnum )
  {
    final RoleEnum role = getRoleFromTicketType( ticketTypeEnum );
    final List<Analyst> suitableAnalysts = AnalystReporter.getSuitableAnalysts( role );
    return getRandomAnalystFromList( suitableAnalysts );
  }

  private Analyst freeRandom( final TicketTypeEnum ticketTypeEnum )
  {
    final RoleEnum role = getRoleFromTicketType( ticketTypeEnum );
    final List<Analyst> suitableFreeAnalysts = new ArrayList<>();
    for ( Analyst analyst : AnalystReporter.getSuitableAnalysts( role ) )
    {
      if ( TicketReporter.getOpenTicketCount( analyst.getId() ) == 0 )
      {
        suitableFreeAnalysts.add( analyst );
      }
    }
    return
      suitableFreeAnalysts.isEmpty() ? random( ticketTypeEnum ) : getRandomAnalystFromList( suitableFreeAnalysts );
  }

  //TODO to be done after QUEUING has been implmented
  private Analyst lowestWorkload()
  {
    return null;
  }


  //TODO not yet implmented
  private Analyst realWorld()
  {
    return null;
  }


  private Analyst getRandomAnalystFromList( final List<Analyst> analysts )
  {
    return analysts.get( randomGenerator.nextInt( analysts.size() ) );
  }


  private RoleEnum getRoleFromTicketType( final TicketTypeEnum type )
  {
    final RoleEnum role;
    switch ( type )
    {
      case LOCKEDDOCUMENT:
      default:
        role = RoleEnum.FIRSTLINE;
        break;
      case DBA:
        role = RoleEnum.DBA;
        break;
      case SYSTEMDOWN:
        role = RoleEnum.FIRSTLINE;
        break;
      case JAVA:
        role = RoleEnum.JAVA;
        break;
      case ABL:
        role = RoleEnum.ABL;
    }
    return role;
  }

  public void assignTicket( final SupportTicket ticket, final Analyst analyst )
  {
    final TicketManager ticketManager = new TicketManager();
    final boolean toQueue = TicketReporter.getOpenTicketCount( analyst.getId() ) > 0;
    if ( toQueue )
    {
      ticketManager.assignTicketToQueue( ticket.getId(), analyst.getId() );
    }
    else
    {
      ticketManager.assignTicketToWIP( ticket.getId(), analyst.getId() );
    }
  }
}
