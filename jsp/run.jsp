<%@ page import="tomb.supportsim.app.SupportSimApp" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
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


<%
    SupportSimApp supportSimApp = SupportSimApp.getInstance();
    supportSimApp.start(true);
%>

<div id="navbar"></div>
<br>
<br>

<% List<List<Analyst>> lol = ViewHelper.getActiveAnalystLol();
%>

<div class="container">
    <h1>Who is working?</h1>

    <div class="row">
        <% for ( int i = 0; i < lol.size(); i++ )
        {
            List<Analyst> roleList = lol.get( i ); %>
        <div class="col-md-4">
            <p>BOX A</p>
        <%}%>
        </div>


        <!--div class="col-md-4"><p>Box 1</p></div>
        <div class="col-md-4"><p>Box 2</p></div>
        <div class="col-md-4"><p>Box 3</p></div>
        <div class="col-md-4"><p>Box 4</p></div>
        <div class="col-md-4"><p>Box 5</p></div>
        <div class="col-md-4"><p>Box 6</p></div>
        <div class="col-md-4"><p>Box 7</p></div>
        <div class="col-md-4"><p>Box 8</p></div>
        <div class="col-md-4"><p>Box 9</p></div>
        <div class="col-md-4"><p>Box 10</p></div>
        <div class="col-md-4"><p>Box 11</p></div>
        <div class="col-md-4"><p>Box 12</p></div-->
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
