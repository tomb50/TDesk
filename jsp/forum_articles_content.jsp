<%@ page import="com.tombeadman.tdesk.models.ZDTopic" %>
<%@ page import="com.tombeadman.tdesk.view.ViewHelper" %>
<div class="row-fluid">
    <div class="span">
        <div class="widget">
            <div class="widget-header">
                <div class="title">
                    <span class="fs1" aria-hidden="true" data-icon="&#xe071;"></span> Forum Articles
                </div>
            </div>
            <div class="widget-body">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Forum</th>
                        <th>Type</th>
                        <th>Submitted By</th>
                        <th>Creation Date</th>

                    </tr>
                    </thead>

                    <% for ( ZDTopic topic : ViewHelper.getOrderedTopics() )
                    {
                      String userName = ViewHelper.getUserName( topic.getSubmitterId() );
                      String forumName = ViewHelper.getForumName(topic.getForumId());
                    %>
                    <tr class="clickableRow" href=<%=ViewHelper.getTopicLink(topic.getId())%>>
                        <td style="word-wrap: break-word;min-width: 350px;max-width: 450px;">
                        <%=topic.getTitle()%>
                        </td>
                        <td><%= forumName %>
                        </td>
                        <td><%= topic.getTopicType() %>
                        </td>
                        <td><%= userName %>
                        </td>
                        <td><%=topic.getCreatedAt()%>
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