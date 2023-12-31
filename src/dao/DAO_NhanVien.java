package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.HinhAnh;
import entity.NhanVien;


public class DAO_NhanVien {
	private String gt;
	DAO_ChucVu dao_ChucVu = new DAO_ChucVu();
	DAO_HinhAnh dao_HinhAnh = new DAO_HinhAnh();
	public ArrayList<NhanVien> getAllNV(){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NHANVIEN";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				String diaChi = rs.getString(4);
				Date NgayvaoLam = rs.getDate(5);
				String sdt = rs.getString(6);
				String cccd = rs.getString(7);
				boolean gioitinh = rs.getBoolean(8);
				
				if (gioitinh == true) {
					gt = "Nữ";
				}else {
					gt = "Nam";
				}
				ChucVu maCV = dao_ChucVu.getChucVuTheoMa(rs.getString(9));
				HinhAnh maHinhAnh = new HinhAnh(rs.getString(10));
				
				NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, diaChi, NgayvaoLam, sdt, cccd, gioitinh, maCV, maHinhAnh);
				dsNV.add(nv);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsNV; 
	}
	//Lấy nhân viên theo mã
	public NhanVien getNhanVienTheoMa2(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien nhanVien = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from NHANVIEN where MANV = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				String diaChi = rs.getString(4);
				Date NgayvaoLam = rs.getDate(5);
				String sdt = rs.getString(6);
				String cccd = rs.getString(7);
				boolean gioitinh = rs.getBoolean(8);
				
				if (gioitinh == true) {
					gt = "Nữ";
				}else {
					gt = "Nam";
				}
				ChucVu maCV = dao_ChucVu.getChucVuTheoMa(rs.getString("MACHUCVU"));
				HinhAnh maHinhAnh = dao_HinhAnh.getHinhAnhTheoMa(rs.getString("MAANH"));
				
				nhanVien = new NhanVien(maNV, tenNV, ngaySinh, diaChi, NgayvaoLam, sdt, cccd, gioitinh, maCV, maHinhAnh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return nhanVien;
	}
	public ArrayList<NhanVien> getNhanVienTheoMa(String ma){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NHANVIEN where MANV = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ma);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				String diaChi = rs.getString(4);
				Date NgayvaoLam = rs.getDate(5);
				String sdt = rs.getString(6);
				String cccd = rs.getString(7);
				boolean gioitinh = rs.getBoolean(8);
				
				if (gioitinh == true) {
					gt = "Nữ";
				}else {
					gt = "Nam";
				}
				ChucVu maCV = new ChucVu(rs.getString(9));
				HinhAnh maHinhAnh = new HinhAnh(rs.getString(10));
				
				NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, diaChi, NgayvaoLam, sdt, cccd, gioitinh, maCV, maHinhAnh);
				dsNV.add(nv);
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return dsNV;	
	}
	
	public int getCurrentSequenceNumber() {
	    int newMaNV = 0;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT MAX(MANV) FROM NHANVIEN";
	        Statement stm = con.createStatement();
	        ResultSet rs = stm.executeQuery(sql);

	        if (rs.next()) {
	            String lastMaNV = rs.getString(1);

	            // kiểm tra xem dữ liệu được truy xuất MANVcó phải là null hay không, 
	            // bắt đầu bằng "NV" và có độ dài ít nhất là 11. Nếu đúng, nó sẽ trích xuất phần số thứ tự của ID nhân viên.
	            if (lastMaNV != null && lastMaNV.startsWith("NV") && lastMaNV.length() >= 11) {
	            	
	                // Extract the sequence number part from MANV
	                String sequenceNumberPart = lastMaNV.substring(10);

	                // Phần số thứ tự được trích xuất sẽ được chuyển thành số nguyên ( newMaNV). 
	            	// Đây sẽ là cơ sở để tạo ID nhân viên tiếp theo.
	                newMaNV = Integer.parseInt(sequenceNumberPart);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return newMaNV;
	}
	
	
	public boolean createNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			st = con.prepareStatement("insert into " + "NHANVIEN values(?,?,?,?,?,?,?,?,?,?)");
			
			st.setString(1, nv.getMaNV());
			st.setString(2, nv.getTenNV());
			st.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
			st.setString(4, nv.getDiaChi());
			st.setDate(5, new java.sql.Date(nv.getNgayVaoLam().getTime()));
			st.setString(6, nv.getSdt());
			st.setString(7, nv.getCccd());
			st.setBoolean(8, nv.getGioiTinh());
			st.setString(9, nv.getChucVu().getMaChucVu());
			st.setString(10, nv.getHinhAnh().getMaAnh());
			
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
	
	// Thêm phương thức getMaAnhByMaNV
	public String getMaAnhByMaNV(String maNV) {
        String maAnh = null;

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT MAANH FROM NHANVIEN WHERE MANV = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, maNV);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        maAnh = resultSet.getString("MAANH");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maAnh;
    }

    // Thêm phương thức getHinhAnhByMaAnh
    public HinhAnh getHinhAnhByMaAnh(String maAnh) {
        HinhAnh hinhAnh = null;

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HINHANH WHERE MAANH = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, maAnh);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Giả sử HinhAnh có các thuộc tính MAANH, TENANH, URL
                        String maAnhResult = resultSet.getString("MAANH");
                        String tenAnh = resultSet.getString("TENANH");
                        String url = resultSet.getString("URL");

                        // Tạo đối tượng HinhAnh từ dữ liệu lấy từ cơ sở dữ liệu
                        hinhAnh = new HinhAnh(maAnhResult, tenAnh, url);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hinhAnh;
    }
    
    public String getGioiTinhByMaNV(String maNV) {
        String gioiTinh = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT GIOITINH FROM NHANVIEN WHERE MANV = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, maNV);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        boolean gioitinhBoolean = resultSet.getBoolean(1);
                        gioiTinh = gioitinhBoolean ? "Nữ" : "Nam";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gioiTinh;
    }
    
    public String getGioiTinhByMaCV(String maNV) {
        String chuvu = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT MACHUCVU FROM NHANVIEN WHERE MANV = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, maNV);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        chuvu = resultSet.getString(1);              
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chuvu;
    }
    
    public boolean xoaNV(String maNV) {
		int k = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "delete from NHANVIEN where MANV = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maNV);
			k = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return k>0;
	}
    
	public boolean updateTTNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update NhanVien set TENNV = ?, GIOITINH = ?, NGAYSINH = ?, CCCD = ?, DIACHI = ?, SDT = ? , MACHUCVU = ? where MANV = ?");
			pstm.setString(1, nhanVien.getTenNV());
			pstm.setBoolean(2, nhanVien.getGioiTinh());
			pstm.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
			pstm.setString(4, nhanVien.getCccd());
			pstm.setString(5, nhanVien.getDiaChi());
			pstm.setString(6, nhanVien.getSdt());
			pstm.setString(7, nhanVien.getChucVu().getMaChucVu());
			pstm.setString(8, nhanVien.getMaNV());
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
	public String layNgayNhanVienTruoc() {
		String ngayCu = null;
		try {
			ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT TOP 1 MANV FROM NHANVIEN ORDER BY NGAYVAOLAM DESC, MANV DESC";
	        Statement stm = con.createStatement();
	        ResultSet rs = stm.executeQuery(sql);
	        
	        if (rs.next()) {
	            String lastNV = rs.getString(1);

	            // Check if lastMaKH is not null and has the expected format
	          //HT20112023002013
	            if (lastNV != null && lastNV.startsWith("NV") && lastNV.length() >= 12) {
	                // Extract the sequence number part from MAKH
	            	//NV14112023001
	               ngayCu = lastNV.substring(2,10);

	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ngayCu;
	}
	//Lấy mã hóa đơn cuối
		public int getCurrentSequenceNumber2() {
			int newMaHD = 0;
			try {
				ConnectDB.getInstance();
		        Connection con = ConnectDB.getConnection();
		        String sql = "SELECT TOP 1 MANV FROM NHANVIEN ORDER BY NGAYVAOLAM DESC, MANV DESC";
		        Statement stm = con.createStatement();
		        ResultSet rs = stm.executeQuery(sql);
		        
		        if (rs.next()) {
		            String lastNV = rs.getString(1);

		            // Check if lastMaKH is not null and has the expected format
		            	//NV14112023001
		            if (lastNV != null && lastNV.startsWith("NV") && lastNV.length() >= 12) {
		                // NV14112023001
		                String sequenceNumberPart = lastNV.substring(10);

		                // Convert the extracted part to an integer
		                newMaHD = Integer.parseInt(sequenceNumberPart);
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return newMaHD;
		}
}
