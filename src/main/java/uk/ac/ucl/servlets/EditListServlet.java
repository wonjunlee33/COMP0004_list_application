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
      // Get the data from the model
      // Model model = ModelFactory.getModel();
    
      // Then add the data to the request object that will be sent to the Java Server Page, so that
      // the JSP can access the data (a Java data structure).
      String labelRequest = (String) request.getParameter("editLabel");
      if (labelRequest != null) {
        request.setAttribute("prevLabel", labelRequest);
      }

      String numFields = request.getParameter("numFields");
      if (numFields != null) {
        request.setAttribute("numFields", numFields);
      }

      // Invoke the JSP.
      // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/editList.jsp");
      dispatch.forward(request, response);
    }
    
}
