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

  public Analyst getAnalyst( final TicketTypeEnum type )
  {

    final RoleEnum role = getRoleFromTicketType( type );
    Analyst analyst = null;
    final List<Analyst> suitableAnalysts = AnalystReporter.getSuitableFreeAnalysts( role );
    switch ( this.assignmentMethodEnum )
    {
      case REALWORLD:
        break;
      case FIRST:
        break;
      case FULLRANDOM:
        analyst = getAnyAnalyst( suitableAnalysts );
        break;
      case RANDOM:
        final List<Analyst> suitableFreeAnalysts = new ArrayList<Analyst>();
        for ( Analyst analyst1 : suitableAnalysts )
        {
          if ( TicketReporter.getOpenTicketCount( analyst1.getId() ) == 0 )
          {
            suitableFreeAnalysts.add( analyst1 );
          }
        }
        analyst =
          !suitableFreeAnalysts.isEmpty() ? getAnyAnalyst( suitableFreeAnalysts ) : getAnyAnalyst( suitableAnalysts );
        break;
      case LONGESTWAITING:
        break;
    }
    return analyst;
  }

  private Analyst getAnyAnalyst( final List<Analyst> suitableAnalysts )
  {
    return suitableAnalysts.get( randomGenerator.nextInt( suitableAnalysts.size() ) );
  }

  private RoleEnum getRoleFromTicketType( final TicketTypeEnum type )
  {
    RoleEnum role = null;
    switch ( type )
    {
      case LOCKEDDOCUMENT:
        role = RoleEnum.FIRSTLINE;
        break;
      case DBA:
        SYSTEMDOWN:
        role = RoleEnum.DBA;
        break;
      case JAVA:
        role = RoleEnum.JAVA;
        break;
      case ABL:
        role = RoleEnum.ABL;
      default:
        break;
    }

    return role;
  }

  public void assignTicket( final SupportTicket ticket, final Analyst analyst )
  {
    final TicketManager ticketManager = new TicketManager();
    ticketManager.assignTicket( ticket.getId(), analyst.getId() );
  }
}
