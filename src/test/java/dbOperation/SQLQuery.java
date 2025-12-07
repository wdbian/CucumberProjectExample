package dbOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQuery {
	
	public static String queryCustomerInfo(String customerEmail){
		String query = "select FirstName, LastName \n" +
		               "from Customers \n" +
				       "where CustomerId in ( \n\t" +
		               "select CustomerId from CustomerEmail \n" +
				       "where EmailAddress = '" + customerEmail + "')";
		
		String result = null;
		try (Connection conn = SQLConnection.getConnection();
			PreparedStatement statment = conn.prepareStatement(query);
			ResultSet rs = statment.executeQuery();) {
			if(rs.next()) {
				result = rs.getString("FirstName") + " " + rs.getString("LastName");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.removeConnection();
		}
		return result;
	}
	
	
}
