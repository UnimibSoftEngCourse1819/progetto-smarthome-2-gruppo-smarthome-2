package middleware;

public class MiddlewareException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object lowLevelException;
	
	public MiddlewareException(Object e) {
		this.setLowLevelException(e);
	}
	
	public void setLowLevelException(Object e) {
		this.lowLevelException = e;
	}
		
	public Object getLowLevelException() {
		return this.lowLevelException;
	}
	
	public String getLowLevelExceptionString() {
		return this.lowLevelException.toString();
	}
	
	@Override
	public String getMessage() {
		return this.getLowLevelExceptionString();
	}
}
