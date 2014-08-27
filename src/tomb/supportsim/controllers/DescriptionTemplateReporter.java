package tomb.supportsim.controllers;

import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.DescriptionTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/08/2014 Time: 21:17
 */
public class DescriptionTemplateReporter
{
  public List<DescriptionTemplate> getAllDescriptionTemplates()
  {
    return HibernateUtil.getEntityList( DescriptionTemplate.class );
  }
}
