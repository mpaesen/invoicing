package persistency;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.Properties;

public abstract class DBConnection {
	private Connection connection;
	private String url;
	private Properties info;
	private CallableStatement procedure;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Properties getInfo() {
		return info;
	}

	public void setInfo(Properties info) {
		this.info = info;
	}

	public CallableStatement getProcedure() {
		return procedure;
	}

	public void setProcedure(CallableStatement procedure) {
		this.procedure = procedure;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName(info.getProperty("driver"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, info);
		}
		return connection;
	}

	private CallableStatement callProc(String sql) throws SQLException {
		connection = getConnection();
		procedure = connection.prepareCall(sql);
		return procedure;
	}

	public ResultSet getResultset(String sql, Object[] args, int[] argTypes,
			ArgIO[] argsIO) {
		try {
			procedure = callProc(sql);

			for (int i = 0; i < args.length; i++) {
				prepareArguments(procedure, (i + 1), args[i], argTypes[i],
						argsIO[i]);
			}
			return procedure.executeQuery();
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return null;
	}
	public ResultSet getResultset(String sql) {
		try {
			procedure = callProc(sql);
			return procedure.executeQuery();
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return null;
	}

	public boolean createBusinessObject(String sql, Object[] args,
			int[] argTypes, ArgIO[] argsIO) {
		boolean success = false;
		try {
			procedure = callProc(sql);

			for (int i = 0; i < args.length; i++) {
				prepareArguments(procedure, (i + 1), args[i], argTypes[i],
						argsIO[i]);
			}
			success = procedure.execute();
			success = procedure.getBoolean(args.length);
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return success;
	}

	public boolean removeBusinessObject(String sql, Object[] args,
			int[] argTypes, ArgIO[] argsIO) {
		boolean success = false;
		try {
			procedure = callProc(sql);

			for (int i = 0; i < args.length; i++) {
				prepareArguments(procedure, (i + 1), args[i], argTypes[i],
						argsIO[i]);
			}
			success = procedure.execute();
			success = procedure.getBoolean(args.length);
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return success;
	}

	private void prepareArguments(CallableStatement proc, int sequence,
			Object value, int type, ArgIO io) {
		try {
			if (io == ArgIO.IN) {
				switch (type) {
				case Types.BOOLEAN: {
					proc.setBoolean(sequence, (value==null?null:((Boolean) value).booleanValue()));
					break;
				}
				case Types.CHAR: {
					proc.setString(sequence, (value==null?null:((String) value).trim()));
					break;
				}
				case Types.VARCHAR: {
					proc.setString(sequence, (value==null?null:((String) value).trim()));
					break;
				}
				case Types.INTEGER: {
					proc.setInt(sequence, (value==null?null:((Integer) value).intValue()));
					break;
				}
				case Types.DECIMAL: {
					proc.setBigDecimal(sequence, (value==null?null:((BigDecimal) value)));
					break;
				}
				case Types.DATE: {
					utilities.Date udate;
					Date date = null;
						udate = (value==null?null:(utilities.Date) value);
						date = new Date(new GregorianCalendar(udate.getJaar(),
								udate.getMaand(), udate.getDag()).getTimeInMillis());
					proc.setDate(sequence, date);
					break;
				}
				default:
					throw new SQLException("SQL Input Type " + io
							+ " not found");
				}
			} else {
				if (io == ArgIO.OUT) {
					switch (type) {
					case Types.BOOLEAN: {
						proc.registerOutParameter(sequence, Types.BOOLEAN);
						break;
					}
					case Types.CHAR: {
						proc.registerOutParameter(sequence, (Types.CHAR));
						break;
					}
					case Types.VARCHAR: {
						proc.registerOutParameter(sequence, (Types.VARCHAR));
						break;
					}
					case Types.INTEGER: {
						proc.registerOutParameter(sequence, Types.INTEGER);
						break;
					}
					case Types.DECIMAL: {
						proc.registerOutParameter(sequence, Types.DECIMAL);
						break;
					}
					case Types.DATE: {
						proc.registerOutParameter(sequence, Types.DATE);
						break;
					}
					default:
						throw new SQLException("SQL Output Type " + io
								+ " not found");
					}
				}
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
	}
}
