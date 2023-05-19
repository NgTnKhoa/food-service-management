package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;

import Model.Combo;
import Model.Drink;
import Model.NetManagement;
import View.Frame;

public class ChooseDrinkController {
	private Frame frame;
	private NetManagement netManagement = NetManagement.getInstance();
	private ActionListener chooseDrinkController, chooseDrinkControllerForDrink, confirmControllerForDrink,
			confirmControllerForCombo;

	public ChooseDrinkController(Frame frame) {
		this.frame = frame;
		chooseDrinkController = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.subDrinkFrame.setVisible(true);
				frame.nameCombo = e.getActionCommand();

			}
		};
		confirmControllerForCombo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmForCombo();
			}
		};
		confirmControllerForDrink = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmForDrink();
			}
		};
		chooseDrinkControllerForDrink = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkDrink(e.getActionCommand());
				frame.subDrinkFrame.setVisible(true);
				frame.nameCombo = e.getActionCommand();
			}
		};
	}

	public void checkDrink(String actioncommand) {
		// setEnable false cho tat ca check box
		frame.getSubDrinkFrame().drinkPanel.setEnabled(false);
		for (int i = 0; i < frame.getSubDrinkFrame().drinkPanel.getComponentCount(); i++) {
			frame.getSubDrinkFrame().drinkPanel.getComponent(i).setEnabled(false);
		}
		// select drink 
		Enumeration<AbstractButton> e = frame.getSubDrinkFrame().checkboxGroup_Drink.getElements();
		AbstractButton b = null;
		for (int i = 0; i <= frame.getSubDrinkFrame().checkboxGroup_Drink.getButtonCount(); i++) {
			if (e.hasMoreElements()) {
				b = e.nextElement();
				if (b.getName().equals(actioncommand)) {
					b.setSelected(true);
					frame.getSubDrinkFrame().searchDrink(actioncommand);
				}
			}

		}
	}
	// chon drink cua phan combo
	public void confirmForCombo() {
		// thay doi choose drink thanh 1 drink do khach hang chon
		Drink replacedDrink = frame.getSubDrinkFrame().addTopping();
		for (Combo combo : netManagement.getComboList()) {
			if (combo.getName().equals(frame.nameCombo)) {
				// Thay hinh ảnh thành nước vừa chọn 
				replacedDrink.setSrcImage(netManagement.searchDrink(replacedDrink.getName()).getSrcImage());
				combo.setDrink(replacedDrink);
				combo.setPrice(combo.getPriceCombo());
			}
		}
		// update lai Frame
		frame.getCardPanel_Combo().removeAll();
		// refresh de thể hiện sự thay đổi
		frame.refreshCarPanel_Combo();
		frame.getCardPanel_Combo().updateUI();
		frame.getSubDrinkFrame().dispose();
		// refresh để xóa cac lựa chọn check box
		frame.getSubDrinkFrame().refreshByConfirm();
	}
	// chon drink cua phan drink
	public void confirmForDrink() {
		// chon topping 
		Drink replacedDrink = frame.getSubDrinkFrame().addTopping();
		// sau khi chon topping xong --> tiến hành set lại giá
		for (int i = 0; i < netManagement.getDrinkList().size(); i++) {
		//	tìm nước để set lại giá
			if (netManagement.getDrinkList().get(i).getName().equals(frame.nameCombo)) {
				// tiến hành set giá
				netManagement.getDrinkList().get(i).setPrice((double) Math.round(replacedDrink.getPrice() * 100) / 100);
				break;
			}
		}
		
		frame.getCardPanel_Drink().removeAll();
		frame.refreshCardPanel_Drink();
		frame.getCardPanel_Drink().updateUI();
		frame.getSubDrinkFrame().dispose();
		frame.getSubDrinkFrame().refreshByConfirm();
	}

	public ActionListener getChooseDrinkControllerForDrink() {
		return chooseDrinkControllerForDrink;
	}

	public ActionListener getChooseDrinkController() {
		return chooseDrinkController;
	}

	public ActionListener getConfirmControllerForDrink() {
		return confirmControllerForDrink;
	}

	public ActionListener getConfirmControllerForCombo() {
		return confirmControllerForCombo;
	}

}
