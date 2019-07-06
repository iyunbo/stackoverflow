package org.iyunbo.dropwizard.issue;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	private static final Logger log = LoggerFactory.getLogger(HelloWorldApplication.class);

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
		log.info("application is started");
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration,
	                Environment environment) {
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(),
				configuration.getDefaultName()
		);
		environment.jersey().register(resource);
	}

}
