package org.averni.fmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FMDServer extends HttpServlet {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/plain");
    PrintWriter out = response.getWriter();
    String data = "";
    Map<String, String> params = request.getParameterMap();
    if (params.containsKey("symbol")) {
      String symbol = request.getParameter("symbol");
      SymbolLoader sl = new SymbolLoader();
      data = sl.getSymbolData(symbol);
    } else if (params.containsKey("signals")) {
      data = BuySignals.getBuySignals(); 
    }
    out.println(data);
  }


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }

}
