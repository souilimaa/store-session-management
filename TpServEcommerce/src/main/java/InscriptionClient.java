import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InscriptionClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public InscriptionClient() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
        Cookie[] cookies=request.getCookies();
        String nomCookie=null;
        String motDePasseCookie=null;
        String nomRecu=request.getParameter("nom");
        String motDePasseRecu=request.getParameter("password");
        if(cookies!=null) {
        	 for (Cookie cookie : cookies) {
                 if (cookie.getName().equals("nom")) {
                     nomCookie = cookie.getValue();
                 } else if (cookie.getName().equals("motdepasse")) {
                     motDePasseCookie = cookie.getValue();
                 }
             }
        }
        out.println("<div>Parametres recu:NR="+nomRecu+" MPR= "+motDePasseRecu+" NC= "+nomCookie+" MPC= "+motDePasseCookie+"</div>");
        if (nomCookie == null && nomRecu==null) {
        out.println("<h1>Veuillez effectuer votre inscription </h1>");
		out.println("<div>Le nom et mot de passe > 3 caracteres!!</div>");
		out.println("<form action=\"InscriptionClient\"  method=\"get\">");
		out.println("<div>");
		out.println("<label>Nom:</label>");
		out.println("<input type=\"text\" minlength=\"4\" name=\"nom\" required>");
		out.println("</div>");
		out.println("<div>");
		out.println("<label>Password:</label>");
		out.println("<input type=\"password\" minlength=\"4\" name=\"password\"  required>");
		out.println("</div>");
		out.println("<input type=\"submit\" value=\"Sinscrire\" name=\"action\">");
		out.println("</form>");
        }else if(nomCookie==null && nomRecu!=null){
        	Cookie nomCookieObj=new Cookie("nom",nomRecu);
        	Cookie motPasseCookieObj=new Cookie("motdepasse",motDePasseRecu);
        	response.addCookie(motPasseCookieObj);
        	response.addCookie(nomCookieObj);
            response.sendRedirect(request.getRequestURI());
        			}
        else if(identique(nomRecu,nomCookie) && identique(motDePasseRecu,motDePasseCookie)) {
            response.sendRedirect(request.getContextPath() + "/Achat");
        }
        else{
        	out.println("<h1>Authentification </h1>");
    		out.println("<div>Le nom et mot de passe > 3 caracteres!!</div>");
    		out.println("<form action=\"InscriptionClient\"  method=\"get\">");
    		out.println("<div>");
    		out.println("<label>Nom:</label>");
    		out.println("<input type=\"text\" minlength=\"4\" name=\"nom\" required >");
    		out.println("</div>");
    		out.println("<div>");
    		out.println("<label>Password:</label>");
    		out.println("<input type=\"password\" minlength=\"4\" name=\"password\" required>");
    		out.println("</div>");
    		out.println("<input type=\"submit\" value=\"Sidentifier\" name=\"action\">");
    		out.println("</form>");
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	boolean identique(String recu,String cookie) {
		return((recu!=null)&&(recu.length()>3) && (cookie!=null)&&(recu.equals(cookie)));
	}

}
