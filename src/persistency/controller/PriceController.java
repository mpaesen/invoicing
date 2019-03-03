/**
 * 
 */
package persistency.controller;

//import java.sql.Date;

import model.Business;
import model.BusinessTypeEnum;
import model.Price;
import persistency.ArgIO;
import persistency.DBFacade;
import persistency.logging.Logger;
import utilities.Date;
import utilities.DatumException;

import java.util.*;

/**
 * @author Mathy
 * 
 */
public class PriceController {
    private static DBFacade facade;
    private static StringBuilder callableStatement;

    /**
     * Get all price
     *
     * @param id
     * @return
     */
    public static Price getOneProductPrice(String id) {
        Price reference = null;
        DBFacade facade = new DBFacade();
        StringBuilder callableStatement = new StringBuilder();

        int LENGTH = 1;
        callableStatement.append("{call readOneProductPrice(?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {
                    case 0: {
                        args[i] = id;
                        argsType[i] = java.sql.Types.CHAR;
                        break;
                    }
                    default:
                        throw new Exception("Parameter" + i + " not vallid");
                }
                argsIO[i] = ArgIO.IN;
            } catch (Exception e) {
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
            }
        }
        Collection<Business> list = facade.getResult(BusinessTypeEnum.PRICE,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            Iterator<Business> it = list.iterator();
            if (it.hasNext()) {
                reference = (Price) it.next();
            }
        }
        return reference;
    }

    /**
     * @param id
     * @return
     */
    public static boolean removeOneProductPrice(String id) {
        DBFacade facade = new DBFacade();
        StringBuilder callableStatement = new StringBuilder();

        int LENGTH = 2;
        callableStatement.append("{call removeOneProductPrice(?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {
                    case 0: {
                        args[i] = id;
                        argsType[i] = java.sql.Types.CHAR;
                        argsIO[i] = ArgIO.IN;
                        break;
                    }
                    case 1: {
                        // out success boolean)
                        args[i] = null;
                        argsType[i] = java.sql.Types.BOOLEAN;
                        argsIO[i] = ArgIO.OUT;
                        break;
                    }
                    default:
                        throw new Exception("Parameter" + i + " not vallid");
                }
            } catch (Exception e) {
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
            }
        }
        return facade.removeBusinessObject(callableStatement.toString(), args,
                argsType, argsIO);
    }

    /**
     * Get all price
     *
     * @param id
     * @return
     */

    public static List<Price> getAllValidProductPrice(String id) {
        List<Price> priceList = null;

        try {
            Date always = new Date();

            always.setDatum(31, 11, 2999);
            priceList = getAllValidProductPrice(id, always);
        } catch (DatumException e) {
            Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
        }
        return priceList;
    }

    public static List<Price> getAllValidProductPrice(String id, Date date) {
        List<Price> priceList = null;
        Price reference = null;
        DBFacade facade = new DBFacade();
        StringBuilder callableStatement = new StringBuilder();

        int LENGTH = 2;
        callableStatement.append("{call readProductPriceFromDate(?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {
                    case 0: {
                        args[i] = id;
                        argsType[i] = java.sql.Types.CHAR;
                        break;
                    }
                    case 1: {
                        args[i] = date;
                        argsType[i] = java.sql.Types.DATE;
                        break;
                    }
                    default:
                        throw new Exception("Parameter" + i + " not vallid");
                }
                argsIO[i] = ArgIO.IN;
            } catch (Exception e) {
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
            }
        }
        Collection<Business> list = facade.getResult(BusinessTypeEnum.PRICE,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            priceList = new LinkedList<Price>();
            Iterator<Business> it = list.iterator();
            while (it.hasNext()) {
                reference = (Price) it.next();
                priceList.add(reference);
            }
        }
        return priceList;
    }

    /**
     * Prepare parameters for SP createPrice()
     *
     * @param args
     * @param argsType
     * @param argsIO
     * @param i
     * @throws Exception
     */
    public static void preparePrice(Price price, Object[] args, int[] argsType,
                                    ArgIO[] argsIO, int i) throws Exception {
        switch (i) {
            case 0: {
                // IN id char(15),
                args[i] = price.getIdPrice();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 1: {
                // in prodId char(15),
                args[i] = price.getPriProdid();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 2: {
                // in from date(),
                args[i] = price.getPrifrom();
                argsType[i] = java.sql.Types.DATE;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 3: {
                // in price per unit char(30),
                args[i] = price.getPriUnit();
                argsType[i] = java.sql.Types.DECIMAL;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 4: {
                // in unitofmeasure char(20),
                args[i] = price.getPriMeasure();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }

            case 5: {
                // out success boolean)
                args[i] = null;
                argsType[i] = java.sql.Types.BOOLEAN;
                argsIO[i] = ArgIO.OUT;
                break;
            }
            default:
                throw new Exception("Error while creating SP createPrice");
        }
    }

    /**
     * Create one concrete price for a product
     *
     * @param price
     * @return
     */
    public static boolean createPrice(Price price) {

        int LENGTH = 6;
        facade = new DBFacade();
        callableStatement = new StringBuilder();
        callableStatement.append("{call createPrice(?, ?, ?, ?, ?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                PriceController.preparePrice(price, args, argsType, argsIO, i);
            } catch (Exception e) {
                // System.err.print(price);
                Logger.getLogger().logMsg(String.format(callableStatement + price.toString()));
            }
        }
        return facade.createObject(callableStatement.toString(), args,
                argsType, argsIO);
    }

    /**
     * @param price
     * @return
     */
    public static boolean updatePrice(Price price) {
        int LENGTH = 6;
        callableStatement = new StringBuilder();
        callableStatement.append("{call updatePrice(?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                PriceController.preparePrice(price, args, argsType, argsIO, i);
            } catch (Exception e) {
                //System.err.print(price);
                Logger.getLogger().logMsg(String.format(callableStatement + price.toString()));
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));
    }

    public static boolean removePrice(String priceId) {
        boolean success = true;
        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 2;
        callableStatement.append("{call removePrice(?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {
                    case 0: {
                        args[i] = priceId;
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
                        throw new Exception("Error while creating SP removePrice()");
                }

            } catch (Exception e) {
                Logger.getLogger().logMsg(String.format(String.valueOf(callableStatement)));
            }
        }
        success = facade.removeBusinessObject(callableStatement.toString(),
                args, argsType, argsIO);

        return success;

    }

    /**
     * @return TreeMap
     */
    public static TreeMap<String, String> getProductPriceDetails(
            String product, Date date) {
        TreeMap<String, String> prices = null;
        Price detail;
        List<Price> list = PriceController.getAllValidProductPrice(product,
                date);
        prices = new TreeMap<String, String>();
        if (list != null) {
            Iterator<Price> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                detail = it.next();
                prices.put(detail.toString(), detail.getIdPrice());
                i++;
            }
        }
        return prices;
    }

}
