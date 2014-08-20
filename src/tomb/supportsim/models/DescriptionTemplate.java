package tomb.supportsim.models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 10:38
 */
public class DescriptionTemplate implements Serializable
{
  private DescriptionTemplatePK id;
  private String text1;
  private String text2;
  private String text3;
  public DescriptionTemplate()
  {
  }

  public DescriptionTemplatePK getId()
  {
    return id;
  }

  public void setId( final DescriptionTemplatePK id )
  {
    this.id = id;
  }

  public String getText1()
  {
    return text1;
  }

  public void setText1( final String text1 )
  {
    this.text1 = text1;
  }

  public String getText2()
  {
    return text2;
  }

  public void setText2( final String text2 )
  {
    this.text2 = text2;
  }

  public String getText3()
  {
    return text3;
  }

  public void setText3( final String text3 )
  {
    this.text3 = text3;
  }
}
