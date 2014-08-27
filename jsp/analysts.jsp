<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
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
