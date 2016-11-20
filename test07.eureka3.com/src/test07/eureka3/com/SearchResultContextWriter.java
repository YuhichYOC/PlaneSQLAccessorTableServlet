package test07.eureka3.com;

import java.util.ArrayList;

public class SearchResultContextWriter {

	/**
	 * このインスタンスが記述を行うためのデータ
	 */
	private ArrayList<OneRow> myTable;
	public void setMyTable(ArrayList<OneRow> value) {
		this.myTable = value;
	}

	/**
	 * myTable の内容を記述 : メインメソッド
	 */
	public String writeSearchResult() {
		
		String retValue = "";
		retValue += this.writeTableHead();
		
		int rowCount = 1;
		
		if (myTable.isEmpty()) {
			// 何もしない
		} else {
			for (OneRow thisRow: this.myTable) {
				// テーブルレコードヘッダ ( 検索側 )
				retValue += this.writeTRHead(rowCount);
				// レコードの内容 ( 検索側 )
				retValue += this.writeOneRow(rowCount, thisRow.getMyRow().get(0).getColumnContent(), thisRow.getMyRow().get(1).getColumnContent());
				// レコードテール ( 検索側 )
				retValue += this.writeTRTail();
				// テーブルレコードヘッダ ( 編集側 )
				retValue += this.writeTRHead4Edit(rowCount);
				// レコードの内容 ( 編集側 )
				retValue += this.writeOneRow4Edit(rowCount, thisRow.getMyRow().get(0).getColumnContent(), thisRow.getMyRow().get(1).getColumnContent(), thisRow.getMyRow().get(2).getColumnContent());
				// レコードテール ( 編集側 )
				retValue += this.writeTRTail();
				rowCount += 1;
			}
		}
		retValue += this.writeTableTail();
		
		return retValue;
		
	}

	/**
	 * テーブルの内容を記述 ( 検索結果側 )
	 */
	private String writeOneRow(int rowCount, String prefCode, String prefName) {
		String retValue = "";
		retValue += "<td class=\"resultTable submit\">" + rowCount + "</td>" + "\n";
		retValue += "<td class=\"resultTable\">" + prefCode + "</td>" + "\n";
		retValue += "<td class=\"resultTable\">" + prefName + "</td>" + "\n";
		return retValue;
	}

	/**
	 * テーブルの内容を記述 ( 編集用フォーム側 )
	 */
	private String writeOneRow4Edit(int rowCount, String prefCode, String prefName, String prefUpdateKey) {
		String retValue = "";
		retValue += "<form action=\"searchForm.jsp\" method=\"post\" id=\"row" + rowCount + "\">" + "\n";
		retValue += "<td class=\"resultEdit submit\">" + "<input type=\"submit\" value=\"更新\">" + "</td>" + "\n";
		retValue += "<td class=\"resultEdit\">" + "\n";
		retValue += "  更新前の値 : " + "<input type=\"text\" name=\"updateKeyPrefCode\" value=\"" + prefCode + "\" readonly=\"readonly\" size=\"3\" style=\"border:none\" >" + "<br/>" + "\n";
		retValue += "  " + "<input type=\"text\" name=\"updateValuePrefCode\" value=\"" + prefCode + "\" size=\"3\">" + "<br/>" + "\n";
		retValue += "</td>";
		retValue += "<td class=\"resultEdit\">" + "\n";
		retValue += "  更新前の値 : " + "<input type=\"text\" name=\"updateKeyPrefName\" value=\"" + prefName + "\" readonly=\"readonly\" size=\"20\" style=\"border:none\" >" + "<br/>" + "\n";
		retValue += "  " + "<input type=\"text\" name=\"updateValuePrefName\" value=\"" + prefName + "\" size=\"20\">" + "<br/>" + "\n";
		retValue += "</td>";
		retValue += "<td style=\"display:none\">" + "\n";
		retValue += "  <input type=\"text\" name=\"updateKeyAllCode\" value=\"" + prefUpdateKey + "\" readonly=\"readonly\" size=\"20\" style=\"border:none\" >" + "<br/>" + "\n";
		retValue += "</td>";
		retValue += "</form>";
		return retValue;
	}

	/**
	 * テーブルタグのヘッダ部分を記述
	 */
	private String writeTableHead() {
		return "<table class=\"resultTable\">" + "\n";
	}

	/**
	 * テーブルレコード ( 検索結果側 ) のヘッダ部分を記述
	 * @return
	 */
	private String writeTRHead(int rowCount) {
		return "<tr class=\"resultTable\" id=\"row" + rowCount + "\">" + "\n";
	}

	private String writeTRHead4Edit(int rowCount) {
		return "<tr class=\"resultEdit\" id=\"row" + rowCount + "\">" + "\n";
	}

	/**
	 * テーブルタグのテール部分を記述
	 */
	private String writeTableTail() {
		return "</table>" + "\n";
	}

	/**
	 * テーブルレコード ( 検索結果側 ) のテール部分を記述
	 */
	private String writeTRTail() {
		return "</tr>" + "\n";
	}

}
