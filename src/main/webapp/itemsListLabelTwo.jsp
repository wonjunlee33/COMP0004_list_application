<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

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
      ArrayList<Item> itemsList = (ArrayList<Item>) request.getAttribute("listOfItems");
      String label = (itemsList.get(0)).getLabel();
      for (Item item : itemsList)
      {
        int currentID = item.getId();
        String value = item.getProperty();
        String href = "specificItem.html?id=" + currentID;
    %>
    <li><a href="<%=href%>"><%=value%></a></li>
    <% } %>
  </ul>
</div>
<form method="post" action="/runEditList.html">
  <input type="hidden" name="editLabel" value="<%= label %>">
  <button type="submit" class="btn">Edit</button>
</form>  
<% if (request.getParameter("editList") != null) { %>
  <%
    String editLabel = request.getParameter("editLabel");
    if(editLabel != null && !editLabel.isEmpty()) {
      request.setAttribute("editLabel", editLabel);
      // edit the item
    }
  %>
<% } %>
<form method="post" action="/runDeleteList.html">
  <input type="hidden" name="deleteLabel" value="<%= label %>">
  <button type="submit" class="btn">Delete</button>
</form>  
<% if (request.getParameter("deleteList") != null) { %>
  <%
    String deleteLabel = request.getParameter("deleteLabel");
    if(deleteLabel != null && !deleteLabel.isEmpty()) {
      request.setAttribute("deleteLabel", deleteLabel);
      // delete the item from the model
    }
  %>
<% } %>
<p>Want to reference this list? Just use the label name after 'list': <%=label%></p>
<jsp:include page="/footer.jsp"/>
</body>
</html>
