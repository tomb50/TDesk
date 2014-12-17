<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="com.tombeadman.screensteps.model.Lesson" %>
<%@ page import="com.tombeadman.screensteps.model.Manual" %>
<%@ page import="com.tombeadman.screensteps.model.Chapter" %>
<%@ page import="tomb.supportsim.models.jira.IssueShell" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page import="java.util.Date" %>
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
                        <th>Title</th>
                        <th>Status</th>
                        <th>Creation Date</th>
                        <!--th>tags</th-->

                    </tr>
                    </thead>

                    <% for ( IssueShell issueShell : ViewHelper.getJiraBugs() )
                    {
                        String id = issueShell.getKey();
                        String title = issueShell.getSummary();
                        String status = issueShell.getStatus().getName();
                        Date creationDate = issueShell.getCreationDate().toDate();


                    %>
                    <tr class="clickableRow" href=<%=id%>>
                        <td style="word-wrap: break-word;min-width: 350px;max-width: 450px;">
                            <%=id%>
                        </td>
                        <td><%= title %>
                        </td>
                        <td><%= status %>
                        </td>
                        <td><%= creationDate %>
                        </td>
                        <!--td> //here
                        </td-->
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
    jQuery( document ).ready( function ( $ )
                              {
                                  $( ".clickableRow" ).click( function ()
                                                              {
                                                                  window.open( $( this ).attr( "href" ) );
                                                              } );
                              } );
</script>