package customerspanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

//import hawkertable.hawkertableBean;/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class customerpanelViewController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> cmbAreas;

	@FXML
	private ComboBox<String> cmbPapers;
	
	@FXML
	private TableView<customertableBean> tableData;
	Connection con;
	PreparedStatement pst;
	ObservableList<customertableBean> ary;

	void createtable()
	{
		tableData.getColumns().clear();
		
		TableColumn<customertableBean,String> cname =new TableColumn<customertableBean,String>("C Name");
		cname.setCellValueFactory(new PropertyValueFactory<>("cname"));


		TableColumn<customertableBean,String> mobileno =new TableColumn<customertableBean,String>("Mobile No");
		mobileno.setCellValueFactory(new PropertyValueFactory<>("mobileno"));

		TableColumn<customertableBean,String> area =new TableColumn<customertableBean,String>("Area");
		area.setCellValueFactory(new PropertyValueFactory<>("area"));

		TableColumn<customertableBean,String> dos =new TableColumn<customertableBean,String>("Date of Start");
		dos.setCellValueFactory(new PropertyValueFactory<>("dos"));

		TableColumn<customertableBean,String> spapers =new TableColumn<customertableBean,String>("Newspapers");
		spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
		spapers.setMinWidth(300);

		tableData.getColumns().addAll(cname, mobileno, area, dos,spapers);

	}//============================================================================

	//============================================================================

	@FXML
	void doAllCust(ActionEvent event) 
	{
//		tableData.getColumns().clear();
		tableData.setItems(findAll());
		System.out.println("Records Fetched Successfully..."); 
	}
	//-------------------
	ObservableList<customertableBean> findAll()
	{
//		ObservableList<customertableBean>	ary =FXCollections.observableArrayList();
		ary =FXCollections.observableArrayList();
		try {	pst = con.prepareStatement("select * from customers");
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String name = table.getString("cname");
			String mno=table.getString("mobileno");
			String carea=table.getString("area");
			String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
			String paper=table.getString("spapers");

			customertableBean ref=new customertableBean(name, mno, carea, DOJ,paper);
			ary.add(ref);}
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
	//============================================================================

	//============================================================================

	@FXML
	void doExport(ActionEvent event) 
	{
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
		String text="cname,mobileno,area,dos,spapers\n";
		writer.write(text);
		for(customertableBean p : ary)
		{
			text= p.getCname()+","+p.getMobileno()+","+p.getArea()+","+p.getDos()+","+p.getSpapers()+"\n";
		writer.write(text);
		}
		}
		catch (Exception e)
		{e.printStackTrace();
		}
		finally{
			writer.flush();
		writer.close();
		}
		}
	//-===========================================================================
	ObservableList<customertableBean> FetchAllCustomers()
	{
			ary =FXCollections.observableArrayList();
//			ObservableList<customertableBean> ary =FXCollections.observableArrayList();
		try {	pst = con.prepareStatement("select * from customers where area=?");
		pst.setString(1, cmbAreas.getSelectionModel().getSelectedItem());
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String name = table.getString("cname");
			String mno=table.getString("mobileno");
			String carea=table.getString("area");
			String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
			String paper=table.getString("spapers");
			
			customertableBean ref=new customertableBean(name, mno, carea, DOJ,paper);
			ary.add(ref);
		}
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
	//-------------------------------------------------------

	@FXML
	void doFetxhAreas(ActionEvent event)
	{   
//	tableData.getColumns().clear();
	tableData.setItems(FetchAllCustomers());
	System.out.println("Records Fetched Successfully...");   	

	}
	//=====================================================================

	//=====================================================================
	@FXML
	ObservableList<customertableBean> findboth()
	{
//		ObservableList<customertableBean>	ary =FXCollections.observableArrayList();
		ary =FXCollections.observableArrayList();
		try {		pst = con.prepareStatement("select * from customers where area=? and spapers like ?");
		pst.setString(1, cmbAreas.getSelectionModel().getSelectedItem());
		pst.setString(2, "%"+ cmbPapers.getSelectionModel().getSelectedItem()+"%");
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String name = table.getString("cname");
			String mno=table.getString("mobileno");
			String carea=table.getString("area");
			String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
			String paper=table.getString("spapers");

			customertableBean ref=new customertableBean(name, mno, carea, DOJ,paper);
			ary.add(ref);
		}
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
	//-----------------------------------------
	@FXML
	void doFilterBoth(ActionEvent event) {
//		tableData.getColumns().clear();
		tableData.setItems(findboth());
		System.out.println("Records Fetched Successfully..."); 
	}
	//============================================================================

	
	//=============================================================================
	ObservableList<customertableBean> findpapers()
	{
//		ObservableList<customertableBean>	ary =FXCollections.observableArrayList();
		ary =FXCollections.observableArrayList();
		try {		pst = con.prepareStatement("select * from customers where  spapers like ?");
		pst.setString(1, "%"+ cmbPapers.getSelectionModel().getSelectedItem()+"%");
		ResultSet table=pst.executeQuery();
		while(table.next()) {
			String name = table.getString("cname");
			String mno=table.getString("mobileno");
			String carea=table.getString("area");
			String DOJ = String.valueOf(table.getDate("dos").toLocalDate());
			String paper=table.getString("spapers");

			customertableBean ref=new customertableBean(name, mno, carea, DOJ,paper);
			ary.add(ref);
		}
		}
		catch(Exception ex) { ex.printStackTrace(); }
		return ary;
	}
	
	//------------------------------------

	@FXML
	void doSearchPapers(ActionEvent event) {
//		tableData.getColumns().clear();
		tableData.setItems(findpapers());
		System.out.println("Records Fetched Successfully..."); 
	}
	//============================================================================

	//====================================================================
	void doFillAreas()
	{ ArrayList<String> items= new ArrayList<String>(Arrays.asList("Select","Ajit Road",
			"SBS Colony","Model Town","Green City","Qila Mubarak","Mall Road","Balaji Enclave"));
	cmbAreas.getItems().addAll(items);
	}
	//-----------------------------------------------
	
	void doFillPapers()
	{try {
		pst=con.prepareStatement("select paper from papers");
		ResultSet table=pst.executeQuery();
		while(table.next())
		{  	cmbPapers.getItems().add(table.getString(1)); } } 
	catch (SQLException e) { e.printStackTrace(); }
	}

	//-----------------------------------------------
	@FXML
	void initialize() 
	{	con=billmanager.MySQLConnector.doConnect();
	if(con==null) System.out.println("Connection Problem");
	else System.out.println("Connected");
	doFillAreas();
	doFillPapers();
	createtable();
	}
}
