package persistency.test;

import junit.framework.TestCase;
import model.BusinessTypeEnum;
import model.Number;
import model.test.DummyFactory;
import persistency.ArgIO;
import persistency.DBFacade;
import persistency.controller.NumberController;
import persistency.logging.BaseLogger;


public class TestDummyNumber extends TestCase {
    private DBFacade facade;
    private StringBuffer callableStatement;
    private Number number;


    @Override
    public void setUp() {
        facade = new DBFacade();
        number = (Number) DummyFactory.createBusiness(BusinessTypeEnum.NUMBER);
        callableStatement = new StringBuffer();
    }

    public void testCreateNumber() {
        int LENGTH = 5;
        callableStatement.append("{call createNumber(?, ?, ?, ?, ?)}");
        Object[] args = new Object[LENGTH];
        int[] argsType = new int[LENGTH];
        ArgIO[] argsIO = new ArgIO[LENGTH];
        for (int i = 0; i < args.length; i++) {
            try {
                NumberController.prepareNumber(number, args, argsType, argsIO,
                        i);
            } catch (Exception e) {
                //System.err.print(number);
                BaseLogger.getLogger().logMsg(String.valueOf(number));
                //e.printStackTrace();
            }
        }
        assertTrue(facade.createObject(callableStatement.toString(), args,
                argsType, argsIO));
    }

}
