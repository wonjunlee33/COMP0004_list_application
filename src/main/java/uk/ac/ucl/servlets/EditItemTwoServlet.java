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

@WebServlet("/runEditItemTwo.html")
public class EditItemTwoServlet extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();

    // extracting the data from the text box into a hashmap
    String numFieldsString = request.getParameter("numFields");
    int numFields = Integer.parseInt(numFieldsString);
    String idRequestString = request.getParameter("idRequest");
    int idRequest = Integer.parseInt(idRequestString);

    // start creating new hashmap
    HashMap<String,String> newItemHashMap = new HashMap<>();
    newItemHashMap.put("label", request.getParameter("labelParameter"));
    newItemHashMap.put("value", request.getParameter("valueParameter"));
    newItemHashMap.put("id", idRequestString);

    // for loop for all the rest
    for (int i = 1; i < numFields + 1; ++i) {
      newItemHashMap.put(request.getParameter("parameterKey"+i), request.getParameter("parameterValue"+i));
    }

    // delete previous item
    model.deleteItem(idRequest);

    // append the text box into the json
    model.writeToJsonFile(newItemHashMap);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editResult.jsp");
    dispatch.forward(request, response);
  }
}