package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Product;
import edu.bjtu.crm.sso.domain.model.RecordYear;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecordYearMapper {

    @Insert("INSERT INTO crm_sso_record_year_user (user_username, price, income, year) " +
            "VALUE (#{username}, #{price}, #{income}, #{year})")
    int addRecordYearUser(RecordYear record);

    @Insert("INSERT INTO crm_sso_record_year_consumer (consumer_phone, price, year) " +
            "VALUE (#{phone}, #{price}, #{year})")
    int addRecordYearConsumer(RecordYear record);

    @Insert("INSERT INTO crm_sso_record_year_product (product_name, price, year) " +
            "VALUE (#{name}, #{price}, #{year})")
    int addRecordYearProduct(RecordYear record);

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

    @Select("<script>SELECT user_username as username, price, income, year" +
            " FROM crm_sso_record_year_user " +
            "<where>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.username != null and record.username != ''\">" +
            "            AND user_username = #{record.username}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordYear findRecordYearUserByRecord(@Param("record") RecordYear record);

    @Select("<script>SELECT consumer_phone as phone, price, year" +
            " FROM crm_sso_record_year_consumer " +
            "<where>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.phone != null and record.phone != ''\">" +
            "            AND consumer_phone = #{record.phone}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordYear findRecordYearConsumerByRecord(@Param("record") RecordYear record);

    @Select("<script>SELECT product_name as name, price, year" +
            " FROM crm_sso_record_year_product " +
            "<where>" +
            "        <if test=\"record.year != -1\">" +
            "            AND year = #{record.year}" +
            "        </if>" +
            "        <if test=\"record.name != null and record.name != ''\">" +
            "            AND product_name = #{record.name}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    RecordYear findRecordYearProductByRecord(@Param("record") RecordYear record);


    @Select("<script>SELECT user_username as username, price, income, year" +
            " FROM crm_sso_record_year_user " +
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
    List<RecordYear> findRecordYearUserByRecordAndTime(@Param("record") RecordYear record, @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime);

    @Select("<script>SELECT consumer_phone as phone, price, year" +
            " FROM crm_sso_record_year_consumer " +
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
    List<RecordYear> findRecordYearConsumerByRecordAndTime(@Param("record") RecordYear record, @Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime);

    @Select("<script>SELECT product_name as name, price, year" +
            " FROM crm_sso_record_year_product " +
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
    List<RecordYear> findRecordYearProductByRecordAndTime(@Param("record") RecordYear record, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Update("UPDATE crm_sso_record_year_user set price = #{price}, income = #{income}" +
            " WHERE user_username = #{username}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordYearUesrByRecord(RecordYear record);

    @Update("UPDATE crm_sso_record_year_consumer set price = #{price}" +
            " WHERE consumer_phone = #{phone}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordYearConsumerByRecord(RecordYear record);

    @Update("UPDATE crm_sso_record_year_product set price = #{price}" +
            " WHERE product_name = #{name}" +
            " AND year = #{year}" +
            " AND is_delete = false")
    int updateRecordYearProductByRecord(RecordYear record);
}
