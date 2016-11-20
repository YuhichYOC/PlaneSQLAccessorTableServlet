package test07.eureka3.com;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SearchResultTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * タグ処理
	 */
	public int doStartTag() throws JspException {
		
		try {
			
			if ((pageContext.getRequest().getParameterMap().containsKey("codeFrom")) 
					&& (pageContext.getRequest().getParameterMap().containsKey("codeTo"))) {
				
				// HTTP リクエストに検索キーが指定してあるときのみ処理を行う
				SearchBean myBean = new SearchBean();
				myBean.setSearchFrom(pageContext.getRequest().getParameterMap().get("codeFrom")[0]);
				myBean.setSearchTo(pageContext.getRequest().getParameterMap().get("codeTo")[0]);
				myBean.search_TEST01();
				
				SearchResultContextWriter w = new SearchResultContextWriter();
				w.setMyTable(myBean.getSession().get("session"));
				pageContext.getOut().write(w.writeSearchResult());
				
			}
			
		} catch (Exception e) {
			
			throw new JspException(e);
			
		}
		return SKIP_BODY;
		
	}

}
