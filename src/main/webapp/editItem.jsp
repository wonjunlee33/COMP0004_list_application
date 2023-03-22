<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Edit Item</h1>
  <form method="post" action="/runEditItemTwo.html">
    <input type="hidden" name="editid" value="<%= id %>">
    <button type="submit">Edit</button>
  </form>  
  <% if (request.getParameter("editItem") != null) { %>
    <%
      String editid = request.getParameter("editid");
      if(editid != null && !editid.isEmpty()) {
        request.setAttribute("editid", editid); 
      }
    %>
  <% } %>
</div>
</body>
</html>


