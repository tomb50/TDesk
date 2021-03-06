
<%@ page import="java.util.Date" %>
<%@ page import="com.tombeadman.tdesk.models.jira.IssueShell" %>
<%@ page import="com.tombeadman.tdesk.util.LinkUtil" %>
<%@ page import="com.tombeadman.tdesk.view.ViewHelper" %>

<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe071;"></span> Jira Issues
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Type</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Creation Date</th>
                    </tr>
                    </thead>
                    <% for ( IssueShell issueShell : ViewHelper.getJiraIssues() )
                    {
                        final String id = issueShell.getKey();
                        final String type = issueShell.getIssueType().getName();
                        final String title = issueShell.getSummary();
                        final String status = issueShell.getStatus().getName();
                        final Date creationDate = issueShell.getCreationDate().toDate();
                    %>
                    <tr class="clickableRow" href=<%=LinkUtil.getJiraBrowseLink( id )%>>
                        <td style="word-wrap: break-word;min-width: 100px;max-width: 150px;">
                            <%=id%>
                        </td>
                        <td style="word-wrap: break-word;min-width: 50px;max-width: 100px;">
                            <span id="type-col" class="badge">
                                <%= type %>
                            </span>
                        </td>
                        <td><%= title %>
                        </td>
                        <td style="word-wrap: break-word;min-width: 175px;max-width: 200px;">
                            <span id="status-col" class="badge">
                                <%= status %>
                            </span>
                        </td>
                        <td style="word-wrap: break-word;min-width: 200px;max-width: 250px;"><%= creationDate %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $( '#status-col.badge:contains("Closed")' ).addClass( 'badge-success' );
    $( '#status-col.badge:contains("Open")' ).addClass( 'badge-important' );
    $( '#status-col.badge:contains("Released To Live")' ).addClass( 'badge-info' );
    $( '#status-col.badge:contains("Released For Testing")' ).addClass( 'badge-warning' ); //Not a waring,
                                                                                           // just a nice colour
</script>
<script>
    //$( '#type-col.badge:contains("Bug")' ).addClass( 'badge-inverse' );
    $( '#type-col.badge:contains("Feature")' ).addClass( 'badge-inverse' );
</script>
<script>
    jQuery( document ).ready( function ( $ )
                              {
                                  $( ".clickableRow" ).click( function ()
                                                              {
                                                                  window.open( $( this ).attr( "href" ) );
                                                              } );
                              } );
</script>