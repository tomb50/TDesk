package tomb.supportsim.controllers;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Customer;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 08:44
 */
public class CustomerReporter
{
  public String getCustomerName( final int i )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    Customer customer = (Customer) session.get( Customer.class, i );
    session.getTransaction().commit();
    session.close();
    return customer.getName();
  }

  public int getNumberOfCustomers()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Number count =
      (Number) session.createCriteria( Customer.class ).setProjection( Projections.rowCount() ).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return count.intValue();
  }
}
