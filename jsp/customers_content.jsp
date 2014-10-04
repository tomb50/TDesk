<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="tomb.supportsim.models.Customer" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.List" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe075;"></span> Customers
                </div>
            </div>
            <div class="widget-body">
                <% List<Customer> customers = ViewHelper.getAllCustomers();
                    List<Field> customerFields = ViewHelper.getCustomerAttributes();
                %>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <% for ( int col = 0; col < customerFields.size(); col++ )
                        { %>
                        <th><%= customerFields.get( col ).getName() %>
                        </th>
                        <% } %>
                    </tr>
                    </thead>
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
        </div>
    </div>
</div>