//TestJDBC

package persistency;
import java.sql.*;

public class TableMetaDataProps {
	private String columns[];
	private String columnTypes[];
	private Connection con;

	private PreparedStatement pstmt;
	private ResultSetMetaData rmd;
	private ResultSet rs;

	private int size;



	public String column(int i) throws SQLException {
		String col = "";
		if (columnTypes[i - 1].equals("CHAR"))
			col = rs.getString(i);
		if (columnTypes[i - 1].equals("DECIMAL"))
			col = rs.getBigDecimal(i).toString();
		if (columnTypes[i - 1].equals("INTEGER"))
			col = Integer.toString(rs.getInt(i));
		if (columnTypes[i - 1].equals("NUMERIC"))
			col = rs.getString(i);
		if (columnTypes[i - 1].equals("TIMESTAMP"))
			col = rs.getString(i);
		if (col == null)
			col = " ";
		return col;
	}

	private String columnHeaders() throws SQLException {
		StringBuffer kolommen = new StringBuffer();
		for (int i = 0; i < rmd.getColumnCount(); i++) {
			kolommen.append(rmd.getColumnName(i + 1));

			getSize(i);
			for (int j = 0;
				j < size - rmd.getColumnName(i + 1).trim().length();
				j++) {
				kolommen.append(" ");
			}
			columns[i] = rmd.getColumnName(i + 1);
			columnTypes[i] = rmd.getColumnTypeName(i + 1);
		}

		return kolommen.toString();

	}

	private String columnTypes() throws SQLException {
		StringBuffer myTypes = new StringBuffer();
		for (int i = 0; i < rmd.getColumnCount(); i++) {
			myTypes.append(columnTypes[i]);

			getSize(i);
			for (int j = 0;
				j < size - rmd.getColumnTypeName(i + 1).trim().length();
				j++) {
				myTypes.append(" ");
			}
		}
		return myTypes.toString();
	}

	private void getSize(int i) throws SQLException {
		size =
			Math.max(
				rmd.getColumnDisplaySize(i + 1),
				rmd.getColumnName(i + 1).length())
				+ 1;
	}

	private void printRecordDetails() throws SQLException {

		while (rs.next()) {
			System.out.print(recordDetails());
		}

	}

	private String recordDetails() throws SQLException {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < rmd.getColumnCount(); i++) {
			output.append(column(i + 1).trim());
			getSize(i);
			for (int j = 0; j < size - column(i + 1).trim().length(); j++) {
				output.append(" ");
			}
		}
		output.append("\n");
		return output.toString();
	}

	private void close() {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
