package com.fundingForAll.www.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Search {
    private static final int pageSize = 10;
    private String searchKeyword;
    private int currentPage;
    private SearchType searchType;
    private SortType sortType;
    private int startRowNum;
    private int endRowNum;

    public Search() {
    }

    public Search(String searchKeyword, int currentPage, SearchType searchType, SortType sortType, int startRowNum, int endRowNum) {
        this.searchKeyword = searchKeyword;
        this.currentPage = currentPage;
        this.searchType = searchType;
        this.sortType = sortType;
        this.startRowNum = startRowNum;
        this.endRowNum = endRowNum;
    }

    public int getStartRowNum() {
        this.startRowNum = pageSize * (currentPage - 1) + 1;
        return this.startRowNum;
    }

    public int getEndRowNum() {
        this.endRowNum = pageSize * currentPage;
        return this.endRowNum;
    }
}
