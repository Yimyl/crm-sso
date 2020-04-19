package edu.bjtu.crm.sso.service;

import edu.bjtu.crm.sso.domain.model.Record;
import edu.bjtu.crm.sso.domain.model.RecordDay;
import edu.bjtu.crm.sso.domain.model.RecordMonth;
import edu.bjtu.crm.sso.domain.model.RecordYear;

import java.util.Date;
import java.util.List;

public interface ProfitMngService {
    int addRecord(Record record);

    int updateRecord(Record record);

    List<Record> findRecordByRecord(Record record, Date startTime, Date endTime);


    List<RecordDay> findRecordDayUserByRecord(Record record, Date startTime, Date endTime);

    public List<RecordMonth> findRecordMonthUserByRecord(Record record, Date startTime, Date endTime);

    public List<RecordYear> findRecordYearUserByRecord(Record record, Date startTime, Date endTime);

    public List<RecordDay> findRecordDayConsumerByRecord(Record record, Date startTime, Date endTime);

    public List<RecordMonth> findRecordMonthConsumerByRecord(Record record, Date startTime, Date endTime);

    public List<RecordYear> findRecordYearConsumerByRecord(Record record, Date startTime, Date endTime);

    public List<RecordDay> findRecordDayProductByRecord(Record record, Date startTime, Date endTime);

    public List<RecordMonth> findRecordMonthProductByRecord(Record record, Date startTime, Date endTime);

    public List<RecordYear> findRecordYearProductByRecord(Record record, Date startTime, Date endTime);

}
