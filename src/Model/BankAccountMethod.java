package Model;

public class BankAccountMethod implements PaymentMethod{
	private String name;
	private String cardNumber;
	
	public BankAccountMethod(String name, String cardNumber) {
		this.name = name;
		this.cardNumber = cardNumber;
	}

	@Override
	public double payment(double amount) {
		// TODO Auto-generated method stub
		return (double) Math.round(amount*(1-0.02) * 1000) / 1000;
	}

	@Override
	public String description() {
		return "(Discount 2%)";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	

}
