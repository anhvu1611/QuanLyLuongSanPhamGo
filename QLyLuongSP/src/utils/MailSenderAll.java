package utils;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import DAO.BangLuongNhanVien_Dao;
import entity.BangLuongNhanVien;

public class MailSenderAll extends SwingWorker<Void, Void> {
    private String bangMa[] = null;

    public MailSenderAll(String[] bangMa) {
        this.bangMa = bangMa;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (int i = 0; i < bangMa.length; i++) {
            BangLuongNhanVien bangLuongNhanVien = BangLuongNhanVien_Dao.timBangLuongBangMa(bangMa[i]);
            System.out.println(bangLuongNhanVien.toString());
            Email.guiMailChoNguoiDung(bangLuongNhanVien.getNhanVien().getEmail(), "Bảng Lương Nhân Viên", bangLuongNhanVien);
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            // Xử lý khi công việc nền hoàn thành
            JOptionPane.showMessageDialog(null, "Đã gửi email xong!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
