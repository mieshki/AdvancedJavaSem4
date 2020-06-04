package pl.jazapp.app.webapp.extension.validator;

import javax.faces.context.FacesContext;
import java.util.PropertyResourceBundle;

public abstract class ContextMessagesHelper {
    public static PropertyResourceBundle getMsg(FacesContext context){
        return context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);
    }
}
