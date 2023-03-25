<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp" />
    <title>Showing List</title>
</head>
<body>
    <jsp:include page="/header.jsp" />
    <div class="main">
        <h2>Item Details:</h2>
        <ul>
            <%  
                String idStr = request.getParameter("id");
                int id = Integer.parseInt(idStr);
                ArrayList<HashMap<String,String>> items = (ArrayList<HashMap<String,String>>) request.getAttribute("listOfItems");
                HashMap<String,String> itemToDisplay = items.get(0);
                for (HashMap<String,String> it : items) {
                    if (it.get("id").compareTo(idStr) == 0) {
                        itemToDisplay = it;
                        break;
                    }
                }
                for (Map.Entry<String, String> entry : itemToDisplay.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equalsIgnoreCase("url")) {
            %>
                        <li><%=key%>: <a href="<%=value%>"><%=value%></a></li>
                    <% 
                    } else if (key.equalsIgnoreCase("item")) { 
                        String specificValue = value; // assuming the value parameter is stored in a local variable called value
                        HashMap<String, String> specificItem = null;

                        // iterate over the ArrayList to find the specific item
                        for (HashMap<String, String> item : items) {
                            if (item.get("id").equals(specificValue)) {
                                specificItem = item;
                                break;
                            }
                        }
                    %>
                        <% if (specificItem != null) { %>
                            <li>
                              <%=key%>: <a href="/specificItem.html?id=<%=value%>"><%= specificItem.get("label") %> / <%= specificItem.get("value") %></a>
                            </li>
                        <% } else { %>
                            <li>Item not found! Please re-enter.</li>
                        <% } %>
                    <% 
                    } else if (key.equalsIgnoreCase("list")) { 
                    %>
                        <li><%=key%>: <a href="/specificItemLabel.html?label=<%=value%>"><%=value%></a></li>
                    <% 
                    } else if (key.equalsIgnoreCase("image") || key.equalsIgnoreCase("img")) { 
                    %>
                        <p><img src="<%=value%>"></p>
                    <% 
                    } else if (key.equalsIgnoreCase("id")) { 
                    %>

                    <% 
                    } else { 
                    %>
                        <li><%=key%>: <%=value%></li>
                    <% 
                    } 
                } 
            %>
        </ul>
        <form method="post" action="/runEditItem.html">
            <input type="hidden" name="editid" value="<%= id %>">
            <button type="submit">Edit</button>
        </form>  
        <% if (request.getParameter("editItem") != null) { %>
            <%
                String editid = request.getParameter("editid");
                if(editid != null && !editid.isEmpty()) {
                    request.setAttribute("editid", editid);
                    // edit the item from the model
                }
            %>
        <% } %>
        <form method="post" action="/runDeleteItem.html">
            <input type="hidden" name="deleteid" value="<%= id %>">
            <button type="submit">Delete</button>
        </form>  
        <% if (request.getParameter("deleteItem") != null) { %>
          <%
              String deleteid = request.getParameter("deleteid");
              if (deleteid != null && !deleteid.isEmpty()) {
                  request.setAttribute("deleteid", deleteid);
                  // delete the item from the model
              }
          %>
      <% } %>  
    </div>
    <p>Want to reference this item? Just reference the ID after putting 'item': <%=id%></p>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>