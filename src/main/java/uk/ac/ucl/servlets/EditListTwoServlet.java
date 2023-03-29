package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.datastruct.Item;

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
    ArrayList<Item> fullItemList = model.getItems();

    // extracting the data from the text box
    String editedLabel = request.getParameter("editedLabel");
    String prevLabel = request.getParameter("prevLabel");

    // change the hashmaps accordingly
    ArrayList<Integer> idToEdit = model.getItemsIDFromLabel(prevLabel);
    ArrayList<Item> itemsToEdit = new ArrayList<>();
    for (int id : idToEdit) {
        itemsToEdit.add(model.getItemFromId(id));
        model.deleteItem(id);
    }

    // iterate through the itemsToEdit and change the value of key "label"
    for (Item item : itemsToEdit) {
        item.editLabel(editedLabel);
        model.writeJsonArray(item);
    }

    List<Item> itemsToDelete = new ArrayList<>();

    for (Item item : fullItemList) {
      if (item.otherParametersContainsKey("list") && item.otherParametersContainsValue(prevLabel)) {
        item.removeOtherParameter("list");
        item.addItem("list", editedLabel);
        itemsToDelete.add(item);
      } else if (item.otherParametersContainsKey("List") && item.otherParametersContainsValue(prevLabel)) {
        item.removeOtherParameter("List");
        item.addItem("List", editedLabel);
        itemsToDelete.add(item);
      } else if (item.otherParametersContainsKey("LIST") && item.otherParametersContainsValue(prevLabel)) {
        item.removeOtherParameter("LIST");
        item.addItem("LIST", editedLabel);
        itemsToDelete.add(item);
      }
    }
    
    for (Item item : itemsToDelete) {
        model.deleteItem(item.getId());
        model.writeJsonArray(item);
    }
    

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editResult.jsp");
    dispatch.forward(request, response);
  }
}