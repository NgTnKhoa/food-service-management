package Model;

public class CashMethod implements PaymentMethod {

	@Override
	public double payment(double amount) {
		return (double) Math.round(amount * 1000) / 1000;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "(Discount 0%)";
	}

}
