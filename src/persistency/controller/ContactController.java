/**
 * 
 */
package persistency.controller;

import model.Business;
import model.BusinessTypeEnum;
import model.Contact;
import persistency.ArgIO;
import persistency.DBFacade;
import persistency.logging.BaseLogger;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Mathy
 * 
 */
public class ContactController {
    private static DBFacade facade;
    private static StringBuilder callableStatement;

    /**
     * Prepare parameters for SP createContact()
     *
     * @param args
     * @param argsType
     * @param argsIO
     * @param i
     * @throws Exception
     */
    public static void prepareContact(Contact contact, Object[] args,
                                      int[] argsType, ArgIO[] argsIO, int i) throws Exception {
        switch (i) {
            case 0: {
                // IN id char(15),
                args[i] = contact.getIdContact();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 1: {
                // in idcus char(15),
                args[i] = contact.getIdCus();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 2: {
                // in lname char(30),
                args[i] = contact.getConLName();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 3: {
                // in fname char(30),
                args[i] = contact.getConFName();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 4: {
                // in phone char(20),
                args[i] = contact.getConPhone();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 5: {
                // in Mobile char(20),
                args[i] = contact.getConMobile();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 6: {
                // in email char(30),
                args[i] = contact.getConEMail();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 7: {
                // in title char(15),
                args[i] = contact.getConTitle();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 8: {
                // in pref char(5),
                args[i] = contact.getConPref();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 9: {
                // in lng char(5),
                args[i] = contact.getConLang();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 10: {
                // out success boolean)
                args[i] = null;
                argsType[i] = java.sql.Types.BOOLEAN;
                argsIO[i] = ArgIO.OUT;
                break;
            }
            default:
                throw new Exception("Error while creating SP createContact");
        }
    }

    /**
     * @param contact
     * @return
     */
    public static boolean createContact(Contact contact) {

        int LENGTH = 11;
        callableStatement = new StringBuilder();
        callableStatement
                .append("{call createContact(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                ContactController.prepareContact(contact, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
                //  System.err.print(contact);
                BaseLogger.getLogger().logMsg(String.format(callableStatement + contact.toString()));
                //e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));

    }

    /**
     * @param contact
     * @return
     */
    public static boolean updateContact(Contact contact) {

        int LENGTH = 11;
        callableStatement = new StringBuilder();
        callableStatement
                .append("{call updateContact(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                ContactController.prepareContact(contact, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
                //  System.err.print(contact);
                BaseLogger.getLogger().logMsg(String.format(callableStatement + contact.toString()));
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
    public static boolean removeContact(String id) {

        int LENGTH = 2;
        callableStatement = new StringBuilder();
        callableStatement.append("{call removeContact( ?, ?)}");
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
                                "Error while creating SP removeContact()");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));
    }

    /**
     * @param filter
     * @return
     */
    public static Collection<Business> getContacts(String[] filter) {
        Collection<Business> list;

        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 2;
        callableStatement.append("{call readAllContact(?,?)}");
        Object[] args = {"", true};
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
                                "Error while creating SP readAllContact()");
                }
            } catch (Exception e) {
                //  System.err.print(contact);
                BaseLogger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
                //e.printStackTrace();
            }
        }
        list = facade.getResult(BusinessTypeEnum.CONTACT,
                callableStatement.toString(), args, argsType, argsIO);
        return list;
    }

    /**
     * Read one contact with SP readOneContact()
     *
     * @param id
     * @return
     */
    public static Contact getContact(String id) {
        Contact reference = null;
        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 1;
        callableStatement.append("{call readOneContact(?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                args[i] = id;
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collection<Business> list = facade.getResult(BusinessTypeEnum.CONTACT,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            Iterator<Business> it = list.iterator();
            while (it.hasNext()) {
                reference = (Contact) it.next();
            }
        }
        return reference;
    }

}
