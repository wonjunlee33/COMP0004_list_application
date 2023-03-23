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
      String label = (itemsList.get(0)).get("label");
      for (HashMap<String,String> item : itemsList)
      {
        int currentID = Integer.parseInt(item.get("id"));
        String value = item.get("value");
        String href = "specificItem.html?id=" + currentID;
    %>
    <li><a href="<%=href%>"><%=value%></a></li>
    <% } %>
  </ul>
</div>
<p>Want to reference this list? Just use the following link:</p>
<p>/specificItemLabel.html?label=<%=label%></p>
<jsp:include page="/footer.jsp"/>
</body>
</html>
