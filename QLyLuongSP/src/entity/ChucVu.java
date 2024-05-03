/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ACER
 */
public class ChucVu {
    private String maChucVu;
    private String tenChucVu;
    private float luongCuongTheoChucVu;

    // Constructor
    public ChucVu(String maChucVu, String tenChucVu, float heSoLuong) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.luongCuongTheoChucVu = heSoLuong;
    }

    // Getters and Setters
    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public float getHeSoLuong() {
        return luongCuongTheoChucVu;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.luongCuongTheoChucVu = heSoLuong;
    }

    @Override
    public String toString() {
        return tenChucVu;
    }
     

}
