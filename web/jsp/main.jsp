<%-- 
    Document   : main
    Created on : 25.05.2014, 16:47:31
    Author     : Anna
--%>

<jsp:directive.page contentType="text/html; charset=UTF-8"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h3>Welcome</h3>
        <hr />
        <c:out value="${user}, hello!"/>
        <hr />
        <form name="ExitForm" method="POST" action="controller">
        <input type="hidden" name="command" value="exit" />
        <input type="submit" value="Exit">
        </form><hr/>
    </body>
</html>
