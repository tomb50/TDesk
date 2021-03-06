package com.tombeadman.tdesk.control;

import com.tombeadman.tdesk.models.zendesk.ZDOrganisation;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 20:56
 */
public class OrganisationManager extends ModelManager
{

  public ZDOrganisation getOrganisation(final Long organisationId)
  {
    return getCache().getOrganisationMap().get( organisationId );
  }
}
