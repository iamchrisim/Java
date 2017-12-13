import java.util.ArrayList;

//Dohoo Im//

public class Customer extends Bank	{
	
	private int cust_id;
	private SavingsAccount savingsAccnt;
	private CheckingAccount checkingAcct;
	String cust_name;
	
	ArrayList<SavingsAccount> savingAccnt=new ArrayList<SavingsAccount>();
	ArrayList<CheckingAccount> checkingAccnt=new ArrayList<CheckingAccount>();

	public Customer(int id, SavingsAccount savingsAcc)
	{
		cust_id = id;
		savingsAccnt = savingsAcc;
	}
	public Customer(int id, CheckingAccount checkingAcc)
	{
		cust_id = id;
		checkingAcct = checkingAcc;
	}
	public void setSavingAccount(SavingsAccount sav_accnt)
	{
		savingsAccnt = sav_accnt;
	}
	public void setCheckingAccount(CheckingAccount chk_accnt)
	{
		checkingAcct = chk_accnt;
	}
  
	public SavingsAccount addSavingsAccount()
	{
		return savingsAccnt;
	}
	public CheckingAccount addCheckingAccount()
	{
		return checkingAcct;
	}

	//BOOLEAN METHOD WITHDRAW MONEY FROM SavingsAccount FOR THE CheckingAccount
	public boolean transferMoney(CheckingAccount chk_accnt, double a_amt)
	{
		boolean isMoneyTransfer=false;
		for(SavingsAccount sa:savingAccnt)
		{
			if(sa.getAccnt_id()==chk_accnt.getAccnt_id())
			{
				sa.withdraw(a_amt);
				isMoneyTransfer=true;
			}
		}
		return isMoneyTransfer;
	}
}
