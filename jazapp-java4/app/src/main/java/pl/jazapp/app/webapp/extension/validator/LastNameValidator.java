package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("lastnameValidator")
public class LastNameValidator implements Validator<String> {

    public static final String LAST_NAME_ONLY_LETTERS
            = "pl.register.validator.LAST_NAME_ONLY_LETTERS";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if(!value.matches("[a-zA-Z]*")){
            var messageString = ContextMessagesHelper.getMsg(context).getString(LAST_NAME_ONLY_LETTERS);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageString));
        }
    }
}
