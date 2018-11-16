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

    <link rel="stylesheet" type="text/css" href="../css/style2.css">

    <title>Login Page</title>
</head>

<body>
    <div class="topdiv">
        <p class="headerp">Welcome!</p>  <!-- CHANGE THIS PER APP -->
    </div>
    <div class="maincontainer">
        <div class="mainleft">
            <p class="headerlabel">Registration</p>
            <div class="tablediv">
                <table class="formtable">
                    <tbody>
                        <form:form action="/registration" method="POST" modelAttribute="user">
                            <tr>
                                <td>
                                    <div class="formlabel fncol-1">First Name:</div>
                                </td>
                                <td>
                                    <form:input type="text" class="formtextbox fncol-2" path="first_name">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="formflash">
<!-- errors -->                         <form:errors path="first_name" />
                                    </div>
                                </td>    
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Last Name:</div>
                                </td>
                                <td>
                                    <form:input type="text" class="formtextbox fncol-2" path="last_name">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <div class="formflash">
<!-- errors -->                         <form:errors path="last_name" />
                                    </div>
                                </td>    
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Email:</div>
                                </td>
                                <td>
                                    <form:input type="text" class="formtextbox fncol-2" path="email">
                                </td>
                                <td>
                                </td>
                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>
                                        <div class="formflash">
<!-- errors                -->              <form:errors path="email" />  
                                        </div>
                                </td>                                

                            </tr>

                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Password:</div>
                                </td>
                                <td>
                                    <form:input type="password" class="formtextbox fncol-2" path="password">
                                </td>
                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>
                                    <div class="formflash">
<!-- errors                                 -->
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Confirm pw:</div>
                                </td>
                                <td>
                                    <input type="password" class="formtextbox fncol-2" name="confirmpw">
                                </td>
                                <td>
                                        <div class="formflash fncol-3"></div>
                                </td>
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1"></div>
                                </td>
                                <td>
                                    <input type="submit" class="submitbtn fncol-2" name="register" value="Register">
                                </td>
                                <td>
                                        <div class="formflash fncol-3"></div>
                                </td>
                            </tr>                    
                        </form>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="mainright">
            <p class="headerlabel">Login</p>
            <div class="tablediv">
                <table class="logintable">
                    <tbody>
                        <form action="/login_registration/process_login" method="POST">
                        {% csrf_token %}
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Email:</div>
                                </td>
                                <td>
                                    <input type="text" class="formtextbox fncol-2" name="loginemail">
                                </td>
                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>
                                    <div class="formflash">
<!-- errors     -->
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1">Password:</div>
                                </td>
                                <td>
                                    <input type="password" class="formtextbox fncol-2" name="loginpassword">
                                </td>
                                <td>
                                        <div class="formflash fncol-3"></div>
                                </td>
                            </tr>
                            <tr>
                                <td class="">
                                    <div class="formlabel fncol-1"></div>
                                </td>
                                <td>
                                    <input type="submit" class="submitbtn fncol-2" name="login" value="Login">
                                </td>
                            </tr>                    
                        </form>
                    </tbody>
                </table>
            </div>
        </div>
    </div>   
</body>

</html>