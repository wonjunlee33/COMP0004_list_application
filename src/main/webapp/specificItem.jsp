<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

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
                // display everything of the id that is passed in
                String idStr = request.getParameter("id");
                int id = Integer.parseInt(idStr);
                ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("listOfItems");
                Item itemToDisplay = null;
                for (Item it : items) {
                    if (it.getId() == id) {
                        itemToDisplay = it;
                        break;
                    }
                }
            %>
                <h3>>> <%=itemToDisplay.getLabel()%> >> <%=itemToDisplay.getProperty()%></h3>
            <%
                // check for keywords (url, image, item, list), and do not display id param
                for (Map.Entry<String, String> entry : itemToDisplay.getOtherParameters().entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.equalsIgnoreCase("url")) {
            %>
                        <li><%=key%>: <a href="<%=value%>"><%=value%></a></li>
                    <% 
                    } else if (key.equalsIgnoreCase("item")) { 
                        String specificValue = value; // assuming the value parameter is stored in a local variable called value
                        Item specificItem = null;

                        // iterate over the ArrayList to find the specific item
                        for (Item item : items) {
                            if (item.getId() == Integer.parseInt(specificValue)) {
                                specificItem = item;
                                break;
                            }
                        }
                    %>
                        <% if (specificItem != null) { %>
                            <li>
                              <%=key%>: <a href="/specificItem.html?id=<%=value%>"><%= specificItem.getLabel() %> / <%= specificItem.getProperty() %></a>
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
            <button class="btn" type="submit">Edit</button>
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
            <button class="btn" type="submit">Delete</button>
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