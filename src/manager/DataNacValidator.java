package manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;



@FacesValidator("DataNacValidator")
public class DataNacValidator implements javax.faces.validator.Validator {
	
	
	public void validate(FacesContext ctx, UIComponent cmpt, Object valor){
			validateBeginDate(ctx, cmpt, valor);
		
	}
	
	
	
	public void validateBeginDate(FacesContext context, UIComponent component, Object value) {
		Date data_hoje = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 16);
		System.out.println("Data Hoje: "+ data_hoje);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	    Date nascimento = null;
		try {
			nascimento = df.parse(value.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    @SuppressWarnings("deprecation")
		int result = data_hoje.getDate() - nascimento.getDate();
		System.out.println("Resultado: "+result);
		if (result <= 16) {
			throw new ValidatorException(new FacesMessage(
					"Data de Nascimento Invalida!!!"));
		}
	}
}