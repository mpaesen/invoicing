package utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer implements TableCellRenderer {
    public static TableCellRenderer RIGHT;
    public static TableCellRenderer CENTER;
    public static TableCellRenderer LEFT;
    private final DefaultTableCellRenderer renderer;
    private final int horAlignment;
    private final int[] alignments = new int[]{JLabel.LEFT, JLabel.CENTER, JLabel.RIGHT};

    public CellRenderer(final JTable table, final int horizontalAlignment) {
        horAlignment = horizontalAlignment;
        //table.setBackground(Color.CYAN);
        renderer = (DefaultTableCellRenderer) table.getCellRenderer(0, 0);
        //table.getTableHeader().getDefaultRenderer();
    }

    public CellRenderer(final JTable table) {
        this(table, 0);
        RIGHT = new CellRenderer(table, alignments[2]);
        CENTER = new CellRenderer(table, alignments[1]);
        LEFT = new CellRenderer(table, alignments[0]);

    }

    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int col) {
        final Component c = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        final JLabel label = (JLabel) c;
        label.setHorizontalAlignment(horAlignment);
        return label;
    }

}
