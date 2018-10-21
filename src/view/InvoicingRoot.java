package view;

import com.toedter.calendar.JDateChooser;
import model.*;
import persistency.controller.*;
import utilities.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Iterator;
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
public class InvoicingRoot extends javax.swing.JFrame {

    private JTabbedPane tabbedPane;
    private JLabel jLabelCustomerType;
    private JLabel jLabelCustomerName;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabelCodeTitle;
    private JLabel jLabelCodeHeaderSelection;
    private JLabel jLabelQuoteStatus;
    private JLabel jLabelTotaal;
    private JLabel jLabelTotalAmount;
    private JFormattedTextField jFormattedTextFieldCustomerPanelCustomerName;
    private JFormattedTextField jFormattedTextFieldProductPanelProductName;
    private JFormattedTextField jFormattedTextFieldInvoicePanelCustomerName;
    private JFormattedTextField jFormattedTextFieldQuotesPanelCustomerName;
    private JComboBox jComboBoxCustomerType;
    private JComboBox jComboBoxCodeHeader;
    private JComboBox jComboBoxInvoiceStatus;
    private JComboBox jComboBoxInvoiceType;
    private JComboBox jComboBoxQuoteStatus;
    private ComboBoxModel jComboBoxCodeHeaderModel;
    private ComboBoxModel jComboBoxCustomerTypeModel;
    private ComboBoxModel jComboBoxQuoteStatusModel;
    private ComboBoxModel jComboBoxInvoiceStatusModel;
    private ComboBoxModel jComboBoxInvoiceTypeModel;
    private JButton jButtonProductPanelProductSearch;
    private JButton jButtonProductPanelProductNew;
    private JButton jButtonInvoicePanelCustomerSearch;
    private JButton jButtonInvoicePanelInvoiceNew;
    private JButton jButtonQuotePanelQuoteNew;
    private JButton jButtonQuotePanelCustomerSearch;
    private JButton jButtonNewCustomerPanelCustomer;
    private JButton jButtonCustomerPanelCustomerSearch;
    private JButton jButtonNewCode;
    private JButton jButtonNewNumber;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteCustomer;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateCustomer;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteQuote;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateQuote;
    private JRadioButtonMenuItem jRadioButtonMenuItemCreditInvoice;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateInvoice;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteProduct;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateProduct;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteCode;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateCode;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteNumber;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateNumber;
    private JPopupMenu jPopupMenuCustomerList;
    private JPopupMenu jPopupMenuQuoteList;
    private JPopupMenu jPopupMenuInvoiceList;
    private JPopupMenu jPopupMenuProductList;
    private JPopupMenu jPopupMenuCodeList;
    private JPopupMenu jPopupMenuNumberList;
    private JScrollPane customerScrollPane;
    private JScrollPane productScrollPane;
    private JScrollPane quoteScrollPane;
    private JScrollPane invoiceScrollPane;
    private JScrollPane jScrollPaneNumber;
    private JScrollPane jScrollPaneCodeDetail;
    private JPanel customerPanel;
    private JPanel productPanel;
    private JPanel quotePanel;
    private JPanel invoicePanel;
    private JPanel settingsPanel;
    private JPanel jPanelNorthPanelCode;
    private JPanel jPanelCenterPanelCode;
    private JPanel jPanelSouthPanelCode;
    private JPanel jPanelSouthPanelNumber;
    private JPanel jPanelCenterPanelNumber;
    private JPanel jPanelNorthPanelNumber;
    private JInternalFrame jInternalFrameNumber;
    private JInternalFrame jInternalFrameCode;
    private JSplitPane jSplitPaneConfiguration;
    private JTable jTableCustomers;
    private JTable jTableProducts;
    private JTable jTableQuotes;
    private JTable jTableInvoices;
    private JTable jTableNumberDetails;
    private JTable jTableCodeDetails;
    private TableModel jTableCustomersModel;
    private TableModel jTableProductsModel;
    private TableModel jTableQuotesModel;
    private TableModel jTableInvoicesModel;
    private TableModel jTableCodeDetailsModel;
    private TableModel jTableNumberDetailsModel;
    private JDateChooser reqDlvDate;
    private TreeMap<String, String> customerTypes, quoteStats, invoiceStats,
            invoiceTypes, nbrCategories;
    private int row;
    private Date toDay;
    private JFrame frame;
    private DateCellRenderer dateCellRenderer;
    private DateRenderer dateRenderer;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager
                    .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public InvoicingRoot() {
        super();
        try {
            toDay = new Date();
        } catch (final DatumException e) {
            e.printStackTrace();
        }
        // initGUI();
    }

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final InvoicingRoot inst = new InvoicingRoot();
                inst.initGUI();
            }
        });
    }

    public void initGUI() {
        try {
            frame = this;
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setMinimumSize(new java.awt.Dimension(550, 750));
            final JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            getContentPane().add(topPanel);

            // Create the tab pages
            createCustomerPage();
            createProductPage();
            createQuotePage();
            createInvoicePage();
            createSettingsPage();

            // Create a tabbed pane
            tabbedPane = new JTabbedPane();

            tabbedPane.addTab("Klanten", customerPanel);
            tabbedPane.addTab("Offertes", quotePanel);
            tabbedPane.addTab("Facturen", invoicePanel);
            tabbedPane.addTab("Producten", productPanel);
            tabbedPane.addTab("Instellingen", settingsPanel);
            topPanel.add(tabbedPane, BorderLayout.CENTER);
            tabbedPane.setPreferredSize(new java.awt.Dimension(1189, 720));

            pack();
            this.setSize(1363, 749);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setFocusable(true);

        } catch (final Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }

    /**
     * Builds the Settings Panel
     */
    public void createSettingsPage() {

        settingsPanel = new JPanel();
        final GroupLayout settingsPanelLayout = new GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setVerticalGroup(settingsPanelLayout
                .createSequentialGroup()
                .addGap(7)
                .addComponent(getJSplitPaneConfiguration(), 0, 681,
                        Short.MAX_VALUE));
        settingsPanelLayout.setHorizontalGroup(settingsPanelLayout
                .createSequentialGroup()
                .addGap(7)
                .addComponent(getJSplitPaneConfiguration(), 0, 730,
                        Short.MAX_VALUE));

        getJTableCodeDetails().setModel(getJTableCodeDetailsModel(null));
        getJTableCodeDetails().setToolTipText("Klik om te wijzigen");
        setComponentPopupMenu(getJTableCodeDetails(), getJPopupMenuCodeList());

        getJTableNumberDetails().setToolTipText("Klik om te wijzigen");
        setComponentPopupMenu(getJTableNumberDetails(),
                getJPopupMenuNumberList());

        getJComboBoxCodeHeader().addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                String id = getJComboBoxCodeHeader().getSelectedItem()
                        .toString();
                id = id.substring(0, 3);
                getJTableCodeDetails().setModel(getJTableCodeDetailsModel(id));
            }
        });

    }

    /**
     * Builds the Customer Panel
     */
    public void createCustomerPage() {

        customerPanel = new JPanel();
        final GroupLayout customerPanelLayout = new GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanel.addFocusListener(new FocusAdapter() {
            public void focusGained() {
                resetCustomerPanel();
            }
        });
        {
            customerScrollPane = new JScrollPane();
            {
                resetCustomerPanel();
                jTableCustomers.getTableHeader().setFont(
                        new java.awt.Font("Dialog", 1, 12));
                jTableCustomers.setToolTipText("Klik om te wijzigen");
            }
        }
        customerPanelLayout
                .setVerticalGroup(customerPanelLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                customerPanelLayout
                                        .createParallelGroup(
                                                GroupLayout.Alignment.BASELINE)
                                        .addComponent(
                                                getJButtonCustomerPanelCustomerSearch(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                getJComboBoxCustomerType(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                getJFormattedTextFieldCustomerPanelCustomerName(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getJLabelCustomerName(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getJLabelCustomerType(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, 19,
                                                GroupLayout.PREFERRED_SIZE))
                        .addGap(41)
                        .addComponent(customerScrollPane, 0, 546,
                                Short.MAX_VALUE)
                        .addGap(22)
                        .addComponent(getJButtonNewCustomerPanelCustomer(),
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, 22));
        customerPanelLayout
                .setHorizontalGroup(customerPanelLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                customerPanelLayout
                                        .createParallelGroup()
                                        .addGroup(
                                                GroupLayout.Alignment.LEADING,
                                                customerPanelLayout
                                                        .createSequentialGroup()
                                                        .addGroup(
                                                                customerPanelLayout
                                                                        .createParallelGroup()
                                                                        .addComponent(
                                                                                getJLabelCustomerName(),
                                                                                GroupLayout.Alignment.LEADING,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                customerPanelLayout
                                                                                        .createSequentialGroup()
                                                                                        .addPreferredGap(
                                                                                                getJLabelCustomerName(),
                                                                                                getJButtonNewCustomerPanelCustomer(),
                                                                                                LayoutStyle.ComponentPlacement.INDENT)
                                                                                        .addComponent(
                                                                                                getJButtonNewCustomerPanelCustomer(),
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                81,
                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(24)
                                                        .addComponent(
                                                                getJFormattedTextFieldCustomerPanelCustomerName(),
                                                                0, 766,
                                                                Short.MAX_VALUE)
                                                        .addPreferredGap(
                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(
                                                                getJLabelCustomerType(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                98,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(
                                                                getJComboBoxCustomerType(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                146,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(
                                                                getJButtonCustomerPanelCustomerSearch(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                102,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(74))
                                        .addComponent(customerScrollPane,
                                                GroupLayout.Alignment.LEADING,
                                                0, 1324, Short.MAX_VALUE))
                        .addContainerGap());

    }

    /**
     *
     */
    private void resetCustomerPanel() {
        jTableCustomersModel = new DefaultTableModel(getEmptyCustomerColumns(),
                getCustomerColumnTitles());
        jTableCustomers = new JTable();
        customerScrollPane.setViewportView(jTableCustomers);
        jTableCustomers.setModel(jTableCustomersModel);
    }

    /**
     * Generate Column titles
     *
     * @return String[]
     */
    private String[] getCustomerColumnTitles() {
        return new String[]{"Nummer", "Naam", "Ondernemersnummer",
                "Telefoon", "GSM", "eMail", "Type"};
    }

    private String[][] getEmptyCustomerColumns() {

        return null;
    }

    private String[][] getEmptyProductColumns() {

        return null;
    }

    /**
     * Generate Column content for all selected customers
     *
     * @return String[][]
     */

    private String[][] getCustomerColumns(final int[] columnWidth) {
        String[][] columns;
        Customer customer;
        final String[] filter = getCustomerFilter();
        final Collection<Business> list = CustomerController
                .getCustomers(filter);
        columns = new String[list.size()][];
        final Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            customer = (Customer) it.next();
            columns[i] = new String[]{
                    customer.getIdCus(),
                    customer.getCusName(),
                    customer.getCusVat(),
                    customer.getCusPhone(),
                    customer.getCusMobile(),
                    customer.getCusEMail(),
                    CodeController.getOneCodeDetail(
                            CodeEnum.CUSTOMER_TYPE.getType(),
                            customer.getCusType()).getCodeDesc()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;
        }
        return columns;
    }

    private void calculateColumnWidth(final Object[] row,
                                      final int[] columnWidth) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].toString().length() > columnWidth[i]) {
                columnWidth[i] = row[i].toString().length();
            }
        }
    }

    /**
     * @return
     */
    private String[] getCustomerFilter() {
        final String name = getJFormattedTextFieldCustomerPanelCustomerName()
                .getText();
        final Object desc = getJComboBoxCustomerType().getSelectedItem();
        final String type = customerTypes.get(desc);
        final String[] filter = {name, type, Constants.TRUE};
        return filter;
    }

    private JComboBox getJComboBoxCustomerType() {
        if (jComboBoxCustomerType == null) {
            customerTypes = CodeController
                    .getCodeDetails(CodeEnum.CUSTOMER_TYPE.getType());// CustomerType
            jComboBoxCustomerTypeModel = new DefaultComboBoxModel(customerTypes
                    .keySet().toArray());
            jComboBoxCustomerType = new JComboBox();
            jComboBoxCustomerType.setModel(jComboBoxCustomerTypeModel);
        }
        return jComboBoxCustomerType;
    }

    private JFormattedTextField getJFormattedTextFieldCustomerPanelCustomerName() {
        if (jFormattedTextFieldCustomerPanelCustomerName == null) {
            jFormattedTextFieldCustomerPanelCustomerName = new JFormattedTextField();
            jFormattedTextFieldCustomerPanelCustomerName
                    .setText(Constants.EMPTY);
        }
        return jFormattedTextFieldCustomerPanelCustomerName;
    }

    private JLabel getJLabelCustomerName() {
        if (jLabelCustomerName == null) {
            jLabelCustomerName = new JLabel();
            jLabelCustomerName.setText("Klant Naam :");
        }
        return jLabelCustomerName;
    }

    private JLabel getJLabelCustomerType() {
        if (jLabelCustomerType == null) {
            jLabelCustomerType = new JLabel();
            jLabelCustomerType.setText("Type Klant :");
        }
        return jLabelCustomerType;
    }

    private JButton getJButtonCustomerPanelCustomerSearch() {
        if (jButtonCustomerPanelCustomerSearch == null) {
            jButtonCustomerPanelCustomerSearch = new JButton();
            jButtonCustomerPanelCustomerSearch.setText(Constants.SEARCH);
            jButtonCustomerPanelCustomerSearch
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                refillJTableCustomer();
                            }
                        }
                    });
        }
        return jButtonCustomerPanelCustomerSearch;
    }

    /**
     * Refill CustomerTable
     */
    private void refillJTableCustomer() {
        final int[] columnWidth = new int[getCustomerColumnTitles().length];

        jTableCustomersModel = new DefaultTableModel(
                getCustomerColumns(columnWidth), getCustomerColumnTitles());
        jTableCustomers = new JTable();
        jTableCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        customerScrollPane.setViewportView(jTableCustomers);
        jTableCustomers.setModel(jTableCustomersModel);
        jTableCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableCustomers.getTableHeader().setFont(
                new java.awt.Font("Dialog", 1, 12));
        jTableCustomers.setToolTipText("Klik om te wijzigen");
        setAlignmentCustomer(jTableCustomers);
        setComponentPopupMenu(jTableCustomers, getJPopupMenuCustomerList());
    }

    /**
     */
    private void setAlignmentCustomer(final JTable jTable) {
        final CellRenderer renderer = new CellRenderer(jTable);
        for (int i = 0; i < getCustomerColumnTitles().length; i++) {
            switch (i) {
                case 1:
                case 2:
                case 5: {
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.LEFT);
                    break;
                }// left align amount
                // case 5:
                // {
                // jTable.getColumnModel().getColumn(i).setCellRenderer(CellRenderer.RIGHT);
                // // Right align amount
                // break;
                // }
                default:
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.CENTER); // Right align
                    // amount
                    break;
            }
        }
    }

    private JButton getJButtonNewCustomerPanelCustomer() {
        if (jButtonNewCustomerPanelCustomer == null) {
            jButtonNewCustomerPanelCustomer = new JButton();
            jButtonNewCustomerPanelCustomer.setText("Nieuw");
            jButtonNewCustomerPanelCustomer.setToolTipText("Nieuwe klant");
            jButtonNewCustomerPanelCustomer
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                new JDialogCustomer(frame, null,
                                        CRUDOperationEnum.NEW);
                                refillJTableCustomer();
                            }
                        }
                    });

        }
        return jButtonNewCustomerPanelCustomer;
    }

    private JPopupMenu getJPopupMenuCustomerList() {
        if (jPopupMenuCustomerList == null) {
            jPopupMenuCustomerList = new JPopupMenu();
            final ButtonGroup customerContext = new ButtonGroup();
            customerContext.add(getJRadioButtonMenuItemUpdateCustomer());
            customerContext.add(getJRadioButtonMenuItemDeleteCustomer());
            getJRadioButtonMenuItemUpdateCustomer().setSelected(true);
            jPopupMenuCustomerList.add(getJRadioButtonMenuItemUpdateCustomer());
            jPopupMenuCustomerList.add(getJRadioButtonMenuItemDeleteCustomer());
        }
        return jPopupMenuCustomerList;
    }

    private JPopupMenu getJPopupMenuCodeList() {
        if (jPopupMenuCodeList == null) {
            jPopupMenuCodeList = new JPopupMenu();
            final ButtonGroup codeContext = new ButtonGroup();
            codeContext.add(getJRadioButtonMenuItemUpdateCode());
            codeContext.add(getJRadioButtonMenuItemDeleteCode());
            getJRadioButtonMenuItemUpdateCode().setSelected(true);
            jPopupMenuCodeList.add(getJRadioButtonMenuItemUpdateCode());
            jPopupMenuCodeList.add(getJRadioButtonMenuItemDeleteCode());
        }
        return jPopupMenuCodeList;
    }

    private JPopupMenu getJPopupMenuNumberList() {
        if (jPopupMenuNumberList == null) {
            jPopupMenuNumberList = new JPopupMenu();
            final ButtonGroup numberContext = new ButtonGroup();
            numberContext.add(getJRadioButtonMenuItemUpdateNumber());
            numberContext.add(getJRadioButtonMenuItemDeleteNumber());
            getJRadioButtonMenuItemUpdateNumber().setSelected(true);
            jPopupMenuNumberList.add(getJRadioButtonMenuItemUpdateNumber());
            jPopupMenuNumberList.add(getJRadioButtonMenuItemDeleteNumber());
        }
        return jPopupMenuNumberList;
    }

    /**
     * Builds the Product Panel
     */
    public void createProductPage() {

        productPanel = new JPanel();
        final GroupLayout productPanelLayout = new GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanel.addFocusListener(new FocusAdapter() {
            public void focusGained() {
                resetProductPanel();
            }
        });

        productScrollPane = new JScrollPane();
        productPanelLayout
                .setVerticalGroup(productPanelLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                productPanelLayout
                                        .createParallelGroup(
                                                GroupLayout.Alignment.BASELINE)
                                        .addComponent(
                                                getJButtonProductPanelProductSearch(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                getJFormattedTextFieldProductPanelProductNaam(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, 26,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getJLabel3(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                        .addGap(26)
                        .addComponent(productScrollPane,
                                GroupLayout.PREFERRED_SIZE, 564,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(
                                LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getJButtonProductPanelProductNew(),
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE));
        productPanelLayout
                .setHorizontalGroup(productPanelLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                productPanelLayout
                                        .createParallelGroup()
                                        .addComponent(productScrollPane,
                                                GroupLayout.Alignment.LEADING,
                                                0, 1324, Short.MAX_VALUE)
                                        .addGroup(
                                                GroupLayout.Alignment.LEADING,
                                                productPanelLayout
                                                        .createSequentialGroup()
                                                        .addPreferredGap(
                                                                productScrollPane,
                                                                getJLabel3(),
                                                                LayoutStyle.ComponentPlacement.INDENT)
                                                        .addGroup(
                                                                productPanelLayout
                                                                        .createParallelGroup()
                                                                        .addComponent(
                                                                                getJLabel3(),
                                                                                GroupLayout.Alignment.LEADING,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                118,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                productPanelLayout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(
                                                                                                getJButtonProductPanelProductNew(),
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                81,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(37)))
                                                        .addComponent(
                                                                getJFormattedTextFieldProductPanelProductNaam(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                729,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(39)
                                                        .addComponent(
                                                                getJButtonProductPanelProductSearch(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                113,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 313,
                                                                Short.MAX_VALUE)))
                        .addContainerGap());
        {

            resetProductPanel();
            jTableProducts.getTableHeader().setFont(
                    new java.awt.Font("Dialog", 1, 12));
            jTableProducts.setToolTipText("Klik om te wijzigen");
        }

    }

    /**
     *
     */
    private void resetProductPanel() {
        jTableProductsModel = new DefaultTableModel(getEmptyProductColumns(),
                getProductColumnTitles());
        jTableProducts = new JTable();
        productScrollPane.setViewportView(jTableProducts);
        jTableProducts.setModel(jTableProductsModel);
    }

    /**
     * Generate Column titles
     *
     * @return String[]
     */
    private String[] getProductColumnTitles() {
        return new String[]{"Nummer", "Naam", "Omschrijving", "Categorie",
                "Type"};
    }

    /**
     * Generate Column content for all selected products
     *
     * @return String[][]
     */
    private String[][] getProductColumns(final int[] columnWidth) {
        String[][] columns;
        Product product;
        final String[] filter = getProductFilter(getJFormattedTextFieldProductPanelProductNaam()
                .getText());
        final Collection<Business> list = ProductController.getProducts(filter);
        columns = new String[list.size()][];
        final Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            product = (Product) it.next();
            columns[i] = new String[]{
                    product.getIdProd(),
                    product.getProdCode(),
                    product.getProdDesc(),
                    CodeController.getOneCodeDetail(
                            ProductController.PRODUCT_CATEGORY,
                            product.getProdCat()).getCodeDesc(),
                    CodeController.getOneCodeDetail(
                            ProductController.PRODUCT_TYPE,
                            product.getProdType()).getCodeDesc()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;
        }
        return columns;
    }

    /**
     * @return
     */
    private String[] getProductFilter(final String prodCode) {
        final String[] filter = {prodCode, Constants.EMPTY, Constants.TRUE};
        return filter;
    }

    private JButton getJButtonProductPanelProductNew() {
        if (jButtonProductPanelProductNew == null) {
            jButtonProductPanelProductNew = new JButton();
            jButtonProductPanelProductNew.setText("Nieuw");
            jButtonProductPanelProductNew
                    .setToolTipText("Nieuw product/dienst");
            jButtonProductPanelProductNew
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                new JDialogProduct(frame, null,
                                        CRUDOperationEnum.NEW);
                                refillJTableProduct();
                            }
                        }
                    });
        }
        return jButtonProductPanelProductNew;
    }

    private JButton getJButtonProductPanelProductSearch() {
        if (jButtonProductPanelProductSearch == null) {
            jButtonProductPanelProductSearch = new JButton();
            jButtonProductPanelProductSearch.setText(Constants.SEARCH);
            jButtonProductPanelProductSearch
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                refillJTableProduct();
                            }

                        }
                    });
        }
        return jButtonProductPanelProductSearch;
    }

    private void refillJTableProduct() {
        final int[] columnWidth = new int[getProductColumnTitles().length];

        jTableProductsModel = new DefaultTableModel(
                getProductColumns(columnWidth), getProductColumnTitles());
        jTableProducts = new JTable();
        jTableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        productScrollPane.setViewportView(jTableProducts);

        jTableProducts.setModel(jTableProductsModel);
        jTableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableProducts.getTableHeader().setFont(
                new java.awt.Font("Dialog", 1, 12));
        jTableProducts.setToolTipText("Klik om te wijzigen");
        setAlignmentProduct(jTableProducts);
        setComponentPopupMenu(jTableProducts, getJPopupMenuProductList());

    }

    /**

     */
    private void setAlignmentProduct(final JTable jTable) {
        final CellRenderer renderer = new CellRenderer(jTable);
        for (int i = 0; i < getProductColumnTitles().length; i++) {
            switch (i) {
                case 1:
                case 2: {
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.LEFT);
                    break;
                }// left align amount
                // case 5:
                // {
                // jTable.getColumnModel().getColumn(i).setCellRenderer(CellRenderer.RIGHT);
                // // Right align amount
                // break;
                // }
                default:
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.CENTER); // Right align
                    // amount
                    break;
            }
        }
    }

    private JPopupMenu getJPopupMenuProductList() {
        if (jPopupMenuProductList == null) {
            jPopupMenuProductList = new JPopupMenu();
            final ButtonGroup productContext = new ButtonGroup();
            productContext.add(getJRadioButtonMenuItemUpdateProduct());
            productContext.add(getJRadioButtonMenuItemDeleteProduct());
            getJRadioButtonMenuItemUpdateProduct().setSelected(true);
            jPopupMenuProductList.add(getJRadioButtonMenuItemUpdateProduct());
            jPopupMenuProductList.add(getJRadioButtonMenuItemDeleteProduct());
        }
        return jPopupMenuProductList;
    }

    private JLabel getJLabel3() {
        if (jLabel3 == null) {
            jLabel3 = new JLabel();
            jLabel3.setText("Product Naam :");
        }
        return jLabel3;
    }

    private JFormattedTextField getJFormattedTextFieldProductPanelProductNaam() {
        if (jFormattedTextFieldProductPanelProductName == null) {
            jFormattedTextFieldProductPanelProductName = new JFormattedTextField();
            jFormattedTextFieldProductPanelProductName.setText(Constants.EMPTY);
        }
        return jFormattedTextFieldProductPanelProductName;
    }

    /**
     * Builds the Quotes panel
     */
    public void createQuotePage() {
        quotePanel = new JPanel();
        final GroupLayout quotePanelLayout = new GroupLayout(quotePanel);
        quotePanel.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent evt) {
                resetQuotePanel();
            }

            public void focusLost(final FocusEvent evt) {
                resetQuotePanel();
            }

        });

        quotePanel.setLayout(quotePanelLayout);
        quoteScrollPane = new JScrollPane();
        quotePanelLayout
                .setVerticalGroup(quotePanelLayout
                        .createSequentialGroup()
                        .addGap(7)
                        .addGroup(
                                quotePanelLayout
                                        .createParallelGroup(
                                                GroupLayout.Alignment.BASELINE)
                                        .addComponent(
                                                getJButtonQuotePanelQuoteSearch(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                getJComboBoxQuoteStatus(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getJLabel1(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                getJFormattedTextFieldQuotesPanelCustomerName(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE, 23,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getJLabelQuoteStatus(),
                                                GroupLayout.Alignment.BASELINE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 54, Short.MAX_VALUE)
                        .addComponent(getReqDlvDate(),
                                GroupLayout.PREFERRED_SIZE, 0,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quoteScrollPane,
                                GroupLayout.PREFERRED_SIZE, 527,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(23)
                        .addComponent(getJButtonQuotePanelQuoteNew(),
                                GroupLayout.PREFERRED_SIZE, 27,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, 24));
        quotePanelLayout
                .setHorizontalGroup(quotePanelLayout
                        .createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                quotePanelLayout
                                        .createParallelGroup()
                                        .addGroup(
                                                GroupLayout.Alignment.LEADING,
                                                quotePanelLayout
                                                        .createSequentialGroup()
                                                        .addGroup(
                                                                quotePanelLayout
                                                                        .createParallelGroup()
                                                                        .addComponent(
                                                                                getJLabel1(),
                                                                                GroupLayout.Alignment.LEADING,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                quotePanelLayout
                                                                                        .createSequentialGroup()
                                                                                        .addGap(7)
                                                                                        .addComponent(
                                                                                                getJButtonQuotePanelQuoteNew(),
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                83,
                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(
                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(
                                                                quotePanelLayout
                                                                        .createParallelGroup()
                                                                        .addComponent(
                                                                                getJFormattedTextFieldQuotesPanelCustomerName(),
                                                                                GroupLayout.Alignment.LEADING,
                                                                                0,
                                                                                658,
                                                                                Short.MAX_VALUE)
                                                                        .addGroup(
                                                                                GroupLayout.Alignment.LEADING,
                                                                                quotePanelLayout
                                                                                        .createSequentialGroup()
                                                                                        .addGap(36)
                                                                                        .addComponent(
                                                                                                getReqDlvDate(),
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                210,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(412)))
                                                        .addPreferredGap(
                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(
                                                                getJLabelQuoteStatus(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                114,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(32)
                                                        .addComponent(
                                                                getJComboBoxQuoteStatus(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                175,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(47)
                                                        .addComponent(
                                                                getJButtonQuotePanelQuoteSearch(),
                                                                GroupLayout.PREFERRED_SIZE,
                                                                108,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0,
                                                                64,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addComponent(quoteScrollPane,
                                                GroupLayout.Alignment.LEADING,
                                                0, 1324, Short.MAX_VALUE))
                        .addContainerGap());
        {
            resetQuotePanel();
            jTableQuotes.getTableHeader().setFont(
                    new java.awt.Font("Dialog", 1, 12));
            jTableQuotes.setToolTipText("Klik om te wijzigen");

        }

    }

    /**
     *
     */
    private void resetQuotePanel() {
        jTableQuotesModel = new DefaultTableModel(getEmptyQuoteColumns(),
                getQuoteColumnTitles());
        jTableQuotes = new JTable();
        quoteScrollPane.setViewportView(jTableQuotes);
        jTableQuotes.setModel(jTableQuotesModel);
    }

    /**
     * @return
     */
    private String[] getQuoteColumnTitles() {
        return new String[]{"Nummer", "Klant", "Leverdatum", "Straat",
                "Huisnummer", "Postbus", "Postcode", "Gemeente"};
    }

    private String[][] getEmptyQuoteColumns() {

        return null;
    }

    /**
     * @return
     */
    private String[][] getQuoteColumns(final String customerName,
                                       final int[] columnWidth) {
        String[][] columns;
        QuoteView quote;
        final String[] filter = getQuoteFilter();
        final Collection<Business> list = QuoteController
                .getQuotesByCustomerName(filter);
        columns = new String[list.size()][];
        final Iterator<Business> it = list.iterator();
        Address address;
        int i = 0;
        while (it.hasNext()) {
            quote = (QuoteView) it.next();
            address = AddressController.getAddress(quote.getQteDlvAddid());
            columns[i] = new String[]{quote.getIdQuote(), quote.getCusName(),
                    quote.getQteReqDlvDate("dd-MM-yy"), address.getAddStreet(),
                    address.getAddNumber(), address.getAddBox(),
                    address.getAddZip(), address.getAddCity()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;

        }
        return columns;
    }

    /**
     * @return
     */
    private String[] getQuoteFilter() {
        final String customerName = getJFormattedTextFieldQuotesPanelCustomerName()
                .getText();
        final Object desc = getJComboBoxQuoteStatus().getSelectedItem();
        final String status = quoteStats.get(desc);
        final String type = Constants.EMPTY;
        final String[] filter = {customerName, status, type, Constants.TRUE};
        return filter;
    }

    private void refillJTableQuote(final String cusID) {
        final int[] columnWidth = new int[getQuoteColumnTitles().length];

        jTableQuotesModel = new DefaultTableModel(getQuoteColumns(cusID,
                columnWidth), getQuoteColumnTitles());
        jTableQuotes = new JTable();
        jTableQuotes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        quoteScrollPane.setViewportView(jTableQuotes);

        jTableQuotes.setModel(jTableQuotesModel);
        jTableQuotes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableQuotes.getTableHeader().setFont(
                new java.awt.Font("Dialog", 1, 12));
        jTableQuotes.setToolTipText("Klik om te wijzigen");
        setAlignmentQuote(jTableQuotes);
        setComponentPopupMenu(jTableQuotes, getJPopupMenuQuoteList());

    }

    /**

     */
    private void setAlignmentQuote(final JTable jTable) {
        final CellRenderer renderer = new CellRenderer(jTable);
        for (int i = 0; i < getQuoteColumnTitles().length; i++) {
            switch (i) {
                case 1:
                case 3:
                case 7: {
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.LEFT);
                    break;
                }// left align amount
                // case 5:
                // {
                // jTable.getColumnModel().getColumn(i).setCellRenderer(CellRenderer.RIGHT);
                // // Right align amount
                // break;
                // }
                default:
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.CENTER); // Right align
                    // amount
                    break;
            }
        }
    }

    private JPopupMenu getJPopupMenuQuoteList() {
        if (jPopupMenuQuoteList == null) {
            jPopupMenuQuoteList = new JPopupMenu();
            final ButtonGroup quoteContext = new ButtonGroup();
            quoteContext.add(getJRadioButtonMenuItemUpdateQuote());
            quoteContext.add(getJRadioButtonMenuItemDeleteQuote());
            getJRadioButtonMenuItemUpdateQuote().setSelected(true);
            jPopupMenuQuoteList.add(getJRadioButtonMenuItemUpdateQuote());
            jPopupMenuQuoteList.add(getJRadioButtonMenuItemDeleteQuote());
        }
        return jPopupMenuQuoteList;
    }

    /**
     * Builds the Invoices Panel
     */
    public void createInvoicePage() {
        invoicePanel = new JPanel();
        final GroupLayout invoicePanelLayout = new GroupLayout(invoicePanel);
        invoicePanel.setLayout(invoicePanelLayout);
        invoiceScrollPane = new JScrollPane();
        invoicePanelLayout.setVerticalGroup(invoicePanelLayout.createSequentialGroup()
                .addGap(7)
                .addGroup(invoicePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getJButtonInvoicePanelCustomerSearch(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJComboBoxInvoiceType(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJLabel6(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJComboBoxInvoiceStatus(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJFormattedTextFieldInvoicePanelCustomerName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJLabel2(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJLabel4(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                .addGap(54)
                .addComponent(invoiceScrollPane, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, Short.MAX_VALUE)
                .addGroup(invoicePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getJButtonInvoicePanelInvoiceNew(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJLabelTotalAmount(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getJLabelTotaal(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, 30));
        invoicePanelLayout.setHorizontalGroup(invoicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(invoicePanelLayout.createParallelGroup()
                        .addComponent(invoiceScrollPane, GroupLayout.Alignment.LEADING, 0, 1318, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                .addGroup(invoicePanelLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                                .addPreferredGap(getJLabel2(), getJButtonInvoicePanelInvoiceNew(), LayoutStyle.ComponentPlacement.INDENT)
                                                .addComponent(getJButtonInvoicePanelInvoiceNew(), GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(getJLabel2(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(invoicePanelLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                                .addComponent(getJFormattedTextFieldInvoicePanelCustomerName(), GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18))
                                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                                .addGap(400)
                                                .addComponent(getJLabelTotaal(), GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(invoicePanelLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                                .addComponent(getJLabel6(), GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(getJComboBoxInvoiceType(), GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
                                                .addGap(18)
                                                .addComponent(jLabelTotalAmount, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                                                .addGap(83)))
                                .addComponent(getJLabel4(), GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getJComboBoxInvoiceStatus(), GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                                .addGap(56)
                                .addComponent(getJButtonInvoicePanelCustomerSearch(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 82, Short.MAX_VALUE)))
                .addContainerGap(18, 18));
        {
            jTableInvoicesModel = new DefaultTableModel(
                    getEmptyInvoiceColumns(), getInvoiceColumnTitles());
            jTableInvoices = new JTable();
            invoiceScrollPane.setViewportView(jTableInvoices);
            jTableInvoices.setModel(jTableInvoicesModel);
            jTableInvoices.getTableHeader().setFont(
                    new java.awt.Font("Dialog", 1, 12));
            jTableInvoices.setToolTipText("Klik om te wijzigen");
        }
        dateCellRenderer = new DateCellRenderer(toDay);
        dateRenderer = new DateRenderer();
    }

    /**
     * @return
     */
    private String[] getInvoiceColumnTitles() {
        return new String[]{"Nummer", "Klant", "Factuurdatum", "Vervaldatum",
                "Vervallen", "Bedrag", "Straat", "Huisnummer", "Postbus",
                "Postcode", "Gemeente"};
    }

    private String[][] getEmptyInvoiceColumns() {

        return null;
    }

    /**
     * @return
     */
    private Object[][] getInvoiceColumns(final String customerName,
                                         final int[] columnWidth) {
        Object[][] columns;
        InvoiceView invoice;
        final String[] filter = getInvoiceFilter();
        final Collection<Business> list = InvoiceController
                .getInvoicesByCustomerName(filter);
        columns = new Object[list.size()][];
        final Iterator<Business> it = list.iterator();
        Address address;
        int i = 0;
        Amount totalAmount = new Amount(0.0);
        while (it.hasNext()) {
            invoice = (InvoiceView) it.next();
            address = AddressController.getAddress(invoice.getInvAddid());
            // dateRenderer.setValue(new
            // java.util.Date(invoice.getInvDueDate().getTimeInMilliSeconds()));
            // //"dd-MM-yy"
            columns[i] = new Object[]{
                    invoice.getIdInvoice(),
                    invoice.getCusName(),
                    invoice.getInvDate("dd-MM-yy"),
                    // dateCellRenderer.getTableCellRendererComponent(jTableInvoices,
                    // invoice.getInvDueDate().toString(SEPARATOR), false,
                    // false, i, VALIDATION_COLUMN),
                    invoice.getInvDueDate("dd-MM-yy"), invoiceExpired(invoice),
                    getInvoiceAmount(invoice, totalAmount), address.getAddStreet(),
                    address.getAddNumber(), address.getAddBox(),
                    address.getAddZip(), address.getAddCity()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;
        }
        jLabelTotalAmount.setText(totalAmount.toString());
        return columns;
    }

    private String invoiceExpired(final Invoice invoice) {
        if (invoice.getInvStatus().equals(model.FixTypes.INVOICE_STATUS_CONFIRMED)) {
            return Constants.PAYED;
        }

        if (invoice.getInvDueDate().kleinerDan(toDay)) {
            return Constants.YES;
        }
        return Constants.EMPTY;
    }

    private String getInvoiceAmount(final Invoice invoice, Amount totalAmount) {
        final Amount amount = InvoiceController.getInvoiceAmount(invoice
                .getIdInvoice());
        totalAmount.add(amount);
        return amount.toString();
    }

    /**
     * @return
     */
    private String[] getInvoiceFilter() {
        final String customerName = getJFormattedTextFieldInvoicePanelCustomerName()
                .getText();
        Object desc = getJComboBoxInvoiceStatus().getSelectedItem();
        final String status = invoiceStats.get(desc);
        desc = getJComboBoxInvoiceType().getSelectedItem();
        final String type = invoiceTypes.get(desc);
        final String[] filter = {customerName, status, type, Constants.TRUE};
        return filter;
    }

    /**
     * @param cusID
     */
    private void refillJTableInvoice(final String cusID) {
        final int[] columnWidth = new int[getInvoiceColumnTitles().length];

        jTableInvoicesModel = new DefaultTableModel(getInvoiceColumns(cusID,
                columnWidth), getInvoiceColumnTitles());

        jTableInvoices = new JTable();
        jTableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        invoiceScrollPane.setViewportView(jTableInvoices);

        jTableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTableInvoices.getTableHeader().setFont(
                new java.awt.Font("Dialog", 1, 12));
        jTableInvoices.setToolTipText("Klik om te wijzigen");
        jTableInvoices.setModel(jTableInvoicesModel);
        setAlignmentInvoicing(jTableInvoices);

        // jTableInvoices.getTableHeader().setBackground(Color.cyan);;
        setComponentPopupMenu(jTableInvoices, getJPopupMenuInvoiceList());

    }

    /**

     */
    private void setAlignmentInvoicing(final JTable jTable) {
        final CellRenderer renderer = new CellRenderer(jTable);
        for (int i = 0; i < getInvoiceColumnTitles().length; i++) {
            switch (i) {
                case 1:
                case 6:
                case 10: {
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.LEFT);
                    break;
                }// left align amount
                case 5: {
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.RIGHT); // Right align
                    // amount
                    break;
                }
                default:
                    jTable.getColumnModel().getColumn(i)
                            .setCellRenderer(CellRenderer.CENTER); // Right align
                    // amount
                    break;
            }
        }

    }

    private JPopupMenu getJPopupMenuInvoiceList() {
        if (jPopupMenuInvoiceList == null) {
            jPopupMenuInvoiceList = new JPopupMenu();
            final ButtonGroup invoiceContext = new ButtonGroup();
            invoiceContext.add(getJRadioButtonMenuItemUpdateInvoice());
            invoiceContext.add(getJRadioButtonMenuItemCreditInvoice());
            getJRadioButtonMenuItemUpdateInvoice().setSelected(true);
            jPopupMenuInvoiceList.add(getJRadioButtonMenuItemUpdateInvoice());
            jPopupMenuInvoiceList.add(getJRadioButtonMenuItemCreditInvoice());
        }
        return jPopupMenuInvoiceList;
    }

    /**
     * Auto-generated method for setting the popup menu for a component
     */
    private void setComponentPopupMenu(final java.awt.Component parent,
                                       final javax.swing.JPopupMenu menu) {
        parent.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(final java.awt.event.MouseEvent e) {
                // if(e.isPopupTrigger()
                menu.show(parent, e.getX(), e.getY());
                final JTable table = (JTable) e.getSource();
                table.clearSelection();
                final Point p = e.getPoint();
                row = table.rowAtPoint(p);
            }
        });
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateCustomer() {
        if (jRadioButtonMenuItemUpdateCustomer == null) {
            jRadioButtonMenuItemUpdateCustomer = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateCustomer.setText("Details");
            jRadioButtonMenuItemUpdateCustomer.setOpaque(false);
            jRadioButtonMenuItemUpdateCustomer
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {

                            final String id = (String) jTableCustomersModel
                                    .getValueAt(row, 0);// customer number
                            final Customer customer = CustomerController
                                    .getCustomer(id);
                            new JDialogCustomer(frame, customer,
                                    CRUDOperationEnum.UPDATE);
                            refillJTableCustomer();

                        }
                    });

        }
        return jRadioButtonMenuItemUpdateCustomer;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateCode() {
        if (jRadioButtonMenuItemUpdateCode == null) {
            jRadioButtonMenuItemUpdateCode = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateCode.setText("Details");
            jRadioButtonMenuItemUpdateCode.setOpaque(false);
            jRadioButtonMenuItemUpdateCode
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {

                            final String code = (String) jTableCodeDetailsModel
                                    .getValueAt(row, 0);// code
                            final String det = (String) jTableCodeDetailsModel
                                    .getValueAt(row, 1);// det
                            final CodeDetail codeDet = CodeController
                                    .getOneCodeDetail(code, det);// code
                            new JDialogCode(frame, code, codeDet,
                                    CRUDOperationEnum.UPDATE);
                            getJTableCodeDetails().setModel(
                                    getJTableCodeDetailsModel(code));

                        }
                    });

        }
        return jRadioButtonMenuItemUpdateCode;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateNumber() {
        if (jRadioButtonMenuItemUpdateNumber == null) {
            jRadioButtonMenuItemUpdateNumber = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateNumber.setText("Details");
            jRadioButtonMenuItemUpdateNumber.setOpaque(false);
            jRadioButtonMenuItemUpdateNumber
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            final String desc = (String) getJTableNumberDetailModel()
                                    .getValueAt(row, 0);// category
                            final String category = (String) getNbrCategories()
                                    .get(desc);

                            final int year = Integer
                                    .parseInt((String) getJTableNumberDetailModel()
                                            .getValueAt(row, 1));// year
                            final model.Number current = NumberController
                                    .readOneNumber(category, year);
                            new JDialogNumber(frame, current,
                                    CRUDOperationEnum.UPDATE);
                            getJTableNumberDetails().setModel(
                                    getJTableNumberDetailModel());
                        }
                    });

        }
        return jRadioButtonMenuItemUpdateNumber;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateQuote() {
        if (jRadioButtonMenuItemUpdateQuote == null) {
            jRadioButtonMenuItemUpdateQuote = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateQuote.setText("Details");
            jRadioButtonMenuItemUpdateQuote.setOpaque(false);
            jRadioButtonMenuItemUpdateQuote
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {

                            final String id = (String) jTableQuotesModel
                                    .getValueAt(row, 0);// quote
                            final Quote quote = QuoteController.getQuote(id);
                            new JDialogQuote(frame, quote,
                                    CRUDOperationEnum.UPDATE);
                            refillJTableQuote(Constants.EMPTY);

                        }
                    });

        }
        return jRadioButtonMenuItemUpdateQuote;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateProduct() {
        if (jRadioButtonMenuItemUpdateProduct == null) {
            jRadioButtonMenuItemUpdateProduct = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateProduct.setText("Details");
            jRadioButtonMenuItemUpdateProduct.setOpaque(false);
            jRadioButtonMenuItemUpdateProduct
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {

                            final String id = (String) jTableProductsModel
                                    .getValueAt(row, 0);// product number
                            final Product product = ProductController
                                    .getProduct(id);
                            new JDialogProduct(frame, product,
                                    CRUDOperationEnum.UPDATE);
                            refillJTableProduct();

                        }
                    });

        }
        return jRadioButtonMenuItemUpdateProduct;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateInvoice() {
        if (jRadioButtonMenuItemUpdateInvoice == null) {
            jRadioButtonMenuItemUpdateInvoice = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateInvoice.setText("Details");
            jRadioButtonMenuItemUpdateInvoice.setOpaque(false);
            jRadioButtonMenuItemUpdateInvoice
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            final String id = (String) jTableInvoicesModel
                                    .getValueAt(row, 0);// invoice
                            final Invoice invoice = InvoiceController
                                    .getInvoice(id);
                            new JDialogInvoice(frame, invoice,
                                    CRUDOperationEnum.UPDATE);
                            refillJTableInvoice(Constants.EMPTY);
                        }
                    });

        }
        return jRadioButtonMenuItemUpdateInvoice;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteCustomer() {
        if (jRadioButtonMenuItemDeleteCustomer == null) {
            jRadioButtonMenuItemDeleteCustomer = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteCustomer.setText("Verwijderen");
            jRadioButtonMenuItemDeleteCustomer.setOpaque(false);
            jRadioButtonMenuItemDeleteCustomer
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            // number
                            final String cusID = (String) jTableCustomersModel
                                    .getValueAt(row, 0);// customer
                            final Customer temp = CustomerController
                                    .getCustomer(cusID);
                            if (!(InvoiceController.getInvoices(new String[]{
                                    cusID, Constants.EMPTY, Constants.EMPTY,
                                    Constants.TRUE})).isEmpty()) {

                                JOptionPane.showMessageDialog(frame,
                                        temp.getCusName()
                                                + Constants.INVOICES_EXIST);
                            } else {
                                final int response = JOptionPaneItemRemove
                                        .confirm(cusID);
                                if (response == JOptionPane.YES_OPTION) {
                                    CustomerController.removeCustomer(cusID);
                                    QuoteController
                                            .removeQuoteByCustomer(cusID);
                                    refillJTableCustomer();
                                }
                            }
                        }
                    });
        }
        return jRadioButtonMenuItemDeleteCustomer;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteCode() {
        if (jRadioButtonMenuItemDeleteCode == null) {
            jRadioButtonMenuItemDeleteCode = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteCode.setText("Verwijderen");
            jRadioButtonMenuItemDeleteCode.setOpaque(false);
            jRadioButtonMenuItemDeleteCode
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            // number
                            final String code = (String) jTableCodeDetailsModel
                                    .getValueAt(row, 0);// code
                            final String detail = (String) jTableCodeDetailsModel
                                    .getValueAt(row, 1);// detail

                            final int response = JOptionPaneItemRemove
                                    .confirm(code + ", " + detail);
                            if (response == JOptionPane.YES_OPTION) {
                                CodeController.removeCodeDetail(code, detail);
                                getJTableCodeDetails().setModel(
                                        getJTableCodeDetailsModel(code));
                                // refill codedetails
                            }

                        }
                    });
        }
        return jRadioButtonMenuItemDeleteCode;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteNumber() {
        if (jRadioButtonMenuItemDeleteNumber == null) {
            jRadioButtonMenuItemDeleteNumber = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteNumber.setText("Verwijderen");
            jRadioButtonMenuItemDeleteNumber.setOpaque(false);
            jRadioButtonMenuItemDeleteNumber
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            // number
                            final String desc = (String) jTableNumberDetailsModel
                                    .getValueAt(row, 0);
                            final String category = (String) getNbrCategories()
                                    .get(desc);
                            final String strYear = (String) jTableNumberDetailsModel
                                    .getValueAt(row, 1);

                            final int response = JOptionPaneItemRemove
                                    .confirm(desc + ", " + strYear);
                            if (response == JOptionPane.YES_OPTION) {
                                NumberController.removeNumber(category,
                                        Integer.parseInt(strYear));
                                getJTableNumberDetails().setModel(
                                        getJTableNumberDetailModel());
                            }

                        }
                    });
        }
        return jRadioButtonMenuItemDeleteNumber;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteQuote() {
        if (jRadioButtonMenuItemDeleteQuote == null) {
            jRadioButtonMenuItemDeleteQuote = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteQuote.setText("Verwijderen");
            jRadioButtonMenuItemDeleteQuote.setOpaque(false);
            jRadioButtonMenuItemDeleteQuote
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            // number
                            final String id = (String) jTableQuotesModel
                                    .getValueAt(row, 0);// quotes
                            final Quote temp = QuoteController.getQuote(id);
                            if (temp.getQteStatus().equals(
                                    QuoteController.QUOTE_STATUS_CONFIRMED)) {
                                JOptionPane.showMessageDialog(frame,
                                        temp.getIdQuote()
                                                + Constants.QUOTE_CONFIRMED);
                            } else {
                                final int response = JOptionPaneItemRemove
                                        .confirm(id);
                                if (response == JOptionPane.YES_OPTION) {
                                    QuoteController.removeQuote(id);
                                    refillJTableQuote(Constants.EMPTY);
                                }
                            }
                        }
                    });
        }
        return jRadioButtonMenuItemDeleteQuote;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemCreditInvoice() {
        if (jRadioButtonMenuItemCreditInvoice == null) {
            jRadioButtonMenuItemCreditInvoice = new JRadioButtonMenuItem();
            jRadioButtonMenuItemCreditInvoice.setText("Crediteren");
            jRadioButtonMenuItemCreditInvoice.setOpaque(false);
            jRadioButtonMenuItemCreditInvoice
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            final String id = (String) jTableInvoicesModel
                                    .getValueAt(row, 0);// invoices
                            final Invoice temp = InvoiceController
                                    .getInvoice(id);
                            if (temp.getInvType().equals(model.FixTypes.CREDIT_NOTE)) {

                                JOptionPane.showMessageDialog(frame, id
                                        + Constants.CREDIT_NOTE_NOT_ALLOWED);
                            } else {
                                final int response = JOptionPaneItemRemove
                                        .confirm(id);
                                if (response == JOptionPane.YES_OPTION) {
                                    final Invoice newCreditNote = InvoiceController
                                            .createNewCreditNote(temp);
                                    InvoiceDetailController
                                            .createNewCreditNoteDetails(
                                                    newCreditNote, temp);
                                    new JDialogInvoice(frame, newCreditNote,
                                            CRUDOperationEnum.UPDATE);
                                    refillJTableInvoice(Constants.EMPTY);
                                }
                            }
                        }
                    });
        }
        return jRadioButtonMenuItemCreditInvoice;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteProduct() {
        if (jRadioButtonMenuItemDeleteProduct == null) {
            jRadioButtonMenuItemDeleteProduct = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteProduct.setText("Verwijderen");
            jRadioButtonMenuItemDeleteProduct.setOpaque(false);
            jRadioButtonMenuItemDeleteProduct
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent e) {
                            // number
                            final String prodID = (String) jTableProductsModel
                                    .getValueAt(row, 0);// product
                            if (!(QuoteDetailController
                                    .getQuotesByProductId(new String[]{
                                            prodID, Constants.TRUE}))
                                    .isEmpty()) {

                                JOptionPane.showMessageDialog(frame, prodID
                                        + Constants.QUOTES_EXIST);
                            } else {
                                final int response = JOptionPaneItemRemove
                                        .confirm(prodID);
                                if (response == JOptionPane.YES_OPTION) {
                                    ProductController.removeProduct(prodID);
                                    refillJTableProduct();
                                }
                            }
                        }
                    });
        }
        return jRadioButtonMenuItemDeleteProduct;
    }

    private JLabel getJLabel1() {
        if (jLabel1 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("Klant Naam :");
        }
        return jLabel1;
    }

    private JFormattedTextField getJFormattedTextFieldQuotesPanelCustomerName() {
        if (jFormattedTextFieldQuotesPanelCustomerName == null) {
            jFormattedTextFieldQuotesPanelCustomerName = new JFormattedTextField();
            jFormattedTextFieldQuotesPanelCustomerName.setText(Constants.EMPTY);
        }
        return jFormattedTextFieldQuotesPanelCustomerName;
    }

    private JButton getJButtonQuotePanelQuoteSearch() {
        if (jButtonQuotePanelCustomerSearch == null) {
            jButtonQuotePanelCustomerSearch = new JButton();
            jButtonQuotePanelCustomerSearch.setText(Constants.SEARCH);
            jButtonQuotePanelCustomerSearch
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                refillJTableQuote(Constants.EMPTY);
                            }
                        }
                    });
        }
        return jButtonQuotePanelCustomerSearch;
    }

    private JButton getJButtonQuotePanelQuoteNew() {
        if (jButtonQuotePanelQuoteNew == null) {
            jButtonQuotePanelQuoteNew = new JButton();
            jButtonQuotePanelQuoteNew.setText("Nieuw");
            jButtonQuotePanelQuoteNew.setToolTipText("Nieuwe offerte");
            jButtonQuotePanelQuoteNew.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    {
                        new JWindowCustomer(frame, BusinessTypeEnum.QUOTE);
                        refillJTableQuote(Constants.EMPTY);
                    }
                }
            });
        }
        return jButtonQuotePanelQuoteNew;
    }

    private JButton getJButtonInvoicePanelInvoiceNew() {
        if (jButtonInvoicePanelInvoiceNew == null) {
            jButtonInvoicePanelInvoiceNew = new JButton();
            jButtonInvoicePanelInvoiceNew.setText("Nieuw");
            jButtonInvoicePanelInvoiceNew.setToolTipText("Nieuwe factuur");
            jButtonInvoicePanelInvoiceNew
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                new JWindowCustomer(frame,
                                        BusinessTypeEnum.INVOICE);
                                refillJTableInvoice(Constants.EMPTY);
                            }
                        }
                    });
        }
        return jButtonInvoicePanelInvoiceNew;
    }

    private JButton getJButtonInvoicePanelCustomerSearch() {
        if (jButtonInvoicePanelCustomerSearch == null) {
            jButtonInvoicePanelCustomerSearch = new JButton();
            jButtonInvoicePanelCustomerSearch.setText(Constants.SEARCH);
            jButtonInvoicePanelCustomerSearch
                    .addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            {
                                refillJTableInvoice(Constants.EMPTY);
                            }

                        }
                    });
        }
        return jButtonInvoicePanelCustomerSearch;
    }

    private JFormattedTextField getJFormattedTextFieldInvoicePanelCustomerName() {
        if (jFormattedTextFieldInvoicePanelCustomerName == null) {
            jFormattedTextFieldInvoicePanelCustomerName = new JFormattedTextField();
            jFormattedTextFieldInvoicePanelCustomerName
                    .setText(Constants.EMPTY);
        }
        return jFormattedTextFieldInvoicePanelCustomerName;
    }

    private JLabel getJLabel2() {
        if (jLabel2 == null) {
            jLabel2 = new JLabel();
            jLabel2.setText("Klant Naam :");
        }
        return jLabel2;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private JComboBox getJComboBoxQuoteStatus() {
        if (jComboBoxQuoteStatus == null) {
            quoteStats = CodeController.getCodeDetails(CodeEnum.QUOTE_STATUS
                    .getType());// Quote Status'
            jComboBoxQuoteStatusModel = new DefaultComboBoxModel(quoteStats
                    .keySet().toArray());
            jComboBoxQuoteStatus = new JComboBox();
            jComboBoxQuoteStatus.setModel(jComboBoxQuoteStatusModel);
        }
        return jComboBoxQuoteStatus;
    }

    private JLabel getJLabelQuoteStatus() {
        if (jLabelQuoteStatus == null) {
            jLabelQuoteStatus = new JLabel();
            jLabelQuoteStatus.setText("Status offerte :");
        }
        return jLabelQuoteStatus;
    }

    private JDateChooser getReqDlvDate() {
        if (reqDlvDate == null) {
            reqDlvDate = new JDateChooser();
        }
        return reqDlvDate;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private JComboBox getJComboBoxInvoiceStatus() {
        if (jComboBoxInvoiceStatus == null) {
            invoiceStats = CodeController
                    .getCodeDetails(CodeEnum.INVOICE_STATUS.getType());// Invoice
            // Status'

            jComboBoxInvoiceStatusModel = new DefaultComboBoxModel(invoiceStats
                    .keySet().toArray());
            jComboBoxInvoiceStatus = new JComboBox();
            jComboBoxInvoiceStatus.setModel(jComboBoxInvoiceStatusModel);
        }
        return jComboBoxInvoiceStatus;
    }

    private JComboBox getJComboBoxInvoiceType() {
        if (jComboBoxInvoiceType == null) {
            invoiceTypes = CodeController.getCodeDetails(CodeEnum.INVOICE_TYPE
                    .getType());// Invoice Type'

            jComboBoxInvoiceTypeModel = new DefaultComboBoxModel(invoiceTypes
                    .keySet().toArray());
            jComboBoxInvoiceType = new JComboBox();
            jComboBoxInvoiceType.setModel(jComboBoxInvoiceTypeModel);
        }
        return jComboBoxInvoiceType;
    }

    private TreeMap getNbrCategories() {
        if (nbrCategories == null) {
            nbrCategories = CodeController.getCodeDetails(CodeEnum.NUMBER
                    .getType());// Number
        }
        return nbrCategories;
    }

    private JLabel getJLabel4() {
        if (jLabel4 == null) {
            jLabel4 = new JLabel();
            jLabel4.setText("Status :");
        }
        return jLabel4;
    }

    public JSplitPane getJSplitPaneConfiguration() {
        if (jSplitPaneConfiguration == null) {
            jSplitPaneConfiguration = new JSplitPane();
            jSplitPaneConfiguration.setOrientation(JSplitPane.VERTICAL_SPLIT);
            jSplitPaneConfiguration.add(getJInternalFrameNumber(),
                    JSplitPane.RIGHT);
            jSplitPaneConfiguration.add(getJInternalFrameCode(),
                    JSplitPane.LEFT);
        }
        return jSplitPaneConfiguration;
    }

    public JInternalFrame getJInternalFrameCode() {
        if (jInternalFrameCode == null) {
            jInternalFrameCode = new JInternalFrame();
            final BorderLayout jInternalFrameCodeLayout = new BorderLayout();
            jInternalFrameCode.getContentPane().setLayout(
                    jInternalFrameCodeLayout);
            jInternalFrameCode.setVisible(true);
            jInternalFrameCode.setBounds(1, 1, 720, 272);
            jInternalFrameCode
                    .setPreferredSize(new java.awt.Dimension(720, 272));
            jInternalFrameCode.setTitle("Codes");
            jInternalFrameCode.setToolTipText("Wijzigen van selectiecodes");
            jInternalFrameCode.getContentPane().add(getJPanelNorthPanelCode(),
                    BorderLayout.NORTH);
            jInternalFrameCode.getContentPane().add(getJPanelCenterPanelCode(),
                    BorderLayout.CENTER);
            jInternalFrameCode.getContentPane().add(getJPanelSouthPanelCode(),
                    BorderLayout.SOUTH);

        }
        return jInternalFrameCode;
    }

    public JInternalFrame getJInternalFrameNumber() {
        if (jInternalFrameNumber == null) {
            jInternalFrameNumber = new JInternalFrame();
            final BorderLayout jInternalFrameNumberLayout = new BorderLayout();
            jInternalFrameNumber.getContentPane().setLayout(
                    jInternalFrameNumberLayout);
            jInternalFrameNumber.setVisible(true);
            jInternalFrameNumber.setBounds(1, 336, 720, 240);
            jInternalFrameNumber.setPreferredSize(new java.awt.Dimension(720,
                    240));
            jInternalFrameNumber.setTitle("Numbers");
            jInternalFrameNumber.setToolTipText("Instellen van nummers");
            jInternalFrameNumber.getContentPane().add(
                    getJPanelNorthPanelNumber(), BorderLayout.NORTH);
            jInternalFrameNumber.getContentPane().add(
                    getJPanelCenterPanelNumber(), BorderLayout.CENTER);
            jInternalFrameNumber.getContentPane().add(
                    getJPanelSouthPanelNumber(), BorderLayout.SOUTH);
        }
        return jInternalFrameNumber;
    }

    public JPanel getJPanelNorthPanelNumber() {
        if (jPanelNorthPanelNumber == null) {
            jPanelNorthPanelNumber = new JPanel();
            jPanelNorthPanelNumber.add(getJLabelNummerTitle());
        }
        return jPanelNorthPanelNumber;
    }

    public JPanel getJPanelCenterPanelNumber() {
        if (jPanelCenterPanelNumber == null) {
            jPanelCenterPanelNumber = new JPanel();
            final BorderLayout jPanelCenterPanelNumberLayout = new BorderLayout();
            jPanelCenterPanelNumber.setLayout(jPanelCenterPanelNumberLayout);
            jPanelCenterPanelNumber.setBorder(new SoftBevelBorder(
                    BevelBorder.LOWERED, null, null, null, null));
            jPanelCenterPanelNumber.add(getJScrollPaneNumber(),
                    BorderLayout.CENTER);
        }
        return jPanelCenterPanelNumber;
    }

    public JPanel getJPanelSouthPanelNumber() {
        if (jPanelSouthPanelNumber == null) {
            jPanelSouthPanelNumber = new JPanel();
            jPanelSouthPanelNumber.add(getJButtonNewNumber());
        }
        return jPanelSouthPanelNumber;
    }

    public JPanel getJPanelCenterPanelCode() {
        if (jPanelCenterPanelCode == null) {
            jPanelCenterPanelCode = new JPanel();
            final BorderLayout jPanelCenterPanelCodeLayout = new BorderLayout();
            jPanelCenterPanelCode.setLayout(jPanelCenterPanelCodeLayout);
            jPanelCenterPanelCode.setBorder(new SoftBevelBorder(
                    BevelBorder.LOWERED, null, null, null, null));
            final JPanel codeHeaderPanel = new JPanel();
            codeHeaderPanel.add(getJLabelCodeHeaderSelection());
            codeHeaderPanel.add(getJComboBoxCodeHeader());
            jPanelCenterPanelCode.add(codeHeaderPanel, BorderLayout.NORTH);
            jPanelCenterPanelCode.add(getJScrollPaneCodeDetail(),
                    BorderLayout.CENTER);
            codeHeaderPanel.setPreferredSize(new java.awt.Dimension(712, 40));
        }
        return jPanelCenterPanelCode;
    }

    private JPanel getJPanelNorthPanelCode() {
        if (jPanelNorthPanelCode == null) {
            jPanelNorthPanelCode = new JPanel();
            jPanelNorthPanelCode.add(getJLabelCodeTitle());
        }
        return jPanelNorthPanelCode;
    }

    private JPanel getJPanelSouthPanelCode() {
        if (jPanelSouthPanelCode == null) {
            jPanelSouthPanelCode = new JPanel();
            jPanelSouthPanelCode.add(getJButtonNewCode());
        }
        return jPanelSouthPanelCode;
    }

    public JButton getJButtonNewNumber() {
        if (jButtonNewNumber == null) {
            jButtonNewNumber = new JButton();
            jButtonNewNumber.setText("Nieuw");
            jButtonNewNumber.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    new JDialogNumber(frame, null, CRUDOperationEnum.NEW);
                    getJTableNumberDetails().setModel(
                            getJTableNumberDetailModel());
                }
            });

        }
        return jButtonNewNumber;
    }

    public JButton getJButtonNewCode() {
        if (jButtonNewCode == null) {
            jButtonNewCode = new JButton();
            jButtonNewCode.setText("Nieuw");
            jButtonNewCode.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    {
                        String codeID = getJComboBoxCodeHeader()
                                .getSelectedItem().toString();
                        codeID = codeID.substring(0, 3);
                        new JDialogCode(frame, codeID, null,
                                CRUDOperationEnum.NEW);
                        getJTableCodeDetails().setModel(
                                getJTableCodeDetailsModel(codeID));
                    }
                }
            });

        }
        return jButtonNewCode;
    }

    public JLabel getJLabelCodeTitle() {
        if (jLabelCodeTitle == null) {
            jLabelCodeTitle = new JLabel();
            jLabelCodeTitle.setText("Beheer van codes");
            jLabelCodeTitle.setFont(new java.awt.Font("Bell MT", 1, 18));
        }
        return jLabelCodeTitle;
    }

    private JLabel getJLabelNummerTitle() {
        if (jLabel5 == null) {
            jLabel5 = new JLabel();
            jLabel5.setFont(new java.awt.Font("Bell MT", 1, 18));
            jLabel5.setText("Beheer van nummers");
            jLabel5.setPreferredSize(new java.awt.Dimension(180, 21));
        }
        return jLabel5;
    }

    public JComboBox getJComboBoxCodeHeader() {
        if (jComboBoxCodeHeader == null) {
            final Collection<Business> list = CodeController
                    .getAllCodeHeaders();
            // CodeHeaders
            final Iterator<Business> it = list.iterator();
            final CodeHeader codes[] = new CodeHeader[list.size()];
            int i = 0;
            while (it.hasNext()) {
                codes[i] = (CodeHeader) it.next();
                i++;
            }
            jComboBoxCodeHeaderModel = new DefaultComboBoxModel(list.toArray());
            jComboBoxCodeHeader = new JComboBox();
            jComboBoxCodeHeader.setModel(jComboBoxCodeHeaderModel);
            jComboBoxCodeHeader
                    .setPreferredSize(new java.awt.Dimension(268, 24));
        }
        return jComboBoxCodeHeader;
    }

    public JTable getJTableCodeDetails() {
        if (jTableCodeDetails == null) {
            jTableCodeDetails = new JTable();
            jTableCodeDetails.setModel(new DefaultTableModel());
            getJTableCodeDetails().getTableHeader().setFont(
                    new java.awt.Font("Dialog", 1, 12));

        }
        return jTableCodeDetails;
    }

    private TableModel getJTableCodeDetailsModel(final String id) {
        jTableCodeDetailsModel = new DefaultTableModel(
                getCodeDetailColumns(id), getCodeDetailTitles());
        return jTableCodeDetailsModel;
    }

    /**
     * @return
     */
    private String[] getCodeDetailTitles() {
        return new String[]{"Id", "Code", "Omschrijving"};
    }

    /**
     * @return
     */
    private String[][] getCodeDetailColumns(final String id) {
        final int[] columnWidth = new int[getInvoiceColumnTitles().length];

        String[][] columns;
        CodeDetail detail;
        final Collection<Business> list = CodeController.getAllCodeDetails(id);
        columns = new String[list.size()][];
        final Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            detail = (CodeDetail) it.next();
            columns[i] = new String[]{detail.getIdCode(),
                    detail.getCodeDet(), detail.getCodeDesc()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;
        }
        return columns;
    }

    /**
     * @return
     */
    private String[] getNumberHeaderTitles() {
        return new String[]{"Categorie", "Vanaf", "Start nummer",
                "Laatste nummer"};
    }

    /**
     * @return
     */
    private String[][] getNumberHeaderColumns() {
        final int[] columnWidth = new int[getInvoiceColumnTitles().length];

        String[][] columns;
        model.Number number;
        final Collection<Business> list = NumberController.getAllNumbers();
        columns = new String[list.size()][];
        final Iterator<Business> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            number = (model.Number) it.next();
            columns[i] = new String[]{
                    CodeController.getOneCodeDetail(CodeEnum.NUMBER.getType(),
                            number.getNbrCategory().trim()).getCodeDesc(),
                    number.getNbrYear().toString(),
                    number.getNbrStrValue().trim(),
                    number.getNbrLstValue().trim()};
            calculateColumnWidth(columns[i], columnWidth);
            i++;
        }
        return columns;
    }

    public JLabel getJLabelCodeHeaderSelection() {
        if (jLabelCodeHeaderSelection == null) {
            jLabelCodeHeaderSelection = new JLabel();
            jLabelCodeHeaderSelection.setText("Selecteer Code :");
        }
        return jLabelCodeHeaderSelection;
    }

    public JTable getJTableNumberDetails() {
        if (jTableNumberDetails == null) {
            jTableNumberDetails = new JTable();
            jTableNumberDetails.setModel(getJTableNumberDetailModel());
            getJTableNumberDetails().getTableHeader().setFont(
                    new java.awt.Font("Dialog", 1, 12));
        }
        return jTableNumberDetails;
    }

    private TableModel getJTableNumberDetailModel() {
        jTableNumberDetailsModel = new DefaultTableModel(
                getNumberHeaderColumns(), getNumberHeaderTitles());

        return jTableNumberDetailsModel;

    }

    private JScrollPane getJScrollPaneCodeDetail() {
        if (jScrollPaneCodeDetail == null) {
            jScrollPaneCodeDetail = new JScrollPane();
            jScrollPaneCodeDetail.setPreferredSize(new java.awt.Dimension(712,
                    142));
            jScrollPaneCodeDetail.setViewportView(getJTableCodeDetails());
        }
        return jScrollPaneCodeDetail;
    }

    private JScrollPane getJScrollPaneNumber() {
        if (jScrollPaneNumber == null) {
            jScrollPaneNumber = new JScrollPane();
            jScrollPaneNumber
                    .setPreferredSize(new java.awt.Dimension(712, 275));
            jScrollPaneNumber.setViewportView(getJTableNumberDetails());

        }
        return jScrollPaneNumber;
    }

    private JLabel getJLabel6() {
        if (jLabel6 == null) {
            jLabel6 = new JLabel();
            jLabel6.setText("Type :");
        }
        return jLabel6;
    }

    public void setjTextTotal(JLabel jLabelTotalAmount) {
        this.jLabelTotalAmount = jLabelTotalAmount;
        jLabelTotalAmount.setToolTipText("Total Amount");
        jLabelTotalAmount.setFont(new java.awt.Font("Bitstream Charter", 1, 12));
    }

    public JLabel getJLabelTotalAmount() {
        if (jLabelTotalAmount == null) {
            jLabelTotalAmount = new JLabel();
            jLabelTotalAmount.setText("€0.0");
            jLabelTotalAmount.setHorizontalAlignment(JLabel.RIGHT);
        }
        return jLabelTotalAmount;
    }

    private JLabel getJLabelTotaal() {
        if (jLabelTotaal == null) {
            jLabelTotaal = new JLabel();
            jLabelTotaal.setText("Totaal:");
        }
        return jLabelTotaal;
    }
}
