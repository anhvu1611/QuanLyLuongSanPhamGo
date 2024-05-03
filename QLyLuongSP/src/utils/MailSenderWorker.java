package utils;

import javax.swing.*;

import entity.BangLuongNhanVien;

import java.util.concurrent.ExecutionException;

public class MailSenderWorker extends SwingWorker<Boolean, Void> {
    private String to;
    private String subject;
    private BangLuongNhanVien bangLuong;

    public MailSenderWorker(String to, String subject, BangLuongNhanVien bangLuong) {
        this.to = to;
        this.subject = subject;
        this.bangLuong = bangLuong;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        // Gọi hàm guiMailChoNguoiDung ở đây
        return Email.guiMailChoNguoiDung(to, subject, bangLuong);
    }

    @Override
    protected void done() {
        try {
            // Nhận kết quả từ doInBackground
            boolean result = get();
            
            if (result) {
                // Xử lý khi gửi email thành công
                JOptionPane.showMessageDialog(null, "Gửi email thành công!");
            } else {
                // Xử lý khi gặp lỗi trong quá trình gửi email
                JOptionPane.showMessageDialog(null, "Gặp lỗi trong quá trình gửi email");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

