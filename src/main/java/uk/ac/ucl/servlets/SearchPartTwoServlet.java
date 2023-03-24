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

// The servlet invoked to perform a search.
// The url http://localhost:8080/runsearch.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/runsearch.html")
public class SearchPartTwoServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    // Use the model to do the search and put the results into the request object sent to the
    // Java Server Page used to display the results.
    Model model = ModelFactory.getModel();
    String searchParameter = (String) request.getParameter("searchParameter");
    String searchString = (String) request.getParameter("searchString");
    

    // invoke the search operation here
    ArrayList<HashMap<String,String>> listOfMatchingItems = model.searchFor(searchParameter, searchString);
    request.setAttribute("listOfMatchingItems", listOfMatchingItems);

    // Invoke the JSP page.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
    dispatch.forward(request, response);
  }
}
