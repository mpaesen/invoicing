package persistency.test;

import model.BusinessTypeEnum;
import model.Number;
import model.test.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.ArgIO;
import persistency.DBFacade;
import persistency.controller.NumberController;

public class TestDummyNumber {
    private DBFacade facade;
    private StringBuffer callableStatement;
    private Number number;

    @BeforeAll
    public void setUp() {
        facade = new DBFacade();
        number = (Number) DummyFactory.createBusiness(BusinessTypeEnum.NUMBER);
        callableStatement = new StringBuffer();
    }

    @Test
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
                System.err.print(number);
                e.printStackTrace();
            }
        }
        if (!facade.createObject(callableStatement.toString(), args,
                argsType, argsIO)) throw new AssertionError();
    }

}
