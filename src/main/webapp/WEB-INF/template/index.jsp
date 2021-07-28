<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <%@ include file="header.jsp" %>
    <body>
    	<%@ include file="nav.jsp" %>
		<main>
			<jsp:include page="../views/${viewName}/index.jsp" />
        </main>
    </body>
    <%@ include file="footer.jsp" %>
    <%@ include file="scripts.jsp" %>
</html>