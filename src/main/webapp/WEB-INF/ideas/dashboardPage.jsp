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

    <title>Ideas</title>
</head>

<body>
    <div class="container-fluid">
    	<br>
    	<br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <h1>Welcome, ${user.name}</h1>
            </div>
            <div class="col-xl-1 col-lg-1 col-med-1">
                <a class="btn btn-link float-right" href="/logout">Logout</a>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <th>Ideas</th>
                        <th>Created By</th>
                        <th>Likes</th>
                        <th>Action</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${ideas}" var="idea">
                            <tr>
                                <td><a href='/ideas/${idea.id}'>${idea.content}</a></td>
								<td>${idea.creator.name}</td>
                                <td>${idea.likers.size()}</td>
                                <td>
	                                <c:if test="${!idea.likers.contains(user)}">
	                                    <a href="/ideas/${idea.id}/like/${user.id}">Like</a>
	                                </c:if>
	                                <c:if test="${idea.likers.contains(user)}">
	                                    <a href="/ideas/${idea.id}/unlike/${user.id}">Unlike</a>
	                                </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">        
        		<a class='btn btn-primary' href='/ideas/new'>Create an idea</a>
        	</div>   
        </div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    </div>
</body>

</html>