package dao;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiSach;
import entity.NhaSanXuat;

public class DAO_NSX {
	public ArrayList<NhaSanXuat> getAllNSX(){
		ArrayList<NhaSanXuat> dsNSX = new ArrayList<NhaSanXuat>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NHASANXUAT";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNSX = rs.getString(1);
				String tenNSX = rs.getString(2);
				String thanhPho = rs.getString(3);
				String email = rs.getString(4);
				String sdt = rs.getString(5);
				
				NhaSanXuat nsx = new NhaSanXuat(maNSX,tenNSX,thanhPho,email,sdt);
				dsNSX.add(nsx);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNSX;
	}
	public NhaSanXuat getNSXTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhaSanXuat nhasanxuat = null;
		try {
			PreparedStatement pstm = con.prepareStatement("select * from NHASANXUAT where MANSX = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				nhasanxuat = new NhaSanXuat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nhasanxuat;
	}

	public NhaSanXuat getNSXTheoTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		NhaSanXuat nhasanxuat = null;
		try {
			pstm = con.prepareStatement("select * from NHASANXUAT where TENNSX = ?");
			pstm.setString(1, ten);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				nhasanxuat = new NhaSanXuat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nhasanxuat;
	}

//	public boolean addNhaSanXuat(NhaSanXuat nhaSanXuat) {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement pstm = null;
//		int n = 0;
//		try {
//			pstm = con.prepareStatement("insert into NHASANXUAT values(?,?,?,?,?)");
//			pstm.setString(2, nhaSanXuat.getTenNSX());
//			pstm.setString(3, nhaSanXuat.getThanhPho());
//			pstm.setString(4, nhaSanXuat.getEmail());
//			pstm.setString(5, nhaSanXuat.getSdt());
//			pstm.setString(1, nhaSanXuat.getMaNSX());
//			n = pstm.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				pstm.close();
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//			}
//		}
//		return n > 0;
//	}

	public boolean updateNhaSanXuat(NhaSanXuat nhaSanXuat) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update NHASANXUAT set TENNSX = ?, THANHPHO = ?, EMAIL = ?, SDT = ? where MANSX = ?");
			pstm.setString(1, nhaSanXuat.getTenNSX());
			pstm.setString(2, nhaSanXuat.getThanhPho());
			pstm.setString(3, nhaSanXuat.getEmail());
			pstm.setString(4, nhaSanXuat.getSdt());
			pstm.setString(5, nhaSanXuat.getMaNSX());
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

	
	public boolean createNSX(NhaSanXuat nsx) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			st = con.prepareStatement("insert into NHASANXUAT" + " values(?,?,?,?,?)");
			
			st.setString(1, nsx.getMaNSX());
			st.setString(2, nsx.getTenNSX());
			st.setString(3, nsx.getThanhPho());
			st.setString(4, nsx.getEmail());
			st.setString(5, nsx.getSdt());
			
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
	public boolean xoaNSX(String maNSX) {
		int k = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from NHASANXUAT where MANSX = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maNSX);
			k = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return k>0;
	}
	
	public String taoMaNSX() {
        String maMau = "NSX000001"; // Giá trị mặc định nếu không có dữ liệu trong bảng
        int soCuoi = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) AS SOLUONG FROM NHASANXUAT";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
             soCuoi = rs.getInt("SOLUONG");
            }


                if (soCuoi > 0 ) {
                    int soMoi = soCuoi + 1;
                    String numberStr = String.format("%06d", soMoi);
                    maMau = "NSX" + numberStr;
                }
                else {
                	return null;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maMau;
}
}

