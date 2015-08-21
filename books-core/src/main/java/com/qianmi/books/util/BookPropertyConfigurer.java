package com.qianmi.books.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 *       
 * 项目名称：sales-core    
 * 类名称：ErrorCodesPropertyConfigurer    
 * 类描述：     加载异常配置信息
 *
 * 创建人：176china@gmail.com
 * 创建时间：2012-7-23 下午7:30:42    
 * 修改人：of165   
 * 修改时间：2012-7-23 下午7:30:42    
 * 修改备注：    
 * @version     
 *
 */
public class BookPropertyConfigurer extends PropertyPlaceholderConfigurer {
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        logger.info("加载异常配置信息");
        super.processProperties(beanFactory, props);
        BookConstants.loadProperties(props);
        logger.info("加载完成");
    }
}
