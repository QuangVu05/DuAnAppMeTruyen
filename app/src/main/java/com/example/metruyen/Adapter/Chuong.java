package com.example.metruyen.Adapter;

import java.io.Serializable;

public class Chuong implements Serializable {
    private int idchuong;
    private String sochuong,tieude,date;

    public Chuong() {

    }

    public Chuong(int idchuong, String sochuong, String tieude, String date) {
        this.idchuong = idchuong;
        this.sochuong = sochuong;
        this.tieude = tieude;
        this.date = date;
    }

    public int getIdchuong() {
        return idchuong;
    }

    public void setIdchuong(int idchuong) {
        this.idchuong = idchuong;
    }

    public String getSochuong() {
        return sochuong;
    }

    public void setSochuong(String sochuong) {
        this.sochuong = sochuong;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
