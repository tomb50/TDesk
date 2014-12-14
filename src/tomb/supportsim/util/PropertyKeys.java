package tomb.supportsim.util;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 00:56
 */
public interface PropertyKeys
{
  String ASSIGNMENT_METHOD = "assignment.method";
  String CRON_ASSIGNMENT = "${poller.assignment.cron}";
  String CRON_UPDATE_TICKETS = "${poller.update.tickets.cron}";
  String CRON_CREATION = "${poller.assignment.cron}";
  String ZENDESK_SUBDOMAIN = "zendesk.subdomain";
  String ZENDESK_USER = "zendesk.user";
  String ZENDESK_PASSWORD = "zendesk.password";
  String ZENDESK_TOKEN = "zendesk.token";
  String SCREENSTEPS_USER = "screensteps.username";
  String SCREENSTEPS_PASSWORD = "screensteps.password";
  String SCREENSTEPS_DOMAIN = "screensteps.domain";
  String JIRA_URL = "jira.url";
  String JIRA_USERNAME = "jira.username";
  String JIRA_PASSWORD = "jira.password";
  String JIRA_TIMEOUT = "jira.timeout"; // seconds
  String LOCAL_DATA = "app.data.local";
}
