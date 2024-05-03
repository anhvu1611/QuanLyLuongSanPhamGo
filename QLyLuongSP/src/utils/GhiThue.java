package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
/**
 *
 * @author ACER
 */
public class GhiThue {
	private static String filePath = "src/config/ThueConfig.properties";
	private static Properties properties = readConfigFile(filePath);
	public static double THUE_TNCN_DUOI_5TRIEU = Double.parseDouble(properties.getProperty("TNTTDuoi5Trieu"));
	public static double THUE_TNCN_TU_5_TOI_10TRIEU = Double.parseDouble(properties.getProperty("TNTTTu5Toi10Trieu"));
	public static double THUE_TNCN_TU_10_TOI_18TRIEU = Double.parseDouble(properties.getProperty("TNTTTu10Toi18Trieu"));
	public static double THUE_TNCN_TU_18_TOI_32TRIEU = Double.parseDouble(properties.getProperty("TNTTTu18Toi32Trieu"));
	public static double THUE_TNCN_TU_32_TOI_50TRIEU = Double.parseDouble(properties.getProperty("TNTTTu32Toi50Trieu"));
	public static double THUE_TNCN_TU_50_TOI_80TRIEU = Double.parseDouble(properties.getProperty("TNTTTu50Toi80Trieu"));
	public static double THUE_TNCN_TREN_80 = Double.parseDouble(properties.getProperty("TNTTTren80"));
	
	public static ArrayList<Vector<String>> layDuLieuThue() {
		Properties properties = readConfigFile(filePath);
		ArrayList<Vector<String>> bangThue = new ArrayList<>();
		Vector<String> data1 = new Vector<>();
		data1.add("Dưới 5 Triệu");
		data1.add(properties.getProperty("TNTTDuoi5Trieu"));
		bangThue.add(data1);
		Vector<String> data2 = new Vector<>();
		data2.add("Từ 5 Tới 10 Triệu");
		data2.add(properties.getProperty("TNTTTu5Toi10Trieu"));
		bangThue.add(data2);
		Vector<String> data3 = new Vector<>();
		data3.add("Từ 10 Tới 18 Triệu");
		data3.add(properties.getProperty("TNTTTu10Toi18Trieu"));
		bangThue.add(data3);
		Vector<String> data4 = new Vector<>();
		data4.add("Từ 18 Tới 32 Triệu");
		data4.add(properties.getProperty("TNTTTu18Toi32Trieu"));
		bangThue.add(data4);
		Vector<String> data5 = new Vector<>();
		data5.add("Từ 32 Tới 50 Triệu");
		data5.add(properties.getProperty("TNTTTu32Toi50Trieu"));
		bangThue.add(data5);
		Vector<String> data6 = new Vector<>();
		data6.add("Từ 50 Tới 80 Triệu");
		data6.add(properties.getProperty("TNTTTu50Toi80Trieu"));
		bangThue.add(data6);
		Vector<String> data7 = new Vector<>();
		data7.add("Trên 80 Triệu");
		data7.add(properties.getProperty("TNTTTren80"));
		bangThue.add(data7);
		
		return bangThue;
	}
	
	
	public static void thayDoiGiaTriThue(String duoi5Trieu, String tu5Toi10Trieu, String tu10Toi18Trieu, String tu18Toi32Trieu, 
										 String tu32Toi50Trieu, String tu50Toi80Trieu, String tren80TrieuTroDi ) {
		try {
			Properties properties = readConfigFile(filePath);
			properties.setProperty("TNTTDuoi5Trieu", duoi5Trieu);
			properties.setProperty("TNTTTu5Toi10Trieu", tu5Toi10Trieu);
			properties.setProperty("TNTTTu10Toi18Trieu", tu10Toi18Trieu);
			properties.setProperty("TNTTTu18Toi32Trieu", tu18Toi32Trieu);
			properties.setProperty("TNTTTu32Toi50Trieu", tu32Toi50Trieu);
			properties.setProperty("TNTTTu50Toi80Trieu", tu50Toi80Trieu);
	        properties.setProperty("TNTTTren80", tren80TrieuTroDi);
	        writeConfigFile(filePath, properties);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    
    private static Properties readConfigFile(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
    private static void writeConfigFile(String filePath, Properties properties) {
        try (OutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}