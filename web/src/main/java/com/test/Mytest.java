package com.test;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 指定测试用例的运行器这里指定了SpringJUnit4ClassRunner
 */
@RunWith(SpringJUnit4ClassRunner.class)
/**
 * 指定测试用例加载的bean配置文件这里指定了remoting-servlet.xml
 */
@ContextConfiguration({"file:WebContent/WEB-INF/springMVC-servlet.xml"})
/**
 * 这个类是做单元测试用的
 * @ author sys
 * 
 */
public class Mytest extends TestCase {
	
	
	@Before(value = "")
    public void init(){  
    }  
	
    @After(value = "")
    public void destory(){  
    }
    
    @Test  
    public void test() throws Exception {  
    	
    }
    
}
