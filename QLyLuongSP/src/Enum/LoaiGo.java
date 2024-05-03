/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author ACER
 */
public enum LoaiGo {
    GOTHUONG("Gỗ Hương"),
    GOTRUNG("Gỗ Sao"),
    GOTOT("Gỗ Bằng Lăng");
	private final String loaiGo;
	
	private LoaiGo(String loaiGo) {
		this.loaiGo = loaiGo;
	}
	
	public String getLoaiGo() {
		return loaiGo;
	}
	
	@Override
	public String toString() {
		return getLoaiGo();
	}
}