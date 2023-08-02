package papermaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
//import papermaster.MySQLConnector;

public class papermasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbPaper;

    @FXML
    private TextField txtPrice;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doSearch(ActionEvent event)
    {
    	String pname=cmbPaper.getSelectionModel().getSelectedItem();
    
    try 
    {
	pst=con.prepareStatement("select * from papers where paper=?");
	pst.setString(1,pname);
	
	ResultSet table=pst.executeQuery();
	  while(table.next())
	  {
	  txtPrice.setText(String.valueOf(table.getFloat("price")));
      }
	}
    
    catch (SQLException e)
     {
	e.printStackTrace();
     }
    }
    
    @FXML
    void doAvail(ActionEvent event)
    {
    	String pname=cmbPaper.getSelectionModel().getSelectedItem();
		float pprice=Float.parseFloat(txtPrice.getText());
    	try {
    		
			pst=con.prepareStatement("insert into papers values(?,?)");
			pst.setString(1, pname);
			pst.setFloat(2, pprice);
			pst.executeUpdate();
			System.out.println("record savedx");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void doEdit(ActionEvent event)
    {
    try {
    	String pname= cmbPaper.getSelectionModel().getSelectedItem();
    	Float pr=Float.parseFloat(txtPrice.getText());
    	
		pst=con.prepareStatement("update papers set price = ? where paper =?");
		pst.setString(2,pname);
		pst.setFloat(1,pr);
		int count=pst.executeUpdate();
		System.out.println(count+" record/s updated");
	} 
    catch (SQLException e) {
		e.printStackTrace();
	}
    

    }

    
    @FXML
    void doWithdraw(ActionEvent event)
    {

    	String pname=cmbPaper.getSelectionModel().getSelectedItem();
    
    try 
    {
	pst=con.prepareStatement("delete  from papers where paper=?");
	pst.setString(1,pname);
	int count=pst.executeUpdate();
	if(count!=0)
		System.out.println(count+ " Records Deleted");
	else
		System.out.println("Invalid ID");
	}
	    
    catch (SQLException e)
     {
	e.printStackTrace();
     }
    
    }
    @FXML
    void doNew(ActionEvent event)
    {
    	cmbPaper.getSelectionModel().clearSelection();
    	txtPrice.setText("");

    }
    
    void fillPapers()
    {
    	try {
    	pst=con.prepareStatement("select paper from papers");
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{  //  System.out.println(table.getString(1));
    		cmbPaper.getItems().add(table.getString(1));
    	}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	
    }

    @FXML
    void initialize() {
    	
//       ArrayList<String> papers = new ArrayList<String>(Arrays.asList("Times of India -5.40","The New Indian Express -6.50","Hindustan Times- 6.45","The Hindu -5.30","The Economic Times-4.8","The Tribune- 4.80"));
//       cmbPaper.getItems().addAll(papers);
    	con=MySQLConnector.doConnect();
    	
    	if(con==null)
    		System.out.println("Connection Problem");
    	else
    		System.out.println("CONNECTED");
    	fillPapers();

    }

}
