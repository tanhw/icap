package com.business;

/**
 * Created by 西 on 2015/7/23.
 */
public interface IHandleTask {

    /**
     * 定时处理
     * @return
     */
    boolean doBusiness(String taskdate) throws Exception;
}
