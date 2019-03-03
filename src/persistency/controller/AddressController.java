
package persistency.controller;

import model.Address;
import model.Business;
import model.BusinessTypeEnum;
import persistency.ArgIO;
import persistency.DBFacade;
import persistency.logging.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author Mathy
 * 
 */
public class AddressController {
    private static DBFacade facade;
    private static StringBuilder callableStatement;

    /**
     * reads one Address by id
     *
     * @param id
     * @return
     */
    public static Address getAddress(String id) {
        Address reference = null;
        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 1;
        callableStatement.append("{call readOneAddressByID(?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                args[i] = id;
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
            } catch (Exception e) {
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
                //e.printStackTrace();
            }
        }
        Collection<Business> list = facade.getResult(BusinessTypeEnum.ADDRESS,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            Iterator<Business> it = list.iterator();
            while (it.hasNext()) {
                reference = (Address) it.next();
            }
        }
        return reference;
    }

    /**
     * Read all addresses for a specific customer
     *
     * @param filter
     * @return
     */
    public static Collection<Business> getAddresses(String[] filter) {
        Collection<Business> list;

        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 3;
        callableStatement.append("{call readAllAddress(?,?,?)}");
        Object[] args = {"", "", true};
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {

                    case 0: {
                        // in id char(15),
                        if (!filter[i].equals("")) {
                            args[i] = filter[i];
                        }
                        argsType[i] = java.sql.Types.CHAR;
                        argsIO[i] = ArgIO.IN;
                        break;
                    }
                    case 1: {
                        // in atype char(5),
                        if (!filter[i].equals("")) {
                            args[i] = filter[i];
                        }
                        argsType[i] = java.sql.Types.CHAR;
                        argsIO[i] = ArgIO.IN;
                        break;

                    }
                    case 2: {
                        // active success boolean)
                        if (filter[i].equals("false")) {
                            args[i] = false;
                        }
                        argsType[i] = java.sql.Types.BOOLEAN;
                        argsIO[i] = ArgIO.IN;
                        break;
                    }
                    default:
                        throw new Exception(
                                "Error while creating SP readAllAddress()");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list = facade.getResult(BusinessTypeEnum.ADDRESS,
                callableStatement.toString(), args, argsType, argsIO);
        return list;
    }

    /**
     * @return TreeMap
     */
    public static TreeMap<String, String> getAddressDetails(String[] filter) {
        TreeMap<String, String> addresses;
        Address detail;
        Collection<Business> list = AddressController.getAddresses(filter);
        addresses = new TreeMap<String, String>();
        Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            detail = (Address) it.next();
            addresses.put(detail.toString(), detail.getIdAddress());
            i++;
        }
        return addresses;
    }

    /**
     * prepare parameters for SP createAddress()
     *
     * @param args
     * @param argsType
     * @param argsIO
     * @param i
     * @throws Exception
     */
    public static void prepareAddress(Address address, Object[] args,
                                      int[] argsType, ArgIO[] argsIO, int i) throws Exception {
        switch (i) {
            case 0: {
                // IN id char(15),
                args[i] = address.getIdAddress();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 1: {
                // in idcus or id cont char(15),
                args[i] = address.getAddRef();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 2: {
                // in street char(20),
                args[i] = address.getAddStreet();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 3: {
                // in number char(5),
                args[i] = address.getAddNumber();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 4: {
                // in box char(5),
                args[i] = address.getAddBox();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 5: {
                // in zip char(5),
                args[i] = address.getAddZip();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 6: {
                // in city char(20),
                args[i] = address.getAddCity();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 7: {
                // in Country char(3),
                args[i] = address.getAddCountry();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 8: {
                // in type char(5),
                args[i] = address.getAddType();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 9: {
                // out success boolean)
                args[i] = null;
                argsType[i] = java.sql.Types.BOOLEAN;
                argsIO[i] = ArgIO.OUT;
                break;
            }
            default:
                throw new Exception("Error while creating SP createAddress");
        }
    }

    /**
     * Create a specific address
     *
     * @param address
     * @return
     */
    public static boolean createAddress(Address address) {
        int LENGTH = 10;
        callableStatement = new StringBuilder();
        callableStatement
                .append("{call createAddress(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                AddressController.prepareAddress(address, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
//                System.err.print(address);
                Logger.getLogger().logMsg(String.format(callableStatement + address.toString()));
                //e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));

    }

    /**
     * Create a specific address
     *
     * @param address
     * @return
     */
    public static boolean updateAddress(Address address) {
        int LENGTH = 10;
        callableStatement = new StringBuilder();
        callableStatement
                .append("{call updateAddress(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                AddressController.prepareAddress(address, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
//                System.err.print(address);
                Logger.getLogger().logMsg(String.format(callableStatement + address.toString()));
                //e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));

    }

    /**
     * Remove a specific contact
     *
     * @return
     */
    public static boolean removeAddress(String id) {

        int LENGTH = 2;
        callableStatement = new StringBuilder();
        callableStatement.append("{call removeAddress( ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {

                    case 0: {
                        // in id char(15),
                        args[i] = id;
                        argsType[i] = java.sql.Types.CHAR;
                        argsIO[i] = ArgIO.IN;
                        break;
                    }
                    case 1: {
                        // active success boolean)
                        args[i] = false;
                        argsType[i] = java.sql.Types.BOOLEAN;
                        argsIO[i] = ArgIO.OUT;
                        break;
                    }
                    default:
                        throw new Exception(
                                "Error while creating SP removeAddress()");
                }
            } catch (Exception e) {
//                System.err.print(address);
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
                //e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));
    }

}
