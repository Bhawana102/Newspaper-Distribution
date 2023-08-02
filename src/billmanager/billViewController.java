package billmanager;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import customermanager.MySQLConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
public class billViewController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ComboBox<String> cmbCustomer;
    @FXML
    private DatePicker dpBillUpto;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtLastDate;
    @FXML
    private TextField txtMissing;
    @FXML
    private TextField txtPaper;
    @FXML
    private TextField txtPayable;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnGen;
    
    Connection con;
    PreparedStatement pst;
   //------------------------------------------------------- 
    void doFillNo()
    {  	try {
			pst=con.prepareStatement("select mobileno from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String id=table.getString("mobileno");
				cmbCustomer.getItems().add(id);
			}}
    	catch (SQLException e) {	e.printStackTrace();  }	 }
    
    //==============================
    @FXML
    void doFetchCustomer(ActionEvent event) {
    	String mno=cmbCustomer.getSelectionModel().getSelectedItem();
		try {
			pst=con.prepareStatement("select * from customers where mobileno=?");
			pst.setString(1, mno);
			System.out.println(mno);
			ResultSet table=pst.executeQuery();
			Float amountt=(float) 0;
			while(table.next())
			{ String prices=table.getString("sprices");
			String[] eachPrice=prices.split(",");
			
			for (String element : (eachPrice))
				amountt+=Float.parseFloat(element);
			
				txtPaper.setText(table.getString("spapers"));
				txtPrice.setText(prices);
				txtAmount.setText(String.valueOf(amountt));
			}
		} catch (SQLException e) {			e.printStackTrace(); } }
//=========================================
    @FXML
    void doGenBill(ActionEvent event)
    {
    	try {
    		String init=txtLastDate.getText();
    		LocalDate d2=dpBillUpto.getValue();
    		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		 LocalDate d1 = LocalDate.parse(init, pattern);
    		 
              long daysin = d2.toEpochDay()-d1.toEpochDay();
              if(daysin<0)
              {
      			Alert alert = new Alert(AlertType.WARNING);
      			alert.setTitle(null);
      			alert.setContentText("Kindly fill billing date again.");
      			alert.showAndWait(); 
      			txtPayable.setText("");
      		  }
              
              int daysleft=Integer.parseInt(txtMissing.getText());
             
              Float finalbill= (daysin-daysleft)*Float.parseFloat(txtAmount.getText());
              txtPayable.setText(finalbill.toString());
              
              pst=con.prepareStatement("insert into bills values(?,?,?,?,?)");
              pst.setString(1, cmbCustomer.getSelectionModel().getSelectedItem());
              pst.setString(2, String.valueOf(d1));
              pst.setString(3, String.valueOf(d2));
              pst.setFloat(4, finalbill);
              pst.setInt(5, 1);
              pst.executeUpdate();
				System.out.println("Record Saved........");
              
		} 
    	catch (Exception e) { e.printStackTrace(); } }
    //==========================
    java.sql.Date findDOS(String x)
    {
    	java.sql.Date doss= null;
    	try {
			pst=con.prepareStatement("select dos from customers where mobileno=?");
			pst.setString(1,x);
			ResultSet table =pst.executeQuery();
			while(table.next())
			{
				 doss=table.getDate("dos");
			}
			
		} 
    	catch (SQLException e) {	e.printStackTrace(); }
    	
		return doss;  }
//================================================
    @FXML
    void doLastBillingDate(ActionEvent event)
    {
    	String x=cmbCustomer.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select * from bills where mobileno =? order by dateto desc limit 1");
			pst.setString(1, x);
			ResultSet table=pst.executeQuery();
			System.out.println("sdfg");
			if(table.next()){
//				while(table.next()){
				String s=table.getDate("dateto").toString();
				System.out.println(s);
				txtLastDate.setText(s);
			}
//    }	if(txtLastDate.getText()==null)  /System.out.println(findDOS(x).toString()+"cfvghbjn");
			else	txtLastDate.setText(findDOS(x).toString());
		} catch (SQLException e) {	e.printStackTrace();}  }
//===========================================
    @FXML
    void initialize() {
    	
    	con=MySQLConnector.doConnect();
    	if(con==null)
    		System.out.println("Connection Problem");
    else
    	System.out.println("Connected");
    	doFillNo();
    	txtMissing.setText("0");
    }
    //==================================
}
