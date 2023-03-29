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
    // Get the data from the model
    Model model = ModelFactory.getModel();


    // get all the necessary data from jsp
    String numFieldsString = (String) request.getParameter("numFields");
    int numFields = Integer.parseInt(numFieldsString);

    // extracting the data from the text box into a hashmap
    Item newItemHashMap = new Item(model.generateID(), request.getParameter("labelParameter"), request.getParameter("valueParameter"), new HashMap<>());
    for (int i = 1; i < numFields + 1; ++i) {
      if ((request.getParameter("parameterKey" + i) != null && (request.getParameter("parameterValue" + i)) != null) || ((request.getParameter("parameterKey" + i)).compareToIgnoreCase("label") != 0 && (request.getParameter("parameterValue" + i)).compareToIgnoreCase("value") != 0)) {
        newItemHashMap.addItem(request.getParameter("parameterKey" + i), request.getParameter("parameterValue" + i));
      }
    }

    System.out.println(newItemHashMap.getOtherParameters());
    // append the text box into the json
    model.writeJsonArray(newItemHashMap);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/addResult.jsp");
    dispatch.forward(request, response);
  }
}