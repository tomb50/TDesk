<%@ page import="tomb.supportsim.models.Analyst" %>
<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<div class="row-fluid">
    <!-- Most resolved Bars -->
    <div class="span6">
        <div class="widget no-margin">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe095;"></span> Total Resolved By Analyst
                </div>
            </div>
            <div class="widget-body">
                <ul class="progress-statistics">
                    <%
                        BigDecimal totalClosedCount = BigDecimal.valueOf( ViewHelper.getTotalClosedTicketCount() );
                        HashMap map = (HashMap) ViewHelper.getClosedTicketCountByAnalystMap();
                        for ( final Iterator it = map.entrySet().iterator(); it.hasNext(); )
                        {
                            Map.Entry pairs = (Map.Entry) it.next();
                            String analystName = ((Analyst)pairs.getKey()).getName();
                            Integer intCount =  (Integer) pairs.getValue();
                            BigDecimal percent = BigDecimal.valueOf( intCount );
                            percent = percent.divide( totalClosedCount, 5, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) );

                    %>
                    <li>
                        <div class="details">
                            <span> <%=analystName%></span>
                            <span class="pull-right"><%=intCount%></span>
                        </div>
                        <div class="progress progress-striped active">
                            <div class="bar" style="width: <%=percent%>%;">
                            </div>
                        </div>
                    </li>
                    <%}%>
                </ul>
            </div>
        </div>
    </div>
    <!-- Total ticket count bars -->
    <div class="span6">
        <div class="widget no-margin">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe101;"></span> Total tickets
                </div>
            </div>
            <div class="widget-body">
                <ul class="progress-statistics">
                    <% BigDecimal totalCount = BigDecimal.valueOf( ViewHelper.getTotalTicketCount() );
                        HashMap typeMap = (HashMap) ViewHelper.getTicketCountByTypeMap();
                        for ( final Iterator it = typeMap.entrySet().iterator(); it.hasNext(); )
                        {
                            Map.Entry pairs = (Map.Entry) it.next();
                            String type = pairs.getKey().toString();
                            Integer intVal =  (Integer) pairs.getValue();
                            BigDecimal val = BigDecimal.valueOf( intVal );
                            val = val.divide( totalCount, 3, BigDecimal.ROUND_CEILING).multiply(
                                    BigDecimal.valueOf( 100 ) );
                    %>
                    <li>
                        <div class="details">
                            <span> <%=type%></span>
                            <span class="pull-right"><%=intVal%></span>
                        </div>
                        <div class="progress progress-striped active">
                            <div class="bar" style="width: <%=val.stripTrailingZeros()%>%;">
                            </div>
                        </div>
                    </li>
                    <%}%>
                 <!--ToDo - apply progress-info, progress-warning  etc stlying-->
                </ul>
            </div>
        </div>
    </div>
</div>