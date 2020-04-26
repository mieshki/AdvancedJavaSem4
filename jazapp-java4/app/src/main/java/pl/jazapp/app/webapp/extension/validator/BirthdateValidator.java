package pl.jazapp.app.webapp.extension.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("birthdateValidator")
public class BirthdateValidator implements Validator<String> {

    public static final String WRONG_DATE_FORMAT
            = "pl.register.validator.WRONG_DATE_FORMAT";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if(!value.matches("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$")){
            var messageString = ContextMessagesHelper.getMsg(context).getString(WRONG_DATE_FORMAT);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", messageString));
        }
    }
}
