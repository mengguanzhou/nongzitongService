package com.mgz.nongzitong;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
public class NongzitongApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(NongzitongApplication.class, args);
	}
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // TODO Auto-generated method stub
        super.configureMessageConverters(converters);
        
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastConf = new FastJsonConfig();
        
        fastConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastConf);
        
        converters.add(fastJsonConverter);
    }
	
	@Bean
    public HttpMessageConverters fastJsonConverters(){
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastConf = new FastJsonConfig();
        
        fastConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastConf);
        
        HttpMessageConverter<?> converter = fastJsonConverter;
        return new HttpMessageConverters(converter);
    }
}
