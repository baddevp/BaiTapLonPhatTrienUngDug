package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.KhachHang;

public class DAO_ChucVu {
	public ArrayList<ChucVu> getAllCV(){
		ArrayList<ChucVu> dsCV = new ArrayList<ChucVu>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from CHUCVU";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maChucVu = rs.getString(1);
				String tenChucVu = rs.getString(2);
				
				ChucVu cv = new ChucVu(maChucVu,tenChucVu);
				dsCV.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsCV;
	}
	
	//Mã tự phát sinh
	public String generateNewMaChucVu() {
        String newMaChucVu = "CV001"; // Giá trị mặc định nếu không có dữ liệu trong bảng

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT MAX(MACHUCVU) FROM CHUCVU";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String lastMaChucVu = rs.getString(1);

                if (lastMaChucVu != null) {
                    int number = Integer.parseInt(lastMaChucVu.substring(2)) + 1;
                    String numberStr = String.format("%03d", number);
                    newMaChucVu = "CV" + numberStr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMaChucVu;
    }
	
	// xoá 
	public boolean xoaCV(String maChucVu) {
		int k = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from CHUCVU where MACHUCVU = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maChucVu);
			k = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return k>0;
	}
	
	// Thêm 
	public boolean createCV(ChucVu cv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			st = con.prepareStatement("insert into " + "CHUCVU values(?,?)");
			
			st.setString(1, cv.getMaChucVu());
			st.setString(2, cv.getTenChucVu());
			n = st.executeUpdate();
		    } catch (SQLException e) {
		    // TODO: handle exception
		        e.printStackTrace();
	    } finally {
		    try {
			    st.close();
		    } catch (SQLException e2) {
			    // TODO: handle exception
			    e2.printStackTrace();
		    }
		}
	    return n>0;
    }
	
	// sửa chức vụ
	public boolean updateCV(String maChucVu, String tenChucVu) {
	    int k = 0;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Customize the SQL statement based on your database schema
	        String sql = "UPDATE CHUCVU SET TENCHUCVU = ? WHERE MACHUCVU = ?";
	        
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setString(1, tenChucVu);
	        preparedStatement.setString(2, maChucVu);
	        
	        k = preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return k > 0;
	}
	
	public ChucVu getChucVuTheoMa(String ma){
		ChucVu cv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from CHUCVU where MACHUCVU = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ma);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String maCV = rs.getString(1);
				String tenCV = rs.getString(2);
				
				
				cv = new ChucVu(maCV, tenCV);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cv;
	}
	public ChucVu getChucVuTheoTen(String ten){
		ChucVu cv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from CHUCVU where TENCHUCVU LIKE '%?'";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ten);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String maCV = rs.getString(1);
				String tenCV = rs.getString(2);
				
				
				cv = new ChucVu(maCV, tenCV);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cv;
	}
	public int demChucVu() {
		int dem = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT COUNT(*) FROM CHUCVU";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
               
                dem = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dem;
	}
	
}
