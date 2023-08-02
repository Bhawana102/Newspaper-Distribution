package customerspanel;

public class customertableBean
//	create table customers(mobileno varchar(10),cname varchar(50),email varchar(40)
//    		caddress varchar(100),area varchar(50),hawker varchar(40),dos date,spapers varchar(150),sprices varchar(100));
{
	String mobileno;
	String cname;
	String caddress;
	String area;
	String dos;
	String spapers;
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDos() { return dos; }
	public void setDos(String dos) {	this.dos = dos; }
	public String getSpapers() { return spapers; }
	public void setSpapers(String spapers) { this.spapers = spapers; }
	public customertableBean(String mobileno, String cname, String area, String dos,String spapers)
	{	super();
		this.mobileno = mobileno;
		this.cname = cname;
		this.area = area;
		this.dos = dos;
		this.spapers = spapers;
	}
	public customertableBean() {
		super();
	}
	
}
