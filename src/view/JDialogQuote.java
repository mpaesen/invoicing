package view;

import com.toedter.calendar.JDateChooser;
import info.clearthought.layout.TableLayout;
import model.*;
import output.QuoteOutput;
import persistency.RDBConnection;
import persistency.controller.*;
import utilities.*;
import utilities.Date;
import utilities.Figures;
import utilities.FixTypes;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

import static utilities.ComboBoxHelper.getSelectedItem;

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
public class JDialogQuote extends JDialog {
    private static int lineCounter;
    private JFrame parent;
    private JSplitPane jSplitPaneQuoteDetails;
    private JScrollPane jScrollPane1;
    private JInternalFrame jInternalFrameQuoteHeader;
    private JInternalFrame jInternalFrameQuoteDetail;
    private JLabel jLabelStatus;
    private JLabel jLabelQuoteID;
    private JLabel jLabelDeliveryDate;
    private JLabel jLabelInvoiceAddress;
    private JLabel jLabelVat;
    private JLabel jLabelComments;
    private JLabel jLabelDelivery;
    private JLabel jLabelCustomerType;
    private JLabel jLabelTotalExcl;
    private JSeparator jSeparator1;
    private JPanel jPanelHeaderSouth;
    private JPanel jPanelHeaderNorth;
    private JPanel jPanelSouth;
    private JPanel jPanelQuoteDetail;
    private JPanel jPanelQuoteTotal;
    private JTextField jTextFieldCustomer;
    private JTextField jTextFieldQuoteStatus;
    private JTextField jTextFieldQuoteCurrency;
    private JTextField jTextFieldTotalExcl;
    private JTextField jTextFieldQuoteID;
    private JTextField jTextFieldInvoiceAddress;
    private JTextField jTextFieldCompanyRegistrationNumber;
    private JComboBox jComboBoxAddressSelection;
    private ComboBoxModel jComboBoxAddressSelectionModel;
    private JTable jTableQuoteDetails;
    private TableModel jTableQuoteDetailsModel;
    private JScrollPane jScrollPane2;
    private JDateChooser reqDlvDate;
    private JTextArea jTextAreaErrorMessage;
    private JTextArea jTextAreaComments;
    private JButton jButtonQuoteDetailNew;
    private JButton jButtonQuoteSave;
    private JButton jButtonInvoice;
    private JButton jButtonPrint;
    private JPopupMenu jPopupMenuQuoteDetails;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteQuoteDetail;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateQuoteDetail;
    private Customer customer;
    private Quote quote;
    private TreeMap<String, String> dlvAddresses, quoteStats;
    private int row;
    private double totalExcl = 0.0;
    private Date toDay;
    private CRUDOperationEnum operation;
    private boolean freeFormat;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager
                    .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JDialogQuote(JFrame frame, Quote quote, CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.quote = quote;
        initGUI();
    }

    public JDialogQuote(JFrame frame, Customer customer,
                        CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.customer = customer;
        initGUI();
    }

    private JDialogQuote(JFrame frame, boolean modal,
                         CRUDOperationEnum operation) {
        super(frame, modal);
        this.operation = operation;
        quoteStats = CodeController.getCodeDetails(CodeEnum.QUOTE_STATUS
                .getType());// Quote Status'
        freeFormat = !RDBConnection.getProps().getProperty(Constants.QUOTE_DIALOG)
                .equals(Constants.QUOTE_FIX);

        setLineCounter(0);// initialize line counter
        try {
            this.toDay = new Date();
        } catch (DatumException e) {
            e.printStackTrace();
        }
        this.parent = frame;
    }

    public static int getLineCounter() {
        return ++lineCounter;
    }

    private static void setLineCounter(int lineCounter) {
        JDialogQuote.lineCounter = lineCounter;
    }

    private void initGUI() {
        try {
            {
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jSplitPaneQuoteDetails = new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT);
                getContentPane().add(jSplitPaneQuoteDetails,
                        BorderLayout.CENTER);
                quoteHeader();
                quoteDetails();
                quoteButtons();
                setQuoteTotals();

                getContentPane().add(jPanelSouth, BorderLayout.SOUTH);
                jPanelSouth.setPreferredSize(new java.awt.Dimension(588, 60));
            }
            {
                this.setSize(849, 787);
                this.setLocationRelativeTo(parent);
                this.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void quoteHeader() {
        {// Quote Header
            jInternalFrameQuoteHeader = new JInternalFrame();
            BorderLayout jInternalFrameQuoteHeaderLayout = new BorderLayout();
            jSplitPaneQuoteDetails.add(jInternalFrameQuoteHeader,
                    JSplitPane.LEFT);
            jInternalFrameQuoteHeader.setVisible(true);
            jInternalFrameQuoteHeader.setPreferredSize(new java.awt.Dimension(
                    586, 290));
            jInternalFrameQuoteHeader.setBounds(1, 1, 586, 290);
            jInternalFrameQuoteHeader.getContentPane().setLayout(
                    jInternalFrameQuoteHeaderLayout);
            jInternalFrameQuoteHeader.setTitle("Offerte");
            {
                quoteHeaderNorthPanel();
                quoteHeaderSouthPanel();
                initializeQuoteHeaderFields();
            }

        }
    }

    /**
     *
     */
    private void quoteDetails() {
        {// quote Details
            jInternalFrameQuoteDetail = new JInternalFrame();
            jSplitPaneQuoteDetails.add(jInternalFrameQuoteDetail,
                    JSplitPane.RIGHT);
            BorderLayout jInternalFrameQuoteDetailLayout = new BorderLayout();
            jInternalFrameQuoteDetail.getContentPane().setLayout(
                    jInternalFrameQuoteDetailLayout);
            jInternalFrameQuoteDetail.setBounds(1, 332, 586, 292);
            jInternalFrameQuoteDetail.setPreferredSize(new java.awt.Dimension(
                    586, 292));
            jInternalFrameQuoteDetail.setTitle(Constants.DETAILS);
            {
                {
                    jScrollPane1 = new JScrollPane();
                }
                jInternalFrameQuoteDetail.getContentPane().add(jScrollPane1,
                        BorderLayout.NORTH);
                jScrollPane1.setPreferredSize(new java.awt.Dimension(790, 277));
                jScrollPane1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
                        null, null, null, null));
                jScrollPane1.setEnabled(false);
                {
                    jTableQuoteDetails = new JTable();
                    jScrollPane1.setViewportView(jTableQuoteDetails);
                }
                {// Total
                    jInternalFrameQuoteDetail.getContentPane().add(
                            getJPanelQuoteTotal(), BorderLayout.CENTER);
                }
                {// new quote details
                    jPanelQuoteDetail = new JPanel();
                    jPanelQuoteDetail.add(getJButtonQuoteDetailNew(), "0, 0");
                    getJButtonQuoteDetailNew().addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    new JDialogQuoteDetail(quote, null,
                                            CRUDOperationEnum.NEW);
                                    initializeQuoteDetailFields();
                                }
                            });
                }
                jInternalFrameQuoteDetail.setVisible(true);
                jInternalFrameQuoteDetail.getContentPane().add(
                        jPanelQuoteDetail, BorderLayout.SOUTH);
                jPanelQuoteDetail.setPreferredSize(new java.awt.Dimension(790,
                        46));
                jPanelQuoteDetail.setBorder(BorderFactory
                        .createBevelBorder(BevelBorder.LOWERED));
                initializeQuoteDetailFields();

            }
        }
    }

    /**
     *
     */
    private void quoteButtons() {
        {

            jPanelSouth = new JPanel();
            jPanelSouth.add(getJButtonQuoteSave());
            jPanelSouth.add(getJButtonQuotePrint());
            getJButtonQuoteSave().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        saveInput();
                        initializeButtons();
                    }
                }
            });

            getJButtonQuotePrint().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // disable OK button if printed
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        saveInput();
                        if (quote.getQteStatus().equals(
                                QuoteController.QUOTE_STATUS_CONFIRMED)) {
                            // updateQuoteStatus(QuoteController.QUOTE_STATUS_OPEN);
                        } else {
                            updateQuoteStatus(QuoteController.QUOTE_STATUS_OPEN);
                        }
                        new QuoteOutput(quote).run();
                    }
                }
            });

            jPanelSouth.add(getJButtonQuoteInvoice());
            jPanelSouth.add(getMessage());
            getJButtonQuoteInvoice().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // disable Print button if invoiced
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        updateQuoteStatus(QuoteController.QUOTE_STATUS_PRINTED);
                        int response = JOptionPaneCreateInvoice.confirm(quote
                                .getIdQuote());
                        if (response == JOptionPane.YES_OPTION) {
                            Invoice newInvoice = InvoiceController
                                    .createNewInvoice(quote);
                            InvoiceDetailController.createNewInvoiceDetails(
                                    newInvoice, quote);
                            new JDialogInvoice(parent, newInvoice,
                                    CRUDOperationEnum.UPDATE);
                        }
                    }
                }
            });

            initializeButtons();
            // add message after buttons
            resetMessage();
        }
    }

    /**
     *
     */
    private void initializeButtons() {
        getJButtonQuoteDetailNew().setEnabled(false);
        getJButtonQuotePrint().setEnabled(false);
        getJButtonQuoteInvoice().setEnabled(false);

        if (quote != null) {
            getJButtonQuoteSave().setEnabled(false);
            if ((quote.getQteStatus().equals(QuoteController.QUOTE_STATUS_OPEN))) {
                getJButtonQuoteSave().setEnabled(true);
            }
            if (quote.getQteStatus().equals(
                    QuoteController.QUOTE_STATUS_PRINTED)) {
                getJButtonQuoteInvoice().setEnabled(true);
            }
            if (jTableQuoteDetailsModel.getRowCount() > 0) {
                // && (!quote.getQteStatus().equals(
                // QuoteController.QUOTE_STATUS_CONFIRMED))) {
                getJButtonQuotePrint().setEnabled(true);

            }
            if (((quote.getQteStatus()
                    .equals(QuoteController.QUOTE_STATUS_OPEN)))
                    || (quote.getQteStatus()
                    .equals(QuoteController.QUOTE_STATUS_PRINTED))) {
                jTableQuoteDetails.setEnabled(true);
                // FixFormat
                if (!freeFormat) {

                    getJButtonQuoteDetailNew().setEnabled(true);
                    setComponentPopupMenu(jTableQuoteDetails,
                            getJPopupMenuQuoteDetails());
                }
            }
        }
    }

    private void updateQuoteStatus(String previousStatus) {
        if (previousStatus.equals(QuoteController.QUOTE_STATUS_PRINTED)) {
            quote = updateExistingQuote(QuoteController.QUOTE_STATUS_CONFIRMED);
            QuoteController.updateQuote(quote);
        }
        if (previousStatus.equals(QuoteController.QUOTE_STATUS_OPEN)) {
            quote = updateExistingQuote(QuoteController.QUOTE_STATUS_PRINTED);
            QuoteController.updateQuote(quote);
        }
        // Refresh buttons & quoteheader after status modification
        initializeButtons();
        initializeQuoteHeaderFields();
    }

    /**
     *
     */
    private void initializeQuoteDetailFields() {
        final int DEFAULT_PIXELS = 20;
        int[] columnWidth = new int[getQuoteDetailTitles().length];
        jTableQuoteDetailsModel = new MyTableModel(columnWidth);

        jTableQuoteDetails.setModel(jTableQuoteDetailsModel);
        if (freeFormat) {
            jTableQuoteDetails.setDefaultEditor(Double.class,
                    new DefaultCellEditor(new JFormattedTextField()));
        } else {
            for (int i = 0; i < columnWidth.length; i++) {
                TableColumn column = jTableQuoteDetails.getColumnModel()
                        .getColumn(i);
                column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
            }
        }
        jTableQuoteDetails.getTableHeader().setFont(
                new java.awt.Font("Dialog", 1, 12));
        jTableQuoteDetails.setFont(new java.awt.Font("Courier New", 0, 14));
        jTableQuoteDetails.getTableHeader().setToolTipText(
                "Offerte detaillijnen");
        jTableQuoteDetails.setEnabled(false);

        if (freeFormat) {
            // Set up column sizes.
            initColumnSizes(jTableQuoteDetails);
        } else {

            if (getJPopupMenuQuoteDetails() != null) {
                getJPopupMenuQuoteDetails().setEnabled(false);
                jTableQuoteDetails.remove(getJPopupMenuQuoteDetails());
            }
        }
        setQuoteTotals();
    }

    private JPopupMenu getJPopupMenuQuoteDetails() {
        if (jPopupMenuQuoteDetails == null) {
            jPopupMenuQuoteDetails = new JPopupMenu();
            jPopupMenuQuoteDetails.setBounds(219, 128, 76, 19);
            ButtonGroup group = new ButtonGroup();
            group.add(getJRadioButtonMenuItemUpdateQuoteDetail());
            group.add(getJRadioButtonMenuItemDeleteQuoteDetail());
            getJRadioButtonMenuItemUpdateQuoteDetail().setSelected(true);
            jPopupMenuQuoteDetails
                    .add(getJRadioButtonMenuItemUpdateQuoteDetail());
            jPopupMenuQuoteDetails
                    .add(getJRadioButtonMenuItemDeleteQuoteDetail());

        }
        return jPopupMenuQuoteDetails;
    }

    /**
     *
     */
    private void quoteHeaderNorthPanel() {
        {// North panel

            jPanelHeaderNorth = new JPanel();

            TableLayout jPanelHeaderNorthLayout = new TableLayout(
                    new double[][]{
                            {134.0, TableLayout.FILL, TableLayout.FILL,
                                    TableLayout.FILL},
                            {TableLayout.FILL, TableLayout.FILL,
                                    TableLayout.FILL, TableLayout.FILL}});
            jPanelHeaderNorthLayout.setHGap(5);
            jPanelHeaderNorthLayout.setVGap(5);
            jPanelHeaderNorth.setLayout(jPanelHeaderNorthLayout);
            jInternalFrameQuoteHeader.getContentPane().add(jPanelHeaderNorth,
                    BorderLayout.NORTH);
            jPanelHeaderNorth
                    .setPreferredSize(new java.awt.Dimension(576, 130));
            {
                jTextFieldCustomer = new JTextField();
                jPanelHeaderNorth.add(jTextFieldCustomer, "1, 0, 2, 0");
                jPanelHeaderNorth.add(getJLabelCustomerType(), "0, 0");
                jTextFieldCustomer.setPreferredSize(new java.awt.Dimension(231,
                        20));
                jTextFieldCustomer.setEditable(false);
                jTextFieldCustomer.setFont(new java.awt.Font("Dialog", 0, 18));
            }
            {
                jLabelVat = new JLabel();
                jPanelHeaderNorth.add(jLabelVat, "0, 1");
                jLabelVat.setText("Ondernemersnummer :");
            }
            {
                jTextFieldCompanyRegistrationNumber = new JTextField();
                jPanelHeaderNorth.add(jTextFieldCompanyRegistrationNumber,
                        "1, 1");
                jTextFieldCompanyRegistrationNumber
                        .setPreferredSize(new java.awt.Dimension(240, 20));
                jTextFieldCompanyRegistrationNumber.setEditable(false);
                jTextFieldCompanyRegistrationNumber.setFont(new java.awt.Font(
                        "Dialog", 0, 16));
            }
            {
                jLabelInvoiceAddress = new JLabel();
                jPanelHeaderNorth.add(jLabelInvoiceAddress, "0, 2");
                jLabelInvoiceAddress.setText("Facturatie adres :");
            }
            {
                jTextFieldInvoiceAddress = new JTextField();
                jPanelHeaderNorth.add(jTextFieldInvoiceAddress, "1, 2, 3, 2");
                jPanelHeaderNorth.add(getJLabelDelivery(), "0, 3");

                jTextFieldInvoiceAddress.setEditable(false);
                jTextFieldInvoiceAddress.setFont(new java.awt.Font("Dialog", 1,
                        12));
            }
        }
    }

    /**
     *
     */
    private void quoteHeaderSouthPanel() {
        {// South Panel
            jPanelHeaderSouth = new JPanel();
            TableLayout jPanelHeaderSouthLayout = new TableLayout(
                    new double[][]{
                            {132.0, 103.0, 23.0, 110.0, 132.0, 45.0,
                                    TableLayout.FILL},
                            {27.0, 28.0, 46.0, TableLayout.FILL}});
            jPanelHeaderSouthLayout.setHGap(5);
            jPanelHeaderSouthLayout.setVGap(5);
            jPanelHeaderSouth.setLayout(jPanelHeaderSouthLayout);
            jInternalFrameQuoteHeader.getContentPane().add(jPanelHeaderSouth,
                    BorderLayout.SOUTH);
            jInternalFrameQuoteHeader.getContentPane().add(getJSeparator1(),
                    BorderLayout.CENTER);
            jPanelHeaderSouth
                    .setPreferredSize(new java.awt.Dimension(576, 117));
            {
                jLabelDeliveryDate = new JLabel();
                jPanelHeaderSouth.add(jLabelDeliveryDate, "3, 0");
                jLabelDeliveryDate.setText(" Leverdatum :");
            }
            {
                reqDlvDate = new JDateChooser();
                jPanelHeaderSouth.add(reqDlvDate, "4, 0, 5, 0");
                reqDlvDate.setDateFormatString("d MMM, yyyy");
                reqDlvDate.setToolTipText("Startdatum van de werken");
            }
            {
                jLabelQuoteID = new JLabel();
                jPanelHeaderSouth.add(jLabelQuoteID, "0, 0");
                jLabelQuoteID.setText("Offerte Nummer :");
            }
            {
                jTextFieldQuoteID = new JTextField();
                jPanelHeaderSouth.add(jTextFieldQuoteID, "1, 0, 2, 0");
                jTextFieldQuoteID.setEditable(false);
                jTextFieldQuoteID.setFont(new java.awt.Font("Dialog", 1, 18));
            }
            {
                jPanelHeaderSouth.add(getJLabelStatus(), "0, 1");
                jPanelHeaderSouth.add(getJTextFieldQuoteStatus(), "1, 1");
                jPanelHeaderSouth.add(getJLabelComments(), "0, 2");
                jPanelHeaderSouth.add(getJScrollPane2(), "1, 2, 5, 2");
            }
        }
    }

    /**
     *
     */
    private void initializeQuoteHeaderFields() {
        Address address = null;
        jTextFieldQuoteStatus.setText(CodeController.getOneCodeDetail(
                CodeEnum.QUOTE_STATUS.getType(),
                QuoteController.DEFAULT_QUOTE_STATUS).getCodeDesc());
        getJTextAreaComments().setEnabled(true);
        if (quote != null) {
            customer = CustomerController.getCustomer(quote.getQteCusid());
            if (quote.getQteDlvAddid() != null) {
                address = AddressController.getAddress(quote.getQteDlvAddid());
                jPanelHeaderNorth.add(getJComboBoxAddressSelection(),
                        "1, 3, 3, 3");
                jComboBoxAddressSelection.setSelectedItem(getSelectedItem(
                        dlvAddresses, quote.getQteDlvAddid()));

            }
            jTextFieldQuoteID.setText(quote.getIdQuote());
            if (quote.getQteHeaderComments() == null) {
                getJTextAreaComments().setText(Constants.EMPTY);
            }

            getJTextAreaComments().setText(quote.getQteHeaderComments());
            jTextFieldQuoteStatus.setText(CodeController.getOneCodeDetail(
                    CodeEnum.QUOTE_STATUS.getType(), quote.getQteStatus())
                    .getCodeDesc());

            reqDlvDate.setDate(new GregorianCalendar(quote.getQteReqDlvDate()
                    .getJaar(), quote.getQteReqDlvDate().getMaand(), quote
                    .getQteReqDlvDate().getDag()).getTime());
            // no modifications allowed if confirmed
            if (quote.getQteStatus().equals(
                    QuoteController.QUOTE_STATUS_CONFIRMED)) {
                reqDlvDate.setEnabled(false);
                getJComboBoxAddressSelection().setEnabled(false);
                getJTextAreaComments().setEnabled(false);
            }
        }
        if (customer != null) {
            getJLabelCustomerType().setText(
                    CodeController.getOneCodeDetail(
                            CodeEnum.CUSTOMER_TYPE.getType(),
                            customer.getCusType()).getCodeDesc());
            jTextFieldCustomer.setText(customer.getCusName());
            jTextFieldCompanyRegistrationNumber.setText(customer.getCusVat());
            List<Address> list = getAddressesByCustomer(FixTypes.INVOICE_ADRESS_TYPE);
            if (!list.isEmpty()) {
                address = list.get(0);
                jTextFieldInvoiceAddress.setText(address.toString());
                if ((dlvAddresses == null) || dlvAddresses.isEmpty()) {
                    getExistingAddresses(FixTypes.DELIVERY_ADRESS_TYPE);
                }
                if ((dlvAddresses == null) || dlvAddresses.isEmpty()) {
                    getExistingAddresses(FixTypes.INVOICE_ADRESS_TYPE);
                }
            }
            jPanelHeaderNorth.add(getJComboBoxAddressSelection(), "1, 3, 3, 3");

        }

    }

    /**
     * @return
     */
    private String[] getQuoteDetailTitles() {
        return new String[]{Constants.LN, Constants.DESC, Constants.QUANTITY,
                Constants.PRICE, Constants.UNIT, Constants.TOTAL};
    }

    /**
     * @return
     */
    private Object[][] getQuoteDetailColumns(int[] columnWidth) {
        Object[][] columns = null;
        if (freeFormat) {
            columns = new Object[Figures.MAX_QTE_DETAIL_LINES][Figures.MAX_QTE_DETAIL_COLUMNS];
        }
        int i = 0;
        int last = 0;

        if (customer != null && quote != null) {
            QuoteDetail detail;
            Collection<Business> list = QuoteDetailController
                    .readQuoteDetails(quote.getIdQuote());
            // #lines limited!
            if (!freeFormat) {
                columns = new Object[list.size()][];
            }

            Iterator<Business> it = list.iterator();
            totalExcl = 0f;

            while (it.hasNext()) {
                detail = (QuoteDetail) it.next();
                last = detail.getQteDetLine();
                setLineCounter(last);// last detail line
                columns[i] = new Object[]{
                        new Integer(detail.getQteDetLine()),
                        (detail.getQteProdid() != null ? ProductController
                                .getProduct(detail.getQteProdid())
                                .getProdDesc() :
                                detail.getQteComments()),
                        new BigDecimal(detail.getQteQty().doubleValue())
                                .setScale(1, BigDecimal.ROUND_HALF_UP),
                        new BigDecimal(detail.getQtePrice().doubleValue())
                                .setScale(2, BigDecimal.ROUND_HALF_UP),
                        // CodeController.getOneCodeDetail(
                        // CodeEnum.UNIT_OF_MEASURE.getType(),
                        // detail.getQteMeasure()).getCodeDet(),
                        detail.getQteMeasure(),
                        getQuoteLineTotal(detail.getQteQty(),
                                detail.getQtePrice()).setScale(2,
                                BigDecimal.ROUND_HALF_UP)};
                if (!freeFormat) {
                    calculateColumnWidth(columns[i], columnWidth);
                }

                i++;
            }
        }
        // fill up until 20 lines
        setLineCounter(last);
        if (freeFormat) {
            // skip deleted lines!
            if (i < last) {
                last = i;
            }
            fillDetailLines(last, columns);
        }
        return columns;
    }

    /**
     * @param columns
     */
    private void fillDetailLines(int start, Object[][] columns) {
        for (int i = start; i < columns.length; i++) {
            columns[i] = new Object[]{new Integer(getLineCounter()),
                    new String(), new BigDecimal(0d), new BigDecimal(0d),
                    new String(), new BigDecimal(0d), new BigDecimal(0d)};
        }
    }

    private BigDecimal getQuoteLineTotal(BigDecimal qty, BigDecimal price) {
        BigDecimal lineTotal = qty.multiply(price);
        totalExcl = totalExcl + lineTotal.doubleValue();
        return lineTotal;
    }

    private void setQuoteTotals() {
        getJTextFieldQuoteCurrency().setText(customer.getCusCur());
        getJTextFieldQuoteTotalExcl().setText(
                String.format("%25.2f", totalExcl));
    }

    /**
     * All deliveradresses or only the invoice address
     *
     * @param type
     * @return
     */
    private List<Address> getAddressesByCustomer(String type) {

        ArrayList<Address> list = new ArrayList<Address>();
        if (customer != null) {
            Address address;
            String[] filter = getAddressFilter(type);
            Collection<Business> businessList = AddressController
                    .getAddresses(filter);
            Iterator<Business> it = businessList.iterator();

            while (it.hasNext()) {
                address = (Address) it.next();
                list.add(address);
            }

        } else {
            list = null;
        }
        return list;
    }

    private String[] getAddressFilter(String type) {

        String id = customer.getIdCus();
        String[] filter = {id, type, "true"};
        return filter;
    }

    private JTextArea getMessage() {
        if (jTextAreaErrorMessage == null) {
            jTextAreaErrorMessage = new JTextArea();
            jTextAreaErrorMessage.setPreferredSize(new java.awt.Dimension(769,
                    16));
            jTextAreaErrorMessage.setEditable(false);
        }
        return jTextAreaErrorMessage;
    }

    private JLabel getJLabelStatus() {
        if (jLabelStatus == null) {
            jLabelStatus = new JLabel();
            jLabelStatus.setText("Status :");
        }
        return jLabelStatus;
    }

    private JTextField getJTextFieldQuoteCurrency() {
        if (jTextFieldQuoteCurrency == null) {
            jTextFieldQuoteCurrency = new JTextField();
            jTextFieldQuoteCurrency.setEditable(false);
            jTextFieldQuoteCurrency.setFont(new java.awt.Font("Dialog", 1, 14));
        }
        return jTextFieldQuoteCurrency;
    }

    private JLabel getJLabelCustomerType() {
        if (jLabelCustomerType == null) {
            jLabelCustomerType = new JLabel();
            jLabelCustomerType.setFont(new java.awt.Font("Dialog", 1, 14));
        }
        return jLabelCustomerType;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private JComboBox getJComboBoxAddressSelection() {
        if (jComboBoxAddressSelection == null) {
            getExistingAddresses(FixTypes.DELIVERY_ADRESS_TYPE);
        }
        if (jComboBoxAddressSelectionModel.getSize() == 0) {
            getExistingAddresses(FixTypes.INVOICE_ADRESS_TYPE);
        }

        return jComboBoxAddressSelection;
    }

    /**
     *
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getExistingAddresses(String addressType) {
        dlvAddresses = AddressController
                .getAddressDetails(getAddressFilter(addressType));
        jComboBoxAddressSelectionModel = new DefaultComboBoxModel(dlvAddresses
                .keySet().toArray());
        jComboBoxAddressSelection = new JComboBox();
        jComboBoxAddressSelection.setModel(jComboBoxAddressSelectionModel);
    }

    private JButton getJButtonQuoteSave() {
        if (jButtonQuoteSave == null) {
            jButtonQuoteSave = new JButton(Constants.OK);
            jButtonQuoteSave.setToolTipText(Constants.SAVE);
        }
        return jButtonQuoteSave;
    }

    private JButton getJButtonQuotePrint() {
        if (jButtonPrint == null) {
            jButtonPrint = new JButton();
            jButtonPrint.setText(Constants.PRINT);
            jButtonPrint.setToolTipText(Constants.QOUTE_PRINT);
        }
        return jButtonPrint;
    }

    private JButton getJButtonQuoteDetailNew() {
        if (jButtonQuoteDetailNew == null) {
            jButtonQuoteDetailNew = new JButton();
            jButtonQuoteDetailNew.setText(Constants.NEW);
            jButtonQuoteDetailNew.setToolTipText(Constants.NEW_DETAIL);
            jButtonQuoteDetailNew.setPreferredSize(new java.awt.Dimension(81,
                    26));
        }
        return jButtonQuoteDetailNew;
    }

    private JButton getJButtonQuoteInvoice() {
        if (jButtonInvoice == null) {
            jButtonInvoice = new JButton();
            jButtonInvoice.setText(Constants.CONFIRM);
        }
        return jButtonInvoice;
    }

    private JLabel getJLabelDelivery() {
        if (jLabelDelivery == null) {
            jLabelDelivery = new JLabel();
            jLabelDelivery.setText(Constants.DELIVERY_ADDRESS);
        }
        return jLabelDelivery;
    }

    private JTextField getJTextFieldQuoteStatus() {
        if (jTextFieldQuoteStatus == null) {
            jTextFieldQuoteStatus = new JTextField();
            jTextFieldQuoteStatus.setEditable(false);
        }
        return jTextFieldQuoteStatus;
    }

    private JSeparator getJSeparator1() {
        if (jSeparator1 == null) {
            jSeparator1 = new JSeparator();
            jSeparator1.setBorder(BorderFactory
                    .createBevelBorder(BevelBorder.LOWERED));
            jSeparator1.setEnabled(false);
            jSeparator1.setPreferredSize(new java.awt.Dimension(576, 6));
        }
        return jSeparator1;
    }

    /**
     * @param errorMessages
     */
    private boolean validateInput(StringBuilder errorMessages) {
        resetMessage();
        validateQuoteHeader(errorMessages);
        if (!errorMessages.toString().equals(Constants.EMPTY)) {
            redMessage(errorMessages);
            return false;
        }
        // Only test details if free format
        if (freeFormat) {
            validateQuoteDetail(errorMessages);
            if (!errorMessages.toString().equals(Constants.EMPTY)) {
                redMessage(errorMessages);
                return false;
            }
        }

        return true;
    }

    synchronized private void saveInput() {

        if ((quote == null) && (operation == CRUDOperationEnum.NEW)) {
            Quote newQuote = createNewQuote();
            QuoteController.createQuote(newQuote);
            quote = new Quote(newQuote);
            greenMessage(newQuote.getIdQuote() + Constants.ADDED);
        }
        if (operation == CRUDOperationEnum.UPDATE) {

            String status = quoteStats.get(jTextFieldQuoteStatus.getText());
            Quote newQuote = updateExistingQuote(status);

            if (!quote.equals(newQuote)) {
                QuoteController.updateQuote(newQuote);
                quote = new Quote(newQuote);
                greenMessage(newQuote.getIdQuote() + Constants.CHANGED);
            }
        }
        // All modifications should be shown real-time
        if (freeFormat) {
            for (int row = 0; row < Figures.MAX_QTE_DETAIL_LINES; row++) {
                if (!lineIsEmpty(row)) {
                    saveDetail(row);
                } else {
                    QuoteDetail detail = QuoteDetailController
                            .getOneQuoteDetail(quote.getIdQuote(),
                                    (Integer) jTableQuoteDetails.getModel()
                                            .getValueAt(row, 0));
                    if ((detail != null)
                            && (detail.getQtePrice().doubleValue() != Figures.ZERO)) {
                        QuoteDetailController.removeQuoteDetail(detail);
                    }
                }
            }
        }
        initializeQuoteDetailFields();
        setQuoteTotals();
        initializeQuoteHeaderFields();

    }

    /**
     * @return
     */
    private QuoteDetail createDetail(int row) {
        QuoteDetail newDetail = new QuoteDetail(quote.getIdQuote(),
                (Integer) jTableQuoteDetails.getModel().getValueAt(row, 0), // line
                null, // product
                new BigDecimal(jTableQuoteDetails.getModel().getValueAt(row, 2)
                        .toString()), // qty
                jTableQuoteDetails.getModel().getValueAt(row, 4).toString(), // unit
                new BigDecimal(jTableQuoteDetails.getModel().getValueAt(row, 3)
                        .toString()), // price
                jTableQuoteDetails.getModel().getValueAt(row, 1).toString(), // Comment

                true); // Comment
        return newDetail;
    }

    synchronized private void saveDetail(int row) {
        QuoteDetail detail = QuoteDetailController.getOneQuoteDetail(quote
                .getIdQuote(), (Integer) jTableQuoteDetails.getModel()
                .getValueAt(row, 0));
        QuoteDetail newDetail = createDetail(row);
        if ((detail == null)) {
            QuoteDetailController.createQuoteDetail(newDetail);
            detail = new QuoteDetail(newDetail);
            greenMessage(Constants.LINE + newDetail.getQteDetLine()
                    + Constants.ADDED);
        } else {
            if (!detail.equals(newDetail)) {
                QuoteDetailController.updateQuoteDetail(newDetail);
                detail = new QuoteDetail(newDetail);
                greenMessage(Constants.LINE + newDetail.getQteDetLine()
                        + Constants.CHANGED);
            }
        }
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Quote updateExistingQuote(String status) {
        String addresId = dlvAddresses.get(jComboBoxAddressSelection
                .getSelectedItem().toString());
        String comments = getJTextAreaComments().getText();
        String quoteID = quote.getIdQuote();

        Quote newQuote = new Quote(quoteID, customer.getIdCus(), addresId,
                quote.getQteCrtdate(), quote.getQteCrtUserid(), // qtecrtuserid
                toDay, // qteupddate | datetime
                Integer.toHexString(1), // qteupduserid | char(15)
                getReqDlvDate(), // qtereqdlvdate
                status, // qteStatus | char(5)
                quote.getQteType(), // qteType | char(5)
                quote.isQteVat(), // qteVat | tinyint(1)
                comments, // qteHeadercomments varchar(256)
                quote.isActive() // Active | tinyint(1)
        );
        return newQuote;
    }

    /**
     * @param deliveryDate
     * @return
     */
    private Quote createNewQuote() {
        String addresId = dlvAddresses.get(jComboBoxAddressSelection
                .getSelectedItem().toString());
        String status = quoteStats.get(jTextFieldQuoteStatus.getText());
        String comments = getJTextAreaComments().getText();

        String quoteID = NumberController.readLastNumber(
                NumberEnum.QUOTE.getType(), toDay.getJaar()).toString();

        Quote newQuote = new Quote(quoteID, customer.getIdCus(), addresId,
                toDay, Integer.toHexString(1), // qtecrtuserid | char (15)
                toDay, // qteupddate | datetime
                Integer.toHexString(1), // qteupduserid | char(15)
                getReqDlvDate(), // qtereqdlvdate | date
                status, // qteStatus | char(5)
                QuoteController.DEFAULT_QUOTE_TYPE, // qteType | char(5)
                // (default value: Offer)
                true, // qteVat | tinyint(1)
                comments, // qteHeadercomments varchar(256)
                true // Active | tinyint(1)
        );
        return newQuote;
    }

    /**
     * @return
     */
    private Date getReqDlvDate() {
        Date deliveryDate = null;
        try {
            deliveryDate = new Date();
            deliveryDate.setDatum(reqDlvDate.getCalendar().get(Calendar.DATE),
                    reqDlvDate.getCalendar().get(Calendar.MONTH), reqDlvDate
                            .getCalendar().get(Calendar.YEAR));
        } catch (DatumException e) {
            e.printStackTrace();
        }
        return deliveryDate;
    }

    private boolean validateQuoteHeader(StringBuilder errorMessages) {
        if (jTextFieldInvoiceAddress.getText().equals(Constants.EMPTY)) {
            errorMessages.append(Constants.NO_INVOICE_ADDRESS);
            return false;
        }
        if (jComboBoxAddressSelection.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_DELIVERY_ADDRESS);
            return false;
        }
        if (reqDlvDate.getCalendar() == null) {
            errorMessages.append(Constants.NO_DATE);
            return false;
        }
        if (getReqDlvDate().kleinerDan(toDay)) {
            errorMessages.append(Constants.DATE_FUTURE);
            return false;
        }
        return true;
    }

    /**
     * @param errorMessages
     * @return
     */
    private boolean validateQuoteDetail(StringBuilder errorMessages) {
        for (int row = 0; row < Figures.MAX_QTE_DETAIL_LINES; row++) {
            for (int col = 1; col < Figures.MAX_QTE_DETAIL_COLUMNS; col++) {
                if (!lineIsEmpty(row)) {
                    if (!validCellUpdated(row, col)) {
                        errorMessages.append(Constants.INVALID_DETAIL_LINE);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @param errorMessages
     */
    private void redMessage(StringBuilder errorMessages) {
        jTextAreaErrorMessage.setText(errorMessages.toString());
        jTextAreaErrorMessage.setBackground(new java.awt.Color(255, 0, 0));
    }

    private void resetMessage() {
        jTextAreaErrorMessage.setText(Constants.EMPTY);
        jTextAreaErrorMessage.setBackground(new java.awt.Color(238, 238, 238));
    }

    /**
     * @param announcement
     */
    private void greenMessage(String announcement) {
        jTextAreaErrorMessage.setText(announcement);
        jTextAreaErrorMessage.setBackground(new java.awt.Color(0, 255, 0));

    }

    /**
     * Auto-generated method for setting the popup menu for a component
     */
    private void setComponentPopupMenu(final java.awt.Component parent,
                                       final javax.swing.JPopupMenu menu) {
        parent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                menu.show(parent, e.getX(), e.getY());
                JTable table = (JTable) e.getSource();
                table.clearSelection();
                Point p = e.getPoint();
                row = table.rowAtPoint(p);

            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                menu.show(parent, e.getX(), e.getY());
                JTable table = (JTable) e.getSource();
                table.clearSelection();
                Point p = e.getPoint();
                row = table.rowAtPoint(p);

            }
        });
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateQuoteDetail() {
        if (jRadioButtonMenuItemUpdateQuoteDetail == null) {
            jRadioButtonMenuItemUpdateQuoteDetail = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateQuoteDetail.setText(Constants.DETAILS);
            jRadioButtonMenuItemUpdateQuoteDetail
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // no updates allowed when quote is confirmed
                            if (!quote.getQteStatus().equals(
                                    QuoteController.QUOTE_STATUS_CONFIRMED)) {
                                if (!freeFormat) {
                                    Integer line = Integer
                                            .valueOf(jTableQuoteDetailsModel
                                                    .getValueAt(row, 0)
                                                    .toString().trim());// Quote
                                    // Detail
                                    // number
                                    QuoteDetail detail = QuoteDetailController
                                            .getOneQuoteDetail(
                                                    quote.getIdQuote(), line);
                                    new JDialogQuoteDetail(quote, detail,
                                            CRUDOperationEnum.UPDATE);
                                } else {
                                    JOptionPane.showMessageDialog(parent,
                                            Constants.QUOTE_UPDATE_IMPOSSIBLE);

                                }
                            } else {
                                JOptionPane.showMessageDialog(parent,
                                        Constants.QUOTE_UPDATE_NOT_ALLOWED);
                            }
                            initializeQuoteDetailFields();
                        }
                    });

        }
        return jRadioButtonMenuItemUpdateQuoteDetail;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteQuoteDetail() {
        if (jRadioButtonMenuItemDeleteQuoteDetail == null) {
            jRadioButtonMenuItemDeleteQuoteDetail = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteQuoteDetail.setText(Constants.REMOVE);
            jRadioButtonMenuItemDeleteQuoteDetail
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // no updates allowed when quote is confirmed
                            if (!quote.getQteStatus().equals(
                                    QuoteController.QUOTE_STATUS_CONFIRMED)) {

                                Integer line = Integer
                                        .valueOf(jTableQuoteDetailsModel
                                                .getValueAt(row, 0).toString()
                                                .trim());// Quote Detail
                                int response = JOptionPaneItemRemove
                                        .confirm(" lijn " + line);
                                if (response == JOptionPane.YES_OPTION) {
                                    QuoteDetail detail = QuoteDetailController
                                            .getOneQuoteDetail(
                                                    quote.getIdQuote(), line);
                                    QuoteDetailController
                                            .removeQuoteDetail(detail);
                                    initializeQuoteDetailFields();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        Constants.QUOTE_DELETE_NOT_ALLOWED);
                            }
                        }
                    });

        }
        return jRadioButtonMenuItemDeleteQuoteDetail;
    }

    private JLabel getJLabelComments() {
        if (jLabelComments == null) {
            jLabelComments = new JLabel();
            jLabelComments.setText("Referentie :");
        }
        return jLabelComments;
    }

    public JTextArea getJTextAreaComments() {
        if (jTextAreaComments == null) {
            jTextAreaComments = new JTextArea();
            jTextAreaComments
                    .setPreferredSize(new java.awt.Dimension(370, 100));
        }
        return jTextAreaComments;
    }

    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setViewportView(getJTextAreaComments());
        }
        return jScrollPane2;
    }

    public JPanel getJPanelQuoteTotal() {
        if (jPanelQuoteTotal == null) {
            jPanelQuoteTotal = new JPanel();
            TableLayout jPanelQuoteTotalLayout = new TableLayout(
                    new double[][]{{406.0, 157.0, 74.0, TableLayout.FILL},
                            {TableLayout.FILL}});
            jPanelQuoteTotalLayout.setHGap(5);
            jPanelQuoteTotalLayout.setVGap(5);
            jPanelQuoteTotal.setLayout(jPanelQuoteTotalLayout);
            jPanelQuoteTotal.setPreferredSize(new java.awt.Dimension(790, 46));
            jPanelQuoteTotal.add(getJLabelTotalExcl(), "1,0,c,f");
            jPanelQuoteTotal.add(getJTextFieldQuoteTotalExcl(), "3,0,r,f");
            jPanelQuoteTotal.add(getJTextFieldQuoteCurrency(), "2,0,r,f");
        }
        return jPanelQuoteTotal;
    }

    private JTextField getJTextFieldQuoteTotalExcl() {
        if (jTextFieldTotalExcl == null) {
            jTextFieldTotalExcl = new JTextField();
            jTextFieldTotalExcl.setEditable(false);
            jTextFieldTotalExcl.setFont(new java.awt.Font("Dialog", 1, 16));
        }
        return jTextFieldTotalExcl;
    }

    private JLabel getJLabelTotalExcl() {
        if (jLabelTotalExcl == null) {
            jLabelTotalExcl = new JLabel();
            jLabelTotalExcl.setText("Totaal (excl):");
            jLabelTotalExcl.setFont(new java.awt.Font("Dialog", 1, 16));
        }
        return jLabelTotalExcl;
    }

    private void calculateColumnWidth(Object[] row, int[] columnWidth) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].toString().length() > columnWidth[i]) {
                columnWidth[i] = row[i].toString().length();
            }
        }
    }

    /*
     * This method picks good column sizes. If all column heads are wider than
     * the column's cells' contents, then you can just use
     * column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        GenericTableModel model = (GenericTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = {new Integer(99), Constants.GENERAL_PURPOSE,
                new BigDecimal(1000000), new BigDecimal(1000000),
                Constants.UNIT, new BigDecimal(10000000)};
        TableCellRenderer headerRenderer = table.getTableHeader()
                .getDefaultRenderer();
        for (int i = 0; i < Figures.MAX_QTE_DETAIL_COLUMNS; i++) {
            column = table.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(null,
                    column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = table.getDefaultRenderer(model.getColumnClass(i))
                    .getTableCellRendererComponent(table, longValues[i], false,
                            false, 0, i);

            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    public boolean lineIsEmpty(int row) {
        Object object = null;
        for (int col = 1; col < Figures.MAX_QTE_DETAIL_COLUMNS - 2; col++) {
            object = jTableQuoteDetails.getModel().getValueAt(row, col);
            switch (col) {
                case 1:
                    // Free text is allowed
                    if (!object.toString().equals(Constants.EMPTY)) {
                        return false;
                    }
                    break;

                case 2:
                    // Quantity
                    if (extractDouble(object) != 0d) {
                        return false;
                    }
                    break;

                case 3:
                    // Price
                    if (extractDouble(object) != 0d) {
                        return false;
                    }
                    break;
                case 4:
                    // Unit of measure not necessary
                    // if (!object.toString().equals(Constants.EMPTY)) {
                    // return false;
                    // }
                    break;
            }
        }
        return true;
    }

    private boolean validCellUpdated(int row, int col) {
        Object object = jTableQuoteDetails.getModel().getValueAt(row, col);

        switch (col) {
            case 1: {
                // Free tekst not necessary
                // if (object.toString().equals(Constants.EMPTY)) {
                // JOptionPane
                // .showMessageDialog(parent, Constants.NO_PRODUCT_DESC);
                // return false;
                // }
                break;
            }
            case 2:
                if ((object == null) || (object.toString().equals(Constants.EMPTY))) {

                    JOptionPane.showMessageDialog(parent,
                            Constants.ONLY_NUMBERS_ALLOWED);

                    return false;
                }
                break;
            case 3:
                if ((object == null) || (object.toString().equals(Constants.EMPTY))) {

                    JOptionPane.showMessageDialog(parent,
                            Constants.ONLY_NUMBERS_ALLOWED);
                    return false;
                }
                break;
            case 4: {
                // Unit of measure not obliged
                // if (object.toString().equals(Constants.EMPTY)) {
                // JOptionPane.showMessageDialog(parent, Constants.NO_MEASURE);
                // return false;
                // }
                break;
            }
        }
        return true;
    }

    /**
     * @param object
     */
    @SuppressWarnings("finally")
    private double extractDouble(Object object) {
        double doubleValue = 0d;
        try {
            doubleValue = Double.parseDouble(object.toString());
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(parent,
                    Constants.ONLY_NUMBERS_ALLOWED);
        }
        return doubleValue;
    }

    class MyTableModel extends GenericTableModel {

        MyTableModel(int[] columnWidth) {
            super(columnWidth);
        }

        public void setTableModel(int[] columnWidth) {
            setColumnNames(getQuoteDetailTitles());
            setData(getQuoteDetailColumns(columnWidth));
        }

        public void updateLineTotal(int row) {
            double qty = extractDouble(jTableQuoteDetails.getModel()
                    .getValueAt(row, 2));
            double price = extractDouble(jTableQuoteDetails.getModel()
                    .getValueAt(row, 3));

            double total = qty * price;
            ((GenericTableModel) jTableQuoteDetails.getModel()).getData()[row][Figures.MAX_QTE_DETAIL_COLUMNS - 1] = new BigDecimal(
                    total).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setValueAt(Object value, int row, int col) {
            setValue(value, row, col);
            fireTableCellUpdated(row, col);
            if (validCellUpdated(row, col)) {
                updateLineTotal(row);
            }
        }

        public boolean isCellEditable(int row, int col) {
            // Note that the data/cell address is constant,
            // no matter where the cell appears onscreen.
            return (col <= 4) && (col >= 1);
        }

    }

}
