<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="refresh" content="10">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<% List<SupportTicket> tickets = ViewHelper.getAllTickets();
    List<Field> ticketFields = ViewHelper.getTicketAttributes();
%>

<div id="navbar"></div>

<div class="container">
    <h1>Tickets</h1>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">All Records</div>
        <div class="panel-body">
            <p>The following table contains all Support Ticket Records.</p>
        </div>
        <table class="table table-striped table-bordered table-condensed">
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
                <td><%= SupportTicket.class.getMethod(
                        "get".concat( StringUtils.capitalize( ticketFields.get( col ).getName() ) ) ).invoke(
                        tickets.get( rowNumber ) ) %>
                </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>$( "#navbar" ).load( "navbar.html" );</script>
</body>
</html>
