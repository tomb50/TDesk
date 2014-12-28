package com.tombeadman.tdesk.control;

import com.tombeadman.screensteps.model.Lesson;
import com.tombeadman.screensteps.model.Manual;
import com.tombeadman.tdesk.datastore.Cache;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/12/14 Time: 21:56
 */
public class SSManager
{
  public List<Lesson> getLessons()
  {
    return Cache.getInstance().getLessons();
  }

  public List<Manual> getManuals()
  {
    return Cache.getInstance().getManuals();
  }
}
