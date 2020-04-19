package pl.jazapp.app.webapp.extension.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("loginAndPasswordValidator")
public class LoginAndPasswordValidator implements Validator<String> {

    public static final String CAN_CONTAIN_ONLY_SMALL_LETTERS_AND_NUMBERS_MESSAGE_ID
            = "pl.register.validator.UsernameValidator.USERNAME_OR_PASSWORD_TOO_LONG";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

    }
}
