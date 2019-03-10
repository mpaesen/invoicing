/**
 * 
 */
package view;

import info.clearthought.layout.TableLayout;
import model.Customer;
import model.Price;
import model.Quote;
import model.QuoteDetail;
import persistency.controller.*;
import persistency.logging.BaseLogger;
import utilities.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.TreeMap;

import static utilities.ComboBoxHelper.getSelectedItem;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * @author Administrator
 * 
 */
public class JDialogQuoteDetail extends JDialog {
    private JPanel jPanelNorth;
    private JPanel jPanelQuoteDetail;
    private JPanel jPanelSouth;

    private JComboBox jComboBoxProduct;
    private JComboBox jComboBoxPrice;
    private JComboBox jComboBoxUnitOfMeasure;

    private ComboBoxModel jComboBoxProductModel;
    private ComboBoxModel jComboBoxUnitOfMeasureModel;
    private ComboBoxModel jComboBoxPriceModel;

    private JLabel jLabelUnitOfMeasure;
    private JLabel jLabelPrijsSelect;
    private JLabel jLabelTotal;
    private JTextField jTextFieldProductPrice;
    private JTextField jTextFieldLineTotal;
    private JTextField jTextFieldQuoteCurrency;
    private JScrollPane jScrollPaneComments;
    private JTextArea message;

    private JLabel jLabelProduct;
    private JLabel jLabelQuoteID;
    private JLabel jLabelLineNumber;
    private JLabel jLabelPrice;
    private JLabel jLabelComments;
    private JTextArea jTextAreaComments;
    private JTextField jTextFieldQteQty;
    private JButton jButtonConfirm;
    private JLabel jLabelQuantity;

    private JTextField jTextFieldQuoteID;
    private JTextField jTextFieldQteDetLine;

    private TreeMap<String, String> qtePrices, qteProducts, qteUnits;
    private QuoteDetail detail, previous;
    private Quote quote;
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

    public JDialogQuoteDetail(Quote quote, QuoteDetail detail,
                              CRUDOperationEnum operation) {
        super();
        this.quote = quote;
        this.detail = detail;
        this.operation = operation;

        initGUI();
    }

    private void initGUI() {

        {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            this.setPreferredSize(new java.awt.Dimension(462, 362));
            this.setBounds(0, 0, 462, 362);
            {
                {
                    jPanelNorth = new JPanel();
                    getContentPane().add(jPanelNorth, BorderLayout.NORTH);
                    jPanelNorth
                            .setPreferredSize(new java.awt.Dimension(443, 41));
                    {
                        jLabelQuoteID = new JLabel();
                        jPanelNorth.add(jLabelQuoteID);
                        jLabelQuoteID.setText("Offerte :");
                    }
                    {// quote
                        jTextFieldQuoteID = new JTextField();
                        jPanelNorth.add(jTextFieldQuoteID);
                        jTextFieldQuoteID.setEditable(false);
                        jTextFieldQuoteID
                                .setPreferredSize(new java.awt.Dimension(114,
                                        26));
                        jTextFieldQuoteID.setFont(new java.awt.Font("Dialog",
                                1, 18));
                    }
                }

                jPanelQuoteDetail = new JPanel();
                TableLayout jPanelQuoteDetailLayout = new TableLayout(
                        new double[][]{
                                {104.0, 68.0, 102.0, 111.0, TableLayout.FILL},
                                {25.0, 28.0, 29.0, 25.0, 25.0,
                                        TableLayout.FILL}});
                jPanelQuoteDetailLayout.setHGap(5);
                jPanelQuoteDetailLayout.setVGap(5);
                jPanelQuoteDetail.setLayout(jPanelQuoteDetailLayout);

                getContentPane().add(jPanelQuoteDetail, BorderLayout.CENTER);
                jPanelQuoteDetail.setPreferredSize(new java.awt.Dimension(371,
                        210));
                jPanelQuoteDetail.setBorder(new SoftBevelBorder(
                        BevelBorder.LOWERED, null, null, null, null));
                {
                    jLabelLineNumber = new JLabel();
                    jPanelQuoteDetail.add(jLabelLineNumber, "0, 0");
                    jLabelLineNumber.setText("Lijn :");
                }
                {// Line
                    jTextFieldQteDetLine = new JTextField();
                    jTextFieldQteDetLine.setFont(new Font("Dialog", 1, 18));
                    jPanelQuoteDetail.add(jTextFieldQteDetLine, "1, 0");
                    jTextFieldQteDetLine.setEditable(false);
                }
                {
                    jLabelProduct = new JLabel();
                    jPanelQuoteDetail.add(jLabelProduct, "0, 1");
                    jLabelProduct.setText("Product/Dienst :");
                }
                {
                    jLabelQuantity = new JLabel();
                    jPanelQuoteDetail.add(jLabelQuantity, "0, 3");
                    jLabelQuantity.setText("Hoeveelheid :");
                }
                {
                    jLabelPrice = new JLabel();
                    jPanelQuoteDetail.add(jLabelPrice, "2, 3");
                    jLabelPrice.setText("Prijs :");
                }

                {// product
                    jComboBoxProduct = getJComboBoxProduct();
                    jComboBoxProduct.addItemListener(new ItemListener() {
                        public void itemStateChanged(ItemEvent evt) {
                            jComboBoxPrice.setModel(getJComboBoxPriceModel());

                        }
                    });
                    jPanelQuoteDetail.add(jComboBoxProduct, "2, 1, 3, 1");
                }
                {// price
                    jComboBoxPrice = getJComboBoxPriceEmpty();
                    jComboBoxPrice.addItemListener(new ItemListener() {
                        public void itemStateChanged(ItemEvent evt) {
                            StringBuilder errorMessages = new StringBuilder();
                            resetMessage();
                            if (validateInput(errorMessages)) {
                                jTextFieldLineTotal
                                        .setText(calculateLineTotal(createDetail()));
                                initializeFields();
                            }
                        }
                    });
                    jPanelQuoteDetail.add(jComboBoxPrice, "2, 2, 4, 2");

                }

                {// Quantity
                    jPanelQuoteDetail.add(getJTextFieldQteQty(), "0, 4");
                }
            }
            {
                jPanelQuoteDetail.add(getJScrollPaneComments(), "1, 5, 3, 5");
            }
            {
                jLabelComments = new JLabel();
                jPanelQuoteDetail.add(jLabelComments, "0, 5");
                jPanelQuoteDetail.add(getJTextFieldQuoteCurrency(), "4, 4");
                jPanelQuoteDetail.add(getJTextFieldLineTotal(), "3, 4");
                jPanelQuoteDetail.add(getJTextFieldProductPrice(), "2, 4");
                jPanelQuoteDetail.add(getJLabelTotal(), "3, 3");
                jPanelQuoteDetail.add(getJLabelPrijsSelect(), "0, 2");
                jPanelQuoteDetail.add(getJLabelUnitOfMeasure(), "1, 3");
                jPanelQuoteDetail.add(getJComboBoxUnitOfMeasure(), "1, 4");
                jLabelComments.setText("Commentaar :");
            }
        }
        {
            jPanelSouth = new JPanel();
            getContentPane().add(jPanelSouth, BorderLayout.SOUTH);
            jPanelSouth.setPreferredSize(new java.awt.Dimension(511, 63));
            {
                jButtonConfirm = new JButton();
                jPanelSouth.add(getJButtonConfirm());
                jButtonConfirm.setText("OK");
                jButtonConfirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        StringBuilder errorMessages = new StringBuilder();
                        resetMessage();
                        if (validateInput(errorMessages)) {
                            saveInput();
                            initializeFields();
                        }

                    }
                });
            }
            {// messages
                message = new JTextArea();
                jPanelSouth.add(getMessage());
                message.setPreferredSize(new java.awt.Dimension(430, 19));
                resetMessage();
            }
        }

        initializeFields();
        this.setModal(true);
        this.setLocationByPlatform(true);
        this.setVisible(true);

    }

    /**
     *
     */
    private void resetMessage() {
        message.setText("");
        message.setBackground(new java.awt.Color(238, 238, 238));
    }

    private boolean validateInput(StringBuilder errorMessages) {
        boolean success = true;
        validateQuoteDetail(errorMessages);
        if (!errorMessages.toString().equals("")) {
            redMessage(errorMessages);
            success = false;
        }
        return success;
    }

    /**
     *
     */
    private void greenMessage(String announcement) {
        message.setText(announcement);
        message.setBackground(new java.awt.Color(0, 255, 0));
    }

    /**
     * @param errorMessages
     */
    private void redMessage(StringBuilder errorMessages) {
        message.setText(errorMessages.toString());
        message.setBackground(new java.awt.Color(255, 0, 0));
    }

    synchronized private void saveInput() {
        QuoteDetail newDetail = createDetail();
        if ((detail == null) && (operation.equals(CRUDOperationEnum.NEW))) {
            QuoteDetailController.createQuoteDetail(newDetail);
            detail = new QuoteDetail(newDetail);
            greenMessage("Lijn " + newDetail.getQteDetLine()
                    + " is toegevoegd!");
        } else {
            if (!detail.equals(newDetail)) {
                QuoteDetailController.updateQuoteDetail(newDetail);
                detail = new QuoteDetail(newDetail);
                greenMessage("Lijn " + newDetail.getQteDetLine()
                        + " is gewijzigd!");
            }
        }
    }

    /**
     * @return
     */
    private QuoteDetail createDetail() {
        QuoteDetail newDetail = new QuoteDetail(quote.getIdQuote(),
                new Integer(jTextFieldQteDetLine.getText()),
                qteProducts.get(jComboBoxProduct.getSelectedItem().toString()),
                new BigDecimal(jTextFieldQteQty.getText()),
                qteUnits.get(getJComboBoxUnitOfMeasure().getSelectedItem()
                        .toString()), new BigDecimal(
                jTextFieldProductPrice.getText()),
                jTextAreaComments.getText(), true);
        return newDetail;
    }

    private BigDecimal getPrice(String priceID) {
        String key = qtePrices.get(priceID);
        BigDecimal price = null;
        if (!key.equals("")) {
            Price productPrice = PriceController.getOneProductPrice(key);
            price = productPrice.getPriUnit();
        }
        try {
            price = new BigDecimal(jTextFieldProductPrice.getText());
        } catch (NumberFormatException ex) {
            BaseLogger.logMsg(ex.getMessage());
        }
        return price;
    }

    private String getUnitOfMeasure(String priceID) {
        String key = qtePrices.get(priceID);
        if (!key.equals("")) {
            Price price = PriceController.getOneProductPrice(key);
            return price.getPriMeasure();
        }
        return getJComboBoxUnitOfMeasure().getSelectedItem().toString();
    }

    /**
     * Validate quoteDetail panel
     *
     * @param errorMessages
     */
    private void validateQuoteDetail(StringBuilder errorMessages) {
        if ((operation.equals(CRUDOperationEnum.NEW))
                || ((errorMessages.toString().equals(Constants.EMPTY)) && (!detail
                .equals(previous)))) {

            String selectedPrice = jComboBoxPrice.getSelectedItem().toString();
            // no selected price
            if (!selectedPrice.equals(Constants.EMPTY)) {
                Price productPrice = PriceController
                        .getOneProductPrice(qtePrices.get(selectedPrice));
                getJComboBoxUnitOfMeasure().setSelectedItem(
                        productPrice.getPriMeasure());
                getJTextFieldProductPrice().setText(
                        productPrice.getPriUnit().toString());
            }
        }
        BigDecimal qty = null;
        BigDecimal price = null;

        try {
            qty = new BigDecimal(jTextFieldQteQty.getText());
        } catch (NumberFormatException ex) {
            errorMessages.append(Constants.QTY_NO_FIGURES + "\n");
        } catch (Exception ex) {
            BaseLogger.logMsg(ex.getMessage());
        }

        try {
            price = new BigDecimal(jTextFieldProductPrice.getText());
        } catch (NumberFormatException ex) {
            errorMessages.append(Constants.PRICE_NO_FIGURES + "\n");
        } catch (Exception ex) {
            BaseLogger.logMsg(ex.getMessage());
        }

        if (jComboBoxProduct.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_PRODUCT + "\n");
        }
        if ((jComboBoxPrice.getSelectedItem() == null)
                && (price.doubleValue() <= Figures.ZERO)) {
            errorMessages.append(Constants.NO_PRICE + "\n");
        }
        if ((qty == null) || (qty.doubleValue() <= Figures.ZERO)) {
            errorMessages.append(Constants.NO_QTY + "\n");
        }
    }

    /**
     * @return Products
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private JComboBox getJComboBoxProduct() {
        if (jComboBoxProduct == null) {
            qteProducts = ProductController
                    .getProductDetails(getProductFilter());
            qteProducts.put("", ""); // first element empty
            jComboBoxProductModel = new DefaultComboBoxModel(qteProducts
                    .keySet().toArray());
            jComboBoxProduct = new JComboBox();
            jComboBoxProduct.setModel(jComboBoxProductModel);
        }
        return jComboBoxProduct;
    }

    private ComboBoxModel getJComboBoxPriceModel() {
        qtePrices = PriceController.getProductPriceDetails(qteProducts
                .get(getJComboBoxProduct().getSelectedItem().toString()), quote
                .getQteReqDlvDate());
        qtePrices.put("", ""); // first element empty
        jComboBoxPriceModel = new DefaultComboBoxModel(qtePrices.keySet()
                .toArray());
        return jComboBoxPriceModel;
    }

    private JComboBox getJComboBoxPriceEmpty() {
        if (jComboBoxPrice == null) {
            jComboBoxPriceModel = new DefaultComboBoxModel();

            jComboBoxPrice = new JComboBox();
            jComboBoxPrice.setModel(jComboBoxPriceModel);

        }
        return jComboBoxPrice;
    }

    private String[] getProductFilter() {
        String[] filter = {"", "", "true"};
        return filter;
    }

    public JButton getJButtonConfirm() {
        return jButtonConfirm;
    }

    public JTextArea getJTextAreaComments() {
        return jTextAreaComments;
    }

    public JTextArea getMessage() {
        return message;
    }

    private JScrollPane getJScrollPaneComments() {
        if (jScrollPaneComments == null) {
            jScrollPaneComments = new JScrollPane();
            {// Comments
                jTextAreaComments = new JTextArea();
                jScrollPaneComments.setViewportView(jTextAreaComments);

                jScrollPaneComments.setPreferredSize(new java.awt.Dimension(
                        403, 32));
            }
        }
        return jScrollPaneComments;
    }

    private JTextField getJTextFieldQuoteCurrency() {
        if (jTextFieldQuoteCurrency == null) {
            jTextFieldQuoteCurrency = new JTextField();
            jTextFieldQuoteCurrency.setEditable(false);
            jTextFieldQuoteCurrency.setFont(new java.awt.Font("Dialog", 1, 12));
        }
        return jTextFieldQuoteCurrency;
    }

    private JTextField getJTextFieldLineTotal() {
        if (jTextFieldLineTotal == null) {
            jTextFieldLineTotal = new JTextField();
            jTextFieldLineTotal.setEditable(false);
        }
        return jTextFieldLineTotal;
    }

    private void initializeFields() {
        jTextFieldQuoteID.setText(quote.getIdQuote());
        if (detail != null) {
            Customer customer = CustomerController.getCustomer(quote
                    .getQteCusid());
            jTextFieldQuoteCurrency.setText(customer.getCusCur());
            jTextFieldQteDetLine.setText(new Integer(detail.getQteDetLine())
                    .toString());
            jTextFieldQteQty.setText(detail.getQteQty().toString());
            jTextFieldProductPrice.setText(detail.getQtePrice().toString());
            getJComboBoxUnitOfMeasure().setSelectedItem(
                    ComboBoxHelper.getSelectedItem(qteUnits,
                            detail.getQteMeasure()));
            jTextFieldLineTotal.setText(calculateLineTotal(detail));
            jComboBoxProduct.setSelectedItem(getSelectedItem(qteProducts,
                    detail.getQteProdid()));
            jTextAreaComments.setText(detail.getQteComments());
            previous = new QuoteDetail(detail);
        } else {
            jTextFieldQteDetLine.setText(new Integer(JDialogQuote
                    .getLineCounter()).toString());
        }
    }

    private String calculateLineTotal(QuoteDetail detail) {
        double total = detail.getQteQty().doubleValue()
                * detail.getQtePrice().doubleValue();
        BigDecimal dec = new BigDecimal(total);
        dec = dec.setScale(2, BigDecimal.ROUND_HALF_UP);
        return dec.toString();
    }

    public JTextField getJTextFieldProductPrice() {
        if (jTextFieldProductPrice == null) {
            jTextFieldProductPrice = new JTextField();
            jTextFieldProductPrice.setText("0.00");
        }
        return jTextFieldProductPrice;
    }

    public JTextField getJTextFieldQteQty() {
        if (jTextFieldQteQty == null) {
            jTextFieldQteQty = new JTextField();
            jTextFieldQteQty.setText("0.00");
        }
        return jTextFieldQteQty;
    }

    private JLabel getJLabelTotal() {
        if (jLabelTotal == null) {
            jLabelTotal = new JLabel();
            jLabelTotal.setText("Totaal :");
        }
        return jLabelTotal;
    }

    private JLabel getJLabelPrijsSelect() {
        if (jLabelPrijsSelect == null) {
            jLabelPrijsSelect = new JLabel();
            jLabelPrijsSelect.setText("Selecteer Prijs :");
        }
        return jLabelPrijsSelect;
    }

    private JLabel getJLabelUnitOfMeasure() {
        if (jLabelUnitOfMeasure == null) {
            jLabelUnitOfMeasure = new JLabel();
            jLabelUnitOfMeasure.setText("Eenh.");
        }
        return jLabelUnitOfMeasure;
    }

    public JComboBox getJComboBoxUnitOfMeasure() {
        if (jComboBoxUnitOfMeasure == null) {
            qteUnits = CodeController.getCodeDetails(CodeEnum.UNIT_OF_MEASURE
                    .getType());// Units of measure

            jComboBoxUnitOfMeasureModel = new DefaultComboBoxModel(qteUnits
                    .keySet().toArray());
            jComboBoxUnitOfMeasure = new JComboBox();
            jComboBoxUnitOfMeasure.setModel(jComboBoxUnitOfMeasureModel);
        }
        return jComboBoxUnitOfMeasure;
    }

}
