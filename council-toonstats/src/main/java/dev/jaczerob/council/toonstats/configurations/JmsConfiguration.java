package dev.jaczerob.council.toonstats.configurations;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class JmsConfiguration {
    @Value("${BROKER_HOST:tcp://0.0.0.0:61616}")
    private String brokerURL;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        connectionFactory.setBrokerURL(this.brokerURL);
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setTrustAllPackages(true);

        return connectionFactory;
    }
}
