<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.Customer" %>
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

<%  List<Customer> customers = ViewHelper.getAllCustomers();
    List<Field> customerFields = ViewHelper.getCustomerAttributes();
%>

<h1>Customers</h1>

<TABLE>
    <% for ( int rowNumber = 0; rowNumber < customers.size(); rowNumber++ )
    { %>
    <TR>
        <% for ( int col = 0; col < customerFields.size(); col++ )
        { %>
        <TD><%= Customer.class.getMethod(
                "get".concat( StringUtils.capitalize( customerFields.get( col ).getName() ) ) ).invoke(
                customers.get( rowNumber ) ) %>
        </TD>
        <% } %>
    </TR>
    <% } %>
</TABLE
</body>
</html>
