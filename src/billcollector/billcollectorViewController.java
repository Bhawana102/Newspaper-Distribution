package billcollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customermanager.MySQLConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class billcollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtAmount;

    @FXML
    private Label txtEnd;

    @FXML
    private TextField txtMobile;

    @FXML
    private Label txtStart;
Connection con;
PreparedStatement pst;
    @FXML
    void doBill(ActionEvent event) {
    	String mno=txtMobile.getText();
    	try {
			pst=con.prepareStatement("select * from bills where mobileno=?");
			pst.setString(1, mno);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				txtAmount.setText(table.getString("bill"));
				txtStart.setText(table.getDate("datefrom").toString());
				txtEnd.setText(table.getDate("dateto").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doPay(ActionEvent event) {
    	String mno=txtMobile.getText();
    	try {
			pst=con.prepareStatement("update bills set billstatus=1 where mobileno=? and billstatus=0 and datefrom=?");
			pst.setString(1, mno);
			pst.setString(2, txtStart.getText());
			int c=pst.executeUpdate();
			System.out.println(c+" Record/s updated.");	
		} catch (SQLException e) {
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
