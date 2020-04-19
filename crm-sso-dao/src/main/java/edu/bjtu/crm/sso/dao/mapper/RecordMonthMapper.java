package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.domain.model.RecordMonth;
import edu.bjtu.crm.sso.domain.model.RecordMonth;
import edu.bjtu.crm.sso.domain.model.RecordMonth;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecordMonthMapper {

    @Insert("INSERT INTO crm_sso_record_month_user (user_username, price, income, month, year) " +
            "VALUE (#{username}, #{price}, #{income}, #{month}, #{year})")
    int addRecordMonthUser(RecordMonth record);

    @Insert("INSERT INTO crm_sso_record_month_consumer (consumer_phone, price, month, year) " +
            "VALUE (#{phone}, #{price}, #{month}, #{year})")
    int addRecordMonthConsumer(RecordMonth record);

    @Insert("INSERT INTO crm_sso_record_month_product (product_name, price, month, year) " +
            "VALUE (#{name}, #{price}, #{month}, #{year})")
    int addRecordMonthProduct(RecordMonth record);

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

    @Select("<script>SELECT user_username as username, price, income, month, year" +
            " FROM crm_sso_record_month_user " +
            "<where>" +
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
    RecordMonth findRecordMonthUserByRecord(@Param("record") RecordMonth record);

    @Select("<script>SELECT consumer_phone as phone, price, month, year" +
            " FROM crm_sso_record_month_consumer " +
            "<where>" +
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
    RecordMonth findRecordMonthConsumerByRecord(@Param("record") RecordMonth record);

    @Select("<script>SELECT product_name as name, price, month, year" +
            " FROM crm_sso_record_month_product " +
            "<where>" +
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
    RecordMonth findRecordMonthProductByRecord(@Param("record") RecordMonth record);


    @Select("<script>SELECT user_username as username, price, income, month, year" +
            " FROM crm_sso_record_month_user " +
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
    List<RecordMonth> findRecordMonthUserByRecordAndTime(@Param("record") RecordMonth record, @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    @Select("<script>SELECT consumer_phone as phone, price, month, year" +
            " FROM crm_sso_record_month_consumer " +
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
    List<RecordMonth> findRecordMonthConsumerByRecordAndTime(@Param("record") RecordMonth record, @Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    @Select("<script>SELECT product_name as name, price, month, year" +
            " FROM crm_sso_record_month_product " +
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
    List<RecordMonth> findRecordMonthProductByRecordAndTime(@Param("record") RecordMonth record, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Update("UPDATE crm_sso_record_month_user set price = #{price}, income = #{income}" +
            " WHERE user_username = #{username}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordMonthUesrByRecord(RecordMonth record);

    @Update("UPDATE crm_sso_record_month_consumer set price = #{price}" +
            " WHERE consumer_phone = #{phone}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordMonthConsumerByRecord(RecordMonth record);

    @Update("UPDATE crm_sso_record_month_product set price = #{price}" +
            " WHERE product_name = #{name}" +
            " AND month = #{month}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordMonthProductByRecord(RecordMonth record);
}
