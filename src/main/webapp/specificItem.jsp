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
  <form method="post" action="EditItemServlet">
    <button type="submit" name="editItem" value="<%=request.getParameter("id")%>">Edit Item</button>
  </form>
  <form method="post" action="/runDeleteItem.html">
    <input type="hidden" name="deleteid" value="<%= id %>">
    <button type="submit">Delete</button>
  </form>  
  <% if (request.getParameter("deleteItem") != null) { %>
    <%
      String deleteid = request.getParameter("deleteid");
      if(deleteid != null && !deleteid.isEmpty()) {
        request.setAttribute("deleteid", deleteid);
        // delete the item from the model
      }
    %>
  <% } %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
