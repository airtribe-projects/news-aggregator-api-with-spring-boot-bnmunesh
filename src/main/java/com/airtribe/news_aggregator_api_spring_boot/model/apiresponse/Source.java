package com.airtribe.news_aggregator_api_spring_boot.model.apiresponse;

import lombok.Getter;
import lombok.Setter;

public class Source {
    private String uri;
    private String dataType;
    private String title;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
