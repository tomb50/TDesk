<%@ page import="tomb.supportsim.controllers.AnalystReporter" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
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

<% List<Analyst> analysts = ViewHelper.getAnalysts();
    List<Field> analystFields = ViewHelper.getAnalystAttributes();
%>

<h1>Analysts</h1>

<TABLE>
    <% for ( int rowNumber = 0; rowNumber < analysts.size(); rowNumber++ )
    { %>
    <TR>
        <% for ( int col = 0; col < analystFields.size(); col++ )
        { %>
        <TD><%= Analyst.class.getMethod(
                "get".concat( StringUtils.capitalize( analystFields.get( col ).getName() ) ) ).invoke(
                analysts.get( rowNumber ) ) %>
        </TD>
        <% } %>
    </TR>
    <% } %>
</TABLE
</body>
</html>
