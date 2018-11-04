/**
 * 
 */
package persistency.controller;

import model.Business;
import model.BusinessTypeEnum;
import model.Product;
import persistency.ArgIO;
import persistency.DBFacade;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author Mathy
 * 
 */
public class ProductController {

    public static String PRODUCT_CATEGORY = "Pct";
    public static String PRODUCT_TYPE = "Ptp";
    private static DBFacade facade;
    private static StringBuilder callableStatement;

    public static Product getProduct(String id) {
        Product reference = null;
        DBFacade facade = new DBFacade();
        StringBuilder callableStatement = new StringBuilder();

        int LENGTH = 1;
        callableStatement.append("{call readOneProduct(?)}");
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
        Collection<Business> list = facade.getResult(BusinessTypeEnum.PRODUCT,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            Iterator<Business> it = list.iterator();
            while (it.hasNext()) {
                reference = (Product) it.next();
            }
        }
        return reference;
    }

    public static Product getProductByCode(String prodCode) {
        Product reference = null;
        DBFacade facade = new DBFacade();
        StringBuilder callableStatement = new StringBuilder();

        int LENGTH = 1;
        callableStatement.append("{call readOneProductByCode(?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                args[i] = prodCode;
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collection<Business> list = facade.getResult(BusinessTypeEnum.PRODUCT,
                callableStatement.toString(), args, argsType, argsIO);
        if (!list.isEmpty()) {
            Iterator<Business> it = list.iterator();
            while (it.hasNext()) {
                reference = (Product) it.next();
            }
        }
        return reference;
    }

    /**
     * Prepare parameters for SP createProduct
     *
     * @param args
     * @param argsType
     * @param argsIO
     * @param i
     * @throws Exception
     */
    public static void prepareProduct(Product product, Object[] args,
                                      int[] argsType, ArgIO[] argsIO, int i) throws Exception {
        switch (i) {
            case 0: {
                // IN id char(15),
                args[i] = product.getIdProd();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 1: {
                // in code char(15),
                args[i] = product.getProdCode();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 2: {
                // in desc char(30),
                args[i] = product.getProdDesc();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 3: {
                // in cat char(5),
                args[i] = product.getProdCat();
                argsType[i] = java.sql.Types.CHAR;
                argsIO[i] = ArgIO.IN;
                break;
            }
            case 4: {
                // in type char(5),
                args[i] = product.getProdType();
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
                throw new Exception("Error while creating SP createProduct");
        }
    }

    /**
     * Creates one concrete product
     *
     * @param product
     * @return
     */
    public static boolean createProduct(Product product) {
        int LENGTH = 6;
        callableStatement = new StringBuilder();
        callableStatement.append("{call createProduct(?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                ProductController.prepareProduct(product, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
                System.err.print(product);
                e.printStackTrace();
            }
        }
        return facade.createObject(callableStatement.toString(), args,
                argsType, argsIO);

    }

    /**
     * @param product
     * @return
     */
    public static boolean updateProduct(Product product) {
        int LENGTH = 6;
        callableStatement = new StringBuilder();
        callableStatement.append("{call updateProduct(?, ?, ?, ?, ?, ?)}");
        facade = new DBFacade();
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                ProductController.prepareProduct(product, args, argsType,
                        argsIO, i);
            } catch (Exception e) {
                System.err.print(product);
                e.printStackTrace();
            }
        }
        return (facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));
    }

    public static boolean removeProduct(String prodId) {
        boolean success = true;
        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 2;
        callableStatement.append("{call removeProduct(?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {
                    case 0: {
                        args[i] = prodId;
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
                                "Error while creating SP removeProduct()");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        success = facade.removeBusinessObject(callableStatement.toString(),
                args, argsType, argsIO);

        return success;

    }

    /**
     * @param filter
     * @return Collection of Business objects (Products)
     */
    public static Collection<Business> getProducts(String[] filter) {
        Collection<Business> list;

        callableStatement = new StringBuilder();
        facade = new DBFacade();
        int LENGTH = 3;
        callableStatement.append("{call readAllProduct(?,?,?)}");
        Object[] args = {"", "", true};
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                switch (i) {

                    case 0: {
                        // in code char(30),
                        if (!filter[i].equals("")) {
                            args[i] = filter[i];
                        }
                        argsType[i] = java.sql.Types.CHAR;
                        argsIO[i] = ArgIO.IN;
                        break;
                    }
                    case 1: {
                        // in Categorie char(5),
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
                                "Error while creating SP readAllProduct()");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list = facade.getResult(BusinessTypeEnum.PRODUCT,
                callableStatement.toString(), args, argsType, argsIO);
        return list;
    }

    /**
     * @return TreeMap
     */
    public static TreeMap<String, String> getProductDetails(String[] filter) {
        TreeMap<String, String> products;
        Product detail;
        Collection<Business> list = ProductController.getProducts(filter);
        products = new TreeMap<String, String>();
        Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            detail = (Product) it.next();
            products.put(detail.getProdCode(), detail.getIdProd());
            i++;
        }
        return products;
    }

}
