package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.BankAccountMethod;
import Model.CashMethod;
import Model.MomoMethod;
import Model.NetManagement;
import View.Frame;
import View.PaymentFrame;

public class PaymentController implements ActionListener, ItemListener {
	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();

	public PaymentController(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Confirm")) {
			if (frame.getPaymentFrame().getBa_RadioBtn().isSelected()) {
				frame.getPaymentFrame().setPaymentMethod("Bank Account");
				frame.getPaymentFrame().getBa_Frame().setVisible(true);
			} else if (frame.getPaymentFrame().getMomo_RadioBtn().isSelected()) {
				frame.getPaymentFrame().setPaymentMethod("Momo");
				frame.getPaymentFrame().getMomoFrame().setVisible(true);
			} else if (frame.getPaymentFrame().getCash_RadioBtn().isSelected()) {
				JOptionPane.showMessageDialog(null, "Order success!");
				frame.getPaymentFrame().setPaymentMethod("Cash");
				frame.getPaymentFrame().setVisible(false);
				Order_Cart();
			}
		} else if (e.getActionCommand().equals("Back")) {
			frame.getPaymentFrame().getBa_Frame().setVisible(false);
			frame.getPaymentFrame().getMomoFrame().setVisible(false);
		} else if (e.getActionCommand().equals("Confirm_ba")) {
			JOptionPane.showMessageDialog(null, "Order success!");
			frame.getPaymentFrame().setVisible(false);
			frame.getPaymentFrame().getBa_Frame().setVisible(false);
			Order_Cart();
		} else if (e.getActionCommand().equals("Confirm_momo")) {
			JOptionPane.showMessageDialog(null, "Order success!");
			frame.getPaymentFrame().getMomoFrame().setVisible(false);
			frame.getPaymentFrame().setVisible(false);
			Order_Cart();
		}
	}

	public void Order_Cart() {
		DefaultTableModel model = (DefaultTableModel) frame.history.getHistoryTable().getModel();
		for (java.util.Map.Entry<String, Double> entry : netManagement.getPriceHashMap().entrySet()) {
			if ((entry.getValue() + "").equals("0.0")) {

			} else {
				model.addRow(new Object[] { frame.getLblcurrentName().getText().substring(6), entry.getKey(),
						new java.util.Date() + "", entry.getValue(), frame.getPaymentFrame().getPaymentMethod() });
			}
		}
		frame.getScrollpanepanel().removeAll();
		netManagement.getCartItems().clear();
		netManagement.getPriceHashMap().clear();
		frame.getScrollpanepanel().updateUI();
		frame.getTextField_Amount().setText("");
		frame.getScrollpanepanel().setName(null);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (frame.getPaymentFrame().getBa_RadioBtn().isSelected()) {
			netManagement.setPaymentMethod(netManagement.getBankAccountMethod());
			frame.getPaymentFrame().getLbAmountBA().setText("Amount: "+netManagement.getDiscount() + "$");
			frame.getPaymentFrame().getLbAmountMomo().setText("");
			frame.getPaymentFrame().getLbAmountCash().setText("");
		} else if (frame.getPaymentFrame().getMomo_RadioBtn().isSelected()) {
			netManagement.setPaymentMethod(netManagement.getMomoMethod());
			frame.getPaymentFrame().getLbAmountMomo().setText("Amount: "+netManagement.getDiscount() + "$");
			frame.getPaymentFrame().getLbAmountCash().setText("");
			frame.getPaymentFrame().getLbAmountBA().setText("");
		} else if (frame.getPaymentFrame().getCash_RadioBtn().isSelected()) {
			netManagement.setPaymentMethod(new CashMethod());
			frame.getPaymentFrame().getLbAmountCash().setText("Amount: "+netManagement.getDiscount() + "$");
			frame.getPaymentFrame().getLbAmountMomo().setText("");
			frame.getPaymentFrame().getLbAmountBA().setText("");
		}
		netManagement.getPriceHashMap().clear();
		netManagement.getPriceHashMap().putAll(netManagement.getCartItems());
	}
}
