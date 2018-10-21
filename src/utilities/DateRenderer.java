package utilities;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;

public class DateRenderer extends DefaultTableCellRenderer {
    DateFormat formatter;

    public DateRenderer() {
        super();
    }

    public void setValue(Object value) {
        if (formatter == null) {
            formatter = DateFormat.getDateInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}
