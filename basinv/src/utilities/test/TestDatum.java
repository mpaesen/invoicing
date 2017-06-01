package utilities.test;

import java.util.Arrays;

import utilities.Date;
import utilities.DatumException;

public class TestDatum {

	/**
	 * @param args
	 * @throws DatumException
	 */
	public static void main(String[] args) throws DatumException {
		// String datArray[] = { "12/09/2009", "29/03/2009", "30/09/2009",
		// "20/09/2009", "11/12/2009", "22/02/2009", "31/12/2008",
		// "31/12/2009" };
		// Date datums[] = new Date[datArray.length+1];
		// for (int i = 0; i < datArray.length; i++) {
		// try {
		// datums[i] = new Date(datArray[i]);
		// } catch (DatumException exception) {
		// System.err.println(exception);
		// }
		// }datums[datArray.length] = new Date();
		// // datums = Date.sorteerDatums(datums);
		// Arrays.sort(datums);
		// for (int i = 0; i < datums.length; i++) {
		// System.out.println(datums[i]);
		// }
		// System.out.println("Er zijn "+ datums[datums.length -
		// 1].verschilInDagen(datums[0])+" dagen tussen " + datums[0] + " en "
		// + datums[datums.length - 1] + ".");

		Date test = new Date(new java.util.Date().getTime());
		test.setDatum(10, 0, 2012);
		Date test2 = new Date(test.getTimeInMilliSeconds());
		System.out.printf("%s %s", test, test2);

	}
}
