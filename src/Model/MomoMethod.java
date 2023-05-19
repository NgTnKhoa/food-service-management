package Model;

public class MomoMethod implements PaymentMethod  {
	private String name;
	private String phoneNumber;
	public MomoMethod(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public double payment(double amount) {
		// TODO Auto-generated method stub
		return (double)Math.round(amount*(1-0.04) * 1000) / 1000;
		
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "(Discount 4%)";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
