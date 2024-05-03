package utils;
import javax.swing.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportToExcel {
	public static void exportToExcel(JTable table) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Bảng Lương Nhân Viên"));

            // Tạo hàng đầu tiên cho tiêu đề
            Row titleRow = sheet.createRow(0);
            Cell titleCell = CellUtil.createCell(titleRow, 0, "Bảng Lương Nhân Viên");

            // Định dạng tiêu đề
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleStyle.setFont(titleFont);

            titleCell.setCellStyle(titleStyle);
            
            

            // Merge cells để tạo tiêu đề chiều dài 8 ô
            int firstCol = 0;
            int lastCol = 8;
            int firstRow = 0;
            int lastRow = 0;
            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            
            RegionUtil.setBorderTop(BorderStyle.MEDIUM, new CellRangeAddress(firstRow, lastRow, firstCol, lastCol), sheet);
            RegionUtil.setBorderBottom(BorderStyle.MEDIUM, new CellRangeAddress(firstRow, lastRow, firstCol, lastCol), sheet);
            RegionUtil.setBorderLeft(BorderStyle.MEDIUM, new CellRangeAddress(firstRow, lastRow, firstCol, lastCol), sheet);
            RegionUtil.setBorderRight(BorderStyle.MEDIUM, new CellRangeAddress(firstRow, lastRow, firstCol, lastCol), sheet);


            // Đặt chiều rộng của ô tiêu đề
            sheet.setColumnWidth(firstCol, 30 * 256); // 256 là đơn vị mặc định, bạn có thể điều chỉnh nó theo nhu cầu

            // Tạo hàng thứ hai cho tên cột và định dạng header
            Row headerRow = sheet.createRow(1);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int col = 0; col < table.getColumnCount(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));
                cell.setCellStyle(headerStyle);
            }

            // Đặt chiều rộng của mỗi ô (cell) tương đương với 3 cột và tăng chiều cao của header
            for (int col = 0; col < table.getColumnCount(); col++) {
                sheet.setColumnWidth(col, 15 * 256); // 256 là đơn vị mặc định, bạn có thể điều chỉnh nó theo nhu cầu
            }
            headerRow.setHeightInPoints(30);

            // Đặt chiều rộng và chiều cao của mỗi cell
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setShrinkToFit(true); // Để giảm kích thước văn bản để vừa vào ô

            for (int row = 0; row < table.getRowCount(); row++) {
                Row excelRow = sheet.createRow(row + 2); // Bắt đầu từ hàng thứ ba sau tiêu đề và header
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Cell cell = excelRow.createCell(col);
                    cell.setCellValue(String.valueOf(table.getValueAt(row, col)));
                    cell.setCellStyle(cellStyle);
                }
            }

            // Lưu file Excel trên Desktop
            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            File file = new File(desktopPath + "BangLuongNhanVien/BangLuong" + LocalDate.now() + "_" + getCurrentTime() + ".xlsx");

            // Tạo thư mục nếu nó không tồn tại
            file.getParentFile().mkdirs();

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");

                // Mở tập tin Excel sau khi lưu
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Môi trường không hỗ trợ mở tập tin tự động.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy thời gian hiện tại (giây)
	private static String getCurrentTime() {
	        LocalTime currentTime = LocalTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
	        return currentTime.format(formatter);
	    
	}
}
