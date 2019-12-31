package com.example.administrator.searchforlovedones;

public class Saysomething_bean {
    private String name;
    private String phone;
    private String content;
    private int id;//图像，暂时一次替代
    private String time;
    private String ids;

    public Saysomething_bean(String ids,int id,String name,String phone,String time,String content){
        this.ids=ids;
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.time=time;
        this.content=content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
