package com.bambi.jvmDeep.copy;

public class DeepCopy {
    public static void main(String[] args) {

    }
}

class Teacher implements Cloneable{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Teacher() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class Student implements Cloneable{
    private String name ;
    private Teacher teacher;
    private Integer age;

    public Student() {

    }

    public Student(String name, Teacher teacher, Integer age) {
        this.name = name;
        this.teacher = teacher;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅复制 shallowCopy的时候
//        Object obj = super.clone();
//        return obj;

        //改为深复制
        Student student = (Student) super.clone();
        //本来是浅复制，现在将Teacher对象复制一份并重新set进来
        student.setTeacher((Teacher) student.getTeacher().clone());
        return student;
    }
}
