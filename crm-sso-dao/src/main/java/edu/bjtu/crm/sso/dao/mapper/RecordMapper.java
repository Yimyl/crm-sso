package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.domain.model.Record;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert("INSERT INTO crm_sso_record (user_username, consumer_phone, product_name, price, discount, real_price,commission_rate) " +
            "VALUE (#{username}, #{phone}, #{name}, #{price}, #{discount}, #{realPrice}, #{commissionRate})")
    int addRecord(Record record);

//    @Select("SELECT id, name, cost, price, commission_rate as commissionRate " +
//            "FROM crm_sso_product " +
//            "WHERE name = #{name} AND " +
//            "is_delete = false")
//    Product findProductByName(String username);

    @Select("SELECT user_username as username, consumer_phone as phone, product_name as name, price, discount, real_price,commission_rate , create_time as createTime" +
            "FROM crm_sso_record " +
            "WHERE id = #{id} AND " +
            "is_delete = false")
    Record findRecordById(long id);



    @Select("<script>SELECT user_username as username, consumer_phone as phone, product_name as name, price, discount, real_price as realPrice, " +
            "commission_rate as commissionRate, create_time as createTime" +
            " FROM crm_sso_record " +
            "<where>" +
            "        <if test=\"record.username != null and record.username != ''\">" +
            "            AND user_username = #{record.username}" +
            "        </if>" +
            "        <if test=\"record.phone != null and record.phone != ''\">" +
            "            AND consumer_phone = #{record.phone}" +
            "        </if>" +
            "        <if test=\"record.name != null and record.name != ''\">" +
            "            AND product_name = #{record.name}" +
            "        </if>" +
            "        <if test=\"startTime != null\">" +
            "            AND create_time &gt;= #{startTime}" +
            "        </if>" +
            "        <if test=\"endTime != null\">" +
            "            AND create_time &lt; #{endTime}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<Record> findRecordByRecord(@Param("record") Record record, @Param("startTime") Date startTime, @Param("endTime") Date endTime);


//    @Update("UPDATE crm_sso_product set name = #{name}, cost = #{cost},price = #{price}, commission_rate = #{commissionRate}" +
//            " WHERE name = #{name} and is_delete = false")
//    int updateProduct(Product product);
//
//    @Update("UPDATE crm_sso_product set is_delete = true " +
//            " WHERE name = #{name} and is_delete = false")
//    int deleteRecordByName(String name);
}
