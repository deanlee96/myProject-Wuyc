package aw.select.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RequestData {
/*
 * 查价机扫描
 */
	@Id
//	@Column(name = "id")  
    @GeneratedValue(strategy=GenerationType.IDENTITY)//sqlserver对应的自增长策略  
	private Long id;
//	@Column(name = "barcode")
	private String barcode;
//	@Column(name = "sktype")
	private String sktype;
	
	public RequestData() {
		
	}

	public RequestData(String barcode, String sktype) {
		
		this.barcode = barcode;
		this.sktype = sktype;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getSktype() {
		return sktype;
	}

	public void setSktype(String sktype) {
		this.sktype = sktype;
	}

	@Override
	public String toString() {
		return "RequestData [barcode=" + barcode + ", sktype=" + sktype + "]";
	}
	
	
	
	
	
	
}
