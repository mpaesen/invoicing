package persistency;

import model.Number;
import model.*;
import org.apache.log4j.Level;
import persistency.logging.BaseLogger;
import utilities.Date;
import utilities.DatumException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBFacade {
    private static DBConnection connection;
    private ResultSet resultSet;

    public DBFacade() {
        if (connection == null) {
            connection = new RDBConnection();
        }
    }

    public Collection<Business> getResult(BusinessTypeEnum business,
                                          String sql, Object[] args, int[] argTypes, ArgIO[] argsIO) {
        List<Business> list = new ArrayList<Business>();

        Business object = null;

        try {
            resultSet = connection.getResultset(sql, args, argTypes, argsIO);
            // ResultSetMetaData rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                object = createBusinessObject(business, resultSet);
                list.add(object);
            }
        } catch (SQLException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }

        return list;
    }

    public Collection<Business> getResult(BusinessTypeEnum business, String sql) {
        List<Business> list = new ArrayList<Business>();

        Business object = null;

        try {
            resultSet = connection.getResultset(sql);

            while (resultSet.next()) {
                object = createBusinessObject(business, resultSet);
                list.add(object);
            }
        } catch (SQLException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }

        return list;
    }

    /**
     * Removes one business object
     *
     * @param sql
     * @param args
     * @param argTypes
     * @param argsIO
     * @return
     */
    public boolean removeBusinessObject(String sql, Object[] args,
                                        int[] argTypes, ArgIO[] argsIO) {
        return connection.removeBusinessObject(sql, args, argTypes, argsIO);
    }

    /**
     * create a business object of type BusinessTypeEnum
     *
     * @param business
     * @param result
     * @return
     */
    private Business createBusinessObject(BusinessTypeEnum business,
                                          ResultSet result) {
        Business object = null;

        try {
            object = createBusinessObject(business, result, object);
        } catch (SQLException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }
        return object;
    }

    /**
     * Creates a business object of typ BusinessTypeEnum
     *
     * @param business
     * @param result
     * @param object
     * @return Business
     * @throws SQLException
     */
    private Business createBusinessObject(BusinessTypeEnum business,
                                          ResultSet result, Business object) throws SQLException {
        switch (business.getSeq()) {
            case 0: {// Customer
                object = new Customer(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getString(8),
                        result.getString(9), result.getString(10),
                        result.getString(11), result.getString(12),
                        result.getString(13), result.getString(14),
                        result.getString(15), result.getString(16),
                        result.getBoolean(17));
                break;
            }// Customer
            case 1: {// Contact
                object = new Contact(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getString(8),
                        result.getString(9), result.getString(10),
                        result.getBoolean(11));
                break;
            }// Contact
            case 2: {// Address
                object = new Address(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                        result.getString(7), result.getString(8),
                        result.getString(9), result.getBoolean(10));
                break;
            }// Address
            case 3: {// Product
                object = new Product(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getBoolean(6));
                break;
            }// Product
            case 4: {// Price

                try {
                    object = new Price(result.getString(1), result.getString(2),
                            new Date(result.getDate(3).getTime()),
                            result.getBigDecimal(4), result.getString(5),
                            result.getBoolean(6));
                } catch (DatumException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }// Price
            case 5: {// Quote
                try {
                    object = new Quote(result.getString(1), result.getString(2),
                            result.getString(3), new Date(result.getDate(4)
                            .getTime()), result.getString(5), new Date(
                            result.getDate(6).getTime()),
                            result.getString(7), new Date(result.getDate(8)
                            .getTime()), result.getString(9),
                            result.getString(10), result.getBoolean(11),
                            result.getString(12), result.getBoolean(13));
                } catch (DatumException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }// Quote
            case 6: {// QuoteDetail
                object = new QuoteDetail(result.getString(1), new Integer(
                        String.valueOf(result.getInt(2))), result.getString(3),
                        result.getBigDecimal(4), result.getString(5),
                        result.getBigDecimal(6), result.getString(7), true);
                break;
            }// QuoteDetail
            case 7: {// Invoice
                try {
                    object = new Invoice(result.getString(1), result.getString(2),
                            result.getString(3), result.getString(4), new Date(
                            result.getDate(5).getTime()),
                            result.getString(6), new Date(result.getDate(7)
                            .getTime()), result.getString(8), new Date(
                            result.getDate(9).getTime()), new Date(result
                            .getDate(10).getTime()), result.getString(11),
                            result.getString(12), result.getBoolean(13),
                            result.getString(14), result.getBoolean(15));
                } catch (DatumException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }// Invoice
            case 8: {// InvoiceDetail
                object = new InvoiceDetail(result.getString(1), new Integer(
                        String.valueOf(result.getInt(2))), result.getString(3),
                        result.getBigDecimal(4), result.getString(5),
                        result.getBigDecimal(6), result.getString(7),
                        result.getString(8), true);
                break;
            }// InvoiceDetail
            case 9: {// Number
                object = new Number(result.getString(1), new Integer(
                        String.valueOf(result.getInt(2))), result.getString(3),
                        result.getString(4), true);
                break;
            }// Number
            case 10: {// CodeHeader
                object = new CodeHeader(result.getString(1), result.getString(2),
                        new Integer(String.valueOf(result.getInt(3))), true);
                break;
            }// CodeHeader
            case 11: {// CodeDetail
                object = new CodeDetail(result.getString(1), result.getString(2),
                        result.getString(3), true);
                break;
            }// Number
            case 12: {// QuoteView

                try {
                    object = new QuoteView(new Quote(result.getString(3),
                            result.getString(4), result.getString(5), new Date(
                            result.getDate(6).getTime()),
                            result.getString(7), new Date(result.getDate(8)
                            .getTime()), result.getString(9), new Date(
                            result.getDate(10).getTime()),
                            result.getString(11), result.getString(12),
                            result.getBoolean(13), result.getString(14),
                            result.getBoolean(15)), result.getString(1),
                            result.getString(2));
                } catch (DatumException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }// QuoteView
            case 13: {// InvoiceView

                try {
                    object = new InvoiceView(new Invoice(
                            result.getString(4), result.getString(5),
                            result.getString(6), result.getString(7), new Date(
                            result.getDate(8).getTime()),
                            result.getString(9), new Date(result.getDate(10)
                            .getTime()), result.getString(11), new Date(
                            result.getDate(12).getTime()), new Date(result
                            .getDate(13).getTime()), result.getString(14),
                            result.getString(15), result.getBoolean(16),
                            result.getString(17), result.getBoolean(18)),
                            result.getString(1), result.getString(2),
                            result.getString(3));
                } catch (DatumException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }// QuoteView
            case 14: {// BigDecimal
                try {
                    object = new Amount(result.getBigDecimal(1));
                } catch (NumberFormatException e) {
                    BaseLogger.logMsg(e.getMessage());
                }
                break;
            }
            default: {
                break;
            }

        }
        BaseLogger.logMsg(object.toString(), Level.TRACE);
        return object;
    }

    public boolean createObject(String sql, Object[] args, int[] argTypes,
                                ArgIO[] argsIO) {
        boolean success = false;

        success = connection.createBusinessObject(sql, args, argTypes, argsIO);
        return success;
    }
}
