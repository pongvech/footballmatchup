<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12 matchcard-needlogin matchcard">
    <div class="pull-right">
        <c:if test="${playerMatch == null}">
            <span>
                <a href="<spring:url value='/match/join/${match.id}' />" class="btn">
                    Join
                </a>
            </span>
        </c:if>
        <c:if test="${match.creator.id == loggedInPlayer.id}">
            <a href="<spring:url value='/match/edit/${match.id}' />" class="btn">Edit</a>
        </c:if>
    </div>
    <div class="pull-left">
        <h2>${match.name}</h2>
        <div>
            Creator : ${match.creator.username}
        </div>
        <c:if test="${match.detail != null}">
            <div>
                Detail : ${match.detail}
            </div>
        </c:if>
        <div>
            Where : ${match.place}
        </div>
        <div>
            When : <joda:format value="${match.playTime}" pattern="${formatDateTime}" />
        </div>
        <h3>Who join</h3>
        <div class="row">
            <div class="span4">
                Team A
                <ul class="drop"></ul>
            </div>
            <div class="span4">
                <ul class="drop">
                    <c:forEach items="${joinedPlayerDisplayList}" var="joinedPlayer">
                        <li class="playercard">
                                ${joinedPlayer.player.username}
                            <span class="star" id="${joinedPlayer.player.id}_${joinedPlayer.match.id}_${joinedPlayer.playerRating.id}" data-score="${joinedPlayer.playerRating.rating}"></span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="span4">
                Team B
                <ul class="drop"></ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<spring:url value='/assets/js/raty/jquery.raty.min.js' />"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.star').raty({
            path: '<spring:url value='/assets/js/raty/img/' />',
            click: function(score, evt) {
                var splitStarId = $(this).attr('id').split("_");
                var playerId = splitStarId[0];
                var matchId = splitStarId[1];
                var playerRatingId = splitStarId[2];
                $.post("<spring:url value='/match/rest/giverating' />", {
                    "score": score,
                    "playerId": playerId,
                    "matchId": matchId,
                    "playerRatingId": playerRatingId
                }, function(data) {
                    // First time loggedInPlayer give rating, joinedPlayer.playerRating.id is "".
                    // When loggedInPlayer change rating it will save a new document to db.
                    if(playerRatingId == "") {
                        $("#" + playerId + "_" + matchId + "_").attr("id", $("#" +playerId + "_" + matchId + "_").attr("id") + data.id);
                    }
                });
            },
            score: function() {
                return $(this).attr('data-score');
            }
        });
        $(".drop").sortable({
            connectWith: ".drop"
        }).disableSelection();
    });
</script>
