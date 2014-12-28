package com.tombeadman.tdesk.control.imports;

import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 14/12/14 Time: 14:40
 */
public abstract class Importer implements Runnable
{
  protected final Logger logger = Logger.getLogger( getClass().getSimpleName() );
  private Thread t;
  private boolean active = true;

  public boolean isActive()
  {
    return active;
  }

  protected void setActive( final boolean active )
  {
    this.active = active;
  }



  public void start()
  {
    if ( t == null )
    {
      t = new Thread( this );
      t.start();
    }
  }
}
