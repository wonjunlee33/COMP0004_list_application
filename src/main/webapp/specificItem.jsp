<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Showing List</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Item Details:</h2>
  <ul>
    <%
      // Retrieve the ID parameter from the URL
      String idStr = request.getParameter("id");
      int id = Integer.parseInt(idStr);
      
      // Retrieve the selected HashMap from the list of items
      ArrayList<HashMap<String,String>> items = (ArrayList<HashMap<String,String>>) request.getAttribute("listOfItems");
      HashMap<String,String> itemToDisplay = items.get(id);
  
      // Loop over all key-value pairs in the HashMap and print them out
      for (Map.Entry<String, String> entry : itemToDisplay.entrySet())
      {
        String key = entry.getKey();
        String value = entry.getValue();
    %>
      <li><%=key%>: <%=value%></li>
    <% } %>
  </ul>  
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>