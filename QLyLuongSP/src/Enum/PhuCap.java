/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author ACER
 */
public enum PhuCap {
    MUC_1(500000),
    MUC_2(750000),
    MUC_3(1000000),
    MUC_4(1250000);

    private final int giaTri;

    PhuCap(int giaTri) {
        this.giaTri = giaTri;
    }

    public int getGiaTri() {
        return giaTri;
    }

    @Override
    public String toString() {
        return getGiaTri()+"";
    }
    
    
}
