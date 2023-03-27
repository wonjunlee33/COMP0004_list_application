<%@ page language="java" %>
<%@ page import="java.util.*" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
<div class="main">
  <h1>Add Item</h1>
  <%int numFields = 0; // the initial number of lists to display%>
  
  <form method="POST" action="/runAddItem.html">
    <input type="text" name="label" placeholder="Enter label..." size="50" value="label" required/>
    <input type="text" name="labelParameter" placeholder="Enter label parameter..." size="50" value="" required/>
    <br>
    <input type="text" name="value" placeholder="Enter value..." size="50" value="value" required/>
    <input type="text" name="valueParameter" placeholder="Enter value parameter..." size="50" value="" required/>
    <br>
    <% 
    if(request.getParameter("numFields") != null) {
        numFields = Integer.parseInt(request.getParameter("numFields"));
    }
    for(int i=0; i<numFields; i++) { %>
        <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50"/>
        <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value=""/>
        <br>
    <% } %>
  
    <p></p>
    <input type="hidden" name="numFields" value="<%=numFields%>"/>
    <input type="submit" value="Add"/>
  </form>

  
  <form method="POST" action="/addItem.jsp">
    <input type="hidden" name="numFields" value="<%=numFields+1%>"/>
    <input type="submit" value="Add more information..."/>
  </form>

  <%
  if (numFields > 0) {
  %>
  <form method="POST" action="/addItem.jsp">
    <input type="hidden" name="numFields" value="<%=numFields-1%>"/>
    <input type="submit" value="Delete last field..."/>
  </form>
<% } %>
</div>

<p>Remember: All items must have a label and value parameter!</p>
<p>Also: (for now) please make sure to pre load in the number of fields required!</p>
<jsp:include page="/footer.jsp"/>
</body>
</html>