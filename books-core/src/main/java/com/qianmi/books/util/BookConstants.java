package com.qianmi.books.util;

import java.util.Properties;

/**
 * 
 *     
 * 项目名称：sales-core    
 * 类名称：SaleConstants    
 * 类描述：     常量类
 *
 * 创建人：176china@gmail.com
 * 创建时间：2012-7-23 下午7:07:35    
 * 修改人：of165   
 * 修改时间：2012-7-23 下午7:07:35    
 * 修改备注：    
 * @version     
 *
 */
public class BookConstants {


    // 接口地址 Properties
    public static Properties bookProperties;

    // 加载接口地址
    public static void loadProperties(Properties props) {
        bookProperties = props;
    }

}
