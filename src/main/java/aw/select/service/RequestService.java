package aw.select.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import aw.select.domain.ResponseData;
import aw.select.interfaces.SelectPrice;

@Service
public class RequestService implements SelectPrice{
	private static final Logger LOGGER = LoggerFactory.getLogger(SelectDBService.class);
	
	private String url="jdbc:sqlserver://10.1.7.64:1433;DatabaseName=TPCentralDB";
	private String user="sa";
	private String password="P@sswordSQL12#";
	
	/*
	 * 连接数据库
	 */
	
	public ResponseData setData(String barcode){
		Connection connection =null;
		try {
			connection=DriverManager.getConnection(url, user, password);
			SelectDBService selectDBService = new SelectDBService();
			return selectDBService.reSetResponseData(connection, barcode);
		} catch (SQLException e) {
			
			e.getSQLState();
			return null;
		}
		
	}
	
	
	
	/*
	 * 根据barcode、sktype逻辑判断返回值的方法
	 */

	@Override
	public String selectPrice(ResponseData responseData, String sktype) {
		
		
		
				/*
				 * 根据barcode查询商品的信息
				 */
			//	RequestData requestData = new RequestData(barcode,sktype);
				
				/*TODO
				 * 判断sktype传入的参数的值
				 * 暂时不知道sktype有什么用
				 */
				
				//接收数据库查询到的商品信息
//				ResponseData resultData = 
//					res.findByBarCode(barcode);
				try{
				Double resultNormalPrice = responseData.getNormalPrice();
				Double resultPromoPrice = responseData.getPromoPrice();
				int flagCode = responseData.getFlagCode();
				
			
				/*
				 * 如何判断数据库查询的商品以哪种类型返回
				 * 判断逻辑：比较resultNormalPrice，resultNormalPrice的价格
				 * if数据库查询的数据为普通商品则返回normal页面
				 * if数据库查询的数据是折扣商品则返回discount页面
				 */
				
				/*
				 * 普通商品
				 */
				
				if (resultPromoPrice >= resultNormalPrice && flagCode == 1){
					String normal = getResponseNormalPriceHtml(responseData.getName(),responseData.getInfo(),responseData.getPromoPrice());
					return  normal;
				/*
				 * 折扣商品
				 */
				}else if (resultPromoPrice < resultNormalPrice && flagCode == 1) {

					String discount= getResponseDiscountPriceHtml(responseData.getName(),responseData.getInfo(),responseData.getNormalPrice(),responseData.getDiscount(),responseData.getPromoPrice());
					
					return  discount;
				}
				
				
				if (resultPromoPrice >= resultNormalPrice && flagCode == 2){
					Double totalPrice = responseData.getPromoPrice()*responseData.getWeight();
					totalPrice = (double) Math.round(totalPrice * 100) / 100;
					String normalMeat = getResponseNormalMeatHtml(responseData.getName(),responseData.getInfo(),responseData.getPromoPrice(),responseData.getWeight(),totalPrice );
					return  normalMeat;
				/*
				 * 折扣商品
				 */
				}else if (resultPromoPrice < resultNormalPrice && flagCode == 2) {
					Double totalPrice = responseData.getPromoPrice()*responseData.getWeight();
					totalPrice = (double) Math.round(totalPrice * 100) / 100;
					String discountMeat= getResponseDiscountMeatHtml(responseData.getName(),responseData.getInfo(),responseData.getNormalPrice(),responseData.getDiscount(),responseData.getPromoPrice(),responseData.getWeight(),totalPrice);
					
					return  discountMeat;
				}
				
				
				/*TODO
				 * 会员商品（待确认）
				 */
				else return null;
				
			
				}catch(NullPointerException e){
					LOGGER.info("商品未找到，返回notfound页面");
					return getItemNotFound();
					
				}
	
	}

	



	private String getResponseNormalPriceHtml(String itemName, String info,Double price ){
		
		return " <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	\r\n" + 
				"	<title>Scantech-ID		Tbl Products	</title>\r\n" + 
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"icon\" />" +
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />" +
				"	<script type=\"text/javascript\" src=\"/auth/js/jquerymin.js\">" + 
				"	</script><script type=\"text/javascript\" src=\"/auth/js/bg.js\"></script>" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"/auth/css/cake.40.css\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"/auth/img/bg.jpg\" id=\"background\" alt=\"\" />	" + 
				"<div id=\"container\">\r\n" + 
				"	<div id=\"header\"></div>\r\n" + 
				"	<div id=\"content\">\r\n" + 
				"		<div class=\"tblProducts\">\r\n" + 
				"			<table>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan='2' class=\"transback\" style=\"color:#ffc701\">		"+itemName+"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td class=\"imag\"><img src=\"/auth/files/kleenex_front.png\" class=\"imag\" alt=\"\" />		</td>\r\n" + 
				"					<td>\r\n" + 
				"						<div class=\"pic\">\r\n" + 
				"							<br/>" + info + "			\r\n" + 
				"						</div>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan=\"2\" class=\"transback\">			Price: &#165;"+ price +"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<div id=\"footer\"></div>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"			$(document).ready(function() {\r\n" + 
				"				$(\"#background\").fullBg();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		

	}

	private String getResponseDiscountPriceHtml(String itemName, String info,Double normalprice,Double discount,Double promoprice){
		 return " <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	\r\n" + 
				"	<title>Scantech-ID		Tbl Products	</title>\r\n" + 
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"icon\" />" +
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />" +
				"	<script type=\"text/javascript\" src=\"/auth/js/jquerymin.js\">" + 
				"	</script><script type=\"text/javascript\" src=\"/auth/js/bg.js\"></script>" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"/auth/css/cake.40.css\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"/auth/img/bg.jpg\" id=\"background\" alt=\"\" />	" + 
				"<div id=\"container\">\r\n" + 
				"	<div id=\"header\"></div>\r\n" + 
				"	<div id=\"content\">\r\n" + 
				"		<div class=\"tblProducts\">\r\n" + 
				"			<table>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan='2' class=\"transback\" style=\"color:#ffc701\">		"+itemName+"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td class=\"imag\"><img src=\"/auth/files/kleenex_front.png\" class=\"imag\" alt=\"\" />		</td>\r\n" + 
				"					<td>\r\n" + 
				"						<div class=\"pic\">\r\n" + 
				"							" + info + "	<br/>"+"<b>Price: &#165;</b>"+ normalprice +	"<br/>"+"<b>Discount: &#165;</b>"+discount+"<br/><br/>\r\n" + 
				"						</div>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan=\"2\" class=\"transback\">			Sales Price: &#165;"+ promoprice +"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<div id=\"footer\"></div>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"			$(document).ready(function() {\r\n" + 
				"				$(\"#background\").fullBg();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
	}
	
	private String getItemNotFound(){
		return" <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	\r\n" + 
				"	<title>Scantech-ID		Tbl Products	</title>\r\n" + 
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"icon\" />" +
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />" +
				"	<script type=\"text/javascript\" src=\"/auth/js/jquerymin.js\">" + 
				"	</script><script type=\"text/javascript\" src=\"/auth/js/bg.js\"></script>" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"/auth/css/cake.40.css\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"/auth/img/bg.jpg\" id=\"background\" alt=\"\" />	" + 
				"<div id=\"container\">\r\n" + 
				"<div class=\"notfound\">\r\n" + 
				"<b>NOT</b><br/>"+"<b>FOUND!</b><br/>\r\n"+
				"</div>\r\n" +
				"<script type=\"text/javascript\">\r\n" + 
				"			$(document).ready(function() {\r\n" + 
				"				$(\"#background\").fullBg();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
	}
	
	
	/*
	 * 原价不定重肉类
	 */
	private String getResponseNormalMeatHtml(String itemName, String info,Double price,Float weight,Double totalPrice ){
		 return " <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	\r\n" + 
				"	<title>Scantech-ID		Tbl Products	</title>\r\n" + 
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"icon\" />" +
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />" +
				"	<script type=\"text/javascript\" src=\"/auth/js/jquerymin.js\">" + 
				"	</script><script type=\"text/javascript\" src=\"/auth/js/bg.js\"></script>" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"/auth/css/cake.40.css\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"/auth/img/bg.jpg\" id=\"background\" alt=\"\" />	" + 
				"<div id=\"container\">\r\n" + 
				"	<div id=\"header\"></div>\r\n" + 
				"	<div id=\"content\">\r\n" + 
				"		<div class=\"tblProducts\">\r\n" + 
				"			<table>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan='2' class=\"transback\" style=\"color:#ffc701\">		"+itemName+"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td class=\"imag\"><img src=\"/auth/files/kleenex_front.png\" class=\"imag\" alt=\"\" />		</td>\r\n" + 
				"					<td>\r\n" + 
				"						<div class=\"pic\">\r\n" + 
				"							<br/>" + info + "<br/>"+"<b>Price: &#165;</b>"+ price +	"  /kg<br/>"+"<b>Weight: </b>"+weight+"  kg<br/><br/>\r\n"			 + 
				"						</div>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan=\"2\" class=\"transback\">			Total: &#165;"+ totalPrice +"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<div id=\"footer\"></div>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"			$(document).ready(function() {\r\n" + 
				"				$(\"#background\").fullBg();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
	}
	
	/*
	 * 折扣不定重肉类
	 */
	
	private String getResponseDiscountMeatHtml(String itemName, String info, Double normalPrice, Double discount,
			Double promoPrice, float weight,Double totalPrice) {
		
		return " <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />	\r\n" + 
				"	<title>Scantech-ID		Tbl Products	</title>\r\n" + 
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"icon\" />" +
				"	<link href=\"/auth/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />" +
				"	<script type=\"text/javascript\" src=\"/auth/js/jquerymin.js\">" + 
				"	</script><script type=\"text/javascript\" src=\"/auth/js/bg.js\"></script>" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"/auth/css/cake.40.css\" />" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"/auth/img/bg.jpg\" id=\"background\" alt=\"\" />	" + 
				"<div id=\"container\">\r\n" + 
				"	<div id=\"header\"></div>\r\n" + 
				"	<div id=\"content\">\r\n" + 
				"		<div class=\"tblProducts\">\r\n" + 
				"			<table>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan='2' class=\"transback\" style=\"color:#ffc701\">		"+itemName+"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<td class=\"imag\"><img src=\"/auth/files/kleenex_front.png\" class=\"imag\" alt=\"\" />		</td>\r\n" + 
				"					<td>\r\n" + 
				"						<div class=\"pic\">\r\n" + 
				"							<br/>" + info + "<br/>"+"<b>Price: &#165;</b>"+ normalPrice +	"  /kg<br/>"+"<b>Discount: &#165;</b>"+discount+"  /kg<br/>"+"<b>SalePrice: &#165;</b>"+promoPrice+"  /kg<br/>"+"<b>Weight: </b>"+weight+"  kg<br/><br/>\r\n"			 + 
				"						</div>\r\n" + 
				"					</td>\r\n" + 
				"				</tr>\r\n" + 
				"				<tr>\r\n" + 
				"					<th colspan=\"2\" class=\"transback\">			Total: &#165;"+ totalPrice +"		</th>\r\n" + 
				"				</tr>\r\n" + 
				"			</table>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<div id=\"footer\"></div>\r\n" + 
				"</div>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"			$(document).ready(function() {\r\n" + 
				"				$(\"#background\").fullBg();\r\n" + 
				"			});\r\n" + 
				"			\r\n" + 
				"</script>\r\n" + 
				"</body>\r\n" + 
				"</html>";
	}
	
	
	
	

}
