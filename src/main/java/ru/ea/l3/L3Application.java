package ru.ea.l3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@SpringBootApplication
public class L3Application extends SpringBootServletInitializer {

	@Bean
	public ViewResolver getXLTViewResolver() {
		final XsltViewResolver xsltViewResolver = new XsltViewResolver();
		xsltViewResolver.setOrder(1);
		xsltViewResolver.setSourceKey("xmlSource");
		xsltViewResolver.setViewClass(XsltView.class);
		xsltViewResolver.setViewNames("lists");
		xsltViewResolver.setPrefix("/WEB-INF/xslt/");
		xsltViewResolver.setSuffix(".xsl");
		return xsltViewResolver;
	}
	public static void main(String[] args) {
		SpringApplication.run(L3Application.class, args);
	}
}
