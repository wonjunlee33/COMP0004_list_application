<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Edit List</h1>
  <% 
  String prevLabel = (String) request.getAttribute("prevLabel");
  %>
  <form method="POST" action="/runEditListTwo.html">
    <input type="hidden" name="prevLabel" value="<%=request.getAttribute("prevLabel")%>"/>
    <input type="text" name="editedLabel" value="<%=prevLabel%>" size="200"/>
    <input type="submit" value="Edit"/>
  </form>  
</div>
</body>
</html>