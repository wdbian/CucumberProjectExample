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
				       "where EmailAddress = '?')";
		
		String result = null;
		try (Connection conn = SQLConnection.getConnection();
			 PreparedStatement statement = conn.prepareStatement(query)) 
		{
			statement.setString(1, customerEmail);
			try (ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					result = rs.getString("FirstName") + " " + rs.getString("LastName");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLConnection.removeConnection();
		}
		return result;
	}
	
	public static ResultSet queryRetail(String locationIDs) {
		String[] locationIDArray = locationIDs.split(",");
		StringBuilder locationPlaceHolder = new StringBuilder();
		for (int i = 0; i < locationIDArray.length; i++) {
			locationPlaceHolder.append("?");
			if (i < locationIDArray.length - 1) {
				locationPlaceHolder.append(",");
			}
		}
		
		String query = "select RetailLocationID, RetailLocationName \n" +
				       "from RetailLocations \n" +
				       "where RetailLocationID in (" + locationPlaceHolder.toString() + ")";
		
		ResultSet rs = null;
		try (Connection conn = SQLConnection.getConnection();
			 PreparedStatement statement = conn.prepareStatement(query)) 
		{
			int startIndex = 1;
			for (String locationId : locationIDArray) {
				statement.setInt(startIndex, Integer.parseInt(locationId.trim()));
			}
			rs = statement.executeQuery();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
