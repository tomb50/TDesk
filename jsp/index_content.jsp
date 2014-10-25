<%@ page import="org.zendesk.client.v2.model.Status" %>
<%@ page import="tomb.supportsim.models.ZDOrganisation" %>
<%@ page import="tomb.supportsim.models.ZDTicket" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.List" %>
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
                    <span class="fs1" aria-hidden="true" data-icon="&#xe088;"></span> First Line
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="products">
                        <h3><%= ViewHelper.getOpenTicketCount("Support")%>
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
                        <h3><%= ViewHelper.getOpenTicketCount("Java")%>
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
                    <span class="fs1" aria-hidden="true" data-icon="&#xe094;"></span> Character
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="income">
                        <h3><%= ViewHelper.getOpenTicketCount( "Character" )%>
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
                    <span class="fs1" aria-hidden="true" data-icon="&#xe0b9;"></span> Projects
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="expenses">
                        <h3><%= ViewHelper.getOpenTicketCount("Projects")%>
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
                    <span class="fs1" aria-hidden="true" data-icon="&#xe0a4;"></span> Solved
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="signups">
                        <h3><%= ViewHelper.getTicketCountByState( Status.SOLVED)%>
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
                          <% if (ViewHelper.getTicketCountByState(Status.NEW) == 0)
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
                <% List<ZDTicket> newTickets = ViewHelper.getTicketByState( Status.NEW ); %>

                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                    <tr>
                        <th>Ticket Number</th>
                        <th>Description</th>
                        <th>Customer</th>
                        <th>Status</th>
                        <th>Date Created</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for ( int i = 0; i < newTickets.size(); i++ )
                    {
                        ZDTicket ticket =  newTickets.get( i );
                        ZDOrganisation organisation = ViewHelper.getOrganisation( ticket.getOrganizationId() );

                    %>
                    <tr>
                        <td>
                                     <span>
                                         <%= ticket.getId() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getSubject() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= organisation.getName() %>
                                     </span>
                        </td>
                        <td>
                                     <span id="state-col" class="badge">
                                         <%= ticket.getStatus() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getCreatedAt() %>
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
                final BigDecimal openCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( Status.OPEN ) );
                final BigDecimal newCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( Status.NEW ) );
                final BigDecimal pendingCount =
                        BigDecimal.valueOf( ViewHelper.getTicketCountByState( Status.PENDING ) );
                final BigDecimal holdCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( Status.HOLD ) );
                final BigDecimal solvedCount = BigDecimal.valueOf( ViewHelper.getTicketCountByState( Status.SOLVED ) );
                final BigDecimal totalCount =
                        openCount.add( newCount ).add( pendingCount ).add( holdCount ).add( solvedCount );
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
                        <h5 class="stat-value"><%= openCount%>
                            <span class="stat-name">Open</span>
                        </h5>

                        <div class="progress progress-striped active no-margin">
                            <div class="bar" style="width:
                                <%= openCount.equals( BigDecimal.ZERO ) ? openCount :
                                    openCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="fs1 arrow text-warning" aria-hidden="true" data-icon="&#xe091;"></span>
                        <h5 class="stat-value"><%= pendingCount%>
                            <span class="stat-name">pending</span>
                        </h5>

                        <div class="progress progress-striped progress-info active no-margin">
                            <div class="bar" style="width:
                                <%= pendingCount.equals( BigDecimal.ZERO ) ? pendingCount :
                                    pendingCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="fs1 arrow text-warning" aria-hidden="true" data-icon="&#xe091;"></span>
                        <h5 class="stat-value"><%= holdCount%>
                            <span class="stat-name">Hold</span>
                        </h5>

                        <div class="progress progress-striped progress-info active no-margin">
                            <div class="bar" style="width:
                                <%= holdCount.equals( BigDecimal.ZERO ) ? holdCount :
                                    holdCount.divide( totalCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) )%>%;">
                            </div>
                        </div>
                    </li>

                    <li>
                        <span class="fs1 arrow text-success" aria-hidden="true" data-icon="&#xe0fe;"></span>
                        <h5 class="stat-value"><%= solvedCount%>
                            <span class="stat-name">Solved</span>
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
                    <span class="fs1" aria-hidden="true" data-icon="&#xe07e;"></span> Open Ticket Overview
                </div>
            </div>
            <div class="widget-body">
                <% List<ZDTicket> tickets = ViewHelper.getOpenUnassignedTickets(); %>

                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                    <tr>
                        <th>Ticket Number</th>
                        <th>Description</th>
                        <th>Customer</th>
                        <th>Group</th>
                        <th>Last Updated</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for ( int i = 0; i < tickets.size(); i++ )
                    {
                        ZDTicket ticket = tickets.get( i ) ;
                        ZDOrganisation customer = ViewHelper.getOrganisation( ticket.getOrganizationId() );
                        String groupName = ticket.getGroupId() !=null
                                           ? ViewHelper.getGroupName(ticket.getGroupId()) : "";
                    %>
                    <tr class="clickableRow" href=<%=ViewHelper.getTicketLink(ticket.getId())%>>
                        <td>
                                     <span>
                                         <%= ticket.getId() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= ticket.getSubject() %>
                                     </span>
                        </td>
                        <td>
                                     <span>
                                         <%= customer != null ? customer.getName() : "" %>
                                     </span>
                        </td>
                        <td>
                                     <span id="group-col" class="badge">
                                         <%= groupName%>
                                     </span>
                        </td>

                        <td>
                                     <span>
                                         <%= ticket.getUpdatedAt()%>
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


<!-- Add a bit of color to the Ticket statuses-->
<script>
    $( '#state-col.badge:contains("PENDING")' ).addClass( 'badge-success' );
    $( '#state-col.badge:contains("NEW")' ).addClass( 'badge-important' );
    $( '#state-col.badge:contains("HOLD")' ).addClass( 'badge-info' );
    $( '#state-col.badge:contains("OPEN")' ).addClass( 'badge-warning' );
    $( '#group-col.badge:contains("Support")' ).addClass( 'badge-success' );
    $( '#group-col.badge:contains("Java")' ).addClass( 'badge-important' );
    $( '#group-col.badge:contains("Development")' ).addClass( 'badge-info' );
    $( '#group-col.badge:contains("Character")' ).addClass( 'badge-warning' );
</script>

<script>
    jQuery(document).ready(function($) {
        $(".clickableRow").click(function() {
            window.open($(this).attr("href"));
        });
    });
</script>