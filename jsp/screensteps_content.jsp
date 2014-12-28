<%@ page import="com.tombeadman.tdesk.view.ViewHelper" %>
<%@ page import="com.tombeadman.tdesk.models.screensteps.ScreenstepsTableEntry" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe071;"></span> ScreenSteps Articles
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>Space</th>
                        <th>Manual</th>
                        <th>Chapter</th>
                        <th>Title</th>
                    </tr>
                    </thead>
                    <%
                        for ( com.tombeadman.tdesk.models.screensteps.ScreenstepsTableEntry screenstepsTableEntry : com.tombeadman.tdesk.view.ViewHelper.getSceenstepsTableEntries() )
                        {
                            final String spaceName = screenstepsTableEntry.getSpaceName();
                            final String manualName = screenstepsTableEntry.getManualName();
                            final String chapterName = screenstepsTableEntry.getChapterName();
                            final String lessonTitle = screenstepsTableEntry.getLessonName();
                            final String url = screenstepsTableEntry.getUrl();
                    %>
                    <tr class="clickableRow" href=<%=url%>>
                        <td style="word-wrap: break-word;min-width: 175px;max-width: 200px;">
                            <%=spaceName%>
                        </td>
                        <td style="word-wrap: break-word;min-width: 175px;max-width: 200px;"><%= manualName %>
                        </td>
                        <td style="word-wrap: break-word;min-width: 175px;max-width: 200px;"><%= chapterName %>
                        </td>
                        <td style="word-wrap: break-word;min-width: 175px;max-width: 400px;"><%= lessonTitle %>
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
    jQuery( document ).ready( function ( $ )
                              {
                                  $( ".clickableRow" ).click( function ()
                                                              {
                                                                  window.open( $( this ).attr( "href" ) );
                                                              } );
                              } );
</script>