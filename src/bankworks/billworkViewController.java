package bankworks;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class billworkViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label lblHead;
    
    @FXML
    private URL location;
    Connection con;
    PreparedStatement pst;
    

    @FXML
    void goCollect(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billcollector/billcollectorView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)  {  e.printStackTrace();  }
    }

    @FXML
    void goGenerate(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billmanager/billView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)  {  e.printStackTrace();  }
    }
    
//    @FXML
//    void goReview(ActionEvent event) {
//    	try{
//    		Parent root=FXMLLoader.load(getClass().getResource("/billboard/billboardView.fxml")); 
//			Scene scene = new Scene(root);
//			Stage stage=new Stage();
//			stage.setScene(scene);
//			stage.show();
//		}
//		catch(Exception e)  {  e.printStackTrace();  }
//    }
    
    @FXML
    void goBack(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/admindesk/admindeskView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			 Scene scene1=(Scene)lblHead.getScene();
		   scene1.getWindow().hide();
		}
		catch(Exception e)  {  e.printStackTrace();  }
    }

    @FXML
    void initialize() {
    	con=billmanager.MySQLConnector.doConnect();
    	if(con==null)
    		System.out.println("Connection Problem");
       else
    	System.out.println("Connected");
    }

}
