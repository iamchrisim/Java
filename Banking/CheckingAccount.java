
//Dohoo Im//

public class CheckingAccount extends BankAccount {

	Customer a_cust;

	public CheckingAccount(int account_No, String account_name,double accnt_balance) {

		super(account_No, account_name, accnt_balance);
	}
	public void withdraw(double a_amt) {

		
		if(a_amt <= accnt_balance)
		{
			accnt_balance = accnt_balance - a_amt;
			System.out.printf("\nSucessfully withdrown from Savings $%.2f",a_amt);
		}
		else
		{
			boolean flag12=a_cust.transferMoney(this, a_amt);
			if(!flag12)
			System.out.println("\nInsufficient Funds Exception");
		}
	}
	
	public void displayAmount()
	{
		System.out.printf("\nThe checking balance is: " + "$"+accnt_balance);
	}

}