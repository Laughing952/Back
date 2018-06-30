package com.laughing.android.retrofitlaughing;

/**
 * 作者：Laughing on 2018/3/14 09:29
 * 邮箱：719240226@qq.com
 */

public class Contributor {
    private String login;
    private Integer contributions;

    public Contributor(String login, Integer contributions) {
        this.login = login;
        this.contributions = contributions;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getContributions() {
        return contributions;
    }

    public void setContributions(Integer contributions) {
        this.contributions = contributions;
    }
}
