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
import java.util.HashMap;

@WebServlet("/runAddItem.html")
public class ViewAddItemServlet extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();

    String numFieldsString = (String) request.getParameter("numFields");
    int numFields = Integer.parseInt(numFieldsString);

    // displays the right amount of fields, and preloads in the previous values for easy editing
    Item newItemHashMap = new Item(model.generateID(), request.getParameter("labelParameter"), request.getParameter("valueParameter"), new HashMap<>());
    for (int i = 1; i < numFields + 1; ++i) {
      if ((request.getParameter("parameterKey" + i) != null && !request.getParameter("parameterKey" + i).isEmpty()) && (request.getParameter("parameterValue" + i) != null && !request.getParameter("parameterValue" + i).isEmpty())) {
        newItemHashMap.addItem(request.getParameter("parameterKey" + i), request.getParameter("parameterValue" + i));
      }
    }

    model.writeJsonArray(newItemHashMap);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/success.jsp");
    dispatch.forward(request, response);
  }
}