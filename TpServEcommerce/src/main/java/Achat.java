import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Achat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Achat() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> disques = new ArrayList<String>();
		disques.add("Disque CD- AMOR TICINES 15 Euros");
		disques.add("Disque CD- Los Mayas 19 Euros");
		disques.add("Disque CD- Dick Anglas 25 Euros");
		disques.add("Disque CD- Frederic Angonas 35 Euros");
	    
	    
	    PrintWriter out=response.getWriter();
        Cookie[] cookies=request.getCookies();
        String IdentificationNom=null;
        if(cookies!=null) {
       	 for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nom")) {
                	IdentificationNom = cookie.getValue();
                }  
            }
       }

		out.println("<html> <body style=\" display:flex; justify-content:center;\">");
		out.println("<div>");
		out.println("<div style=\"margin-bottom:30px; margin-top:5px\">");
		out.println("<h1>Bienvenue "+IdentificationNom+" ,dans la servlet Achat"+"</h1>");
		out.println("<h4>(Liste des disques pour achat)</h4>");
		out.println("<table style=\"border: 1px solid grey;\">");
		out.println("<tr>");
		out.println("<td class=\"gold\" style=\"border: 1px solid black; width:250px; text-align:center; font-weight:bold;\">Nom Disque</td>");
		out.println("<td class=\"gold\" style=\"border: 1px solid black; text-align:center; font-weight:bold;\">Nom Commander</td>");
		out.println("</tr>");
		for(String disque :disques) {
			out.println("<tr>");
			out.println("<td style=\"border: 1px solid black; width:250px; text-align:center; font-weight:bold;\">"+disque+"</td>");
			out.println("<td style=\"border: 1px solid black; text-align:center; font-weight:bold;\">"
					+ "<a href=\"commande?disque="+disque+"\"><img src=\"resources/images/shoppingCard.jpg\" alt=\"image1\"></a>"
					+ "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<style>"
				+ ".gold{background-Color:gold;}"
				+ "img{width:75px;}</style>");
		out.println("</body></html>"); 
	    
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
