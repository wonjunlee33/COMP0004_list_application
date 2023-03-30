<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>


<html>
<head>
  <jsp:include page="/meta.jsp" />
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Add Item</h1>
  <%int numFields = 0; // the initial number of lists to display%>
  
  <!-- creates boxes for label and value, these are needed for every item -->
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
      <!-- logic to make more fields if necessary -->
        <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50"/>
        <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value=""/>
        <br>
    <% } %>
  
    <p></p>
    <input type="hidden" name="numFields" value="<%=numFields%>"/>
    <input type="submit" class="btn" value="Add"/>
  </form>
  
  <form method="POST" action="/addItem.jsp">
    <input type="hidden" name="numFields" value="<%=numFields+1%>"/>
    <input type="submit" class="btn" value="Add more information..."/>
  </form>

  <%
  if (numFields > 0) {
  %>
  <!-- only display this field if there are optional parameters, since need labels and values -->
  <form method="POST" action="/addItem.jsp">
    <input type="hidden" name="numFields" value="<%=numFields-1%>"/>
    <input type="submit" class="btn" value="Delete last field..."/>
  </form>
<% } %>
</div>

<p>Remember: All items must have a label and value parameter!</p>
<p>Also, please make sure to pre-load in the number of fields required!</p>
<p>Empty fields will not be added, so they can safely be put in if necessary.</p>

<jsp:include page="/footer.jsp"/>
</body>
</html>