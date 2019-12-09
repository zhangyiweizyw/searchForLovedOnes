package com.example.administrator.searchforlovedones;

public class Vagrant {
    private String name;//流浪者姓名
    private String sex;//流浪者性别
    private String age;//流浪者大约年龄
    private String findaddress;//流浪者发现地址

    private String begintime;//开始流浪时间
    private String targetfamily;//目标家庭信息
    private String describe;//流浪者特征描述
    private String phonenumber;//发现者联系方式

    public Vagrant(String name, String sex, String age, String findaddress, String begintime, String targetfamily, String describe, String phonenumber) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.findaddress = findaddress;

        this.begintime = begintime;
        this.targetfamily = targetfamily;
        this.describe = describe;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFindaddress() {
        return findaddress;
    }

    public void setFindaddress(String findaddress) {
        this.findaddress = findaddress;
    }


    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getTargetfamily() {
        return targetfamily;
    }

    public void setTargetfamily(String targetfamily) {
        this.targetfamily = targetfamily;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
