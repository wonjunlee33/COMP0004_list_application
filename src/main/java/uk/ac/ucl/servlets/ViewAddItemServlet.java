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
    HashMap<String,String> newItemHashMap = new HashMap<>();
    newItemHashMap.put("id", String.valueOf(model.generateID()));
    newItemHashMap.put("label", request.getParameter("labelParameter"));
    newItemHashMap.put("value", request.getParameter("valueParameter"));
    for (int i = 1; i < numFields + 1; ++i) {
      if ((request.getParameter("parameterKey" + i) != null && (request.getParameter("parameterValue" + i)) != null) || ((request.getParameter("parameterKey" + i)).compareToIgnoreCase("label") != 0 && (request.getParameter("parameterValue" + i)).compareToIgnoreCase("value") != 0)) {
        newItemHashMap.put(request.getParameter("parameterKey" + i), request.getParameter("parameterValue" + i));
      }
    }

    // append the text box into the json
    model.writeToJsonFile(newItemHashMap);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/addResult.jsp");
    dispatch.forward(request, response);
  }
}