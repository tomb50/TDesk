<%@ page import="tomb.supportsim.controllers.TicketReporter" %>
<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.models.Customer" %>
<%@ page import="tomb.supportsim.models.SupportTicket" %>
<%@ page import="tomb.supportsim.models.enums.TicketTypeEnum" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<div class="row-fluid">
    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe074;"></span> Total Tickets
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="clients">
                        <h3> <%= ViewHelper.getTotalTicketCount()%> </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe1cf;"></span> Locked Documents
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="products">
                        <h3> <%= ViewHelper.getTicketCountByType( TicketTypeEnum.LOCKEDDOCUMENT )%> </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe036;"></span> Java
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="sales">
                        <h3> <%= ViewHelper.getTicketCountByType( TicketTypeEnum.JAVA )%> </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe038;"></span> ABL
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="income">
                        <h3> <%= ViewHelper.getTicketCountByType( TicketTypeEnum.ABL )%> </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe038;"></span> DBA
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="expenses">
                        <h3> <%= ViewHelper.getTicketCountByType( TicketTypeEnum.DBA )%> </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="span2">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe08a;"></span> System Down
                </div>
            </div>
            <div class="widget-body">
                <div class="current-statistics">
                    <div class="signups">
                        <h3><%= ViewHelper.getTicketCountByType( TicketTypeEnum.SYSTEMDOWN )%>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe08a;"></span> Ticket Overview
                </div>
            </div>
            <div class="widget-body">
                <% List tickets = ViewHelper.getJoinedDetailsForAllTickets(); %>

                <table class="table table-striped table-bordered table-condensed table-hover no-margin">
                    <thead>
                        <tr>
                            <th>Ticket Number</th>
                            <th>Description</th>
                            <th>Customer</th>
                            <th>Status</th>
                            <th>Assignee</th>
                            <th>Date Assigned</th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (int i = 0; i <tickets.size(); i++)
                            {
                                SupportTicket ticket =
                                        (SupportTicket) ((Map)tickets.get( i )).get( TicketReporter.TICKET_KEY );
                                Analyst analyst =
                                        (Analyst) ((Map)tickets.get( i )).get( TicketReporter.ANALYST_KEY );
                                Customer customer =
                                        (Customer) ((Map)tickets.get( i )).get( TicketReporter.CUSTOMER_KEY );

                        %>
                             <tr>
                                 <td>
                                     <span>
                                         <%= ticket.getId() %>
                                     </span>
                                 </td>
                                 <td>
                                     <span>
                                         <%= ticket.getDescription() %>
                                     </span>
                                 </td>
                                 <td>
                                     <span>
                                         <%= customer.getName() %>
                                     </span>
                                 </td>
                                 <td>
                                     <span id="state-col" class="badge" >
                                         <%= ticket.getState() %>
                                     </span>
                                 </td>
                                 <td>
                                     <span>
                                         <%= analyst.getName() %>
                                     </span>
                                 </td>
                                 <td>
                                     <span>
                                         <%= ticket.getTimeWIPStarted() %>
                                     </span>
                                 </td>


                             </tr>

                           <% }   %>
                    </tbody>
                    </table>


            </div>
        </div>
    </div>
    </div>

</div>
<!-- Add a bit of color to the Ticket statuses-->
<script>
    $( '#state-col.badge:contains("CLOSED")' ).addClass( 'badge-success' );
    $( '#state-col:contains("NEW")' ).addClass( 'badge-important' );
    $( '#state-col:contains("QUEUED")' ).addClass( 'badge-info' );
    //$('#tb.badge:contains("WIP")').addClass('badge-important');
</script>