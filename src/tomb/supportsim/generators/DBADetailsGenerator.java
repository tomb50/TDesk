package tomb.supportsim.generators;

import tomb.supportsim.models.enums.TicketTypeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 03/08/2014 Time: 13:52
 */
public class DBADetailsGenerator extends DetailsGenerator
{
  @Override
  protected TicketTypeEnum getType()
  {
    return TicketTypeEnum.DBA;
  }

  @Override
  protected BigDecimal getEstimatedCompletionTime()
  {
    return new BigDecimal( 10 * randomGenerator.nextDouble() ).setScale( 3, RoundingMode.UP );
  }
}
