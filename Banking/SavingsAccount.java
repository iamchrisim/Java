
//Dohoo Im//

public class SavingsAccount extends BankAccount	{

	public SavingsAccount(int accountnum, String owner, double balance)
	{
		super(accountnum, owner, balance);
	}
	public void withdraw(double a_amt)
	{
		if(a_amt <= accnt_balance)
		{
			accnt_balance = accnt_balance - a_amt;

			System.out.printf("\nSucessfully withdrown from Savings $%.2f", a_amt);
		}
		else
		{
			System.out.println("\nInsufficient Funds Exception");
		}
	}
	public void displayAmount()
	{
		System.out.printf("\nThe savings balance is: "+"$"+ accnt_balance);
	}

}