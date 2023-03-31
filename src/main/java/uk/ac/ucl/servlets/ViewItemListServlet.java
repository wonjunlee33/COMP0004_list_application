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

@WebServlet("/itemsList.html")
public class ViewItemListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    ArrayList<? extends ItemInterface> items = model.getItems();

    request.setAttribute("listOfItems", items);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/itemsList.jsp");
    dispatch.forward(request, response);
  }

}