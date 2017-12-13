import java.util.ArrayList;

//Dohoo Im//

public class Bank {
	
	private ArrayList<BankAccount> accountList=new ArrayList<BankAccount>();
	private ArrayList<Customer> customerList=new ArrayList<Customer>();
	
	public void addCustomer(Customer cus)
	{
		customerList.add(cus);
	}
	public void addAccount(BankAccount acc)
	{
		accountList.add(acc);
	}
	public static void main(String[] args)
	{
		SavingsAccount savings = new SavingsAccount(1, "Chris", 1000);
		CheckingAccount checking = new CheckingAccount(1, "Chris", 1000);   
		Customer customer1 = new Customer(1, savings);
		Customer customer2 = new Customer(1, checking);
      
		Bank newcust = new Bank();
		newcust.addCustomer(customer1);
		newcust.addCustomer(customer2);
		
		customer1.addSavingsAccount().displayAmount();
		customer1.addSavingsAccount().deposit(100);
		customer1.addSavingsAccount().displayAmount();
		customer1.addSavingsAccount().withdraw(100);
		customer1.addSavingsAccount().displayAmount();
		customer1.addSavingsAccount().withdraw(10000);
		
		customer2.addCheckingAccount().displayAmount();
		customer2.addCheckingAccount().deposit(100);
		customer2.addCheckingAccount().displayAmount();
		customer2.addCheckingAccount().withdraw(200);
		customer2.addCheckingAccount().displayAmount();
		//customer2.addCheckingAccount().withdraw(1000);
		//customer2.addCheckingAccount().displayAmount();
		customer2.addCheckingAccount().withdraw(100);
		customer2.addCheckingAccount().displayAmount();

		}
}