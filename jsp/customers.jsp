<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.Customer" %>
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
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<% List<Customer> customers = ViewHelper.getAllCustomers();
    List<Field> customerFields = ViewHelper.getCustomerAttributes();
%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">SupportSim</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav pull-right">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="run.jsp">Run</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">All Tables <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="analysts.jsp">Analysts</a></li>
                        <li><a href="customers.jsp">Customers</a></li>
                        <li><a href="description_templates.jsp">Descriptions</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Tickets</li>
                        <li><a href="tickets.jsp">Tickets</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>
<br>
<br>

<div class="container">
    <h1>Customers</h1>
    <table class="table table-striped table-bordered table-condensed">
        <% for ( int rowNumber = 0; rowNumber < customers.size(); rowNumber++ )
        { %>
        <tr>
            <% for ( int col = 0; col < customerFields.size(); col++ )
            { %>
            <td><%= Customer.class.getMethod(
                    "get".concat( StringUtils.capitalize( customerFields.get( col ).getName() ) ) ).invoke(
                    customers.get( rowNumber ) ) %>
            </td>
            <% } %>
        </tr>
        <% } %>
    </TABLE>
</div>

        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>$( "#navbar" ).load( "navbar.html" );</script>
</body>
</html>
