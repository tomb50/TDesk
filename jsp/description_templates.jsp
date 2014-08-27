<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.DescriptionTemplate" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%  List<DescriptionTemplate> descriptions = ViewHelper.getAllDescriptionTemplates();
    List<Field> descriptionFields = ViewHelper.getDescriptionTemplateAttributes();
%>

<h1>Description Templates</h1>

<TABLE>
    <% for ( int rowNumber = 0; rowNumber < descriptions.size(); rowNumber++ )
    { %>
    <TR>
        <% for ( int col = 0; col < descriptionFields.size(); col++ )
        { %>
        <TD><%= DescriptionTemplate.class.getMethod(
                "get".concat( StringUtils.capitalize( descriptionFields.get( col ).getName() ) ) ).invoke(
                descriptions.get( rowNumber ) ) %>
        </TD>
        <% } %>
    </TR>
    <% } %>
</TABLE
</body>
</html>
