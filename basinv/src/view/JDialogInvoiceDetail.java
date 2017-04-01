/**
 * 
 */
package view;

import static utilities.ComboBoxHelper.getSelectedItem;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.TreeMap;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import model.Customer;
import model.Invoice;
import model.InvoiceDetail;
import model.Price;
import persistency.controller.CodeController;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceDetailController;
import persistency.controller.PriceController;
import persistency.controller.ProductController;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.ComboBoxHelper;
import utilities.Constants;
import utilities.Figures;

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
public class JDialogInvoiceDetail extends JDialog {
	private JPanel jPanelNorth;
	private JPanel jPanelInvoiceDetail;
	private JPanel jPanelSouth;

	private JComboBox jComboBoxProduct;
	private JComboBox jComboBoxPrice;
	private JComboBox jComboBoxVatPercentage;
	private JComboBox jComboBoxUnitOfMeasure;


	private ComboBoxModel jComboBoxProductModel;
	private ComboBoxModel jComboBoxPriceModel;
	private ComboBoxModel jComboBoxVatPercentageModel;
	private ComboBoxModel jComboBoxUnitOfMeasureModel;

	private JLabel jLabelUnitOfMeasure;
	private JLabel jLabelPrijsSelect;
	private JLabel jLabelTotal;
	private JLabel jLabelVatPercentage;

	private JTextField jTextFieldProductPrice;
	private JTextField jTextFieldLineTotalExcl;
	private JTextField jTextFieldInvoiceCurrency;
	private JTextField jTextFieldLineTotalIncl;
	private JTextField jTextFieldVatAmount;

	private JScrollPane jScrollPaneComments;
	private JTextArea message;

	private JLabel jLabelProduct;
	private JLabel jLabelInvoiceID;
	private JLabel jLabelLineNumber;
	private JLabel jLabelPrice;
	private JLabel jLabelComments;
	private JTextArea jTextAreaComments;
	private JTextField jTextFieldInvQty;
	private JButton jButtonConfirm;
	private JLabel jLabelQuantity;

	private JTextField jTextFieldInvoiceID;
	private JTextField jTextFieldInvDetLine;

	private TreeMap<String, String> invPrices, invProducts, invVat, invUnits;
	private InvoiceDetail detail, previous;
	private Invoice invoice;
	private CRUDOperationEnum operation;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JDialogInvoiceDetail(Invoice invoice, InvoiceDetail detail,
			CRUDOperationEnum operation) {
		super();
		this.invoice = invoice;
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
						jLabelInvoiceID = new JLabel();
						jPanelNorth.add(jLabelInvoiceID);
						jLabelInvoiceID.setText("Factuur :");
					}
					{// invoice
						jTextFieldInvoiceID = new JTextField();
						jPanelNorth.add(jTextFieldInvoiceID);
						jTextFieldInvoiceID.setEditable(false);
						jTextFieldInvoiceID
								.setPreferredSize(new java.awt.Dimension(114,
										26));
						jTextFieldInvoiceID.setFont(new java.awt.Font("Dialog",
								1, 18));
					}
				}

				jPanelInvoiceDetail = new JPanel();
				TableLayout jPanelInvoiceDetailLayout = new TableLayout(
						new double[][] {
								{ 110.0, 73.0, 109.0, 101.0, TableLayoutConstants.FILL },
								{ 25.0, 25.0, 25.0, 25.0, 25.0, 25.0,
										TableLayoutConstants.FILL } });
				jPanelInvoiceDetailLayout.setHGap(5);
				jPanelInvoiceDetailLayout.setVGap(5);
				jPanelInvoiceDetail.setLayout(jPanelInvoiceDetailLayout);

				getContentPane().add(jPanelInvoiceDetail, BorderLayout.CENTER);
				jPanelInvoiceDetail.setPreferredSize(new java.awt.Dimension(
						371, 210));
				jPanelInvoiceDetail.setBorder(new SoftBevelBorder(
						BevelBorder.LOWERED, null, null, null, null));
				{
					jLabelLineNumber = new JLabel();
					jPanelInvoiceDetail.add(jLabelLineNumber, "0, 0");
					jLabelLineNumber.setText("Lijn :");
				}
				{// Line
					jTextFieldInvDetLine = new JTextField();
					jTextFieldInvDetLine.setFont(new Font("Dialog", 1, 18));
					jPanelInvoiceDetail.add(jTextFieldInvDetLine, "1, 0");
					jTextFieldInvDetLine.setEditable(false);
				}
				{
					jLabelProduct = new JLabel();
					jPanelInvoiceDetail.add(jLabelProduct, "0, 1");
					jLabelProduct.setText("Product/Dienst :");
				}
				{
					jLabelQuantity = new JLabel();
					jPanelInvoiceDetail.add(jLabelQuantity, "0, 3");
					jLabelQuantity.setText("Hoeveelheid :");
				}
				{
					jLabelPrice = new JLabel();
					jPanelInvoiceDetail.add(jLabelPrice, "2, 3");
					jLabelPrice.setText("Prijs :");
				}

				{// product
					jComboBoxProduct = getJComboBoxProduct();
					jComboBoxProduct.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent evt) {
							jComboBoxPrice.setModel(getJComboBoxPriceModel());

						}
					});

					jPanelInvoiceDetail.add(jComboBoxProduct, "2, 1, 3, 1");
				}
				{// price
					jComboBoxPrice = getJComboBoxPriceEmpty();
					jComboBoxPrice.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent evt) {
							StringBuilder errorMessages = new StringBuilder("");
							resetMessage();
							if (validateInput(errorMessages)) {
//								jTextFieldLineTotalExcl
//										.setText(calculateLineTotalExcl(createDetail()));
//								jTextFieldVatAmount
//										.setText(calculateVat(createDetail()));
//
								initializeFields();
							}
						}
					});
					jPanelInvoiceDetail.add(jComboBoxPrice, "2, 2, 4, 2");

				}

				{// Quantity
					jPanelInvoiceDetail.add(getJTextFieldInvQty(), "0, 4");
				}
			}
			{
				jPanelInvoiceDetail.add(getJScrollPaneComments(), "1, 6, 3, 5");
			}
			{
				jLabelComments = new JLabel();
				jPanelInvoiceDetail.add(jLabelComments, "0, 6");
				jPanelInvoiceDetail.add(getJTextFieldInvoiceCurrency(), "4, 5");
				jPanelInvoiceDetail.add(getJTextFieldLineTotal(), "3, 4");
				jPanelInvoiceDetail.add(getJTextFieldProductPrice(), "2, 4");
				jPanelInvoiceDetail.add(getJLabelTotal(), "3, 3");
				jPanelInvoiceDetail.add(getJLabelPrijsSelect(), "0, 2");
				jPanelInvoiceDetail.add(getJLabelUnitOfMeasure(), "1, 3");
				jPanelInvoiceDetail.add(getJComboBoxUnitOfMeasure(), "1, 4");
				jPanelInvoiceDetail.add(getJLabelVatPercentage(), "0, 5");
				jPanelInvoiceDetail.add(getJComboBoxVatPercentage(), "1, 5");
				jPanelInvoiceDetail.add(getJTextFieldVatAmount(), "2, 5");
				jPanelInvoiceDetail.add(getJTextFieldTotalIncl(), "3, 5");
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
					@Override
					public void actionPerformed(ActionEvent evt) {
						StringBuilder errorMessages = new StringBuilder("");
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
		validateInvoiceDetail(errorMessages);
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
		InvoiceDetail newDetail = createDetail();
		if ((detail == null) && (operation.equals(CRUDOperationEnum.NEW))) {
			InvoiceDetailController.createInvoiceDetail(newDetail);
			detail = new InvoiceDetail(newDetail);
			greenMessage("Lijn " + newDetail.getInvDetLine()
					+ " is toegevoegd!");
		} else {
			if (!detail.equals(newDetail)) {
				InvoiceDetailController.updateInvoiceDetail(newDetail);
				detail = new InvoiceDetail(newDetail);
				greenMessage("Lijn " + newDetail.getInvDetLine()
						+ " is gewijzigd!");
			}
		}
	}

	/**
	 * @return
	 */
	private InvoiceDetail createDetail() {
		String key = jComboBoxVatPercentage.getSelectedItem().toString();
		String vat = invVat.get(key);
		InvoiceDetail newDetail = new InvoiceDetail(invoice.getIdInvoice(),
				new Integer(jTextFieldInvDetLine.getText()), invProducts
						.get(jComboBoxProduct.getSelectedItem().toString()),
				new BigDecimal(jTextFieldInvQty.getText()),
				invUnits.get(
						getJComboBoxUnitOfMeasure().getSelectedItem().toString()),
				getPrice(jComboBoxPrice.getSelectedItem().toString()),
				vat,
				jTextAreaComments.getText(), true);
		return newDetail;
	}

	private BigDecimal getPrice(String priceID) {
		String key = invPrices.get(priceID);
		BigDecimal price = null;
		if (!key.equals("")) {
			Price productPrice = PriceController.getOneProductPrice(key);
			price = productPrice.getPriUnit();
		}
		try {
			price = new BigDecimal(jTextFieldProductPrice.getText());
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		return price;

	}

	private String getUnitOfMeasure(String priceID) {
		String key = invPrices.get(priceID);
		if (!key.equals("")) {
			Price price = PriceController.getOneProductPrice(key);
			return price.getPriMeasure();
		}
		return getJComboBoxUnitOfMeasure().getSelectedItem().toString();
	}

	/**
	 * Validate invoiceDetail panel
	 * 
	 * @param errorMessages
	 */
	private void validateInvoiceDetail(StringBuilder errorMessages) {
		if ((operation.equals(CRUDOperationEnum.NEW))
				|| ((errorMessages.toString().equals(Constants.EMPTY)) && (!detail
						.equals(previous)))) {

			String selectedPrice = jComboBoxPrice.getSelectedItem().toString();
			// no selected price
			if (!selectedPrice.equals(Constants.EMPTY)) {
				Price productPrice = PriceController
						.getOneProductPrice(invPrices.get(selectedPrice));
				getJComboBoxUnitOfMeasure().setSelectedItem(
						productPrice.getPriMeasure().toString());
				getJTextFieldProductPrice().setText(
						productPrice.getPriUnit().toString());
			}
		}
		BigDecimal price = null;
		BigDecimal qty = null;
		try {
			qty = new BigDecimal(jTextFieldInvQty.getText());
		} catch (NumberFormatException ex) {
			errorMessages.append(Constants.QTY_NO_FIGURES + "\n");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			price = new BigDecimal(jTextFieldProductPrice.getText());
		} catch (NumberFormatException ex) {
			errorMessages.append(Constants.PRICE_NO_FIGURES + "\n");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (jComboBoxProduct.getSelectedItem() == null) {
			errorMessages.append(Constants.NO_PRODUCT + "\n");

		}
		if ((jComboBoxPrice.getSelectedItem() == null)
				&& (price.doubleValue() <= Figures.ZERO)) {
			errorMessages.append(Constants.NO_PRICE + "\n");
		}
		if (qty.doubleValue() <= Figures.ZERO) {
			errorMessages.append(Constants.NO_QTY + "\n");
		}
	}

	/**
	 * @return Products
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	private JComboBox getJComboBoxProduct() {
		if (jComboBoxProduct == null) {
			invProducts = ProductController
					.getProductDetails(getProductFilter());
			invProducts.put("", ""); // first element empty
			jComboBoxProductModel = new DefaultComboBoxModel(invProducts
					.keySet().toArray());
			jComboBoxProduct = new JComboBox();
			jComboBoxProduct.setModel(jComboBoxProductModel);
		}
		return jComboBoxProduct;
	}

	private ComboBoxModel getJComboBoxPriceModel() {
		invPrices = PriceController
				.getProductPriceDetails(invProducts.get(getJComboBoxProduct()
						.getSelectedItem().toString()), invoice.getInvDate());
		invPrices.put("", ""); // first element empty
		jComboBoxPriceModel = new DefaultComboBoxModel(invPrices.keySet()
				.toArray());
		return jComboBoxPriceModel;
	}

	private ComboBoxModel getJComboBoxVatPercentageModel() {
		invVat = CodeController.getCodeDetails(Constants.VAT);
		jComboBoxVatPercentageModel = new DefaultComboBoxModel(invVat.keySet()
				.toArray());
		return jComboBoxVatPercentageModel;
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
		String[] filter = { "", "", "true" };
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

	private JTextField getJTextFieldInvoiceCurrency() {
		if (jTextFieldInvoiceCurrency == null) {
			jTextFieldInvoiceCurrency = new JTextField();
			jTextFieldInvoiceCurrency.setEditable(false);
			jTextFieldInvoiceCurrency
					.setFont(new java.awt.Font("Dialog", 1, 12));
		}
		return jTextFieldInvoiceCurrency;
	}

	private JTextField getJTextFieldLineTotal() {
		if (jTextFieldLineTotalExcl == null) {
			jTextFieldLineTotalExcl = new JTextField();
			jTextFieldLineTotalExcl.setEditable(false);
		}
		return jTextFieldLineTotalExcl;
	}

	private void initializeFields() {
		jTextFieldInvoiceID.setText(invoice.getIdInvoice());
		if (detail != null) {
			Customer customer = CustomerController.getCustomer(invoice
					.getInvCusid());
			jTextFieldInvoiceCurrency.setText(customer.getCusCur());
			jTextFieldInvDetLine.setText(new Integer(detail.getInvDetLine())
					.toString());
			jTextFieldInvQty.setText(detail.getInvQty().toString());
			getJComboBoxUnitOfMeasure().setSelectedItem(ComboBoxHelper
					.getSelectedItem(invUnits, detail.getInvMeasure()));
			jTextFieldProductPrice.setText(detail.getInvPrice().toString());
			jComboBoxProduct.setSelectedItem(getSelectedItem(invProducts,
					detail.getInvProdid()));
			jComboBoxVatPercentage.setSelectedItem(getSelectedItem(invVat,
					detail.getInvVat()));
			//value calculations
			jTextFieldLineTotalExcl.setText(calculateLineTotalExcl(detail));
			jTextFieldVatAmount.setText(calculateVat(detail));
			jTextFieldLineTotalIncl.setText(calculateLineTotalIncl(calculateLineTotalExcl(detail), calculateVat(detail)));

			jTextAreaComments.setText(detail.getInvComments());
			previous = new InvoiceDetail(detail);
		} else {
			jTextFieldInvDetLine.setText(new Integer(JDialogInvoice
					.getLineCounter()).toString());
		}

	}

	private String calculateLineTotalExcl(InvoiceDetail detail) {
		double total = detail.getInvQty().doubleValue()
				* detail.getInvPrice().doubleValue();
		BigDecimal dec = new BigDecimal(total);
		dec = dec.setScale(2, BigDecimal.ROUND_HALF_UP);
		return dec.toString();
	}

	private String calculateVat(InvoiceDetail detail) {
		String StrVatPerc = invVat.get(jComboBoxVatPercentage.getSelectedItem().toString());
		double vatPerc = new BigDecimal(StrVatPerc).doubleValue() / Figures.HUNDRED;
		double vatAmount = detail.getInvQty().doubleValue()
				* detail.getInvPrice().doubleValue() * vatPerc;
		BigDecimal dec = new BigDecimal(vatAmount);
		dec = dec.setScale(2, BigDecimal.ROUND_HALF_UP);
		return dec.toString();
	}

	private String calculateLineTotalIncl(String excl, String vatAmt) {
		double total = new BigDecimal(excl).doubleValue() + new BigDecimal(vatAmt).doubleValue();
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

	public JTextField getJTextFieldInvQty() {
		if (jTextFieldInvQty == null) {
			jTextFieldInvQty = new JTextField();
			jTextFieldInvQty.setText("0.00");
		}
		return jTextFieldInvQty;
	}

	private JLabel getJLabelTotal() {
		if (jLabelTotal == null) {
			jLabelTotal = new JLabel();
			jLabelTotal.setText("Totaal (Excl/Incl):");
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

	public JLabel getJLabelUnitOfMeasure() {
		if (jLabelUnitOfMeasure == null) {
			jLabelUnitOfMeasure = new JLabel();
			jLabelUnitOfMeasure.setText("Eenh.");
		}
		return jLabelUnitOfMeasure;
	}

	public JComboBox getJComboBoxUnitOfMeasure() {
		if (jComboBoxUnitOfMeasure == null) {
			invUnits = CodeController.getCodeDetails(CodeEnum.UNIT_OF_MEASURE
					.getType());// Units of measure
			jComboBoxUnitOfMeasureModel = new DefaultComboBoxModel(invUnits
					.keySet().toArray());
			jComboBoxUnitOfMeasure = new JComboBox();
			jComboBoxUnitOfMeasure.setModel(jComboBoxUnitOfMeasureModel);
		}
		return jComboBoxUnitOfMeasure;
	}

	private JLabel getJLabelVatPercentage() {
		if (jLabelVatPercentage == null) {
			jLabelVatPercentage = new JLabel();
			jLabelVatPercentage.setText("BTW :");
		}
		return jLabelVatPercentage;
	}

	public JComboBox getJComboBoxVatPercentage() {
		if (jComboBoxVatPercentage == null) {
			jComboBoxVatPercentage = new JComboBox();
			jComboBoxVatPercentage.setModel(getJComboBoxVatPercentageModel());
		}
		return jComboBoxVatPercentage;
	}

	public JTextField getJTextFieldVatAmount() {
		if (jTextFieldVatAmount == null) {
			jTextFieldVatAmount = new JTextField();
			jTextFieldVatAmount.setEditable(false);
		}
		return jTextFieldVatAmount;
	}

	public JTextField getJTextFieldTotalIncl() {
		if (jTextFieldLineTotalIncl == null) {
			jTextFieldLineTotalIncl = new JTextField();
			jTextFieldLineTotalIncl.setEditable(false);
		}
		return jTextFieldLineTotalIncl;
	}
}
