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

@WebServlet("/runEditItemTwo.html")
public class EditItemTwoServlet extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();

    // extracting the data from the text box into an item struct
    String numFieldsString = request.getParameter("numFields");
    int numFields = Integer.parseInt(numFieldsString);
    String idRequestString = request.getParameter("idRequest");
    int idRequest = Integer.parseInt(idRequestString);

    // start creating new Item
    Item newItem = new Item(idRequest, request.getParameter("labelParameter"), request.getParameter("valueParameter"), new HashMap<>());

    // for loop for all the rest (ie filling up the hashmap of extra parameters)
    for (int i = 1; i < numFields + 2; ++i) {
      if (request.getParameter("parameterKey" + i).compareToIgnoreCase("") != 0 && (request.getParameter("parameterValue" + i)).compareToIgnoreCase(idRequestString) != 0) {
        newItem.addItem(request.getParameter("parameterKey"+i), request.getParameter("parameterValue"+i));
      } else {
        continue;
      }
    }

    // delete previous item
    model.deleteItem(idRequest);

    // append into the json
    model.writeJsonArray(newItem);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editResult.jsp");
    dispatch.forward(request, response);
  }
  
}