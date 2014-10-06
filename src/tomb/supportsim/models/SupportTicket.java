package tomb.supportsim.models;

import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 01/08/2014 Time: 11:51
 */
public class SupportTicket implements Serializable
{
  private int id;
  private TicketTypeEnum type;
  private String description;
  private TicketStateEnum state;
  private int assigneeId;
  private BigDecimal estimatedCompletionTime;
  private int customerId;
  private Timestamp timeWIPStarted;

  public SupportTicket()
  {
  }

  public int getId()
  {
    return id;
  }

  public void setId( final int id )
  {
    this.id = id;
  }

  public TicketTypeEnum getType()
  {
    return type;
  }

  public void setType( final TicketTypeEnum type )
  {
    this.type = type;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }

  public TicketStateEnum getState()
  {
    return state;
  }

  public void setState( final TicketStateEnum state )
  {
    this.state = state;
  }

  public int getAssigneeId()
  {
    return assigneeId;
  }

  public void setAssigneeId( final int assigneeId )
  {
    this.assigneeId = assigneeId;
  }

  public BigDecimal getEstimatedCompletionTime()
  {
    return estimatedCompletionTime;
  }

  public void setEstimatedCompletionTime( final BigDecimal estimatedCompletionTime )
  {
    this.estimatedCompletionTime = estimatedCompletionTime;
  }

  public int getCustomerId()
  {
    return customerId;
  }

  public void setCustomerId( final int customer )
  {
    this.customerId = customer;
  }

  public Timestamp getTimeWIPStarted()
  {
    return timeWIPStarted;
  }

  public void setTimeWIPStarted( final Timestamp timeAssigned )
  {
    this.timeWIPStarted = timeAssigned;
  }

  @Override
  public String toString()
  {
    return "SupportTicket{" +
      "id=" + id +
      ", type=" + type +
      ", description='" + description + '\'' +
      ", state=" + state +
      ", assigneeId=" + assigneeId +
      ", estimatedCompletionTime=" + estimatedCompletionTime +
      ", customerId=" + customerId +
      ", timeAssigned=" + timeWIPStarted +
      '}';
  }
}
