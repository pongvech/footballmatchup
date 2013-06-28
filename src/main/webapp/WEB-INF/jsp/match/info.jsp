<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12 matchcard-needlogin matchcard">

    <!-- Top button section -->
    <div class="row">
        <div class="span12">&nbsp;</div>
        <div class="span12">
            <c:if test="${playerMatch == null && !past}">
                <a href="<spring:url value='/match/join/${match.id}' />" class="btn">
                    Join
                </a>
            </c:if>
            <c:if test="${match.creator.id == loggedInPlayer.id}">
                <a href="<spring:url value='/match/edit/${match.id}' />" class="btn">
                    Edit
                </a>
            </c:if>
        </div>
    </div>

    <!-- Match Info section -->
    <div class="row">
        <div class="span12">
            <h2>
                ${match.name}
                <c:if test="${creator == true}">
                    <span class="label label-important matchcard_playercount">Creator</span>
                </c:if>
                <c:if test="${not empty playerMatch}">
                    <span class="label label-success matchcard_playercount">Joined</span>
                </c:if>
            </h2>

            <div>
                Creator : ${match.creator.username}
            </div>
            <c:if test="${match.detail != null}">
                <div>
                    Description : ${match.detail}
                </div>
            </c:if>
            <div>
                Date/Time : <joda:format value="${match.playTime}" pattern="${formatDateTime}" />
            </div>
            <div>
                Venue : ${match.place}
            </div>

        </div>
    </div>

    <!-- Spacer -->
            <div class="row">
                <div class="span12"> &nbsp; </div>
    </div>

    <!-- Player Column Header -->
    <div class="row">
        <div class="span4 pagination-centered"><h3>Team A (${match.teamAScore})</h3></div>
        <div class="span4 pagination-centered">
            <a href="#" class="btn btn-danger matchup_button">
                MatchUp!
            </a>
        </div>
        <div class="span4 pagination-centered"><h3>(${match.teamBScore}) Team B</h3></div>
    </div>

    <!-- Spacer -->
    <div class="row">
        <div class="span12"> &nbsp; </div>
    </div>

     <!-- Player Section -->
     <div class="row">
        <div class="span4 teambox">
            &nbsp;
        </div>
        <div class="span4 teambox">
            <c:forEach items="${joinedPlayerDisplayList}" var="joinedPlayer">
                <div class="playercard bg_lightblue pagination-centered">
                        ${joinedPlayer.player.username}
                    <span class="star" id="${joinedPlayer.player.id}_${joinedPlayer.match.id}_${joinedPlayer.playerRating.id}" data-score="${joinedPlayer.playerRating.rating}"></span>
                </div>
            </c:forEach>
        </div>
        <div class="span4 teambox">
            &nbsp;
        </div>
    </div>

    <!-- Spacer -->
    <div class="row">
        <div class="span12"> &nbsp; </div>
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
        $(".teambox").sortable({
            connectWith: ".teambox"
        }).disableSelection();
    });
</script>
