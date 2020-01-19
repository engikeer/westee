package com.mfun.util;

public class PageSupport {
    // 当前页码，来自前端
    private int currentPageNo = 1;

    // 总记录数，来自数据库
    private int totalCount;

    // 页面容量
    private int pageSize = 0;

    // 总页数：totalCount / pageSize + 1
    private int totalPageCount = 1;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo > 0) {
            this.currentPageNo = currentPageNo;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
       if (totalCount > 0) {
           this.totalCount = totalCount;
           // 设置总页数
           setTotalPageCount();
       }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    private void setTotalPageCount() {
        int count = (int) Math.ceil(totalCount / ((double) pageSize));
        totalPageCount = Math.max(count, 0);
    }
}
