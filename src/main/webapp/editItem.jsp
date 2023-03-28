<%@ page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Edit Item</h1>
  <!--get the stuff from EditItemServlet-->
  <%
  HashMap<String,String> itemToEdit = (HashMap<String,String>) request.getAttribute("itemToEdit");
  String idRequest = itemToEdit.get("id");
  int numFields = itemToEdit.size() - 3; // the initial number of lists to display
  int i = 0;

  %>
  
  <form method="POST" action="/runEditItemTwo.html">
    <input type="text" name="label" placeholder="Enter label..." size="50" value="label" required/>
    <input type="text" name="labelParameter" placeholder="Enter label parameter..." size="50" value="<%=itemToEdit.get("label")%>" required/>
    <br>
    <input type="text" name="value" placeholder="Enter value..." size="50" value="value" required/>
    <input type="text" name="valueParameter" placeholder="Enter value parameter..." size="50" value="<%=itemToEdit.get("value")%>" required/>
    <br>
    <%
    String key, value;
    for(Map.Entry<String,String> entry : itemToEdit.entrySet()) { 
        key = entry.getKey();
        value = entry.getValue();
        if (key == "label" || key == "value" || key == "id") {
          continue;
        }
    %>
    <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50" value="<%=key%>"/>
    <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value="<%=value%>"/>
    <br>
    <% 
    i++;    
    } 
    %>

    <input type="text" name="parameterKey<%=i + 1%>" placeholder="Enter Parameter Name <%=i + 1%>..." size="50" value=""/>
    <input type="text" name="parameterValue<%=i + 1%>" placeholder="Enter Parameter Value <%=i + 1%>..." size="50" value=""/>

    <br> 
    <br>
    <input type="hidden" name="numFields" value="<%=numFields%>"/>
    <input type="hidden" name="idRequest" value="<%=idRequest%>"/>
    <input type="submit" value="Edit"/>
  </form>

</div>

<p>Remember: All items must have a label and value parameter!</p>
<p>Also: (for now) please make sure to pre load in the number of fields required!</p>
<jsp:include page="/footer.jsp"/>
</body>
</html>