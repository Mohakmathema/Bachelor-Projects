//name: Mohak Bhakta Mathema

package finalAssessment_JAVA;

import javax.swing.*; //create GUI components and handling events
import javax.swing.table.DefaultTableModel;

import java.awt.*; //color and layout management
import java.awt.event.*;
import java.io.*; //i/o operations

public class GUI extends JFrame {
	//adding components to the GUI using the Design tab
    private JTextField accountNumTextField;
    private JTextField amountTextField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JTextField senderAccountNumTextField;
    private JTextField receiverAccountNumTextField;
    private JTextField transferAmountTextField;
    private JButton transferButton;
    private JPanel tablePanel;
    private JButton showAllButton;
    private JScrollPane scrollPane;

    
    //constructor:
    public GUI() {
    	//GUI component:
    	setBackground(new Color(55, 55, 55));
    	setForeground(new Color(55, 55, 55));
        setTitle("Banking System");
        setSize(830, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//setting up inital components and properties like bg-color, adding components to GUI and set eventListeners
        initComponents();
        addComponents();
        addListeners();
        
        
    }
    
//initial components method:
    private void initComponents() {
    	//this initializes the GUI components
    	depositButton = new JButton("Deposit");
    	withdrawButton = new JButton("Withdraw");
    	transferButton = new JButton("Transfer");
    	
    	 amountTextField = new JTextField(10);
    	 transferAmountTextField = new JTextField(10);
    	 
    	 accountNumTextField = new JTextField(10);
    	 senderAccountNumTextField = new JTextField(10);
    	 receiverAccountNumTextField = new JTextField(10);
    	 
    	 scrollPane = new JScrollPane();
    	 scrollPane.setVisible(false);
    	 
    }
//adding components to GUI:
    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(new Color(55, 55, 55));
        panel.setLayout(null);
        getContentPane().add(panel);
        
        JPanel depNWithPanel = new JPanel();
        depNWithPanel.setBackground(new Color(42, 42, 42));
        depNWithPanel.setBounds(10, 12, 318, 170);
        panel.add(depNWithPanel);
        depNWithPanel.setLayout(null);
        
        depositButton.setBackground(new Color(0, 128, 255));
        depositButton.setBounds(99, 112, 82, 23);
        depNWithPanel.add(depositButton);
       
        withdrawButton.setBackground(new Color(0, 128, 255));
        withdrawButton.setBounds(191, 112, 90, 23);
        depNWithPanel.add(withdrawButton);
       
        amountTextField.setForeground(new Color(127, 127, 127));
        amountTextField.setBackground(new Color(55, 55, 55));
        amountTextField.setBounds(99, 67, 181, 21);
        depNWithPanel.add(amountTextField);
        
        accountNumTextField.setForeground(new Color(127, 127, 127));
        accountNumTextField.setBackground(new Color(55, 55, 55));
        accountNumTextField.setBounds(100, 34, 181, 21);
        depNWithPanel.add(accountNumTextField);
        JLabel lblAccountNum = new JLabel("Account Num");
        lblAccountNum.setForeground(new Color(127, 127, 127));
        lblAccountNum.setBackground(new Color(127, 127, 127));
        lblAccountNum.setBounds(10, 37, 90, 15);
        depNWithPanel.add(lblAccountNum);
        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setForeground(new Color(127, 127, 127));
        lblAmount.setBackground(new Color(127, 127, 127));
        lblAmount.setBounds(10, 70, 79, 15);
        depNWithPanel.add(lblAmount);
        
        senderAccountNumTextField.setForeground(new Color(127, 127, 127));
        senderAccountNumTextField.setBackground(new Color(55, 55, 55));
        senderAccountNumTextField.setBounds(95, 71, 181, 21);
        
        receiverAccountNumTextField.setForeground(new Color(127, 127, 127));
        receiverAccountNumTextField.setBackground(new Color(55, 55, 55));
        receiverAccountNumTextField.setBounds(95, 104, 181, 21);
        
        transferAmountTextField.setForeground(new Color(127, 127, 127));
        transferAmountTextField.setBackground(new Color(55, 55, 55));
        transferAmountTextField.setBounds(95, 137, 181, 21);
        
        transferButton.setBackground(new Color(0, 128, 255));
        transferButton.setBounds(142, 188, 96, 23);
        JPanel transferPanel = new JPanel();
        transferPanel.setBackground(new Color(42, 42, 42));
        transferPanel.setBounds(10, 211, 318, 240);
        panel.add(transferPanel);
        transferPanel.setLayout(null);
        JLabel lblSenderAc = new JLabel("Sender A/C");
        lblSenderAc.setForeground(new Color(127, 127, 127));
        lblSenderAc.setBounds(14, 74, 81, 15);
        transferPanel.add(lblSenderAc);
        transferPanel.add(senderAccountNumTextField);
        JLabel lblReceiverAc = new JLabel("Recieve A/C");
        lblReceiverAc.setForeground(new Color(127, 127, 127));
        lblReceiverAc.setBounds(14, 107, 81, 15);
        transferPanel.add(lblReceiverAc);
        transferPanel.add(receiverAccountNumTextField);
        transferPanel.add(transferAmountTextField);
        transferPanel.add(transferButton);
        JLabel lblAmount_1 = new JLabel("Amount");
        lblAmount_1.setForeground(new Color(127, 127, 127));
        lblAmount_1.setBounds(14, 140, 69, 15);
        transferPanel.add(lblAmount_1);
        
        panel.add(depNWithPanel);
        
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(42, 42, 42));
        tablePanel.setBounds(338, 12, 468, 439);
        tablePanel.setLayout(null);
        panel.add(tablePanel);
        showAllButton = new JButton("Show All");
        showAllButton.setBounds(373, 12, 85, 23);
        tablePanel.add(showAllButton);
        
        
        showAllButton.setForeground(new Color(255, 255, 255));
        showAllButton.setBackground(new Color(0, 128, 255));
        
       
    }
//adding actionListeners:
    //action listeners respond to button clicks, menu selection etc.
    //unlike mouse listeners which listens for mouse click and other actions(performable in any component)
    private void addListeners() {
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //this runs auto when button clicked.
                deposit();
                showAll(); //showAll() to update after deposit
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // e is to access detail about event
                withdraw();
                showAll();//showAll() to update after withdraw
            }
        });
        
        transferButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		transfer();
        		showAll();//showAll() to update after transfer
        	}
        });
        
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleTable(); //showAll and hideAll
            }
        });
    }

    private void deposit() {
        String accountNum = accountNumTextField.getText(); //getting accountNumber input from textfield in GUI
        String depositAmount = amountTextField.getText(); //getting amount input from textfield in GUI

        if (accountNum.isEmpty() || depositAmount.isEmpty()) { //popup window if any of the above is left empty
            JOptionPane.showMessageDialog(this, "Please fill in both account number and deposit amount", "Error", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.ERROR_MESSAGE =constant used to specify type of message dialog displayed 
            //"Error" = title of the dialog box
            /*
             * this = refers to the parent component(current instance of the class(JPanel)). 
             * determines if the dialog box should be centered or not. null = centered to the screen. 
             * 														   this = not null = centered to the component
             * */
            return;
        }
//exception handling:
        try {
        	//br object of class and class wraps around another reader(fileReader) and buffers the input to provide effecient reading of chars, array, lines
            BufferedReader br = new BufferedReader(new FileReader("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv")); //reading data from csv file
           //string builder to modify string and construct & store modified CSV content
            StringBuilder sb = new StringBuilder();
          //storing each line read from the file
            String line;
            boolean found = false; //account found or not found
//reading each lie from CSV file:
            while ((line = br.readLine()) != null) {
            	//splitting line with ,
                String[] data = line.split(",");
                //checking of data array has at least 4 elements and account number matches with input
                if (data.length >= 4 && data[2].equals(accountNum)) {
                	//parsing current balance and deposit from String to Double
                    double currentBalance = Double.parseDouble(data[3]); //data[3]=amount stored in index 3
                    double deposit = Double.parseDouble(depositAmount);
                    currentBalance += deposit; //adding deposit into current balance
                    //update balance in array by converting value of argument into String 
                    data[3] = String.valueOf(currentBalance);
                    //account number found set
                    found = true;
                }
                sb.append(String.join(",", data)).append("\n"); //appending modified or unmodified  line into StinrgBuilder
            }
            br.close(); //closing BufferedReader

            if (!found) { //account number not found, window will display error
                JOptionPane.showMessageDialog(this, "Account number not found: " + accountNum, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //writing modified CSV content back into the file
            BufferedWriter bw = new BufferedWriter(new FileWriter("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
            bw.write(sb.toString());//modified CSV content stored into StringBuilderas string and write into CSV fild with BufferedWriter.
            bw.close();//closing bufferedReader

            JOptionPane.showMessageDialog(this, "Deposit successful!"); //deposit successful popup menu
        } catch (IOException E) {
           //exception for fle error I/O
            JOptionPane.showMessageDialog(this, "Error: " + E, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException E) { //errors when parsing numbers
            JOptionPane.showMessageDialog(this, "Invalid deposit amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw() { //withdraw method
        String accountNum = accountNumTextField.getText(); //getting account number input from textField and storing into variable
        String withdrawAmount = amountTextField.getText();//getting withdraw amount input from textField and storing into variable
//condition: if any of the above variables are empty then the below if statement runs:
        if (accountNum.isEmpty() || withdrawAmount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both account number and withdrawal amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //exception handling:
        try {
        	//reafing the data from the csv file:
            BufferedReader br = new BufferedReader(new FileReader("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
            //stingBuilder stores modified csv content
            StringBuilder sb = new StringBuilder();
          //storing each line read from the file
            String line;
            boolean found = false; //account found/not found here it is false.

            //while loop runs if: reading eachline and it is not null
            while ((line = br.readLine()) != null) {
            	//splitting lines with ,
                String[] data = line.split(",");
                //checking if array of data has atleast 4 elemtns and the input account number is equal to the account number in the file
                if (data.length >= 4 && data[2].equals(accountNum)) {
                	//parsing currentBalance and withdrawal from Stirng to Double
                    double currentBalance = Double.parseDouble(data[3]); //balance is stored into 3rd index of array
                    double withdrawal = Double.parseDouble(withdrawAmount);
                    //condition to check if current balance is smaller than withdrawal amount
                    if (withdrawal > currentBalance) {
                    	//yes = insufficient balance window message dialog
                        JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //no = subtracting withdrawal amount from current balance
                    currentBalance -= withdrawal;
                    data[3] = String.valueOf(currentBalance);//updating balance in data array
                    found = true; //account number found
                }
                sb.append(String.join(",", data)).append("\n");//appending new data into StinrgBuilder 
            }
            br.close();//closing BufferedReader 

            //if account number was incorrect or not found:
            if (!found) {
                JOptionPane.showMessageDialog(this, "Account number not found: " + accountNum, "Error", JOptionPane.ERROR_MESSAGE);
                return; //exit method
            }
//writing the new data into the csv file
            BufferedWriter bw = new BufferedWriter(new FileWriter("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
            bw.write(sb.toString());//modified CSV content stored into StringBuilderas string and write into CSV fild with BufferedWriter.
            bw.close(); //closing the BufferedWriter

            //successful withdrawl message dialog to show withdrawl successful message
            JOptionPane.showMessageDialog(this, "Withdrawal successful!");
        } catch (IOException E) {
            //IO Exception 
            JOptionPane.showMessageDialog(this, "Error: " + E.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException E) {
        	//exception with parsing numbers
            JOptionPane.showMessageDialog(this, "Invalid withdrawal amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void transfer() {
    	String senderAccountNum = senderAccountNumTextField.getText(); //getting input from senderAccountTextField in GUI and storing into senderAccountNum
        String receiverAccountNum = receiverAccountNumTextField.getText(); //getting input from receiverAccontNumTextField in GUI and storing into receiverAccountNum
        String transferAmount = transferAmountTextField.getText(); //getting amount from transferAmountTextField ad storing into transferAmount
        //show message dialogbox if any of the above 3 input are left empty:
        if (senderAccountNum.isEmpty() || receiverAccountNum.isEmpty() || transferAmount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in sender account number, receiver account number, and transfer amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //exception handling:
        try {
        	//reading the data from/in thr csv file
            BufferedReader br = new BufferedReader(new FileReader("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
            //StringBuilder to store modified csv content
            StringBuilder sb = new StringBuilder();
            //storing each line read from the file
            String line;
            //sender and receiver not found initially as not input
            boolean senderFound = false;
            boolean receiverFound = false;
//reading through eveeryline of the CSV file till they are not null
            while ((line = br.readLine()) != null) {
            	//splitting each line of the array of data by ,
                String[] data = line.split(",");
                //checking if array of data has atleast 4 elements and senderAccountNum input is equal to account number in CSV file
                if (data.length >= 4 && data[2].equals(senderAccountNum)) {
                	//parsing senderBalance and amount into Double
                    double senderBalance = Double.parseDouble(data[3]); //balance is stored in index 3
                    double amount = Double.parseDouble(transferAmount);
                    //check if amount is more than balance of sender:
                    //yes= messageDialog saying insufficient balance
                    if (amount > senderBalance) {
                        JOptionPane.showMessageDialog(this, "Insufficient balance in sender account", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //no= subtract amount sent from sender balance
                    senderBalance -= amount;
                    //updating array with new senderBalance 
                    data[3] = String.valueOf(senderBalance);
                    //sender found
                    senderFound = true;
                }
                //checking if array of data has atleast 4 elements and senderAccountNum input is equal to account number in CSV file
                if (data.length >= 4 && data[2].equals(receiverAccountNum)) {
                	//parsing senderBalance and amount into Double
                    double receiverBalance = Double.parseDouble(data[3]);
                    double amount = Double.parseDouble(transferAmount);
                    //adding amount sent from sender into the receiver's balance
                    receiverBalance += amount;
                    //updating receiver's balance with new data
                    data[3] = String.valueOf(receiverBalance);
                    //receiver found
                    receiverFound = true;
                }
                //appending StirngBuilder to append new data to the stringBuilder
                sb.append(String.join(",", data)).append("\n");
            }
            br.close();//closing BufferedBuilder

            if (!senderFound) { //message display if sender not found
                JOptionPane.showMessageDialog(this, "Sender account number not found: " + senderAccountNum, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!receiverFound) { //message display if receiver not found
                JOptionPane.showMessageDialog(this, "Receiver account number not found: " + receiverAccountNum, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //writing the new csv content into the CSV file
            BufferedWriter bw = new BufferedWriter(new FileWriter("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
            bw.write(sb.toString());//modified CSV content stored into StringBuilderas string and write into CSV fild with BufferedWriter.
            bw.close();//closing BufferedWriter

            //showing message dialog after transfer is successful
            JOptionPane.showMessageDialog(this, "Transfer successful!");
        } catch (IOException E) {
        	//I/O Exception handling
            JOptionPane.showMessageDialog(this, "Error: " + E, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException E) {
        	//Number Formant Exception Handling
            JOptionPane.showMessageDialog(this, "Invalid transfer amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //toggle the table to show and hide/not show method:
    private void toggleTable() {
    	//check if scroll pane is currently visible
    	if(scrollPane.isVisible()) {
    		//yes = hide it
    		scrollPane.setVisible(false);
    		//update the text of the showAllButton to ShowAll
    		showAllButton.setText("Show All");
    	} //no:
    	else {
    		//scrollPane not visible, run the showAll() method
    		showAll();;
    	}
    }
    
    //showAll button method:
    private void showAll() {
    	try {
    		
    		//open for reading csv file
    		BufferedReader br = new BufferedReader(new FileReader("Accounts_84655eaf-d80b-4695-b6d7-92da3f7c60bf_90187_.csv"));
    		//array named column for column names/header names of the table
    		String[] column = {"First Name", "Last Name", "Account Number", "Balance"};//first arguments
    		//creating table with specified column in the above array
    		//column = column names and 0 = row count initial
    		DefaultTableModel tableColumn = new DefaultTableModel(column, 0); //new constructor //0=no initial rows
    		String line;//storing each line read from the file
    		
    		//reading through each line of CSV file until it's null
    		while((line=br.readLine())!=null){
    			//data array
    			//splitting lines into the array of string using ,
    			String[] data = line.split(",");
    			//checking if the length of the data array is atleast 4
    			if(data.length>=4) {
    				//adding row to the column with data from the csv file
    				tableColumn.addRow(data); //adds a row of data into table column
    			}
    		}
    		br.close(); //closing buffered reader
    		
    		//creating a JTable with the populated table model
    		JTable table = new JTable(tableColumn); //new JTable = constructor
    		
    		//if scrollpane is already present and visible. remove ftom the panel
    		 if (scrollPane != null && scrollPane.isVisible()) {
                 tablePanel.remove(scrollPane); //to refresh UI and information we remove scrollpane 
             }
    		 //creating new JScrollPane with the table
    		scrollPane = new JScrollPane(table);
    		//setting boundaries
    		scrollPane.setBounds(10, 50, 448, 379);
    		//adding scroll pane into the table panel
            tablePanel.add(scrollPane); //adding back updated scrollpane
            //setting the scrollPane to be visible
            scrollPane.setVisible(true);
            //revalidating the table panel to reflect changes and update correctly
            tablePanel.revalidate(); //recalculating layout and components in JFrame
            //repainting the table panel to update the appearance like changing color
            tablePanel.repaint();//reflects changes made on JPanel//redraws table with updated data
            //setting the text of showAllButton to Hide All
            showAllButton.setText("Hide All");
    	}
    	catch(IOException E){
    		// i/O Exception message dialog box display
    		JOptionPane.showMessageDialog(this, "Error "+ E + JOptionPane.ERROR_MESSAGE);
    	}
    }

    //main method to start application(GUI)
    public static void main(String[] args) {
    	//using Swing utilities (invokeLater) method to make sure GUI components are initialized 
    	//makes sure the code creates and displays GUI using Event Dispatch Thread(EDT) making sure operations done in thread-safe manner
        SwingUtilities.invokeLater(new Runnable() {
        	//interface where tasks can be done asyncronosuly(program does not wait for this to be complete)
        	//run method of Runnable interface
            public void run() { //displays GUI
            	//creating object of GUI class and setting visible to true/ setting GUI to visible
                new GUI().setVisible(true);
                //run is being executed in a different thread EDT than main
            }
        });
    }
}
