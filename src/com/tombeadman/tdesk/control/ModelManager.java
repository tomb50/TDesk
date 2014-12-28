package com.tombeadman.tdesk.control;

import com.tombeadman.tdesk.datastore.Cache;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:51
 */
public abstract class ModelManager
{
  private static Cache cache = Cache.getInstance();

  public static Cache getCache()
  {
    return cache;
  }

}
