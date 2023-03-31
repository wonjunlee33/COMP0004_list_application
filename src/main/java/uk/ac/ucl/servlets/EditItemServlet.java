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

@WebServlet("/runEditItem.html")
public class EditItemServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
      Model model = ModelFactory.getModel();
    
      String idRequest = (String) request.getParameter("editid");
      if (idRequest != null) {
        int id = Integer.parseInt(idRequest);
    
        ItemInterface itemToEdit = model.getSpecificItem(id);
        request.setAttribute("itemToEdit", itemToEdit);
      }

      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/editItem.jsp");
      dispatch.forward(request, response);
    }

}