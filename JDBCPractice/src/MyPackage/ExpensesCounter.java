package MyPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//import java.sql.DriverManager;
//
//import com.sun.jdi.connect.spi.Connection;

public class ExpensesCounter {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String url ="jdbc:mysql://localhost:3306/expenses";
		String Name ="root";
		String password ="Srithar@2004";
		
		Connection con = DriverManager.getConnection(url,Name,password);
		Statement st = con.createStatement();
		
		while(true) {
			
			System.out.println("-------Expenses Tracker-------");
			System.out.println("1.Insert the Expenses: ");
			System.out.println("2. Display the Expenses: ");
			System.out.println("3. Update the Expenses: ");
			System.out.println("4. Update the Purpose of Purchase: ");
			System.out.println("5. Update the Date of Purchase: ");
			System.out.println("6. Calculate the Specific Expenses: ");
			System.out.println("7. Calculate the Total Expenses: ");
			System.out.println("8. Delete the Specific Data");
			System.out.println("9. Delete the All Data");
			System.out.println("10. Close the Execution: ");
			
			
			System.out.println("Enter the Choice: ");
			int choice =s.nextInt();
			
			switch(choice) {
			case 1:
				String InsertQuery ="insert into expenses.expensesadder values(?,?,?)";
				PreparedStatement PS = con.prepareStatement(InsertQuery);
				
				System.out.println("Enter the Date of Purchase: ");
				s.nextLine();
				String Date =s.nextLine();
				
				java.sql.Date sqlDate =java.sql.Date.valueOf(Date);
				
				System.out.println("Enter the Purpose of Purchase: ");
				
				String Purchase =s.nextLine();
				
				System.out.println("Enter the Amount Spend: ");
				
				int Amount =s.nextInt();
				
				PS.setDate(1,sqlDate);
				PS.setString(2, Purchase);
				PS.setInt(3, Amount);
				
				PS.executeUpdate();
				
				System.out.println("Data Successfully Inserted!!");
				break;
				
			case 2:
				String DisplayQuery ="select * from expenses.expensesadder";
				ResultSet rs = st.executeQuery(DisplayQuery);
				System.out.println("Date   PurposeOfPurchase   Amount");
				while(rs.next()) {
					System.out.print(rs.getDate(1)+" ");
					System.out.print(rs.getString(2)+" ");
					System.out.println(rs.getInt(3));
				}
				
				System.out.println("----------------------------");
				break;
				
			case 3:
			
			    String UpdateQuery = "UPDATE expenses.expensesadder SET Amount = ? WHERE Date = ? AND Amount = ? AND PurposeOfPurchase = ?";

			    // Ask for new updated amount
			    System.out.print("Please enter the Updated Amount: ");
			    int UpdatedAmount = s.nextInt();

			    // Ask for date
			    System.out.println("Please Enter the Date of Purchase (yyyy-mm-dd): ");
			    s.nextLine(); // consume newline left by nextInt
			    String date = s.nextLine();
			    java.sql.Date sqldate = java.sql.Date.valueOf(date);

			    // Ask for purpose
			    System.out.println("Please Enter the Purpose Of Purchase: ");
			    String pur = s.nextLine();

			    // Ask for old amount (the amount to be replaced)
			    System.out.println("Enter the Oldest Amount: ");
			    int OldestAmount = s.nextInt();

			    // Prepare query
			    PreparedStatement ppss = con.prepareStatement(UpdateQuery);
			    ppss.setInt(1, UpdatedAmount);
			    ppss.setDate(2, sqldate);
			    ppss.setInt(3, OldestAmount);
			    ppss.setString(4, pur);

			    // Execute update
			    int row = ppss.executeUpdate();

			    if (row > 0) {
			        System.out.println("Data Updated Successfully !!");
			    } else {
			        System.out.println("No Matching record found to Update");
			    }
			    break;

				
			case 4:
			// (assuming this is for updating PurposeOfPurchase)
			    String PurchaseUpdate = "UPDATE expenses.expensesadder SET PurposeOfPurchase = ? WHERE Date = ? AND PurposeOfPurchase = ? AND Amount = ?";

			    // Get updated purpose
			    System.out.println("Please Enter the Updated Purpose of Purchase: ");
			    s.nextLine(); // consume newline left from previous nextInt
			    String UpdatedPurpose = s.nextLine();

			    // Get date
			    System.out.println("Please Enter the Date for Match (yyyy-mm-dd): ");
			    String MatchDate = s.nextLine();
			    java.sql.Date sqldd = java.sql.Date.valueOf(MatchDate);

			    // Get amount
			    System.out.println("Enter the Amount for Match: ");
			    int MatchAmount = s.nextInt();
			    s.nextLine(); // consume newline

			    // Get old purpose
			    System.out.println("Enter the Oldest Purpose of Purchase: ");
			    String OldPurposeOfPurchase = s.nextLine();

			    // Prepare statement
			    PreparedStatement prep = con.prepareStatement(PurchaseUpdate);
			    prep.setString(1, UpdatedPurpose);
			    prep.setDate(2, sqldd);
			    prep.setString(3, OldPurposeOfPurchase);
			    prep.setInt(4, MatchAmount);

			    // Execute update
			    int Up = prep.executeUpdate();
			    if (Up > 0) {
			        System.out.println("The Purpose of Purchase was Updated");
			    } else {
			        System.out.println("No matching record found to update");
			    }

			    break;

				
			case 5:
				System.out.println("Enter the Updated Date: ");
				s.nextLine();
				String UpdatedDate =s.nextLine();
				java.sql.Date UpdateDate =java.sql.Date.valueOf(UpdatedDate);
//				s.nextLine();
				
				System.out.println("Previous Date: ");
				String PreviousDate =s.nextLine();
				
				java.sql.Date PreDate =java.sql.Date.valueOf(PreviousDate);
				
				System.out.println("Enter the Purpose Of Purchase for Match: ");
				String Purpurchase=s.nextLine();
				
				System.out.println("Enter the Amount for Match: ");
				int MatAmount =s.nextInt();
				
				String DateQuery ="update expenses.expensesadder set Date=? where PurposeOfPurchase =? and Amount =? and Date=?";
				
				PreparedStatement Pre = con.prepareStatement(DateQuery);
				Pre.setDate(1,UpdateDate);
				Pre.setString(2,Purpurchase);
				Pre.setInt(3, MatAmount);
				Pre.setDate(4, PreDate);
				
				int Dat = Pre.executeUpdate();
				
				if(Dat>0) {
					System.out.println("Data is Updated Successfully!!");
				}
				else {
					System.out.println("Data is not found for Match!!");
				}
				
				break;
			case 6:
				System.out.println("Please Enter the PurposeOfPurchase : ");
				s.nextLine();
				String Purpose = s.nextLine();

				
				String CalculateQuery ="select SUM(Amount) as TotalAmount from expensesadder where PurposeOfPurchase = ?";

				PreparedStatement ps2 = con.prepareStatement(CalculateQuery);
				ps2.setString(1, Purpose);

				ResultSet RS = ps2.executeQuery();

				if(RS.next()) {
					int total =RS	.getInt("TotalAmount");
				    System.out.println("Total Amount for " + Purpose + " = " + total);
				} else {
				    System.out.println("No records found for " + Purpose);
				}
				break;
			
			case 7:
				String TotalQuery ="select sum(Amount) as TotalAmount from expenses.expensesadder";
				
				ResultSet rr = st.executeQuery(TotalQuery);
				
				rr.next();
				System.out.println(rr.getInt(1));
				
				break;
				
			case 8:
				String Del ="Delete from expenses.expensesadder where Date=? and PurposeOfPurchase =? and Amount =?";
				PreparedStatement prest= con.prepareStatement(Del);
				
				System.out.println("Enter the Date of the Expenses to Delete: ");
				String DelDate =s.next();
				
				java.sql.Date deldate = java.sql.Date.valueOf(DelDate);
				
				prest.setDate(1,deldate );
				s.nextLine();
				System.out.println("Enter the Purpose of Purchase for Delete: ");
				String delpur=s.nextLine();
				
				prest.setString(2, delpur);
				
				System.out.println("Enter the Amount to Delete: ");
				int delamount =s.nextInt();
				
				prest.setInt(3,delamount);
				
				int delset = prest.executeUpdate();
				if(delset>0) {
					System.out.println("Data deleted Successfully!!");
					
				}
				else
					System.out.println("Data is not match to delete");
				
				
				break;
				
			case 9:
				String dd = "delete from expenses.expensesadder";
				int delt=st.executeUpdate(dd);
				if(delt>0) {
					System.out.println("Datas are Deleted Successfully!!");
				}
				break;
				
			case 10:
				con.close();
				
				
				System.out.println("Great Job, You did your Work Well!!");
				break;
				
			default:
				System.out.println("Please Enter the Correct Choice!!");
			}
			
		}
//		
		
	}

}
