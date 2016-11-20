package test07.eureka3.com;

import java.io.Serializable;

public class OneItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * このインスタンスが表す列のインデックス ( 何番目の列か )
	 */
	private int columnIndex;
	public void setColumnIndex(int value) {
		this.columnIndex = value;
	}
	public int getColumnIndex() {
		return this.columnIndex;
	}

	/**
	 * このインスタンスが表す列の内容
	 */
	private String columnContent;
	public void setColumnContent(String value) {
		this.columnContent = value;
	}
	public String getColumnContent() {
		return this.columnContent;
	}

}
