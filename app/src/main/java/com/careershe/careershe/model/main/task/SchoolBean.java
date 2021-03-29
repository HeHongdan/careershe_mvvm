package com.careershe.careershe.model.main.task;

import com.careershe.rxhttp.request.base.BaseBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 2021/3/29
 * @since v2021/3/29
 */
//@Getter
//@Setter
//@AllArgsConstructor
public class SchoolBean extends BaseBean {

    private String name;

    public SchoolBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
