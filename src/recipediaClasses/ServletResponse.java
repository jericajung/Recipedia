package recipediaClasses;

import java.util.ArrayList;

public class ServletResponse {
	private String status;
	private String data;
	private ArrayList<String> errors;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public ArrayList<String> getErrors() {
		return errors;
	}
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
	public void addError(String error) {
		this.errors.add(error);
	}
}
