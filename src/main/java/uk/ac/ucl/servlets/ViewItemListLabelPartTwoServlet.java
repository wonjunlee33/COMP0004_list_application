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

// The servlet invoked to display a list of patients. Note that this data is just example data,
// you replace it with your data.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/specificItemLabel.html/*")
public class ViewItemListLabelPartTwoServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    ArrayList<HashMap<String,String>> items = model.getItems();
    String label = request.getParameter("label");

    // make new arraylist containing only stuff with correct labels
    ArrayList<HashMap<String,String>> filteredItems = new ArrayList<>();
    for (HashMap<String,String> item : items) {
      if (item.get("label").compareToIgnoreCase(label) == 0) {
        filteredItems.add(item);
      }
    }

    // Then add the data to the request object that will be sent to the Java Server Page, so that
    // the JSP can access the data (a Java data structure).
    request.setAttribute("listOfItems", filteredItems);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemsListLabelTwo.jsp");
    dispatch.forward(request, response);
  }
}