package org.iyunbo.spirng.playground.conf;

import cormoran.pepper.jvm.GCInspector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TechnicalBeans {
	@Bean
	public GCInspector gcInspector() {
		return new GCInspector();
	}
}
