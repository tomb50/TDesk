package tomb.supportsim.generators;

import tomb.supportsim.controllers.CustomerReporter;
import tomb.supportsim.controllers.DescriptionManager;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 20:46
 */
public abstract class DetailsGenerator
{
  String delimiter = " - ";
  protected Random randomGenerator;

  public DetailsGenerator()
  {
    randomGenerator = new Random();
  }

  public void addDetails( SupportTicket newTicket )
  {
    newTicket.setDescription( getDescription() );
    newTicket.setEstimatedCompletionTime( getEstimatedCompletionTime() );
    newTicket.setType( getType() );
    newTicket.setState( TicketStateEnum.NEW );
    newTicket.setCustomerId( getCustomerId() );
  }

  private int getCustomerId()
  {
    CustomerReporter customerReporter = new CustomerReporter();
    int noCustomers = customerReporter.getNumberOfCustomers();
    return randomGenerator.nextInt( noCustomers );
  }

  protected abstract TicketTypeEnum getType();

  protected abstract BigDecimal getEstimatedCompletionTime();

  protected String getDescription()
  {
    DescriptionManager descriptionManager = new DescriptionManager();
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append( descriptionManager.getRandomDescriptionElement1( getType() ) );
    stringBuffer.append( delimiter );
    stringBuffer.append( descriptionManager.getRandomDescriptionElement2( getType() ) );
    return stringBuffer.toString();
  }
}
