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
import java.util.ArrayList;

@WebServlet("/runDeleteList.html")
public class DeleteListServlet extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();

    String labelRequest = (String) request.getParameter("deleteLabel");
    if (labelRequest != null) { 
      // delete all items that have the current label to delete
      ArrayList<Integer> itemsToDelete = model.getItemsIDFromLabel(labelRequest);
      for (int itemID : itemsToDelete) {
        model.deleteItem(itemID);
      }
    }

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/deleteResult.jsp");
    dispatch.forward(request, response);
  }
  
}