<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe071;"></span> Analysts
                </div>
            </div>
            <div class="widget-body">
                <% List<Analyst> analysts = ViewHelper.getAnalysts();
                    List<Field> analystFields = ViewHelper.getAnalystAttributes();
                %>

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <% for ( int col = 0; col < analystFields.size(); col++ )
                        { %>
                        <th><%= analystFields.get( col ).getName() %>
                        </th>
                        <% } %>
                    </tr>
                    </thead>

                    <% for ( int rowNumber = 0; rowNumber < analysts.size(); rowNumber++ )
                    { %>
                    <tr>
                        <% for ( int col = 0; col < analystFields.size(); col++ )
                        { %>
                        <td><%= Analyst.class.getMethod(
                                "get".concat( StringUtils.capitalize( analystFields.get( col ).getName() ) ) ).invoke(
                                analysts.get( rowNumber ) ) %>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>