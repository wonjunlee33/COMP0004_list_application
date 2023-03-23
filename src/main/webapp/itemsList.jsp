<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>List App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Items:</h2>
  <ul>
    <%
      ArrayList<HashMap<String,String>> itemsList = (ArrayList<HashMap<String,String>>) request.getAttribute("listOfItems");
      for (HashMap<String,String> item : itemsList)
      {
        String label = item.get("label");
        String value = item.get("value");
        int currentID = Integer.parseInt(item.get("id"));
        String href = "specificItem.html?id=" + currentID;
    %>
    <li><a href="<%=href%>"><%=label%> / <%=value%></a></li>
    <% } %>
  </ul>
  
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
