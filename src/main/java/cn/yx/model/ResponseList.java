package cn.yx.model;

import java.util.List;

/**
 * @author yuxuanjiao
 * @date 2017年10月20日 下午4:10:50
 * @version 1.0
 */
@SuppressWarnings(value={"rawtypes"})
public class ResponseList {

    private List rows;
    private Long total;

    public ResponseList() {

    }

    public ResponseList(List rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public ResponseList setRows(List rows) {
        this.rows = rows;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public ResponseList setTotal(Long total) {
        this.total = total;
        return this;
    }
    
    public ResponseList total(Long total) {
        this.total = total;
        return this;
    }
    
    public static ResponseList rows(List rows) {
        ResponseList list = new ResponseList();
        list.setRows(rows);
        return list;
    }
}
