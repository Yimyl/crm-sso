package edu.bjtu.crm.sso.service;

import edu.bjtu.crm.sso.domain.model.Consumer;

import java.util.List;

public interface ConsumerMngService {
    int addConsumer(Consumer consumer);

    Consumer findConsumerByName(String name);

    Consumer findConsumerById(long id);

    Consumer findConsumerByPhone(String phone);

    List<Consumer> findConsumerByConsumer(Consumer consumer);

    int updateConsumer(Consumer consumer);

    int deleteConsumerByPhone(String phone);

    double updateBalance(String phone, double balance);

    double updateConsume(String phone, double consume);
}
