package tomb.supportsim.models.jira;

import java.io.Serializable;
import java.net.URI;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 17/12/14 Time: 10:59
 */
public class UserShell implements Serializable
{
  private String displayName;
  private URI self;
  private String emailAddress;

  public String getDisplayName()
  {
    return displayName;
  }

  public void setDisplayName( final String displayName )
  {
    this.displayName = displayName;
  }

  public URI getSelf()
  {
    return self;
  }

  public void setSelf( final URI self )
  {
    this.self = self;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress( final String emailAddress )
  {
    this.emailAddress = emailAddress;
  }
}
