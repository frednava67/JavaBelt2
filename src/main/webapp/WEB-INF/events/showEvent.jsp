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

    <title>${event.name}</title>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <h1>${event.name}</h1>
            </div>
            <div class="col-xl-1 col-lg-1 col-med-1">
                <a class="btn btn-link float-right" href="/events">Home</a>
            </div>
        </div>
        <br>
        <div class="row">		
        	<div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-4 col-lg-4 col-med-4">
            	<br>
	            <label>Host: ${event.host.first_name} ${event.host.last_name}</label><br>
	            <label>Date: ${event.eventDate}</label><br>
				<label>Location: ${event.location}, ${event.state}</label><br>
	            <label>People who are attending this event: ${event.users.size()}</label><br><br>
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <th>Name</th>
                        <th>Location</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${event.users}" var="attendee">
                            <tr>
                                <td>${attendee.first_name} ${attendee.last_name}</td>
                                <td>${attendee.location}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>	            
            </div>
        	<div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-4 col-lg-4 col-med-4">
            	<br>
                <h2>Message Wall</h2>
                <br>
                <div class="col-xl-12 col-lg-12 col-med-12 messagesdiv border border-dark">
                    <c:forEach items="${messages}" var="message">
                        </p>
                            <c:out value="${message.messager.first_name} says ${message.content}"/>
                            <hr>
                        <p>
                    </c:forEach>                    
                </div>
                <br>
                <br>
                <h5>Add Comment:</h5>
                <form:form action="/messages/process" modelAttribute="message" method="POST">
                    <form:input type="text" path="content" class="form-control newmessagetextbox"/>
                    <form:errors path="content"></form:errors>
                    <input type="hidden" name="eventid" value="${event.id}">
                    <br>
                    <input type="submit" value="Submit" class="float-right"/>
                </form:form>
            </div>
        	<div class="col-xl-1 col-lg-1 col-med-1"></div>
        </div>
    </div>
</body>
</html>