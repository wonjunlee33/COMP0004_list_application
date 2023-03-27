package uk.ac.ucl.servlets;

// import uk.ac.ucl.model.Model;
// import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/runEditItemThree.html")
public class EditItemThreeServlet extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    // Model model = ModelFactory.getModel();

    // extracting the data from the text box into a hashmap
    String numFieldsString = request.getParameter("numFields");
    int numFields = Integer.parseInt(numFieldsString);
    String editidString = request.getParameter("editid");

    // start creating new hashmap
    HashMap<String,String> newItemHashMap = new HashMap<>();
    newItemHashMap.put("label", request.getParameter("labelParameter"));
    newItemHashMap.put("value", request.getParameter("valueParameter"));
    newItemHashMap.put("id", editidString);

    // for loop for all the rest
    for (int i = 1; i < numFields + 2; ++i) {
      if (request.getParameter("parameterKey" + i) != null && (request.getParameter("parameterValue" + i)) != null) {
        newItemHashMap.put(request.getParameter("parameterKey"+i), request.getParameter("parameterValue"+i));
      } else {
        continue;
      }
    }

    // relay the new stuff back to editItem.jsp
    request.setAttribute("itemToEdit", newItemHashMap);
    System.out.println(newItemHashMap);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/editItem.jsp");
    dispatch.forward(request, response);
  }
}