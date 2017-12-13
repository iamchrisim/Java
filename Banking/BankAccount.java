
//Dohoo Im//  

public abstract class BankAccount {

	int accountNo;
	String account_name;
	double accnt_balance;
	
	public BankAccount(int accountnum, String owner, double balance)	{

		this.accountNo = accountnum;
		this.account_name = owner;
		this.accnt_balance = balance;
	}

	public int getAccnt_id()
	{
		return accountNo;
	}
	public String getAccount_Name()
	{
		return account_name;
	}
	public void deposit(double a_amt)
	{
		accnt_balance= accnt_balance + a_amt;

		System.out.printf("\nAccount deposited with $%.2f", a_amt);
	}

	public abstract void withdraw(double a_amt);

}