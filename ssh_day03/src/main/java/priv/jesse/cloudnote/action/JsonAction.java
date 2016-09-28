package priv.jesse.cloudnote.action;

public interface JsonAction <T> {

	JsonResult<T> getResult();

	/* (non-Javadoc)
	 * @see priv.jesse.cloudnote.web.JsonAction#setResult(priv.jesse.cloudnote.web.JsonResult)
	 */
	void setResult(JsonResult<T> result);
	

}