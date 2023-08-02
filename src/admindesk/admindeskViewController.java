package admindesk;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class admindeskViewController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Label lblDevelop;
    
    @FXML
    private URL location;
    Connection con;
    PreparedStatement pst;

    @FXML
    void doCustomerMaster(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customermanager/CustomerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doPaperMaster(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/papermaster/papermasterView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doBillServices(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bankworks/billworkView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    

    @FXML
    void doLogout(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/adminlogin/adminloginView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			 Scene scene1=(Scene)lblDevelop.getScene();
		   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    

    @FXML
    void doBillReview(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billboard/billboardView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void doCustomertable(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerspanel/customerpanelView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			 Scene scene1=(Scene)lblDevelop.getScene();
//		   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    

    @FXML
    void doHawkerManager(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawkermanager/hawkerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			 Scene scene1=(Scene)lblDevelop.getScene();
//		   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doMeetus(MouseEvent event) {

    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/meetus/meetView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			 Scene scene1=(Scene)lblDevelop.getScene();
//		   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    @FXML
    void dohawkertable(ActionEvent event) {

    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawkertable/hawkertableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
//			 Scene scene1=(Scene)lblDevelop.getScene();
//		   scene1.getWindow().hide();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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