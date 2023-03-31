package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.datastruct.ItemInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/specificItemLabel.html/*")
public class ViewItemListLabelPartTwoServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    ArrayList<ItemInterface> items = model.getItems();
    String label = request.getParameter("label");

    // make new arraylist containing only stuff with correct labels
    ArrayList<ItemInterface> filteredItems = new ArrayList<>();
    for (ItemInterface item : items) {
      if (item.getLabel().compareToIgnoreCase(label) == 0) {
        filteredItems.add(item);
      }
    }

    request.setAttribute("listOfItems", filteredItems);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemsListLabelTwo.jsp");
    dispatch.forward(request, response);
  }
}