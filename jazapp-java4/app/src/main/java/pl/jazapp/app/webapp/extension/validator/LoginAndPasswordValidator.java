package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("loginAndPasswordValidator")
public class LoginAndPasswordValidator implements Validator<String> {

    public static final String USERNAME_OR_PASSWORD_TOO_LONG
            = "pl.login.validator.LoginValidator.USERNAME_OR_PASSWORD_TOO_LONG";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if(value.length() > 25){
            var messageString = ContextMessagesHelper.getMsg(context).getString(USERNAME_OR_PASSWORD_TOO_LONG);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageString));
        }
    }
}
