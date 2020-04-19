package edu.bjtu.crm.sso.service.impl;

import edu.bjtu.crm.sso.dao.mapper.*;
import edu.bjtu.crm.sso.domain.model.*;
import edu.bjtu.crm.sso.service.ProductMngService;
import edu.bjtu.crm.sso.service.ProfitMngService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ProfitMngServiceImpl implements ProfitMngService {
    @Resource
    private RecordMapper recordMapper;

    @Resource
    private RecordDayMapper recordDayMapper;

    @Resource
    private RecordMonthMapper recordMonthMapper;

    @Resource
    private RecordYearMapper recordYearMapper;

    @Transactional
    @Override
    public int addRecord(Record record) {
        try {
            Date today = new Date();
            record.setCreateTime(today);
            LocalDate now = LocalDate.now();
            record.setDay(now.getDayOfMonth());
            record.setMonth(now.getMonthValue());
            record.setYear(now.getYear());
            System.out.println(record);
            //添加记录
            recordMapper.addRecord(record);
            // day 三条/2条
            addDay(record);
            
            //month 三条/2条
            addMonth(record);
            
            //year 三条/2条
            addYear(record);
            return 1;
        }catch (Exception e) {
            System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    private void addDay(Record record) {
        RecordDay recordDay = new RecordDay(record);
        RecordDay recordDayUser = recordDayMapper.findRecordDayUserByRecord(recordDay);
        if (recordDayUser != null) {
            recordDayUser.setPrice(recordDayUser.getPrice() + recordDay.getPrice());
            recordDayUser.setIncome(recordDayUser.getIncome() + recordDay.getIncome());
            recordDayMapper.updateRecordDayUesrByRecord(recordDayUser);
        } else {
            System.out.println(recordDay.getYear());
            recordDayMapper.addRecordDayUser(recordDay);
        }
        RecordDay recordDayProduct = recordDayMapper.findRecordDayProductByRecord(recordDay);
        if (recordDayProduct != null) {
            recordDayProduct.setPrice(recordDayProduct.getPrice() + recordDay.getPrice());
            recordDayMapper.updateRecordDayProductByRecord(recordDayProduct);
        } else {
            recordDayMapper.addRecordDayProduct(recordDay);
        }

        if (StringUtils.isNotEmpty(recordDay.getPhone())) {
            RecordDay recordDayConsumer = recordDayMapper.findRecordDayConsumerByRecord(recordDay);
            if (recordDayConsumer != null) {
                recordDayConsumer.setPrice(recordDayConsumer.getPrice() + recordDay.getPrice());
                recordDayMapper.updateRecordDayConsumerByRecord(recordDayConsumer);
            } else {
                recordDayMapper.addRecordDayConsumer(recordDay);
            }
        }
    }

    private void addMonth(Record record) {
        RecordMonth recordMonth = new RecordMonth(record);
        RecordMonth recordMonthUser = recordMonthMapper.findRecordMonthUserByRecord(recordMonth);
        if (recordMonthUser != null) {
            recordMonthUser.setPrice(recordMonthUser.getPrice() + recordMonth.getPrice());
            recordMonthUser.setIncome(recordMonthUser.getIncome() + recordMonth.getIncome());
            recordMonthMapper.updateRecordMonthUesrByRecord(recordMonthUser);
        } else {
            recordMonthMapper.addRecordMonthUser(recordMonth);
        }
        RecordMonth recordMonthProduct = recordMonthMapper.findRecordMonthProductByRecord(recordMonth);
        if (recordMonthProduct != null) {
            recordMonthProduct.setPrice(recordMonthProduct.getPrice() + recordMonth.getPrice());
            recordMonthMapper.updateRecordMonthProductByRecord(recordMonthProduct);
        } else {
            recordMonthMapper.addRecordMonthProduct(recordMonth);
        }

        if (StringUtils.isNotEmpty(recordMonth.getPhone())) {
            RecordMonth recordMonthConsumer = recordMonthMapper.findRecordMonthConsumerByRecord(recordMonth);
            if (recordMonthConsumer != null) {
                recordMonthConsumer.setPrice(recordMonthConsumer.getPrice() + recordMonth.getPrice());
                recordMonthMapper.updateRecordMonthConsumerByRecord(recordMonthConsumer);
            } else {
                recordMonthMapper.addRecordMonthConsumer(recordMonth);
            }
        }
    }

    private void addYear(Record record) {
        RecordYear recordYear = new RecordYear(record);
        RecordYear recordYearUser = recordYearMapper.findRecordYearUserByRecord(recordYear);
        if (recordYearUser != null) {
            recordYearUser.setPrice(recordYearUser.getPrice() + recordYear.getPrice());
            recordYearUser.setIncome(recordYearUser.getIncome() + recordYear.getIncome());
            recordYearMapper.updateRecordYearUesrByRecord(recordYearUser);
        } else {
            recordYearMapper.addRecordYearUser(recordYear);
        }
        RecordYear recordYearProduct = recordYearMapper.findRecordYearProductByRecord(recordYear);
        if (recordYearProduct != null) {
            recordYearProduct.setPrice(recordYearProduct.getPrice() + recordYear.getPrice());
            recordYearMapper.updateRecordYearProductByRecord(recordYearProduct);
        } else {
            recordYearMapper.addRecordYearProduct(recordYear);
        }

        if (StringUtils.isNotEmpty(recordYear.getPhone())) {
            RecordYear recordYearConsumer = recordYearMapper.findRecordYearConsumerByRecord(recordYear);
            if (recordYearConsumer != null) {
                recordYearConsumer.setPrice(recordYearConsumer.getPrice() + recordYear.getPrice());
                recordYearMapper.updateRecordYearConsumerByRecord(recordYearConsumer);
            } else {
                recordYearMapper.addRecordYearConsumer(recordYear);
            }
        }
    }
    
    @Override
    public int updateRecord(Record record) {
        return 0;
    }

    @Override
    public List<Record> findRecordByRecord(Record record, Date startTime, Date endTime) {
        List<Record> records = recordMapper.findRecordByRecord(record, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordDay> findRecordDayUserByRecord(Record record, Date startTime, Date endTime) {
        RecordDay recordDay = new RecordDay(record);
        List<RecordDay> records = recordDayMapper.findRecordDayUserByRecordAndTime(recordDay, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordMonth> findRecordMonthUserByRecord(Record record, Date startTime, Date endTime) {
        RecordMonth recordMonth = new RecordMonth(record);
        List<RecordMonth> records = recordMonthMapper.findRecordMonthUserByRecordAndTime(recordMonth, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordYear> findRecordYearUserByRecord(Record record, Date startTime, Date endTime) {
        RecordYear recordYear = new RecordYear(record);
        List<RecordYear> records = recordYearMapper.findRecordYearUserByRecordAndTime(recordYear, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordDay> findRecordDayConsumerByRecord(Record record, Date startTime, Date endTime) {
        RecordDay recordDay = new RecordDay(record);
        List<RecordDay> records = recordDayMapper.findRecordDayConsumerByRecordAndTime(recordDay, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordMonth> findRecordMonthConsumerByRecord(Record record, Date startTime, Date endTime) {
        RecordMonth recordMonth = new RecordMonth(record);
        List<RecordMonth> records = recordMonthMapper.findRecordMonthConsumerByRecordAndTime(recordMonth, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordYear> findRecordYearConsumerByRecord(Record record, Date startTime, Date endTime) {
        RecordYear recordYear = new RecordYear(record);
        List<RecordYear> records = recordYearMapper.findRecordYearConsumerByRecordAndTime(recordYear, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordDay> findRecordDayProductByRecord(Record record, Date startTime, Date endTime) {
        RecordDay recordDay = new RecordDay(record);
        List<RecordDay> records = recordDayMapper.findRecordDayProductByRecordAndTime(recordDay, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordMonth> findRecordMonthProductByRecord(Record record, Date startTime, Date endTime) {
        RecordMonth recordMonth = new RecordMonth(record);
        List<RecordMonth> records = recordMonthMapper.findRecordMonthProductByRecordAndTime(recordMonth, startTime, endTime);
        return records;
    }

    @Override
    public List<RecordYear> findRecordYearProductByRecord(Record record, Date startTime, Date endTime) {
        RecordYear recordYear = new RecordYear(record);
        List<RecordYear> records = recordYearMapper.findRecordYearProductByRecordAndTime(recordYear, startTime, endTime);
        return records;
    }

}
