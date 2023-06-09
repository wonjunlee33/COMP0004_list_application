/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.45
 * Generated at: 2023-03-31 11:16:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uk.ac.ucl.datastruct.Item;

public final class addItem_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("uk.ac.ucl.datastruct.Item");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/meta.jsp", out, false);
      out.write("\n");
      out.write("  <title>List Application</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/header.jsp", out, false);
      out.write("\n");
      out.write("<div class=\"main\">\n");
      out.write("  <h1>Add Item</h1>\n");
      out.write("  ");
int numFields = 0; // the initial number of lists to display
      out.write("\n");
      out.write("  \n");
      out.write("  <!-- creates boxes for label and value, these are needed for every item -->\n");
      out.write("  <form method=\"POST\" action=\"/runAddItem.html\">\n");
      out.write("    <input type=\"text\" name=\"label\" placeholder=\"Enter label...\" size=\"50\" value=\"label\" required/>\n");
      out.write("    <input type=\"text\" name=\"labelParameter\" placeholder=\"Enter label parameter...\" size=\"50\" value=\"\" required/>\n");
      out.write("    <br>\n");
      out.write("    <input type=\"text\" name=\"value\" placeholder=\"Enter value...\" size=\"50\" value=\"value\" required/>\n");
      out.write("    <input type=\"text\" name=\"valueParameter\" placeholder=\"Enter value parameter...\" size=\"50\" value=\"\" required/>\n");
      out.write("    <br>\n");
      out.write("    ");
 
    if(request.getParameter("numFields") != null) {
        numFields = Integer.parseInt(request.getParameter("numFields"));
    }
    for(int i=0; i<numFields; i++) { 
      out.write("\n");
      out.write("      <!-- logic to make more fields if necessary -->\n");
      out.write("        <input type=\"text\" name=\"parameterKey");
      out.print(i + 1);
      out.write("\" placeholder=\"Enter Parameter Name ");
      out.print(i + 1);
      out.write("...\" size=\"50\"/>\n");
      out.write("        <input type=\"text\" name=\"parameterValue");
      out.print(i + 1);
      out.write("\" placeholder=\"Enter Parameter Value ");
      out.print(i + 1);
      out.write("...\" size=\"50\" value=\"\"/>\n");
      out.write("        <br>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("  \n");
      out.write("    <p></p>\n");
      out.write("    <input type=\"hidden\" name=\"numFields\" value=\"");
      out.print(numFields);
      out.write("\"/>\n");
      out.write("    <input type=\"submit\" class=\"btn\" value=\"Add\"/>\n");
      out.write("  </form>\n");
      out.write("  \n");
      out.write("  <form method=\"POST\" action=\"/addItem.jsp\">\n");
      out.write("    <input type=\"hidden\" name=\"numFields\" value=\"");
      out.print(numFields+1);
      out.write("\"/>\n");
      out.write("    <input type=\"submit\" class=\"btn\" value=\"Add more information...\"/>\n");
      out.write("  </form>\n");
      out.write("\n");
      out.write("  ");

  if (numFields > 0) {
  
      out.write("\n");
      out.write("  <!-- only display this field if there are optional parameters, since need labels and values -->\n");
      out.write("  <form method=\"POST\" action=\"/addItem.jsp\">\n");
      out.write("    <input type=\"hidden\" name=\"numFields\" value=\"");
      out.print(numFields-1);
      out.write("\"/>\n");
      out.write("    <input type=\"submit\" class=\"btn\" value=\"Delete last field...\"/>\n");
      out.write("  </form>\n");
 } 
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<p>Remember: All items must have a label and value parameter!</p>\n");
      out.write("<p>Also, please make sure to pre-load in the number of fields required!</p>\n");
      out.write("<p>Empty fields will not be added, so they can safely be put in if necessary.</p>\n");
      out.write("<br>\n");
      out.write("<p>Some specific keys:\n");
      out.write("  <li>\n");
      out.write("    image / img : Pass a URL to display the image.\n");
      out.write("  </li>\n");
      out.write("  <li>\n");
      out.write("    url / URL : Pass a URL to display a hyperlink.\n");
      out.write("  </li>\n");
      out.write("  <li>\n");
      out.write("    list : pass in a valid label to have a link to that list.\n");
      out.write("  </li>\n");
      out.write("  <li>\n");
      out.write("    item : pass in a valid item ID to have a link to that item.\n");
      out.write("  </li>\n");
      out.write("</p>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer.jsp", out, false);
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
