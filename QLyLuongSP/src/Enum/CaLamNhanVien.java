/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author ACER
 */
public enum CaLamNhanVien {
    FULLTIME("Full-time"),
    PARTTIME("Part-time"),
    TANGCA("Tăng ca");
	private final String caLam;

    private CaLamNhanVien(String caLam) {
        this.caLam = caLam;
    }

    public String getCaLam() {
        return caLam;
    } 
    
    @Override
    public String toString() {
       return getCaLam();
    }
}
