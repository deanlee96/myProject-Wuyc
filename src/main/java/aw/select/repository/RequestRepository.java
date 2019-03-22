/*
 * jpa仓库
 */
//package aw.select.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import aw.select.domain.ResponseData;
//import aw.select.domain.TestData;
//
//
//
//public interface RequestRepository extends JpaRepository<ResponseData, String> {
//	
//	/*
//	 * 根据barcode查询商品的信息
//	 * TODO查询语句有问题需要改
//	 */
//	 @Query(value="select szName name,szDesc info,POSIdentity.dPackingUnitPriceAmount normalprice,cast(0 as float) as discount，"
//	 		+ "ProdRangeStoreGroupPosIdentityAffiliation.dPackingUnitPriceAmount promoprice "
//	 		+ "from POSIdentity inner join ItemLookupCode on "
//	 		+ "POSIdentity.szPOSItemID = ItemLookupCode.szPOSItemID "
//	 		+ "inner join ProdRangeStoreGroupPosIdentityAffiliation on "
//	 		+ "ProdRangeStoreGroupPosIdentityAffiliation.szPOSItemID = ItemLookupCode.szPOSItemID "
//	 		+ "inner join Item on Item.szItemID=POSIdentity.szItemID where szItemLookupCode =?1",nativeQuery = true)
////	   List<ResponseData> findByBarCode(@Param("barcode") String barcode);
//	ResponseData findByBarCode(@Param("barcode")String barcode);
//	 
//	 /*
//	  * 
//	  * 测试用
//	  */
////	 @Query(value="select a.bLocked from Item a where a.szItemID=:id ")
////	 public String getId(@Param("id") String id);
////	   
////	 @Query("select t from ResponseData t where t.barcode= :barcode")
////	 ResponseData findByBarCode2(@Param("barcode")String barcode);
////	 
////	 @Query("select t from TestData t where t.id = :id")
////	 TestData test(@Param("id")String id);
//
//}
