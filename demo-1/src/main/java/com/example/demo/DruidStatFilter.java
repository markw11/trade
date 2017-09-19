package com.example.demo;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties.WebStatFilter;

/**  
 * druid监控拦截去  
 * @ClassName: DruidStatFilter   
 * @author wangqinghua  
 * @date 2017年7月24日 上午10:53:40  
 */  
@WebFilter(filterName="druidWebStatFilter",  
urlPatterns="/*",  
initParams={  
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源  
})  
public class DruidStatFilter extends WebStatFilter {  
  
}
