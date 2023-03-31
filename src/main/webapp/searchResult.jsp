<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="uk.ac.ucl.datastruct.Item" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>List Application</title>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <div class="main">
        <!-- display everything that is necessary -->
        <h1>Search Result</h1>
        <% 
        ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("listOfMatchingItems");
        if (items.size() != 0) { %>
            <ul>
            <% for (Item item : items) { 
                String label = item.getLabel();
                String value = item.getProperty();
                int currentID = item.getId();
                String href = "specificItem.html?id=" + currentID;
            %>
                <li><a href="<%=href%>"><%=label%> / <%=value%></a></li>
            <% } %>
            </ul>
        <% } else { %>
            <p>Nothing found! Please try again.</p>
        <% } %>
    </div>
    <jsp:include page="/footer.jsp"/>
</body>
</html>