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
      List<String> values = (List<String>) request.getAttribute("listOfItems");
      List<Integer> index = (List<Integer>) request.getAttribute("index");
      for (int i = 0; i < values.size(); i++)
      {
        String item = values.get(i);
        int id = index.get(i);
        String href = "specificItem.html?id=" + id;
    %>
    <li><a href="<%=href%>"><%=item%></a></li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
