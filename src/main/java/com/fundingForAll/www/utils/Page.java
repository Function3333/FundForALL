package com.fundingForAll.www.utils;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Page {
    private static int pageUnit = 5;
    private static int pageSize = 10;

    private int startUnit;
    private int endUnit;
    private int maxUnit;
    private int currentPage;
    private int totalCount;



    public Page(int currentPage, int totalCount) {
        this.startUnit = ((currentPage - 1)/pageUnit * pageUnit) + 1;
        this.endUnit = startUnit + pageUnit - 1;
        this.maxUnit = totalCount / pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
    }
}
