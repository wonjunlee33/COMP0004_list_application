<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
  <jsp:include page="/meta.jsp"/>
</head>
<body>
  <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous"> -->
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