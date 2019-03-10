package view;

import com.toedter.calendar.JDateChooser;
import info.clearthought.layout.TableLayout;
import model.Price;
import model.Product;
import persistency.controller.CodeController;
import persistency.controller.NumberController;
import persistency.controller.PriceController;
import persistency.logging.BaseLogger;
import utilities.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class JDialogPrice extends JDialog {

    private JDialog parent;
    private JPanel jPanelNorth;
    private JLabel jLabelUnitOfMeasure;
    private JDateChooser jDateFrom;
    private JComboBox jComboBoxUnitOfMeasure;
    private ComboBoxModel jComboBoxUnitOfMeasureModel;
    private JTextField jTextFieldPriUnit;
    private JLabel jLabelPriUnit;
    private JPanel jPanelCenter;
    private JPanel jPanelSouth;
    private Product product;
    private Price price;
    private CRUDOperationEnum operation;
    private Date toDay;
    private TreeMap<String, String> priceUnites;
    private JLabel jLabelFrom;
    private JLabel jLabelProductCode;
    private JTextArea jTextAreaMessage;
    private JButton jButtonSavePrice;
    private int row;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager
                    .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
    }

    public JDialogPrice(JDialog frame, Product product, Price price,
                        CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.price = price;
        this.product = product;
        initGUI();
    }

    private JDialogPrice(JDialog frame, boolean modal,
                         CRUDOperationEnum operation) {
        super(frame, modal);
        this.operation = operation;
        priceUnites = CodeController.getCodeDetails(CodeEnum.UNIT_OF_MEASURE
                .getType());// Units of measure

        try {
            this.toDay = new Date();
        } catch (DatumException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
        this.parent = frame;
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().add(getJPanelNorth(), BorderLayout.NORTH);
            getContentPane().add(getJPanelCenter(), BorderLayout.CENTER);
            getContentPane().add(getJPanelSouth(), BorderLayout.SOUTH);
            initializePriceFields();
            this.setSize(342, 249);
            this.setLocationRelativeTo(parent);
            this.setVisible(true);
        } catch (Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
    }

    private void initializePriceFields() {
        getJLabelProductCode().setText(product.getProdCode());
        if (price != null) {
            jComboBoxUnitOfMeasure.setSelectedItem(ComboBoxHelper
                    .getSelectedItem(priceUnites, price.getPriMeasure()));
            getJTextFieldPriUnit().setText(price.getPriUnit().toString());
            jDateFrom.setDate(new GregorianCalendar(price.getPrifrom()
                    .getJaar(), price.getPrifrom().getMaand(), price
                    .getPrifrom().getDag()).getTime());
        } else {
            getJTextFieldPriUnit().setText("0.00");
        }
    }

    public JPanel getJPanelNorth() {
        if (jPanelNorth == null) {
            jPanelNorth = new JPanel();
            jPanelNorth.setPreferredSize(new java.awt.Dimension(219, 32));
            jPanelNorth.add(getJLabelProductCode());
        }
        return jPanelNorth;
    }

    public JPanel getJPanelCenter() {
        if (jPanelCenter == null) {
            jPanelCenter = new JPanel();
            TableLayout jPanelCenterLayout = new TableLayout(new double[][]{
                    {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
                    {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
                            TableLayout.FILL}});
            jPanelCenterLayout.setHGap(5);
            jPanelCenterLayout.setVGap(5);
            jPanelCenter.setLayout(jPanelCenterLayout);
            jPanelCenter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
                    null, null, null, null));
            jPanelCenter.setPreferredSize(new java.awt.Dimension(219, 116));
            jPanelCenter.add(getJLabelFrom(), "0, 2");
            jPanelCenter.add(getJLabelPriUnit(), "0, 0");
            jPanelCenter.add(getJLabelUnitOfMeasure(), "1, 0");
            jPanelCenter.add(getJTextFieldPriUnit(), "0, 1");
            jPanelCenter.add(getJComboBoxUnitOfMeasure(), "1, 1, 2, 1");
            jPanelCenter.add(getJDateFrom(), "1, 2, 2, 2");
        }
        return jPanelCenter;
    }

    public JPanel getJPanelSouth() {
        if (jPanelSouth == null) {
            jPanelSouth = new JPanel();
            jPanelSouth.setPreferredSize(new java.awt.Dimension(219, 59));
            jPanelSouth.add(getJButtonSavePrice());
            getJButtonSavePrice().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        saveInput();
                        initializePriceFields();
                    }
                }
            });
            jPanelSouth.add(getJTextAreaMessage());
        }
        return jPanelSouth;
    }

    /**
     * @param errorMessages
     */
    private boolean validateInput(StringBuilder errorMessages) {
        resetMessage();
        validatePrice(errorMessages);
        if (!errorMessages.toString().equals(Constants.EMPTY)) {
            redMessage(errorMessages);
            return false;
        }
        return true;
    }

    private boolean validatePrice(StringBuilder errorMessages) {
        if (jTextFieldPriUnit.getText().equals(Constants.EMPTY)) {
            errorMessages.append(Constants.NOT_ZERO_PRICE);
            return false;
        }
        if (jComboBoxUnitOfMeasure.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_UNIT);
            return false;
        }
        if (getJDateFrom().getCalendar() == null) {
            errorMessages.append(Constants.NO_DATE);
            return false;
        }
        return true;
    }

    synchronized private void saveInput() {

        if ((price == null) && (operation == CRUDOperationEnum.NEW)) {
            Price newPrice = createNewPrice();
            PriceController.createPrice(newPrice);
            price = new Price(newPrice);
            greenMessage(newPrice.getIdPrice() + " werd Toegevoegd");
        }
        if (operation == CRUDOperationEnum.UPDATE) {
            Price newPrice = updateExistingPrice();

            if (!price.equals(newPrice)) {
                PriceController.updatePrice(newPrice);
                price = new Price(newPrice);
                greenMessage(newPrice.getIdPrice() + " werd gewijzigd");
            }
        }
        // All modifications should be shown real-time
        initializePriceFields();
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Price updateExistingPrice() {
        String unitOfMeasure = priceUnites.get(jComboBoxUnitOfMeasure
                .getSelectedItem().toString());
        Date dateFrom = null;
        try {
            dateFrom = new Date(jDateFrom.getCalendar().get(Calendar.DATE),
                    jDateFrom.getCalendar().get(Calendar.MONTH), jDateFrom
                    .getCalendar().get(Calendar.YEAR));
        } catch (DatumException e) {
            e.printStackTrace();
        }
        Price newPrice = new Price(price.getIdPrice(), product.getIdProd(),
                dateFrom, new BigDecimal(jTextFieldPriUnit.getText()),
                unitOfMeasure, price.isActive());
        return newPrice;
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Price createNewPrice() {
        String priceID = NumberController.readLastNumber(
                NumberEnum.PRICE.getType(), 0).toString();
        String unitOfMeasure = priceUnites.get(jComboBoxUnitOfMeasure
                .getSelectedItem().toString());
        Date dateFrom = null;
        try {
            dateFrom = new Date(jDateFrom.getCalendar().get(Calendar.DATE),
                    jDateFrom.getCalendar().get(Calendar.MONTH), jDateFrom
                    .getCalendar().get(Calendar.YEAR));
        } catch (DatumException e) {
            e.printStackTrace();
        }

        Price newPrice = new Price(priceID, product.getIdProd(), dateFrom,
                new BigDecimal(jTextFieldPriUnit.getText()), unitOfMeasure,
                true);
        return newPrice;
    }

    /**
     *
     */
    private void setProductUnitOfMeasureComboBox() {
        jComboBoxUnitOfMeasureModel = new DefaultComboBoxModel(priceUnites
                .keySet().toArray());
        jComboBoxUnitOfMeasure = new JComboBox();
        jComboBoxUnitOfMeasure.setModel(jComboBoxUnitOfMeasureModel);
    }

    public JButton getJButtonSavePrice() {
        if (jButtonSavePrice == null) {
            jButtonSavePrice = new JButton();
            jButtonSavePrice.setText("OK");
        }
        return jButtonSavePrice;
    }

    public JTextArea getJTextAreaMessage() {
        if (jTextAreaMessage == null) {
            jTextAreaMessage = new JTextArea();
            jTextAreaMessage.setPreferredSize(new java.awt.Dimension(184, 16));
            resetMessage();
        }
        return jTextAreaMessage;
    }

    public JLabel getJLabelProductCode() {
        if (jLabelProductCode == null) {
            jLabelProductCode = new JLabel();
            jLabelProductCode.setPreferredSize(new java.awt.Dimension(98, 18));
            jLabelProductCode.setFont(new java.awt.Font("Dialog", 1, 14));
        }
        return jLabelProductCode;
    }

    public JLabel getJLabelFrom() {
        if (jLabelFrom == null) {
            jLabelFrom = new JLabel();
            jLabelFrom.setText("Vanaf :");
        }
        return jLabelFrom;
    }

    public JLabel getJLabelPriUnit() {
        if (jLabelPriUnit == null) {
            jLabelPriUnit = new JLabel();
            jLabelPriUnit.setText("Prijs");
        }
        return jLabelPriUnit;
    }

    public JLabel getJLabelUnitOfMeasure() {
        if (jLabelUnitOfMeasure == null) {
            jLabelUnitOfMeasure = new JLabel();
            jLabelUnitOfMeasure.setText("Eenheid");
            setProductUnitOfMeasureComboBox();
        }
        return jLabelUnitOfMeasure;
    }

    public JTextField getJTextFieldPriUnit() {
        if (jTextFieldPriUnit == null) {
            jTextFieldPriUnit = new JTextField();
        }
        return jTextFieldPriUnit;
    }

    public JComboBox getJComboBoxUnitOfMeasure() {
        if (jComboBoxUnitOfMeasure == null) {
            jComboBoxUnitOfMeasureModel = new DefaultComboBoxModel(priceUnites
                    .keySet().toArray());
            jComboBoxUnitOfMeasure = new JComboBox();
            jComboBoxUnitOfMeasure.setModel(jComboBoxUnitOfMeasureModel);
        }
        return jComboBoxUnitOfMeasure;
    }

    public JDateChooser getJDateFrom() {
        if (jDateFrom == null) {
            jDateFrom = new JDateChooser();
            jDateFrom.setDateFormatString("d MMM, yyyy");
            jDateFrom.setToolTipText("Startdatum voor deze prijs");

        }
        return jDateFrom;
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

}
