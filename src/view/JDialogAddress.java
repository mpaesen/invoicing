/**
 * 
 */
package view;

import info.clearthought.layout.TableLayout;
import model.Address;
import model.Customer;
import persistency.controller.AddressController;
import persistency.controller.CodeController;
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
public class JDialogAddress extends JDialog {

    private JPanel jPanelAddressFrame;
    private JPanel jPanelButtonFrame;
    private JTextField jTextFieldCity;
    private JTextField jTextFieldBox;
    private JTextField jTextFieldZip;
    private JTextField jTextFieldNumber;
    private JTextField jTextFieldStreet;
    private JComboBox jComboBoxCountry;
    private JComboBox jComboBoxAddressType;
    private JTextArea message;
    private JLabel jLabelBox;
    private JLabel jLabelCountry;
    private JLabel jLabelCity;
    private JLabel jLabelZip;
    private JLabel jLabelNumber;
    private JLabel jLabelType;
    private JLabel jLabelStreet;
    private JLabel jLabelCustomer;
    private JPanel jPanelTitleFrame;
    private JButton jButtonSave;
    private Address address;
    private Customer customer;
    private CRUDOperationEnum operation;
    private TreeMap<String, String> addTypes, addCountries;

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

    public JDialogAddress(Customer customer, Address address,
                          CRUDOperationEnum operation) {
        super();
        this.customer = customer;
        this.address = address;
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
            {// Title Frame
                this.setBounds(0, 0, 586, 275);
                BorderLayout thisLayout = new BorderLayout();
                this.setPreferredSize(new java.awt.Dimension(586, 275));
                getContentPane().setLayout(thisLayout);
                this.setTitle("Adres");
                {
                    jPanelTitleFrame = new JPanel();
                    getContentPane().add(jPanelTitleFrame, BorderLayout.NORTH);
                    jPanelTitleFrame.setPreferredSize(new java.awt.Dimension(
                            335, 28));
                    jLabelCustomer = new JLabel(customer.getCusName());
                    {
                        jLabelCustomer = new JLabel();
                        jPanelTitleFrame.add(jLabelCustomer);
                        jLabelCustomer.setText(customer.getCusName());
                        jLabelCustomer.setFont(new java.awt.Font("Bodoni MT",
                                1, 16));
                    }
                }
                {// address Frame
                    jPanelAddressFrame = new JPanel();
                    TableLayout jPanelAddressFrameLayout = new TableLayout(
                            new double[][]{
                                    {144.0, 55.0, 123.0, 55.0,
                                            TableLayout.FILL},
                                    {TableLayout.FILL, TableLayout.FILL,
                                            TableLayout.FILL, TableLayout.FILL,
                                            TableLayout.FILL, TableLayout.FILL,
                                            TableLayout.FILL}});
                    jPanelAddressFrameLayout.setHGap(5);
                    jPanelAddressFrameLayout.setVGap(5);
                    jPanelAddressFrame.setLayout(jPanelAddressFrameLayout);
                    getContentPane().add(jPanelAddressFrame,
                            BorderLayout.CENTER);
                    jPanelAddressFrame.setPreferredSize(new java.awt.Dimension(
                            335, 176));
                    jPanelAddressFrame.setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.LOWERED));
                    {
                        jLabelType = new JLabel();
                        jPanelAddressFrame.add(jLabelType, "0, 0");
                        jLabelType.setText("Adres type :");
                    }
                    {
                        jLabelStreet = new JLabel();
                        jPanelAddressFrame.add(jLabelStreet, "0, 1");
                        jLabelStreet.setText("Straat :");
                    }

                    {
                        jLabelNumber = new JLabel();
                        jPanelAddressFrame.add(jLabelNumber, "0, 2");
                        jLabelNumber.setText("Nummer :");
                    }
                    {
                        jLabelZip = new JLabel();
                        jPanelAddressFrame.add(jLabelZip, "0, 3");
                        jLabelZip.setText("Postcode :");
                    }
                    {
                        jLabelCountry = new JLabel();
                        jPanelAddressFrame.add(jLabelCountry, "0, 5");
                        jLabelCountry.setText("Land :");
                    }

                    {
                        jLabelBox = new JLabel();
                        jPanelAddressFrame.add(jLabelBox, "2,2,c,f");
                        jLabelBox.setText("Bus :");
                    }

                    {
                        jLabelCity = new JLabel();
                        jPanelAddressFrame.add(jLabelCity, "0, 4");
                        jLabelCity.setText("Gemeente :");
                    }

                    {
                        ComboBoxModel jComboBoxAddressTypeModel = new DefaultComboBoxModel();
                        jComboBoxAddressType = getJComboBoxAddressType(jComboBoxAddressTypeModel);
                        jPanelAddressFrame.add(jComboBoxAddressType,
                                "1, 0, 2, 0");
                    }
                    {
                        ComboBoxModel jComboBoxCountryModel = new DefaultComboBoxModel();
                        jComboBoxCountry = getJComboBoxCountries(jComboBoxCountryModel);
                        jPanelAddressFrame.add(jComboBoxCountry, "1, 5, 2, 5");
                    }
                    {
                        jTextFieldStreet = new JTextField();
                        jPanelAddressFrame.add(jTextFieldStreet, "1, 1, 4, 1");
                    }
                    {
                        jTextFieldNumber = new JTextField();
                        jPanelAddressFrame.add(jTextFieldNumber, "1, 2");
                    }
                    {
                        jTextFieldZip = new JTextField();
                        jPanelAddressFrame.add(jTextFieldZip, "1, 3");
                    }
                    {
                        jTextFieldBox = new JTextField();
                        jPanelAddressFrame.add(jTextFieldBox, "3, 2");
                    }
                    {
                        jTextFieldCity = new JTextField();
                        jPanelAddressFrame.add(jTextFieldCity, "1, 4, 3, 4");
                    }
                }

                // initialize addressfields
                if (address != null) {
                    jTextFieldStreet.setText(address.getAddStreet());
                    jTextFieldNumber.setText(address.getAddNumber());
                    jTextFieldBox.setText(address.getAddBox());
                    jTextFieldCity.setText(address.getAddCity());
                    jTextFieldZip.setText(address.getAddZip());
                    jComboBoxAddressType.setSelectedItem(ComboBoxHelper
                            .getSelectedItem(addTypes, address.getAddType()));
                    jComboBoxCountry.setSelectedItem(ComboBoxHelper
                            .getSelectedItem(addCountries,
                                    address.getAddCountry()));

                }
                {
                    jPanelButtonFrame = new JPanel();
                    getContentPane().add(jPanelButtonFrame, BorderLayout.SOUTH);
                    FlowLayout jPanelButtonFrameLayout = new FlowLayout();
                    jPanelButtonFrame.setLayout(jPanelButtonFrameLayout);
                    jPanelButtonFrame.setPreferredSize(new java.awt.Dimension(
                            335, 55));
                    {
                        message = new JTextArea();
                        jPanelButtonFrame.add(message);
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
                                        Constants.EMPTY);
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
                                validateAddress(errorMessages);
                                if (!errorMessages.toString().equals(
                                        Constants.EMPTY)) {
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
                                        && (address == null)) {
                                    Address newAddress = new Address(
                                            NumberController.readLastNumber(
                                                    NumberEnum.ADDRESS
                                                            .getType(), 0)
                                                    .toString(), customer
                                            .getIdCus(),
                                            jTextFieldStreet.getText(),
                                            jTextFieldNumber.getText(),
                                            jTextFieldBox.getText(),
                                            jTextFieldZip.getText(),
                                            jTextFieldCity.getText(),
                                            addCountries.get(jComboBoxCountry
                                                    .getSelectedItem()
                                                    .toString()), addTypes
                                            .get(jComboBoxAddressType
                                                    .getSelectedItem()
                                                    .toString()), true);
                                    if (address == null) {
                                        AddressController
                                                .createAddress(newAddress);
                                        address = new Address(newAddress);

                                        greenMessage("Address "
                                                + address.getIdAddress()
                                                + " werd toegevoegd!");
                                    }

                                } else {
                                    if (operation == CRUDOperationEnum.UPDATE) {
                                        Address newAddress = new Address(
                                                address.getIdAddress(),
                                                customer.getIdCus(),
                                                jTextFieldStreet.getText(),
                                                jTextFieldNumber.getText(),
                                                jTextFieldBox.getText(),
                                                jTextFieldZip.getText(),
                                                jTextFieldCity.getText(),
                                                addCountries
                                                        .get(jComboBoxCountry
                                                                .getSelectedItem()
                                                                .toString()),
                                                addTypes.get(jComboBoxAddressType
                                                        .getSelectedItem()
                                                        .toString()), true);
                                        if (!newAddress.equals(address)) {
                                            AddressController
                                                    .updateAddress(newAddress);

                                            address = new Address(newAddress);
                                            greenMessage("Address "
                                                    + address.getIdAddress()
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

    private void resetMessage() {
        message.setText(Constants.EMPTY);
        message.setBackground(new java.awt.Color(238, 238, 238));
    }

    /**
     * Validate address panel
     *
     * @param errorMessages
     */
    private void validateAddress(StringBuilder errorMessages) {
        resetMessage();
        if (jTextFieldStreet.equals(Constants.EMPTY)) {
            errorMessages.append(Constants.NO_STREET + "\n");
        }
        if (jTextFieldZip.equals(Constants.EMPTY)) {
            errorMessages.append(Constants.NO_ZIP + "\n");
        }
    }

    /**
     * Build AddressType combobox
     *
     * @param jComboBoxAddressTypeModel
     * @return
     */
    private JComboBox getJComboBoxAddressType(
            ComboBoxModel jComboBoxAddressTypeModel) {
        if (jComboBoxAddressType == null) {
            addTypes = CodeController.getCodeDetails(CodeEnum.ADDRESS_TYPE
                    .getType());// AddressTypes
            jComboBoxAddressTypeModel = new DefaultComboBoxModel(addTypes
                    .keySet().toArray());
            jComboBoxAddressType = new JComboBox();
            jComboBoxAddressType.setModel(jComboBoxAddressTypeModel);
        }
        return jComboBoxAddressType;
    }

    /**
     * Build Countries combobox
     *
     * @param jComboBoxCountryModel
     * @return
     */
    private JComboBox getJComboBoxCountries(ComboBoxModel jComboBoxCountryModel) {
        if (jComboBoxCountry == null) {
            addCountries = CodeController.getCodeDetails(CodeEnum.COUNTRY
                    .getType());// AddressTypes
            jComboBoxCountryModel = new DefaultComboBoxModel(addCountries
                    .keySet().toArray());
            jComboBoxCountry = new JComboBox();
            jComboBoxCountry.setModel(jComboBoxCountryModel);
        }
        return jComboBoxCountry;
    }

}
