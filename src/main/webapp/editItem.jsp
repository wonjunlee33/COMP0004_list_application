<%@ page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

<html>
<head>
  <title>List Application</title>
  <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="main">
  <h1>Edit Item</h1>
  <!--get the stuff from EditItemServlet-->
  <%
  Item itemToEdit = (Item) request.getAttribute("itemToEdit");
  int idRequest = itemToEdit.getId();
  int numFields = itemToEdit.getOtherParameters().size(); // the initial number of lists to display
  int i = 0;
  %>
  
  <!-- edit label and value params first -->
  <form method="POST" action="/runEditItemTwo.html">
    <input type="text" name="label" placeholder="Enter label..." size="50" value="label" required/>
    <input type="text" name="labelParameter" placeholder="Enter label parameter..." size="50" value="<%=itemToEdit.getLabel()%>" required/>
    <br>
    <input type="text" name="value" placeholder="Enter value..." size="50" value="value" required/>
    <input type="text" name="valueParameter" placeholder="Enter value parameter..." size="50" value="<%=itemToEdit.getProperty()%>" required/>
    <br>
    <%
    String key, value;
    for(Map.Entry<String,String> entry : itemToEdit.getOtherParameters().entrySet()) { 
        key = entry.getKey();
        value = entry.getValue();
        if (key == "label" || key == "value" || key == "id") {
          continue;
        }
    %>
    <!-- now edit the rest of the hashmap params -->
    <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50" value="<%=key%>"/>
    <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value="<%=value%>"/>
    <br>
    <% 
    i++;    
    } 
    %>

    <!-- one more field to add more items -->
    <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50" value=""/>
    <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value=""/>

    <br> 
    <br>
    <input type="hidden" name="numFields" value="<%=numFields%>"/>
    <input type="hidden" name="idRequest" value="<%=idRequest%>"/>
    <input type="submit" class="btn" value="Edit"/>
  </form>

</div>

<p>Remember: All items must have a label and value parameter!</p>
<jsp:include page="/footer.jsp"/>
</body>
</html>