package com.tombeadman.tdesk.control;

import com.tombeadman.tdesk.models.zendesk.ZDForum;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:56
 */
public class ForumManager extends ModelManager
{

  public ZDForum getForum(final Long forumId)
  {
    return getCache().getForumMap().get( forumId );
  }
}
