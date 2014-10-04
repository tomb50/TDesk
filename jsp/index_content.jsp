<%@ page import="tomb.supportsim.controllers.TicketReporter" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.models.Customer" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<%@ page import="tomb.supportsim.models.enums.TicketStateEnum" %>
<%@ page import="tomb.supportsim.models.enums.TicketTypeEnum" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<div class="row-fluid">
    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe0c2;"></span> Total Tickets
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="clients">
                        <h3><%= ViewHelper.getTotalTicketCount()%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe088;"></span> Locked Documents
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="products">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.LOCKEDDOCUMENT )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe094;"></span> Java
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="sales">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.JAVA )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe094;"></span> ABL
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="income">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.ABL )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe0b9;"></span> DBA
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="expenses">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.DBA )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe0a4;"></span> System Down
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="signups">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.SYSTEMDOWN )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">

    <div class="span8">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon=
                          <% if (ViewHelper.getTicketCountByState(TicketStateEnum.NEW) == 0)
                          { %>
                          "&#xe0fe;"></span> New Tickets  - No New Tickets!
                            <%
                          }
                           else
                          { %>
                          "&#xe0f5;"></span> New Tickets
                            <%
                          }
                          %>
                </div>
            </div>
            <div class="widget-body">
                <% List newTickets = ViewHelper.getJoinedDetailsForNewTickets(); %>

                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                    <tr>
                        <th>Ticket Number</th>
                        <th>Description</th>
                        <th>Customer</th>
                        <th>Status</th>
                        <th>Date Assigned</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for ( int i = 0; i < newTickets.size(); i++ )
                    {
                        SupportTicket ticket =
                                (SupportTicket) ( (Map) newTickets.get( i ) ).get( TicketReporter.TICKET_KEY );
                        Customer customer =
                                (Customer) ( (Map) newTickets.get( i ) ).get( TicketReporter.CUSTOMER_KEY );

                    %>
                    <tr>
                        <td>
                                     <span>
                                         <%= ticket.getId() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getDescription() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= customer.getName() %>
                                     </span>
                        </td>
                        <td>
                                     <span id="state-col" class="badge">
                                         <%= ticket.getState() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getTimeWIPStarted() %>
                                     </span>
                        </td>


                    </tr>

                    <% } %>
                    </tbody>
                </table>

            </div>
        </div>
    </div>


    <div class="span4">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe096;"></span> At A Glance
                </div>
            </div>

            <%
                final BigDecimal totalCount = BigDecimal.valueOf( ViewHelper.getTotalOpenTicketCount() );
                final BigDecimal newCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( TicketStateEnum.NEW ) );
                final BigDecimal queueCount =
                        BigDecimal.valueOf( ViewHelper.getTicketCountByState( TicketStateEnum.QUEUED ) );
                final BigDecimal wipCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( TicketStateEnum.WIP ) );
                final BigDecimal closedCount =
                        BigDecimal.valueOf( ViewHelper.getTicketCountByState( TicketStateEnum.CLOSED ) );
            %>

            <div class="widget-body">
                <ul id="stats-count">
                    <li>
                        <span class="fs1 arrow text-error" aria-hidden="true" data-icon="&#xe0f5;"></span>
                        <h5 class="stat-value"><%= newCount%>
                            <span class="stat-name">New</span>
                        </h5>

                        <div class="progress progress-striped progress-danger active no-margin">
                            <div class="bar" style="width:
                                <%= newCount.equals( BigDecimal.ZERO ) ? newCount :
                                    newCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="fs1 arrow text text-info" aria-hidden="true" data-icon="&#xe077;"></span>
                        <h5 class="stat-value"><%= queueCount%>
                            <span class="stat-name">Queued</span>
                        </h5>

                        <div class="progress progress-striped active no-margin">
                            <div class="bar" style="width:
                                <%= queueCount.equals( BigDecimal.ZERO ) ? queueCount :
                                    queueCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="fs1 arrow text-warning" aria-hidden="true" data-icon="&#xe091;"></span>
                        <h5 class="stat-value"><%= wipCount%>
                            <span class="stat-name">WIP</span>
                        </h5>

                        <div class="progress progress-striped progress-info active no-margin">
                            <div class="bar" style="width:
                                <%= wipCount.equals( BigDecimal.ZERO ) ? wipCount :
                                    wipCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="fs1 arrow text-success" aria-hidden="true" data-icon="&#xe0fe;"></span>
                        <h5 class="stat-value"><%= closedCount%>
                            <span class="stat-name">Closed</span>
                        </h5>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe07e;"></span> Recent Ticket Overview
                </div>
            </div>
            <div class="widget-body">
                <% List tickets = ViewHelper.getJoinedDetailsForAllTickets(); %>

                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                    <tr>
                        <th>Ticket Number</th>
                        <th>Description</th>
                        <th>Customer</th>
                        <th>Status</th>
                        <th>Assignee</th>
                        <th>WIP Start Date</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for ( int i = 0; i < tickets.size(); i++ )
                    {
                        SupportTicket ticket =
                                (SupportTicket) ( (Map) tickets.get( i ) ).get( TicketReporter.TICKET_KEY );
                        Analyst analyst =
                                (Analyst) ( (Map) tickets.get( i ) ).get( TicketReporter.ANALYST_KEY );
                        Customer customer =
                                (Customer) ( (Map) tickets.get( i ) ).get( TicketReporter.CUSTOMER_KEY );

                    %>
                    <tr>
                        <td>
                                     <span>
                                         <%= ticket.getId() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getDescription() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= customer.getName() %>
                                     </span>
                        </td>
                        <td>
                                     <span id="state-col" class="badge">
                                         <%= ticket.getState() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= analyst.getName() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getTimeWIPStarted() == null ? "-" : ticket.getTimeWIPStarted()%>
                                     </span>
                        </td>


                    </tr>

                    <% } %>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>

</div>
<!-- Add a bit of color to the Ticket statuses-->
<script>
    $( '#state-col.badge:contains("CLOSED")' ).addClass( 'badge-success' );
    $( '#state-col.badge:contains("NEW")' ).addClass( 'badge-important' );
    $( '#state-col.badge:contains("QUEUED")' ).addClass( 'badge-info' );
    $( '#state-col.badge:contains("WIP")' ).addClass( 'badge-warning' );
</script>