package com.example.metruyen.activity;

import com.example.metruyen.Adapter.Chuong;

import java.io.Serializable;

public class TruyenTranh implements Serializable {
    private int id;
    private  String idAnh;
    private String name,gioithieu,tacgia,theloai,trangthai;

    public TruyenTranh() {

    }

    public TruyenTranh(int id, String idAnh, String name, String gioithieu, String tacgia, String theloai, String trangthai) {
        this.id = id;
        this.idAnh = idAnh;
        this.name = name;
        this.gioithieu = gioithieu;
        this.tacgia = tacgia;
        this.theloai = theloai;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdAnh() {
        return idAnh;
    }

    public void setIdAnh(String idAnh) {
        this.idAnh = idAnh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }


}

