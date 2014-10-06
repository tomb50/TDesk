package tomb.supportsim.controllers;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class AnalystReporter
{

  public static List<Analyst> getAllAnalysts()
  {
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class );
  }

  private static List<Analyst> getAnalystsByWorkingState(final WorkingStateEnum workingStateEnum)
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "state", workingStateEnum) );
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class, restrictions );
  }

  public static List<Analyst> getSuitableAnalysts( final RoleEnum roleEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "state", WorkingStateEnum.WORKING ) );
    restrictions.add( Restrictions.or( Restrictions.eq( "role", RoleEnum.ALL ), Restrictions.eq( "role", roleEnum ) ) );
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class, restrictions );
  }


  public static Integer getLargestQueue(final RoleEnum roleEnum)
  {
    Integer largesQueueSize = 0;
    for ( Analyst analyst : getAllAnalysts() )
    {
      if(analyst.getRole().equals( roleEnum ))
      {
        Integer queueSize = TicketReporter.getTicketCount( analyst.getId(), TicketStateEnum.QUEUED );
        if ( queueSize > largesQueueSize ) largesQueueSize = queueSize;
      }
    }
    return largesQueueSize;
  }
}
