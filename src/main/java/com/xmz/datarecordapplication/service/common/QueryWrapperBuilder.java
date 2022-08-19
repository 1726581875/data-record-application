package com.xmz.datarecordapplication.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.xmz.datarecordapplication.model.common.TimeRange;
import com.xmz.datarecordapplication.model.entity.event.EventRecord;


/**
 * @author xiaomingzhang
 * @date 2022/8/4
 */
public class QueryWrapperBuilder<T> {

    private LambdaQueryWrapper<T> queryWrapper;


    private QueryWrapperBuilder(){}

    private QueryWrapperBuilder(LambdaQueryWrapper<T> queryWrapper) {
        this.queryWrapper = queryWrapper;
    }

    public static <T> QueryWrapperBuilder<T> build(){
        return new QueryWrapperBuilder<T>(new LambdaQueryWrapper<T>());
    }

    public LambdaQueryWrapper<T> getQueryWrapper() {
        return this.queryWrapper;
    }


    public <T> QueryWrapperBuilder<T>  timeRange(SFunction<T, ?> column, TimeRange timeRange) {

        if(timeRange == null) {
            return (QueryWrapperBuilder<T>) this;
        }


        if(timeRange.getStartTime() != null) {
            //queryWrapper.ge(column, timeRange.getStartTime());
        }

        if(timeRange.getEndTime() != null) {
            //queryWrapper.le(column, timeRange.getEndTime());
        }

        return (QueryWrapperBuilder<T>) this;
    }

    public static void main(String[] args) {
        QueryWrapperBuilder<EventRecord> queryWrapperBuilder = QueryWrapperBuilder.build();

        queryWrapperBuilder.timeRange(EventRecord::getBinLogFileName, null);
    }


}
