package priv.jesse.web;

public class JsonResult<T> {
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	private String message;
	private T data;
	public JsonResult() {
	}
	public JsonResult(int state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	public JsonResult(int state,String message){
		this(state,message,null);
	}
	public JsonResult(String message){
		this(ERROR,message,null);
	}
	public JsonResult(T t){
		this(SUCCESS,"³É¹¦",t);
	}
	public JsonResult(Exception e){
		this(ERROR,e.getMessage(),null);
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
