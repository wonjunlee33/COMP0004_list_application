package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/runEditListTwo.html")
public class EditListTwoServlet extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    ArrayList<HashMap<String,String>> fullItemList = model.getItems();

    // extracting the data from the text box
    String editedLabel = request.getParameter("editedLabel");
    String prevLabel = request.getParameter("prevLabel");

    // change the hashmaps accordingly
    ArrayList<Integer> idToEdit = model.getItemsIDFromLabel(prevLabel);
    ArrayList<HashMap<String,String>> itemsToEdit = new ArrayList<>();
    for (int id : idToEdit) {
        itemsToEdit.add(model.getHashMapFromId(id));
        model.deleteItem(id);
    }

    // iterate through the itemsToEdit and change the value of key "label"
    for (HashMap<String,String> item : itemsToEdit) {
        item.remove("label");
        item.put("label", editedLabel);
        model.writeToJsonFile(item);
    }

    // find every hashmap that has this label referenced and change it to the new one
    for (HashMap<String,String> item : fullItemList) {
      HashMap<String,String> selectedItem = item;
      if (selectedItem.containsKey("list") && selectedItem.containsValue(prevLabel)) {
        selectedItem.remove("list");
        selectedItem.put("list", editedLabel);
        model.deleteItem(Integer.parseInt(item.get("id")));
        model.writeToJsonFile(selectedItem);
      } else if (selectedItem.containsKey("List") && selectedItem.containsValue(prevLabel)) {
        selectedItem.remove("List");
        selectedItem.put("List", editedLabel);
        model.deleteItem(Integer.parseInt(item.get("id")));
        model.writeToJsonFile(selectedItem);
      } else if (selectedItem.containsKey("LIST") && selectedItem.containsValue(prevLabel)) {
        selectedItem.remove("LIST");
        selectedItem.put("LIST", editedLabel);
        model.deleteItem(Integer.parseInt(item.get("id")));
        model.writeToJsonFile(selectedItem);
      } else continue;
    }

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editResult.jsp");
    dispatch.forward(request, response);
  }
}
