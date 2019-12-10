package com.example.administrator.searchforlovedones;

public class Basic_information {
    private String id;//id 用于标识
    private String photo;//图片
    private String name;//名字
    private String sex;//性别

    public Basic_information(String id,String photo,String name,String sex){
        this.id=id;
        this.photo=photo;
        this.name=name;
        this.sex=sex;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }



}