package com.alektoni.sharearide.model.vm;


import java.util.List;


public class MyPage<T> {

    private int page;
    private int pageSize;
    private int totalPages;
    private List<T> content;

    public MyPage(int page, int pageSize, int totalPages, List<T> content){
        this.page=page;
        this.pageSize=pageSize;
        this.totalPages=totalPages;
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
