/**
 * 
 */
package com.core.controller.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 基础类 MAPPER
 * 
 * @ author sys
 */
public interface IBaseMapper<T> {
	
    /**
     * 获取 序列号 
     * @ author sys
     * @return
     */
    Long getIdValue();	
	
	/**
	 * 插入记录
	 * @ author sys
	 * @param obj
	 * @return
	 */
    int insert(T obj);

    /**
     * 插入记录(有效字段,即非空字段)
     * @ author sys
     * @param obj
     * @return
     */
    int insertSelective(T obj);	

	/**
	 * 物理删除记录
	 * @ author sys
	 * @param seq
	 * @return
	 */
	<K> int deleteByPrimaryKey(K seq);
	
    /**
     * 更新记录
     * @ author sys
     * @param obj
     * @return
     */
    int updateByPrimaryKey(T obj);
    
    /**
     * 更新记录(有效字段,即非空字段)
     * @ author sys
     * @param obj
     * @return
     */
    int updateByPrimaryKeySelective(T obj);    

    /**
     * 根据主键 返回记录
     * @ author sys
     * @param seq
     * @return
     */
    <K> T selectByPrimaryKey(K seq);
    
    /**
     * 根据 条件返回记录
     * @ author sys
     * @param params
     * @return
     */
    T selectByParams(@Param(value="params") Map<String,Object> params);
    
    /**
     * 查询 符合条件的记录总数
     * @ author sys
     * @param params
     * @return
     */
    int selectCountByParams(@Param(value="params") Map<String,Object> params);
    
    
    /**
     * 分页查询 记录
     * @ author sys
     * @param params 查询条件
     * @param startIndex 开始游标
     * @param endIndex 结束游标
     * @param orderParam 排序参数
     * @return
     */
    List<T> selectListByParams(@Param(value="params") Map<String,Object> params,
    						   @Param(value="startIndex")Integer startIndex,
    						   @Param(value="endIndex")Integer endIndex,
    						   @Param(value="orderParam") String orderParam );
	
    /**
     * 根据条件 查询所有记录
     * @ author sys
     * @param params 查询条件
     * @param orderParam 排序参数
     * @return
     */
    List<T> selectAllListByParams(@Param(value="params") Map<String,Object> params,
    							  @Param(value="orderParam") String orderParam );
}
