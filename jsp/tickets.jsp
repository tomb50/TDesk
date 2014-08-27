<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
        }
    </style>
</head>
<body>

<%  List<SupportTicket> tickets = ViewHelper.getAllTickets();
    List<Field> ticketFields = ViewHelper.getTicketAttributes();
%>

<h1>Tickets</h1>

<TABLE>
    <% for ( int rowNumber = 0; rowNumber < tickets.size(); rowNumber++ )
    { %>
    <TR>
        <% for ( int col = 0; col < ticketFields.size(); col++ )
        { %>
        <TD><%= SupportTicket.class.getMethod(
                "get".concat( StringUtils.capitalize( ticketFields.get( col ).getName() ) ) ).invoke(
                tickets.get( rowNumber ) ) %>
        </TD>
        <% } %>
    </TR>
    <% } %>
</TABLE
</body>
</html>
