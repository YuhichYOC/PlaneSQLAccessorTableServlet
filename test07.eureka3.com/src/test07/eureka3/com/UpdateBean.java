package test07.eureka3.com;

import java.io.Serializable;
import java.sql.SQLException;

public class UpdateBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * UPDATE文 : TEST01 ( 郵便番号テーブル ) の更新
	 */
	private static String QueryStringUpdate_TEST01 = ""
			+ "UPDATE                    "
			+ "       test               "
			+ "SET                       "
			+ "       PREF_CODE = ?      "
			+ "     , PREF_NAME = ?      ";
	/**
	 * WHERE 句
	 */
	private static String WhereStmt = ""
			+ "WHERE                     ";
	/**
	 * AND
	 */
	private static String AndStmt = ""
			+ "   AND                    ";
	/**
	 * 更新条件 ( PREF_CODE )
	 */
	private static String ConditionPrefCode = ""
			+ "       PREF_CODE = ?      ";
	/**
	 * 更新条件 ( PREF_NAME )
	 */
	private static String ConditionPrefName = ""
			+ "       PREF_NAME = ?      ";
	/**
	 * 更新条件 ( PREF_UPDATEKEY )
	 */
	private static String ConditionUpdateKey = ""
			+ "       PREF_UPDATEKEY = ? ";

	/**
	 * 更新キー ( PREF_CODE ) のセット
	 */
	private String updateKeyPrefCode = "";
	public void setUpdateKeyPrefCode(String value) {
		this.updateKeyPrefCode = value;
	}

	/**
	 * 更新キー ( PREF_NAME ) のセット
	 */
	private String updateKeyPrefName = "";
	public void setUpdateKeyPrefName(String value) {
		this.updateKeyPrefName = value;
	}

	/**
	 * 更新キー ( PREF_UPDATEKEY : すべての行で一意のキー ) のセット
	 */
	private String updateKeyAllCode = "";
	public void setUpdateKeyAllCode(String value) {
		this.updateKeyAllCode = value;
	}

	/**
	 * 更新値 ( PREF_CODE ) のセット
	 */
	private String updateValuePrefCode = "";
	public void setUpdateValuePrefCode(String value) {
		this.updateValuePrefCode = value;
	}

	/**
	 * 更新値 ( PREF_NAME ) のセット
	 */
	private String updateValuePrefName = "";
	public void setUpdateValuePrefName(String value) {
		this.updateValuePrefName = value;
	}

	/**
	 * 更新の実行
	 */
	public void update_TEST01() throws Exception {
		
		try {
			
			this.instantiateAccessor();
			
			this.myAccessor.setQueryString(this.getQueryString_UPDATE_TEST01());
			this.myAccessor.createPreparedStatement();
			this.setParametersIntoQuery_UPDATE_TEST01();
			this.myAccessor.executeUpdate();
			
			this.destructionAccessor();
			
		} catch (SQLException e) {
			
			throw new Exception(e);
			
		} catch (Throwable e) {
			
			throw new Exception(e);
			
		}
		
	}

	/**
	 * TEST01 テーブルを更新するクエリ文字列の作成
	 */
	private String getQueryString_UPDATE_TEST01() {
		
		String retVal = "";
		
		retVal += UpdateBean.QueryStringUpdate_TEST01;
		retVal += UpdateBean.WhereStmt;
		retVal += UpdateBean.ConditionUpdateKey;
		return retVal;
		
	}
	private void setParametersIntoQuery_UPDATE_TEST01() throws SQLException {
		
		int bindCount = 0;
		// 更新値のセット : PREF_CODDE ( 指定なしの時は「変更なし」として更新前 ( つまり検索キー ) をセット
		if (!(this.updateValuePrefCode.equals(""))) {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.updateValuePrefCode);
		} else {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.updateKeyPrefCode);
		}
		// 更新値のセット : PREF_NAME ( 指定なしの時は「変更なし」として更新前 ( つまり検索キー ) をセット
		if (!(this.updateValuePrefName.equals(""))) {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.updateValuePrefName);
		} else {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.updateKeyPrefName);
		}
		// 検索キーのセット : PREF_UPDATEKEY
		if (!(this.updateKeyAllCode.equals(""))) {
			bindCount += 1;
			this.myAccessor.setParameterIntoQuery(bindCount, this.updateKeyAllCode);
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