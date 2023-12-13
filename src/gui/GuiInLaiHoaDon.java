package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import connectDB.ConnectDB;
import dao.DAO_ChiTietHoaDon;
import dao.DAO_ChiTietHoanTra;
import dao.DAO_HoaDon;
import dao.DAO_HoaDonTraHang;
import dao.DAO_KhachHang;
import dao.DAO_MauSac;
import dao.DAO_NhanVien;
import dao.DAO_QuanLySach;
import dao.DAO_QuanLyVPP;
import entity.ChiTietHoaDon;
import entity.ChiTietHoanTra;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.KhachHang;
import entity.MauSac;
import entity.NhanVien;
import entity.Sach;
import entity.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class GuiInLaiHoaDon extends JFrame implements ActionListener, MouseListener {

	public static JPanel contentPane;
	DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
	DAO_HoaDonTraHang dao_HoaDonHT = new DAO_HoaDonTraHang();
	DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	DAO_ChiTietHoaDon dao_CTHD = new DAO_ChiTietHoaDon();
	DAO_ChiTietHoanTra dao_chiTietTra = new DAO_ChiTietHoanTra();
	DAO_ChiTietHoanTra dao_HoanTra = new DAO_ChiTietHoanTra();
	DAO_QuanLySach dao_QLSach = new DAO_QuanLySach();
	DAO_QuanLyVPP dao_QLVPP = new DAO_QuanLyVPP();
	private JTextField txtTimKiem;
	private DefaultTableModel modelHD;
	private JTable tblHD;

	private JButton btnChon;
	private JPanel pnlChonHDTH;
	private JComboBox cbxPhanLoai;
	private PdfPTable tableTien;
	private PdfPTable tableTienKhach;


	static NhanVien nv;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiInLaiHoaDon frame = new GuiInLaiHoaDon(nv);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiInLaiHoaDon(NhanVien nv) {
		this.nv = nv;
		this.setTitle("Quản lý khách hàng");
		this.setSize(1930, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(51, 204, 204));

		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226);
		Color color2 = new Color(233, 221, 244);

		// Tiêu đề
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBounds(10, 10, 1891, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);

		JLabel lblTieuDe = new JLabel("IN HÓA ĐƠN");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));
		// Form chon hoa don tra hang
		pnlChonHDTH = new JPanel();
		pnlChonHDTH.setBackground(new Color(255, 255, 255));

		pnlChonHDTH.setBounds(10, 79, 1891, 890);
		contentPane.add(pnlChonHDTH);
		pnlChonHDTH.setLayout(null);


		txtTimKiem = new JTextField("Nhập thông tin cần tìm");
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimKiem.setBounds(370, 24, 800, 40);
		pnlChonHDTH.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		txtTimKiem.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtTimKiem.getText().trim();
				timKiemTheoMaHD(tuKhoa);
			}

		});

		modelHD = new DefaultTableModel();
		modelHD.addColumn("Mã hóa đơn");
		modelHD.addColumn("Ngày lập hóa đơn");
		modelHD.addColumn("Tổng tiền");
		modelHD.addColumn("Nhân viên");
		modelHD.addColumn("Khách hàng");
		modelHD.addColumn("Trạng thái");
		tblHD = new JTable(modelHD);
		tblHD.setBackground(new Color(153, 204, 255));
		tblHD.setFont(font2);
		tblHD.setRowHeight(35);
		JTableHeader tbHeaderHD = tblHD.getTableHeader();
		tbHeaderHD.setFont(font2);
		tbHeaderHD.setBackground(new Color(51, 204, 204));
		JScrollPane jScrollPane = new JScrollPane(tblHD);
		jScrollPane.setBounds(20, 81, 1850, 796);
		pnlChonHDTH.add(jScrollPane);

		JLabel lblNewLabel = new JLabel("Chọn hóa đơn hoàn trả :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(59, 25, 268, 33);
		pnlChonHDTH.add(lblNewLabel);

		btnChon = new JButton("Chọn");
		btnChon.setBackground(new Color(51, 204, 204));
		btnChon.setForeground(new Color(255, 255, 255));
		btnChon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnChon.setBounds(1270, 24, 250, 40);
		pnlChonHDTH.add(btnChon);

		cbxPhanLoai = new JComboBox();
		cbxPhanLoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cbxPhanLoai.setBounds(1620, 24, 250, 40);
		cbxPhanLoai.addItem("Thanh toán");
		cbxPhanLoai.setSelectedIndex(0);
		cbxPhanLoai.addItem("Trả hàng");
		cbxPhanLoai.addItem("Tất cả");
		pnlChonHDTH.add(cbxPhanLoai);
		cbxPhanLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedCategory = cbxPhanLoai.getSelectedItem().toString();
				if (selectedCategory.equalsIgnoreCase("Thanh toán")) {
					modelHD.setRowCount(0);
					DocDuLieuDatataabase(selectedCategory, nv);
				} else if (selectedCategory.equalsIgnoreCase("Trả hàng")) {
					modelHD.setRowCount(0);
					DocDuLieuDatataabase(selectedCategory, nv);
				} else {
					modelHD.setRowCount(0);
					DocDuLieuDatataabase("Tất cả", nv);
				}

			}
		});

		txtTimKiem.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập thông tin cần tìm")) {
					txtTimKiem.setText("");
					txtTimKiem.setForeground(Color.BLACK); // Đổi màu chữ khi có focus
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Nhập thông tin cần tìm");
					txtTimKiem.setForeground(Color.GRAY); // Đổi màu chữ gợi ý khi mất focus
				}
			}
		});
		;
		;

		

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		DocDuLieuDatataabase("Thanh toán", nv);

		// Bắt sự kiện
		btnChon.addActionListener(this);
		tblHD.addMouseListener(this);
	

	}

	public void DocDuLieuDatataabase(String tt, NhanVien nv) {
		if(tt.equalsIgnoreCase("Thanh toán")) {
			docHDThanhToan();
		}else if(tt.equalsIgnoreCase("Trả hàng")) {
			docHDTraHang();
		}else {
			docHDThanhToan();
			docHDTraHang();
		}
		
	}
	public void docHDThanhToan() {
		for(HoaDon hd : dao_HoaDon.getHDNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
			KhachHang kh = dao_KhachHang.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
			if(hd.isTrangThai() == true) {
				modelHD.addRow(new Object[] { hd.getMaHoaDon(), hd.getNgayLapHoaDon(), hd.getTongTien(),
						hd.getNhanVien().getTenNV(), kh.getTenKH(), strTrangThai(hd.isTrangThai()) });	
			}
		}
	}
	public void docHDTraHang() {
		for(HoaDonHoanTra hd : dao_HoaDonHT.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
			KhachHang kh = dao_KhachHang.getKhachHangTheoMa(hd.getKhachHang().getMaKH());
				modelHD.addRow(new Object[] { hd.getMaYeuCauTraHang(), hd.getNgayLapHoaDonTH(), hd.getTongHoanTra(),
						hd.getNhanVien().getTenNV(), kh.getTenKH(), "Trả hàng" });	
			
		}
	}

	public String strTrangThai(Boolean tt) {
		if (tt) {
			return "Đã thanh toán";
		}
		return "Chờ thanh toán";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnChon)) {
			int selectedRow = tblHD.getSelectedRow();
			if(selectedRow == -1 ) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để in lại");
			}else {
				String maHD = (String) tblHD.getValueAt(selectedRow, 0);
				if(isHoaDonMua(maHD)) {
					inLaiHoaDonMua(dao_HoaDon.getHDTheoMaHD(maHD));
				}else {
					inLaiHoaDonTra(dao_HoaDonHT.getHDHTTheoMaHDTH(maHD));
				}
				
			}
		}
	
	

	}	
	// Tìm hóa đơn trả hàng theo mã
	public void timKiemTheoMaHD(String tuKhoa) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelHD);
		tblHD.setRowSorter(sorter);

		if (tuKhoa.isEmpty()) {
			sorter.setRowFilter(null);
		} else {

			RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa), 1, 0);
			sorter.setRowFilter(filter);

		}
	}
	//Kiểm tra hóa đơn thanh toán
	public boolean isHoaDonMua(String mahd) {
	    return mahd.startsWith("HD");
	}
	//Kiểm tra hóa đơn trả
	public boolean isHoaDonTra(String mahd) {
	    return mahd.startsWith("TH");
	}
	//In hóa đơn trả
		public void inLaiHoaDonTra(HoaDonHoanTra hd) {
	        // Lấy thông tin từ tblSPHD, tên nhân viên và ngày thanh toán
	        String tenNhanVien = hd.getNhanVien().getTenNV();
	        Date ngayThanhToan = Date.from(hd.getNgayLapHoaDonTH().atZone(ZoneId.systemDefault()).toInstant());

	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
	        String ngayThanhToanFormatted = sdf.format(ngayThanhToan);

	     

	        // Tạo đối tượng Document
	        Document document = new Document();
	        
	        try {
	        	String fontPath = "src/font/DejaVuSans.ttf";
	        	
	        	if (!Files.exists(Paths.get(fontPath))) {
	                System.err.println("Font file not found. Check the fontPath: " + fontPath);
	                return;
	            }

	        	BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

	        	com.itextpdf.text.Font fontTieuDe = new com.itextpdf.text.Font(baseFont, 15, Font.BOLD);
	        	com.itextpdf.text.Font fontHead = new com.itextpdf.text.Font(baseFont, 12, Font.BOLD);
	        	com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12, Font.PLAIN);
	        	
	            // Đặt tên file PDF và tạo đối tượng PdfWriter
	        	String maHD = hd.getMaYeuCauTraHang();
	            String fileName = "HoaDon_" + maHD + ".pdf";
	            PdfWriter.getInstance(document, new FileOutputStream(fileName));

	            // Mở document để bắt đầu viết
	            document.open();

	            // Thêm thông tin hóa đơn vào document
	            Paragraph paragraph2 = new Paragraph("CÔNG TY CP SÁCH GIÁO DỤC TẠI TP. HCM", font);
	            Paragraph paragraph3 = new Paragraph("HIỆU SÁCH TƯ NHÂN FUTUREZE", fontHead);
	            Paragraph paragraph4 = new Paragraph("Nhóm 14", font);
	            Paragraph paragraph5 = new Paragraph("HÓA ĐƠN TRẢ HÀNG", fontTieuDe);

	            // Đặt căn giữa cho đối tượng Paragraph
	            paragraph3.setAlignment(Element.ALIGN_CENTER);
	            paragraph2.setAlignment(Element.ALIGN_CENTER);
	            paragraph4.setAlignment(Element.ALIGN_CENTER);
	            paragraph5.setAlignment(Element.ALIGN_CENTER);

	            // Thêm đối tượng Paragraph vào tài liệu
	            document.add(paragraph2);
	            document.add(paragraph3);
	            document.add(paragraph4);
	            document.add(paragraph5);
	            document.add(new Paragraph("\n"));
	            
	            document.add(new Paragraph("Mã hóa đơn: " + maHD, font));
	            document.add(new Paragraph("Ngày Thanh Toán: " + ngayThanhToanFormatted, font));
	            document.add(new Paragraph("Thu Ngân: " + tenNhanVien, font));
	            document.add(new Paragraph("\n"));
	            // Tạo bảng cho sản phẩm trong hóa đơn
	            PdfPTable table = new PdfPTable(5);
	            table.setWidthPercentage(100);

	            // Thêm font cho header của bảng
	            PdfPCell cellHeadTenSP = new PdfPCell(new Paragraph("Tên sản phẩm", fontHead));
	            PdfPCell cellHeadGia = new PdfPCell(new Paragraph("Số lượng mua", fontHead));
	            PdfPCell cellHeadSoLuong = new PdfPCell(new Paragraph("Giá", fontHead));
	            PdfPCell cellHeadSoLuongTra = new PdfPCell(new Paragraph("Số lượng trả", fontHead));
	            PdfPCell cellHeadThanhTien = new PdfPCell(new Paragraph("Thành tiền", fontHead));
	            

	            // Thêm header vào bảng
	            table.addCell(cellHeadTenSP);
	            table.addCell(cellHeadGia);
	            table.addCell(cellHeadSoLuong);
	            table.addCell(cellHeadSoLuongTra);
	            table.addCell(cellHeadThanhTien);
	            
	            // Lấy thông tin sản phẩm từ tblSPHD và thêm vào bảng
	            double tienDaMua = 0;
	    	    int tongSoTra = 0;
	    	    int tongSoMua = 0;
	    	    double tongTienTra = 0;
	            for(ChiTietHoanTra ct : dao_chiTietTra.getDSTHTheoMaYCHT(maHD)) {
	            	//Thêm tên sản phẩm
	            	PdfPCell cellSP = new PdfPCell(new Paragraph(ct.getSanPham().getTenSanPham(), font));
	            	int soLuongMua = dao_CTHD.laySoLuongSanPhamDaMua(maHD, ct.getSanPham().getMaSanPham());
	            	tongSoMua += soLuongMua;
	            	PdfPCell cellSL = new PdfPCell(new Paragraph(soLuongMua + "", font));
	            	double giaBan = ct.getSanPham().getGiaBan();
	            	tienDaMua += soLuongMua * giaBan;
	            	PdfPCell cellGia = new PdfPCell(new Paragraph(giaBan + "", font));
	            	int soTra = ct.getSoLuongTra();
	            	PdfPCell cellSoLuongTra = new PdfPCell(new Paragraph(soTra + "", font));
	            	double thanhTien = soTra * giaBan;
	            	PdfPCell cellThanhTien = new PdfPCell(new Paragraph(thanhTien + "", font));
	            	
	            	  cellSP.setBorder(PdfPCell.NO_BORDER);
	                  cellSL.setBorder(PdfPCell.NO_BORDER);
	                  cellGia.setBorder(PdfPCell.NO_BORDER);
	                  cellSoLuongTra.setBorder(PdfPCell.NO_BORDER);
	                  cellThanhTien.setBorder(PdfPCell.NO_BORDER);

	                  table.addCell(cellSP);
	                  table.addCell(cellSL);
	                  table.addCell(cellGia);
	                  table.addCell(cellSoLuongTra);
	                  table.addCell(cellThanhTien);
	            }

	           
	            
	    	    tableTien = new PdfPTable(3);
	            tableTien.setWidthPercentage(100);
	            
	            PdfPCell kt = new PdfPCell(new Paragraph("", fontHead));;
	    	    kt.setBorder(Rectangle.OUT_BOTTOM);
	            
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            
	            Locale localeVN = new Locale("vi", "VN");
	            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);
	            String strTongTien = currencyFormat.format(tienDaMua);
	            PdfPCell tongTienCK = new PdfPCell(new Paragraph("Số lượng mua :", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienCKValue = new PdfPCell(new Paragraph(tongSoMua  + "", font));
	            
	            PdfPCell tongTien = new PdfPCell(new Paragraph("Tổng tiền mua:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienValue = new PdfPCell(new Paragraph(strTongTien, font));

	            PdfPCell tongSLTra = new PdfPCell(new Paragraph("Số lượng trả:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongSoTraValue = new PdfPCell(new Paragraph(tongSoTra + "", font));
	            
	            String strTongTienTT = currencyFormat.format(tongTienTra);
	            PdfPCell tongTienKT = new PdfPCell(new Paragraph("Tổng tiền trả:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienKTValue = new PdfPCell(new Paragraph(strTongTienTT, font));
	            
	       
	           
	            
	            tongTien.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tongTienCK.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienCKValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tongTienKT.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienKTValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tongSLTra.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongSoTraValue.setBorder(PdfPCell.NO_BORDER);

	            tableTien.addCell(tongTien);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienValue);
	            
	            tableTien.addCell(tongTienCK);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienCKValue);
	            
	            tableTien.addCell(tongTienKT);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienKTValue);
	            
	            tableTien.addCell(tongSLTra);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongSoTraValue);
	            
	            // Thêm bảng vào document
	            document.add(table);
	            document.add(new Paragraph("\n"));
	            document.add(tableTien);
	            Paragraph prTH = new Paragraph("\n", font);
	            Paragraph prTHtt = new Paragraph("\n", font);
	            Paragraph prCamOn = new Paragraph("XIN CẢM ƠN QUÝ KHÁCH & HẸN GẶP LẠI", font);

	            // Đặt căn giữa cho đối tượng Paragraph
	            prTH.setAlignment(Element.ALIGN_CENTER);
	            prTHtt.setAlignment(Element.ALIGN_CENTER);
	            prCamOn.setAlignment(Element.ALIGN_CENTER);

	            // Thêm đối tượng Paragraph vào tài liệu
	            document.add(prTH);
	            document.add(prTHtt);
	            document.add(prCamOn);
	            
	            // Đóng document
	            document.close();

	            // Hiển thị thông báo thành công
	            JOptionPane.showMessageDialog(this, "Hóa đơn đã được xuất thành công.");

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất hóa đơn.");
	        }
	    }
		//In lại hóa đơn mua
		public void inLaiHoaDonMua(HoaDon hd) {
	        // Lấy thông tin từ tblSPHD, tên nhân viên và ngày thanh toán
	        String tenNhanVien = hd.getNhanVien().getTenNV();
	        Date ngayThanhToan = Date.from(hd.getNgayLapHoaDon().atZone(ZoneId.systemDefault()).toInstant());

	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
	        String ngayThanhToanFormatted = sdf.format(ngayThanhToan);

	     

	        // Tạo đối tượng Document
	        Document document = new Document();
	        
	        try {
	        	String fontPath = "src/font/DejaVuSans.ttf";
	        	
	        	if (!Files.exists(Paths.get(fontPath))) {
	                System.err.println("Font file not found. Check the fontPath: " + fontPath);
	                return;
	            }

	        	BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

	        	com.itextpdf.text.Font fontTieuDe = new com.itextpdf.text.Font(baseFont, 15, Font.BOLD);
	        	com.itextpdf.text.Font fontHead = new com.itextpdf.text.Font(baseFont, 12, Font.BOLD);
	        	com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12, Font.PLAIN);
	        	
	            // Đặt tên file PDF và tạo đối tượng PdfWriter
	        	String maHD = hd.getMaHoaDon();
	            String fileName = "HoaDon_" + maHD + ".pdf";
	            PdfWriter.getInstance(document, new FileOutputStream(fileName));

	            // Mở document để bắt đầu viết
	            document.open();

	            // Thêm thông tin hóa đơn vào document
	            Paragraph paragraph2 = new Paragraph("CÔNG TY CP SÁCH GIÁO DỤC TẠI TP. HCM", font);
	            Paragraph paragraph3 = new Paragraph("HIỆU SÁCH TƯ NHÂN FUTUREZE", fontHead);
	            Paragraph paragraph4 = new Paragraph("Nhóm 14", font);
	            Paragraph paragraph5 = new Paragraph("HÓA ĐƠN THANH TOÁN", fontTieuDe);

	            // Đặt căn giữa cho đối tượng Paragraph
	            paragraph3.setAlignment(Element.ALIGN_CENTER);
	            paragraph2.setAlignment(Element.ALIGN_CENTER);
	            paragraph4.setAlignment(Element.ALIGN_CENTER);
	            paragraph5.setAlignment(Element.ALIGN_CENTER);

	            // Thêm đối tượng Paragraph vào tài liệu
	            document.add(paragraph2);
	            document.add(paragraph3);
	            document.add(paragraph4);
	            document.add(paragraph5);
	            document.add(new Paragraph("\n"));
	            
	            document.add(new Paragraph("Mã hóa đơn: " + maHD, font));
	            document.add(new Paragraph("Ngày Thanh Toán: " + ngayThanhToanFormatted, font));
	            document.add(new Paragraph("Thu Ngân: " + tenNhanVien, font));
	            document.add(new Paragraph("\n"));
	            // Tạo bảng cho sản phẩm trong hóa đơn
	            PdfPTable table = new PdfPTable(4);
	            table.setWidthPercentage(100);

	            // Thêm font cho header của bảng
	            PdfPCell cellHeadTenSP = new PdfPCell(new Paragraph("Tên Sản Phẩm", fontHead));
	            PdfPCell cellHeadSoLuong = new PdfPCell(new Paragraph("Số Lượng", fontHead));
	            PdfPCell cellHeadGia = new PdfPCell(new Paragraph("Giá", fontHead));
	            PdfPCell cellHeadThanhTien = new PdfPCell(new Paragraph("Thành tiền", fontHead));

	            // Thêm header vào bảng
	            table.addCell(cellHeadTenSP);
	            table.addCell(cellHeadGia);
	            table.addCell(cellHeadSoLuong);
	            table.addCell(cellHeadThanhTien);

	            double tongBanChuaVAT = 0;
	    	    int tongSoMua = 0;
	            for(ChiTietHoaDon ct : dao_CTHD.getDSTheoMaHD(maHD)) {
	            	//Thêm tên sản phẩm
	            	PdfPCell cellSP = new PdfPCell(new Paragraph(ct.getSanPham().getTenSanPham(), font));
	            	int soLuongMua = ct.getSoLuong();
	            	tongSoMua += soLuongMua;
	            	PdfPCell cellSL = new PdfPCell(new Paragraph(soLuongMua + "", font));
	            	double giaBan = ct.getSanPham().getGiaBan();
	            	tongBanChuaVAT += soLuongMua * giaBan;
	            	PdfPCell cellGia = new PdfPCell(new Paragraph(giaBan + "", font));
	            	double thanhTien = soLuongMua * giaBan;
	            	PdfPCell cellThanhTien = new PdfPCell(new Paragraph(thanhTien + "", font));
	            	
	            	  cellSP.setBorder(PdfPCell.NO_BORDER);
	                  cellSL.setBorder(PdfPCell.NO_BORDER);
	                  cellGia.setBorder(PdfPCell.NO_BORDER);
	                  cellThanhTien.setBorder(PdfPCell.NO_BORDER);

	                  table.addCell(cellSP);
	                  table.addCell(cellSL);
	                  table.addCell(cellGia);
	                  table.addCell(cellThanhTien);
	            }

	            
	    	    tableTien = new PdfPTable(3);
	            tableTien.setWidthPercentage(100);
	            
	            PdfPCell kt = new PdfPCell(new Paragraph("", fontHead));;
	    	    kt.setBorder(Rectangle.OUT_BOTTOM);
	            
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            
	            Locale localeVN = new Locale("vi", "VN");
	            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);
	            String strTongTien = currencyFormat.format(tongBanChuaVAT);
	            
	            PdfPCell tongTien = new PdfPCell(new Paragraph("Tổng tiền", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienValue = new PdfPCell(new Paragraph(strTongTien, font));

	            double dbtongTien = hd.getTongTien();
	            double dbtongTienCK = tongBanChuaVAT - dbtongTien;
	            String strTongTienTT = currencyFormat.format(dbtongTien);
	            String strTongTienCK = currencyFormat.format(dbtongTienCK);
	            PdfPCell tongTienCK = new PdfPCell(new Paragraph("Tổng tiền chiết khấu", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienCKValue = new PdfPCell(new Paragraph(strTongTienCK, font));
	            
	            PdfPCell tongTienKT = new PdfPCell(new Paragraph("Tổng số thanh toán", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienKTValue = new PdfPCell(new Paragraph(strTongTienTT, font));
	            
	            tongTien.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tongTienCK.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienCKValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tongTienKT.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienKTValue.setBorder(PdfPCell.NO_BORDER);

	            tableTien.addCell(tongTien);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienValue);
	            
	            tableTien.addCell(tongTienCK);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienCKValue);
	            
	            tableTien.addCell(tongTienKT);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienKTValue);
	            
	            tableTienKhach = new PdfPTable(3);
	            tableTienKhach.setWidthPercentage(100);
	            
	            double dbtienKD = hd.getTienNhan();
	            String strTienKD = currencyFormat.format(dbtienKD);
	            
	            PdfPCell tienKD = new PdfPCell(new Paragraph("Tiền khách đưa", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tienKDValue = new PdfPCell(new Paragraph(strTienKD, font));
	            
	            double dbtienThua = dbtienKD - dbtongTien;
	            String strTienThua = currencyFormat.format(dbtienThua);
	            PdfPCell tienThua = new PdfPCell(new Paragraph("Tiền trả lại", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tienThuaValue = new PdfPCell(new Paragraph(strTienThua, font));
	            
	            tienKD.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tienKDValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tienThua.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tienThuaValue.setBorder(PdfPCell.NO_BORDER);
	            
	            tableTienKhach.addCell(tienKD);
	            tableTienKhach.addCell(kt);
	            tableTienKhach.addCell(tienKDValue);
	            
	            tableTienKhach.addCell(tienThua);
	            tableTienKhach.addCell(kt);
	            tableTienKhach.addCell(tienThuaValue);
	            
	            kt.setBorder(Rectangle.OUT_BOTTOM);
	            
	            tableTienKhach.addCell(kt);
	            tableTienKhach.addCell(kt);
	            tableTienKhach.addCell(kt);
	            
	            // Thêm bảng vào document
	            document.add(table);
	            document.add(new Paragraph("\n"));
	            document.add(tableTien);
	            document.add(new Paragraph("\n"));
	            document.add(tableTienKhach);
	            document.add(new Paragraph("\n"));
	            //
	            Paragraph prTH = new Paragraph("Khách hàng đã mua chỉ đổi trả lại trong vòng 3 ngày", font);
	            Paragraph prTHtt = new Paragraph("(có kèm theo hóa đơn)", font);
	            Paragraph prCamOn = new Paragraph("XIN CẢM ƠN QUÝ KHÁCH & HẸN GẶP LẠI", font);

	            // Đặt căn giữa cho đối tượng Paragraph
	            prTH.setAlignment(Element.ALIGN_CENTER);
	            prTHtt.setAlignment(Element.ALIGN_CENTER);
	            prCamOn.setAlignment(Element.ALIGN_CENTER);

	            // Thêm đối tượng Paragraph vào tài liệu
	            document.add(prTH);
	            document.add(prTHtt);
	            document.add(prCamOn);
	            
	            // Đóng document
	            document.close();

	            // Hiển thị thông báo thành công
	            JOptionPane.showMessageDialog(this, "Hóa đơn đã được xuất thành công.");

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất hóa đơn.");
	        }
	    }
}
