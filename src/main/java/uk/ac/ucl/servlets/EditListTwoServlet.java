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
    Model model = ModelFactory.getModel();
    ArrayList<Item> fullItemList = model.getItems();

    String editedLabel = request.getParameter("editedLabel");
    String prevLabel = request.getParameter("prevLabel");

    // take in the toEdit items, and delete them accordingly
    ArrayList<Integer> idToEdit = model.getItemsIDFromLabel(prevLabel);
    ArrayList<Item> itemsToEdit = new ArrayList<>();
    for (int id : idToEdit) {
        itemsToEdit.add(model.getItemFromId(id));
        model.deleteItem(id);
    }

    // iterate through the itemsToEdit and change the value of key "label", and reappend back to JSON
    for (Item item : itemsToEdit) {
        item.editLabel(editedLabel);
        model.writeJsonArray(item);
    }

    // this section below checks all items to see if the previous list was mentioned anywhere, and changes the reference automatically
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

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editResult.jsp");
    dispatch.forward(request, response);
  }
  
}