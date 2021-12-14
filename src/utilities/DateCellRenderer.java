package utilities;

import persistency.logging.BaseLogger;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DateCellRenderer extends JLabel implements TableCellRenderer {
    private final Date toDay;

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
            BaseLogger.logMsg(e.getMessage());
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
