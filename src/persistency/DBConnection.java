package persistency;

import persistency.logging.BaseLogger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.Properties;

import static org.apache.log4j.lf5.LogLevel.CONFIG;

public abstract class DBConnection {
    private static String docPath;
    private static String logLevel;
    private Connection connection;
    private String url;
    private Properties info;
    private CallableStatement procedure;


    public static String getDocPath() {
        return docPath;
    }

    public static void setDocPath(final String docPath) {
        DBConnection.docPath = docPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(final Properties info) {
        this.info = info;
    }

    public CallableStatement getProcedure() {

        BaseLogger.logMsg(procedure.toString(), CONFIG);
        return procedure;
    }

    public void setProcedure(final CallableStatement procedure) {
        this.procedure = procedure;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName(info.getProperty("driver"));
            } catch (final ClassNotFoundException e) {
                BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
            }
            connection = DriverManager.getConnection(url, info);
        }
        return connection;
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    private CallableStatement callProc(final String sql) throws SQLException {
        connection = getConnection();
        procedure = connection.prepareCall(sql);
        return procedure;
    }

    public ResultSet getResultset(final String sql, final Object[] args, final int[] argTypes, final ArgIO[] argsIO) {
        try {
            procedure = callProc(sql);

            for (int i = 0; i < args.length; i++) {
                prepareArguments(procedure, (i + 1), args[i], argTypes[i], argsIO[i]);
            }
            return procedure.executeQuery();
        } catch (final SQLException e) {
            BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
        }
        return null;
    }

    public ResultSet getResultset(final String sql) {
        try {
            procedure = callProc(sql);
            return procedure.executeQuery();
        } catch (final SQLException e) {
            BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
        }
        return null;
    }

    public boolean createBusinessObject(final String sql, final Object[] args, final int[] argTypes, final ArgIO[] argsIO) {
        boolean success = false;
        try {
            procedure = callProc(sql);

            for (int i = 0; i < args.length; i++) {
                prepareArguments(procedure, (i + 1), args[i], argTypes[i], argsIO[i]);
            }
            success = procedure.execute();
            success = procedure.getBoolean(args.length);
        } catch (final SQLException e) {
            BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
        }
        return success;
    }

    public boolean removeBusinessObject(final String sql, final Object[] args, final int[] argTypes, final ArgIO[] argsIO) {
        boolean success = false;
        try {
            procedure = callProc(sql);

            for (int i = 0; i < args.length; i++) {
                prepareArguments(procedure, (i + 1), args[i], argTypes[i], argsIO[i]);
            }
            success = procedure.execute();
            success = (args.length <= 0 || procedure.getBoolean(args.length));
        } catch (final SQLException e) {
            BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
        }
        return success;
    }

    private void prepareArguments(final CallableStatement proc, final int sequence, final Object value, final int type, final ArgIO io) {
        try {
            if (io == ArgIO.IN) {
                switch (type) {
                    case Types.BOOLEAN: {
                        proc.setBoolean(sequence, (value == null ? null : ((Boolean) value).booleanValue()));
                        break;
                    }
                    case Types.CHAR: {
                        proc.setString(sequence, (value == null ? null : ((String) value).trim()));
                        break;
                    }
                    case Types.VARCHAR: {
                        proc.setString(sequence, (value == null ? null : ((String) value).trim()));
                        break;
                    }
                    case Types.INTEGER: {
                        proc.setInt(sequence, (value == null ? null : ((Integer) value).intValue()));
                        break;
                    }
                    case Types.DECIMAL: {
                        proc.setBigDecimal(sequence, (value == null ? null : ((BigDecimal) value)));
                        break;
                    }
                    case Types.DATE: {
                        utilities.Date udate;
                        Date date = null;
                        udate = (value == null ? null : (utilities.Date) value);
                        date = new Date(new GregorianCalendar(udate.getJaar(), udate.getMaand(), udate.getDag()).getTimeInMillis());
                        proc.setDate(sequence, date);
                        break;
                    }
                    default:
                        throw new SQLException("SQL Input Type " + io + " not found");
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
                            throw new SQLException("SQL Output Type " + io + " not found");
                    }
                }
            }
        } catch (final SQLException e) {
            BaseLogger.getLogger().logMsg(String.format(String.valueOf(e.getMessage())));
        }
    }
}
