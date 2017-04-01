package utilities;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public abstract class GenericTableModel extends AbstractTableModel {

	private String[] columnNames;
	private Object[][] data;
	private Object[] longValues; 

	public GenericTableModel(int[] columnWidth){ 
		setTableModel(columnWidth);
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public Object[] getLongValues() {
		return longValues;
	}
	public void setLongValues(Object[] longValues){
		this.longValues = longValues;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return (data == null ? 0 : data.length);
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public void setValue(Object value, int row, int col){
		data[row][col] = value;
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column
	 * would contain text ("true"/"false"), rather than a check box.
	 */
	@Override
	public Class getColumnClass(int c) {
		Object object = getValueAt(0, c);
		if (object == null)
			return null;
		return object.getClass();
	}

	@Override
	public abstract boolean isCellEditable(int row, int col);
	@Override
	public abstract void setValueAt(Object value, int row, int col);
	public abstract void setTableModel(int[] columnWidth);
	public abstract void updateLineTotal(int row);
	
}
