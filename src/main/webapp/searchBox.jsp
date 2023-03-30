<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

<html>
<head>
  <title>List Application</title>
  <jsp:include page="/meta.jsp"/>
</head>
<body>
<div class="main">
  <h1>Search by <%= request.getAttribute("searchParameter") %></h1>
  <form method="POST" action="/runsearch.html">
    <input type="hidden" name="searchParameter" value="<%=request.getAttribute("searchParameter")%>"/>
    <input type="text" name="searchString" placeholder="Enter search keyword here" size="100"/>
    <input type="submit" class="btn" value="Search"/>
  </form>
</div>
</body>
</html>