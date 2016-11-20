package test07.eureka3.com; 

import java.sql.*;

import org.apache.commons.logging.*;

public class MySQLAccessor {

	/**
	 * ログ出力クラス
	 */
	public static Log log = LogFactory.getLog(MySQLAccessor.class);

	/**
	 * データベース接続ユーザーID
	 */
	private String UserId = "";
	public void setUserId(String value) {
		this.UserId = value;
	}

	/**
	 * データベース接続パスワード
	 */
	private String Password = "";
	public void setPassword(String value) {
		this.Password = value;
	}

	/**
	 * データベース名
	 */
	private String DataSource = "";
	public void setDataSource(String value) {
		this.DataSource = value;
	}

	/**
	 * 接続文字列
	 */
	private String connectionString = "";
	public void createConnectionString() {
		this.connectionString = "jdbc:mysql://localhost:3306/" + this.DataSource;
	}

	/**
	 * コネクション・コネクションオープンメソッド
	 */
	private Connection myConnection = null;
	public void createConnection() throws SQLException {
		try {
			this.myConnection = DriverManager.getConnection(this.connectionString, this.UserId, this.Password);
		} catch(SQLException e) {
			log.error("Error Occured :", e);
			throw new SQLException(e);
		}
	}

	/**
	 * コンストラクタ
	 */
	public MySQLAccessor() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
						
		} catch(ClassNotFoundException e) {
			
			log.error("Error Occured : ", e);
			
		}
		
	}

	/**
	 * GC時メソッド・コネクション廃棄
	 */
	private void destruction() throws Throwable {
		
		if(this.myConnection != null) {
			
			this.myConnection.close();
			
		}
		
	}

	/**
	 * GC時
	 */
	@Override
	protected void finalize() throws Throwable {
		
		try {
			
			super.finalize();
			
		} finally {
			
			destruction();
			
		}
		
	}

	/**
	 * クエリ文字列・外部ファイルによる設定
	 */
	private String QueryString = "";
	public void setQueryString(String value) {
		this.QueryString = value;
	}

	/**
	 * ステートメントの生成
	 */
	private PreparedStatement ps = null;
	public void createPreparedStatement() {
		try {
			ps = null;
			this.ps = this.myConnection.prepareStatement(this.QueryString);
		} catch (SQLException e) {
			log.error("Error Occured :", e);
		}
	}

	/**
	 * バインドパラメータのセット
	 * @param index : PrepareStatement へセットするパラメータの位置インデックス
	 * @param value : PrepareStatement へセットするパラメータの値
	 * @throws SQLException
	 */
	public void setParameterIntoQuery(int index, String value) throws SQLException {
		ps.setString(index, value);
	}

	/**
	 * SELECT文発行・結果セットの取得
	 */
	public ResultSet executeSelectQuery() {
		
		ResultSet retSet = null;
		
		try {
			
			retSet = ps.executeQuery();
			
		} catch(SQLException e) {
			
			log.error("Error Occured : ", e);
			
		}
		return retSet;
		
	}

	/**
	 * PrepareStatement による UPDATE の実行
	 */
	public int executeUpdate() {
		
		int retVal = 0;
		
		try {
			
			retVal = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			log.error("Error Occured : ", e);
		}
		return retVal;
		
	}

}
