package test07.eureka3.com;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class UpdateTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * タグ処理
	 */
	public int doStartTag() throws JspException {
		
		try {
			
			if ((pageContext.getRequest().getParameterMap().containsKey("updateKeyPrefCode")) 
					&& (pageContext.getRequest().getParameterMap().containsKey("updateKeyPrefName"))) {
				
				// HTTP リクエストに更新キーが指定してあるときのみ処理を行う
				
				UpdateBean myBean = new UpdateBean();
				myBean.setUpdateKeyPrefCode(pageContext.getRequest().getParameterMap().get("updateKeyPrefCode")[0]);
				myBean.setUpdateKeyPrefName(pageContext.getRequest().getParameterMap().get("updateKeyPrefName")[0]);
				myBean.setUpdateValuePrefCode(pageContext.getRequest().getParameterMap().get("updateValuePrefCode")[0]);
				myBean.setUpdateValuePrefName(pageContext.getRequest().getParameterMap().get("updateValuePrefName")[0]);
				myBean.setUpdateKeyAllCode(pageContext.getRequest().getParameterMap().get("updateKeyAllCode")[0]);
				myBean.update_TEST01();
				
			}
			
		} catch (Exception e) {
			
			throw new JspException(e);
			
		}
		return SKIP_BODY;
		
	}

}
