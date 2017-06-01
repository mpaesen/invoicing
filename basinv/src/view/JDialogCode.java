package view;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.CodeDetail;
import persistency.controller.CodeController;
import utilities.CRUDOperationEnum;
import utilities.Constants;

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
public class JDialogCode extends JDialog {
	private JFrame parent;

	private JPanel jPanelCodeSouth;
	private JPanel jPanelCodeNorth;

	private JLabel jLabelCodeDesc;
	private JLabel jLabelCodeDet;
	private JLabel jLabel1;

	private JTextField jTextFieldIdCode;
	private JTextField jTextFieldCodeDesc;
	private JTextField jTextFieldCodeDet;

	private JButton jButtonCodeSave;

	private JTextArea jTextAreaMessage;

	private CodeDetail code;

	private String codeID = null;

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

	public JDialogCode(JFrame frame, String codeID, CodeDetail code,
			CRUDOperationEnum operation) {
		this(frame, true, operation);
		this.code = code;
		this.codeID = codeID;
		initGUI();
	}

	private JDialogCode(JFrame frame, boolean modal, CRUDOperationEnum operation) {
		super(frame, modal);
		this.operation = operation;
		this.parent = frame;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			createNorthPanel();
			createSouthPanel();
			this.setSize(544, 193);
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
		jPanelCodeSouth = new JPanel();
		getContentPane().add(jPanelCodeSouth, BorderLayout.SOUTH);
		jPanelCodeSouth.setPreferredSize(new java.awt.Dimension(354, 56));
		jPanelCodeSouth.add(getJButtonCodeSave());
		getJButtonCodeSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				StringBuilder errorMessages = new StringBuilder(Constants.EMPTY);
				if (validateInput(errorMessages)) {
					saveInput();
					initializeCodeFields();
				}
			}
		});
		jPanelCodeSouth.add(getJTextAreaMessage());
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
		validateCode(errorMessages);
		if (!errorMessages.toString().equals(Constants.EMPTY)) {
			redMessage(errorMessages);
			return false;
		}
		return true;
	}

	private void validateCode(StringBuilder errorMessages) {
		resetMessage();
		if (jTextFieldCodeDesc.equals(Constants.EMPTY)) {
			errorMessages.append(Constants.NO_DESCRIPTION + "\n");
		}
		if (jTextFieldCodeDet.equals(Constants.EMPTY)) {
			errorMessages.append(Constants.NO_CODE + "\n");
		}
		if (operation == CRUDOperationEnum.NEW) {
			CodeDetail temp = CodeController.getOneCodeDetail(codeID,
					jTextFieldCodeDet.getText());
			if (temp != null) {
				errorMessages.append(Constants.CODE_EXIST + "\n");
			}
		}
	}

	synchronized private void saveInput() {

		if ((code == null) && (operation == CRUDOperationEnum.NEW)) {
			CodeDetail newCode = createNewCode();
			CodeController.createCode(newCode);
			code = new CodeDetail(newCode);
			greenMessage(newCode.getCodeDet() + " werd Toegevoegd");
			// ones created, updates are allowed
			operation = CRUDOperationEnum.UPDATE;
		} else {
			if (operation == CRUDOperationEnum.UPDATE) {
				CodeDetail newCode = updateExistingCode();

				if (!code.equals(newCode)) {
					CodeController.updateCode(newCode);
					code = new CodeDetail(newCode);
					greenMessage(newCode.getCodeDet() + " werd gewijzigd");
				}
			}
		}
		// All modifications should be shown real-time
		initializeCodeFields();
	}

	/**
	 * @param deliveryDate
	 * @return
	 */
	private CodeDetail updateExistingCode() {
		String codeDesc = jTextFieldCodeDesc.getText();
		String codeDet = jTextFieldCodeDet.getText();

		CodeDetail newCode = new CodeDetail(code.getIdCode(), codeDet,
				codeDesc, code.isActive());
		return newCode;
	}

	/**
	 * @param deliveryDate
	 * @return
	 */
	private CodeDetail createNewCode() {
		String codeDet = jTextFieldCodeDet.getText();
		String codeDesc = jTextFieldCodeDesc.getText();

		CodeDetail newCode = new CodeDetail(codeID, codeDet, codeDesc, true);
		return newCode;
	}

	/**
	 * 
	 */
	private JButton getJButtonCodeSave() {
		if (jButtonCodeSave == null) {
			jButtonCodeSave = new JButton();
			jButtonCodeSave.setText("OK");
			jButtonCodeSave.setToolTipText("Gegevens bewaren");
		}
		return jButtonCodeSave;
	}

	private void createNorthPanel() {
		jPanelCodeNorth = new JPanel();
		TableLayout jPanel2Layout = new TableLayout(new double[][] {
				{ 100.0, 90.0, 51.0, TableLayout.FILL }, { 26.0, 26.0, 28.0 } });
		jPanel2Layout.setHGap(5);
		jPanel2Layout.setVGap(5);
		jPanelCodeNorth.setLayout(jPanel2Layout);
		getContentPane().add(jPanelCodeNorth, BorderLayout.NORTH);
		jPanelCodeNorth.setPreferredSize(new java.awt.Dimension(362, 103));
		{
			jLabelCodeDesc = new JLabel();
			jPanelCodeNorth.add(jLabelCodeDesc, "0, 2");
			jLabelCodeDesc.setText("Omschrijving :");
		}

		initializeCodeFields();
		jPanelCodeNorth.add(getJTextFieldCodeDesc(), "1, 2, 3, 2");
		jPanelCodeNorth.add(getJTextFieldIdCode(), "1, 0");
		jPanelCodeNorth.add(getJLabelCodeDet(), "0, 0");
		jPanelCodeNorth.add(getJTextFieldCodeDet(), "1, 1");
		jPanelCodeNorth.add(getJLabel1(), "0, 1");
		jTextFieldIdCode.setFont(new java.awt.Font("Dialog", 1, 18));
	}

	private void initializeCodeFields() {
		if (code != null) {
			getJTextFieldIdCode().setText(code.getIdCode());
			getJTextFieldCodeDet().setText(code.getCodeDet());
			getJTextFieldCodeDet().setEditable(false);
			getJTextFieldCodeDesc().setText(code.getCodeDesc());
		} else {
			getJTextFieldIdCode().setText(codeID);
		}
	}

	public JButton getJButtonSave() {
		return jButtonCodeSave;
	}

	public JTextField getJTextFieldCodeDesc() {
		if (jTextFieldCodeDesc == null) {
			jTextFieldCodeDesc = new JTextField();
		}
		return jTextFieldCodeDesc;
	}

	public JTextField getJTextFieldIdCode() {
		if (jTextFieldIdCode == null) {
			jTextFieldIdCode = new JTextField();
			jTextFieldIdCode.setEditable(false);
		}
		return jTextFieldIdCode;
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

	public JLabel getJLabelCodeDet() {
		if (jLabelCodeDet == null) {
			jLabelCodeDet = new JLabel();
			jLabelCodeDet.setText("Code :");
		}
		return jLabelCodeDet;
	}

	public JTextField getJTextFieldCodeDet() {
		if (jTextFieldCodeDet == null) {
			jTextFieldCodeDet = new JTextField();
		}
		return jTextFieldCodeDet;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Detail :");
		}
		return jLabel1;
	}

}
