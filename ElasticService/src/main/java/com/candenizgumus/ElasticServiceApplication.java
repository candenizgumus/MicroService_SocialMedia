package com.candenizgumus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SpringBootApplication
public class ElasticServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ElasticServiceApplication.class);
    }
}