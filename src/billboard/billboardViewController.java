package billboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class billboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;

    @FXML
    private ToggleGroup hello;

    @FXML
    private RadioButton rdPaid;

    @FXML
    private RadioButton rdPend;

    @FXML
    private TableView<billtableBean> tableData;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMobile;

    Connection con;
    PreparedStatement pst;
    ObservableList<billtableBean> ary;
    
    void createtable()
    {
    	tableData.getColumns().clear();
		TableColumn<billtableBean,String> mobileno =new TableColumn<billtableBean,String>("Mobile No");
		mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
		
		TableColumn<billtableBean,String> datefrom =new TableColumn<billtableBean,String>("Date from");
		datefrom.setCellValueFactory(new PropertyValueFactory<>("datefrom"));

		TableColumn<billtableBean,String> dateto =new TableColumn<billtableBean,String>("Date To");
		dateto.setCellValueFactory(new PropertyValueFactory<>("dateto"));
		
		TableColumn<billtableBean,Float> bill =new TableColumn<billtableBean,Float>("Bill");
		bill.setCellValueFactory(new PropertyValueFactory<>("bill"));

		
		TableColumn<billtableBean,Integer> billstatus =new TableColumn<billtableBean,Integer>("Bill Status");
		billstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
		

		tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(mobileno, datefrom, dateto, bill,billstatus)));
	
//		(mobileno, datefrom, dateto, bill,billstatus);
    }
    @FXML
    void doMobileBill(ActionEvent event) {
    	tableData.setItems(findMobile());
		System.out.println("Records Fetched Successfully...");
    }
	ObservableList<billtableBean> findMobile()
	{
//		ObservableList<billtableBean>	ary =FXCollections.observableArrayList();
		ary =FXCollections.observableArrayList();
		Float totalbl = (float) 0.0 ;
		try {	pst = con.prepareStatement("select * from bills where mobileno=?");
		pst.setString(1, txtMobile.getText());
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String mno = table.getString("mobileno");
			String df=String.valueOf(table.getDate("datefrom").toLocalDate());
			String dt=String.valueOf(table.getDate("dateto").toLocalDate());
			Float bl = table.getFloat("bill");
			totalbl += bl;
		   Integer bstat=table.getInt("billstatus");

			billtableBean ref=new billtableBean(mno, df, dt, bl,bstat);
			ary.add(ref);
			}
		txtAmount.setText(String.valueOf(totalbl));
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
//------------------------
    @FXML
    void doPrint(ActionEvent event) {

    	try {
			writeExcel();
				System.out.println("done exporrt");
			}
			catch (Exception e)
			{ e.printStackTrace();
			}
	}
	//============================================================================
	public void writeExcel() throws Exception {
		Writer writer = null;
		try
		{
		File file= new File("Users.csv");
		FileWriter fx=new FileWriter(file);
		 writer = new BufferedWriter(fx);
		String text="mobileno,datefrom,dateto,bill,billstatus\n";
		writer.write(text);
		for(billtableBean p : ary)
		{ text= p.getMobileno()+","+p.getDatefrom()+","+p.getDateto()+","+p.getBill()+","+p.getBillstatus()+"\n";
		writer.write(text);	}
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		finally{
			writer.flush();
		writer.close();
		}}
	
	ObservableList<billtableBean> findpaidpend()
	{Float totalbl = (float) 0.0 ;
	ary =FXCollections.observableArrayList();
		try {	pst = con.prepareStatement("select * from bills where billstatus=?");
		if(rdPaid.isSelected())
			pst.setInt(1,1);
		else if(rdPend.isSelected())
		pst.setInt(1, 0);
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String mno = table.getString("mobileno");
			String df=String.valueOf(table.getDate("datefrom").toLocalDate());
			String dt=String.valueOf(table.getDate("dateto").toLocalDate());
			Float bl = table.getFloat("bill");
			totalbl += bl;
		   Integer bstat=table.getInt("billstatus");

			billtableBean ref=new billtableBean(mno, df, dt, bl,bstat);
			ary.add(ref);}
		txtAmount.setText(String.valueOf(totalbl));
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}

    @FXML
    void doPaidPend(ActionEvent event) {
    	tableData.setItems(findpaidpend());
		System.out.println("Records Fetched Successfully...");
    }


//------------------------------
    
    ObservableList<billtableBean> finddate()
	{
    	Float totalbl = (float) 0.0 ;
//		ObservableList<billtableBean>	ary =FXCollections.observableArrayList();
    	ary =FXCollections.observableArrayList();
		try {	pst = con.prepareStatement("select * from bills where datefrom >= ? and dateto <= ?");
		pst.setDate(1, java.sql.Date.valueOf(dpFrom.getValue()));
		pst.setDate(2, java.sql.Date.valueOf(dpTo.getValue()));
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String mno = table.getString("mobileno");
			String df=String.valueOf(table.getDate("datefrom").toLocalDate());
			String dt=String.valueOf(table.getDate("dateto").toLocalDate());
			Float bl = table.getFloat("bill");
			totalbl += bl;
		   Integer bstat=table.getInt("billstatus");

			billtableBean ref=new billtableBean(mno, df, dt, bl,bstat);
			ary.add(ref);}
		txtAmount.setText(String.valueOf(totalbl));
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
    //===========================================
    @FXML
    void doSearch(ActionEvent event) {
tableData.setItems(finddate());
System.out.println("records done");
    }
//-----------------------------------------------------
    @FXML
    void initialize() {
    	con=billmanager.MySQLConnector.doConnect();
    	if(con==null)
    		System.out.println("Connection Problem");
       else
    	System.out.println("Connected");
    	createtable();
    }

}
