package cn.edu.xupt.help.link.mapper;

import cn.edu.xupt.help.framework.domain.TbLink;
import cn.edu.xupt.help.framework.domain.TbLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TbLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int countByExample(TbLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int deleteByExample(TbLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int insert(TbLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int insertSelective(TbLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    List<TbLink> selectByExample(TbLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    TbLink selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TbLink record, @Param("example") TbLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TbLink record, @Param("example") TbLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_link
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbLink record);
}