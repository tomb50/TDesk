package com.tombeadman.tdesk.control;

import com.tombeadman.tdesk.models.ZDTopic;
import com.tombeadman.tdesk.view.TopicForumComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:56
 */
public class TopicManager extends ModelManager
{

  public List<ZDTopic> getOrderedTopics()
  {
    List<ZDTopic> topics =  getCache().getTopics();
    Collections.sort( topics, new TopicForumComparator() );
    return topics;
  }
}
