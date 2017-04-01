package view;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Price;
import model.Product;
import persistency.controller.CodeController;
import persistency.controller.NumberController;
import persistency.controller.PriceController;
import persistency.controller.ProductController;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.ComboBoxHelper;
import utilities.Constants;
import utilities.Date;
import utilities.DatumException;
import utilities.NumberEnum;

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
public class JDialogProduct extends JDialog {
	private JFrame parent;
	private JDialog dialog;

	private JPanel jPanelProductSouth;
	private JPanel jPanelProductNorth;
	private JPanel jPanelProductCenter;

	private JLabel jLabelProdType;
	private JLabel jLabelProdDesc;
	private JLabel jLabelProdCode;
	private JLabel jLabelProdCat;
	private JTextField jTextFieldIdProduct;
	private JTextField jTextFieldProdCode;
	private JTextField jTextFieldProdDesc;

	private JTable jTableProductPrices;
	private TableModel jTableProductPricesModel;

	private JComboBox jComboBoxProdCat;
	private JComboBox jComboBoxProdType;
	private ComboBoxModel jComboBoxProdCatModel;
	private ComboBoxModel jComboBoxProdTypeModel;

	private JButton jButtonProductSave;
	private JTextArea jTextAreaMessage;
	private JButton jButtonNewPrice;
	private JScrollPane jScrollPane1;
	private JPopupMenu jPopupMenuProductPrices;

	private JRadioButtonMenuItem jRadioButtonMenuItemDeleteProductPrice;
	private JRadioButtonMenuItem jRadioButtonMenuItemUpdateProductPrice;

	private Product product;
	private CRUDOperationEnum operation;
	private Date toDay;

	private TreeMap<String, String> prodCats, prodTypes;
	private int row;


	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JDialogProduct(JFrame frame, Product product,
			CRUDOperationEnum operation) {
		this(frame, true, operation);
		this.product = product;
		initGUI();
	}

	private JDialogProduct(JFrame frame, boolean modal,
			CRUDOperationEnum operation) {
		super(frame, modal);
		this.operation = operation;
		prodCats = CodeController.getCodeDetails(CodeEnum.PRODUCT_CATEGORY
				.getType());// Product categories'
		prodTypes = CodeController.getCodeDetails(CodeEnum.PRODUCT_TYPE
				.getType());// Product Type'

		try {
			this.toDay = new Date();
		} catch (DatumException e) {
			e.printStackTrace();
		}
		this.parent = frame;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			createNorthPanel();
			createCenterPanel();
			createSouthPanel();
			this.setSize(370, 319);
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void createSouthPanel() {
		jPanelProductSouth = new JPanel();
		getContentPane().add(jPanelProductSouth, BorderLayout.SOUTH);
		jPanelProductSouth.setPreferredSize(new java.awt.Dimension(354, 56));
		jPanelProductSouth.add(getJButtonProductSave());
		getJButtonProductSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				StringBuilder errorMessages = new StringBuilder(Constants.EMPTY);
				if (validateInput(errorMessages)) {
					saveInput();
					initializeProductFields();
				}
			}
		});
		jPanelProductSouth.add(getJTextAreaMessage());
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
		validateProduct(errorMessages);
		if (!errorMessages.toString().equals(Constants.EMPTY)) {
			redMessage(errorMessages);
			return false;
		}
		return true;
	}

	private boolean validateProduct(StringBuilder errorMessages) {
		if (jTextFieldProdCode.getText().equals("")) {
			errorMessages.append(Constants.NO_PRODUCT_CODE);
			return false;
		}
		if (jComboBoxProdCat.getSelectedItem() == null) {
			errorMessages.append(Constants.NO_PRODUCT_CATEGORY);
			return false;
		}
		if (jComboBoxProdType.getSelectedItem() == null) {
			errorMessages.append(Constants.NO_PRODUCT_TYPE);
			return false;
		}
		Product temp = ProductController.getProductByCode(jTextFieldProdCode
				.getText());
		if ((temp != null) && (operation.equals(CRUDOperationEnum.NEW))) {
			errorMessages.append(Constants.PRODUCT_CODE_EXIST);
			return false;
		}
		return true;
	}

	synchronized private void saveInput() {

		if ((product == null) && (operation == CRUDOperationEnum.NEW)) {
			Product newProduct = createNewProduct();
			ProductController.createProduct(newProduct);
			product = new Product(newProduct);
			greenMessage(newProduct.getIdProd() + " werd Toegevoegd");
			//ones created, updates are allowed
			operation=CRUDOperationEnum.UPDATE;
		}
		if (operation == CRUDOperationEnum.UPDATE) {
			Product newProduct = updateExistingProduct();

			if (!product.equals(newProduct)) {
				ProductController.updateProduct(newProduct);
				product = new Product(newProduct);
				greenMessage(newProduct.getIdProd() + " werd gewijzigd");
			}
		}
		// All modifications should be shown real-time
		initializeProductFields();
	}

	/**
	 * @param deliveryDate
	 * @return
	 */
	private Product updateExistingProduct() {
		String productCategory = prodCats.get(jComboBoxProdCat
				.getSelectedItem().toString());
		String productType = prodTypes.get(jComboBoxProdType.getSelectedItem()
				.toString());
		String productCode = jTextFieldProdCode.getText();
		String productDesc = jTextFieldProdDesc.getText();

		Product newProduct = new Product(product.getIdProd(), productCode,
		// Unique product code
				productDesc, // product Description
				productCategory, // prodCategory | char(5)
				productType, // prodType | char(5)
				product.isActive() // Active | tinyint(1)
		);
		return newProduct;
	}

	/**
	 * @param deliveryDate
	 * @return
	 */
	private Product createNewProduct() {
		String productCategory = prodCats.get(jComboBoxProdCat
				.getSelectedItem().toString());
		String productType = prodTypes.get(jComboBoxProdType.getSelectedItem()
				.toString());
		String productCode = jTextFieldProdCode.getText();
		String productDesc = jTextFieldProdDesc.getText();

		String productID = NumberController.readLastNumber(
				NumberEnum.PRODUCT.getType(), 0).toString();

		Product newProduct = new Product(productID, productCode, // Unique
				// product
				// code
				productDesc, // product Description
				productCategory, // prodCategory | char(5)
				productType, // prodType | char(5)

				true // Active | tinyint(1)
		);
		return newProduct;
	}


	/**
	 * 
	 */
	private JButton getJButtonProductSave() {
		if (jButtonProductSave == null) {
			jButtonProductSave = new JButton();
			jButtonProductSave.setText("OK");
			jButtonProductSave.setToolTipText("Gegevens bewaren");
		}
		return jButtonProductSave;
	}

	/**
	 * 
	 */
	private void createCenterPanel() {
		jPanelProductCenter = new JPanel();
		getContentPane().add(getJPanel1(), BorderLayout.CENTER);
		jPanelProductCenter.setPreferredSize(new java.awt.Dimension(354, 147));
		jPanelProductCenter.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		{
			jScrollPane1 = new JScrollPane();
			jPanelProductCenter.add(jScrollPane1);
			jPanelProductCenter.add(getJButtonNewPrice());
			getJButtonNewPrice().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					new JDialogPrice(dialog, product, null,
							CRUDOperationEnum.NEW);
					initializeProductPriceFields();
				}
			});
			jScrollPane1.setPreferredSize(new java.awt.Dimension(317, 85));
			{
				initializeProductPriceFields();
				jScrollPane1.setViewportView(getJTableProductPrices());
				getJTableProductPrices().getTableHeader().setFont(
						new java.awt.Font("Dialog", 1, 12));
			}
		}
	}

	/**
	 * 
	 */
	private void createNorthPanel() {
		jPanelProductNorth = new JPanel();
		TableLayout jPanel2Layout = new TableLayout(new double[][] {
				{ 90.0, 118.0, 51.0, TableLayoutConstants.FILL },
				{ 26.0, 26.0, 26.0, TableLayoutConstants.FILL } });
		jPanel2Layout.setHGap(5);
		jPanel2Layout.setVGap(5);
		jPanelProductNorth.setLayout(jPanel2Layout);
		getContentPane().add(jPanelProductNorth, BorderLayout.NORTH);
		jPanelProductNorth.setPreferredSize(new java.awt.Dimension(362, 102));
		{
			jLabelProdCode = new JLabel();
			jPanelProductNorth.add(jLabelProdCode, "0, 0");
			jLabelProdCode.setText("Naam :");
		}
		{
			jLabelProdDesc = new JLabel();
			jPanelProductNorth.add(jLabelProdDesc, "0, 1");
			jLabelProdDesc.setText("Omschrijving :");
		}
		{
			jLabelProdCat = new JLabel();
			jPanelProductNorth.add(jLabelProdCat, "0, 2");
			jLabelProdCat.setText("Categorie :");
		}
		{
			jLabelProdType = new JLabel();
			jPanelProductNorth.add(jLabelProdType, "2, 2");
			jLabelProdType.setText("Type :");
		}

		initializeProductFields();
		jPanelProductNorth.add(getJComboBoxProdType(), "3, 2");
		jPanelProductNorth.add(getJComboBoxProdCat(), "1, 2");
		jPanelProductNorth.add(getJTextFieldProdDesc(), "1, 1, 3, 1");
		jPanelProductNorth.add(getJTextFieldProdCode(), "1, 0");
		jPanelProductNorth.add(getJTextFieldIdProduct(), "2, 0");
		jTextFieldIdProduct.setEditable(false);
		jTextFieldIdProduct.setFont(new java.awt.Font("Dialog", 1, 18));
	}

	/**
	 * 
	 */
	private void setProductCategoryComboBox() {
		jComboBoxProdCatModel = new DefaultComboBoxModel(prodCats.keySet()
				.toArray());
		jComboBoxProdCat = new JComboBox();
		jComboBoxProdCat.setModel(jComboBoxProdCatModel);
	}

	/**
	 * 
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	private void setProductTypeComboBox() {
		jComboBoxProdTypeModel = new DefaultComboBoxModel(prodTypes.keySet()
				.toArray());
		jComboBoxProdType = new JComboBox();
		jComboBoxProdType.setModel(jComboBoxProdTypeModel);

	}

	private void initializeProductFields() {
		if (product != null) {
			getJTextFieldIdProduct().setText(product.getIdProd());
			getJTextFieldProdDesc().setText(product.getProdDesc());
			getJTextFieldProdCode().setText(product.getProdCode());
			getJTextFieldProdCode().setEditable(false);
		}
		setProductTypeComboBox();
		setProductCategoryComboBox();
		if (product != null) {
			jComboBoxProdType.setSelectedItem(ComboBoxHelper.getSelectedItem(
					prodTypes, product.getProdType()));
			jComboBoxProdCat.setSelectedItem(ComboBoxHelper.getSelectedItem(
					prodCats, product.getProdCat()));
		}
	}

	/**
	 * @return
	 */
	private String[] getProductPriceTitles() {
		return new String[] { "Nr.", "Prijs", "Eenheid", "Vanaf" };
	}

	/**
	 * @return
	 */
	private String[][] getProductPriceColumns(int[] columnWidth) {
		String[][] columns = null;
		if (product != null) {
			Price price;
			List<Price> list = PriceController.getAllValidProductPrice(
					product.getIdProd());
			//no known prices for this product
			if (list == null) {
				return null;
			}
			columns = new String[list.size()][];
			Iterator<Price> it = list.iterator();

			int i = 0;
			while (it.hasNext()) {
				price = it.next();
				columns[i] = new String[] {
						price.getIdPrice(),
						String.format("%10.2f", price.getPriUnit()
								.doubleValue()),
						String.format("%5s", price.getPriMeasure()),

						String.format("%10s", price.getPrifrom()
								.getDatumInEuropeesFormaat()) };
				calculateColumnWidth(columns[i], columnWidth);
				i++;
			}
		} 
		return columns;
	}

	private void calculateColumnWidth(String[] row, int[] columnWidth) {
		for (int i = 0; i < row.length; i++) {
			if (row[i].length() > columnWidth[i]) {
				columnWidth[i] = row[i].length();
			}
		}
	}

	public JPanel getJPanel1() {
		return jPanelProductCenter;
	}

	public JButton getJButtonSave() {
		return jButtonProductSave;
	}

	public JComboBox getJComboBoxProdType() {
		return jComboBoxProdType;
	}

	public JComboBox getJComboBoxProdCat() {
		return jComboBoxProdCat;
	}

	public JTextField getJTextFieldProdDesc() {
		if (jTextFieldProdDesc == null) {
			jTextFieldProdDesc = new JTextField();
		}
		return jTextFieldProdDesc;
	}

	public JTextField getJTextFieldProdCode() {
		if (jTextFieldProdCode == null) {
			jTextFieldProdCode = new JTextField();
		}
		return jTextFieldProdCode;
	}

	public JTable getJTableProductPrices() {
		if (jTableProductPrices == null) {
			jTableProductPrices = new JTable();
			jTableProductPrices.setToolTipText("Klik om te wijzigen");
		}
		return jTableProductPrices;
	}

	public JTextField getJTextFieldIdProduct() {
		if (jTextFieldIdProduct == null) {
			jTextFieldIdProduct = new JTextField();
		}
		return jTextFieldIdProduct;
	}

	/**
	 * 
	 */
	private void initializeProductPriceFields() {
		final int DEFAULT_PIXELS = 20;
		int[] columnWidth = new int[getProductPriceTitles().length];
		jTableProductPricesModel = new DefaultTableModel(
				getProductPriceColumns(columnWidth), getProductPriceTitles());

		getJTableProductPrices().setModel(jTableProductPricesModel);
		for (int i = 0; i < columnWidth.length; i++) {
			TableColumn column = getJTableProductPrices().getColumnModel()
					.getColumn(i);
			column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
		}

		jTableProductPrices.getTableHeader().setFont(
				new java.awt.Font("Dialog", 1, 12));
		jTableProductPrices.setFont(new java.awt.Font("Courier New", 0, 14));
		jTableProductPrices.getTableHeader().setToolTipText(
				"Offerte detaillijnen");
		setComponentPopupMenu(jTableProductPrices, getJPopupMenuProductPrices());
	}

	private JPopupMenu getJPopupMenuProductPrices() {
		if (jPopupMenuProductPrices == null) {
			jPopupMenuProductPrices = new JPopupMenu();
			jPopupMenuProductPrices.setBounds(219, 128, 76, 19);
			ButtonGroup group = new ButtonGroup();
			group.add(getJRadioButtonMenuItemUpdateProductPrice());
			group.add(getJRadioButtonMenuItemDeleteProductPrice());
			getJRadioButtonMenuItemUpdateProductPrice().setSelected(true);
			jPopupMenuProductPrices
					.add(getJRadioButtonMenuItemUpdateProductPrice());
			jPopupMenuProductPrices
					.add(getJRadioButtonMenuItemDeleteProductPrice());
		}
		return jPopupMenuProductPrices;
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				menu.show(parent, e.getX(), e.getY());
				JTable table = (JTable) e.getSource();
				table.clearSelection();
				Point p = e.getPoint();
				row = table.rowAtPoint(p);
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				menu.show(parent, e.getX(), e.getY());
				JTable table = (JTable) e.getSource();
				table.clearSelection();
				Point p = e.getPoint();
				row = table.rowAtPoint(p);
			}
		});
		jTableProductPrices.add(menu);

	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemUpdateProductPrice() {
		if (jRadioButtonMenuItemUpdateProductPrice == null) {
			jRadioButtonMenuItemUpdateProductPrice = new JRadioButtonMenuItem();
			jRadioButtonMenuItemUpdateProductPrice.setText("Details");
			jRadioButtonMenuItemUpdateProductPrice
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String idPrice = jTableProductPricesModel
									.getValueAt(row, 0).toString().trim();// Product
							// price
							Price price = PriceController
									.getOneProductPrice(idPrice);
							new JDialogPrice(dialog, product, price,
									CRUDOperationEnum.UPDATE);
							initializeProductPriceFields();
						}
					});

		}
		return jRadioButtonMenuItemUpdateProductPrice;
	}

	private JRadioButtonMenuItem getJRadioButtonMenuItemDeleteProductPrice() {
		if (jRadioButtonMenuItemDeleteProductPrice == null) {
			jRadioButtonMenuItemDeleteProductPrice = new JRadioButtonMenuItem();
			jRadioButtonMenuItemDeleteProductPrice.setText("Verwijderen");
			jRadioButtonMenuItemDeleteProductPrice
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String idPrice = jTableProductPricesModel
									.getValueAt(row, 0).toString().trim();// Product
							// price
							int response = JOptionPaneItemRemove
									.confirm(" prijs " + idPrice);
							if (response == JOptionPane.YES_OPTION) {
								PriceController.removeOneProductPrice(idPrice);
								initializeProductPriceFields();
							}
						}
					});

		}
		return jRadioButtonMenuItemDeleteProductPrice;
	}

	public JButton getJButtonNewPrice() {
		if (jButtonNewPrice == null) {
			jButtonNewPrice = new JButton();
			jButtonNewPrice.setText("Nieuw");
		}
		return jButtonNewPrice;
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
