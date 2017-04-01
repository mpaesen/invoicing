package utilities;

import java.util.Iterator;
import java.util.TreeMap;

public class ComboBoxHelper {
	/**
	 * Gets the JCombobox with the initial value from the databasetable
	 * @param map
	 * @param code
	 * @return
	 */
	public static String getSelectedItem(TreeMap map, String code){

		Object []keys = map.keySet().toArray();
		Iterator<String> it = map.values().iterator();
		int i = 0;
		String element = null;
		while(it.hasNext()){
			if(it.next().equals(code)){
				return keys[i].toString();
			}
			i++;
		}
		return element;
	}

}
