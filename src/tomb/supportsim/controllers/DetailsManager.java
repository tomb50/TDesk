package tomb.supportsim.controllers;

import tomb.supportsim.generators.*;
import tomb.supportsim.models.SupportTicket;

import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 20:39
 */
class DetailsManager
{

  private final Random randomGenerator;

  public DetailsManager()
  {
    randomGenerator = new Random();
  }

  public void createDetailsForNewTicket( final SupportTicket ticket )
  {
    final DetailsGenerator detailsGenerator = getDetailsGenerator( randomGenerator.nextInt( 100 ) );
    detailsGenerator.addDetails( ticket );
  }

  private DetailsGenerator getDetailsGenerator( final int key )
  {
    if ( key < 5 )
    {
      return new DBADetailsGenerator();
    }
    else if ( key < 15 )
    {
      return new SDDetailsGenerator();
    }
    else if ( key < 25 )
    {
      return new ABLDetailsGenerator();
    }
    else if ( key < 40 )
    {
      return new JavaDetailsGenerator();
    }
    else
    {
      return new LockedDocDetailsGenerator();
    }
  }
}
