package com.my.ex.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// @ServerEndpoint를 사용하면 @Autowired를 사용할 수 없기때문에 사용 가능하도록 해당 클래스를 설정
@Component
public class ServerEndpointConfigurator extends javax.websocket.server.ServerEndpointConfig.Configurator implements ApplicationContextAware {
	
	private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServerEndpointConfigurator.context = applicationContext;
    }
    
}
