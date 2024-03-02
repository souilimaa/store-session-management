import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class commande extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public commande() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String disque=request.getParameter("disque");
		HttpSession session=request.getSession();
		 List<String> panier = (List<String>) session.getAttribute("panier");
		    if (panier == null) {
		        panier = new ArrayList<String>();
		    }
		    
		    panier.add(disque);
		    session.setAttribute("panier", panier);
		Cookie[] cookies=request.getCookies();
        String IdentificationNom=null;
        if(cookies!=null) {
       	 for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nom")) {
                	IdentificationNom = cookie.getValue();
                }  
            }
       }
		PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<div>");
		out.println("<div class=\"center\">");
		out.println("<h1>Bienvenue "+IdentificationNom+", dans la servlet Achat</h1>");
		out.println("<h2 style=\"margin-bottom:50px\">(votre panier)</h2>");
		out.println("<div><table border=\"1\">");
		out.println("<tr><td class=\"gold\">Informations disque commandés: </td></tr>");
		for(String disqueE :panier) { 
			out.println("<tr><td>"+disqueE+"</span></td></tr>");
		}
		out.println("</table></div>");
		out.println("<div style=\"margin:50px;\">Votre panier contient: <span style=\"font-weight:bold;\">"+panier.size()+"</span> disque(s)</div>");
		out.println("<div><a href=\"Achat\"> Vous pouvez commandez un autre disque</a></div>");
		out.println("<div><a href=\"enregistre\"> Vous pouvez enregistrer votre commande</a></div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("<style>.center{width:700px;display: flex;flex-direction: column;align-items: center;}"
				+ " .gold{background-Color:gold;font-Weight:bold; width:460px;} "
				+ "tr{text-align:center} body{display:flex; justify-content:center;}");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
