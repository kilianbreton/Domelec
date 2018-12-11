package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exceptions.DbmIOException;

public class DBManager {
	private final String DRIVER ="org.firebirdsql.jdbc.FBDriver";
	private final String PRE_URL = "jdbc:firebirdsql://localhost/";
	private final String POST_URL = "?wireCrypt=DISABLED&localEncoding=ISO8859_1";
	
	private String url ="jdbc:firebirdsql://localhost/D:/Programmation/DOMELEC.FDB?wireCrypt=DISABLED&localEncoding=ISO8859_1";
	private String user="sysdba";
	private String password ="root";
	private Statement st = null;
	private Connection cnx = null;
	private ResultSet rs = null;
	
	public DBManager(String path, String user, String password) throws Exception {
		this.user = user;
		this.password = password;
		this.url = PRE_URL + path + POST_URL;
		commonBuilder();
	}
	
	public DBManager(String user, String password) throws Exception {
		this.user = user;
		this.password = password;
		commonBuilder();
	}
	
	
	public DBManager() throws Exception {
		try {
			commonBuilder();
		}catch(Exception e) {
			throw new DbmIOException(url);
		}
	}
	
	
	
	public ResultSet selectQuery(String sql) throws SQLException {	
		st = cnx.createStatement();	
		rs = st.executeQuery(sql);
		return rs;
	}
	
	
	public void insertQuery(String sql) throws SQLException {
		st = cnx.createStatement();
		st.execute(sql);
	}
	
	public boolean closeConnection() throws SQLException {
		boolean ret = true;
		st.close();
		return ret;
	}
	
	private void commonBuilder() throws Exception {
		
			Class.forName(DRIVER).newInstance();
			cnx = DriverManager.getConnection(url, user, password);
		
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPRE_URL() {
		return PRE_URL;
	}

	public String getPOST_URL() {
		return POST_URL;
	}

	
}