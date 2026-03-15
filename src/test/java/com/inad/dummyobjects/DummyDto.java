package com.inad.dummyobjects;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class DummyDto {

    private String stringVal;
    private int intVal;
    private long longVal;
    private float floatVal;
    private double doubleVal;
    private boolean booleanVal;

    private Integer integerWrapper;
    private Long longWrapper;
    private Float floatWrapper;
    private Double doubleWrapper;
    private Boolean booleanWrapper;

    private BigDecimal bigDecimalVal;

    private Date dateVal;
    private Instant instantVal;
    private Timestamp timestampVal;
    private LocalDate localDateVal;
    private LocalDateTime localDateTimeVal;
    private LocalTime localTimeVal;

    private FactoryTest.TestEnum enumVal;

    private FactoryTest.InnerDto innerDto;

    private List<FactoryTest.InnerDto> listDto;

    public String getStringVal() {
        return stringVal;
    }

    public void setStringVal(String stringVal) {
        this.stringVal = stringVal;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public long getLongVal() {
        return longVal;
    }

    public void setLongVal(long longVal) {
        this.longVal = longVal;
    }

    public float getFloatVal() {
        return floatVal;
    }

    public void setFloatVal(float floatVal) {
        this.floatVal = floatVal;
    }

    public double getDoubleVal() {
        return doubleVal;
    }

    public void setDoubleVal(double doubleVal) {
        this.doubleVal = doubleVal;
    }

    public boolean isBooleanVal() {
        return booleanVal;
    }

    public void setBooleanVal(boolean booleanVal) {
        this.booleanVal = booleanVal;
    }

    public Integer getIntegerWrapper() {
        return integerWrapper;
    }

    public void setIntegerWrapper(Integer integerWrapper) {
        this.integerWrapper = integerWrapper;
    }

    public Long getLongWrapper() {
        return longWrapper;
    }

    public void setLongWrapper(Long longWrapper) {
        this.longWrapper = longWrapper;
    }

    public Float getFloatWrapper() {
        return floatWrapper;
    }

    public void setFloatWrapper(Float floatWrapper) {
        this.floatWrapper = floatWrapper;
    }

    public Double getDoubleWrapper() {
        return doubleWrapper;
    }

    public void setDoubleWrapper(Double doubleWrapper) {
        this.doubleWrapper = doubleWrapper;
    }

    public Boolean getBooleanWrapper() {
        return booleanWrapper;
    }

    public void setBooleanWrapper(Boolean booleanWrapper) {
        this.booleanWrapper = booleanWrapper;
    }

    public BigDecimal getBigDecimalVal() {
        return bigDecimalVal;
    }

    public void setBigDecimalVal(BigDecimal bigDecimalVal) {
        this.bigDecimalVal = bigDecimalVal;
    }

    public Date getDateVal() {
        return dateVal;
    }

    public void setDateVal(Date dateVal) {
        this.dateVal = dateVal;
    }

    public Instant getInstantVal() {
        return instantVal;
    }

    public void setInstantVal(Instant instantVal) {
        this.instantVal = instantVal;
    }

    public Timestamp getTimestampVal() {
        return timestampVal;
    }

    public void setTimestampVal(Timestamp timestampVal) {
        this.timestampVal = timestampVal;
    }

    public LocalDate getLocalDateVal() {
        return localDateVal;
    }

    public void setLocalDateVal(LocalDate localDateVal) {
        this.localDateVal = localDateVal;
    }

    public LocalDateTime getLocalDateTimeVal() {
        return localDateTimeVal;
    }

    public void setLocalDateTimeVal(LocalDateTime localDateTimeVal) {
        this.localDateTimeVal = localDateTimeVal;
    }

    public LocalTime getLocalTimeVal() {
        return localTimeVal;
    }

    public void setLocalTimeVal(LocalTime localTimeVal) {
        this.localTimeVal = localTimeVal;
    }

    public FactoryTest.TestEnum getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(FactoryTest.TestEnum enumVal) {
        this.enumVal = enumVal;
    }

    public FactoryTest.InnerDto getInnerDto() {
        return innerDto;
    }

    public void setInnerDto(FactoryTest.InnerDto innerDto) {
        this.innerDto = innerDto;
    }

    public List<FactoryTest.InnerDto> getListDto() {
        return listDto;
    }

    public void setListDto(List<FactoryTest.InnerDto> listDto) {
        this.listDto = listDto;
    }
}
