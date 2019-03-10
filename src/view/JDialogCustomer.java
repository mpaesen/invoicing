/**
 * 
 */
package view;

import info.clearthought.layout.TableLayout;
import model.Address;
import model.Business;
import model.Contact;
import model.Customer;
import org.apache.log4j.lf5.LogLevel;
import persistency.controller.*;
import persistency.logging.BaseLogger;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.Constants;
import utilities.NumberEnum;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import static eu.europa.test.CheckTaxNumber.isValidVat;
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
 * @author Mathy
 *
 */
public class JDialogCustomer extends JDialog {

    private final JFrame parent;
    private final CRUDOperationEnum operation;
    private JScrollPane jScrollPaneAddresses;
    private JScrollPane jScrollPaneContacts;
    private JLabel jLabelWebsite;
    private JLabel jLabelName;
    private JLabel jLabelVat;
    private JLabel jLabelPhone;
    private JLabel jLabelMobile;
    private JLabel jLabelFax;
    private JLabel jLabelemail;
    private JLabel jLabelType;
    private JLabel jLabelClass;
    private JLabel jLabelLang;
    private JLabel jLabelCur;
    private JLabel jLabelPayment;
    private JTextArea message;
    private JTextField jTextFieldCusName;
    private JTextField jTextFieldCusVat;
    private JTextField jTextFieldCusPhone;
    private JTextField jTextFieldCusMobile;
    private JTextField jTextFieldCusInfo;
    private JLabel jLabel3;
    private JTextField jTextFieldCusAccount;
    private JTextField jTextFieldCusActivity;
    private JLabel jLabel2;
    private JLabel jLabel1;
    private JTextField jTextFieldCusWebsite;
    private JTextField jTextFieldCusFax;
    private JTextField jTextFieldCusemail;
    private JComboBox jComboBoxCusCur;
    private JComboBox jComboBoxCusLang;
    private JComboBox jComboBoxCusCat;
    private JComboBox jComboBoxCusType;
    private JComboBox jComboBoxCusPay;
    private ComboBoxModel jComboBoxCusTypeModel;
    private ComboBoxModel jComboBoxCusCatModel;
    private ComboBoxModel jComboBoxCusLangModel;
    private ComboBoxModel jComboBoxCusCurModel;
    private ComboBoxModel jComboBoxCusPayModel;
    private JButton jButtonCustomerWijzig;
    private JButton jButtonAddressNew;
    private JButton jButtonContactNew;
    private JPopupMenu jPopupMenuContacts;
    private JPopupMenu jPopupMenuAddresses;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteContact;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateContact;
    private JRadioButtonMenuItem jRadioButtonMenuItemDeleteAddress;
    private JRadioButtonMenuItem jRadioButtonMenuItemUpdateAddress;
    private JTable jTableContacts;
    private JTable jTableAddresses;
    private TableModel jTableContactsModel;
    private TableModel jTableAddressModel;
    private JSplitPane jSplitPaneCustomer;
    private JSplitPane jSplitPaneContacts;
    private JPanel jPanelCustomerDetails;
    private JPanel jPanelCustomerContacts;
    private JPanel jPanelAddresses;
    private JInternalFrame jInternalFrameCustomerDetails;
    private JInternalFrame jInternalFrameCustomerContacts;
    private JInternalFrame jInternalFrameCustomerAddresses;
    private Customer customer;
    private TreeMap<String, String> cusTypes, cusCats, cusLangs, cusCurs, cusPays;
    private int row;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }
    }

    public JDialogCustomer(final JFrame parent, final Customer customer, final CRUDOperationEnum operation) {
        super(parent, true);
        this.customer = customer;
        this.parent = parent;
        this.operation = operation;
        initGUI();

    }

    private Customer getCustomer() {
        return customer;
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.setTitle("Klant");
            final JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            getContentPane().add(topPanel, BorderLayout.NORTH);
            topPanel.setPreferredSize(new java.awt.Dimension(842, 732));

            // create the internal frames
            createCustomerInternalFrame();
            createContactsInternalFrame();
            createAddressesInternalFrame();

            // build Contacts SplitPane
            {
                jSplitPaneContacts = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jInternalFrameCustomerDetails, jInternalFrameCustomerContacts);
                jSplitPaneContacts.setPreferredSize(new java.awt.Dimension(840, 412));
            }

            // build General SplitPane (including contacts & addresses)
            {
                jSplitPaneCustomer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jSplitPaneContacts, jInternalFrameCustomerAddresses);
                topPanel.add(jSplitPaneCustomer, BorderLayout.CENTER);
                jSplitPaneCustomer.setPreferredSize(new java.awt.Dimension(842, 675));
            }

            {
                final JPanel buttonPanel = new JPanel();

                {
                    jButtonCustomerWijzig = new JButton();
                    buttonPanel.add(jButtonCustomerWijzig);
                    jButtonCustomerWijzig.setText("OK");
                    jButtonCustomerWijzig.setPreferredSize(new java.awt.Dimension(59, 32));
                    jButtonCustomerWijzig.setFocusable(false);
                    jButtonCustomerWijzig.setToolTipText("Gegevens bewaren");
                    jButtonCustomerWijzig.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {
                            final StringBuilder errorMessages = new StringBuilder();
                            if (validateInput(errorMessages)) {
                                saveInput();
                                enableAddressButton();
                                enableContactButton();
                            }
                            validateAddress(errorMessages);
                            validateContact(errorMessages);
                        }
                    });
                }
                topPanel.add(buttonPanel, BorderLayout.SOUTH);
                buttonPanel.setPreferredSize(new java.awt.Dimension(842, 77));
                {
                    message = new JTextArea();
                    resetMessage();
                    buttonPanel.add(message);
                    message.setFont(new java.awt.Font("Calibri", 0, 12));
                    message.setEditable(false);
                    message.setPreferredSize(new java.awt.Dimension(983, 21));
                }
            }

            pack();
            this.setSize(1539, 756);
            this.setLocationRelativeTo(parent);
            this.setModal(true);
            this.setVisible(true);
        } catch (final Exception e) {
            BaseLogger.getLogger().logMsg(e.getMessage());

        }
    }

    /**
     * @param errorMessages
     */
    private boolean validateInput(final StringBuilder errorMessages) {
        boolean success = true;
        resetMessage();
        validateCustomer(errorMessages);
        if (!errorMessages.toString().equals("")) {
            redMessage(errorMessages);
            success = false;
        }
        return success;
    }

    synchronized private void saveInput() {
        if ((customer == null) && (operation == CRUDOperationEnum.NEW)) {
            final Customer newCustomer = new Customer(NumberController.readLastNumber(NumberEnum.CUSTOMER.getType(), 0).toString(), jTextFieldCusName.getText(), jTextFieldCusVat.getText(), jTextFieldCusPhone.getText(), jTextFieldCusMobile.getText(), jTextFieldCusFax.getText(),
                    jTextFieldCusemail.getText(), jTextFieldCusWebsite.getText(), cusTypes.get(jComboBoxCusType.getSelectedItem().toString()), cusCats.get(jComboBoxCusCat.getSelectedItem().toString()), cusLangs.get(jComboBoxCusLang.getSelectedItem().toString()), cusCurs.get(jComboBoxCusCur
                    .getSelectedItem().toString()), cusPays.get(jComboBoxCusPay.getSelectedItem().toString()), jTextFieldCusActivity.getText(), jTextFieldCusAccount.getText(), jTextFieldCusInfo.getText(), true);
            CustomerController.createCustomer(newCustomer);
            customer = new Customer(newCustomer);
            greenMessage(newCustomer.getCusName() + " werd Toegevoegd");

        }
        if (operation == CRUDOperationEnum.UPDATE) {
            final Customer newCustomer = new Customer(getCustomer().getIdCus(), jTextFieldCusName.getText(), jTextFieldCusVat.getText(), jTextFieldCusPhone.getText(), jTextFieldCusMobile.getText(), jTextFieldCusFax.getText(), jTextFieldCusemail.getText(), jTextFieldCusWebsite.getText(),
                    cusTypes.get(jComboBoxCusType.getSelectedItem().toString()), cusCats.get(jComboBoxCusCat.getSelectedItem().toString()), cusLangs.get(jComboBoxCusLang.getSelectedItem().toString()), cusCurs.get(jComboBoxCusCur.getSelectedItem().toString()), cusPays.get(jComboBoxCusPay
                    .getSelectedItem().toString()), jTextFieldCusActivity.getText(), jTextFieldCusAccount.getText(), jTextFieldCusInfo.getText(), true);

            if (!customer.equals(newCustomer)) {
                CustomerController.updateCustomer(newCustomer);
                customer = new Customer(newCustomer);
                greenMessage(newCustomer.getCusName() + " werd gewijzigd");
            }
        }
    }

    /**
     * shows a message when succeeded.
     *
     * @param announcement
     */
    private void greenMessage(final String announcement) {
        message.setText(announcement);
        message.setBackground(new java.awt.Color(0, 255, 0));
    }

    /**
     * @param errorMessages
     */
    private void redMessage(final StringBuilder errorMessages) {
        message.setText(errorMessages.toString());
        message.setBackground(new java.awt.Color(255, 0, 0));
    }

    private void resetMessage() {
        message.setText("");
        message.setBackground(new java.awt.Color(238, 238, 238));
    }

    /**
     * Validate main customer panel
     *
     * @param errorMessages
     */
    private void validateCustomer(final StringBuilder errorMessages) {
        errorMessages.setLength(0);
        if (jTextFieldCusName.getText().equals("")) {
            errorMessages.append(Constants.NAME_CAN_NOT_BE_EMPTY + "\n");
        }
        if (((jComboBoxCusType.getSelectedItem().equals("Klant")) || (jComboBoxCusType.getSelectedItem().equals("Leverancier"))) && jTextFieldCusVat.getText().equals("")) {
            errorMessages.append(Constants.VAT_CAN_NOT_BE_EMPTY + "\n");
        }
        if (!jTextFieldCusVat.getText().equals("")) {
            final String strVat = jTextFieldCusVat.getText().substring(2);
            final String strCountry = jTextFieldCusVat.getText().substring(0, 2);
            try {
                if (!isValidVat(strCountry, strVat)) {
                    errorMessages.append(Constants.VAT_NOT_CORRECT + "\n");
                }
            } catch (final Exception e) {
                BaseLogger.logMsg(e.getMessage(), LogLevel.DEBUG);
                errorMessages.append(e.getMessage());
                errorMessages.append(":\t");
                errorMessages.append(Constants.VAT_NOT_CORRECT + "\n");
            }
        }
        if (jTextFieldCusemail.getText().equals("")) {
            // errorMessages.append(EMAIL_NOT_CORRECT+"\n");
        }
        if (jTextFieldCusMobile.getText().equals("")) {
            // errorMessages.append(MOBILE_NOT_CORRECT+"\n");
        }
        if (jTextFieldCusPhone.getText().equals("")) {
            // errorMessages.append(PHONE_NOT_CORRECT+"\n");
        }

        if (jComboBoxCusType.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_TYPE + "\n");
        }

        if (jComboBoxCusCat.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_CATEGORY + "\n");
        }

        if (jComboBoxCusLang.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_LANGUAGE + "\n");
        }

        if (jComboBoxCusCur.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_CURRENCY + "\n");
        }
        if (jComboBoxCusPay.getSelectedItem() == null) {
            errorMessages.append(Constants.NO_PAYMENT + "\n");
        }

    }

    /**
     * Validate contact panel
     *
     * @param errorMessages
     */
    private void validateContact(final StringBuilder errorMessages) {
        if (jTableContacts.getRowCount() == 0) {
            errorMessages.append(Constants.NO_CONTACTS + "\n");
        }
    }

    /**
     * Validate address panel
     *
     * @param errorMessages
     */
    private void validateAddress(final StringBuilder errorMessages) {
        if (jTableAddresses.getRowCount() == 0) {
            errorMessages.append(Constants.NO_INVOICE_ADDRESS + "\n");
        }
    }

    /**
     * Builds the main customer Frame
     */
    private void createCustomerInternalFrame() {
        {
            jInternalFrameCustomerDetails = new JInternalFrame("Details");
            jInternalFrameCustomerDetails.setLayout(new BorderLayout());
            buildJPanelCustomerDetails(customer);
            jInternalFrameCustomerDetails.getContentPane().add(jPanelCustomerDetails, BorderLayout.WEST);
            jInternalFrameCustomerDetails.setVisible(true);
            jInternalFrameCustomerDetails.setBounds(1, 1, 497, 410);
            jInternalFrameCustomerDetails.setPreferredSize(new java.awt.Dimension(497, 410));
        }
    }

    /**
     * Build Customer details screen
     */
    private void buildJPanelCustomerDetails(final Customer customer) {
        jPanelCustomerDetails = new JPanel();
        // Tablelayout
        final TableLayout jPanelCustomerDetailsLayout = new TableLayout(new double[][]{{151.0, 225.0, TableLayout.FILL, TableLayout.FILL}, {20.0, 20.0, 20.0, 20.0, 20.0, 20.0, 20.0, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, 20.0, 20.0, 20.0, 20.0, 20.0}});

        jPanelCustomerDetailsLayout.setHGap(5);
        jPanelCustomerDetailsLayout.setVGap(5);
        jPanelCustomerDetails.setLayout(jPanelCustomerDetailsLayout);
        jPanelCustomerDetails.setPreferredSize(new java.awt.Dimension(485, 377));
        jPanelCustomerDetails.setOpaque(false);
        jPanelCustomerDetails.setDoubleBuffered(false);
        jPanelCustomerDetails.setAlignmentX(10.0f);
        jPanelCustomerDetails.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

        // LABELS
        {
            jLabelName = new JLabel();
            jPanelCustomerDetails.add(jLabelName, "0, 0");
            jLabelName.setText("Naam :");
            jLabelName.setAlignmentX(5.0f);
            jLabelName.setPreferredSize(new java.awt.Dimension(179, 20));
            // jLabelName.setPreferredSize(new java.awt.Dimension(204, 20));
        }
        {
            jLabelVat = new JLabel();
            jPanelCustomerDetails.add(jLabelVat, "0, 1");
            jLabelVat.setText("Ondernemersnummer :");
        }
        {
            jLabelPhone = new JLabel();
            jPanelCustomerDetails.add(jLabelPhone, "0, 2");
            jLabelPhone.setText("Telefoon :");
        }
        {
            jLabelMobile = new JLabel();
            jPanelCustomerDetails.add(jLabelMobile, "0, 3");
            jLabelMobile.setText("GSM");
        }
        {
            jLabelFax = new JLabel();
            jPanelCustomerDetails.add(jLabelFax, "0, 4");
            jLabelFax.setText("Fax :");
        }
        {
            jLabelemail = new JLabel();
            jPanelCustomerDetails.add(jLabelemail, "0, 5");
            jLabelemail.setText("email :");
        }
        {
            jLabelWebsite = new JLabel();
            jPanelCustomerDetails.add(jLabelWebsite, "0, 6");
            jLabelWebsite.setText("Website");
        }
        {
            jLabelType = new JLabel();
            jPanelCustomerDetails.add(jLabelType, "0, 10");
            jLabelType.setText("Type :");
        }
        {
            jLabelClass = new JLabel();
            jPanelCustomerDetails.add(jLabelClass, "0, 11");
            jLabelClass.setText("Categorie :");
        }
        {
            jLabelLang = new JLabel();
            jPanelCustomerDetails.add(jLabelLang, "0, 12");
            jLabelLang.setText("Taal :");
        }
        {
            jLabelCur = new JLabel();
            jPanelCustomerDetails.add(jLabelCur, "0, 13");
            jLabelCur.setText("Muntcode :");
        }

        // FIELDS
        {
            jTextFieldCusName = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusName, "1, 0, 3, 0");

        }
        {
            jTextFieldCusVat = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusVat, "1, 1");

        }
        {
            jTextFieldCusPhone = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusPhone, "1, 2");

        }
        {
            jTextFieldCusMobile = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusMobile, "1, 3");

        }
        {
            jTextFieldCusFax = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusFax, "1, 4");

        }
        {
            jTextFieldCusemail = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusemail, "1, 5, 2, 5");

        }
        {
            jTextFieldCusWebsite = new JTextField();
            jPanelCustomerDetails.add(jTextFieldCusWebsite, "1, 6, 2, 6");

        }
        {
            jComboBoxCusType = getJComboBoxCusType();
            jPanelCustomerDetails.add(jComboBoxCusType, "1, 10");
            jComboBoxCusType.setModel(jComboBoxCusTypeModel);
        }
        {

            jComboBoxCusCat = getJComboBoxCusCat();
            jPanelCustomerDetails.add(jComboBoxCusCat, "1, 11");
            jComboBoxCusCat.setModel(jComboBoxCusCatModel);
        }
        {
            jComboBoxCusLang = getJComboBoxCusLang();
            jPanelCustomerDetails.add(jComboBoxCusLang, "1, 12");
            jComboBoxCusLang.setModel(jComboBoxCusLangModel);
        }
        {
            jComboBoxCusCur = getJComboBoxCusCur();
            jPanelCustomerDetails.add(jComboBoxCusCur, "1, 13");
            jPanelCustomerDetails.add(getJLabelPayment(), "0, 14");
            jPanelCustomerDetails.add(getJComboBoxCusPay(), "1, 14");
            jPanelCustomerDetails.add(getJLabel1(), "0, 7");
            jPanelCustomerDetails.add(getJLabel2(), "0, 8");
            jPanelCustomerDetails.add(getJTextFieldCusActivity(), "1, 7, 3, 7");
            jPanelCustomerDetails.add(getJTextFieldCusAccount(), "1, 8");
            jPanelCustomerDetails.add(getJLabel3(), "0, 9");
            jPanelCustomerDetails.add(getJTextFieldCusInfo(), "1, 9");
            jComboBoxCusCur.setModel(jComboBoxCusCurModel);
        }

        resetFields();
        if (customer != null) {
            initializeFields();
        }

    }

    private JComboBox getJComboBoxCusType() {
        if (jComboBoxCusType == null) {
            cusTypes = CodeController.getCodeDetails(CodeEnum.CUSTOMER_TYPE.getType());// CustomerType
            jComboBoxCusTypeModel = new DefaultComboBoxModel(cusTypes.keySet().toArray());
            jComboBoxCusType = new JComboBox();
            jComboBoxCusType.setModel(jComboBoxCusTypeModel);
        }
        return jComboBoxCusType;
    }

    private JComboBox getJComboBoxCusCat() {
        if (jComboBoxCusCat == null) {
            cusCats = CodeController.getCodeDetails(CodeEnum.CUSTOMER_CATEGORY.getType());// CustomerCategory
            jComboBoxCusCatModel = new DefaultComboBoxModel(cusCats.keySet().toArray());
            jComboBoxCusCat = new JComboBox();
            jComboBoxCusCat.setModel(jComboBoxCusCatModel);
        }
        return jComboBoxCusCat;
    }

    private JComboBox getJComboBoxCusLang() {
        if (jComboBoxCusLang == null) {
            cusLangs = CodeController.getCodeDetails(CodeEnum.LANGUAGE.getType());// CustomerLanguage
            jComboBoxCusLangModel = new DefaultComboBoxModel(cusLangs.keySet().toArray());
            jComboBoxCusLang = new JComboBox();
            jComboBoxCusLang.setModel(jComboBoxCusLangModel);
        }
        return jComboBoxCusLang;
    }

    private JComboBox getJComboBoxCusCur() {
        if (jComboBoxCusCur == null) {
            cusCurs = CodeController.getCodeDetails(CodeEnum.CURRENCY.getType());// CustomerCurrency
            jComboBoxCusCurModel = new DefaultComboBoxModel(cusCurs.keySet().toArray());
            jComboBoxCusCur = new JComboBox();
            jComboBoxCusCur.setModel(jComboBoxCusCurModel);
        }
        return jComboBoxCusCur;
    }

    private JLabel getJLabelPayment() {
        if (jLabelPayment == null) {
            jLabelPayment = new JLabel();
            jLabelPayment.setText("Betaling :");
        }
        return jLabelPayment;
    }

    private JComboBox getJComboBoxCusPay() {
        if (jComboBoxCusPay == null) {
            cusPays = CodeController.getCodeDetails(CodeEnum.PAYMENT.getType());// PaymentConditions

            jComboBoxCusPayModel = new DefaultComboBoxModel(cusPays.keySet().toArray());
            jComboBoxCusPay = new JComboBox();
            jComboBoxCusPay.setModel(jComboBoxCusPayModel);
        }
        return jComboBoxCusPay;
    }

    private void resetFields() {
        jTextFieldCusWebsite.setText("");
        jTextFieldCusemail.setText("");
        jTextFieldCusFax.setText("");
        jTextFieldCusMobile.setText("");
        jTextFieldCusPhone.setText("");
        jTextFieldCusVat.setText("");
        jTextFieldCusName.setText("");
    }

    private void initializeFields() {
        jTextFieldCusWebsite.setText(customer.getCusWebsite());
        jTextFieldCusemail.setText(customer.getCusEMail());
        jTextFieldCusFax.setText(customer.getCusFax());
        jTextFieldCusMobile.setText(customer.getCusMobile());
        jTextFieldCusPhone.setText(customer.getCusPhone());
        jTextFieldCusVat.setText(customer.getCusVat());
        jTextFieldCusName.setText(customer.getCusName());
        jTextFieldCusActivity.setText(customer.getCusActivity());
        jTextFieldCusAccount.setText(customer.getCusAccount());
        jTextFieldCusInfo.setText(customer.getCusInfo());

        jComboBoxCusType.setSelectedItem(getSelectedItem(cusTypes, customer.getCusType()));
        jComboBoxCusCat.setSelectedItem(getSelectedItem(cusCats, customer.getCusClass()));
        jComboBoxCusLang.setSelectedItem(getSelectedItem(cusLangs, customer.getCusLang()));
        jComboBoxCusCur.setSelectedItem(getSelectedItem(cusCurs, customer.getCusCur()));
        jComboBoxCusPay.setSelectedItem(getSelectedItem(cusPays, customer.getCusPay()));
    }

    /**
     * Builds the bottom Addresses Frame
     */
    private void createAddressesInternalFrame() {
        {
            jPanelAddresses = new JPanel();
            jPanelAddresses.setLayout(new BorderLayout());
            {
                jScrollPaneAddresses = new JScrollPane();
                {
                    refillAddressView();
                }
                jPanelAddresses.add(jScrollPaneAddresses, BorderLayout.CENTER);
                jScrollPaneAddresses.setPreferredSize(new java.awt.Dimension(830, 226));
                jScrollPaneAddresses.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
                jScrollPaneAddresses.setAutoscrolls(true);
            }
            {
                final JPanel buttonPanel = new JPanel();
                jPanelAddresses.add(buttonPanel, BorderLayout.SOUTH);
                buttonPanel.setPreferredSize(new java.awt.Dimension(830, 35));
                {
                    jButtonAddressNew = new JButton();
                    buttonPanel.add(jButtonAddressNew);
                    jButtonAddressNew.setText("Nieuw");
                    jButtonAddressNew.setToolTipText("Nieuw adres");
                    enableAddressButton();
                    jButtonAddressNew.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {

                            new JDialogAddress(customer, null, CRUDOperationEnum.NEW);
                            refillAddressView();
                        }
                    });
                }
            }

            jInternalFrameCustomerAddresses = new JInternalFrame("Adressen");
            jInternalFrameCustomerAddresses.add(jPanelAddresses);
            jPanelAddresses.setPreferredSize(new java.awt.Dimension(830, 236));
            jInternalFrameCustomerAddresses.setVisible(true);
            jInternalFrameCustomerAddresses.setBounds(1, 427, 840, 227);
            jInternalFrameCustomerAddresses.setPreferredSize(new java.awt.Dimension(840, 227));
        }

    }

    /**
     * New Address button can only be enabled if the customer exists
     */
    private void enableAddressButton() {
        jButtonAddressNew.setEnabled(true);
        if (customer == null) {
            jButtonAddressNew.setEnabled(false);
        }

    }

    /**
     * New Address button can only be enabled if the customer exists
     */
    private void enableContactButton() {
        jButtonContactNew.setEnabled(true);
        if (customer == null) {
            jButtonContactNew.setEnabled(false);
        }

    }

    /**
     *
     */
    private void refillAddressView() {
        final int DEFAULT_PIXELS = 150;
        final int[] columnWidth = new int[getAddressTitles().length];
        jTableAddressModel = new DefaultTableModel(getAddressColumns(columnWidth), getAddressTitles());
        jTableAddresses = new JTable();
        jTableAddresses.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPaneAddresses.setViewportView(jTableAddresses);
        jTableAddresses.setModel(jTableAddressModel);
        for (int i = 0; i < getAddressTitles().length; i++) {
            final TableColumn column = jTableAddresses.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
        }
        jTableAddresses.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        jTableAddresses.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 12));
        setComponentPopupMenu(jTableAddresses, getJPopupMenuAddresses());
    }

    /**
     * @return
     */
    private String[] getAddressTitles() {
        return new String[]{"id", "Type", "Straat", "Nummer", "Bus", "Postcode", "Gemeente", "Land"};
    }

    private JPopupMenu getJPopupMenuAddresses() {
        if (jPopupMenuAddresses == null) {
            jPopupMenuAddresses = new JPopupMenu();
            jPopupMenuAddresses.setBounds(219, 128, 76, 19);
            final ButtonGroup group = new ButtonGroup();
            group.add(getJRadioButtonMenuItemUpdateAddress());
            group.add(getJRadioButtonMenuItemDeleteAddress());
            getJRadioButtonMenuItemUpdateAddress().setSelected(true);
            jPopupMenuAddresses.add(getJRadioButtonMenuItemUpdateAddress());
            jPopupMenuAddresses.add(getJRadioButtonMenuItemDeleteAddress());
        }
        return jPopupMenuAddresses;
    }

    /**
     * @return
     */
    private String[][] getAddressColumns(final int[] columnWidth) {

        String[][] columns;
        if (customer != null) {
            Address address;
            final String[] filter = getAddressFilter();
            final Collection<Business> list = AddressController.getAddresses(filter);
            columns = new String[list.size()][];
            final Iterator<Business> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                address = (Address) it.next();
                columns[i] = new String[]{address.getIdAddress(), CodeController.getOneCodeDetail(CodeEnum.ADDRESS_TYPE.getType(), address.getAddType()).getCodeDesc(), address.getAddStreet(), address.getAddNumber(), address.getAddBox(), address.getAddZip(), address.getAddCity(),
                        CodeController.getOneCodeDetail(CodeEnum.COUNTRY.getType(), address.getAddCountry()).getCodeDesc()};
                calculateColumnWidth(columns[i], columnWidth);

                i++;
            }
        } else {
            columns = null;
        }
        return columns;
    }

    private void calculateColumnWidth(final String[] row, final int[] columnWidth) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].length() > columnWidth[i]) {
                columnWidth[i] = row[i].length();
            }
        }
    }

    private String[] getAddressFilter() {

        final String id = customer.getIdCus();
        final String[] filter = {id, "", "true"};
        return filter;
    }

    /**
     * Builds the right contacts frame
     */
    private void createContactsInternalFrame() {
        {
            jPanelCustomerContacts = new JPanel();
            jPanelCustomerContacts.setLayout(new BorderLayout());
            {
                jScrollPaneContacts = new JScrollPane();
                {
                    refillContactsView();
                }
                jPanelCustomerContacts.add(jScrollPaneContacts, BorderLayout.CENTER);
                jScrollPaneContacts.setPreferredSize(new java.awt.Dimension(415, 314));
                jScrollPaneContacts.getHorizontalScrollBar().setAutoscrolls(true);
                jScrollPaneContacts.getVerticalScrollBar().setAutoscrolls(true);
                jScrollPaneContacts.setAutoscrolls(true);
                jScrollPaneContacts.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
            }
            {
                final JPanel buttonPanel = new JPanel();
                jPanelCustomerContacts.add(buttonPanel, BorderLayout.SOUTH);
                {
                    jButtonContactNew = new JButton("Nieuw");
                    buttonPanel.add(jButtonContactNew);
                    jButtonContactNew.setToolTipText("Nieuw contact");
                    enableContactButton();
                    jButtonContactNew.addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent evt) {

                            new JDialogContact(customer, null, CRUDOperationEnum.NEW);
                            refillContactsView();
                        }
                    });
                }
            }

            jInternalFrameCustomerContacts = new JInternalFrame("Contacten");
            jInternalFrameCustomerContacts.add(jPanelCustomerContacts);
            jInternalFrameCustomerContacts.setVisible(true);
            jInternalFrameCustomerContacts.setBounds(388, 1, 443, 339);
            jInternalFrameCustomerContacts.setPreferredSize(new java.awt.Dimension(443, 339));
        }
    }

    /**
     *
     */
    private void refillContactsView() {
        final int DEFAULT_PIXELS = 20;
        final int[] columnWidth = new int[getContactTitles().length];

        jTableContactsModel = new DefaultTableModel(getContactColumns(columnWidth), getContactTitles());
        jTableContacts = new JTable();
        jScrollPaneContacts.setViewportView(jTableContacts);
        jTableContacts.setModel(jTableContactsModel);
        for (int i = 0; i < getContactTitles().length; i++) {
            final TableColumn column = jTableContacts.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
        }
        jTableContacts.getTableHeader().setFont(new java.awt.Font("Dialog", 1, 12));
        setComponentPopupMenu(jTableContacts, getJPopupMenuContacts());
    }

    /**
     * @return
     */
    private String[] getContactTitles() {
        return new String[]{"id", "Prefix", "Naam", "Voornaam", "GSM", "eMail", "Telefoon"};
    }

    /**
     * @return
     */
    private String[][] getContactColumns(final int[] columnWidth) {

        String[][] columns;
        if (customer != null) {
            Contact contact;
            final String[] filter = getContactFilter();
            final Collection<Business> list = ContactController.getContacts(filter);
            columns = new String[list.size()][];
            final Iterator<Business> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                contact = (Contact) it.next();
                columns[i] = new String[]{contact.getIdContact(), contact.getConPref(), contact.getConLName(), contact.getConFName(), contact.getConMobile(), contact.getConEMail(), contact.getConPhone()};
                calculateColumnWidth(columns[i], columnWidth);
                i++;
            }
        } else {
            columns = null;
        }
        return columns;
    }

    private String[] getContactFilter() {

        final String id = customer.getIdCus();
        final String[] filter = {id, "true"};
        return filter;
    }

    private JPopupMenu getJPopupMenuContacts() {
        if (jPopupMenuContacts == null) {
            jPopupMenuContacts = new JPopupMenu();
            jPopupMenuContacts.setBounds(219, 128, 76, 19);
            final ButtonGroup group = new ButtonGroup();
            group.add(getJRadioButtonMenuItemUpdateContact());
            group.add(getJRadioButtonMenuItemDeleteContact());
            getJRadioButtonMenuItemUpdateContact().setSelected(true);
            jPopupMenuContacts.add(getJRadioButtonMenuItemUpdateContact());
            jPopupMenuContacts.add(getJRadioButtonMenuItemDeleteContact());

        }
        return jPopupMenuContacts;
    }

    /**
     * Auto-generated method for setting the popup menu for a component
     */
    private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
        parent.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(final java.awt.event.MouseEvent e) {
                // if(e.isPopupTrigger())
                menu.show(parent, e.getX(), e.getY());
                final JTable table = (JTable) e.getSource();
                table.clearSelection();
                final Point p = e.getPoint();
                row = table.rowAtPoint(p);

            }

            @Override
            public void mouseReleased(final java.awt.event.MouseEvent e) {
                // if(e.isPopupTrigger())
                menu.show(parent, e.getX(), e.getY());
                final JTable table = (JTable) e.getSource();
                table.clearSelection();
                final Point p = e.getPoint();
                row = table.rowAtPoint(p);

            }
        });
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateContact() {
        if (jRadioButtonMenuItemUpdateContact == null) {
            jRadioButtonMenuItemUpdateContact = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateContact.setText("Details");
            jRadioButtonMenuItemUpdateContact.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {

                    final String id = (String) jTableContactsModel.getValueAt(row, 0);// contact
                    // number
                    final Contact contact = ContactController.getContact(id);
                    new JDialogContact(customer, contact, CRUDOperationEnum.UPDATE);
                    refillContactsView();
                }
            });

        }
        return jRadioButtonMenuItemUpdateContact;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateAddress() {
        if (jRadioButtonMenuItemUpdateAddress == null) {
            jRadioButtonMenuItemUpdateAddress = new JRadioButtonMenuItem();
            jRadioButtonMenuItemUpdateAddress.setText("Details");
            jRadioButtonMenuItemUpdateAddress.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {

                    final String id = (String) jTableAddressModel.getValueAt(row, 0);// product
                    // number
                    final Address address = AddressController.getAddress(id);
                    new JDialogAddress(customer, address, CRUDOperationEnum.UPDATE);
                    refillAddressView();

                }
            });

        }
        return jRadioButtonMenuItemUpdateAddress;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteAddress() {
        if (jRadioButtonMenuItemDeleteAddress == null) {
            jRadioButtonMenuItemDeleteAddress = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteAddress.setText("Verwijderen");
            jRadioButtonMenuItemDeleteAddress.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {

                    final String id = (String) jTableAddressModel.getValueAt(row, 0);// Address
                    final int response = JOptionPaneItemRemove.confirm(" adres " + id);
                    if (response == JOptionPane.YES_OPTION) {
                        AddressController.removeAddress(id);
                        refillAddressView();
                    }
                }
            });
        }
        return jRadioButtonMenuItemDeleteAddress;
    }

    private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteContact() {
        if (jRadioButtonMenuItemDeleteContact == null) {
            jRadioButtonMenuItemDeleteContact = new JRadioButtonMenuItem();
            jRadioButtonMenuItemDeleteContact.setText("Verwijderen");
            jRadioButtonMenuItemDeleteContact.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    final String id = (String) jTableContactsModel.getValueAt(row, 0);// contact
                    final int response = JOptionPaneItemRemove.confirm(" contact " + id);
                    if (response == JOptionPane.YES_OPTION) {
                        ContactController.removeContact(id);
                        refillContactsView();
                    }

                }
            });
        }
        return jRadioButtonMenuItemDeleteContact;
    }

    private JLabel getJLabel1() {
        if (jLabel1 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("Activiteit:");
        }
        return jLabel1;
    }

    private JLabel getJLabel2() {
        if (jLabel2 == null) {
            jLabel2 = new JLabel();
            jLabel2.setText("Rekening nummer:");
        }
        return jLabel2;
    }

    private JTextField getJTextFieldCusActivity() {
        if (jTextFieldCusActivity == null) {
            jTextFieldCusActivity = new JTextField();
        }
        return jTextFieldCusActivity;
    }

    private JTextField getJTextFieldCusAccount() {
        if (jTextFieldCusAccount == null) {
            jTextFieldCusAccount = new JTextField();
        }
        return jTextFieldCusAccount;
    }

    private JLabel getJLabel3() {
        if (jLabel3 == null) {
            jLabel3 = new JLabel();
            jLabel3.setText("Register:");
        }
        return jLabel3;
    }

    private JTextField getJTextFieldCusInfo() {
        if (jTextFieldCusInfo == null) {
            jTextFieldCusInfo = new JTextField();
        }
        return jTextFieldCusInfo;
    }

}
