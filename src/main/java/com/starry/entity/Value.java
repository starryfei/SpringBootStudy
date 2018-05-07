package com.starry.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @ClassName  Value   
 * @Description TODO(这里用一句话描述这个类的作用)   
 * @author yafei.qin 
 * @date 2018年5月7日 下午5:14:58   
 *     
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String quote;

    public Value() {
    }

    public Long getId() {
        return this.id;
    }

    public String getQuote() {
        return this.quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
