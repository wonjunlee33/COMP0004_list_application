<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>List App</title>
</head>
<body>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
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
<form method="post" action="/runEditList.html">
  <input type="hidden" name="editLabel" value="<%= label %>">
  <button type="submit">Edit</button>
</form>  
<% if (request.getParameter("editList") != null) { %>
  <%
    String editLabel = request.getParameter("editLabel");
    if(editLabel != null && !editLabel.isEmpty()) {
      request.setAttribute("editLabel", editLabel);
      // edit the item from the model
    }
  %>
<% } %>
<form method="post" action="/runDeleteList.html">
  <input type="hidden" name="deleteLabel" value="<%= label %>">
  <button type="submit">Delete</button>
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
