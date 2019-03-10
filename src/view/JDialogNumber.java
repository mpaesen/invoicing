package view;

import info.clearthought.layout.TableLayout;
import model.Number;
import persistency.controller.CodeController;
import persistency.controller.NumberController;
import persistency.logging.BaseLogger;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.ComboBoxHelper;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.TreeMap;

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
public class JDialogNumber extends JDialog {
    private JFrame parent;

    private JPanel jPanelNumberSouth;
    private JPanel jPanelNumberNorth;

    private JLabel jLabelNumCategory;
    private JLabel jLabelNumLstValue;
    private JLabel jLabelNumStrValue;
    private JLabel jLabelNumYear;

    private JComboBox jComboBoxNumCategory;
    private ComboBoxModel jComboBoxNumCategoryModel;

    private JTextField jTextFieldNumLstValue;
    private JTextField jTextFieldNumStrValue;
    private JTextField jTextFieldNumYear;

    private JButton jButtonNumberSave;

    private JTextArea jTextAreaMessage;

    private Number number;

    private TreeMap<String, String> nbrCategories;

    private CRUDOperationEnum operation;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager
                    .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
    }

    public JDialogNumber(JFrame frame, Number number,
                         CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.number = number;
        initGUI();
    }

    private JDialogNumber(JFrame frame, boolean modal,
                          CRUDOperationEnum operation) {
        super(frame, modal);
        this.operation = operation;
        this.parent = frame;
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            BorderLayout thisLayout = new BorderLayout();
            getContentPane().setLayout(thisLayout);
            createNorthPanel();
            createSouthPanel();
            this.setSize(370, 194);
            this.setLocationRelativeTo(parent);
            this.setVisible(true);
        } catch (Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
    }

    /**
     *
     */
    private void createSouthPanel() {
        jPanelNumberSouth = new JPanel();
        getContentPane().add(jPanelNumberSouth, BorderLayout.SOUTH);
        jPanelNumberSouth.setPreferredSize(new java.awt.Dimension(354, 56));
        jPanelNumberSouth.add(getJButtonNumberSave());
        getJButtonNumberSave().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                StringBuilder errorMessages = new StringBuilder(Constants.EMPTY);
                if (validateInput(errorMessages)) {
                    saveInput();
                    initializeNumberFields();
                }
            }
        });
        jPanelNumberSouth.add(getJTextAreaMessage());
    }

    /**
     *
     */
    private JTextArea getJTextAreaMessage() {
        if (jTextAreaMessage == null) {
            jTextAreaMessage = new JTextArea();
            jTextAreaMessage.setEditable(false);
            jTextAreaMessage.setPreferredSize(new java.awt.Dimension(312, 16));
            resetMessage();
        }
        return jTextAreaMessage;
    }

    /**
     * @param errorMessages
     */
    private boolean validateInput(StringBuilder errorMessages) {
        resetMessage();
        validateNumber(errorMessages);
        if (!errorMessages.toString().equals(Constants.EMPTY)) {
            redMessage(errorMessages);
            return false;
        }
        return true;
    }

    private void validateNumber(StringBuilder errorMessages) {
        resetMessage();
        try {
            Integer.parseInt(getJTextFieldNumYear().getText());
            Integer.parseInt(getJTextFieldNumStrValue().getText());
            Integer.parseInt(getJTextFieldNumLstValue().getText());
        } catch (NumberFormatException exp) {
            errorMessages.append(Constants.ONLY_NUMBERS_ALLOWED);
        }
    }

    synchronized private void saveInput() {

        if ((number == null) && (operation == CRUDOperationEnum.NEW)) {
            Number newNumber = createNewNumber();
            NumberController.createNumber(newNumber);
            number = new Number(newNumber);
            greenMessage(newNumber.getNbrCategory() + " werd Toegevoegd");
            // ones created, updates are allowed
            operation = CRUDOperationEnum.UPDATE;
        } else {
            if (operation == CRUDOperationEnum.UPDATE) {
                Number newNumber = updateExistingNumber();

                if (!number.equals(newNumber)) {
                    NumberController.updateNumber(newNumber);
                    number = new Number(newNumber);
                    greenMessage(newNumber.getNbrCategory() + " werd gewijzigd");
                }
            }
        }
        // All modifications should be shown real-time
        initializeNumberFields();
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Number updateExistingNumber() {
        String nbrStrValue = jTextFieldNumStrValue.getText();
        String nbrLstValue = jTextFieldNumLstValue.getText();

        Number newNumber = new Number(number.getNbrCategory(),
                number.getNbrYear(), nbrStrValue, nbrLstValue,
                number.isActive());
        return newNumber;
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Number createNewNumber() {
        String desc = getJComboBoxNumCategory().getSelectedItem().toString();
        String category = nbrCategories.get(desc);

        Integer nbrYear = new Integer(jTextFieldNumYear.getText());
        String nbrStrValue = jTextFieldNumStrValue.getText();
        String nbrLstValue = jTextFieldNumLstValue.getText();

        Number newNumber = new Number(category, nbrYear, nbrStrValue,
                nbrLstValue, true);
        return newNumber;
    }

    /**
     *
     */
    private JButton getJButtonNumberSave() {
        if (jButtonNumberSave == null) {
            jButtonNumberSave = new JButton();
            jButtonNumberSave.setText("OK");
            jButtonNumberSave.setToolTipText("Gegevens bewaren");
        }
        return jButtonNumberSave;
    }

    private void createNorthPanel() {
        jPanelNumberNorth = new JPanel();
        TableLayout jPanel2Layout = new TableLayout(
                new double[][]{{100.0, 133.0, 54.0, TableLayout.FILL},
                        {26.0, 26.0, 28.0}});
        jPanel2Layout.setHGap(5);
        jPanel2Layout.setVGap(5);
        jPanelNumberNorth.setLayout(jPanel2Layout);
        getContentPane().add(jPanelNumberNorth, BorderLayout.NORTH);
        jPanelNumberNorth.setPreferredSize(new java.awt.Dimension(362, 103));

        jPanelNumberNorth.add(getJLabelNumCategory(), "0, 0");
        jPanelNumberNorth.add(getJLabelNumYear(), "2, 0");
        jPanelNumberNorth.add(getJTextFieldNumYear(), "3, 0");
        jPanelNumberNorth.add(getJComboBoxNumCategory(), "1, 0");
        jPanelNumberNorth.add(getJLabelNumStrValue(), "0, 1");
        jPanelNumberNorth.add(getJLabelNbrLstValue(), "0, 2");
        jPanelNumberNorth.add(getJTextFieldNumStrValue(), "1, 1");
        jPanelNumberNorth.add(getJTextFieldNumLstValue(), "1, 2");
        initializeNumberFields();
    }

    private void initializeNumberFields() {
        getJComboBoxNumCategory().setEditable(false);
        if (number != null) {
            getJComboBoxNumCategory().setSelectedItem(
                    ComboBoxHelper.getSelectedItem(nbrCategories,
                            number.getNbrCategory()));
            getJComboBoxNumCategory().setEnabled(false);
            getJTextFieldNumYear().setText(
                    Integer.toString(number.getNbrYear()));
            getJTextFieldNumYear().setEditable(false);
            getJTextFieldNumStrValue().setText(number.getNbrStrValue());
            getJTextFieldNumLstValue().setText(number.getNbrLstValue());
        }
    }

    public JButton getJButtonSave() {
        return jButtonNumberSave;
    }

    /**
     * @param errorMessages
     */
    private void redMessage(StringBuilder errorMessages) {
        jTextAreaMessage.setText(errorMessages.toString());
        jTextAreaMessage.setBackground(new java.awt.Color(255, 0, 0));
    }

    private void resetMessage() {
        jTextAreaMessage.setText(Constants.EMPTY);
        jTextAreaMessage.setBackground(new java.awt.Color(238, 238, 238));
    }

    /**
     * @param announcement
     */
    private void greenMessage(String announcement) {
        jTextAreaMessage.setText(announcement);
        jTextAreaMessage.setBackground(new java.awt.Color(0, 255, 0));

    }

    public JLabel getJLabelNumCategory() {
        if (jLabelNumCategory == null) {
            jLabelNumCategory = new JLabel();
            jLabelNumCategory.setText("Categorie");
        }
        return jLabelNumCategory;
    }

    private JLabel getJLabelNumYear() {
        if (jLabelNumYear == null) {
            jLabelNumYear = new JLabel();
            jLabelNumYear.setText("Jaar :");
        }
        return jLabelNumYear;
    }

    public JTextField getJTextFieldNumYear() {
        if (jTextFieldNumYear == null) {
            jTextFieldNumYear = new JTextField();
        }
        return jTextFieldNumYear;
    }

    public JLabel getJLabelNumStrValue() {
        if (jLabelNumStrValue == null) {
            jLabelNumStrValue = new JLabel();
            jLabelNumStrValue.setText("Start :");
        }
        return jLabelNumStrValue;
    }

    public JLabel getJLabelNbrLstValue() {
        if (jLabelNumLstValue == null) {
            jLabelNumLstValue = new JLabel();
            jLabelNumLstValue.setText("Laatste :");
        }
        return jLabelNumLstValue;
    }

    public JTextField getJTextFieldNumStrValue() {
        if (jTextFieldNumStrValue == null) {
            jTextFieldNumStrValue = new JTextField();
        }
        return jTextFieldNumStrValue;
    }

    public JTextField getJTextFieldNumLstValue() {
        if (jTextFieldNumLstValue == null) {
            jTextFieldNumLstValue = new JTextField();
        }
        return jTextFieldNumLstValue;
    }

    private TreeMap getNbrCategories() {
        if (nbrCategories == null) {
            nbrCategories = CodeController.getCodeDetails(CodeEnum.NUMBER
                    .getType());// Number
        }
        return nbrCategories;
    }

    @SuppressWarnings("unchecked")
    public JComboBox getJComboBoxNumCategory() {
        if (jComboBoxNumCategory == null) {
            Collection<String> list = getNbrCategories().keySet();
            jComboBoxNumCategoryModel = new DefaultComboBoxModel(list.toArray());
            jComboBoxNumCategory = new JComboBox();
            jComboBoxNumCategory.setModel(jComboBoxNumCategoryModel);
        }
        return jComboBoxNumCategory;
    }

}
