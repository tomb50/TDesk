package tomb.supportsim.controllers;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.DescriptionTemplate;
import tomb.supportsim.models.DescriptionTemplatePK;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 11:03
 */
public class DescriptionManager
{
  private final Random randomGenerator = new Random();

  int getNextSequence( final TicketTypeEnum ticketTypeEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "id.type", ticketTypeEnum ) );
    final Integer max = HibernateUtil.getEntityMax( DescriptionTemplate.class, "id.id", restrictions );
    return max != null ? max + 1 : 1;
  }

  public void addNewDescription( final TicketTypeEnum ticketTypeEnum, final String text1, final String text2,
                                 final String text3 )
  {
    final DescriptionTemplatePK pk = new DescriptionTemplatePK( ticketTypeEnum, getNextSequence( ticketTypeEnum ) );
    final DescriptionTemplate descriptionTemplate = new DescriptionTemplate();
    descriptionTemplate.setId( pk );
    descriptionTemplate.setText1( text1 );
    descriptionTemplate.setText2( text2 );
    descriptionTemplate.setText3( text3 );
    HibernateUtil.insertEntity( descriptionTemplate );
  }

  public String getRandomDescriptionElement1( final TicketTypeEnum ticketTypeEnum )
  {
    int i = getByTypeCount( ticketTypeEnum );
    return getText1( new DescriptionTemplatePK( ticketTypeEnum, randomGenerator.nextInt( i ) + 1 ) );
  }

  public String getRandomDescriptionElement2( final TicketTypeEnum ticketTypeEnum )
  {
    int i = getByTypeCount( ticketTypeEnum );
    return getText2( new DescriptionTemplatePK( ticketTypeEnum, randomGenerator.nextInt( i ) + 1 ) );
  }


  private int getByTypeCount( final TicketTypeEnum ticketTypeEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "id.type", ticketTypeEnum ) );
    Integer count = HibernateUtil.getEntityCount( DescriptionTemplate.class, restrictions );
    return count != null ? count + 1 : 0;
  }


  String getText1( final DescriptionTemplatePK pk )
  {
    final DescriptionTemplate descriptionTemplate =
      DescriptionTemplate.class.cast( HibernateUtil.getEntity( DescriptionTemplate.class, pk ) );
    return descriptionTemplate != null ? descriptionTemplate.getText1() : "";
  }

  String getText2( final DescriptionTemplatePK pk )
  {
    final DescriptionTemplate descriptionTemplate =
      DescriptionTemplate.class.cast( HibernateUtil.getEntity( DescriptionTemplate.class, pk ) );
    return descriptionTemplate != null ? descriptionTemplate.getText2() : "";
  }
}
