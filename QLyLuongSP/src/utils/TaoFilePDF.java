package utils;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import entity.BangLuongNhanVien;

import javax.swing.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class TaoFilePDF {
    private static String pdfFilePath = System.getProperty("user.home") + "/Desktop/BangLuongNhanVien/BangLuongNhanVien" + System.currentTimeMillis() + ".pdf";

    public static void createInvoicePdf(BangLuongNhanVien nhanVien) {
        try {
            // Tạo đối tượng PdfWriter để ghi vào file PDF
            PdfWriter writer = new PdfWriter(pdfFilePath);

            // Tạo đối tượng PdfDocument với PdfWriter
            PdfDocument pdf = new PdfDocument(writer);

            // Tạo đối tượng Document để thêm nội dung
            Document document = new Document(pdf);
            Paragraph tieuDe = new Paragraph("Cua Hang WorkWood");
            tieuDe.setTextAlignment(TextAlignment.CENTER);
            tieuDe.setBold();
            tieuDe.setFontSize(25);
            tieuDe.setMarginBottom(10);
            document.add(tieuDe);

            Paragraph phieuThanhToan = new Paragraph("Bang Luong Nhan Vien");
            phieuThanhToan.setTextAlignment(TextAlignment.CENTER);
            phieuThanhToan.setItalic();
            phieuThanhToan.setFontSize(22);
            phieuThanhToan.setMarginBottom(10);
            document.add(phieuThanhToan);

            Paragraph ngayLap = new Paragraph("Ngay Lap  :" + nhanVien.getNgayLap());
            ngayLap.setTextAlignment(TextAlignment.LEFT);
            ngayLap.setMarginBottom(10);
            document.add(ngayLap);

            Paragraph maHoaDon = new Paragraph("Ma Bang Luong:" + nhanVien.getMaBangLuong());
            maHoaDon.setTextAlignment(TextAlignment.LEFT);
            maHoaDon.setMarginBottom(10);
            document.add(maHoaDon);

            // Tạo bảng cho các mục hóa đơn
            Table table = new Table(9);
            table.addHeaderCell(new Cell().add(new Paragraph("Ten Nhan Vien")));
            table.addHeaderCell(new Cell().add(new Paragraph("Luong Co Ban")));
            table.addHeaderCell(new Cell().add(new Paragraph("He So")));
            table.addHeaderCell(new Cell().add(new Paragraph("Khau Tru Bao Hiem")));
            table.addHeaderCell(new Cell().add(new Paragraph("Phu Cap")));
            table.addHeaderCell(new Cell().add(new Paragraph("So Ngay Cong")));
            table.addHeaderCell(new Cell().add(new Paragraph("So Gio Tang Ca")));
            table.addHeaderCell(new Cell().add(new Paragraph("Luong Truoc Thue")));
            table.addHeaderCell(new Cell().add(new Paragraph("Luong Nhan")));
            table.setTextAlignment(TextAlignment.CENTER);

            // Thêm dữ liệu vào bảng
            table.addCell(new Cell().add(new Paragraph(nhanVien.getNhanVien().getTen())));
            table.addCell(new Cell().add(new Paragraph(nhanVien.getNhanVien().getChucVu().getHeSoLuong() + "")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.getNhanVien().getHeSoLuong() + "")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.khauTruTuBaoHiemYTeVaBaoHiemXaHoi() + "")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.getNhanVien().getPhuCap() + "")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.getSoNgayDiLam() + "")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.getSoGioTangCa() + "+")));
            table.addCell(new Cell().add(new Paragraph(nhanVien.tongThuNhapCaNhanTruocThue() + "")));
            
            Paragraph luongThuc = new Paragraph(nhanVien.layLuongThucChoNhanVien() + "");
            luongThuc.setTextAlignment(TextAlignment.CENTER);
            luongThuc.setBold();
            luongThuc.setFontSize(15);
            table.addCell(new Cell().add(luongThuc));

            // Thêm bảng vào tài liệu
            table.setWidth(540);
            document.add(table);
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();
            int month = currentDate.getMonthValue();
            int year = currentDate.getYear();
            Paragraph ngayThangNam = new Paragraph("Hồ Chí Minh, ngày " + day + " thang " + month + " nam " + year);
            ngayThangNam.setTextAlignment(TextAlignment.RIGHT);
            document.add(ngayThangNam);

            // Thêm tổng cộng
            Paragraph nguoiLapHoaDon = new Paragraph("Nguoi Lap Hoa Don");
            nguoiLapHoaDon.setTextAlignment(TextAlignment.RIGHT);
            nguoiLapHoaDon.setMarginRight(34);
            document.add(nguoiLapHoaDon);

            Paragraph kyTen = new Paragraph("Ky Ten");
            kyTen.setTextAlignment(TextAlignment.RIGHT);
            kyTen.setMarginRight(63);
            document.add(kyTen);

            Paragraph xacNhan = new Paragraph("<Da xac nhan>");
            xacNhan.setTextAlignment(TextAlignment.RIGHT);
            xacNhan.setMarginRight(42);
            document.add(xacNhan);

            // Đóng tài liệu để lưu các thay đổi
            document.close();
            JOptionPane.showMessageDialog(null, "Tao Phieu LUong Thanh Cong");

            // Mở tập tin PDF sau khi lưu
            Desktop.getDesktop().open(new File(pdfFilePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        BangLuongNhanVien nhanVien = new BangLuongNhanVien(); // Thay bằng đối tượng thực tế
//        createInvoicePdf(nhanVien);
//    }
}
