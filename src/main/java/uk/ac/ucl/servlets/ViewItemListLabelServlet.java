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

@WebServlet("/itemsListLabel.html")
public class ViewItemListLabelServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    ArrayList<Item> items = model.getItems();
    List<String> labelNames = new ArrayList<String>();

    // gets only one instance of items of each list to get a list of unique labels
    for (Item stuff : items) {
      if (!(labelNames.contains(stuff.getLabel()))) {
        labelNames.add(stuff.getLabel());
      }
    }

    request.setAttribute("listOfItems", labelNames);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemsListLabel.jsp");
    dispatch.forward(request, response);
  }
  
}
