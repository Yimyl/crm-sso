package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Consumer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsumerMapper {

    @Insert("INSERT INTO crm_sso_consumer (phone, name, vip_grade,email,balance,consume) " +
            "VALUE (#{phone}, #{name}, #{vipGrade}, #{email}, #{balance}, #{consume})")
    int addConsumer(Consumer consumer);

    @Select("SELECT id,phone, name, vip_grade as vipGrade,email,balance,consume " +
            "FROM crm_sso_consumer " +
            "WHERE name = #{name} AND " +
            "is_delete = false")
    Consumer findConsumerByName(String username);

    @Select("SELECT id,phone, name, vip_grade as vipGrade,email,balance,consume " +
            "FROM crm_sso_consumer " +
            "WHERE id = #{id} AND " +
            "is_delete = false")
    Consumer findConsumerById(long id);

    @Select("SELECT id,phone, name, vip_grade as vipGrade,email,balance,consume " +
            "FROM crm_sso_consumer " +
            "WHERE phone = #{phone} AND " +
            "is_delete = false")
    Consumer findConsumerByPhone(String phone);


    @Select("<script>SELECT id,phone, name, vip_grade as vipGrade,email,balance,consume" +
            " FROM crm_sso_consumer " +
            "<where>" +
            "        <if test=\"id != '-1'\">" +
            "            AND id = #{id}" +
            "        </if>" +
            "        <if test=\"name != null and name != ''\">" +
            "            AND name = #{name}" +
            "        </if>" +
            "        <if test=\"phone != null and phone != ''\">" +
            "            AND phone = #{phone}" +
            "        </if>" +
            "        <if test=\"vipGrade != '-1'\">" +
            "            AND vip_grade = #{vipGrade}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<Consumer> findConsumerByConsumer(Consumer consumer);


    @Update("UPDATE crm_sso_consumer set phone = #{phone}, name = #{name},vip_grade = #{vipGrade}, email = #{email}" +
            " WHERE phone = #{phone} and is_delete = false")
    int updateConsumer(Consumer consumer);

    @Update("UPDATE crm_sso_consumer set is_delete = true " +
            " WHERE phone = #{phone} and is_delete = false")
    int deleteConsumerByPhone(String phone);

    @Select("SELECT id,phone, name, vip_grade as vipGrade,email,balance,consume " +
            "FROM crm_sso_consumer " +
            "WHERE phone = #{phone} AND " +
            "is_delete = false" +
            " FOR UPDATE")
    Consumer findConsumerForUpdate(String phone);

    @Update("UPDATE crm_sso_consumer set balance = #{balance} " +
            " WHERE phone = #{phone} and is_delete = false")
    int updateBalance(String phone, double balance);

    @Update("UPDATE crm_sso_consumer set consume = #{consume} " +
            " WHERE phone = #{phone} and is_delete = false")
    int updateConsume(String phone, double consume);
}
