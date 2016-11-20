package test07.eureka3.com;

import java.io.Serializable;

import java.util.ArrayList;

public class OneRow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OneRow() {
		this.myRow = new ArrayList<OneItem>();
	}

	/**
	 * このインスタンスが表すテーブルの列数
	 */
	private int columnCount;
	public void setColumnCount(int value) {
		this.columnCount = value;
	}
	public int getColumnCount() {
		return this.columnCount;
	}

	/**
	 * このインスタンスの表す行
	 */
	private ArrayList<OneItem> myRow;
	public void setMyRow(ArrayList<OneItem> value) {
		this.myRow = value;
	}
	public ArrayList<OneItem> getMyRow() {
		return this.myRow;
	}

	/**
	 * このインスタンスが表す行のアイテムを引数によりセットする
	 * @param columnIndex : 列のインデックス
	 * @param value : 値
	 */
	public void setItemWithColumnIndex(int columnIndex, String value) {
		this.myRow.get(columnIndex).setColumnContent(value);
	}

	/**
	 * 行のインスタンスを生成
	 */
	public OneRow addRow() {
	
		int loopCount = 0;
		for (loopCount = 0; loopCount < this.columnCount; loopCount++) {
			OneItem addItem = new OneItem();
			addItem.setColumnIndex(loopCount);
			myRow.add(addItem);
		}
		return this;
	
	}

}