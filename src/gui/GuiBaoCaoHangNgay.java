package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

public class GuiBaoCaoHangNgay extends JFrame implements ActionListener, MouseListener {

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
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private DefaultTableModel modelHD;
	private JTable tblHD;
	private JTextField txtTongSP;
	private JTextField txtTienHoanTra;


	static NhanVien nv;
	private JLabel lblTenCuaHang;
	private JLabel lblNewLabel;
	private JDateChooser dtmBaoCao;
	private JLabel lblNV;
	private JLabel lblN;
	private JLabel lblTenNhanVien;
	private JLabel lblNgay;
	private JLabel lblTT;
	private JLabel lblTC;
	private JLabel lblToTi;
	private JLabel lblSoLuongDon;
	private JLabel lblTongThu;
	private JLabel lblTongChi;
	private JLabel lblTongTien;
	private JPanel pnlBaoCao;
	private JButton btnXem;
	private JButton btnXuatBaoCao;
	private PdfPTable tableTien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBaoCaoHangNgay frame = new GuiBaoCaoHangNgay(nv);
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
	public GuiBaoCaoHangNgay(NhanVien nv) {
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
		// Form chon hoa don tra hang
		pnlBaoCao = new JPanel();
		pnlBaoCao.setBackground(new Color(255, 255, 255));

		pnlBaoCao.setBounds(10, 10, 1900, 958);
		contentPane.add(pnlBaoCao);

		modelHD = new DefaultTableModel();
		modelHD.addColumn("STT");
		modelHD.addColumn("Mã hóa đơn");
		modelHD.addColumn("Ngày thu");
		modelHD.addColumn("Thu");
		modelHD.addColumn("Mã trả hàng");
		modelHD.addColumn("Ngày chi");
		modelHD.addColumn("Chi");
		modelHD.addColumn("Tổng tiền");
		tblHD = new JTable(modelHD);
		tblHD.setBackground(new Color(153, 204, 255));
		tblHD.setFont(font2);
		tblHD.setRowHeight(35);
		JTableHeader tbHeaderHD = tblHD.getTableHeader();
		tbHeaderHD.setFont(font2);
		tbHeaderHD.setBackground(new Color(51, 204, 204));
		pnlBaoCao.setLayout(null);
		JScrollPane jScrollPane = new JScrollPane(tblHD);
		jScrollPane.setBounds(30, 137, 1850, 710);
		pnlBaoCao.add(jScrollPane);
		
		JLabel lblTieuDe = new JLabel("BÁO CÁO THU CHI HẰNG NGÀY");
		lblTieuDe.setBounds(620, 10, 650, 50);
		lblTieuDe.setForeground(new Color(51, 204, 204));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 34));
		pnlBaoCao.add(lblTieuDe);
		
		lblTenCuaHang = new JLabel("NHÀ SÁCH TƯ NHÂN FUTUREZE");
		lblTenCuaHang.setBounds(675, 49, 532, 30);
		lblTenCuaHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenCuaHang.setForeground(new Color(51, 204, 204));
		lblTenCuaHang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlBaoCao.add(lblTenCuaHang);
		
		lblNewLabel = new JLabel("Chọn ngày báo cáo:");
		lblNewLabel.setBounds(10, 11, 166, 30);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pnlBaoCao.add(lblNewLabel);
		
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(currentDate);
		dtmBaoCao = new JDateChooser(currentDate);
		dtmBaoCao.setBounds(186, 10, 258, 35);
		pnlBaoCao.add(dtmBaoCao);
		
		lblNV = new JLabel("Nhân viên :");
		lblNV.setBounds(685, 79, 107, 30);
		lblNV.setForeground(new Color(0, 0, 0));
		lblNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlBaoCao.add(lblNV);
		
		lblN = new JLabel("Ngày :");
		lblN.setBounds(724, 106, 107, 30);
		lblN.setForeground(Color.BLACK);
		lblN.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlBaoCao.add(lblN);
		
		lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setBounds(802, 79, 405, 30);
		lblTenNhanVien.setForeground(Color.BLACK);
		lblTenNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlBaoCao.add(lblTenNhanVien);
		
		lblNgay = new JLabel("");
		lblNgay.setBounds(802, 106, 405, 30);
		lblNgay.setForeground(Color.BLACK);
		lblNgay.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlBaoCao.add(lblNgay);
		
		JLabel lblSLD = new JLabel("Số lượng đơn :");
		lblSLD.setBounds(100, 870, 180, 50);
		lblSLD.setForeground(Color.BLACK);
		lblSLD.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblSLD);
		
		lblTT = new JLabel("Tổng thu :");
		lblTT.setBounds(552, 870, 129, 50);
		lblTT.setForeground(Color.BLACK);
		lblTT.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblTT);
		
		lblTC = new JLabel("Tổng chi :");
		lblTC.setBounds(986, 870, 129, 50);
		lblTC.setForeground(Color.BLACK);
		lblTC.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblTC);
		
		lblToTi = new JLabel("Tổng tiền :");
		lblToTi.setBounds(1472, 870, 129, 50);
		lblToTi.setForeground(Color.BLACK);
		lblToTi.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblToTi);
		
		lblSoLuongDon = new JLabel("");
		lblSoLuongDon.setBounds(274, 870, 180, 50);
		lblSoLuongDon.setForeground(Color.BLACK);
		lblSoLuongDon.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblSoLuongDon);
		
		lblTongThu = new JLabel("");
		lblTongThu.setBounds(691, 870, 285, 50);
		lblTongThu.setForeground(Color.BLACK);
		lblTongThu.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblTongThu);
		
		lblTongChi = new JLabel("");
		lblTongChi.setBounds(1125, 870, 337, 50);
		lblTongChi.setForeground(Color.BLACK);
		lblTongChi.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblTongChi);
		
		lblTongTien = new JLabel("");
		lblTongTien.setBounds(1629, 870, 250, 50);
		lblTongTien.setForeground(new Color(255, 0, 0));
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 26));
		pnlBaoCao.add(lblTongTien);
		
		btnXem = new JButton("Xem báo cáo");
		btnXem.setBackground(new Color(51, 204, 204));
		btnXem.setForeground(new Color(255, 255, 255));
		btnXem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXem.setBounds(459, 10, 171, 35);
		pnlBaoCao.add(btnXem);
		btnXem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setNut(true);
			}

			
		});
		
		btnXuatBaoCao = new JButton("Xuất báo cáo");
		btnXuatBaoCao.setForeground(Color.WHITE);
		btnXuatBaoCao.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXuatBaoCao.setBackground(new Color(51, 204, 204));
		btnXuatBaoCao.setBounds(1709, 10, 171, 35);
		pnlBaoCao.add(btnXuatBaoCao);
		btnXem.setVisible(false);
		
		//bắt sự kiện
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnXem.addActionListener(this);
		btnXuatBaoCao.addActionListener(this);
		setNut(false);
		
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXem)) {
		modelHD.setRowCount(0);
		Date date =	dtmBaoCao.getDate();
		LocalDateTime ngayBC = convertToLocalDateTime(date);
		docBaoCaoThuChiTheoNgay(ngayBC);
		tongBaoCao();

		}if(o.equals(btnXuatBaoCao)) {
			if(tblHD.getRowCount() == 0) {
				int input = JOptionPane.showConfirmDialog(this, "Hiện tại chưa có dữ liệu thu chi trong ngày. Bạn có chắc chắn muốn xuất báo cáo không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(input == JOptionPane.YES_OPTION) {
					boolean f = xuatBaoCao();
					if(f) {
						JOptionPane.showMessageDialog(this, "Báo cáo đã được xuất thành công.");
					}else {
						JOptionPane.showMessageDialog(this, "Có lỗi không thể xuất báo cáo.");
					}
				}else {
					boolean f = xuatBaoCao();
					if(f) {
						JOptionPane.showMessageDialog(this, "Báo cáo đã được xuất thành công.");
					}else {
						JOptionPane.showMessageDialog(this, "Có lỗi không thể xuất báo cáo.");
					}
				}
			}
			setNut(false);
		}
	}
	//Doc du lieu theo ngay
	public void docBaoCaoThuChiTheoNgay(LocalDateTime thGian) {
		lblTenNhanVien.setText(nv.getTenNV());
		lblNgay.setText(chuyenDateSoSanh(thGian));
		int i = 0;
		for (HoaDon hd : dao_HoaDon.getHDNhanVienBanDuocTheoNgay(thGian, nv)) {
			i++;
			String maTH = dao_HoaDonHT.getMaTHTheoMaHD(hd.getMaHoaDon());
			// Hóa đơn chưa trả
			if (maTH == null) {
				double tongTien = hd.getTongTien();
				modelHD.addRow(new Object[] { i, hd.getMaHoaDon(), chuyenDateSoSanh(hd.getNgayLapHoaDon()) ,tongTien,"","","",tongTien});
			}
			//Hóa đơn có trả
			else {
				HoaDonHoanTra ht = dao_HoaDonHT.getHDHTTheoMaHDTH(maTH);
				double tongThu = hd.getTongTien();
				double tongTra = ht.getTongHoanTra();
				modelHD.addRow(new Object[] { i, hd.getMaHoaDon(), chuyenDateSoSanh(hd.getNgayLapHoaDon()), tongThu,ht.getMaYeuCauTraHang(), ht.getLyDoTraHang(), tongTra, tongThu - tongTra});
				
			}
			
			
		}
	}
	//Tính tổng
	public void tongBaoCao() {
		int tongHD = 0;
		double tongTienThu = 0;
		double tongTienChi = 0;
		double tongTatCa = 0;
		
		int rowCount =  modelHD.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			tongHD++;
			tongTienThu +=   (double) tblHD.getValueAt(i, 3);
			tongTienChi += tinhTienChi(modelHD, i);
			tongTatCa += (double) tblHD.getValueAt(i, 7);
		}
		lblSoLuongDon.setText(String.valueOf(tongHD));
		lblTongThu.setText(df.format(tongTienThu));
		lblTongChi.setText(df.format(tongTienChi)); 
		lblTongTien.setText(df.format(tongTatCa));
	}
	//Hàm chuyển date --> localdatetime
	 public static LocalDateTime convertToLocalDateTime(Date date) {
	        Instant instant = date.toInstant();
	        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	    }
	// hàm chuyển đổi localdatetime để so sánh
		public static String chuyenDateSoSanh(LocalDateTime chuoiJava) {
			if (chuoiJava == null)
				return null;
			String str = chuoiJava.toString();
			return str.substring(0, 10);
		}
	//Kiểm tra dòng
		public double tinhTienChi(DefaultTableModel model,  int i) {
			try {
				double t = (double) model.getValueAt(i, 6);
				if(t > 0) {
					return t;
				}	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println();
			}
		
		
			return 0;
		}
	//Xuất báo cáo
		public boolean xuatBaoCao() {
	        // Lấy thông tin từ tblSPHD, tên nhân viên và ngày thanh toán
	        String tenNhanVien = nv.getTenNV();
	        Date ngayThanhToan = dtmBaoCao.getDate();

	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
	        String ngayThanhToanFormatted = sdf.format(ngayThanhToan);

	     

	        // Tạo đối tượng Document
	        Document document = new Document();
	        
	        try {
	        	String fontPath = "src/font/DejaVuSans.ttf";
	        	
	        	if (!Files.exists(Paths.get(fontPath))) {
	                System.err.println("Font file not found. Check the fontPath: " + fontPath);
	                return false;
	            }

	        	BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

	        	com.itextpdf.text.Font fontTieuDe = new com.itextpdf.text.Font(baseFont, 15, Font.BOLD);
	        	com.itextpdf.text.Font fontHead = new com.itextpdf.text.Font(baseFont, 12, Font.BOLD);
	        	com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12, Font.PLAIN);
	        	
	            // Đặt tên file PDF và tạo đối tượng PdfWriter
	        	String maNV = nv.getMaNV(); //NV13122023001
	        	String fileName = "BaoCaoThuChi_" + maNV + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+ ".pdf";
	            PdfWriter.getInstance(document, new FileOutputStream(fileName));

	            // Mở document để bắt đầu viết
	            document.open();

	            // Thêm thông tin hóa đơn vào document
	            Paragraph paragraph2 = new Paragraph("CÔNG TY CP SÁCH GIÁO DỤC TẠI TP. HCM", font);
	            Paragraph paragraph3 = new Paragraph("HIỆU SÁCH TƯ NHÂN FUTUREZE", fontHead);
	            Paragraph paragraph4 = new Paragraph("Nhóm 14", font);
	            Paragraph paragraph5 = new Paragraph("BÁO CÁO THU CHI HẰNG NGÀY", fontTieuDe);

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
	            
	            document.add(new Paragraph("Nhân viên : " + tenNhanVien, font));
	            document.add(new Paragraph("Ngày : " + ngayThanhToanFormatted, font));
	            
	            document.add(new Paragraph("\n"));
	            // Tạo bảng cho sản phẩm trong hóa đơn
	            PdfPTable table = new PdfPTable(8);
	            table.setWidthPercentage(50);

	            // Thêm font cho header của bảng
	            PdfPCell cellHeadSTT = new PdfPCell(new Paragraph("STT", fontHead));
	            PdfPCell cellHeadMaHD = new PdfPCell(new Paragraph("Mã hóa đơn", fontHead));
	            PdfPCell cellHeadNgayThu = new PdfPCell(new Paragraph("Ngày thu", fontHead));
	            PdfPCell cellHeadThu = new PdfPCell(new Paragraph("Thu", fontHead));
	            PdfPCell cellHeadHDTra = new PdfPCell(new Paragraph("Mã trả hàng", fontHead));
	            PdfPCell cellHeadNgayChi = new PdfPCell(new Paragraph("Ngày chi", fontHead));
	            PdfPCell cellHeadChi = new PdfPCell(new Paragraph("Chi", fontHead));
	            PdfPCell cellHeadTongTien = new PdfPCell(new Paragraph("Thành tiền", fontHead));
	            

	            // Thêm header vào bảng
	            table.addCell(cellHeadSTT);
	            table.addCell(cellHeadMaHD);
	            table.addCell(cellHeadNgayThu);
	            table.addCell(cellHeadThu);
	            table.addCell(cellHeadHDTra);
	            table.addCell(cellHeadNgayChi);
	            table.addCell(cellHeadChi);
	            table.addCell(cellHeadTongTien);
	            
	            // Lấy thông tin sản phẩm từ tblSPHD và thêm vào bảng
	           
	    	    for (int i = 0; i < tblHD.getRowCount(); i++) {
	                PdfPCell cellSTT = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 0).toString(), font));
	                PdfPCell cellMaHD = new PdfPCell(new Paragraph(tblHD.getValueAt(i,1).toString(), font));
	                PdfPCell cellNgayThu = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 2).toString(), font));
	                
	                PdfPCell cellThu = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 3).toString(), font));
	                PdfPCell cellMaTH = new PdfPCell(new Paragraph(tblHD.getValueAt(i,4).toString(), font));
	                PdfPCell cellNgayChi = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 5).toString(), font));
	          
	                PdfPCell cellChi = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 6).toString(), font));
	                PdfPCell cellTongTien = new PdfPCell(new Paragraph(tblHD.getValueAt(i, 7).toString(), font));
	              
	                table.addCell(cellSTT);
	                table.addCell(cellMaHD);
	                table.addCell(cellNgayThu);
	                table.addCell(cellThu);
	                table.addCell(cellMaTH);
	                table.addCell(cellNgayChi);
	                table.addCell(cellChi);
	                table.addCell(cellTongTien);
	                
	            }

	           
	    	    int soDon = Integer.parseInt(lblSoLuongDon.getText());
	    	    double thu = Double.parseDouble(lblTongThu.getText());
	    	    double chi = Double.parseDouble(lblTongChi.getText());
	    	    double tongTien = Double.parseDouble(lblTongTien.getText());
	    	    
	    	    tableTien = new PdfPTable(3);
	            tableTien.setWidthPercentage(100);
	            
	            PdfPCell kt = new PdfPCell(new Paragraph("", fontHead));;
	    	    kt.setBorder(Rectangle.OUT_BOTTOM);
	            
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            tableTien.addCell(kt);
	            
	            Locale localeVN = new Locale("vi", "VN");
	            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);
	            String strtongThu = currencyFormat.format(thu);
	            PdfPCell celTongThu = new PdfPCell(new Paragraph("Tổng thu :", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienCKValue = new PdfPCell(new Paragraph(strtongThu  + "", font));
	            
	            String strtongTien = currencyFormat.format(tongTien);
	            PdfPCell celTongTien = new PdfPCell(new Paragraph("Tổng tiền:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienValue = new PdfPCell(new Paragraph(strtongTien, font));

	            PdfPCell celSoDon = new PdfPCell(new Paragraph("Số lượng đơn:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongSoTraValue = new PdfPCell(new Paragraph(soDon + "", font));
	            
	            String strTongChi = currencyFormat.format(chi);
	            PdfPCell celTongChi = new PdfPCell(new Paragraph("Tổng chi:", fontHead));
	            kt = new PdfPCell(new Paragraph("", fontHead));
	            PdfPCell tongTienKTValue = new PdfPCell(new Paragraph(strTongChi, font));
	            
	       
	           
	            
	            celTongTien.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienValue.setBorder(PdfPCell.NO_BORDER);
	            
	            celTongThu.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienCKValue.setBorder(PdfPCell.NO_BORDER);
	            
	            celTongChi.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongTienKTValue.setBorder(PdfPCell.NO_BORDER);
	            
	            celSoDon.setBorder(PdfPCell.NO_BORDER);
	            kt.setBorder(PdfPCell.NO_BORDER);
	            tongSoTraValue.setBorder(PdfPCell.NO_BORDER);

	            tableTien.addCell(celSoDon);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongSoTraValue);
	            
	          
	            tableTien.addCell(celTongThu);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienCKValue);
	            
	            tableTien.addCell(celTongChi);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienKTValue);
	            
	            tableTien.addCell(celTongTien);
	            tableTien.addCell(kt);
	            tableTien.addCell(tongTienValue);
	            
	            
	           
	            
	            // Thêm bảng vào document
	            document.add(table);
	            document.add(new Paragraph("\n"));
	            document.add(tableTien);
	            Paragraph prTH = new Paragraph("\n", font);
	            Paragraph prTHtt = new Paragraph("\n", font);
	            Paragraph prCamOn = new Paragraph("NHÀ SÁCH TƯ NHÂN FUTUREZE", font);

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
	            
	            
	            openPdfFile(fileName);
	            return true;

	        } catch (Exception ex) {
	            System.out.println("Có lỗi xảy ra khi xuất báo cáo.");
	        }
	        
	        return false;
	    }
		private static void openPdfFile(String filePath) throws IOException {
	        File pdfFile = new File(filePath);

	        if (pdfFile.exists()) {
	            Desktop desktop = Desktop.getDesktop();
	            desktop.open(pdfFile);
	        } else {
	            System.out.println("The specified PDF file does not exist.");
	            
	        }
	    }
		private void setNut(boolean b) {
			// TODO Auto-generated method stub
			if(b) {
			btnXem.setVisible(false);
			btnXuatBaoCao.setVisible(true);
			}else {
				btnXem.setVisible(true);
				btnXuatBaoCao.setVisible(false);
			}
			
		}
}
