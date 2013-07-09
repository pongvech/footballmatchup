<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="span12"><a href="<spring:url value='/match/create' />" class="btn btn-success"><i class="icon-plus icon-white"></i> Create new Match </a></div>
<div class="span12">&nbsp;</div>
<div class="span12">
    <ul class="thumbnails" id="match-list">
        <%@ include file="include/home_loadmore.jsp"%>
    </ul>
</div>
<c:if test="${countMatch > loadMoreLimit}">
    <div class="span12">
        <div class="loadmore">
            <a href="javascript:void(0);" class="btn btn-block" onclick="loadMore();">
                Load More
            </a>
        </div>
    </div>
</c:if>
<script type="text/javascript">
    var countMatch = ${countMatch};
    var loadMoreStart = ${loadMoreLimit};
    var loadMoreLimit = ${loadMoreLimit};
    function loadMore() {
        $.post("<spring:url value='/match/rest/include/loadmore' />", {
            "start":loadMoreStart
        }, function(data) {
            console.log(data);
            loadMoreStart += loadMoreLimit;
            $("#match-list").append(data);
            if(loadMoreStart > countMatch) {
                $(".loadmore").hide();
            }
        });
    }
</script>