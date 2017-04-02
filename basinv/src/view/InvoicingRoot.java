package view;
  
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;


import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import model.Address;
import model.Business;
import model.BusinessTypeEnum;
import model.CodeDetail;
import model.CodeHeader;
import model.Customer;
import model.Invoice;
import model.InvoiceView;
import model.Number;
import model.Product;
import model.Quote;
import model.QuoteView;
import persistency.controller.AddressController;
import persistency.controller.CodeController;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceController;
import persistency.controller.InvoiceDetailController;
import persistency.controller.NumberController;
import persistency.controller.ProductController;
import persistency.controller.QuoteController;
import persistency.controller.QuoteDetailController;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.Constants;
import utilities.Date;
import utilities.DateCellRenderer;
import utilities.DateRenderer;
import utilities.DatumException;
import utilities.FixTypes;

import com.toedter.calendar.JDateChooser;
    
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

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane tabbedPane;

	private JLabel jLabelCustomerType;
	private JLabel jLabelCustomerName;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabelCodeTitle;
	private JLabel jLabelCodeHeaderSelection;
	private JLabel jLabelQuoteStatus;

	private JFormattedTextField jFormattedTextFieldCustomerPanelCustomerName;
	private JFormattedTextField jFormattedTextFieldProductPanelProductName;
	private JFormattedTextField jFormattedTextFieldInvoicePanelCustomerName;
	private JFormattedTextField jFormattedTextFieldQuotesPanelCustomerName;
	private JLabel jLabel6;

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

	private TreeMap<String, String> customerTypes, quoteStats, invoiceStats, invoiceTypes,
			nbrCategories;

	private int row;
	private Date toDay;

	private JFrame frame;
	private DateCellRenderer dateCellRenderer;
	private DateRenderer dateRenderer;
	
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				InvoicingRoot inst = new InvoicingRoot();
				inst.initGUI();
			}
		});
	}

	public InvoicingRoot() {
		super();
		try {
			toDay = new Date();
		} catch (DatumException e) {
			e.printStackTrace();
		}
		// initGUI();
	}

	public void initGUI() {
		try {
			frame = this;
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setMinimumSize(new java.awt.Dimension(550, 750));
			JPanel topPanel = new JPanel();
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
			tabbedPane.setPreferredSize(new java.awt.Dimension(778, 616));

			pack();
			this.setSize(975, 750);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setFocusable(true);

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * Builds the Settings Panel
	 */
	public void createSettingsPage() {

		settingsPanel = new JPanel();
		GroupLayout settingsPanelLayout = new GroupLayout(
				settingsPanel);
		settingsPanel.setLayout(settingsPanelLayout);
		settingsPanelLayout.setVerticalGroup(settingsPanelLayout
				.createSequentialGroup().addGap(7).addComponent(
						getJSplitPaneConfiguration(), 0, 681, Short.MAX_VALUE));
		settingsPanelLayout.setHorizontalGroup(settingsPanelLayout
				.createSequentialGroup().addGap(7).addComponent(
						getJSplitPaneConfiguration(), 0, 730, Short.MAX_VALUE));

		getJTableCodeDetails().setModel(getJTableCodeDetailsModel(null));
		getJTableCodeDetails().setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(getJTableCodeDetails(), getJPopupMenuCodeList());

		getJTableNumberDetails().setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(getJTableNumberDetails(),
				getJPopupMenuNumberList());

		getJComboBoxCodeHeader().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
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
		GroupLayout customerPanelLayout = new GroupLayout(
				customerPanel);
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
		customerPanelLayout.setVerticalGroup(customerPanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(customerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(getJButtonCustomerPanelCustomerSearch(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJComboBoxCustomerType(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJFormattedTextFieldCustomerPanelCustomerName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJLabelCustomerName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJLabelCustomerType(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
			.addGap(41)
			.addComponent(customerScrollPane, 0, 535, Short.MAX_VALUE)
			.addGap(21)
			.addComponent(getJButtonNewCustomerPanelCustomer(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(23, 23));
		customerPanelLayout.setHorizontalGroup(customerPanelLayout.createSequentialGroup()
			.addContainerGap(17, 17)
			.addGroup(customerPanelLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
			        .addComponent(getJLabelCustomerName(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addGap(24)
			        .addComponent(getJFormattedTextFieldCustomerPanelCustomerName(), GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
			        .addGap(71)
			        .addComponent(getJButtonNewCustomerPanelCustomer(), GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 92, Short.MAX_VALUE)
			        .addComponent(getJLabelCustomerType(), GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
			        .addComponent(getJComboBoxCustomerType(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, GroupLayout.PREFERRED_SIZE)
			        .addComponent(getJButtonCustomerPanelCustomerSearch(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
			        .addGap(109))
			    .addComponent(customerScrollPane, GroupLayout.Alignment.LEADING, 0, 936, Short.MAX_VALUE))
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
		return new String[] { "Nummer", "Naam", "Ondernemersnummer",
				"Telefoon", "GSM", "eMail", "Type" };
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

	private String[][] getCustomerColumns(int[] columnWidth) {
		String[][] columns;
		Customer customer;
		String[] filter = getCustomerFilter();
		Collection<Business> list = CustomerController.getCustomers(filter);
		columns = new String[list.size()][];
		Iterator<Business> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			customer = (Customer) it.next();
			columns[i] = new String[] {
					customer.getIdCus(),
					customer.getCusName(),
					customer.getCusVat(),
					customer.getCusPhone(),
					customer.getCusMobile(),
					customer.getCusEMail(),
					CodeController.getOneCodeDetail(
							CodeEnum.CUSTOMER_TYPE.getType(),
							customer.getCusType()).getCodeDesc() };
			calculateColumnWidth(columns[i], columnWidth);
			i++;
		}
		return columns;
	}

	private void calculateColumnWidth(Object[] row, int[] columnWidth) {
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
		String name = getJFormattedTextFieldCustomerPanelCustomerName()
				.getText();
		Object desc = getJComboBoxCustomerType().getSelectedItem();
		String type = customerTypes.get(desc);
		String[] filter = { name, type, Constants.TRUE };
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
			jFormattedTextFieldCustomerPanelCustomerName.setText(Constants.EMPTY);
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
						@Override
						public void actionPerformed(ActionEvent evt) {
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
		final int DEFAULT_PIXELS = 50;
		int[] columnWidth = new int[getCustomerColumnTitles().length];

		jTableCustomersModel = new DefaultTableModel(
				getCustomerColumns(columnWidth), getCustomerColumnTitles());
		jTableCustomers = new JTable();
		jTableCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		customerScrollPane.setViewportView(jTableCustomers);
		jTableCustomers.setModel(jTableCustomersModel);
		for (int i = 0; i < getCustomerColumnTitles().length; i++) {
			TableColumn column = jTableCustomers.getColumnModel().getColumn(i);
			column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
		}
		jTableCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableCustomers.getTableHeader().setFont(
				new java.awt.Font("Dialog", 1, 12));
		jTableCustomers.setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(jTableCustomers, getJPopupMenuCustomerList());
	}

	private JButton getJButtonNewCustomerPanelCustomer() {
		if (jButtonNewCustomerPanelCustomer == null) {
			jButtonNewCustomerPanelCustomer = new JButton();
			jButtonNewCustomerPanelCustomer.setText("Nieuw");
			jButtonNewCustomerPanelCustomer.setToolTipText("Nieuwe klant");
			jButtonNewCustomerPanelCustomer
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent evt) {
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
			ButtonGroup customerContext = new ButtonGroup();
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
			ButtonGroup codeContext = new ButtonGroup();
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
			ButtonGroup numberContext = new ButtonGroup();
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
		GroupLayout productPanelLayout = new GroupLayout(
				productPanel);
		productPanel.setLayout(productPanelLayout);
		productPanel.addFocusListener(new FocusAdapter() {
			public void focusGained() {
				resetProductPanel();
			}
		});

		productScrollPane = new JScrollPane();
		productPanelLayout.setVerticalGroup(productPanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(productPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(getJButtonProductPanelProductSearch(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJFormattedTextFieldProductPanelProductNaam(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJLabel3(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(26)
			.addComponent(productScrollPane, 0, 554, Short.MAX_VALUE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(getJButtonProductPanelProductNew(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(28, 28));
		productPanelLayout.setHorizontalGroup(productPanelLayout.createSequentialGroup()
			.addContainerGap(13, 13)
			.addGroup(productPanelLayout.createParallelGroup()
			    .addComponent(productScrollPane, GroupLayout.Alignment.LEADING, 0, 933, Short.MAX_VALUE)
			    .addGroup(GroupLayout.Alignment.LEADING, productPanelLayout.createSequentialGroup()
			        .addPreferredGap(productScrollPane, getJLabel3(), LayoutStyle.ComponentPlacement.INDENT)
			        .addComponent(getJLabel3(), GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
			        .addGroup(productPanelLayout.createParallelGroup()
			            .addComponent(getJFormattedTextFieldProductPanelProductNaam(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, productPanelLayout.createSequentialGroup()
			                .addGap(297)
			                .addComponent(getJButtonProductPanelProductNew(), GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
			                .addGap(58)))
			        .addGap(146)
			        .addComponent(getJButtonProductPanelProductSearch(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 159, Short.MAX_VALUE)))
			.addContainerGap(13, 13));
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
		return new String[] { "Nummer", "Naam", "Omschrijving", "Categorie",
				"Type" };
	}

	/**
	 * Generate Column content for all selected products
	 * 
	 * @return String[][]
	 */
	private String[][] getProductColumns(int[] columnWidth) {
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
			columns[i] = new String[] {
					product.getIdProd(),
					product.getProdCode(),
					product.getProdDesc(),
					CodeController.getOneCodeDetail(
							ProductController.PRODUCT_CATEGORY,
							product.getProdCat()).getCodeDesc(),
					CodeController.getOneCodeDetail(
							ProductController.PRODUCT_TYPE,
							product.getProdType()).getCodeDesc() };
			calculateColumnWidth(columns[i], columnWidth);
			i++;
		}
		return columns;
	}

	/**
	 * @return
	 */
	private String[] getProductFilter(String prodCode) {
		String[] filter = { prodCode, Constants.EMPTY, Constants.TRUE };
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
						@Override
						public void actionPerformed(ActionEvent evt) {
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
						@Override
						public void actionPerformed(ActionEvent evt) {
							{
								refillJTableProduct();
							}

						}
					});
		}
		return jButtonProductPanelProductSearch;
	}

	private void refillJTableProduct() {
		final int DEFAULT_PIXELS = 50;
		int[] columnWidth = new int[getProductColumnTitles().length];

		jTableProductsModel = new DefaultTableModel(
				getProductColumns(columnWidth), getProductColumnTitles());
		jTableProducts = new JTable();
		jTableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productScrollPane.setViewportView(jTableProducts);

		jTableProducts.setModel(jTableProductsModel);
		for (int i = 0; i < getProductColumnTitles().length; i++) {
			TableColumn column = jTableProducts.getColumnModel().getColumn(i);
			column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
		}
		jTableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableProducts.getTableHeader().setFont(
				new java.awt.Font("Dialog", 1, 12));
		jTableProducts.setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(jTableProducts, getJPopupMenuProductList());

	}

	private JPopupMenu getJPopupMenuProductList() {
		if (jPopupMenuProductList == null) {
			jPopupMenuProductList = new JPopupMenu();
			ButtonGroup productContext = new ButtonGroup();
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
		GroupLayout quotePanelLayout = new GroupLayout(quotePanel);
		quotePanel.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent evt) {
				resetQuotePanel();
			}

			@Override
			public void focusLost(FocusEvent evt) {
				resetQuotePanel();
			}

		});

		quotePanel.setLayout(quotePanelLayout);
		quoteScrollPane = new JScrollPane();
		quotePanelLayout.setVerticalGroup(quotePanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(quotePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(getJButtonQuotePanelQuoteSearch(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJComboBoxQuoteStatus(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJLabel1(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJFormattedTextFieldQuotesPanelCustomerName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
			    .addComponent(getJLabelQuoteStatus(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(0, 41, Short.MAX_VALUE)
			.addComponent(getReqDlvDate(), GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(quoteScrollPane, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
			.addGap(25)
			.addComponent(getJButtonQuotePanelQuoteNew(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(22, 22));
		quotePanelLayout.setHorizontalGroup(quotePanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(quotePanelLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, quotePanelLayout.createSequentialGroup()
			        .addComponent(getJLabel1(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			        .addGroup(quotePanelLayout.createParallelGroup()
			            .addComponent(getJFormattedTextFieldQuotesPanelCustomerName(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, quotePanelLayout.createSequentialGroup()
			                .addGap(36)
			                .addComponent(getReqDlvDate(), GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
			                .addGap(104)
			                .addComponent(getJButtonQuotePanelQuoteNew(), GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
			                .addGap(26)))
			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			        .addComponent(getJLabelQuoteStatus(), GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addComponent(getJComboBoxQuoteStatus(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			        .addGap(41)
			        .addComponent(getJButtonQuotePanelQuoteSearch(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 79, Short.MAX_VALUE))
			    .addComponent(quoteScrollPane, GroupLayout.Alignment.LEADING, 0, 947, Short.MAX_VALUE))
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
		return new String[] { "Nummer", "Klant", "Leverdatum", "Straat",
				"Huisnummer", "Postbus", "Postcode", "Gemeente" };
	}

	private String[][] getEmptyQuoteColumns() {

		return null;
	}

	/**
	 * @return
	 */
	private String[][] getQuoteColumns(String customerName, int[] columnWidth) {
		String[][] columns;
		QuoteView quote;
		String[] filter = getQuoteFilter();
		Collection<Business> list = QuoteController
				.getQuotesByCustomerName(filter);
		columns = new String[list.size()][];
		Iterator<Business> it = list.iterator();
		Address address;
		int i = 0;
		while (it.hasNext()) {
			quote = (QuoteView) it.next();
			address = AddressController.getAddress(quote.getQteDlvAddid());
			columns[i] = new String[] { quote.getIdQuote(), quote.getCusName(),
					quote.getQteReqDlvDate("dd-MM-yy"), address.getAddStreet(),
					address.getAddNumber(), address.getAddBox(),
					address.getAddZip(), address.getAddCity() };
			calculateColumnWidth(columns[i], columnWidth);
			i++;

		}
		return columns;
	}

	/**
	 * @return
	 */
	private String[] getQuoteFilter() {
		String customerName = getJFormattedTextFieldQuotesPanelCustomerName()
				.getText();
		Object desc = getJComboBoxQuoteStatus().getSelectedItem();
		String status = quoteStats.get(desc);
		String type = Constants.EMPTY;
		String[] filter = { customerName, status, type, Constants.TRUE };
		return filter;
	}

	private void refillJTableQuote(String cusID) {
		final int DEFAULT_PIXELS = 50;
		int[] columnWidth = new int[getQuoteColumnTitles().length];

		jTableQuotesModel = new DefaultTableModel(getQuoteColumns(cusID,
				columnWidth), getQuoteColumnTitles());
		jTableQuotes = new JTable();
		jTableQuotes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		quoteScrollPane.setViewportView(jTableQuotes);

		jTableQuotes.setModel(jTableQuotesModel);
		for (int i = 0; i < getQuoteColumnTitles().length; i++) {
			TableColumn column = jTableQuotes.getColumnModel().getColumn(i);
			column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
		}
		jTableQuotes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableQuotes.getTableHeader().setFont(
				new java.awt.Font("Dialog", 1, 12));
		jTableQuotes.setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(jTableQuotes, getJPopupMenuQuoteList());

	}

	private JPopupMenu getJPopupMenuQuoteList() {
		if (jPopupMenuQuoteList == null) {
			jPopupMenuQuoteList = new JPopupMenu();
			ButtonGroup quoteContext = new ButtonGroup();
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
		GroupLayout invoicePanelLayout = new GroupLayout(
				invoicePanel);
		invoicePanel.setLayout(invoicePanelLayout);
		invoiceScrollPane = new JScrollPane();
		invoicePanelLayout.setVerticalGroup(invoicePanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(invoicePanelLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			        .addComponent(getJComboBoxInvoiceStatus(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addComponent(getJFormattedTextFieldInvoicePanelCustomerName(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
			        .addComponent(getJLabel2(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addComponent(getJLabel4(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
			    .addGroup(invoicePanelLayout.createSequentialGroup()
			        .addGap(0, 24, Short.MAX_VALUE)
			        .addGroup(invoicePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			            .addComponent(getJButtonInvoicePanelCustomerSearch(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(getJComboBoxInvoiceType(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			            .addComponent(getJLabel6(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
			.addGap(19)
			.addComponent(invoiceScrollPane, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(getJButtonInvoicePanelInvoiceNew(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(25, 25));
		invoicePanelLayout.setHorizontalGroup(invoicePanelLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(invoicePanelLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
			        .addComponent(getJLabel2(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			        .addComponent(getJFormattedTextFieldInvoicePanelCustomerName(), GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
			        .addGap(104)
			        .addComponent(getJButtonInvoicePanelInvoiceNew(), GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
			        .addGap(68)
			        .addGroup(invoicePanelLayout.createParallelGroup()
			            .addComponent(getJLabel6(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
			                .addPreferredGap(getJLabel6(), getJLabel4(), LayoutStyle.ComponentPlacement.INDENT)
			                .addComponent(getJLabel4(), GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addGroup(invoicePanelLayout.createParallelGroup()
			            .addComponent(getJComboBoxInvoiceType(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, invoicePanelLayout.createSequentialGroup()
			                .addComponent(getJComboBoxInvoiceStatus(), GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			                .addGap(27)))
			        .addGap(36)
			        .addComponent(getJButtonInvoicePanelCustomerSearch(), GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 72, Short.MAX_VALUE))
			    .addComponent(invoiceScrollPane, GroupLayout.Alignment.LEADING, 0, 926, Short.MAX_VALUE))
			.addContainerGap(27, 27));
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
		return new String[] { "Nummer", "Klant", "Factuurdatum", "Vervaldatum", "Vervallen", "Straat",
				"Huisnummer", "Postbus", "Postcode", "Gemeente" };
	}

	private String[][] getEmptyInvoiceColumns() {

		return null;
	}

	/**
	 * @return
	 */
	private Object[][] getInvoiceColumns(String customerName, int[] columnWidth) {
		Object[][] columns;
		InvoiceView invoice;
		String[] filter = getInvoiceFilter();
		Collection<Business> list = InvoiceController
				.getInvoicesByCustomerName(filter);
		columns = new Object[list.size()][];
		Iterator<Business> it = list.iterator();
		Address address;
		int i = 0;
		jTableInvoices.setDefaultRenderer(JLabel.class, dateRenderer);
//		jTableInvoices.setDefaultRenderer(JLabel.class, dateCellRenderer);
		while (it.hasNext()) {
			invoice = (InvoiceView) it.next();
			address = AddressController.getAddress(invoice.getInvAddid());
//			dateRenderer.setValue(new java.util.Date(invoice.getInvDueDate().getTimeInMilliSeconds())); //"dd-MM-yy"
			columns[i] = new Object[] { invoice.getIdInvoice(),
					invoice.getCusName(), invoice.getInvDate("dd-MM-yy"),
//					dateCellRenderer.getTableCellRendererComponent(jTableInvoices, 
//							invoice.getInvDueDate().toString(SEPARATOR), false, false, i, VALIDATION_COLUMN),
					invoice.getInvDueDate("dd-MM-yy"),
					
					invoiceExpired(invoice),
//					new Boolean(invoice.getInvDueDate().kleinerDan(toDay)),
					address.getAddStreet(), address.getAddNumber(),
					address.getAddBox(), address.getAddZip(),
					address.getAddCity() };
			calculateColumnWidth(columns[i], columnWidth);
			i++;
		}
		return columns;
	}

	private String invoiceExpired(Invoice invoice){
		if(invoice.getInvStatus().equals(FixTypes.INVOICE_STATUS_CONFIRMED)){
			return Constants.PAYED;
		}

		if(invoice.getInvDueDate().kleinerDan(toDay)){
			return Constants.YES;
		}
		return Constants.EMPTY;
	}
		
	/**
	 * @return
	 */
	private String[] getInvoiceFilter() {
		String customerName = getJFormattedTextFieldInvoicePanelCustomerName()
				.getText();
		Object desc = getJComboBoxInvoiceStatus().getSelectedItem();
		String status = invoiceStats.get(desc);
		 desc = getJComboBoxInvoiceType().getSelectedItem();
		String type = invoiceTypes.get(desc);
		String[] filter = { customerName, status, type, Constants.TRUE };
		return filter;
	}

	/**
	 * @param cusID
	 */
	private void refillJTableInvoice(String cusID) {
		final int DEFAULT_PIXELS = 50;
		int[] columnWidth = new int[getInvoiceColumnTitles().length];

		jTableInvoicesModel = new DefaultTableModel(getInvoiceColumns(cusID,
				columnWidth), getInvoiceColumnTitles());
		jTableInvoices = new JTable();
		jTableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		invoiceScrollPane.setViewportView(jTableInvoices);

		jTableInvoices.setModel(jTableInvoicesModel);
		for (int i = 0; i < getInvoiceColumnTitles().length; i++) {
			TableColumn column = jTableInvoices.getColumnModel().getColumn(i);
			column.setPreferredWidth(columnWidth[i] * DEFAULT_PIXELS);
		}
		jTableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableInvoices.getTableHeader().setFont(
				new java.awt.Font("Dialog", 1, 12));
		jTableInvoices.setToolTipText("Klik om te wijzigen");
		setComponentPopupMenu(jTableInvoices, getJPopupMenuInvoiceList());

	}

	private JPopupMenu getJPopupMenuInvoiceList() {
		if (jPopupMenuInvoiceList == null) {
			jPopupMenuInvoiceList = new JPopupMenu();
			ButtonGroup invoiceContext = new ButtonGroup();
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
			public void mousePressed(java.awt.event.MouseEvent e) {
				// if(e.isPopupTrigger()
				menu.show(parent, e.getX(), e.getY());
				JTable table = (JTable) e.getSource();
				table.clearSelection();
				Point p = e.getPoint();
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
						@Override
						public void actionPerformed(ActionEvent e) {

							String id = (String) jTableCustomersModel
									.getValueAt(row, 0);// customer number
							Customer customer = CustomerController
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
						@Override
						public void actionPerformed(ActionEvent e) {

							String code = (String) jTableCodeDetailsModel
									.getValueAt(row, 0);// code
							String det = (String) jTableCodeDetailsModel
									.getValueAt(row, 1);// det
							CodeDetail codeDet = CodeController
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
						@Override
						public void actionPerformed(ActionEvent e) {
							String desc = (String) getJTableNumberDetailModel()
							 .getValueAt(row, 0);// category
							String category = (String) getNbrCategories().get(
							desc);

							int year = Integer.parseInt((String) getJTableNumberDetailModel()
								.getValueAt(row, 1));// year
							Number current = NumberController.readOneNumber(category, year);
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
						@Override
						public void actionPerformed(ActionEvent e) {

							String id = (String) jTableQuotesModel.getValueAt(
									row, 0);// quote
							Quote quote = QuoteController.getQuote(id);
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
						@Override
						public void actionPerformed(ActionEvent e) {

							String id = (String) jTableProductsModel
									.getValueAt(row, 0);// product number
							Product product = ProductController.getProduct(id);
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
						@Override
						public void actionPerformed(ActionEvent e) {
							String id = (String) jTableInvoicesModel
									.getValueAt(row, 0);// invoice
							Invoice invoice = InvoiceController.getInvoice(id);
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
						@Override
						public void actionPerformed(ActionEvent e) {
							// number
							String cusID = (String) jTableCustomersModel
									.getValueAt(row, 0);// customer
							Customer temp = CustomerController
									.getCustomer(cusID);
							if (!(InvoiceController.getInvoices(new String[] {
									cusID, Constants.EMPTY, Constants.EMPTY, Constants.TRUE })).isEmpty()) {

								JOptionPane.showMessageDialog(frame, temp
										.getCusName()
										+ Constants.INVOICES_EXIST);
							} else {
								int response = JOptionPaneItemRemove
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
						@Override
						public void actionPerformed(ActionEvent e) {
							// number
							String code = (String) jTableCodeDetailsModel
									.getValueAt(row, 0);// code
							String detail = (String) jTableCodeDetailsModel
									.getValueAt(row, 1);// detail

							int response = JOptionPaneItemRemove.confirm(code
									+ ", " + detail);
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
						@Override
						public void actionPerformed(ActionEvent e) {
							// number
							String desc = (String) jTableNumberDetailsModel
									.getValueAt(row, 0);
							String category = (String) getNbrCategories().get(
									desc);
							String strYear = (String) jTableNumberDetailsModel
									.getValueAt(row, 1);

							int response = JOptionPaneItemRemove.confirm(desc
									+ ", " + strYear);
							if (response == JOptionPane.YES_OPTION) {
								NumberController.removeNumber(category, Integer
										.parseInt(strYear));
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
						@Override
						public void actionPerformed(ActionEvent e) {
							// number
							String id = (String) jTableQuotesModel.getValueAt(
									row, 0);// quotes
							Quote temp = QuoteController.getQuote(id);
							if (temp.getQteStatus().equals(
									QuoteController.QUOTE_STATUS_CONFIRMED)) {
								JOptionPane.showMessageDialog(frame, temp
										.getIdQuote()
										+ Constants.QUOTE_CONFIRMED);
							} else {
								int response = JOptionPaneItemRemove
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
						@Override
						public void actionPerformed(ActionEvent e) {
							String id = (String) jTableInvoicesModel
									.getValueAt(row, 0);// invoices
							Invoice temp = InvoiceController.getInvoice(id);
							if (temp.getInvType().equals(FixTypes.CREDIT_NOTE)) {

								JOptionPane.showMessageDialog(frame, id
										+ Constants.CREDIT_NOTE_NOT_ALLOWED);
							} else {
								int response = JOptionPaneItemRemove
										.confirm(id);
								if (response == JOptionPane.YES_OPTION) {
									Invoice newCreditNote = InvoiceController
									.createNewCreditNote(temp);
									InvoiceDetailController.createNewCreditNoteDetails(
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
						@Override
						public void actionPerformed(ActionEvent e) {
							// number
							String prodID = (String) jTableProductsModel
									.getValueAt(row, 0);// product
							if (!(QuoteDetailController
									.getQuotesByProductId(new String[] {
											prodID, Constants.TRUE })).isEmpty()) {

								JOptionPane.showMessageDialog(frame, prodID
										+ Constants.QUOTES_EXIST);
							} else {
								int response = JOptionPaneItemRemove
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
						@Override
						public void actionPerformed(ActionEvent evt) {
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
				@Override
				public void actionPerformed(ActionEvent evt) {
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
						@Override
						public void actionPerformed(ActionEvent evt) {
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
						@Override
						public void actionPerformed(ActionEvent evt) {
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
			jFormattedTextFieldInvoicePanelCustomerName.setText(Constants.EMPTY);
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

	@SuppressWarnings( { "rawtypes", "unchecked" })
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

	@SuppressWarnings( { "rawtypes", "unchecked" })
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
		if(jComboBoxInvoiceType == null) {
			invoiceTypes = CodeController
			.getCodeDetails(CodeEnum.INVOICE_TYPE.getType());// Invoice Type'

			 jComboBoxInvoiceTypeModel = 
				new DefaultComboBoxModel(invoiceTypes
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
			BorderLayout jInternalFrameCodeLayout = new BorderLayout();
			jInternalFrameCode.getContentPane().setLayout(
					jInternalFrameCodeLayout);
			jInternalFrameCode.setVisible(true);
			jInternalFrameCode.setBounds(1, 1, 720, 272);
			jInternalFrameCode.setPreferredSize(new java.awt.Dimension(720, 272));
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
			BorderLayout jInternalFrameNumberLayout = new BorderLayout();
			jInternalFrameNumber.getContentPane().setLayout(
					jInternalFrameNumberLayout);
			jInternalFrameNumber.setVisible(true);
			jInternalFrameNumber.setBounds(1, 336, 720, 240);
			jInternalFrameNumber.setPreferredSize(new java.awt.Dimension(720, 240));
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
			BorderLayout jPanelCenterPanelNumberLayout = new BorderLayout();
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
			BorderLayout jPanelCenterPanelCodeLayout = new BorderLayout();
			jPanelCenterPanelCode.setLayout(jPanelCenterPanelCodeLayout);
			jPanelCenterPanelCode.setBorder(new SoftBevelBorder(
					BevelBorder.LOWERED, null, null, null, null));
			JPanel codeHeaderPanel = new JPanel();
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
				@Override
				public void actionPerformed(ActionEvent evt) {
					 new JDialogNumber(frame, null,
					 CRUDOperationEnum.NEW);
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
				@Override
				public void actionPerformed(ActionEvent evt) {
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
			Collection<Business> list = CodeController.getAllCodeHeaders();
			// CodeHeaders
			Iterator<Business> it = list.iterator();
			CodeHeader codes[] = new CodeHeader[list.size()];
			int i = 0;
			while (it.hasNext()) {
				codes[i] = (CodeHeader) it.next();
				i++;
			}
			jComboBoxCodeHeaderModel = new DefaultComboBoxModel(list.toArray());
			jComboBoxCodeHeader = new JComboBox();
			jComboBoxCodeHeader.setModel(jComboBoxCodeHeaderModel);
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

	private TableModel getJTableCodeDetailsModel(String id) {
		jTableCodeDetailsModel = new DefaultTableModel(
				getCodeDetailColumns(id), getCodeDetailTitles());
		return jTableCodeDetailsModel;
	}

	/**
	 * @return
	 */
	private String[] getCodeDetailTitles() {
		return new String[] { "Id", "Code", "Omschrijving" };
	}

	/**
	 * @return
	 */
	private String[][] getCodeDetailColumns(String id) {
		int[] columnWidth = new int[getInvoiceColumnTitles().length];

		String[][] columns;
		CodeDetail detail;
		Collection<Business> list = CodeController.getAllCodeDetails(id);
		columns = new String[list.size()][];
		Iterator<Business> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			detail = (CodeDetail) it.next();
			columns[i] = new String[] { detail.getIdCode(),
					detail.getCodeDet(), detail.getCodeDesc() };
			calculateColumnWidth(columns[i], columnWidth);
			i++;
		}
		return columns;
	}

	/**
	 * @return
	 */
	private String[] getNumberHeaderTitles() {
		return new String[] { "Categorie", "Vanaf", "Start nummer",
				"Laatste nummer" };
	}

	/**
	 * @return
	 */
	private String[][] getNumberHeaderColumns() {
		int[] columnWidth = new int[getInvoiceColumnTitles().length];

		String[][] columns;
		Number number;
		Collection<Business> list = NumberController.getAllNumbers();
		columns = new String[list.size()][];
		Iterator<Business> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			number = (Number) it.next();
			columns[i] = new String[] {
					CodeController.getOneCodeDetail(CodeEnum.NUMBER.getType(),
							number.getNbrCategory().trim()).getCodeDesc(),
					number.getNbrYear().toString(), number.getNbrStrValue().trim(),
					number.getNbrLstValue().trim() };
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
		if(jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Type :");
		}
		return jLabel6;
	}
}
