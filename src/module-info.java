module Newspaper_Assured {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens papermaster to javafx.graphics, javafx.fxml;
	opens hawkermanager to javafx.graphics, javafx.fxml;
	opens customermanager to javafx.graphics, javafx.fxml;
	opens billmanager to javafx.graphics, javafx.fxml;
	opens billcollector to javafx.graphics, javafx.fxml;
	opens hawkertable to javafx.graphics, javafx.fxml,javafx.base;
	opens customerspanel to javafx.graphics, javafx.fxml,javafx.base;
	opens billboard to javafx.graphics, javafx.fxml,javafx.base;
	opens meetus to javafx.graphics, javafx.fxml,javafx.base;
	opens adminlogin to javafx.graphics, javafx.fxml,javafx.base;
	opens admindesk to javafx.graphics, javafx.fxml,javafx.base;
	opens bankworks to javafx.graphics, javafx.fxml,javafx.base;
}
