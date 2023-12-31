package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

public class DAO_TaiKhoan {
	DAO_NhanVien dao_NhanVien = new DAO_NhanVien();

	public DAO_TaiKhoan() {
		ConnectDB.getInstance().connect();
	}
	public TaiKhoan getTaiKhoanTheoMa(String maTim) {
		TaiKhoan taiKhoan = new TaiKhoan();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from TAIKHOAN where MATAIKHOAN = ?");
			pstm.setString(1, maTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				taiKhoan = new TaiKhoan(rs.getString("MATAIKHOAN"), rs.getString("MATKHAU"), rs.getString("PHANQUYEN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoan;
	}
	public ArrayList<TaiKhoan> getAllTK(){
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TAIKHOAN";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maTaiKhoan = rs.getString(1);
				String matKhau = rs.getString(2);
				String phanQuyen = rs.getString(3);
				
				TaiKhoan cv = new TaiKhoan(maTaiKhoan, matKhau, phanQuyen);
				dsTK.add(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsTK;
	}

	// xoá 
	public boolean xoaTK(String maTaiKhoan) {
		int k = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from TAIKHOAN where MATAIKHOAN = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maTaiKhoan);
			k = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return k>0;
	}
	
	// Thêm 
	public boolean createTK(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			st = con.prepareStatement("insert into " + "TAIKHOAN values(?,?,?)");
			st.setString(1, tk.getMaTaiKhoan());
			st.setString(2, tk.getMatKhau());
			st.setString(3, tk.getPhanQuyen());
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
	public boolean updateTK(TaiKhoan TK) {
	    int k = 0;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        
	        // Customize the SQL statement based on your database schema
	        String sql = "UPDATE TAIKHOAN SET MATKHAU = ?, PHANQUYEN = ? WHERE MATAIKHOAN = ?";
	        
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setString(1, TK.getMatKhau());
	        preparedStatement.setString(2, TK.getPhanQuyen());
	        preparedStatement.setString(3, TK.getMaTaiKhoan());
	        k = preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return k > 0;
	}
	public boolean capNhapMatKhau(TaiKhoan taiKhoan) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update TAIKHOAN set MATKHAU = ? where MATAIKHOAN = ?");
			pstm.setString(2, taiKhoan.getMaTaiKhoan());
			pstm.setString(1, taiKhoan.getMatKhau());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean suaMatKhau(String tentk, String mk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update TAIKHOAN set MATKHAU = ? where MATAIKHOAN = ?");
			pstm.setString(2, tentk);
			pstm.setString(1, mk);
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
}
