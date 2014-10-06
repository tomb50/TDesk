<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<%@ page import="tomb.supportsim.models.enums.RoleEnum" %>
<%@ page import="tomb.supportsim.models.enums.TicketStateEnum" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>

<%
    for ( final RoleEnum roleEnum : RoleEnum.values() )
    {
        // Don't care about including ALL Enum.
        if ( roleEnum != RoleEnum.ALL )
        {
%>

<div class="row-fluid">
    <%
        List<Analyst> analysts = ViewHelper.getAnalystInWIP( roleEnum );
    %>
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe074;"></span> <%=roleEnum%> Workloads
                </div>
            </div>
            <div class="widget-body">
                <%
                    if ( ViewHelper.roleHasActiveTickets( roleEnum ) )
                    {
                %>
                <table class="table table-bordered no-margin">
                    <thead>
                    <tr>
                        <th>
                            Ticket Status
                        </th>

                        <%
                            for ( final Analyst analyst : analysts )
                            {
                        %>
                        <th>
                            <%= analyst.getName() %>'s Tickets
                        </th>
                        <%
                            }
                        %>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="warning">
                        <td>
                            <%=TicketStateEnum.WIP%>
                        </td>
                        <%
                            for ( final Analyst analyst : analysts )
                            {
                                final List<SupportTicket> wipTickets =
                                        ViewHelper.getTickets( analyst.getId(), TicketStateEnum.WIP );
                                final SupportTicket wipTicket =
                                        wipTickets.get( 0 ); //Like highlander, there should be only one
                        %>
                        <td>
                            <%=wipTicket.getId()%>
                        </td>
                        <%
                            }
                        %>
                    </tr>

                    <%
                        for ( int i = 0; i < ViewHelper.getLargestQueue( roleEnum ); i++ )
                        {
                    %>
                    <tr class="info">
                        <td>
                            <%=TicketStateEnum.QUEUED%>
                        </td>
                        <%
                            for ( final Analyst analyst : analysts )
                            {
                                final List<SupportTicket> queuedTickets =
                                        ViewHelper.getTickets( analyst.getId(), TicketStateEnum.QUEUED );
                                final SupportTicket queuedTicket;
                                if ( queuedTickets.size() > i )
                                {
                                    queuedTicket =
                                            queuedTickets.get( i );
                                }
                                else
                                {
                                    queuedTicket =
                                            null;
                                }
                        %>
                        <td>
                            <%= queuedTicket != null ? queuedTicket.getId() : "-"%>
                        </td>
                        <%
                            }
                        %>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <br>
                <%
                    }
                %>

                <% final String freeAnalysts = ViewHelper.getFreeAnalysts( roleEnum );
                    {
                        if ( !freeAnalysts.isEmpty() )
                        {
                %>
                <div class="stylish-lists">
                    <dl class="no-margin">
                        <dt class="text-success">
                            Analysts with no work:
                        </dt>
                        <dd>
                            <%= freeAnalysts %>
                        </dd>

                    </dl>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
<%
        }
    }
%>
