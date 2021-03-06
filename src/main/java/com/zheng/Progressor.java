package com.zheng;

import com.google.common.collect.Lists;
import com.zheng.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务进度，记录执行的任务进度
 * 前端可以用该对象信息制作进度条
 * Created by zhenglian on 2016/11/1.
 */
public class Progressor implements Serializable {
    private int total; // 任务总数
    private int complated; // 已经完成的任务数
    private double percent; // complated / total * 100 百分比分值
    private String text; // 进度条提示文本
    private boolean finished; //当前任务是否已经完成

    private boolean hasError = Boolean.FALSE;
    private boolean hasWarn = Boolean.FALSE;

    private CopyOnWriteArrayList<String> errorMessages = Lists.newCopyOnWriteArrayList();
    private CopyOnWriteArrayList<String> warnMessages = Lists.newCopyOnWriteArrayList();

    public Progressor(int complated, int total, String text) {
        this.complated = complated;
        this.total = total;
        this.text = text;

        this.percent = CommonUtil.decimal(this.complated * 1.0 / this.total * 100, 2);
        this.finished = this.complated == this.total;
    }

    public int getTotal() {
        return total;
    }

    public Progressor setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getComplated() {
        return complated;
    }

    public Progressor setComplated(int complated) {
        this.complated = complated;
        return this;
    }

    public double getPercent() {
        return percent;
    }

    public Progressor setPercent(double percent) {
        this.percent = percent;
        return this;
    }

    public String getText() {
        return text;
    }

    public Progressor setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isFinished() {
        return finished;
    }

    public Progressor setFinished(boolean finished) {
        this.finished = finished;
        return this;
    }

    public boolean isHasError() {
        return hasError;
    }

    public Progressor setHasError(boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public boolean isHasWarn() {
        return hasWarn;
    }

    public Progressor setHasWarn(boolean hasWarn) {
        this.hasWarn = hasWarn;
        return this;
    }

    public List<String> getErrorMessages() {
        List<String> messages = Lists.newArrayList();
        messages.addAll(this.getErrorMessages());
        return messages;
    }

    public List<String> getWarnMessages() {
        List<String> messages = Lists.newArrayList();
        messages.addAll(this.getWarnMessages());
        return warnMessages;
    }

    public Progressor addErrorMessage(String errorMessage) {
        if(!StringUtils.isBlank(errorMessage)) {
            this.errorMessages.add(errorMessage);
            this.hasError = true;
        }
        return this;
    }

    public Progressor addWarnMessage(String warnMessage) {
        if(!StringUtils.isBlank(warnMessage)) {
            this.warnMessages.add(warnMessage);
            this.hasWarn = true;
        }
        return this;
    }

    public Progressor addErrorMessage(List<String> messages) {
        this.errorMessages.addAll(messages);
        if(!this.errorMessages.isEmpty()) {
            this.hasError = true;
        }
        return this;
    }

    public Progressor addWarnMessage(List<String> messages) {
        this.warnMessages.addAll(messages);
        if(!this.warnMessages.isEmpty()) {
            this.hasWarn = true;
        }
        return this;
    }

}
