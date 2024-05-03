package utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;

import config.MailConfig;
import entity.BangLuongNhanVien;

public class Email {
    static final String from = MailConfig.APP_EMAIL;
    static final String password = MailConfig.APP_PASSWORD;

    public static boolean guiMailChoNguoiDung(String to, String tieuDe, BangLuongNhanVien bangLuong) {

    	 Properties props = new Properties();
    	    props.put("mail.smtp.host", MailConfig.HOST_NAME); // SMTP HOST
    	    props.put("mail.smtp.port", MailConfig.TSL_PORT+""); // TLS 587 SSL 465
    	    props.put("mail.smtp.auth", "true");
    	    props.put("mail.smtp.starttls.enable", "true");
    	    props.put("mail.smtp.ssl.protocols", MailConfig.PROTOCOL);

    	    // create Authenticator
    	    Authenticator auth = new Authenticator() {
    	        @Override
    	        protected PasswordAuthentication getPasswordAuthentication() {
    	            return new PasswordAuthentication(from, password);
    	        }
    	    };
    	    Session session = Session.getInstance(props, auth);
    	    MimeMessage msg = new MimeMessage(session);

    	    try {
    	        msg.setFrom(new InternetAddress(from));
    	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
    	        msg.setSubject(tieuDe);
    	        msg.setSentDate(new Date());
    	        String content = buildSalaryContent(bangLuong);
    	        msg.setContent(content, "text/HTML; charset=UTF-8");
    	        Transport.send(msg);
    	        System.out.println("Gửi email thành công");
    	        return true;
    	    } catch (MessagingException e) {
    	        System.out.println("Gặp lỗi trong quá trình gửi email");
    	        e.printStackTrace();
    	        return false;
    	    }
    }
    
    private static String buildSalaryContent(BangLuongNhanVien bangLuong) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("<h2>Bảng Lương Nhân Viên</h2>");
        contentBuilder.append("<table border=\"1\">");
        contentBuilder.append("<tr><th>Mã Bảng Lương</th><th>Mã Nhân Viên</th><th>Lương Cơ Sở</th><th>Hệ Số</th><th>Ngày Lập</th><th>Số Ngày Đi Làm</th><th>Số Ngày Làm Ca Ngày</th><th>Số Ngày Làm Nửa Ngày</th><th>Số Ngày Vắng Có Phép</th><th>Số Ngày Trễ</th><th>Số Giờ Tăng Ca</th><th>Số Ngày Vắng Không Phép</th></tr>");
        
        // Add data for each column
        contentBuilder.append("<tr>");
        contentBuilder.append("<td>").append(bangLuong.getMaBangLuong()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getNhanVien().getMaNhanSu()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getNhanVien().getChucVu().getHeSoLuong()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getNhanVien().getHeSoLuong()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getNgayLap()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayDiLam()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayLamCaNgay()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayLamNuaNgay()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayVangCoPhep()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayTre()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoGioTangCa()).append("</td>");
        contentBuilder.append("<td>").append(bangLuong.getSoNgayVangKhongPhep()).append("</td>");
        contentBuilder.append("</tr>");
        
        contentBuilder.append("</table>");
        return contentBuilder.toString();
    }

    private static String generateVerificationCode() {
        // Tạo mã xác nhận ngẫu nhiên, ví dụ sử dụng Apache Commons Codec
        byte[] randomBytes = new byte[4];
        new java.security.SecureRandom().nextBytes(randomBytes);
        return Base64.encodeBase64URLSafeString(randomBytes);
    }
    
    public static String guiMaXacNhanChoNguoiDung(String to) {
    	Properties props = new Properties();
	    props.put("mail.smtp.host", MailConfig.HOST_NAME); // SMTP HOST
	    props.put("mail.smtp.port", MailConfig.TSL_PORT+""); // TLS 587 SSL 465
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.protocols", MailConfig.PROTOCOL);

	    // create Authenticator
	    Authenticator auth = new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, password);
	        }
	    };
	    Session session = Session.getInstance(props, auth);
	    MimeMessage msg = new MimeMessage(session);

	    try {
	    	String maXacNhan = generateVerificationCode();
	        msg.setFrom(new InternetAddress(from));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
	        msg.setSubject("Xác Nhận lấy lại mật khẩu");
	        msg.setSentDate(new Date());
	        String content = "Mã xác nhận người dùng là:"+maXacNhan;
	        msg.setContent(content, "text/HTML; charset=UTF-8");
	        Transport.send(msg);
	        System.out.println("Gửi email thành công");
	        return maXacNhan;
	    } catch (MessagingException e) {
	        System.out.println("Gặp lỗi trong quá trình gửi email");
	        e.printStackTrace();
	        return "";
	    }
    }
    
    public static void main(String[] args) {
		System.out.println(generateVerificationCode());
	}
}
