package dev.jaczerob.council.broker.configuration;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfiguration {
    @Bean
    public BrokerService brokerService() throws Exception {
        final BrokerService brokerService = new BrokerService();
        brokerService.setBrokerId("council-broker");
        brokerService.addConnector("tcp://0.0.0.0:61616");
        return brokerService;
    }
}
