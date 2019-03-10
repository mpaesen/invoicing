package view;

import persistency.logging.BaseLogger;

import javax.swing.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class JOptionPaneCreateInvoice extends JOptionPane {
    public static int confirm(String arg) {
        JDialog.setDefaultLookAndFeelDecorated(false);
        int response = JOptionPane.showConfirmDialog(null,
                "Wilt u onmiddellijk voor offerte " + arg
                        + " een factuur maken?", "Bevestigen",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return response;
    }

    private void initGUI() {
        try {
            {
                setDefaultLocale(new java.util.Locale("nl", "BE"));
            }
        } catch (Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
    }

}
