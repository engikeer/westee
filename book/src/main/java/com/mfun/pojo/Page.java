package com.mfun.pojo;

import java.util.List;

/**
 * 分页模型
 */
public class Page<T> {
    private int pageSize = 4;  // 页面容量，项目指定
    private int totalCount;  // 总记录条数，来自数据库
    private int pageNo;  // 当前的页面，来自前端请求
//    private int pageCount; // 总页面数，总记录数 / 页面容量 + 1
//    private int index;  // 数据库记录开始的索引，
//    private boolean hasNext; // 是否有下一页
//    private boolean hasPrev; // 是否有上一页
    private List<T> data;  // 本页的记录

    public Page(int totalCount, int pageNo) {
        this.totalCount = totalCount;
        setPageNo(pageNo);
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageCount=" + getPageCount() +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Page{" +
//                "pageSize=" + pageSize +
//                ", pageNo=" + pageNo +
//                ", totalCount=" + totalCount +
//                ", pageCount=" + getPageCount() +
//                ", index=" + getIndex() +
//                ", hasNext=" + isHasNext() +
//                ", hasPrev=" + isHasPrev() +
//                '}';
//    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        // 1 <= pageNo <= pageCount（如果 pageCount 为 0，pageNo 仍为 1）
        this.pageNo = Math.max(Math.min(pageNo, getPageCount()), 1);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        if (totalCount % pageSize != 0) {
            return totalCount / pageSize + 1;
        } else {
            return totalCount / pageSize;
        }
    }

    public int getIndex() {
        return pageSize * (pageNo - 1);
    }

    public boolean isHasNext() {
        return pageNo < getPageCount();
    }

    public boolean isHasPrev() {
        return pageNo > 1;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
