package com.example.user.soapconfig.server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "user")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUser (XsdSchema userSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user");
        wsdl11Definition.setSchema(userSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user.xsd"));
    }

    @Bean(name = "userDetails")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUserDetails (XsdSchema userDetailsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserDetailsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user-details");
        wsdl11Definition.setSchema(userDetailsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userDetailsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-details.xsd"));
    }

    @Bean(name = "userPrivilege")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUserPrivilege (XsdSchema userPrivilegeSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserPrivilegePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/user-privilege");
        wsdl11Definition.setSchema(userPrivilegeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema userPrivilegeSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-privilege.xsd"));
    }

    @Bean(name = "penalty")
    public DefaultWsdl11Definition defaultWsdl11DefinitionPenalty (XsdSchema penaltySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PenaltyPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://rentacar.com/penalty");
        wsdl11Definition.setSchema(penaltySchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema penaltySchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user/user-penalty.xsd"));
    }

}
