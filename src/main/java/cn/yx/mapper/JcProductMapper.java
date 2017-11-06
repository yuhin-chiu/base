package cn.yx.mapper;

import cn.yx.entity.JcProduct;
import cn.yx.entity.JcProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface JcProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @Delete({
        "delete from jc_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @Insert({
        "insert into jc_product (id, title, ",
        "browes, author, status, ",
        "create_time, lang, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{browes,jdbcType=BIGINT}, #{author,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lang,jdbcType=INTEGER}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(JcProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @InsertProvider(type=JcProductSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(JcProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @SelectProvider(type=JcProductSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="browes", property="browes", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lang", property="lang", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<JcProduct> selectByExampleWithBLOBs(JcProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @SelectProvider(type=JcProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="browes", property="browes", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lang", property="lang", jdbcType=JdbcType.INTEGER)
    })
    List<JcProduct> selectByExample(JcProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @Select({
        "select",
        "id, title, browes, author, status, create_time, lang, content",
        "from jc_product",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="browes", property="browes", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lang", property="lang", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    JcProduct selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @UpdateProvider(type=JcProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JcProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @Update({
        "update jc_product",
        "set title = #{title,jdbcType=VARCHAR},",
          "browes = #{browes,jdbcType=BIGINT},",
          "author = #{author,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "lang = #{lang,jdbcType=INTEGER},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(JcProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jc_product
     *
     * @mbg.generated Mon Nov 06 14:42:09 CST 2017
     */
    @Update({
        "update jc_product",
        "set title = #{title,jdbcType=VARCHAR},",
          "browes = #{browes,jdbcType=BIGINT},",
          "author = #{author,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "lang = #{lang,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(JcProduct record);
}