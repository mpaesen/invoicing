/**
 * 
 */
package view;

import info.clearthought.layout.TableLayout;
import model.Contact;
import model.Customer;
import persistency.controller.CodeController;
import persistency.controller.ContactController;
import persistency.controller.NumberController;
import persistency.logging.Logger;
import utilities.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
/**
 * @author Mathy
 * 
 */
public class JDialogContact extends JDialog {

    private JButton jButtonSave;
    private JComboBox jComboBoxLanguage;
    private JTextField jTextFieldEmail;
    private JTextField jTextFieldMobile;
    private JTextField jTextFieldPhone;
    private JTextField jTextFieldFName;
    private JTextField jTextFieldLName;
    private JComboBox jComboBoxTitle;
    private JLabel jLabelLanguage;
    private JComboBox jComboBoxPrefix;
    private JLabel jLabelEmail;
    private JLabel jLabelPrefix;
    private JLabel jLabelMiobile;
    private JLabel jLabelPhone;
    private JLabel jLabelFName;
    private JLabel jLabelLName;
    private JTextArea message;
    private JLabel jLabelTitle;
    private JPanel jPanelContactFrame;
    private JPanel jPanelButtonFrame;
    private JLabel jLabelCustomer;
    private JPanel jPanelTitleFrame;
    private Contact contact;
    private Customer customer;
    private CRUDOperationEnum operation;
    private TreeMap<String, String> conTitles, conPrefixes, conLanguages;

    {
        // Set Look & Feel
        try {
            javax.swing.UIManager
                    .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            Logger.getLogger().logMsg(e.getMessage());
            //e.printStackTrace();
        }
    }

    public JDialogContact(Customer customer, Contact contact,
                          CRUDOperationEnum operation) {
        super();
        this.customer = customer;
        this.contact = contact;
        this.operation = operation;
        initGUI();
    }

    private CRUDOperationEnum getOperation() {
        return operation;
    }

    private void initGUI() {
        try {
            {
                setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                this.setBounds(0, 0, 496, 399);
                this.setPreferredSize(new java.awt.Dimension(496, 399));
            }
            {// title Frame
                this.setBounds(0, 0, 667, 311);
                BorderLayout thisLayout = new BorderLayout();
                this.setPreferredSize(new java.awt.Dimension(667, 311));
                getContentPane().setLayout(thisLayout);
                this.setTitle("Contact");
                {
                    jPanelTitleFrame = new JPanel();
                    FlowLayout jPanelTitleFrameLayout = new FlowLayout();
                    getContentPane().add(jPanelTitleFrame, BorderLayout.NORTH);
                    jPanelTitleFrame.setLayout(jPanelTitleFrameLayout);
                    jPanelTitleFrame.setPreferredSize(new java.awt.Dimension(
                            335, 24));

                    {
                        jLabelTitle = new JLabel(customer.getCusName());
                        jPanelTitleFrame.add(jLabelTitle);
                        jLabelTitle.setFont(new java.awt.Font("Bodoni MT", 1,
                                16));
                    }
                }
                {// Contact Frame
                    jPanelContactFrame = new JPanel();
                    TableLayout jPanelContactFrameLayout = new TableLayout(
                            new double[][]{
                                    {158.0, 192.0, 53.0, 187.0,
                                            TableLayout.FILL},
                                    {24.0, 24.0, 24.0, 24.0, 24.0, 24.0,
                                            TableLayout.FILL}});
                    jPanelContactFrameLayout.setHGap(5);
                    jPanelContactFrameLayout.setVGap(5);
                    jPanelContactFrame.setLayout(jPanelContactFrameLayout);
                    getContentPane().add(jPanelContactFrame,
                            BorderLayout.CENTER);
                    jPanelContactFrame.setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.LOWERED));
                    {
                        jLabelLName = new JLabel();
                        jPanelContactFrame.add(jLabelLName, "0, 1");
                        jLabelLName.setText("Naam :");
                    }
                    {
                        jLabelFName = new JLabel();
                        jPanelContactFrame.add(jLabelFName, "0, 2");
                        jLabelFName.setText("Voornaam :");
                    }
                    {
                        jLabelPhone = new JLabel();
                        jPanelContactFrame.add(jLabelPhone, "0, 3");
                        jLabelPhone.setText("Telefoon :");
                    }
                    {
                        jLabelMiobile = new JLabel();
                        jPanelContactFrame.add(jLabelMiobile, "0, 4");
                        jLabelMiobile.setText("GSM :");
                    }
                    {
                        jLabelPrefix = new JLabel();
                        jPanelContactFrame.add(jLabelPrefix, "0, 0");
                        jLabelPrefix.setText("Aanspreek :");
                    }
                    {
                        jLabelEmail = new JLabel();
                        jPanelContactFrame.add(jLabelEmail, "0, 5");
                        jLabelEmail.setText("Email :");
                    }
                    {
                        ComboBoxModel jComboBoxPrefixModel = new DefaultComboBoxModel();
                        jComboBoxPrefix = getJComboBoxPrefix(jComboBoxPrefixModel);
                        jPanelContactFrame.add(jComboBoxPrefix, "1, 0");
                    }
                    {
                        jLabelLanguage = new JLabel();
                        jPanelContactFrame.add(jLabelLanguage, "0, 6");
                        jLabelLanguage.setText("Taal :");
                    }
                    {
                        jLabelTitle = new JLabel();
                        jPanelContactFrame.add(jLabelTitle, "2, 0");
                        jLabelTitle.setText("Titel :");
                    }
                    {
                        ComboBoxModel jComboBoxTitleModel = new DefaultComboBoxModel();
                        jComboBoxTitle = getJComboBoxTitle(jComboBoxTitleModel);
                        jPanelContactFrame.add(jComboBoxTitle, "3, 0");
                    }
                    {
                        jTextFieldLName = new JTextField();
                        jPanelContactFrame.add(jTextFieldLName, "1, 1, 3, 1");
                    }
                    {
                        jTextFieldFName = new JTextField();
                        jPanelContactFrame.add(jTextFieldFName, "1, 2, 3, 2");
                    }
                    {
                        jTextFieldPhone = new JTextField();
                        jPanelContactFrame.add(jTextFieldPhone, "1, 3, 2, 3");
                    }
                    {
                        jTextFieldMobile = new JTextField();
                        jPanelContactFrame.add(jTextFieldMobile, "1, 4, 2, 4");
                    }
                    {
                        jTextFieldEmail = new JTextField();
                        jPanelContactFrame.add(jTextFieldEmail, "1, 5, 3, 5");
                    }
                    {
                        ComboBoxModel jComboBoxLanguageModel = new DefaultComboBoxModel();
                        jComboBoxLanguage = getJComboBoxLanguage(jComboBoxLanguageModel);
                        jPanelContactFrame.add(jComboBoxLanguage, "1, 6");
                    }
                }
                // initialize Contact fields
                initializeFields();
                {// Button Frame
                    jPanelButtonFrame = new JPanel();
                    getContentPane().add(jPanelButtonFrame, BorderLayout.SOUTH);
                    jPanelButtonFrame.setPreferredSize(new java.awt.Dimension(
                            335, 54));
                    {
                        message = new JTextArea();
                        jPanelButtonFrame.add(message);
                        //
                        resetMessage();
                        message.setPreferredSize(new java.awt.Dimension(324, 16));
                        message.setEditable(false);
                    }
                    {
                        jButtonSave = new JButton();
                        jPanelButtonFrame.add(jButtonSave);
                        jButtonSave.setText("OK");
                        jButtonSave.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                StringBuilder errorMessages = new StringBuilder(
                                );
                                if (validateInput(errorMessages)) {
                                    saveInput();
                                    // jButtonSave.setEnabled(false);
                                }
                            }

                            /**
                             *
                             */
                            private void greenMessage(String announcement) {
                                message.setText(announcement);
                                message.setBackground(new java.awt.Color(0,
                                        255, 0));
                            }

                            private boolean validateInput(
                                    StringBuilder errorMessages) {
                                boolean success = true;
                                validateContact(errorMessages);
                                if (!errorMessages.toString().equals("")) {
                                    redMessage(errorMessages);
                                    success = false;
                                }
                                return success;
                            }

                            /**
                             * @param errorMessages
                             */
                            private void redMessage(StringBuilder errorMessages) {
                                message.setText(errorMessages.toString());
                                message.setBackground(new java.awt.Color(255,
                                        0, 0));
                            }

                            synchronized private void saveInput() {
                                if ((operation == CRUDOperationEnum.NEW)
                                        && (contact == null)) {
                                    Contact newContact = new Contact(
                                            NumberController.readLastNumber(
                                                    NumberEnum.CONTACT
                                                            .getType(), 0)
                                                    .toString(), customer
                                            .getIdCus(),
                                            jTextFieldLName.getText(),
                                            jTextFieldFName.getText(),
                                            jTextFieldPhone.getText(),
                                            jTextFieldMobile.getText(),
                                            jTextFieldEmail.getText(),
                                            conTitles.get(jComboBoxTitle
                                                    .getSelectedItem()
                                                    .toString()), conPrefixes
                                            .get(jComboBoxPrefix
                                                    .getSelectedItem()
                                                    .toString()),
                                            conLanguages.get(jComboBoxLanguage
                                                    .getSelectedItem()
                                                    .toString()), true);
                                    if (contact == null) {
                                        ContactController
                                                .createContact(newContact);
                                        contact = new Contact(newContact);
                                        greenMessage("Contact "
                                                + contact.getConLName()
                                                + " werd toegevoegd!");
                                    }

                                } else {
                                    if (operation == CRUDOperationEnum.UPDATE) {
                                        Contact newContact = new Contact(
                                                contact.getIdContact(),
                                                customer.getIdCus(),
                                                jTextFieldLName.getText(),
                                                jTextFieldFName.getText(),
                                                jTextFieldPhone.getText(),
                                                jTextFieldMobile.getText(),
                                                jTextFieldEmail.getText(),
                                                conTitles.get(jComboBoxTitle
                                                        .getSelectedItem()
                                                        .toString()),
                                                conPrefixes.get(jComboBoxPrefix
                                                        .getSelectedItem()
                                                        .toString()),
                                                conLanguages
                                                        .get(jComboBoxLanguage
                                                                .getSelectedItem()
                                                                .toString()),
                                                true);
                                        if (!newContact.equals(contact)) {
                                            ContactController
                                                    .updateContact(newContact);

                                            contact = new Contact(newContact);
                                            greenMessage("Contact "
                                                    + contact.getConLName()
                                                    + " werd gewijzigd!");
                                        }
                                    }
                                }
                            }
                        });

                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger().logMsg(e.getMessage());
            //e.printStackTrace();
        }
        this.setModal(true);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    /**
     *
     */
    private void initializeFields() {
        if (contact != null) {
            jTextFieldLName.setText(contact.getConLName());
            jTextFieldFName.setText(contact.getConFName());
            jTextFieldMobile.setText(contact.getConMobile());
            jTextFieldPhone.setText(contact.getConPhone());
            jTextFieldEmail.setText(contact.getConEMail());
            jComboBoxTitle.setSelectedItem(ComboBoxHelper.getSelectedItem(
                    conTitles, contact.getConTitle()));
            jComboBoxPrefix.setSelectedItem(ComboBoxHelper.getSelectedItem(
                    conPrefixes, contact.getConPref()));
            jComboBoxLanguage.setSelectedItem(ComboBoxHelper.getSelectedItem(
                    conLanguages, contact.getConLang()));

        }
    }

    private void resetMessage() {
        message.setText("");
        message.setBackground(new java.awt.Color(238, 238, 238));
    }

    /**
     * Validate Contact panel
     *
     * @param errorMessages
     */
    private void validateContact(StringBuilder errorMessages) {
        resetMessage();
        if (jTextFieldLName.equals("")) {
            errorMessages.append(Constants.NO_NAME + "\n");
        }
        // if (jTextFieldPhone.equals("")) {
        // errorMessages.append(NO_PHONE + "\n");
        // }
    }

    /**
     * Build Prefix combobox
     *
     * @param jComboBoxPrefixModel
     * @return jComboBoxPrefix
     */
    private JComboBox getJComboBoxPrefix(ComboBoxModel jComboBoxPrefixModel) {
        if (jComboBoxPrefix == null) {
            conPrefixes = CodeController.getCodeDetails(CodeEnum.PREFIX
                    .getType());// Prefix
            jComboBoxPrefixModel = new DefaultComboBoxModel(conPrefixes
                    .keySet().toArray());
            jComboBoxPrefix = new JComboBox();
            jComboBoxPrefix.setModel(jComboBoxPrefixModel);
        }
        return jComboBoxPrefix;
    }

    /**
     * Build Title combobox
     *
     * @param jComboBoxTitleModel
     * @return jComboBoxTitle
     */
    private JComboBox getJComboBoxTitle(ComboBoxModel jComboBoxTitleModel) {
        if (jComboBoxTitle == null) {
            conTitles = CodeController.getCodeDetails(CodeEnum.TITLE.getType());// Titles
            jComboBoxTitleModel = new DefaultComboBoxModel(conTitles.keySet()
                    .toArray());
            jComboBoxTitle = new JComboBox();
            jComboBoxTitle.setModel(jComboBoxTitleModel);
        }
        return jComboBoxTitle;
    }

    /**
     * Build Language combobox
     *
     * @param jComboBoxLanguageModel
     * @return jComboBoxLanguage
     */
    private JComboBox getJComboBoxLanguage(ComboBoxModel jComboBoxLanguageModel) {
        if (jComboBoxLanguage == null) {
            conLanguages = CodeController.getCodeDetails(CodeEnum.LANGUAGE
                    .getType());// Languages
            jComboBoxLanguageModel = new DefaultComboBoxModel(conLanguages
                    .keySet().toArray());
            jComboBoxLanguage = new JComboBox();
            jComboBoxLanguage.setModel(jComboBoxLanguageModel);
        }
        return jComboBoxLanguage;
    }
}
