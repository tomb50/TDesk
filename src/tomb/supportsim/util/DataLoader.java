package tomb.supportsim.util;

import tomb.supportsim.controllers.AnalystManager;
import tomb.supportsim.controllers.CustomerManager;
import tomb.supportsim.controllers.DescriptionManager;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;
import tomb.supportsim.models.enums.TimeZoneEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

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
    //analystManager.recruitAnalyst( "Joe Bloggs", RoleEnum.FIRSTLINE, WorkingStateEnum.WORKING );
    //analystManager.recruitAnalyst( "John Smith", RoleEnum.JAVA, WorkingStateEnum.WORKING );

  }

  private void loadDescriptions()
  {
    //DescriptionManager descriptionManager = new DescriptionManager();
    //descriptionManager.addDescription( TicketTypeEnum.LOCKEDDOCUMENT, "Contract is Locked", "Please Unlock", "" );
    //descriptionManager.addDescription( TicketTypeEnum.DBA, "Live to dev data migration request", "low priority", "" );

  }

  private void loadCustomers()
  {
    //CustomerManager customerManager = new CustomerManager();
    //customerManager.addNewCustomer( "123 & co", TimeZoneEnum.AEST );
    //customerManager.addNewCustomer( "Super duper technologies ltd", TimeZoneEnum.CST );

  }
}
