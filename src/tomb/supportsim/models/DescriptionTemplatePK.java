package tomb.supportsim.models;

import tomb.supportsim.models.enums.TicketTypeEnum;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 10:38
 */
public class DescriptionTemplatePK implements Serializable, Comparable
{
  private TicketTypeEnum type;
  private int id;

  private int hCode = 0;

  public DescriptionTemplatePK( TicketTypeEnum type, int id )
  {
    this.type = type;
    this.id = id;
    String hCodeS = String.valueOf( this.type ) + String.valueOf( this.id );
    hCode = hCodeS.hashCode();
  }

  public DescriptionTemplatePK()
  {
  }

  public TicketTypeEnum getType()
  {
    return type;
  }

  public void setType( final TicketTypeEnum type )
  {
    this.type = type;
  }

  public int getId()
  {
    return id;
  }

  public void setId( final int id )
  {
    this.id = id;
  }


  @Override
  public int hashCode()
  {
    return hCode;
  }

  @Override
  public boolean equals( final Object obj )
  {
    return ( obj instanceof DescriptionTemplatePK ) && ( obj.hashCode() == this.hashCode() )
      && this.compareTo( obj ) == 0;
  }

  @Override
  public int compareTo( Object other )
    throws ClassCastException
  {
    if ( !( other instanceof DescriptionTemplatePK ) )
    {
      throw new ClassCastException( "Object should be instance of DescriptionPk" );
    }

    DescriptionTemplatePK otherPK = (DescriptionTemplatePK) other;
    int compare = this.type.compareTo( otherPK.type );

    if ( compare == 0 )
    {
      compare = new Integer( this.id ).compareTo( otherPK.id );
    }
    return compare;
  }

  @Override
  public String toString()
  {
    return
      type +
      ", " + id ;
  }
}
