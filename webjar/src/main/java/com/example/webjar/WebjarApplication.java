package com.example.webjar;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@NacosPropertySource(dataId = "test", autoRefreshed = true)
public class WebjarApplication {

	@NacosInjected
	private NamingService namingService;

	@Value(value = "${spring.application.name}")
	private String applicationName;

	@NacosValue(value = "${server.port}")
	private int discoveryAddr;

	@PostConstruct
	public void registerInstance() throws NacosException {

		namingService.registerInstance(applicationName, "127.0.0.1", 58888);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebjarApplication.class, args);
	}

}
