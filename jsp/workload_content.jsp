<%@ page import="com.tombeadman.tdesk.models.zendesk.ZDTicket" %>
<%@ page import="com.tombeadman.tdesk.models.zendesk.ZDUser" %>
<%@ page import="com.tombeadman.tdesk.view.ViewHelper" %>
<%@ page import="java.util.List" %>

<div class="row-fluid">
    <%
        List<ZDUser> otherAnalysts = ViewHelper.getSupportAnalysts();
    %>
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe074;"></span> Support Workloads - Open Tickets
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                    <tr>
                        <%
                            for ( final ZDUser user : otherAnalysts )
                            {
                                if ( user != null )
                                {
                        %>
                        <th>
                            <%= user.getName() %>
                        </th>
                        <%
                                }
                            }
                        %>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int max2 = ViewHelper.getLargestWorkload();
                        for ( int i = 0; i < max2 ;i++ )
                        {
                    %>
                    <tr>
                        <%
                            for ( final ZDUser user : otherAnalysts )
                            {
                                if ( user != null )
                                {

                                    final List<ZDTicket> tickets =
                                            ViewHelper.getOpenTicketsForAnalystFromCache( user.getId() );
                                    ZDTicket ticket;
                                    if ( tickets != null && tickets.size() > i )
                                    {
                                        ticket = tickets.get( i );
                                    }
                                    else
                                    {
                                        ticket = null;
                                    }

                        %>
                        <%
                            if ( ticket != null )
                            {
                        %>
                        <td class="clickableCell" href=<%=ViewHelper.getTicketLink( ticket.getId() )%>>
                            <%=ticket.getId()%>
                        </td>
                        <%
                        }
                        else
                        {
                        %>
                        <td>
                            -
                        </td>
                        <%
                            }
                        %>
                        <%

                                }
                            }
                        %>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <br>
            </div>
        </div>
    </div>
</div>


<script>
jQuery( document ).ready( function ( $ ){
$( ".clickableCell" ).click( function (){
window.open( $( this ).attr( "href" ) );
} );
} );
</script>

