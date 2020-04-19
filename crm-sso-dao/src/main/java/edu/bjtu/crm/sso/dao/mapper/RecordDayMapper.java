package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.domain.model.Record;
import edu.bjtu.crm.sso.domain.model.RecordDay;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecordDayMapper {

    @Insert("INSERT INTO crm_sso_record_day_user (user_username, price, income, day, month, year) " +
            "VALUE (#{username}, #{price}, #{income}, #{day}, #{month}, #{year})")
    int addRecordDayUser(RecordDay record);

    @Insert("INSERT INTO crm_sso_record_day_consumer (consumer_phone, price, day, month, year) " +
            "VALUE (#{phone}, #{price}, #{day}, #{month}, #{year})")
    int addRecordDayConsumer(RecordDay record);

    @Insert("INSERT INTO crm_sso_record_day_product (product_name, price, day, month, year) " +
            "VALUE (#{name}, #{price}, #{day}, #{month}, #{year})")
    int addRecordDayProduct(RecordDay record);

//    @Select("SELECT id, name, cost, price, commission_rate as commissionRate " +
//            "FROM crm_sso_product " +
//            "WHERE name = #{name} AND " +
//            "is_delete = false")
//    Product findProductByName(String username);

//    @Select("SELECT user_username, consumer_phone, product_name, price, discount, real_price,commission_rate " +
//            "FROM crm_sso_record " +
//            "WHERE id = #{id} AND " +
//            "is_delete = false")
//    Product findRecordById(long id);

    @Select("<script>SELECT user_username as username, price, income, day, month, year" +
            " FROM crm_sso_record_day_user " +
            "<where>" +
            "        <if test=\"record.day != -1\">" +
            "            AND day = #{record.day}" +
            "        </if>" +
            "        <if test=\"record.month != -1\">" +
            "            AND month = #{record.month}" +
            "        </if>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.username != null and record.username != ''\">" +
            "            AND user_username = #{record.username}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordDay findRecordDayUserByRecord(@Param("record") RecordDay record);

    @Select("<script>SELECT consumer_phone as phone, price, day, month, year" +
            " FROM crm_sso_record_day_consumer " +
            "<where>" +
            "        <if test=\"record.day != -1\">" +
            "            AND day = #{record.day}" +
            "        </if>" +
            "        <if test=\"record.month != -1\">" +
            "            AND month = #{record.month}" +
            "        </if>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.phone != null and record.phone != ''\">" +
            "            AND consumer_phone = #{record.phone}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordDay findRecordDayConsumerByRecord(@Param("record") RecordDay record);

    @Select("<script>SELECT product_name as name, price, day, month, year" +
            " FROM crm_sso_record_day_product " +
            "<where>" +
            "        <if test=\"record.day != -1\">" +
            "            AND day = #{record.day}" +
            "        </if>" +
            "        <if test=\"record.month != -1\">" +
            "            AND month = #{record.month}" +
            "        </if>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.name != null and record.name != ''\">" +
            "            AND product_name = #{record.name}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordDay findRecordDayProductByRecord(@Param("record") RecordDay record);



    @Select("<script>SELECT user_username as username, price, income, day, month, year" +
            " FROM crm_sso_record_day_user " +
            "<where>" +
            "        <if test=\"record.username != null and record.username != ''\">" +
            "            AND user_username = #{record.username}" +
            "        </if>" +
            "        <if test=\"startTime != null\">" +
            "            AND create_time &gt;= #{startTime}" +
            "        </if>" +
            "        <if test=\"endTime != null\">" +
            "            AND create_time &lt; #{endTime}}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<RecordDay> findRecordDayUserByRecordAndTime(@Param("record") RecordDay record, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Select("<script>SELECT consumer_phone as phone, price, day, month, year" +
            " FROM crm_sso_record_day_consumer " +
            "<where>" +
            "        <if test=\"record.phone != null and record.phone != ''\">" +
            "            AND consumer_phone = #{record.phone}" +
            "        </if>" +
            "        <if test=\"startTime != null\">" +
            "            AND create_time &gt;= #{startTime}" +
            "        </if>" +
            "        <if test=\"endTime != null\">" +
            "            AND create_time &lt; #{endTime}}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<RecordDay> findRecordDayConsumerByRecordAndTime(@Param("record") RecordDay record, @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    @Select("<script>SELECT product_name as name, price, day, month, year" +
            " FROM crm_sso_record_day_product " +
            "<where>" +
            "        <if test=\"record.name != null and record.name != ''\">" +
            "            AND product_name = #{record.name}" +
            "        </if>" +
            "        <if test=\"startTime != null\">" +
            "            AND create_time &gt;= #{startTime}" +
            "        </if>" +
            "        <if test=\"endTime != null\">" +
            "            AND create_time &lt; #{endTime}}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<RecordDay> findRecordDayProductByRecordAndTime(@Param("record") RecordDay record, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Update("UPDATE crm_sso_record_day_user set price = #{price}, income = #{income}" +
            " WHERE user_username = #{username}" +
            " AND day = #{day}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordDayUesrByRecord(RecordDay record);

    @Update("UPDATE crm_sso_record_day_consumer set price = #{price}" +
            " WHERE consumer_phone = #{phone}" +
            " AND day = #{day}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordDayConsumerByRecord(RecordDay record);

    @Update("UPDATE crm_sso_record_day_product set price = #{price}" +
            " WHERE product_name = #{name}" +
            " AND day = #{day}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordDayProductByRecord(RecordDay record);

//    @Update("UPDATE crm_sso_product set name = #{name}, cost = #{cost},price = #{price}, commission_rate = #{commissionRate}" +
//            " WHERE name = #{name} and is_delete = false")
//    int updateProduct(Product product);
//
//    @Update("UPDATE crm_sso_product set is_delete = true " +
//            " WHERE name = #{name} and is_delete = false")
//    int deleteRecordByName(String name);
}
