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

@WebServlet("/runsearch.html")
public class SearchPartTwoServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    Model model = ModelFactory.getModel();
    String searchParameter = (String) request.getParameter("searchParameter");
    String searchString = (String) request.getParameter("searchString");
  
    // invoke the search operation here
    ArrayList<ItemInterface> listOfMatchingItems = model.searchFor(searchParameter, searchString);
    request.setAttribute("listOfMatchingItems", listOfMatchingItems);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
    dispatch.forward(request, response);
  }
  
}
