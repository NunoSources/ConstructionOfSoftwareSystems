package controller.web.inputController.actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import presentation.web.model.Model;

/**
 * An abstract HTTP action request handler. 
 * Think of it as an operation in the SSD diagram.
 * It has an init method, because objects are
 * create from the prototype (vide UseCaseFrontController)
 * and its easier to use a no parameters construct.
 * 
 * It allows subclasses to define how to handle individual 
 * actions.
 * 
 * We need to store the http request context, since
 * actions are not http servlets and do not have access to
 * the request data.
 *  
 * @author fmartins
 * @author malopes
 */
public abstract class Action {

	/**
	 * Strategy method for processing each request
	 * @throws ServletException
	 * @throws IOException
	 */
	public abstract void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	
	// the following methods might need to be refactored in order to avoid
	// duplicated code
	protected boolean isInt(Model help, String num, String message) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			help.addMessage(message);
			return false;
		}
	}

	protected int intValue(String num) {
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return 0;
		}
	}


	protected boolean isFilled (Model helper, String value, String message) {
		if (value == null || value.equals("")) {
			helper.addMessage(message);
			return false;
		}
		return true;
	}
	
	
	protected boolean isFilledArrays (Model helper, String[] value, String message) {
		if (value == null) {
			helper.addMessage(message);
			return false;
		} else {
			for (String v : value) {
				if (v.equals("")) {
					helper.addMessage(message);
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean isBoolean (Model helper, String value, String message) {
		try {
			Boolean.parseBoolean(value);
			return true;
		} catch(Exception e) {
			helper.addMessage(message);
			return false;
		}
		
	}
	
	protected boolean booleanValue(String value) {
		try {
			return Boolean.parseBoolean(value);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	protected boolean isFloat (Model helper, String value, String message) {
		try {
			Float.parseFloat(value);
			return true;
		} catch(Exception e) {
			helper.addMessage(message);
			return false;
		}
		
	}
	
	protected float floatValue(String value) {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	protected boolean isDate (Model helper, String value, String message) {
		if (value == null || value.equals("")) {
			helper.addMessage(message);
			return false;
		}
		try {
			LocalDate.parse(value);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	protected boolean isEmail (Model helper, String value, String message) {
		
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher m = pattern.matcher(value);
        return m.matches();
	}


}
