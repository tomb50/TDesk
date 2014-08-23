package tomb.supportsim.controllers;

import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Customer;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 08:44
 */
public class CustomerReporter
{
  public String getCustomerName( final int i )
  {
    final Customer customer = (Customer) HibernateUtil.getEntity( Customer.class, i );
    return customer != null ? customer.getName() : "";
  }

  public int getNumberOfCustomers()
  {
    return HibernateUtil.getEntityCount( Customer.class );
  }
}
