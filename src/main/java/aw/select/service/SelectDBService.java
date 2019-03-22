package aw.select.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import aw.select.domain.ResponseData;


@Service
public class SelectDBService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectDBService.class);

	
	 	public SelectDBService() {
		
	}
	 	


	 	/*
	 	 * sqlserver数据库查询类
	 	 * 并通过set方法将查询到的结果赋值给实体类
	 	 */
	    
	   public ResponseData reSetResponseData(Connection connection,String barcode){
		   PreparedStatement statement = null;
		   ResultSet resultSet = null;
		   ResponseData responseData = null;
		   /*
		    * 根据接收的barcode判断商品类型
		    * 1、13位普通商品
		    * 2、13位，且以28开头的不定重肉类
		    */
		   String preCode = barcode.substring(0, 2);	//截取barcode前两位(前缀码)
		   LOGGER.info("preCode:"+preCode);
		   String midCode = barcode.substring(0, 6);	//截取barcode中间3-6位
		   LOGGER.info("midCode:"+midCode);
		   String finallCode = null;
		   Integer flag = null;
		   Float weight = 0f;
		   /*
		    * flg为判断商品类型的值
		    * 1：普通商品
		    * 2：不定重肉类
		    */
		   if(barcode.length()==13){
			   LOGGER.info("Length:"+barcode.length());
			  weight = (Float.parseFloat(barcode.substring(6, 12))/1000);
			  LOGGER.info("Weight:"+weight);
			   if (preCode.equals("28")){
				   //barcode前缀为28，为不定重肉类
				    flag = 2;
				   finallCode = midCode;
				   
				   
			   }else{
			   flag = 1;
			   finallCode = barcode;
			   }
		   }else{
			   //这个是测试用，不是13位的也可以扫出，以后规定位数则删除
			   flag = 1;
			   finallCode = barcode;
		   }
		   LOGGER.info("Finall:"+finallCode);
		   String sql = "select szName name,szDesc info,POSIdentity.dPackingUnitPriceAmount normalprice,cast(0 as float) as discount,"
	 		+ "ProdRangeStoreGroupPosIdentityAffiliation.dPackingUnitPriceAmount promoprice "
	 		+ "from POSIdentity inner join ItemLookupCode on "
	 		+ "POSIdentity.szPOSItemID = ItemLookupCode.szPOSItemID "
	 		+ "inner join ProdRangeStoreGroupPosIdentityAffiliation on "
	 		+ "ProdRangeStoreGroupPosIdentityAffiliation.szPOSItemID = ItemLookupCode.szPOSItemID "
	 		+ "inner join Item on Item.szItemID=POSIdentity.szItemID where szItemLookupCode ='" + finallCode + "'";
		   

		   try {
			   statement = connection.prepareStatement(sql);

				resultSet = statement.executeQuery();
				LOGGER.info("start to select...");
				 while (resultSet.next()) {
					responseData = new ResponseData();
					responseData.setName(resultSet.getString("name"));
					responseData.setInfo(resultSet.getString("info"));
					responseData.setNormalPrice(resultSet.getDouble("normalprice"));
					responseData.setPromoPrice(resultSet.getDouble("promoprice"));
					double discount = (resultSet.getDouble("normalprice")-resultSet.getDouble("promoprice"));
					//discount取小数后两位
					discount = (double) Math.round(discount * 100) / 100;
					responseData.setDiscount(discount);
					responseData.setWeight(weight);
					responseData.setFlagCode(flag);
				 }
					
				 if (resultSet != null) {
					 resultSet.close();
		            }
		            if (statement != null) {
		                statement.close();
		            }
		          return  responseData;
		} catch (Exception e) {
			e.getMessage();
			return null;
			
			
			
				
		}
		  
	   
		  
}

	   
	   
}
