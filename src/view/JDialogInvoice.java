package view;

import com.toedter.calendar.JDateChooser;
import info.clearthought.layout.TableLayout;
import model.*;
import output.InvoiceOutput;
import persistency.RDBConnection;
import persistency.controller.*;
import utilities.*;
import utilities.Date;

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
 * commercially (ie, by a corporation, company or business for any purposeF
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class JDialogInvoice extends JDialog {
    private static int lineCounter;
    private JFrame parent;
    private JSplitPane jSplitPaneInvoiceDetails;
    private JScrollPane jScrollPane1;
    private JInternalFrame jInternalFrameInvoiceHeader;
    private JInternalFrame jInternalFrameInvoiceDetail;
    private JLabel jLabelStatus;
    private JLabel jLabelInvoiceID;
    private JLabel jLabelInvoiceDate;
    private JLabel jLabelInvoiceAddress;
    private JLabel jLabelVat;
    private JLabel jLabelComments;
    private JLabel jLabelInvoice;
    private JLabel jLabelCustomerType;
    private JLabel jLabelDueDate;
    private JLabel jLabelTotalExcl;
    private JLabel jLabelBTW21;
    private JLabel jLabelTotalIncl;
    private JLabel jLabelBTW6;
    private JLabel jLabelBTW;
    private JSeparator jSeparator1;
    private JPanel jPanelHeaderSouth;
    private JPanel jPanelHeaderNorth;
    private JPanel jPanelSouth;
    private JPanel jPanelInvoiceDetail;
    private JPanel jPanelInvoiceTotal;
    private JTextField jTextFieldCustomer;
    private JTextField jTextFieldVat21;
    private JTextField jTextFieldTotalIncl;
    private JTextField jTextFieldVat6;
    private JTextField jTextFieldInvoiceStatus;
    private JTextField jTextFieldInvoiceCurrency;
    private JTextField jTextFieldTotalExcl;
    private JTextField jTextFieldInvoiceID;
    private JTextField jTextFieldInvoiceAddress;
    private JTextField jTextFieldCompanyRegistrationNumber;
    private JDateChooser invDueDate;
    private JDateChooser invDate;
    private JComboBox jComboBoxAddressSelection;
    private ComboBoxModel jComboBoxAddressSelectionModel;
    private JTable jTableInvoiceDetails;
    private TableModel jTableInvoiceDetailsModel;
    private JScrollPane jScrollPane2;
    private JTextArea jTextAreaErrorMessage;
    private JTextArea jTextAreaComments;
    private JButton jButtonInvoiceDetailNew;
    private JButton jButtonInvoiceSave;
    private JButton jButtonPayed;
    private JButton jButtonPrint;
    private JPopupMenu jPopupMenuInvoiceDetails;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteInvoiceDetail;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateInvoiceDetail;
    private JRadioButton jRadioButtonVat;
    private Customer customer;
    private Invoice invoice;
    private TreeMap<String, String> dlvAddresses, invoiceStats;
    private int row;
    private double totalExcl = 0.0;
    private double totalIncl = 0.0;
    private double totalVat6 = 0.0;
    private double totalVat21 = 0.0;
    private utilities.Date toDay;
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

    public JDialogInvoice(JFrame frame, Invoice invoice,
                          CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.invoice = invoice;
        initGUI();
    }

    public JDialogInvoice(JFrame frame, Customer customer,
                          CRUDOperationEnum operation) {
        this(frame, true, operation);
        this.customer = customer;
        initGUI();
    }

    private JDialogInvoice(JFrame frame, boolean modal,
                           CRUDOperationEnum operation) {
        super(frame, modal);
        this.operation = operation;
        invoiceStats = CodeController.getCodeDetails(CodeEnum.INVOICE_STATUS
                .getType());// Invoice Status'
        freeFormat = !RDBConnection.getProps().getProperty(Constants.INVOICE_DIALOG)
                .equals(Constants.INVOICE_FIX);
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
        JDialogInvoice.lineCounter = lineCounter;
    }

    private void initGUI() {
        try {
            {
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jSplitPaneInvoiceDetails = new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT);
                getContentPane().add(jSplitPaneInvoiceDetails,
                        BorderLayout.CENTER);
                jSplitPaneInvoiceDetails
                        .setPreferredSize(new java.awt.Dimension(815, 723));
                invoiceHeader();
                invoiceDetails();
                invoiceButtons();
                setInvoiceTotals();

                getContentPane().add(jPanelSouth, BorderLayout.SOUTH);
                jPanelSouth.setPreferredSize(new java.awt.Dimension(588, 60));
            }
            {
                this.setSize(856, 859);
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
    private void invoiceHeader() {
        {// Invoice Header
            jInternalFrameInvoiceHeader = new JInternalFrame();
            BorderLayout jInternalFrameInvoiceHeaderLayout = new BorderLayout();
            jSplitPaneInvoiceDetails.add(jInternalFrameInvoiceHeader,
                    JSplitPane.LEFT);
            jInternalFrameInvoiceHeader.setVisible(true);
            jInternalFrameInvoiceHeader
                    .setPreferredSize(new java.awt.Dimension(586, 290));
            jInternalFrameInvoiceHeader.setBounds(1, 1, 586, 290);
            jInternalFrameInvoiceHeader.getContentPane().setLayout(
                    jInternalFrameInvoiceHeaderLayout);
            jInternalFrameInvoiceHeader.setTitle(Constants.INVOICE);
            {
                invoiceHeaderNorthPanel();
                invoiceHeaderSouthPanel();
                initializeInvoiceHeaderFields();
            }

        }
    }

    /**
     *
     */
    private void invoiceDetails() {
        {// invoice Details
            jInternalFrameInvoiceDetail = new JInternalFrame();
            jSplitPaneInvoiceDetails.add(jInternalFrameInvoiceDetail,
                    JSplitPane.RIGHT);
            BorderLayout jInternalFrameInvoiceDetailLayout = new BorderLayout();
            jInternalFrameInvoiceDetail.getContentPane().setLayout(
                    jInternalFrameInvoiceDetailLayout);
            jInternalFrameInvoiceDetail.setBounds(1, 332, 586, 292);
            jInternalFrameInvoiceDetail
                    .setPreferredSize(new java.awt.Dimension(586, 292));
            jInternalFrameInvoiceDetail.setTitle(Constants.DETAILS);
            {
                {
                    jScrollPane1 = new JScrollPane();
                }
                jInternalFrameInvoiceDetail.getContentPane().add(jScrollPane1,
                        BorderLayout.NORTH);
                jScrollPane1.setPreferredSize(new java.awt.Dimension(803, 258));
                jScrollPane1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
                        null, null, null, null));
                jScrollPane1.setEnabled(false);
                {
                    jTableInvoiceDetails = new JTable();
                    jScrollPane1.setViewportView(jTableInvoiceDetails);

                }
                {// Total
                    jInternalFrameInvoiceDetail.getContentPane().add(
                            getJPanelInvoiceTotal(), BorderLayout.CENTER);
                }
                {// new invoice details
                    jPanelInvoiceDetail = new JPanel();
                    jPanelInvoiceDetail.add(getJButtonInvoiceDetailNew(),
                            "0, 0");
                    getJButtonInvoiceDetailNew().addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    new JDialogInvoiceDetail(invoice, null,
                                            CRUDOperationEnum.NEW);
                                    initializeInvoiceDetailFields();
                                    setInvoiceTotals();
                                }
                            });
                }
                jInternalFrameInvoiceDetail.setVisible(true);
                jInternalFrameInvoiceDetail.getContentPane().add(
                        jPanelInvoiceDetail, BorderLayout.SOUTH);
                jPanelInvoiceDetail.setBorder(BorderFactory
                        .createBevelBorder(BevelBorder.LOWERED));
                initializeInvoiceDetailFields();
            }
        }
    }

    /**
     *
     */
    private void invoiceButtons() {
        {
            jPanelSouth = new JPanel();
            jPanelSouth.add(getJButtonInvoiceSave());
            getJButtonInvoiceSave().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        saveInput();
                        initializeButtons();
                    }
                }
            });

            jPanelSouth.add(getJButtonInvoicePrint());
            getJButtonInvoicePrint().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // disable OK button if printed
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        saveInput();
                        if (invoice.getInvStatus().equals(
                                model.FixTypes.INVOICE_STATUS_CONFIRMED)) {
                            // updateQuoteStatus(QuoteController.QUOTE_STATUS_OPEN);
                        } else {
                            updateInvoiceStatus(model.FixTypes.INVOICE_STATUS_OPEN);
                        }
                        new InvoiceOutput(invoice).run();

                    }
                }
            });

            jPanelSouth.add(getJButtonInvoicePayed());
            getJButtonInvoicePayed().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // disable Print button if invoiced
                    StringBuilder errorMessages = new StringBuilder(
                            Constants.EMPTY);
                    if (validateInput(errorMessages)) {
                        updateInvoiceStatus(model.FixTypes.INVOICE_STATUS_PRINTED);
                    }
                }
            });

            initializeButtons();
            // add jTextAreaErrorMessage after buttons
            jPanelSouth.add(getMessage());
            resetMessage();
        }
    }

    /**
     *
     */
    private void initializeButtons() {
        getJButtonInvoiceDetailNew().setEnabled(false);
        getJButtonInvoicePrint().setEnabled(false);
        getJButtonInvoicePayed().setEnabled(false);

        getJRadioButtonVat().setEnabled(false);
        if (operation == CRUDOperationEnum.NEW) {
            getJRadioButtonVat().setEnabled(true);
            getJRadioButtonVat().setSelected(true);
        }
        inverseButtonSelected();
        if (invoice != null) {
            getJButtonInvoiceSave().setEnabled(false);
            getJRadioButtonVat().setSelected(invoice.isInvVat());
            if ((invoice.getInvStatus().equals(model.FixTypes.INVOICE_STATUS_OPEN))) {
                getJButtonInvoiceSave().setEnabled(true);
                getJRadioButtonVat().setEnabled(true);
            }
            if (invoice.getInvStatus().equals(model.FixTypes.INVOICE_STATUS_PRINTED)) {
                getJButtonInvoicePayed().setEnabled(true);
                getJRadioButtonVat().setEnabled(true);
            }
            if ((jTableInvoiceDetailsModel.getRowCount() > 0)
                    && (!invoice.getInvStatus().equals(
                    model.FixTypes.INVOICE_STATUS_CONFIRMED))) {
                getJButtonInvoicePrint().setEnabled(true);

            }
            if (((invoice.getInvStatus().equals(model.FixTypes.INVOICE_STATUS_OPEN)))
                    || invoice.getInvStatus().equals(
                    model.FixTypes.INVOICE_STATUS_PRINTED)) {
                jTableInvoiceDetails.setEnabled(true);
                // FixFormat
                if (!freeFormat) {
                    getJButtonInvoiceDetailNew().setEnabled(true);
                    setComponentPopupMenu(jTableInvoiceDetails,
                            getJPopupMenuInvoiceDetails());
                }
            }
            inverseButtonSelected();
        }
    }

    private void updateInvoiceStatus(String previousStatus) {
        if (previousStatus.equals(model.FixTypes.INVOICE_STATUS_PRINTED)) {
            invoice = updateExistingInvoice(model.FixTypes.INVOICE_STATUS_CONFIRMED);
            InvoiceController.updateInvoice(invoice);
        }
        if (previousStatus.equals(model.FixTypes.INVOICE_STATUS_OPEN)) {
            invoice = updateExistingInvoice(model.FixTypes.INVOICE_STATUS_PRINTED);
            InvoiceController.updateInvoice(invoice);
        }
        // Refresh buttons & invoiceheader after status modification
        initializeButtons();
        initializeInvoiceHeaderFields();
    }

    /**
     *
     */
    private void initializeInvoiceDetailFields() {
        final int DEFAULT_PIXELS = 20;
        int[] columnWidth = new int[getInvoiceDetailTitles().length];
        jTableInvoiceDetailsModel = new MyTableModel(columnWidth);
        jTableInvoiceDetails.setModel(jTableInvoiceDetailsModel);
        if (freeFormat) {
            jTableInvoiceDetails.setDefaultEditor(Double.class,
                    new DefaultCellEditor(new JFormattedTextField()));
        } else {
            for (int i = 0; i < columnWidth.length; i++) {
                TableColumn column = jTableInvoiceDetails.getColumnModel()
                        .getColumn(i);
                column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
            }
        }
        jTableInvoiceDetails.getTableHeader().setFont(
                new java.awt.Font(Constants.FONT_DIALOG, 1, 12));
        jTableInvoiceDetails.setFont(new java.awt.Font(
                Constants.FONT_COURIER_NEW, 0, 14));
        jTableInvoiceDetails.getTableHeader().setToolTipText(
                Constants.INVOICE_DETAIL_LINES);
        jTableInvoiceDetails.setEnabled(false);
        if (freeFormat) {
            // Set up column sizes.
            initColumnSizes(jTableInvoiceDetails);
        } else {
            if (getJPopupMenuInvoiceDetails() != null) {
                getJPopupMenuInvoiceDetails().setEnabled(false);
                jTableInvoiceDetails.remove(getJPopupMenuInvoiceDetails());
            }
        }
        // setInvoiceTotals();
    }

    private JPopupMenu getJPopupMenuInvoiceDetails() {
        if (jPopupMenuInvoiceDetails == null) {
            jPopupMenuInvoiceDetails = new JPopupMenu();
            jPopupMenuInvoiceDetails.setBounds(219, 128, 76, 19);
            ButtonGroup group = new ButtonGroup();
            group.add(getJRadioButtonMenuItemUpdateInvoiceDetail());
            group.add(getJRadioButtonMenuItemDeleteInvoiceDetail());
            getJRadioButtonMenuItemUpdateInvoiceDetail().setSelected(true);
            jPopupMenuInvoiceDetails
                    .add(getJRadioButtonMenuItemUpdateInvoiceDetail());
            jPopupMenuInvoiceDetails
                    .add(getJRadioButtonMenuItemDeleteInvoiceDetail());

        }
        return jPopupMenuInvoiceDetails;
    }

    /**
     *
     */
    private void invoiceHeaderNorthPanel() {
        {// North panel

            jPanelHeaderNorth = new JPanel();

            TableLayout jPanelHeaderNorthLayout = new TableLayout(
                    new double[][]{
                            {134.0, 158.0, 172.0, 40.0, TableLayout.FILL},
                            {TableLayout.FILL, TableLayout.FILL,
                                    TableLayout.FILL, TableLayout.FILL}});
            jPanelHeaderNorthLayout.setHGap(5);
            jPanelHeaderNorthLayout.setVGap(5);
            jPanelHeaderNorth.setLayout(jPanelHeaderNorthLayout);
            jInternalFrameInvoiceHeader.getContentPane().add(jPanelHeaderNorth,
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
                jTextFieldCustomer.setFont(new java.awt.Font(
                        Constants.FONT_DIALOG, 0, 18));
            }
            {
                jLabelVat = new JLabel();
                jPanelHeaderNorth.add(jLabelVat, "0, 1");
                jLabelVat.setText(Constants.ENTERPRISE);
            }
            {
                jTextFieldCompanyRegistrationNumber = new JTextField();
                jPanelHeaderNorth.add(jTextFieldCompanyRegistrationNumber,
                        "1, 1");
                jTextFieldCompanyRegistrationNumber
                        .setPreferredSize(new java.awt.Dimension(240, 20));
                jTextFieldCompanyRegistrationNumber.setEditable(false);
                jTextFieldCompanyRegistrationNumber.setFont(new java.awt.Font(
                        Constants.FONT_DIALOG, 0, 16));
            }
            {
                jLabelInvoiceAddress = new JLabel();
                jPanelHeaderNorth.add(jLabelInvoiceAddress, "0, 2");
                jLabelInvoiceAddress.setText(Constants.INVOICE_ADDRESS);
            }
            {
                jTextFieldInvoiceAddress = new JTextField();
                jPanelHeaderNorth.add(jTextFieldInvoiceAddress, "1, 2, 3, 2");
                jPanelHeaderNorth.add(getJLabelInvoice(), "0, 3");

                jTextFieldInvoiceAddress.setEditable(false);
                jTextFieldInvoiceAddress.setFont(new java.awt.Font(
                        Constants.FONT_DIALOG, 1, 12));
            }
        }
    }

    /**
     *
     */
    private void invoiceHeaderSouthPanel() {
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
            jInternalFrameInvoiceHeader.getContentPane().add(jPanelHeaderSouth,
                    BorderLayout.SOUTH);
            jInternalFrameInvoiceHeader.getContentPane().add(getJSeparator1(),
                    BorderLayout.CENTER);
            jPanelHeaderSouth
                    .setPreferredSize(new java.awt.Dimension(576, 117));
            {
                jLabelInvoiceDate = new JLabel();
                jPanelHeaderSouth.add(jLabelInvoiceDate, "3, 0");
                jLabelInvoiceDate.setText(Constants.INVOICE_DATE);
            }
            {
                jLabelInvoiceID = new JLabel();
                jPanelHeaderSouth.add(jLabelInvoiceID, "0, 0");
                jLabelInvoiceID.setText(Constants.INVOICE_NUMBER);
            }
            {
                jTextFieldInvoiceID = new JTextField();
                jPanelHeaderSouth.add(jTextFieldInvoiceID, "1, 0, 2, 0");
                jTextFieldInvoiceID.setEditable(false);
                jTextFieldInvoiceID.setFont(new java.awt.Font(
                        Constants.FONT_DIALOG, 1, 18));
            }
            {
                jPanelHeaderSouth.add(getJLabelStatus(), "0, 1");
                jPanelHeaderSouth.add(getJTextFieldInvoiceStatus(), "1, 1");
                jPanelHeaderSouth.add(getJLabelComments(), "0, 2");
                jPanelHeaderSouth.add(getJScrollPane2(), "1, 2, 5, 2");
                jPanelHeaderSouth.add(getJLabelDueDate(), "3, 1");
                jPanelHeaderSouth.add(getInvDateChooser(), "4, 0, 5, 0");
                jPanelHeaderSouth.add(getInvDueDateChooser(), "4, 1, 5, 1");
            }
        }
    }

    /**
     *
     */
    private void initializeInvoiceHeaderFields() {
        Address address = null;
        jTextFieldInvoiceStatus.setText(CodeController.getOneCodeDetail(
                CodeEnum.INVOICE_STATUS.getType(),
                model.FixTypes.DEFAULT_INVOICE_STATUS).getCodeDesc());
        getJTextAreaComments().setEnabled(true);
        if (invoice != null) {
            customer = CustomerController.getCustomer(invoice.getInvCusid());
            // Invoice address
            if (invoice.getInvAddid() != null) {
                address = AddressController.getAddress(invoice.getInvAddid());
                jPanelHeaderNorth.add(getJComboBoxAddressSelection(),
                        "1, 3, 3, 3");
                jComboBoxAddressSelection.setSelectedItem(getSelectedItem(
                        dlvAddresses, invoice.getInvDlvAddid()));

            }
            getJRadioButtonVat().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    inverseButtonSelected();
                    initializeInvoiceDetailFields();
                    setInvoiceTotals();
                }

            });

            jTextFieldInvoiceID.setText(invoice.getIdInvoice());
            if (invoice.getInvHeaderComments() == null) {
                getJTextAreaComments().setText(Constants.EMPTY);
            }
            getJTextAreaComments().setText(invoice.getInvHeaderComments());
            jTextFieldInvoiceStatus.setText(CodeController.getOneCodeDetail(
                    CodeEnum.INVOICE_STATUS.getType(), invoice.getInvStatus())
                    .getCodeDesc());

            getInvDateChooser().setDate(
                    new GregorianCalendar(invoice.getInvDate().getJaar(),
                            invoice.getInvDate().getMaand(), invoice
                            .getInvDate().getDag()).getTime());
            getInvDueDateChooser().setDate(
                    new GregorianCalendar(invoice.getInvDueDate().getJaar(),
                            invoice.getInvDueDate().getMaand(), invoice
                            .getInvDueDate().getDag()).getTime());
            // no modifications allowed
            getInvDateChooser().setEnabled(false);
            getInvDueDateChooser().setEnabled(false);
            getJComboBoxAddressSelection().setEnabled(false);
            getJTextAreaComments().setEnabled(false);

            if (!invoice.getInvStatus().equals(
                    model.FixTypes.INVOICE_STATUS_CONFIRMED)) {
                getInvDateChooser().setEnabled(true);
                getInvDueDateChooser().setEnabled(true);
                getJComboBoxAddressSelection().setEnabled(true);
                getJTextAreaComments().setEnabled(true);
            }
        }
        if (customer != null) {
            getJLabelCustomerType().setText(
                    CodeController.getOneCodeDetail(
                            CodeEnum.CUSTOMER_TYPE.getType(),
                            customer.getCusType()).getCodeDesc());
            jTextFieldCustomer.setText(customer.getCusName());
            jTextFieldCompanyRegistrationNumber.setText(customer.getCusVat());
            List<Address> list = InvoiceController.getAddressesByCustomer(
                    customer, model.FixTypes.INVOICE_ADRESS_TYPE);
            if (!list.isEmpty()) {
                address = list.get(0);
                jTextFieldInvoiceAddress.setText(address.toString());
                if ((dlvAddresses == null) || dlvAddresses.isEmpty()) {
                    getExistingAddresses(model.FixTypes.DELIVERY_ADRESS_TYPE);
                }
                if ((dlvAddresses == null) || dlvAddresses.isEmpty()) {
                    getExistingAddresses(model.FixTypes.INVOICE_ADRESS_TYPE);
                }
            }
            jPanelHeaderNorth.add(getJComboBoxAddressSelection(), "1, 3, 3, 3");
            jPanelHeaderNorth.add(getJRadioButtonVat(), "4, 1");
            jPanelHeaderNorth.add(getJLabelBTW(), "3, 1");

        }

    }

    /**
     *
     */
    private void inverseButtonSelected() {
        getJRadioButtonVat().setText(Constants.NO);
        if (getJRadioButtonVat().isSelected()) {
            getJRadioButtonVat().setText(Constants.YES);
        }
    }

    /**
     * @return
     */
    private String[] getInvoiceDetailTitles() {
        return new String[]{Constants.LN, Constants.DESC, Constants.QUANTITY,
                Constants.PRICE, Constants.UNIT, Constants.BTW, Constants.TOTAL};
    }

    /**
     * @return
     */
    private Object[][] getInvoiceDetailColumns(int[] columnWidth) {
        Object[][] columns = null;
        if (freeFormat) {
            columns = new Object[utilities.Figures.MAX_INV_DETAIL_LINES][utilities.Figures.MAX_INV_DETAIL_COLUMNS];
        }
        int i = 0;
        int last = 0;

        if (customer != null && invoice != null) {
            InvoiceDetail detail;
            Collection<Business> list = InvoiceDetailController
                    .readInvoiceDetails(invoice.getIdInvoice());
            // #lines limited!
            if (!freeFormat) {
                columns = new Object[list.size()][];
            }
            Iterator<Business> it = list.iterator();
            totalExcl = 0f;
            totalIncl = 0f;
            totalVat6 = 0f;
            totalVat21 = 0f;
            while (it.hasNext()) {
                detail = (InvoiceDetail) it.next();
                last = detail.getInvDetLine();
                setLineCounter(last);// last detail line
                columns[i] = new Object[]{
                        new Integer(detail.getInvDetLine()),
                        (detail.getInvProdid() != null ? ProductController
                                .getProduct(detail.getInvProdid())
                                .getProdDesc() : detail.getInvComments()),
                        new BigDecimal(detail.getInvQty().doubleValue())
                                .setScale(1, BigDecimal.ROUND_HALF_UP),
                        new BigDecimal(detail.getInvPrice().doubleValue())
                                .setScale(2, BigDecimal.ROUND_HALF_UP),
                        detail.getInvMeasure(),
                        new BigDecimal(detail.getInvVat()).setScale(0,
                                BigDecimal.ROUND_HALF_UP),
                        getInvoiceLineTotal(detail).setScale(2,
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

    private BigDecimal getInvoiceLineTotal(InvoiceDetail detail) {

        BigDecimal lineTotal = detail.getInvQty()
                .multiply(detail.getInvPrice());
        totalExcl += lineTotal.doubleValue();
        BigDecimal vat = new BigDecimal(detail.getInvVat());
        double vatAmount = utilities.Figures.ZERO;
        vatAmount = lineTotal.doubleValue() * vat.doubleValue()
                / utilities.Figures.HUNDRED;
        if (vat.doubleValue() == utilities.Figures.SIX) {
            totalVat6 += vatAmount;
        }
        if (vat.doubleValue() == utilities.Figures.TWENTYONE) {
            totalVat21 += vatAmount;
        }
        return lineTotal;
    }

    private void setInvoiceTotals() {
        if (!getJRadioButtonVat().isSelected()) {
            totalIncl = 0f;
            totalVat6 = 0f;
            totalVat21 = 0f;
        }
        totalIncl = totalExcl + totalVat6 + totalVat21;

        getJTextFieldInvoiceCurrency().setText(customer.getCusCur());
        getJTextFieldInvoiceTotalExcl().setText(
                String.format("%20.2f", totalExcl));
        getJTextFieldVat6().setText(String.format("%20.2f", totalVat6));
        getJTextFieldVat21().setText(String.format("%20.2f", totalVat21));
        getJTextFieldTotalIncl().setText(String.format("%20.2f", totalIncl));
    }

    private String[] getAddressFilter(String type) {

        String id = customer.getIdCus();
        String[] filter = {id, type, Constants.TRUE};
        return filter;
    }

    private JTextArea getMessage() {
        if (jTextAreaErrorMessage == null) {
            jTextAreaErrorMessage = new JTextArea();
            jTextAreaErrorMessage.setPreferredSize(new java.awt.Dimension(601,
                    16));
            jTextAreaErrorMessage.setEditable(false);
        }
        return jTextAreaErrorMessage;
    }

    private JLabel getJLabelStatus() {
        if (jLabelStatus == null) {
            jLabelStatus = new JLabel();
            jLabelStatus.setText(Constants.STATUS);
        }
        return jLabelStatus;
    }

    private JTextField getJTextFieldInvoiceCurrency() {
        if (jTextFieldInvoiceCurrency == null) {
            jTextFieldInvoiceCurrency = new JTextField();
            jTextFieldInvoiceCurrency.setEditable(false);
            jTextFieldInvoiceCurrency.setFont(new java.awt.Font(
                    Constants.FONT_DIALOG, 1, 14));
        }
        return jTextFieldInvoiceCurrency;
    }

    private JLabel getJLabelCustomerType() {
        if (jLabelCustomerType == null) {
            jLabelCustomerType = new JLabel();
            jLabelCustomerType.setFont(new java.awt.Font(Constants.FONT_DIALOG,
                    1, 14));
        }
        return jLabelCustomerType;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private JComboBox getJComboBoxAddressSelection() {
        if (jComboBoxAddressSelection == null) {

            getExistingAddresses(model.FixTypes.DELIVERY_ADRESS_TYPE);
        }
        if (jComboBoxAddressSelectionModel.getSize() == 0) {

            getExistingAddresses(model.FixTypes.INVOICE_ADRESS_TYPE);
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

    private JButton getJButtonInvoiceSave() {
        if (jButtonInvoiceSave == null) {
            jButtonInvoiceSave = new JButton(Constants.OK);
            jButtonInvoiceSave.setToolTipText(Constants.SAVE);
            jButtonInvoiceSave.setPreferredSize(new java.awt.Dimension(69, 25));
        }
        return jButtonInvoiceSave;
    }

    private JButton getJButtonInvoicePrint() {
        if (jButtonPrint == null) {
            jButtonPrint = new JButton();
            jButtonPrint.setText(Constants.PRINT);
            jButtonPrint.setToolTipText(Constants.INVOICE_PRINT);
        }
        return jButtonPrint;
    }

    private JButton getJButtonInvoiceDetailNew() {
        if (jButtonInvoiceDetailNew == null) {
            jButtonInvoiceDetailNew = new JButton();
            jButtonInvoiceDetailNew.setText(Constants.NEW);
            jButtonInvoiceDetailNew.setToolTipText(Constants.NEW_DETAIL);

        }
        return jButtonInvoiceDetailNew;
    }

    private JButton getJButtonInvoicePayed() {
        if (jButtonPayed == null) {
            jButtonPayed = new JButton();
            jButtonPayed.setText(Constants.PAYMENT);
            jButtonPayed.setPreferredSize(new java.awt.Dimension(89, 25));
        }
        return jButtonPayed;
    }

    private JLabel getJLabelInvoice() {
        if (jLabelInvoice == null) {
            jLabelInvoice = new JLabel();
            jLabelInvoice.setText(Constants.DELIVERY_ADDRESS);
        }
        return jLabelInvoice;
    }

    private JTextField getJTextFieldInvoiceStatus() {
        if (jTextFieldInvoiceStatus == null) {
            jTextFieldInvoiceStatus = new JTextField();
            jTextFieldInvoiceStatus.setEditable(false);
        }
        return jTextFieldInvoiceStatus;
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
        validateInvoiceHeader(errorMessages);
        if (!errorMessages.toString().equals(Constants.EMPTY)) {
            redMessage(errorMessages);
            return false;
        }
        // Only test details if free format
        if (freeFormat) {
            validateInvoiceDetail(errorMessages);
            if (!errorMessages.toString().equals(Constants.EMPTY)) {
                redMessage(errorMessages);
                return false;
            }
        }
        return true;
    }

    synchronized private void saveInput() {

        if ((invoice == null) && (operation == CRUDOperationEnum.NEW)) {
            Invoice newInvoice = createNewInvoice();
            InvoiceController.createInvoice(newInvoice);
            invoice = new Invoice(newInvoice);
            greenMessage(newInvoice.getIdInvoice() + Constants.ADDED);
        }
        if (operation == CRUDOperationEnum.UPDATE) {
            String status = invoiceStats.get(jTextFieldInvoiceStatus.getText());
            Invoice newInvoice = updateExistingInvoice(status);

            if (!invoice.equals(newInvoice)) {
                InvoiceController.updateInvoice(newInvoice);
                invoice = new Invoice(newInvoice);
                greenMessage(newInvoice.getIdInvoice() + Constants.CHANGED);
            }
        }
        // All modifications should be shown real-time
        if (freeFormat) {
            for (int row = 0; row < utilities.Figures.MAX_INV_DETAIL_LINES; row++) {
                if (!lineIsEmpty(row)) {
                    saveDetail(row);
                } else {
                    InvoiceDetail detail = InvoiceDetailController
                            .getOneInvoiceDetail(invoice.getIdInvoice(),
                                    (Integer) jTableInvoiceDetails.getModel()
                                            .getValueAt(row, 0));
                    if ((detail != null)
                            && (detail.getInvPrice().doubleValue() != utilities.Figures.ZERO)) {
                        InvoiceDetailController.removeInvoiceDetail(detail);
                    }
                }
            }
        }
        initializeInvoiceDetailFields();
        setInvoiceTotals();
        initializeInvoiceHeaderFields();

    }

    /**
     * @return
     */
    private Invoice updateExistingInvoice(String status) {
        String addressId = dlvAddresses.get(jComboBoxAddressSelection
                .getSelectedItem().toString());
        Address address = null;
        List<Address> list = InvoiceController.getAddressesByCustomer(customer,
                model.FixTypes.INVOICE_ADRESS_TYPE);
        if (!list.isEmpty()) {
            address = list.get(0);
        }
        String comments = getJTextAreaComments().getText();
        String invoiceID = invoice.getIdInvoice();

        Invoice newInvoice = new Invoice(invoiceID, customer.getIdCus(),
                address.getIdAddress(), addressId, invoice.getInvCrtDate(),
                invoice.getInvCrtUserid(), // invcrtuserid
                toDay, // invupddate | datetime
                Integer.toHexString(1), // invupduserid | char(15)
                getInvDate(), // invDate
                getDueDate(), // invDueDate
                status, // invStatus | char(5)
                invoice.getInvType(), // invType | char(5)
                getJRadioButtonVat().isSelected(), // invVat | tinyint(1)
                comments, // invHeadercomments varchar(256)
                invoice.isActive() // Active | tinyint(1)
        );
        return newInvoice;
    }

    /**
     * @return
     */
    private Invoice createNewInvoice() {
        String addressId = dlvAddresses.get(jComboBoxAddressSelection
                .getSelectedItem().toString());
        Address address = null;
        List<Address> list = InvoiceController.getAddressesByCustomer(customer,
                model.FixTypes.INVOICE_ADRESS_TYPE);
        if (!list.isEmpty()) {
            address = list.get(0);
        }
        String status = invoiceStats.get(jTextFieldInvoiceStatus.getText());
        String comments = getJTextAreaComments().getText();

        String invoiceID = NumberController.readLastNumber(
                NumberEnum.INVOICE.getType(), toDay.getJaar()).toString();

        Invoice newInvoice = new Invoice(invoiceID, customer.getIdCus(),
                address.getIdAddress(), addressId, toDay, // crtDate
                Integer.toHexString(1), // invcrtuserid | char (15)
                toDay, // invupddate | datetime
                Integer.toHexString(1), // invupduserid | char(15)
                getInvDate(),// invDate
                getDueDate(), // invDueDate | date
                status, // invStatus | char(5)
                model.FixTypes.DEFAULT_INVOICE_TYPE, // invType | char(5)
                // (default value: Offer)
                getJRadioButtonVat().isSelected(), // invVat | tinyint(1)
                comments, // invHeadercomments varchar(256)
                true // Active | tinyint(1)
        );
        return newInvoice;
    }

    synchronized private void saveDetail(int row) {
        InvoiceDetail detail = InvoiceDetailController.getOneInvoiceDetail(
                invoice.getIdInvoice(), (Integer) jTableInvoiceDetails
                        .getModel().getValueAt(row, 0));
        InvoiceDetail newDetail = createDetail(row);
        if ((detail == null)) {
            InvoiceDetailController.createInvoiceDetail(newDetail);
            detail = new InvoiceDetail(newDetail);
            greenMessage(Constants.LINE + newDetail.getInvDetLine()
                    + Constants.ADDED);
        } else {
            if (!detail.equals(newDetail)) {
                InvoiceDetailController.updateInvoiceDetail(newDetail);
                detail = new InvoiceDetail(newDetail);
                greenMessage(Constants.LINE + newDetail.getInvDetLine()
                        + Constants.CHANGED);
            }
        }
    }

    /**
     * @return
     */
    private InvoiceDetail createDetail(int row) {
        InvoiceDetail newDetail = new InvoiceDetail(invoice.getIdInvoice(),
                (Integer) jTableInvoiceDetails.getModel().getValueAt(row, 0), // line
                null, // product
                new BigDecimal(jTableInvoiceDetails.getModel()
                        .getValueAt(row, 2).toString()), // qty
                jTableInvoiceDetails.getModel().getValueAt(row, 4).toString(), // unit
                new BigDecimal(jTableInvoiceDetails.getModel()
                        .getValueAt(row, 3).toString()), // price
                jTableInvoiceDetails.getModel().getValueAt(row, 5).toString(), // vat
                jTableInvoiceDetails.getModel().getValueAt(row, 1).toString(), // Comment
                true); // Comment
        return newDetail;
    }

    /**
     * @return
     */
    private Date getDueDate() {
        Date dueDate = null;
        try {
            dueDate = new Date(getInvDueDateChooser().getCalendar().get(
                    java.util.Calendar.DATE), getInvDueDateChooser().getCalendar().get(
                    java.util.Calendar.MONTH), getInvDueDateChooser().getCalendar().get(
                    java.util.Calendar.YEAR));
        } catch (DatumException e) {
            e.printStackTrace();
        }
        return dueDate;
    }

    /**
     * @return
     */
    private Date getInvDate() {
        Date invoiceDate = null;
        try {
            invoiceDate = new Date();
            invoiceDate.setDatum(
                    getInvDateChooser().getCalendar().get(java.util.Calendar.DATE),
                    getInvDateChooser().getCalendar().get(java.util.Calendar.MONTH),
                    getInvDateChooser().getCalendar().get(java.util.Calendar.YEAR));
        } catch (DatumException e) {
            e.printStackTrace();
        }
        return invoiceDate;
    }

    private boolean validateInvoiceHeader(StringBuilder errorMessages) {
        if (jTextFieldInvoiceAddress.getText().equals(Constants.EMPTY)) {
            errorMessages.append(Constants.NO_INVOICE_ADDRESS);
            return false;
        }
        if (jComboBoxAddressSelection.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_DELIVERY_ADDRESS);
            return false;
        }
        if (getInvDateChooser().getCalendar() == null) {
            errorMessages.append(Constants.NO_INV_DATE);
            return false;
        }
        if (getInvDueDateChooser().getCalendar() == null) {
            errorMessages.append(Constants.NO_DUE_DATE);
            return false;
        }
        if ((getDueDate() == null) || (getDueDate().kleinerDan(getInvDate()))) {
            errorMessages.append(Constants.DUE_DATE_FUTURE);
            return false;
        }
        return true;
    }

    /**
     * @param errorMessages
     * @return
     */
    private boolean validateInvoiceDetail(StringBuilder errorMessages) {
        for (int row = 0; row < utilities.Figures.MAX_INV_DETAIL_LINES; row++) {
            for (int col = 1; col < utilities.Figures.MAX_INV_DETAIL_COLUMNS; col++) {
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

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateInvoiceDetail() {
        if (jRadioButtonMenuItemUpdateInvoiceDetail == null) {
            jRadioButtonMenuItemUpdateInvoiceDetail = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateInvoiceDetail.setText(Constants.DETAILS);
            jRadioButtonMenuItemUpdateInvoiceDetail
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // no modifications allowed when invoice is
                            // confirmed
                            if (!invoice.getInvStatus().equals(

                                    model.FixTypes.INVOICE_STATUS_CONFIRMED)) {
                                if (!freeFormat) {
                                    Integer line = Integer
                                            .valueOf(jTableInvoiceDetailsModel
                                                    .getValueAt(row, 0)
                                                    .toString().trim());// Invoice
                                    // Detail
                                    // number
                                    InvoiceDetail detail = InvoiceDetailController
                                            .getOneInvoiceDetail(
                                                    invoice.getIdInvoice(),
                                                    line);
                                    new JDialogInvoiceDetail(invoice, detail,
                                            CRUDOperationEnum.UPDATE);
                                } else {
                                    JOptionPane
                                            .showMessageDialog(
                                                    parent,
                                                    Constants.INVOICE_UPDATE_IMPOSSIBLE);

                                }
                            } else {
                                JOptionPane.showMessageDialog(parent,
                                        Constants.INVOICE_UPDATE_NOT_ALLOWED);
                            }
                            initializeInvoiceDetailFields();
                            setInvoiceTotals();
                        }
                    });

        }
        return jRadioButtonMenuItemUpdateInvoiceDetail;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteInvoiceDetail() {
        if (jRadioButtonMenuItemDeleteInvoiceDetail == null) {
            jRadioButtonMenuItemDeleteInvoiceDetail = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteInvoiceDetail.setText(Constants.REMOVE);
            jRadioButtonMenuItemDeleteInvoiceDetail
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // no modifications allowed when invoice is
                            // confirmed
                            if (!invoice.getInvStatus().equals(
                                    model.FixTypes.INVOICE_STATUS_CONFIRMED)) {

                                Integer line = Integer
                                        .valueOf(jTableInvoiceDetailsModel
                                                .getValueAt(row, 0).toString()
                                                .trim());// Invoice Detail
                                int response = JOptionPaneItemRemove
                                        .confirm(Constants.LINE_LOWER_CASE
                                                + line);
                                if (response == JOptionPane.YES_OPTION) {
                                    InvoiceDetail detail = InvoiceDetailController
                                            .getOneInvoiceDetail(
                                                    invoice.getIdInvoice(),
                                                    line);
                                    InvoiceDetailController
                                            .removeInvoiceDetail(detail);
                                    initializeInvoiceDetailFields();
                                    setInvoiceTotals();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        Constants.INVOICE_DELETE_NOT_ALLOWED);
                            }
                        }
                    });

        }
        return jRadioButtonMenuItemDeleteInvoiceDetail;
    }

    private JLabel getJLabelComments() {
        if (jLabelComments == null) {
            jLabelComments = new JLabel();
            jLabelComments.setText(Constants.REFERENCE);
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

    public JPanel getJPanelInvoiceTotal() {
        if (jPanelInvoiceTotal == null) {
            jPanelInvoiceTotal = new JPanel();
            TableLayout jPanelInvoiceTotalLayout = new TableLayout(
                    new double[][]{
                            {451.0, 162.0, 62.0, TableLayout.FILL},
                            {7.0, TableLayout.FILL, TableLayout.FILL,
                                    TableLayout.FILL, TableLayout.FILL, 7.0}});
            jPanelInvoiceTotalLayout.setHGap(5);
            jPanelInvoiceTotalLayout.setVGap(5);
            jPanelInvoiceTotal.setLayout(jPanelInvoiceTotalLayout);
            jPanelInvoiceTotal
                    .setPreferredSize(new java.awt.Dimension(584, 52));
            jPanelInvoiceTotal.add(getJLabelTotalExcl(), "1, 1");
            jPanelInvoiceTotal.add(getJTextFieldInvoiceTotalExcl(), "3, 1");
            jPanelInvoiceTotal.add(getJTextFieldInvoiceCurrency(), "2,4,r,f");
            jPanelInvoiceTotal.add(getJTextFieldVat6(), "3,2,r,f");
            jPanelInvoiceTotal.add(getJTextFieldTotalIncl(), "3, 4");
            jPanelInvoiceTotal.add(getJLabelBTW6(), "1, 2");
            jPanelInvoiceTotal.add(getJLabelTotalIncl(), "1, 4");
            jPanelInvoiceTotal.add(getJLabelBTW21(), "1, 3");
            jPanelInvoiceTotal.add(getJTextFieldVat21(), "3,3,r,f");
        }
        return jPanelInvoiceTotal;
    }

    private JTextField getJTextFieldInvoiceTotalExcl() {
        if (jTextFieldTotalExcl == null) {
            jTextFieldTotalExcl = new JTextField();
            jTextFieldTotalExcl.setEditable(false);
            jTextFieldTotalExcl.setFont(new java.awt.Font(
                    Constants.FONT_DIALOG, 1, 16));
        }
        return jTextFieldTotalExcl;
    }

    private JLabel getJLabelTotalExcl() {
        if (jLabelTotalExcl == null) {
            jLabelTotalExcl = new JLabel();
            jLabelTotalExcl.setText(Constants.TOTAL_EXCL);
            jLabelTotalExcl.setFont(new java.awt.Font(Constants.FONT_DIALOG, 1,
                    16));
        }
        return jLabelTotalExcl;
    }

    private JLabel getJLabelDueDate() {
        if (jLabelDueDate == null) {
            jLabelDueDate = new JLabel();
            jLabelDueDate.setText(Constants.INVOICE_DUEDATE);
        }
        return jLabelDueDate;
    }

    private JDateChooser getInvDateChooser() {
        if (invDate == null) {
            invDate = new JDateChooser();
            invDate.setEnabled(true);
            invDate.setToolTipText(Constants.INVOICE_DATE);
            invDate.setDateFormatString(Constants.DATE_FORMAT);
        }
        return invDate;
    }

    private JDateChooser getInvDueDateChooser() {
        if (invDueDate == null) {
            invDueDate = new JDateChooser();
            invDueDate.setEnabled(true);
            invDueDate.setToolTipText(Constants.INVOICE_DUEDATE);
            invDueDate.setDateFormatString(Constants.DATE_FORMAT);
        }
        return invDueDate;
    }

    private JTextField getJTextFieldVat6() {
        if (jTextFieldVat6 == null) {
            jTextFieldVat6 = new JTextField();
            jTextFieldVat6.setFont(new java.awt.Font(Constants.FONT_DIALOG, 0,
                    14));
            jTextFieldVat6.setEditable(false);
        }
        return jTextFieldVat6;
    }

    private JTextField getJTextFieldTotalIncl() {
        if (jTextFieldTotalIncl == null) {
            jTextFieldTotalIncl = new JTextField();
            jTextFieldTotalIncl.setFont(new java.awt.Font(
                    Constants.FONT_DIALOG, 1, 16));
            jTextFieldTotalIncl.setEditable(false);
        }
        return jTextFieldTotalIncl;
    }

    private JLabel getJLabelBTW6() {
        if (jLabelBTW6 == null) {
            jLabelBTW6 = new JLabel();
            jLabelBTW6.setFont(new java.awt.Font(Constants.FONT_DIALOG, 0, 14));
            jLabelBTW6.setText(Constants.BTW6);
        }
        return jLabelBTW6;
    }

    private JLabel getJLabelTotalIncl() {
        if (jLabelTotalIncl == null) {
            jLabelTotalIncl = new JLabel();
            jLabelTotalIncl.setFont(new java.awt.Font(Constants.FONT_DIALOG, 1,
                    16));
            jLabelTotalIncl.setText(Constants.TOTAL_INCL);
        }
        return jLabelTotalIncl;
    }

    private JLabel getJLabelBTW21() {
        if (jLabelBTW21 == null) {
            jLabelBTW21 = new JLabel();
            jLabelBTW21
                    .setFont(new java.awt.Font(Constants.FONT_DIALOG, 0, 14));
            jLabelBTW21.setText(Constants.BTW21);
        }
        return jLabelBTW21;
    }

    private JTextField getJTextFieldVat21() {
        if (jTextFieldVat21 == null) {
            jTextFieldVat21 = new JTextField();
            jTextFieldVat21.setFont(new java.awt.Font(Constants.FONT_DIALOG, 0,
                    14));
            jTextFieldVat21.setEditable(false);
        }
        return jTextFieldVat21;
    }

    public JRadioButton getJRadioButtonVat() {
        if (jRadioButtonVat == null) {
            jRadioButtonVat = new JRadioButton();
        }
        return jRadioButtonVat;
    }

    private JLabel getJLabelBTW() {
        if (jLabelBTW == null) {
            jLabelBTW = new JLabel();
            jLabelBTW.setText(Constants.BTW_);
        }
        return jLabelBTW;
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
                Constants.UNIT, new BigDecimal(1000), new BigDecimal(10000000)};
        TableCellRenderer headerRenderer = table.getTableHeader()
                .getDefaultRenderer();
        for (int i = 0; i < utilities.Figures.MAX_INV_DETAIL_COLUMNS; i++) {
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
        for (int col = 1; col < utilities.Figures.MAX_INV_DETAIL_COLUMNS - 2; col++) {
            object = jTableInvoiceDetails.getModel().getValueAt(row, col);
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
                    if (!object.toString().equals(Constants.EMPTY)) {
                        return false;
                    }
                    break;
                case 5:
                    // VAT% only if price & Quantity
                    if (extractDouble(object) != 0d) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private boolean validCellUpdated(int row, int col) {
        Object object = jTableInvoiceDetails.getModel().getValueAt(row, col);

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
            case 5: {
                if ((object == null) || (object.toString().equals(Constants.EMPTY))) {

                    JOptionPane.showMessageDialog(parent,
                            Constants.ONLY_NUMBERS_ALLOWED);
                }
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

    private void calculateColumnWidth(Object[] row, int[] columnWidth) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].toString().length() > columnWidth[i]) {
                columnWidth[i] = row[i].toString().length();
            }
        }
    }

    class MyTableModel extends GenericTableModel {

        MyTableModel(int[] columnWidth) {
            super(columnWidth);
        }

        public void setTableModel(int[] columnWidth) {
            setColumnNames(getInvoiceDetailTitles());
            setData(getInvoiceDetailColumns(columnWidth));
        }

        public void updateLineTotal(int row) {
            double qty = extractDouble(jTableInvoiceDetails.getModel()
                    .getValueAt(row, 2));
            double price = extractDouble(jTableInvoiceDetails.getModel()
                    .getValueAt(row, 3));

            double total = qty * price;
            ((GenericTableModel) jTableInvoiceDetails.getModel()).getData()[row][utilities.Figures.MAX_INV_DETAIL_COLUMNS - 1] = new BigDecimal(
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
            return (col <= 5) && (col >= 1);
        }

    }

}