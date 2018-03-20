<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>

        <p>
            <!-- Display the user roles (principal.authorities is EQUAL to roles) -->
            User: <security:authentication property="principal.username" />
            <br><br>
            Roles (s): <security:authentication property="principal.authorities" />
        </p>

        <!-- Add a link to point to /leaders.. this is for managers. SHOW THIS ONLY TO MANAGERS, NOT EMPLOYEES or ADMINS -->
        <security:authorize access="hasRole('MANAGER')">
            <p>
                <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting </a>
                (Only for Managers)
            </p>
        </security:authorize>

        <!-- Add a link to point to /systems.. this is for Admins. SHOW THIS ONLY TO ADMINS, NOT MANAGERS or EMPLOYEES -->
        <security:authorize access="hasRole('ADMIN')">
            <p>
                <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting </a>
                (Only for Admins)
            </p>
        </security:authorize>

        <!-- Add a logout button -->
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>
