<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Edit Item</h1>
  <% 
  String prevString = (String) request.getAttribute("itemToEditString");
  String idRequest = (String) request.getAttribute("idRequest");
  %>
  <form method="POST" action="/runEditItemTwo.html">
    <input type="hidden" name="idRequest" value="<%=request.getAttribute("idRequest")%>"/>
    <input type="text" name="itemToEdit" value="<%=prevString%>" size="200"/>
    <input type="submit" value="Edit"/>
  </form>  
</div>
</body>
</html>


