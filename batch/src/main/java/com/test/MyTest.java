package com.test;


import com.business.IHandleTask;
import com.toolbox.convert.ConvertTools;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 指定测试用例的运行器这里指定了SpringJUnit4ClassRunner
 */
@RunWith(SpringJUnit4ClassRunner.class)
/**
 * 指定测试用例加载的bean配置文件这里指定了remoting-servlet.xml
 */
@ContextConfiguration({ "classpath*:spring/servlet-context.xml" })// classpath*:spring/servlet-context.xml

public class MyTest {


    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value="CheckDataTask")
    private IHandleTask checkDataTask;

    @Autowired
    @Qualifier(value="BanktUploadTask")
    private IHandleTask banktUploadTask;

    @Autowired
    @Qualifier(value="ConsumeTask")
    private IHandleTask  consumeTask;

    @Autowired
    @Qualifier(value="BankPosoffTask")
    private IHandleTask bankPosoffTask;

    @Autowired
    @Qualifier(value="DateChangeTask")
    private IHandleTask dateChangeTask;

    @Autowired
    @Qualifier(value="BusTxnCollectTask")
    private IHandleTask busTxnCollectTask;

    @Before
    public void init() {
    }

    @After
    public void destroy() {
    }

    @Test
       public void test() throws Exception {

        if (!checkDataTask.doBusiness(null)) {
            logger.error("错误");
        }

    }


    public static void main(String[] args) {

        byte[] bytes = null;

        String str = ConvertTools.bytesToHexString(bytes);

        System.out.println(str);
    }

}
