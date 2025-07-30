package com.demo.emsp.code.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 适用于前端框架的分页信息实体类
 * @author: lzq
 * @create: 2024-04-12 10:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 页数
     */
    private long page;
    /**
     * 每页显示的条数
     */
    private long pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 数据行信息
     */
    private T items;

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
