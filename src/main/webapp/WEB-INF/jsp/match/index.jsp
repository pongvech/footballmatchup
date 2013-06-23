<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12">
    <div class="pull-right">
        <a href="<spring:url value='/match/create' />" class="btn">
            Create new Match
        </a>
    </div>
    <div class="pull-left" id="match-list">
        <%@ include file="include/index_loadmore.jsp"%>
    </div>
    <div class="clearfix"></div>
    <div>
        <a href="javascript:void(0);" class="btn btn-block" onclick="loadMore();">
            Load More
        </a>
    </div>
</div>
<script type="text/javascript">
    var loadMoreStart = ${loadMoreLimit};
    var loadMoreLimit = ${loadMoreLimit};
    function loadMore() {
        $.post("<spring:url value='/match/rest/include/loadmore' />", {
            "start":loadMoreStart
        }, function(data) {
            loadMoreStart += loadMoreLimit;
            $("#match-list").append(data);
        });
    }
</script>