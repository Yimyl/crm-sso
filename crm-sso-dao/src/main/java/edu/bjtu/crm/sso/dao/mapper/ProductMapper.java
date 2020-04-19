package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO crm_sso_product (name, cost, price, commission_rate) " +
            "VALUE (#{name}, #{cost}, #{price}, #{commissionRate})")
    int addProduct(Product product);

    @Select("SELECT id, name, cost, price, commission_rate as commissionRate " +
            "FROM crm_sso_product " +
            "WHERE name = #{name} AND " +
            "is_delete = false")
    Product findProductByName(String username);

    @Select("SELECT id,name, cost, price, commission_rate as commissionRate " +
            "FROM crm_sso_product " +
            "WHERE id = #{id} AND " +
            "is_delete = false")
    Product findProductById(long id);



    @Select("<script>SELECT id,name, cost, price, commission_rate as commissionRate " +
            " FROM crm_sso_product " +
            "<where>" +
            "        <if test=\"id != '-1'\">" +
            "            AND id = #{id}" +
            "        </if>" +
            "        <if test=\"name != null and name != ''\">" +
            "            AND name = #{name}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    List<Product> findProductByProduct(Product product);


    @Update("UPDATE crm_sso_product set name = #{name}, cost = #{cost},price = #{price}, commission_rate = #{commissionRate}" +
            " WHERE name = #{name} and is_delete = false")
    int updateProduct(Product product);

    @Update("UPDATE crm_sso_product set is_delete = true " +
            " WHERE name = #{name} and is_delete = false")
    int deleteProductByName(String name);
}
