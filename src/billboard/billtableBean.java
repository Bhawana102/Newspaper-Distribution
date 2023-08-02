package billboard;

public class billtableBean {
	String mobileno;
	String datefrom;
	String dateto;
	Float bill;
	int billstatus;
	public billtableBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public billtableBean(String mobileno, String datefrom, String dateto, Float bill, int billstatus) {
		super();
		this.mobileno = mobileno;
		this.datefrom = datefrom;
		this.dateto = dateto;
		this.bill = bill;
		this.billstatus = billstatus;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public Float getBill() {
		return bill;
	}
	public void setBill(Float bill) {
		this.bill = bill;
	}
	public int getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(int billstatus) {
		this.billstatus = billstatus;
	}

}
