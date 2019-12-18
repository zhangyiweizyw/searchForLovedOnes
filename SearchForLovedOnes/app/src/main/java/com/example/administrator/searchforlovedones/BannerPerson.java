package com.example.administrator.searchforlovedones;

public class BannerPerson {
    private int id;
    private String name;
    private String nati;
    private String photo1;

    @Override
    public String toString() {
        return "BannerPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nati='" + nati + '\'' +
                ", photo1='" + photo1 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNati() {
        return nati;
    }

    public void setNati(String nati) {
        this.nati = nati;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
}
