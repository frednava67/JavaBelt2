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

    <title>Login Page</title>
</head>

<body>
	<br>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-6">
                <fieldset>
                    <legend style="width=auto;" >Registration</legend>
                    <form:form method="POST" action="/registration" modelAttribute="user">
                        <p>
                            <form:label path="name">Name</form:label>
                            <form:input type="text" path="name" />
                            <form:errors style="color: red;" path="name"/> 
                        </p>
                        <p>
                            <form:label path="email">Email:</form:label>
                            <form:input type="email" path="email" />
                            <form:errors style="color: red;" path="email" />   
                        </p>
                        <p>
                            <form:label path="password">Password:</form:label>
                            <form:password path="password" />
                            <form:errors style="color: red;" path="password" />     
                        </p>
                        <p>
                            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
                            <form:password path="passwordConfirmation" />
                        </p>
                        <input type="submit" value="Register!" />
                    </form:form>
                </fieldset>
            </div>
            <div class="col-xl-6">
                <fieldset>
                    <legend style="width=auto;">Login</legend>
                    <p>
                        <c:out value="${error}" />
                    </p>
                    <form method="post" action="/login">
                        <p>
                            <label type="email" for="email">Email</label>
                            <input type="text" id="email" name="email" />
                        </p>
                        <p>
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" />
                        </p>
                        <input type="submit" value="Login" />
                    </form>
                </fieldset>
            </div>
        </div>
    </div>
</body>

</html>