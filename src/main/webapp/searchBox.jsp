<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Search <%= request.getAttribute("searchParameter") %></h1>
  <form method="POST" action="/runsearch.html">
    <input type="hidden" name="searchParameter" value="<%=request.getAttribute("searchParameter")%>"/>
    <input type="text" name="searchString" placeholder="Enter search keyword here"/>
    <input type="submit" value="Search"/>
  </form>
</div>
</body>
</html>