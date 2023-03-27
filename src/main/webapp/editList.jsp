<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
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