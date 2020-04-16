package edu.bjtu.crm.sso.service;

import edu.bjtu.crm.sso.domain.model.Consumer;

import java.util.List;

public interface ProductMngService {
    int addConsumer(Consumer consumer);

    Consumer findConsumerByName(String name);

    Consumer findConsumerById(long id);

    Consumer findConsumerByPhone(String phone);

    List<Consumer> findConsumerByConsumer(Consumer consumer);

    int updateConsumer(Consumer consumer);

    int deleteConsumerByPhone(String phone);
}
