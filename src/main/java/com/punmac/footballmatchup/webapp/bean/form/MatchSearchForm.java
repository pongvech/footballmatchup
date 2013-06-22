package com.punmac.footballmatchup.webapp.bean.form;

import com.punmac.footballmatchup.webapp.bean.form.pagination.LoadMorePagination;

public class MatchSearchForm extends LoadMorePagination {

    // Match name
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
