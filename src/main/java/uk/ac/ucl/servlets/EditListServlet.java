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

@WebServlet("/runEditList.html/*")
public class EditListServlet extends HttpServlet
{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {

      // get pre-change label here
      String labelRequest = (String) request.getParameter("editLabel");
      if (labelRequest != null) {
        request.setAttribute("prevLabel", labelRequest);
      }

      // acquire number of fields to display
      String numFields = request.getParameter("numFields");
      if (numFields != null) {
        request.setAttribute("numFields", numFields);
      }

      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/editList.jsp");
      dispatch.forward(request, response);
    }
    
}
