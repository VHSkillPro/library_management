package utils;

import java.util.function.Predicate;

public class ValidateElement<T> {
	private String message;
	private Predicate<T> func;
	
	public ValidateElement(String message, Predicate<T> func) {
		this.message = message;
		this.func = func;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Predicate<T> getFunc() {
		return func;
	}

	public void setFunc(Predicate<T> func) {
		this.func = func;
	}
}
