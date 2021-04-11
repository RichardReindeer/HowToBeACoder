package com.bambi.jvmDeep.copy;

/**
 * 引用拷贝
 * 创建一个指向对象的引用变量的拷贝
 */
public class QuoteCopy {
    public static void main(String[] args) {
        User user = new User("user1","user1");
        User otherUser = user;
        System.out.println("otherUser = " + otherUser);
        System.out.println("user = " + user);
    }
}

class User{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
