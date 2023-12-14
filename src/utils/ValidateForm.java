package utils;

import java.util.ArrayList;

public class ValidateForm<T> {
	private ArrayList<ValidateElement<T>> elements;
	
	public ValidateForm() {
		this.elements = new ArrayList<ValidateElement<T>>();
	}

	public void addElement(ValidateElement<T> element) {
		elements.add(element);
	}
	
	public String validate(T value) {
		for (ValidateElement<T> element: elements) {
			if (!element.getFunc().test(value)) {
				return element.getMessage();
			}
		}
		return null;
	}
}
