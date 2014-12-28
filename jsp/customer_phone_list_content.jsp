<%@ page import="com.tombeadman.tdesk.models.ZDOrganisation" %>
<%@ page import="com.tombeadman.tdesk.models.ZDUser" %>
<%@ page import="com.tombeadman.tdesk.view.ViewHelper" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe071;"></span> Customer Contact List
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Company</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>
                    </thead>

                    <% for ( ZDUser user : ViewHelper.getOrderedCustomers() )
                    {
                        ZDOrganisation organisation = ViewHelper.getOrganisation( user.getOrganizationId() );
                    %>
                    <tr class="clickableRow" href=<%=ViewHelper.getUserLink(user.getId())%>>
                        <td><%=user.getName()%>
                        </td>
                        <td><%= organisation != null ? organisation.getName() : "" %>
                        </td>
                        <td><%=user.getEmail()%>
                        </td>
                        <td><%=user.getPhone() != null ? user.getPhone() : "No contact number" %>
                        </td>

                    </tr>
                    <% } %>



                </table>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function($) {
        $(".clickableRow").click(function() {
            window.open($(this).attr("href"));
        });
    });
</script>