package com.bambi.jvmDeep.copy;

/**
 * 两个staff指向的对象不同(debug发现两个staff地址不同)
 * 但是两个staff中的boss引用指向的都是同一个boss对象
 */
public class LightCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Boss boss1 = new Boss();
        boss1.setBossAge(20);
        boss1.setBossName("boss1");
        Staff staff1 = new Staff();
        staff1.setUsername("staff1");
        staff1.setPassword("staff1");
        staff1.setBoss(boss1);

        Staff staff2 = new Staff();
        staff2 = (Staff) staff1.clone();
        System.out.println("开始拷贝");
        System.out.println("staff2 name= " + staff2.getUsername());
        System.out.println("staff2 boss"+staff2.getBoss().getBossName());

        System.out.println("修改boss1的名字");
        boss1.setBossName("bossBoss");
        System.out.println("staff2 bossName = "+staff2.getBoss().getBossName());
        System.out.println("staff1 bossName = " + staff1.getBoss().getBossName());
    }
}
class Boss implements Cloneable{
    private String bossName;
    private Integer bossAge;

    public Boss(String bossName, Integer bossAge) {
        this.bossName = bossName;
        this.bossAge = bossAge;
    }

    public Boss() {
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public Integer getBossAge() {
        return bossAge;
    }

    public void setBossAge(Integer bossAge) {
        this.bossAge = bossAge;
    }
}

class Staff implements Cloneable{
    private String username;
    private String password;
    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        return obj;
    }

    public Staff() {
    }

    public Staff(String username, String password, Boss boss) {
        this.username = username;
        this.password = password;
        this.boss = boss;
    }

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
}
