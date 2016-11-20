package test07.eureka3.com;

import java.io.Serializable;

import java.util.ArrayList;
import java.sql.ResultSet;

public class Cast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * このインスタンスの表すテーブル
	 */
	private ArrayList<OneRow> myTable;
	public ArrayList<OneRow> getMyTable() {
		return this.myTable;
	}

	/**
	 * このインスタンスがテーブル化させるレコードセット
	 */
	private ResultSet myResultSet;
	public void setMyResultSet(ResultSet value) {
		this.myResultSet = value;
	}

	/**
	 * このインスタンスの表すテーブルが何列か
	 */
	private int columnCount;
	public void setColumnCount(int value) {
		this.columnCount = value;
	}

	/**
	 * コンストラクタ
	 */
	public Cast() throws Exception {
		
		this.myTable = new ArrayList<OneRow>();
		
	}

	/**
	 * テーブル化
	 */
	public void doCast() throws Exception {
		
		if (this.myResultSet == null) {
			
			return;
			
		} else {
			
			this.myResultSet.first();
			
			while (this.myResultSet.next()) {
				
				OneRow row = new OneRow();
				row.setColumnCount(this.columnCount);
				
				OneRow currentRow = row.addRow();
				int loopCount = 1;
				for (loopCount = 1; loopCount < currentRow.getColumnCount() + 1; loopCount++) {
					currentRow.setItemWithColumnIndex(loopCount - 1, this.myResultSet.getString(loopCount));
				}
				
				this.myTable.add(currentRow);
				
			}
			
		}
		
	}
}
