package org.zfjava.app.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @auther zhangfen
 * @date 2022/1/4 17:42
 */
public class User {
    @NotNull(message="[name],字段不能为空")
    @Length(min=1,max=10,message = "[name]最小为1，最大为10")
    private String name;
    private String password;

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
