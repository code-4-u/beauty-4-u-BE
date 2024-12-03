package com.beauty4u.backend.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.beauty4u.backend", annotationClass = Mapper.class)
public class MyBatisConfig {
}