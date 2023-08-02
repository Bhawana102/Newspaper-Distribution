package customermanager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import customermanager.MySQLConnector;

public class CustomerViewController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbArea;

    @FXML
    private ListView<String> lstPaperA;

    @FXML
    private ListView<String> lstPaperS;

    @FXML
    private ListView<String> lstPriceA;

    @FXML
    private ListView<String> lstPriceS;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker dp;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHawker;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;
    Connection con;
    PreparedStatement pst;
    
//    String selPa="";
//    String selPr="";
    
    @FXML
    void doDblClickDelete(MouseEvent event) 
    {
    	if(event.getClickCount()==2)
    	{
    		lstPriceS.getItems().remove(lstPaperS.getSelectionModel().getSelectedIndex());
    		lstPaperS.getItems().remove(lstPaperS.getSelectionModel().getSelectedIndex());
    	}
    }
   //======================================================== 
    @FXML
    void doSelectPaper(MouseEvent event)
    {
    	String selPaper=lstPaperA.getSelectionModel().getSelectedItem();
		Integer selIndex=lstPaperA.getSelectionModel().getSelectedIndex();
		lstPaperS.getItems().add(selPaper);
		lstPriceA.getSelectionModel().select(selIndex);
		lstPriceS.getItems().add(lstPriceA.getSelectionModel().getSelectedItem());
		
	
	}
  //-------------------------------------------------------------
    @FXML
    void doFetch(ActionEvent event) {
    	String mn=txtMobile.getText();
    	try {
			pst=con.prepareStatement("select * from customers where mobileno=?");
			pst.setString(1, mn);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				java.sql.Date doss=table.getDate("dos");
				txtName.setText(table.getString("cname"));
				txtEmail.setText(table.getString("email"));
				txtName.setText(table.getString("cname"));
				txtAddress.setText(table.getString("caddress"));
				cmbArea.setValue(table.getString("area"));
				txtHawker.setText(table.getString("hawker"));
				dp.setValue(doss.toLocalDate());
				doFillFetchList(table.getString("spapers"),table.getString("sprices"));

			}
		} catch (SQLException e) {	e.printStackTrace(); }
    	

    }
    //====================================
    void doFillFetchList(String pa,String pr)
    {
    	ArrayList<String> Selpapers=new ArrayList<String>(Arrays.asList(pa.split(",")));
    	ArrayList<String> Selprices=new ArrayList<String>(Arrays.asList(pr.split(",")));
    	lstPaperS.getItems().addAll(Selpapers);
    	lstPriceS.getItems().addAll(Selprices);
    }
  //-------------------------------------------------------------
    @FXML
    void doSelArea(ActionEvent event) {
    	String area=cmbArea.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select hname from hawkers where alloareas like ?");
			pst.setString(1,"%"+area+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				txtHawker.setText(table.getString(1));
			}
			
		} 
    	catch (SQLException e) {	e.printStackTrace();	}
    	

    }
  //-------------------------------------------------------------
  //-------------------------------------------------------------
    @FXML
    void doSubscribe(ActionEvent event)
    {
    	try {
//    		create table customers(mobileno varchar(10),cname varchar(50),email varchar(40)
//    		caddress varchar(100),area varchar(50),hawker varchar(40),dos date,spapers varchar(150),sprices varchar(100));
    		
    		String carea=cmbArea.getSelectionModel().getSelectedItem();
    	    String selPa="";
    	    String selPr="";
    		ObservableList<String> arySelPapers	=lstPaperS.getItems();
    		for (String string : arySelPapers)
    			selPa+= ","+string;
    	
    		ObservableList<String> arySel=lstPriceS.getItems();
    		for (String prc : arySel)
    			selPr+= ","+prc;
 
    		pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, txtMobile.getText());
			pst.setString(2, txtName.getText());
			pst.setString(3, (txtEmail.getText()).toLowerCase());
			pst.setString(4, txtAddress.getText());
			pst.setString(5, carea);
			pst.setString(6, txtHawker.getText());
			pst.setDate(7, java.sql.Date.valueOf(dp.getValue()));
//			pst.setString(7, String.valueOf(dp.getValue()));
			pst.setString(8,selPa.substring(1,selPa.length()));
			pst.setString(9, selPr.substring(1,selPr.length()));
			pst.executeUpdate();
			System.out.println("Record Saved.");	
    	}
    	catch (SQLException e) {	e.printStackTrace();  }
    }
  //-------------------------------------------------------------
    @FXML
    void doUnsubscribe(ActionEvent event)
    {
    	try {
			pst=con.prepareStatement("delete from customers where mobileno=?");
			pst.setString(1, txtMobile.getText());
			int c=pst.executeUpdate();
			if(c!=0)
			System.out.println(c+" Deleted");
			else
				System.out.println("Invalid Id");
		} 
    	catch (SQLException e) {	e.printStackTrace(); }
    	
    	

    }
    //====================================================

  //-------------------------------------------------------------
    @FXML
    void doUpdate(ActionEvent event)
    {

    	try {
//    		create table customers(mobileno varchar(10),cname varchar(50),email varchar(40)
//    		caddress varchar(100),area varchar(50),hawker varchar(40),dos date,spapers varchar(150),sprices varchar(100));
    		
    		String carea=cmbArea.getSelectionModel().getSelectedItem();
    	    String selPa="";
    	    String selPr="";
    		ObservableList<String> arySelPapers	=lstPaperS.getItems();
    		for (String string : arySelPapers)
    			selPa+= ","+string;
    	
    		ObservableList<String> arySel=lstPriceS.getItems();
    		for (String prc : arySel)
    			selPr+= ","+prc;
 
    		pst=con.prepareStatement("update customers set cname=?,email=?,caddress=?,area=?,hawker=?,dos=?,spapers=?,sprices=? where mobileno=?");
			pst.setString(9, txtMobile.getText());
			pst.setString(1, txtName.getText());
			pst.setString(2, (txtEmail.getText()).toLowerCase());
			pst.setString(3, txtAddress.getText());
			pst.setString(4, carea);
			pst.setString(5, txtHawker.getText());
			pst.setDate(6, java.sql.Date.valueOf(dp.getValue()));
//			pst.setString(7, String.valueOf(dp.getValue()));
			pst.setString(7,selPa.substring(1,selPa.length()));
			pst.setString(8, selPr.substring(1,selPr.length()));
			int c=pst.executeUpdate();
			System.out.println(c+" Record/s updated.");	
    	}
    	catch (SQLException e) {	e.printStackTrace();  }
    }
  //-------------------------------------------------------------
    void doFillAreas()
    {
    	 ArrayList<String> items= new ArrayList<String>(Arrays.asList("Select","Ajit Road","SBS Colony","Model Town","Green City","Qila Mubarak","Mall Road","Balaji Enclave"));
         cmbArea.getItems().addAll(items);
    }

  //-------------------------------------------------------------
    void doFillList()
    {
    	try {
			pst=con.prepareStatement("select * from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				lstPaperA.getItems().add(table.getString(1));
				lstPriceA.getItems().add(table.getString(2));
			}
//			lstPaperA.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
		} 
    	catch (SQLException e) {	e.printStackTrace(); }
    }
  //-------------------------------------------------------------
    @FXML
    void initialize()
    {
    	con=MySQLConnector.doConnect();
	if(con==null)
		System.out.println("Connection Problem");
else
	System.out.println("Connected");
      doFillAreas();
      doFillList();
      
    }

}
/*   
      String[] myArray = myString.split(",");
      System.out.println("Contents of the array ::"+Arrays.toString(myArray));
      List <String> myList = Arrays.asList(myArray);
   }
   //    void doInput()
//    {
//    	TextInputDialog dialog = new TextInputDialog("");
//		dialog.setTitle("Input Data...");
//		dialog.setContentText("Please enter Marks:");
//
//		// Traditional way to get the response value.
//		Optional<String> result = dialog.showAndWait();
//		
//		//doAlert(result.get());
//		if(result.isPresent())
//		{
//			if(result.get().equals(""))
//				showMsg("Fill Data");
//			else
//			{	
//				showMsg("You Fill:"+result.get());
//				
//			}
//		}
//		 
//    }
//    
//    //=======================================================
//    void 	showMsg(String msg)
//    {
//    	//Alert alert = new Alert(AlertType.INFORMATION);
//    			Alert alert = new Alert(AlertType.WARNING);
//    			//Alert alert = new Alert(AlertType.WARNING);
//    			
//    			alert.setTitle("Information Dialog");
//    				//or
//    			//alert.setTitle(null);
//    			
//    			alert.setHeaderText("Look, an Information Dialog");
//    			alert.setContentText(msg);
//
//    			alert.showAndWait();
//    }
}*/
