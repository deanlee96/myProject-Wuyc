package aw.select.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/*
 * 查价机扫描返回数据实体类
 */

@Entity
public class ResponseData {

	@Id
//	@Column(name = "id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)//sqlserver对应的自增长策略  
	private Long id;
//	@Column(name = "name")
	private String name;
//	@Column(name = "info")
	private String info;
//	@Column(name = "normalPrice")
	private Double normalPrice;
//	@Column(name = "discount")
	private Double discount;

//	@Column(name = "promoPrice")
	private Double promoPrice;
	
	//商品类型代码
	private int flagCode;
	
	//商品重量
	private float weight;
	
//	@Column(name = "barcode")
//	private String barcode;
	
	public ResponseData() {
		
	}

	

	public ResponseData(Long id, String name, String info, Double normalPrice, Double discount, Double promoPrice,Integer flagCode,Float weight) {
		
		this.id = id;
		this.name = name;
		this.info = info;
		this.normalPrice = normalPrice;
		this.discount = discount;
		this.promoPrice = promoPrice;
		this.flagCode = flagCode;
		this.weight = weight;
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}



	public Double getNormalPrice() {
		return normalPrice;
	}



	public void setNormalPrice(Double normalPrice) {
		this.normalPrice = normalPrice;
	}



	public Double getDiscount() {
		return discount;
	}



	public void setDiscount(Double discount) {
		this.discount = discount;
	}



	public Double getPromoPrice() {
		return promoPrice;
	}



	public void setPromoPrice(Double promoPrice) {
		this.promoPrice = promoPrice;
	}

	
	


	public int getFlagCode() {
		return flagCode;
	}



	public void setFlagCode(int flagCode) {
		this.flagCode = flagCode;
	}



	public float getWeight() {
		return weight;
	}



	public void setWeight(float weight) {
		this.weight = weight;
	}



	@Override
	public String toString() {
		return "ResponseData [id=" + id + ", name=" + name + ", info=" + info + ", normalPrice=" + normalPrice
				+ ", discount=" + discount + ", promoPrice=" + promoPrice + ", flagCode=" + flagCode + ", weight="
				+ weight + "]";
	}



	

	



	
	

	
	
	
	
}
