<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe074;"></span> Support Tickets
                </div>
            </div>
            <div class="widget-body">

                <% List<SupportTicket> tickets = ViewHelper.getAllTickets();
                List<Field> ticketFields = ViewHelper.getTicketAttributes();
                    %>

                    <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                        <thead>
                        <tr>
                            <% for ( int col = 0; col < ticketFields.size(); col++ )
                            { %>
                            <th><%= ticketFields.get( col ).getName() %>
                            </th>
                            <% } %>
                        </tr>
                        </thead>

                        <tbody>
                        <% for ( int rowNumber = 0; rowNumber < tickets.size(); rowNumber++ )
                        { %>
                        <tr>
                            <% for ( int col = 0; col < ticketFields.size(); col++ )
                            { %>
                            <td>
                                    <span>
                                    <%= SupportTicket.class.getMethod(
                                        "get".concat(
                                                StringUtils.capitalize( ticketFields.get( col ).getName() ) ) ).invoke(
                                        tickets.get( rowNumber ) ) %>
                                        <span/>
                            </td>
                            <% } %>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>


            </div>
        </div>
    </div>
</div>