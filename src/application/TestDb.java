package application;

import java.sql.ResultSet;

public class TestDb {
	public static void main(String[] args) {
		try {
			DBManager dbm = new DBManager();
			ResultSet rs =dbm.selectQuery("SELECT * FROM SALARIE;");
			while(rs.next()) {
				System.out.println(rs.getString("MATRICULE")+" "+rs.getString("NOM")+" "+rs.getString("PRENOM"));
				
				//System.out.println(rs.getObject(0));
					
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
