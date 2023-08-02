package hawkertable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class hawkertableViewController {
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<hawkertableBean> tableData;
    
    Connection con;
	PreparedStatement pst;
	
    @FXML
    void doFetch(ActionEvent event)
    {
    	TableColumn<hawkertableBean, String> hname=new TableColumn<hawkertableBean, String>("Hawker Name");//col name in table view
    	hname.setCellValueFactory(new PropertyValueFactory<>("hname")); //col name dbms table 
//    	hname.setMinWidth(150);
    	
    	TableColumn<hawkertableBean, String> mobile=new TableColumn<hawkertableBean, String>("Mobile Number");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
//    	mobile.setMinWidth(150);
    	
    	TableColumn<hawkertableBean, String> alloareas=new TableColumn<hawkertableBean, String>("Allocated Areas");
    	alloareas.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
//    	alloareas.setMinWidth(150);
    	
    	TableColumn<hawkertableBean, String> doj=new TableColumn<hawkertableBean, String>("Date of Join");
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
//    	doj.setMinWidth(150);
System.out.println("xscd");
//    	tableData.getColumns().add(1, );
    	
			tableData.getColumns().addAll(hname, mobile, alloareas, doj);
    	
    	System.out.println("fdgbf");
    	tableData.setItems(FetchAllHawkers());
    	
    	System.out.println("Records Fetched Successfully...");
    } 
    
    ObservableList<hawkertableBean> FetchAllHawkers() 
    {
    	ObservableList<hawkertableBean>	ary=FXCollections.observableArrayList();
    	try {
    	   	
    		pst = con.prepareStatement("select * from hawkers");
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
    			String name = table.getString("hname");
	    		String mno=table.getString("mobile");
	    		String alloarea=table.getString("alloareas");
	    		String DOJ = String.valueOf(table.getDate("doj").toLocalDate());
	    		
	    		System.out.println("sdsdfs");
	    		hawkertableBean ref=new hawkertableBean(name, mno, alloarea, DOJ);
	    		System.out.println("sd");
	    		ary.add(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
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