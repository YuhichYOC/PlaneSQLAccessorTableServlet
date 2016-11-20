package test07.eureka3.com;

import java.io.Serializable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import java.sql.SQLException;

public class SearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SELECT文 : TEST01 ( 郵便番号テーブル ) の検索
	 */
	private static String QueryStringSELECT_TEST01 = ""
			+ "SELECT                "
			+ "       PREF_CODE      "
			+ "     , PREF_NAME      "
			+ "     , PREF_UPDATEKEY "
			+ "FROM                  "
			+ "       test           ";
	/**
	 * WHERE 句
	 */
	private static String WhereStmt = ""
			+ "WHERE                 ";
	/**
	 * AND
	 */
	private static String AndStmt = ""
			+ "   AND                ";
	/**
	 * 検索条件 ( From )
	 */
	private static String ConditionWhereFrom = ""
			+ "       PREF_CODE >= ? ";
	/**
	 * 検索条件 ( To )
	 */
	private static String ConditionWhereTo = ""
			+ "       PREF_CODE <= ? ";
	/**
	 * 並び替え順序
	 */
	private static String OrderStmt = ""
			+ "ORDER BY              "
			+ "       PREF_CODE      "
			+ "     , PREF_NAME      "
			+ "     , PREF_UPDATEKEY ";

	/**
	 * 検索開始位置
	 */
	private String searchFrom = "";
	public void setSearchFrom(String value) throws Exception {
		this.searchFrom = value;
	}
	public String getSearchFrom() {
		return this.searchFrom;
	}

	/**
	 * 検索終了位置
	 */
	private String searchTo = "";
	public void setSearchTo(String value) throws Exception {
		this.searchTo = value;
	}
	public String getSearchTo() {
		return this.searchTo;
	}

	private HashMap<String, ArrayList<OneRow>> mySession;
	public HashMap<String, ArrayList<OneRow>> getSession() {
		return mySession;
	}

	/**
	 * 検索の実行・自身へ検索結果をセット
	 */
	public void search_TEST01() throws Exception {
		
		try {
			
			this.instantiateAccessor();
			
			this.myAccessor.setQueryString(this.getQueryString_SELECT_TEST01());
			this.myAccessor.createPreparedStatement();
			this.setParametersIntoQuery_SELECT_TEST01();
			ResultSet rs = this.myAccessor.executeSelectQuery();
			
			Cast c = new Cast();
			c.setMyResultSet(rs);
			c.setColumnCount(3);						// DIコンテナを使っていないのでここで列数を指定する必要がある
			c.doCast();
			
			this.destructionAccessor();
			
			this.mySession = new HashMap<String, ArrayList<OneRow>>();
			this.mySession.put("session", c.getMyTable());
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		} catch (Throwable e) {
			
			throw new Exception(e);
			
		}
		
	}

	/**
	 * TEST01 テーブルを検索するクエリ文字列の作成
	 */
	private String getQueryString_SELECT_TEST01() {
		
		String retVal = "";
		Boolean whereAttached = false;
		
		retVal += SearchBean.QueryStringSELECT_TEST01;
		if (!(this.searchFrom.equals(""))) {
			whereAttached = true;
			retVal += SearchBean.WhereStmt;
			retVal += SearchBean.ConditionWhereFrom;
		}
		if (!(this.searchTo.equals(""))) {
			if (whereAttached) {
				retVal += SearchBean.AndStmt;
				retVal += SearchBean.ConditionWhereTo;
			} else {
				whereAttached = true;
				retVal += SearchBean.WhereStmt;
				retVal += SearchBean.ConditionWhereTo;
			}
		}
		retVal += SearchBean.OrderStmt;
		return retVal;
		
	}
	private void setParametersIntoQuery_SELECT_TEST01() throws SQLException {
		
		int bindCount = 0;
		if (!(this.searchFrom.equals(""))) {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.searchFrom);
		}
		if (!(this.searchTo.equals(""))) {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.searchTo);
		}
		
	}

	/**
	 * MySQLAccessor インスタンス化 / GC
	 */
	private MySQLAccessor myAccessor;
	private void instantiateAccessor() throws SQLException {
		this.myAccessor = new MySQLAccessor();
		this.myAccessor.setDataSource("test");			// DIコンテナを使っていないのでここで接続情報を指定する必要がある
		this.myAccessor.setUserId("test");
		this.myAccessor.setPassword("password");
		this.myAccessor.createConnectionString();
		this.myAccessor.createConnection();
	}
	private void destructionAccessor() throws Throwable {
		this.myAccessor.finalize();
	}

}