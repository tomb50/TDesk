package tomb.supportsim.controllers;

import org.hibernate.Session;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Customer;
import tomb.supportsim.models.enums.TimeZoneEnum;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 08:44
 */
public class CustomerManager
{
  public void addNewCustomer( final String name, final TimeZoneEnum timeZoneEnum )
  {
    final Session session = HibernateUtil.beginTransaction();
    final Customer customer = new Customer();
    customer.setName( name );
    customer.setTimeZone( timeZoneEnum );
    session.save( customer );
    HibernateUtil.commitAndClose( session );
  }
}
