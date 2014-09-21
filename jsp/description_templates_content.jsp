<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.DescriptionTemplate" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe074;"></span>Description Templates
                </div>
            </div>
            <div class="widget-body">

                <% List<DescriptionTemplate> descriptions = ViewHelper.getAllDescriptionTemplates();
                    List<Field> descriptionFields = ViewHelper.getDescriptionTemplateAttributes();
                %>
                <table class="table table-striped table-bordered table-condensed">

                    <thead>
                    <tr>
                        <% for ( int col = 0; col < descriptionFields.size(); col++ )
                        { %>
                        <th><%= descriptionFields.get( col ).getName() %>
                        </th>
                        <% } %>
                    </tr>
                    </thead>
                    <% for ( int rowNumber = 0; rowNumber < descriptions.size(); rowNumber++ )
                    { %>
                    <tr>
                        <% for ( int col = 0; col < descriptionFields.size(); col++ )
                        { %>
                        <td><%= DescriptionTemplate.class.getMethod(
                                "get".concat( StringUtils.capitalize( descriptionFields.get( col ).getName() ) ) ).invoke(
                                descriptions.get( rowNumber ) ) %>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>