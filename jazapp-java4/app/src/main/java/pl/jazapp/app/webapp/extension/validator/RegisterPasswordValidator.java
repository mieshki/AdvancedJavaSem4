package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("registerpasswordValidator")
public class RegisterPasswordValidator implements Validator<String> {

    public static final String REGISTER_PASSWORD_CONDITIONS
            = "pl.register.validator.REGISTER_PASSWORD_CONDITIONS";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if(!value.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9!@#$%^&*])")){
            var messageString = ContextMessagesHelper.getMsg(context).getString(REGISTER_PASSWORD_CONDITIONS);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageString));
        }
    }
}
