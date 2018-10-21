package utilities;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DateCellRenderer extends JLabel implements TableCellRenderer {
    private Date toDay;

    public DateCellRenderer(Date toDay) {
        super();
        this.toDay = toDay;
        setOpaque(true); // MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int col) {
        Date test = null;

        try {
            test = new Date(value.toString());
            // format: "dd-MM-yy"
        } catch (DatumException e) {
            e.printStackTrace();
        }

        if (test.kleinerDan(toDay)) {
            setForeground(Color.red);
        } else {
            setForeground(null);
        }
        setText(test.toString());
        return this;
    }
}
