import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.CommandDaoImpl;
import DAO.UserDaoImpl;
import models.command;
import models.user;
public class enregistre extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public enregistre() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
        String IdentificationNom=null;
        String IdentificationPassword=null;
        if(cookies!=null) {
       	 for (Cookie cookie : cookies) {
                if (cookie.getName().equals("nom")) {
                	IdentificationNom = cookie.getValue();
                }  
                else if(cookie.getName().equals("motdepasse")) {
                	IdentificationPassword=cookie.getValue();
                }
            }
       }
        HttpSession session=request.getSession();
        int userId=-1;
        CommandDaoImpl comDao=null;
		 @SuppressWarnings("unchecked")
		List<String> panier = (List<String>) session.getAttribute("panier");
		 UserDaoImpl userdao=new UserDaoImpl();
		 user u=new user(IdentificationNom,IdentificationPassword);
		 try {
			 userId=userdao.insert(u);
			 comDao=new CommandDaoImpl();
			for(String p:panier) {
				command c=new command(userId,p);
				comDao.insert(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 session.removeAttribute("panier");
		 PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<div>");
		out.println("<div class=\"center\">");
		out.println("<h1>Bienvenue "+IdentificationNom+", dans la servlet Achat</h1>");
		out.println("<div><table border=\"1\">");
		out.println("<tr><td class=\"gold\">Nombre de disques dans le panier: </td></tr>");
		@SuppressWarnings("unchecked")
		List<String> panierr=(List<String>) session.getAttribute("panier");
		if(panierr==null) {
			out.println("<tr><td>Votre panier est vide suite à la commande</td></tr>");
		}
		else {
		for(String disqueE :panierr) { 
			out.println("<tr><td>"+disqueE+"</span></td></tr>");
		}
		}
		out.println("</table></div>");		
		out.println("<h2>Voici ta commande complète</h2>");
		out.println("<div><table border=\"1\">");
		out.println("<tr><td class=\"gold\">Voici les disques commandés: </td></tr>");
		List<command> commandes=new ArrayList<command>();
		try {
			commandes=comDao.getAll(userId);
			for(command c :commandes) { 
				out.println("<tr><td>"+c.getProduct()+"</span></td></tr>");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println("</table></div>");	
		out.println("<div><a href=\"Achat\">Effectuer un autre achat</a></div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</body>");
		out.println("<style>.center{width:700px;display: flex;flex-direction: column;align-items: center;} "
				+ ".gold{background-Color:gold;font-Weight:bold; width:460px;} "
				+ "tr{text-align:center} body{display:flex; justify-content:center;}");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
