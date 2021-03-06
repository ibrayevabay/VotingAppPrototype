package com.shareholder.abay.finapps.finappsproject.rest;

import com.google.gson.annotations.SerializedName;

import org.springframework.http.HttpStatus;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Olzhas.Pazyldayev on 01.09.2016
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -3133231140239334607L;

    @SerializedName("data")
    private Object data;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("state")
    private int state;

    @SerializedName("total")
    private long total;

    @SerializedName("searchTotal")
    private Integer searchTotal;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private boolean success;

    @SerializedName("warning")
    private boolean warning;

    @SerializedName("clazz")
    private Class<?> clazz;

    @SerializedName("cropParameters")
    private List<String> cropParameters;


    public SimpleResponse() {
        super();
        this.state = HttpStatus.OK.value();
    }

    public SimpleResponse(Object data) {
        super();
        this.data = data;
        this.state = HttpStatus.OK.value();
    }

    public SimpleResponse(Throwable e) {
        super();
        this.data = ExceptionUtils.getStackTrace(e);
        this.message = e.getMessage();
        this.state = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public SimpleResponse(Object data, int state) {
        super();
        this.data = data;
        this.state = state;
    }

    public SimpleResponse(Object data, int state, String message) {
        super();
        this.data = data;
        this.state = state;
        this.message = message;
    }

    public SimpleResponse(Object data, long timestamp, int state, String message, boolean success) {
        super();
        this.data = data;
        this.timestamp = timestamp;
        this.state = state;
        this.message = message;
        this.success = success;
    }

    /*
     * Return 200 HTTP status
     */
    @SuppressWarnings("rawtypes")
    public SimpleResponse SUCCESS() {
        this.state = HttpStatus.OK.value();
        this.success = true;
        this.timestamp = System.currentTimeMillis();
        if (data instanceof Collection) {
            if (((Collection) data).size() == 0) {
                this.warning = true;
            }
            this.total = ((Collection) data).size();
        } else if (data instanceof Map) {
            if (((Map) data).size() == 0) {
                this.warning = true;
            }
            this.total = ((Map) data).size();
        }

        return this;
    }

    @SuppressWarnings("rawtypes")
    public SimpleResponse UNDEFINED() {
        this.state = HttpStatus.OK.value();
        this.success = true;
        this.timestamp = System.currentTimeMillis();

        if (data instanceof Collection) {
            if (((Collection) data).size() == 0) {
                this.state = HttpStatus.NO_CONTENT.value();
                this.warning = true;
                this.success = false;
            }
            total = ((Collection) data).size();
        } else if (data instanceof Map) {
            if (((Map) data).size() == 0) {
                this.state = HttpStatus.NO_CONTENT.value();
                this.warning = true;
                this.success = false;
            }
            this.total = ((Map) data).size();
        } else if (data == null) {
            this.state = HttpStatus.NOT_FOUND.value();
            this.warning = true;
            this.success = false;
        }

        return this;
    }

    /*
     * Return Custom HTTP status
     */
    public SimpleResponse ERROR_CUSTOM() {
        this.success = false;
        this.timestamp = System.currentTimeMillis();
        this.message = this.message == null ? "https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#" + state
                : this.message;

        return this;
    }

    /*
     * Return 400 HTTP status
     */
    public SimpleResponse ERROR() {
        this.state = HttpStatus.BAD_REQUEST.value();

        return ERROR_CUSTOM();
    }

    /*
    * Return 404 HTTP status
    */
    public SimpleResponse ERROR_NOT_FOUND() {
        this.state = HttpStatus.NOT_FOUND.value();
        return ERROR_CUSTOM();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public SimpleResponse setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getState() {
        return state;
    }

    public SimpleResponse setState(int state) {
        this.state = state;
        return this;
    }

    public Integer getSearchTotal() {
        return searchTotal;
    }

    public SimpleResponse setSearchTotal(Integer searchTotal) {
        this.searchTotal = searchTotal;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SimpleResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public SimpleResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public SimpleResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }

    public boolean isWarning() {
        return warning;
    }

    public SimpleResponse setWarning(boolean warning) {
        this.warning = warning;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public SimpleResponse setTotal(long total) {
        this.total = total;
        return this;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public SimpleResponse setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public List<String> getCropParameters() {
        return cropParameters;
    }

    public SimpleResponse setCropParameters(List<String> cropParameters) {
        this.cropParameters = cropParameters;
        return this;
    }
}
