package view;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Business;
import model.BusinessTypeEnum;
import model.Customer;
import persistency.controller.CodeController;
import persistency.controller.CustomerController;
import utilities.CRUDOperationEnum;
import utilities.CodeEnum;
import utilities.FixTypes;

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
public class JWindowCustomer extends JDialog {
	private JTable jTableCustomerLookup;
	private TableModel jTableCustomerLookupModel;
	private JScrollPane jScrollPane1;
	private JPanel jPanelCustomerSearch;
	private JTextField jTextFieldCustomerName;
	private JLabel jLabelCustomerName;

	private JFrame parent;
	private BusinessTypeEnum type;
	

	public JWindowCustomer(JFrame parent, BusinessTypeEnum type) {
		super(parent, true);
		this.parent = parent;
		this.type = type;
		initGUI();
	}

	private void initGUI() {
		try {
			{

				this.setSize(321, 276);
				this.setPreferredSize(new java.awt.Dimension(320, 320));
				{
					jPanelCustomerSearch = new JPanel();
					getContentPane().add(jPanelCustomerSearch,
							BorderLayout.NORTH);
					{
						jLabelCustomerName = new JLabel();
						jPanelCustomerSearch.add(jLabelCustomerName);
						jLabelCustomerName.setText("Klant naam :");
					}
					{
						jTextFieldCustomerName = new JTextField();
						jPanelCustomerSearch.add(jTextFieldCustomerName);
						jTextFieldCustomerName
								.setPreferredSize(new java.awt.Dimension(207,
										20));
						jTextFieldCustomerName
								.addKeyListener(new KeyAdapter() {
									@Override
									public void keyTyped(KeyEvent e) {
										refillCustomerView(true);
									}
								});
					}
				}

				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, BorderLayout.CENTER);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(321,
							276));
					// refillCustomerView(false);
				}
				setLocationRelativeTo(parent);
				setFocusable(true);
				setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void refillCustomerView(boolean refill) {
		{
			if (refill == false) {
				jTableCustomerLookupModel = new DefaultTableModel(null,
						getCustomerColumnTitles());

			} else {
				jTableCustomerLookupModel = new DefaultTableModel(
						getCustomerColumns(), getCustomerColumnTitles());
				jTableCustomerLookup = new JTable();
				jScrollPane1.setViewportView(jTableCustomerLookup);
				jTableCustomerLookup.setModel(jTableCustomerLookupModel);
				jTableCustomerLookup.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent evt) {
						String id = ((String) getModel().getValueAt(
								jTableCustomerLookupMousePressed(evt), 0));// customer
						// number
						Customer customer = CustomerController.getCustomer(id);
						if (type == BusinessTypeEnum.QUOTE) {
							new JDialogQuote(parent, customer,
									CRUDOperationEnum.NEW);
						}
						if (type == BusinessTypeEnum.INVOICE) {
							 new JDialogInvoice(parent, customer,
										CRUDOperationEnum.NEW);
						}

					}

					private int jTableCustomerLookupMousePressed(MouseEvent evt) {
						int row;
						JTable table = (JTable) evt.getSource();
						table.clearSelection();
						Point p = evt.getPoint();
						row = table.rowAtPoint(p);
						return row;
					}
				});
			}
		}
	}

	private String[] getCustomerColumnTitles() {
		return new String[] { "Nummer", "Naam", "Type", "BTW" };
	}

	private String[][] getCustomerColumns() {
		String[][] columns;
		Customer customer;
		String[] filter = getCustomerFilter();
		Collection<Business> list = CustomerController.getCustomers(filter);
		columns = new String[list.size()][];//Head company will not be shown
		Iterator<Business> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			customer = (Customer) it.next();
			if(!customer.getCusType().equals(FixTypes.HEAD_COMPANY)){
				columns[i++] = new String[] {
					customer.getIdCus(),
					customer.getCusName(),
					CodeController.getOneCodeDetail(
							CodeEnum.CUSTOMER_TYPE.getType(),
							customer.getCusType()).getCodeDesc(),
					customer.getCusVat() };
			}
		}
		return columns;
	}

	private String[] getCustomerFilter() {
		String[] filter = { jTextFieldCustomerName.getText(), "", "true" };
		return filter;
	}

	protected JTable getModel() {
		return jTableCustomerLookup;
	}
}
