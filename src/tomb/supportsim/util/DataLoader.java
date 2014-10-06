package tomb.supportsim.util;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 20/08/2014 Time: 12:30 This is used to for initial data migration
 */
public class DataLoader
{
  public static void main( String[] args )
  {
    DataLoader dataLoader = new DataLoader();
    dataLoader.loadAnalysts();
    dataLoader.loadDescriptions();
    dataLoader.loadCustomers();
  }

  private void loadAnalysts()
  {
    //AnalystManager analystManager = new AnalystManager();
    //analystManager.recruitAnalyst( "Joe Bloggs", RoleEnum.FIRST_LINE, WorkingStateEnum.WORKING );
    //analystManager.recruitAnalyst( "John Smith", RoleEnum.JAVA, WorkingStateEnum.WORKING );

  }

  private void loadDescriptions()
  {
    //DescriptionManager descriptionManager = new DescriptionManager();
    //descriptionManager.addNewDescription( TicketTypeEnum.LOCKED_DOCUMENT, "Contract is Locked", "Please Unlock", "" );
    //descriptionManager.addNewDescription( TicketTypeEnum.DBA, "Live to dev data migration request", "low priority", "" );

  }

  private void loadCustomers()
  {
    //CustomerManager customerManager = new CustomerManager();
    //customerManager.addNewCustomer( "123 & co", TimeZoneEnum.AEST );
    //customerManager.addNewCustomer( "Super duper technologies ltd", TimeZoneEnum.CST );

  }
}
