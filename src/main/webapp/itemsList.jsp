<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>
<%@ page import="uk.ac.ucl.datastruct.ItemInterface" %>


<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>List App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Items:</h2>
  <!-- creates the href attribute for the hyperlinked text to the specific item -->
  <ul>
    <%
      ArrayList<ItemInterface> itemsList = (ArrayList<ItemInterface>) request.getAttribute("listOfItems");
      for (ItemInterface item : itemsList)
      {
        String label = item.getLabel();
        String value = item.getProperty();
        int currentID = item.getId();
        String href = "specificItem.html?id=" + currentID;
    %>
    <li><a href="<%=href%>"><%=label%> / <%=value%></a></li>
    <% } %>
  </ul>
  
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
