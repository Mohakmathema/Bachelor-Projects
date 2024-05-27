//name: Mohak Bhakta Mathema

package finalAssessment_JAVA;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
//import java.util.*; imports all of java.util 

public class Main {
public static void main(String[] args) { //main method
	
	//creating object called account1 and account2 from Account class :
    Account account1 = new Account("Jeffery", "Ting", 1	, 2000);
    Account account2 = new Account("Hiran", "Patel", 2, 1000);
    //displaying the balance of account1 and account2 using the .getBalance() getter method:
    System.out.println("Account 1 Original balance: " + account1.getBalance());
    System.out.println("Account 2 Original balance: " + account2.getBalance());
    
    //I am depositing money in account1 by calling deposit method also we display the amount:
    //adding integer value as argument to the parameter in the method creation:
    account1.deposit(250);
    System.out.println("Current Amount in Account "+account1.getAccountNum()+" is: "+account1.getBalance());
    
    //I am calling the withdraw method in account2 to withdraw a certain amount as money we input as argument to this method
    account2.withdraw(500);
    System.out.println("Current Amount in Account "+account2.getAccountNum()+" is: "+account2.getBalance());
    
    //creating object t from Transaction class:
    Transaction t = new Transaction();
    //method calling with parameters as account1, account2, amount:
    t.transfer(account1, account2, 250);
    //displaying balance after transfer of money
    System.out.println("Account 1 balance: " + account1.getBalance());
    System.out.println("Account 2 balance: " + account2.getBalance());
    
    //making linkedList with datatype/class of Account
    LinkedList<Account> accounts = new LinkedList<>();
    //file where the data are stored(.csv) format
    String file = "Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv";
    //object of ReadAccounts file parameter passed to ReadAccounts during instantiation
    ReadAccounts reader = new ReadAccounts(file);
    //try/catch exceptiion hendling:
    try(Scanner scanner = new Scanner(new File(file))) { //new File () creates a file object that points to url
    	//Creating a scanner object by passing File object to its constructor. File is called with var file
    	LinkedList<String> firstNames = reader.getFirstNames();
    	LinkedList<String> lastNames = reader.getLastNames();
    	LinkedList<Integer> accountList = reader.getAccounts();	
    	LinkedList<Integer> balanceList = reader.getBalances();
    	
    	//for loop where i=0 and iterates over indices of firstName assuming firstName, lastName, accNum, balance have same size containing corresponding data for each acc.
    	for(int i=0;i<firstNames.size();i++) {
    		String firstName = firstNames.get(i); //takes firstName at index i
    		String lastName = lastNames.get(i);	  //takes lastName at index i
    		int accountNum = accountList.get(i);  //takes accountnum  at index i
    		int balanceNum = balanceList.get(i);  //tales balance at index i
    		//accounts list stores the list of Account objects created with constructor
    		accounts.add(new Account(firstName,lastName,accountNum,balanceNum)); //new accounts obj using the data from above added to it
    	} //populating accounts list with Account obj 
    	System.out.println("Numbers of accounts read: "+accounts.size()); //displays number of account using .size() method to check the size of account
    	if(!accounts.isEmpty()) { //check if accounts list is not empty:
    		Account Account = accounts.get(0); //getting user/accounts from indexing here
//    		Account.deposit(100);
    		System.out.println("ACCOUNT DETAILS FOR: "+Account.getFirstName()+ " "+ Account.getLastName());
    		System.out.println("Name: "+Account.getFirstName()+ " "+ Account.getLastName());
    		System.out.println("Account Number: "+ Account.getAccountNum());
    		System.out.println("Balance: "+ Account.getBalance());
    	}
    	else { //if accounts list is empty:
    		System.out.println("No Accounts have been found");
    	}
    }
    catch(Exception E) { //exception handling if try does not work
    	System.out.println("Error: "+E);
    }
    
    //displaying GUI when Main is run
    //creating object of GUI
    GUI gui = new GUI();
    gui.setVisible(true);  //making GUI visible when this Main class is run
}
}

class Customer{ 
	
	//variables that are private to make them invisible outside this class:
	//making private simply means using a pillar of OOP called ENCAPSULATION.
	
	private String firstName;
	private String lastName;
	
	//making a public Customer CONSTRUCTOR . this can be accessed by any class:
	public Customer(String FName,String LName) {
		this.firstName = FName;
		this.lastName = LName;
	}
	
	//adding a getter method to get the firstName and the lastName:
	//getter methods have a return type, in my case it's a String.
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	//adding a setter method to set the values of firstName and lastName:
	//setter methods are used to set values to the private variables so they have no return type.
	
	public void setFirstName(String FName) {
		this.firstName=FName;
	}
	public void setLastName(String LName) {
		this.lastName=LName;
	}
}
//using extends keyword to inherit Customer to the class Account(Single Inheritance):
class Account extends Customer{
	
	//again setting private keyword to encapsulate it:
	private int balance;
	private int accountNumber;
	
	//making constructor for the Account class:
	public Account(String FName,String LName, int accNum, int accBal){
		
		//super keyword is used to access the values from the parent class:
		super(FName,LName);
		this.accountNumber = accNum;
		this.balance = accBal;
	}
	
	//getter method to get the balance and account number from the user:
	public int getBalance() {
		return balance;	
	}
	public int getAccountNum() {
		return accountNumber;
	}
	
	//creating methods with void(no) return type for deposit and withdraw:
	public void deposit(int amount) {
		//this will add the amount deposited to the account into the balance:
		this.balance += amount;
	}
	public void withdraw(int amount) {
		//i am adding this condition(if/else) statement to check if the balance in account is greater than the withdraw amount
		//yes = no balance displayed
		//no = amount decreased from account
		if(amount>balance) {
			System.out.println("Insufficient Balance, Please Input Amount Within Your Balance");
		}
		else {
			this.balance -= amount;
		}
	}
}

//this class is used for transferring money from one account to the other:
class Transaction{
	//method transfer using custom data type of Account:
	public void transfer(Account acc1, Account acc2, int amount) {
		//if/else statement to compare balance with transfer amount
		if(acc1.getBalance()<amount) {
			System.out.println("Insufficient Balance in Account: "+acc1.getAccountNum());
		}
		else {
			acc1.withdraw(amount);
			acc2.deposit(amount);
			System.out.println("Amount of "+amount+" has been successfully transferred from Account: "+acc1.getAccountNum()+" to Account: "+acc2.getAccountNum());
		}
	}
}

//ReadAccounts to read accounts from csv file
class ReadAccounts{
	
//	public BufferedReader reader; //read data from files, character streams like lists
	private String url;
	
	//constructor of ReadAccounts
	public ReadAccounts(String URL) {
		this.url=URL;
	}
	//making a linkedlist  to get the firstNames from the file
	public LinkedList<String> getFirstNames() throws Exception{  //specifies exception without handeling them calling method are to handle them
		//calling method is main
		LinkedList<String> firstNames = new LinkedList<>();
		Scanner scanner = new Scanner(new File(url));//new File () creates a file object that points to url
		//this takes all the firstNames as they are stored in 0 index
		while(scanner.hasNextLine()) {
			String line  = scanner.nextLine();
			String data[] = line.split(",");
			firstNames.add(data[0]);
			
		}
		scanner.close();	
		return firstNames;
		
	}
	//making a linkedlist  to get the lastNames from the file
	public LinkedList<String> getLastNames() throws Exception{ //specifies exception without handeling them  method does that
		//calling method is main
		LinkedList<String> lastNames = new LinkedList<>();
		Scanner scanner = new Scanner(new File(url));//new File () creates a file object that points to url
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String data[] = line.split(",");
			lastNames.add(data[1]); //lastNames are stored in the 1 index of the array
		}
		scanner.close();//closing scanner as good practice
		return lastNames;
	}
	//linkedlist to get the Account numbers
	public LinkedList<Integer> getAccounts() throws Exception{ //specifies exception without handeling them
		//calling method is main
		LinkedList<Integer> accountList = new LinkedList<>();
		Scanner scanner = new Scanner(new File(url));//new File () creates a file object that points to url
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String data[] = line.split(",");
			accountList.add(Integer.parseInt(data[2])); //account numbers are stored in the 2 index of array
		}
		scanner.close(); 
		return accountList;
	}
	//linkedlist to get the balance or amount in  a account
	public LinkedList<Integer> getBalances() throws Exception{ //specifies exception without handeling them method does that
		//calling method is main
		LinkedList<Integer> balanceList = new LinkedList<>();
		Scanner scanner = new Scanner(new File(url)); //new File () creates a file object that points to url
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String data[] = line.split(",");
			balanceList.add(Integer.parseInt(data[3])); //balance is stored in the 3 index of the array
		}
		scanner.close();
		return balanceList;
	}
}
