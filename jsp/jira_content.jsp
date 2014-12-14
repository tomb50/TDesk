<%@ page import="tomb.supportsim.view.ViewHelper" %>
<%@ page import="com.tombeadman.screensteps.model.Lesson" %>
<%@ page import="com.tombeadman.screensteps.model.Manual" %>
<%@ page import="com.tombeadman.screensteps.model.Chapter" %>
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

                    <% for ( Manual manual : ViewHelper.getManuals() )
                    {
                        for ( Chapter chapter : manual.getChapters() )
                        {
                            for ( Lesson lesson : chapter.getLessons() )
                            {
                                String spaceName = manual.getSpace().getTitle();
                                String manualName = manual.getTitle();
                                String chapterName = chapter.getTitle();
                                String lessonTitle = lesson.getTitle();
                                String url = lesson.getUrl();

                    %>
                    <tr class="clickableRow" href=<%=url%>>
                        <td style="word-wrap: break-word;min-width: 350px;max-width: 450px;">
                            <%=spaceName%>
                        </td>
                        <td><%= manualName %>
                        </td>
                        <td><%= chapterName %>
                        </td>
                        <td><%= lessonTitle %>
                        </td>
                        <!--td> //here
                        </td-->
                    </tr>
                    <%
                                }
                            }
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