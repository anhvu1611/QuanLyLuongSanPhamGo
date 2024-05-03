/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author ACER
 */
public enum TrangThai {
    DITRE("Đi trễ"),
    NGHICOPHEP("Nghỉ có phép"),
    NGHIKHONGPHEP("Nghỉ không phép"),
    COLAM("Đi làm");
    
    private final String trangThai;

    private TrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    } 
    
    @Override
    public String toString() {
       return getTrangThai();
    }
}
