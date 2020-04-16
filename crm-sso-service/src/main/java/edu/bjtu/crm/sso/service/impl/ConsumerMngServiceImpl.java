package edu.bjtu.crm.sso.service.impl;

import edu.bjtu.crm.sso.dao.mapper.ConsumerMapper;
import edu.bjtu.crm.sso.dao.mapper.UserInfoMapper;
import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import edu.bjtu.crm.sso.domain.model.Consumer;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import edu.bjtu.crm.sso.service.ConsumerMngService;
import edu.bjtu.crm.sso.service.UserMngService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConsumerMngServiceImpl implements ConsumerMngService {
    @Resource
    private ConsumerMapper consumerMapper;


    @Override
    public int addConsumer(Consumer consumer) {
        return consumerMapper.addConsumer(consumer);
    }


    @Override
    public Consumer findConsumerByName(String name) {
        return consumerMapper.findConsumerByName(name);
    }

    @Override
    public Consumer findConsumerById(long id) {
        return consumerMapper.findConsumerById(id);
    }

    @Override
    public Consumer findConsumerByPhone(String phone) {
        return consumerMapper.findConsumerByPhone(phone);
    }

    @Override
    public List<Consumer> findConsumerByConsumer(Consumer consumer) {
        return consumerMapper.findConsumerByConsumer(consumer);
    }

    @Override
    public int updateConsumer(Consumer consumer) {
        return consumerMapper.updateConsumer(consumer);
    }

    @Override
    public int deleteConsumerByPhone(String phone) {
        return consumerMapper.deleteConsumerByPhone(phone);
    }
}
