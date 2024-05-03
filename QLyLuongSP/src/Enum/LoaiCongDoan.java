/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author ACER
 */
public enum LoaiCongDoan {
    CATGO("Cắt Gỗ"),
    CHETAC("Chế Tác"),
    LAPRAP("Lắp Ráp"),
    BEMAT("Bề Mặt");
    
    private final String loaiCongDoan;
    
    private LoaiCongDoan(String loaiCongDoan) {
        this.loaiCongDoan = loaiCongDoan;
    }
    
    private String getLoaiCongDoan(){
        return loaiCongDoan;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return getLoaiCongDoan();
    }
    
    public static LoaiCongDoan findEnumByString(String inputString) {
        for (LoaiCongDoan loai : LoaiCongDoan.values()) {
            if (loai.getLoaiCongDoan().equals(inputString)) {
                return loai;
            }
        }
        return null;
    }
}

