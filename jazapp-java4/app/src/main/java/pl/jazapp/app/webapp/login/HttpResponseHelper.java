package pl.jazapp.app.webapp.login;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpResponseHelper {

    public static void setResponse(FacesContext facesContext, String message) throws IOException {
        HttpServletResponse response = (HttpServletResponse)(FacesContext.getCurrentInstance().getExternalContext().getResponse());
        response.getWriter().println(message);
    }
}
