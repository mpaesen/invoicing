package utilities;


import java.io.IOException;

import javax.swing.JOptionPane;
/**standard keyboard */
public final class ConsoleGUI {

	/**read an integer from the keyboard */
	public static int readInt() {
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(null, ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readInt();
		}
	}
	/**read an integer from the keyboard */
	public static int readInt(String text) {
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(null, text));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readInt();
		}
	}
	/**read a double from the keyboard */
	public static double readDouble() {
		try {
			return Double.parseDouble(JOptionPane.showInputDialog(null, ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readDouble();
		}
	}
	/**read a double from the keyboard */
	public static double readDouble(String text) {
		try {
			return Double.parseDouble(JOptionPane.showInputDialog(null, text));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readDouble();
		}
	}

	/**read a string from the keyboard */
	public static String readString() {
		try {
			return JOptionPane.showInputDialog(null, "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readString();
		}
	}

	/**
	 * @param text
	 * @return
	 */
	public static String readString(String text) {

		try {
			return JOptionPane.showInputDialog(null, text);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readString();
		}
	}

	/**read a character from the keyboard */
	public static char readChar() {
		try {
			return readString().charAt(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readChar();
		}
	}
	/**read a character from the keyboard */
	public static char readChar(String text) {
		try {
			return readString(text).charAt(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return readChar();
		}
	}

	/**write an object on screen */
	public static void write(Object o) {
		JOptionPane.showMessageDialog(
			null,
			o,
			"",
			JOptionPane.INFORMATION_MESSAGE);
	}
	/**write an object on screen with a line skip*/
	public static void write(Object o, String text) {
		JOptionPane.showMessageDialog(
			null,
			o,
			text,
			JOptionPane.INFORMATION_MESSAGE);
	}
	/**write an integer on screen */
	public static void write(int x) {
		Integer o = new Integer(x);
		JOptionPane.showMessageDialog(
			null,
			o,
			"",
			JOptionPane.INFORMATION_MESSAGE);
	}

	/**write an integer on screen with a line skip*/
	public static void write(int x, String text) {
		Integer o = new Integer(x);
		JOptionPane.showMessageDialog(
			null,
			o,
			text,
			JOptionPane.INFORMATION_MESSAGE);
	}
	/**write a double on screen */
	public static void write(double x) {
		Double o = new Double(x);
		JOptionPane.showMessageDialog(
			null,
			o,
			"",
			JOptionPane.INFORMATION_MESSAGE);
	}
	/**write a double on screen with a line skip*/
	public static void write(double x, String text) {
		Double o = new Double(x);
		JOptionPane.showMessageDialog(
			null,
			o,
			text,
			JOptionPane.INFORMATION_MESSAGE);
	}
	/**to prevent automatic closure under windows */
	public static void end() {
		write("Press \"Enter\" to end the session");
		try {
			System.in.read();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
				null,
				e,
				"Error",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/**
	 * @return
	 */
	public static boolean nog() {

		return (
			Character.toUpperCase(
				ConsoleGUI.readString("Opnieuw? J/N").charAt(0))
				== 'J')
			? true
			: false;
	}
	/**
	 * @param str
	 * @return
	 */
	public static int nog(String str) {
		return JOptionPane.showConfirmDialog(
			null,
			str,
			"Opnieuw",
			JOptionPane.INFORMATION_MESSAGE);//0=yes, 1=no, 2=cancel

	}
}