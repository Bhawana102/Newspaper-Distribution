package adminlogin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class adminloginViewController {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtPass;
    Connection con;
    PreparedStatement pst;
    String passcod="Aggarwal@03";
    

    @FXML
    void doLog(ActionEvent event) {
    	boolean res=txtPass.getText().contentEquals(passcod);
    if(res)
    {
    	try{
//    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("admindesk/admindeskView.fxml")); 
    		Parent root=FXMLLoader.load(getClass().getResource("/admindesk/admindeskView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			 
	     Scene scene1=(Scene)txtPass.getScene();
			   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    else
    	showMsg();
    }
    
    void 	showMsg()
    {
    	//Alert alert = new Alert(AlertType.INFORMATION);
    			Alert alert = new Alert(AlertType.WARNING);
    			//Alert alert = new Alert(AlertType.WARNING);
    			
    			alert.setTitle("Information Dialog");
    				//or
    			//alert.setTitle(null);
    			
    			alert.setHeaderText("Your entered code doesnot match,Kindly refill passcode.");
//    			alert.setContentText("");

    			alert.showAndWait();
    }
//    void doInput()
//    {   	TextInputDialog dialog = new TextInputDialog("");
//		dialog.setTitle("Input Data...");
//		dialog.setContentText("Please enter Marks:");
//		// Traditional way to get the response value.
//		Optional<String> result = dialog.showAndWait();
//		//doAlert(result.get());
//		if(result.isPresent())
//		{if(result.get().equals(""))
//				showMsg("Fill Data");
//			else		showMsg("You Fill:"+result.get());}		 
//    }
    

    @FXML
    void initialize() {
    	con=billmanager.MySQLConnector.doConnect();
    	if(con==null)
    		System.out.println("Connection Problem");
       else
    	System.out.println("Connected");
//       passcode=;
    	txtPass.setText("");
      
    }

}
