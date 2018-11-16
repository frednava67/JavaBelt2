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
				<h1>${idea.content}</h1>
            </div>
        </div>  
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-2 col-lg-2 col-med-2">
				<h5>Created By:</h5>
            </div>
            <div class="col-xl-7 col-lg-7 col-med-7">
				<h5>${idea.creator.name}</h5>
            </div>
        </div>
        <br>
        <br>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
				<h2>Users who liked your idea:</h2>
            </div>        
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <th>Name</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${idea.likers}" var="liker">
                            <tr>
								<td>${liker.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-1 col-lg-1 col-med-1"></div>
            <div class="col-xl-9 col-lg-9 col-med-9">
            	<a class='btn btn-primary' href='/ideas/${idea.id}/edit'>Edit</a>
            </div>  
        </div>      
    </div>
</body>
</html>