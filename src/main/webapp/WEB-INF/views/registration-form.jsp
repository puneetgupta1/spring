<%--
  Created by IntelliJ IDEA.
  User: pg3893
  Date: 3/20/2018
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration Form</title>

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <!-- Registration Form -->
    <form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
               modelAttribute="crmUser"
               class="form-horizontal">
        <!-- Check for registration error -->
        <c:if test="${registrationError != null}">
            <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                    ${registrationError}
            </div>
        </c:if>

        <!-- User name -->
        <form:input path="userName" placeholder="username" class="form-control" />
        <!-- Password -->
        <form:password path="password" placeholder="password" class="form-control" />
        <button type="submit" class="btn btn-primary">Register</button>
    </form:form>
</body>
</html>
