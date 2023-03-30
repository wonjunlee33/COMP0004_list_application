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
  <h2>Labels:</h2>
  <!-- generates a list of hyperlinked text to the various labels -->
  <ul>
    <%
      List<String> items = (List<String>) request.getAttribute("listOfItems");
      for (int i = 0; i < items.size(); i++)
      {
        String item = items.get(i);
        String href = "specificItemLabel.html?label=" + item;
    %>
    <li><a href="<%=href%>"><%=item%></a></li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
