package tomb.supportsim.generators;

import tomb.supportsim.controllers.DescriptionManager;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 20:55
 */
public class LockedDocDetailsGenerator extends DetailsGenerator
{
  @Override
  protected TicketTypeEnum getType()
  {
    return TicketTypeEnum.LOCKEDDOCUMENT;
  }

  @Override
  protected BigDecimal getEstimatedCompletionTime()
  {
    return new BigDecimal( randomGenerator.nextDouble() ).setScale( 3, RoundingMode.UP );
  }

  @Override
  protected String getDescription()
  {
    DescriptionManager descriptionManager = new DescriptionManager();
    StringBuilder stringBuffer = new StringBuilder();
    stringBuffer.append( descriptionManager.getRandomDescriptionElement1( getType() ) );
    stringBuffer.append( delimiter );
    stringBuffer.append( randomGenerator.nextInt( 99999999 ) );
    stringBuffer.append( delimiter );
    stringBuffer.append( descriptionManager.getRandomDescriptionElement2( getType() ) );
    return stringBuffer.toString();
  }
}
