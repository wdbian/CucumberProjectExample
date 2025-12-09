package utility;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

public class DBUtil {
	
	public static String saveResultSetToFile(ResultSet rs, String fileName) {
		String result = null;
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columns = rs.getMetaData().getColumnCount();
			
			Path dirPath = Path.of(System.getProperty("user.dir"), ConfigUtil.getProperty("folderNameForDatabaseResult"));
			Path filePath = dirPath.resolve(fileName + "_" + Thread.currentThread().getName() + "_" + WebUtil.getTimeStamp() + ".csv");
			if (! dirPath.toFile().exists()) {
				Files.createDirectories(dirPath);
			}
			
			File file = new File(filePath.toString());
			FileWriter fw = new FileWriter(file);
			
			for (int i = 1; i <= columns; i++) {
				fw.append(rsMetaData.getColumnName(i));
				if (i < columns) {
					fw.append(",");
				} else {
					fw.append("\n");
				}
			}
			
			while (rs.next()) {
				for (int i = 1; i <= columns; i++) {
					Object obj = null;
					switch (rsMetaData.getColumnType(i)) {
						case Types.INTEGER:
							obj = rs.getInt(i);
							break;
						case Types.VARCHAR:
						case Types.CHAR:
						case Types.LONGVARCHAR:
							obj = rs.getString(i);
							break;
						case Types.FLOAT:
							obj = rs.getFloat(i);
							break;
						case Types.DOUBLE:
							obj = rs.getDouble(i);
							break;
						case Types.DATE:
							obj = rs.getDate(i);
							break;
						case Types.BOOLEAN:
							obj = rs.getBoolean(i);
							break;
						case Types.TIMESTAMP:
							obj = rs.getTimestamp(i);
							break;
						default:
							obj = rs.getObject(i);
					}
					fw.append(obj != null ? obj.toString() : "NULL");
					if (i < columns) {
						fw.append(",");
					} else {
						fw.append("\n");
					}
				}
			}
			fw.close();
			result = filePath.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
