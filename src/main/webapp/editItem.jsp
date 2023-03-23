<html>
<head>
  <title>List Application</title>
</head>
<body>
<div class="main">
  <h1>Edit Item</h1>
  <% 
  String prevString = (String) request.getAttribute("itemToEditString");
  %>
  <form method="POST" action="/runEditItemTwo.html">
    <input type="text" name="itemToEdit" value="<%=prevString%>" size="200"/>
    <input type="submit" value="Edit"/>
  </form>
</div>
<p>Word of advice: If you want to cancel the edit, just press Edit instead of using the Browser back button!</p>
</body>
</html>


