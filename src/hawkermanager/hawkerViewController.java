package hawkermanager;

import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import papermaster.MySQLConnector;
//import javafx.scene.input.MouseEvent;/
public class hawkerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbArea;

    @FXML
    private ComboBox<String> cmbHawker;

    @FXML
    private ImageView imgHawker;

    @FXML
    private Label lblPicPath;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtPath;

    @FXML
    private TextField txtSelArea;
    Connection con;
    PreparedStatement pst;
    String old="";
    
  //-------------------------------------------------------------
    @FXML
    void doDismiss(ActionEvent event)
    {

    	try{
    	String workername= cmbHawker.getSelectionModel().getSelectedItem();
    	pst=con.prepareStatement("delete from hawkers where hname=?");
    	pst.setString(1, workername);
    		int count=pst.executeUpdate();
    	if(count!=0)
    	{
    		System.out.println(count+ " Records Deleted");
    		doNew(event);
    	}
    		else
    		System.out.println("Invalid name");
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    }
    //-------------------------------------------------------------
//    @FXML
    void doConcatARea(MouseEvent event) {
    }
  //-------------------------------------------------------------
    @FXML
    void doSearch(ActionEvent event) {
    	try{
			pst=con.prepareStatement("select * from hawkers where hname=?");
			String wname= cmbHawker.getSelectionModel().getSelectedItem();
			pst.setString(1, wname);
			ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{ String picPath= table.getString("picpath");
			txtMobile.setText(table.getString("mobile"));
			txtAddress.setText(table.getString("address"));
			txtSelArea.setText(table.getString("alloareas"));
			txtPath.setText(table.getString("adhaarpath"));
			lblPicPath.setText(table.getString("picpath"));
		
						
			imgHawker.setImage(new Image(new FileInputStream(picPath)));
			
			System.out.println(wname+"\t\t"+table.getString("adhaarpath")+"\t"+table.getString("alloareas")+"\t"+picPath);
			
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}

    }
    //-------------------------------------------------------------
    @FXML
    void doSelectArea(ActionEvent event) {
    	
    	String allo=cmbArea.getSelectionModel().getSelectedItem();
    	if(old.isEmpty())
    		old=old+allo;
    	else
    	old=  old +","+allo; 	
    		txtSelArea.setText(old);      }
  //-------------------------------------------------------------
    @FXML
    void doEnroll(ActionEvent event)  {  	try {
			pst=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?,?)");
			pst.setString(1, cmbHawker.getSelectionModel().getSelectedItem());
			pst.setString(2,txtMobile.getText());
			pst.setString(3, txtAddress.getText());
			pst.setString(4, txtSelArea.getText());
			pst.setString(5, lblPicPath.getText());
			pst.setDate(6,java.sql.Date.valueOf(LocalDate.now()));
			pst.setString(7, txtPath.getText());
			pst.executeUpdate();
			System.out.println("Record Saved.");
		} 
    	catch (SQLException e) { e.printStackTrace(); } 	
    }
  //-------------------------------------------------------------

    @FXML
    void doNew(ActionEvent event) {
    	cmbHawker.getSelectionModel().clearSelection();
    	cmbHawker.getItems().add("");
    	txtMobile.clear();
    	cmbArea.getSelectionModel().clearSelection();
    	txtAddress.clear();
    	cmbArea.getSelectionModel().clearSelection();
    	txtSelArea.clear();
    	txtPath.clear();
    	lblPicPath.setText("");
    	try {
			imgHawker.setImage(new Image(new FileInputStream("C:\\Users\\i9jk5\\Dropbox\\Newspaper Assured\\src\\hawkermanager\\bicycle.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  //-------------------------------------------------------------
    @FXML
    void doSelectAdhaar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
  	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf"));
  	 File selectedFile = fileChooser.showOpenDialog(null);
  	 if (selectedFile != null) {
    		txtPath.setText(selectedFile.getPath());
    	    System.out.println(selectedFile.toURI().toString());
    	 }
    	 else
    		txtPath.setText(null);
    }
  //-------------------------------------------------------------

    @FXML
    void doUpload(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
   	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif") );
   	 File selectedFile = fileChooser.showOpenDialog(null);	 
   	 if (selectedFile != null) 
   	 {  lblPicPath.setText(selectedFile.getPath());
   	 Image img =new Image(selectedFile.toURI().toString());
   	    System.out.println(selectedFile.toURI().toString());
   	 try {	imgHawker.setImage(new Image(new FileInputStream(selectedFile)));	} 
   	 catch (FileNotFoundException e) {	e.printStackTrace();  }
   	 }
   	 else
   		lblPicPath.setText("nopic.jpg");
    }
//-------------------------------------------------------------
    @FXML
    void doUpdate(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=?,adhaarpath=? where hname=?");
			pst.setString(6, cmbHawker.getSelectionModel().getSelectedItem());
			pst.setString(1,txtMobile.getText());
			pst.setString(2, txtAddress.getText());
			pst.setString(3, txtSelArea.getText());
			pst.setString(4, lblPicPath.getText());
//			pst.setDate(5,java.sql.Date.valueOf(LocalDate.now()));
			pst.setString(5, txtPath.getText());
			int count=pst.executeUpdate();		
			System.out.println(count+" Records Updated........");
		} 
    	catch (SQLException e) {	e.printStackTrace(); }

    }
  //-------------------------------------------------------------
    void doFillAreas()
    {
    	 ArrayList<String> items= new ArrayList<String>(Arrays.asList("Select","Ajit Road","Model Town","Green City","Qila Mubarak","Mall Road","Balaji Enclave"));
         cmbArea.getItems().addAll(items);
    }
  //-------------------------------------------------------------
void doFillNames()
{
	try {
		pst=con.prepareStatement("select hname from hawkers");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			String wnames=table.getString("hname");//use table wale col ka name
			System.out.println(wnames);
			cmbHawker.getItems().add(String.valueOf(wnames));
		}
		
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
    		System.out.println("CONNECTED");
    	doFillAreas();
    	doFillNames();
    	
     }
}
