package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator<String> {
    public static final String CAN_CONTAIN_ONLY_SMALL_LETTERS_AND_NUMBERS_MESSAGE_ID
            = "pl.register.validator.UsernameValidator.CAN_CONTAIN_ONLY_NUMBERS_AND_SMALL_LETTERS";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if(!value.matches("[a-z0-9]*")){
            var messageString = ContextMessagesHelper.getMsg(context).getString(CAN_CONTAIN_ONLY_SMALL_LETTERS_AND_NUMBERS_MESSAGE_ID);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageString));
        }
    }
}
