/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ACER
 */
public class DiaChi {
    private int maDiaChi;
    private String soNha;
    private String tenThiXa;
    private String tenQuanHuyen;
    private String tenTinhThanh;

    public DiaChi(int maDiaChi, String soNha, String tenThiXa, String tenQuanHuyen, String tenTinhThanh) {
        this.maDiaChi = maDiaChi;
        this.soNha = soNha;
        this.tenThiXa = tenThiXa;
        this.tenQuanHuyen = tenQuanHuyen;
        this.tenTinhThanh = tenTinhThanh;
    }

    public DiaChi() {
    }

    public int gettMaDiaChi() {
        return maDiaChi;
    }

    public void setMaDiaChi(int maDiaChi) {
        this.maDiaChi = maDiaChi;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getTenThiXa() {
        return tenThiXa;
    }

    public void setTenThiXa(String tenThiXa) {
        this.tenThiXa = tenThiXa;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getTenTinhThanh() {
        return tenTinhThanh;
    }

    public void setTenTinhThanh(String tenTinhThanh) {
        this.tenTinhThanh = tenTinhThanh;
    }

    @Override
    public String toString() {
        String diaChiCuThe = soNha+" "+tenThiXa+" "+tenQuanHuyen+" "+tenTinhThanh;
        return diaChiCuThe;
    }


}
