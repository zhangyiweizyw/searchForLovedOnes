package com.example.administrator.searchforlovedones.model;

import android.graphics.Bitmap;

import com.arcsoft.face.GenderInfo;

import java.text.DecimalFormat;


public class ItemShowInfo {
    private Bitmap bitmap;
    private int age;
    private int gender;
    private float similar;
    DecimalFormat decimalFormat=new DecimalFormat(".0");

    public ItemShowInfo() {
    }

    public ItemShowInfo(Bitmap bitmap, int age, int gender, float similar) {
        this.bitmap = bitmap;
        this.age = age;
        this.gender = gender;
        this.similar = similar;

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getSimilar() {
        return similar;
    }

    public void setSimilar(float similar) {
        this.similar = similar;
    }


    @Override
    public String toString() {
        return
                " 年龄=" + age +
                        ", 性别=" + (gender == GenderInfo.MALE ? "男" : (gender == GenderInfo.FEMALE ? "女" : "未知")) +
                        ", 相似度=" + decimalFormat.format(similar*100) +"%";
    }
}
