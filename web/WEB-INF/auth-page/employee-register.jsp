<%-- 
    Document   : login
    Created on : Aug 16, 2016, 10:22:36 PM
    Author     : cruzsyd
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="com.tsppi.bean.JobTypeBean"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/form.css" rel="stylesheet" type="text/css">
        <link href="css/form-error-validation.css" rel="stylesheet" type="text/css">
    </head>
    <body>
	<%@include file="../static-page/navbar.jsp" %>
	<header id="login-header" style="background-image: url(img/login-register-header.png); background-size: cover;">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1>My Account</h1>
                    </div>
                </div>
            </div>
	</header>
	<section>
            <div id="login-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3">
                            <div class="panel panel-login">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <a class="active" id="login-form-link">Register</a>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <form id="register-form" action="registerservlet" method="post" autocomplete="off">
                                                <div class="form-group">
                                                    <input type="text" name="username" id="username" class="form-control" placeholder="Username" required>
                                                </div>
                                                <div class="form-group">
                                                    <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                                                </div>
                                                <div class="form-group">
                                                    <input type="password" name="confirm_password" id="confirm_password" class="form-control" placeholder="Confirm Password" required>
                                                </div> 
                                                <div class="form-group">
                                                        <input type="text" name="first_name" id="first_name" class="form-control" placeholder="First Name" required>
                                                </div>
                                                <div class="form-group">
                                                    <input type="text" name="last_name" id="last_name" class="form-control" placeholder="Last Name" required>
                                                </div>
                                                <div class="form-group">
                                                    <input type="email" name="email" id="email" class="form-control" placeholder="Email Address" required>
                                                </div>
                                                <div class="form-group text-center">
                                                    <label>Job Position: </label>
                                                    <select name="job_type">
                                                        <c:forEach var="jt" items="${jt}">
                                                            <option value="${jt.getJobID()}">${jt.getJobType()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>  
                                                <div class="form-group">
                                                    <div class="row">
                                                        <div class="col-sm-6 col-sm-offset-3">
                                                            <input type="hidden" name="type_of_account" id="form_identifier" value="1">
                                                            <input type="hidden" name="form_identifier" id="form_identifier" value="Employee">
                                                            <input type="submit" name="submit" id="register-submit" class="form-control btn btn-register" value="Register Now">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>                 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	</section>
        
        <script src="imports/jquery.validate.js" type="text/javascript"></script>
        <script src="js/reg-form-validation.js" type="text/javascript"></script>
    </body>
</html>
