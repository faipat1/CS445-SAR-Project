package main;

public class ErrorResponse {
	
	private String type;
	private String title;
	private String detail;
	private int status;
	private String instance;
	
	public ErrorResponse(String detail, String instance) {
		this.type = "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation";
		this.title = "Your request data didn't pass validation";
		this.detail = detail;
		this.status = 400;
		this.instance = instance;
	}

}
