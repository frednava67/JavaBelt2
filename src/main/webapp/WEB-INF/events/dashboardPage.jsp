<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../css/style.css">

    <title>Events</title>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <h1>Welcome, ${user.first_name}</h1>
            </div>
            <div class="col-xl-1 col-lg-1 col-med-1">
                <a class="btn btn-link float-right" href="/logout">Logout</a>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <p>Here are some of the events in your state:</p>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Host</th>
                        <th>Action / Status</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${inStateEvents}" var="event">
                            <tr>
                                <td><a href='/events/${event.id}'>${event.name}</a></td>
                                <td>${event.eventDate}</td>
                                <td>${event.location}</td>
                                <td>${event.host.first_name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user != event.getHost()}">
                                            <c:if test="${!event.users.contains(user)}">
                                                <a href="/events/${event.id}/join/${user.id}">Join</a>
                                            </c:if>
                                            <c:if test="${event.users.contains(user)}">
                                                Joining | <a href="/events/${event.id}/unjoin/${user.id}">Cancel</a>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${user == event.getHost()}">
                                            <c:if test="${!event.users.contains(user)}">
                                                <a href="/events/${event.id}/edit">Edit</a> | <a href="/events/${event.id}/delete">Delete</a>
                                            </c:if>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <p>Here are some of the events in other states:</p>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Host</th>
                        <th>Action / Status</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${outOfStateEvents}" var="event">
                            <tr>
                                <td><a href='/events/${event.id}'>${event.name}</a></td>
                                <td>${event.eventDate}</td>
                                <td>${event.location}</td>
                                <td>${event.host.first_name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user != event.getHost()}">
                                            <c:if test="${!event.users.contains(user)}">
                                                <a href="/events/${event.id}/join/${user.id}">Join</a>
                                            </c:if>
                                            <c:if test="${event.users.contains(user)}">
                                                Joining | <a href="/events/${event.id}/unjoin/${user.id}">Cancel</a>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${user == event.getHost()}">
                                            <c:if test="${!event.users.contains(user)}">
                                                <a href="/events/${event.id}/edit">Edit</a> | <a href="/events/${event.id}/delete">Delete</a>
                                            </c:if>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">

        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <h3>Create an Event</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <form:form method="POST" action="/events/process" modelAttribute="event">
                    <p>
                        <form:label path="name">Name</form:label>
                        <form:input type="text" path="name" />
                        <form:errors style="color: red;" path="name" />
                    </p>
                    <p>
                        <form:label path="eventDate">Date</form:label>
                        <form:input type="date" path="eventDate" />
                        <form:errors style="color: red;" path="eventDate" />
                    </p>
                    <p>
                        <form:label path="location">Location:</form:label>
                        <form:input type="text" path="location" />
                        <form:select path="state">
                            <form:option value="AL">AL</form:option>
                            <form:option value="AK">AK</form:option>
                            <form:option value="AZ">AZ</form:option>
                            <form:option value="AR">AR</form:option>
                            <form:option value="CA">CA</form:option>
                            <form:option value="CO">CO</form:option>
                            <form:option value="CT">CT</form:option>
                            <form:option value="DE">DE</form:option>
                            <form:option value="DC">DC</form:option>
                            <form:option value="FL">FL</form:option>
                            <form:option value="GA">GA</form:option>
                            <form:option value="HI">HI</form:option>
                            <form:option value="ID">ID</form:option>
                            <form:option value="IL">IL</form:option>
                            <form:option value="IN">IN</form:option>
                            <form:option value="IA">IA</form:option>
                            <form:option value="KS">KS</form:option>
                            <form:option value="KY">KY</form:option>
                            <form:option value="LA">LA</form:option>
                            <form:option value="ME">ME</form:option>
                            <form:option value="MD">MD</form:option>
                            <form:option value="MA">MA</form:option>
                            <form:option value="MI">MI</form:option>
                            <form:option value="MN">MN</form:option>
                            <form:option value="MS">MS</form:option>
                            <form:option value="MO">MO</form:option>
                            <form:option value="MT">MT</form:option>
                            <form:option value="NE">NE</form:option>
                            <form:option value="NV">NV</form:option>
                            <form:option value="NH">NH</form:option>
                            <form:option value="NJ">NJ</form:option>
                            <form:option value="NM">NM</form:option>
                            <form:option value="NY">NY</form:option>
                            <form:option value="NC">NC</form:option>
                            <form:option value="ND">ND</form:option>
                            <form:option value="OH">OH</form:option>
                            <form:option value="OK">OK</form:option>
                            <form:option value="OR">OR</form:option>
                            <form:option value="PA">PA</form:option>
                            <form:option value="RI">RI</form:option>
                            <form:option value="SC">SC</form:option>
                            <form:option value="SD">SD</form:option>
                            <form:option value="TN">TN</form:option>
                            <form:option value="TX">TX</form:option>
                            <form:option value="UT">UT</form:option>
                            <form:option value="VT">VT</form:option>
                            <form:option value="VA">VA</form:option>
                            <form:option value="WA">WA</form:option>
                            <form:option value="WV">WV</form:option>
                            <form:option value="WI">WI</form:option>
                            <form:option value="WY">WY</form:option>
                        </form:select>
                        <form:errors style="color: red;" path="location" />
                        <form:errors style="color: red;" path="state" />
                    </p>
                    <input type="hidden" name="hostid" value="${user.id}" />
                    <input type="submit" value="Create Event" />
                </form:form>
            </div>
        </div>
    </div>
</body>

</html>