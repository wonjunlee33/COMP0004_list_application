<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

<html>
<head>
  <title>List Application</title>
  <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="main">
  <h1>Edit List</h1>
  <% 
  String prevLabel = (String) request.getAttribute("prevLabel");
  %>
  <form method="POST" action="/runEditListTwo.html">
    <input type="hidden" name="prevLabel" value="<%=request.getAttribute("prevLabel")%>"/>
    <input type="text" name="editedLabel" value="<%=prevLabel%>" size="100"/>
    <input type="submit" class="btn" value="Edit"/>
  </form>  
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>