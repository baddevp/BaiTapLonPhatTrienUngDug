package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import dao.DAO_ChucVu;
import dao.DAO_NhanVien;
import entity.ChucVu;
import entity.NhanVien;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.Insets;

public class GuiTrangChu extends JFrame implements ActionListener {

	DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private JPanel contentPane;
	private JMenuItem mniTimKiemHDTH;
	JTextField txtTenTK;
	private JMenuItem mniKhachHang;
	private JTabbedPane tabNoiDung;
	private JMenuItem mniLoaiVPP;
	private JPanel pnlTrangChu;
	private JMenuBar mnbChucNang;
	private JMenu mnTrangChu;
	private JMenu mnQuanLy;
	private JMenuItem mniSanPham;
	private JMenu mnLoaiSP;
	private JMenuItem mniLoaiSach;
	private JMenuItem mniNhanVien;
	private JMenuItem mniChucVu;
	private JMenuItem mniNSX;
	private JMenuItem mniMauSac;
	private JMenu mnXuLy;
	private JMenuItem mniBanHang;
	private JMenuItem mniTraHang;
	private JMenuItem mniInHoaDon;
	private JMenu mnTimKiem;
	private JMenuItem mniTimKiemSach;
	private JMenuItem mniTimKiemVPP;
	private JMenuItem mniTimKiemTK;
	private JMenuItem mniTimKiemNV;
	private JMenuItem mniTimKiemKH;
	private JMenuItem mniTimKiemHD;
	private JMenu mnThongKe;
	private JMenuItem mniThongKeNV;
	private JMenuItem mniThongKeDT;
	private JMenuItem mniThongKeKH;
	private JMenuItem mniBaoCaoThuChi;
	private JMenu mnHoTro;
	private JPanel pnlMenu;
	private JSeparator separator;
	private JSeparator separator_19;
	private JSeparator separator_20;
	private GuiBanHang guiBanHang;
	private DAO_NhanVien nhanvien_dao;
	private JMenu mnTaiKhoan;
	private JMenuItem mniThongTinTaiKhoan;
	private JMenuItem mniDangXuat;
	private DAO_ChucVu chucvu_dao;
	private boolean chucNangQL = false, chucNangTN = false;

	static JTextField txtusername;
	static JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTrangChu frame = new GuiTrangChu(txtusername, txtpassword);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiTrangChu(JTextField txtusername, JPasswordField txtpassword) {
		this.txtusername = txtusername;
		this.txtpassword = txtpassword;
		setBackground(new Color(255, 204, 102));
		setTitle("FutureZe - Phầm mềm quản lý nhà sách");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setSize(1930, 1030);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		mnbChucNang = new JMenuBar();
		mnbChucNang.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mnbChucNang.setBorder(null);
		mnbChucNang.setBackground(new Color(255, 255, 255));
		mnbChucNang.setBounds(0, 0, 1914, 74);

		mnTrangChu = new JMenu("Trang Chủ");
		mnTrangChu.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnTrangChu.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/TrangChu.png")));
		mnTrangChu.setFont(new Font("Dialog", Font.BOLD, 18));

		mnQuanLy = new JMenu("Quản lý");
		mnQuanLy.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnQuanLy.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/QuanLy.png")));
		mnQuanLy.setFont(new Font("Dialog", Font.BOLD, 18));

		mniKhachHang = new JMenuItem("Khách hàng");
		mniKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniKhachHang);

		JSeparator separator_2 = new JSeparator();
		mnQuanLy.add(separator_2);

		mniSanPham = new JMenuItem("Sản phẩm");
		mniSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniSanPham);

		mnLoaiSP = new JMenu("Loại Sản Phẩm");
		mnLoaiSP.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mnLoaiSP);

		mniLoaiSach = new JMenuItem("Loại sách");
		mniLoaiSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnLoaiSP.add(mniLoaiSach);

		JSeparator separator_17 = new JSeparator();
		mnLoaiSP.add(separator_17);

		mniLoaiVPP = new JMenuItem("Loại Văn Phòng Phẩm");
		mniLoaiVPP.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnLoaiSP.add(mniLoaiVPP);

		JSeparator separator_1 = new JSeparator();
		mnQuanLy.add(separator_1);

		mniNhanVien = new JMenuItem("Nhân viên");
		mniNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniNhanVien);

		mniChucVu = new JMenuItem("Chức vụ");
		mniChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniChucVu);

		JSeparator separator_3 = new JSeparator();
		mnQuanLy.add(separator_3);

		mniNSX = new JMenuItem("Nhà sản xuất");
		mniNSX.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniNSX);

		mniMauSac = new JMenuItem("Màu sắc");
		mniMauSac.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnQuanLy.add(mniMauSac);

		mnXuLy = new JMenu("Xử lý");
		mnXuLy.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnXuLy.setHorizontalAlignment(SwingConstants.CENTER);
		mnXuLy.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/BanHang.png")));
		mnXuLy.setFont(new Font("Dialog", Font.BOLD, 18));

		mniBanHang = new JMenuItem("Bán hàng");
		mniBanHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnXuLy.add(mniBanHang);

		JSeparator separator_7 = new JSeparator();
		mnXuLy.add(separator_7);

		mniTraHang = new JMenuItem("Trả hàng");
		mniTraHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnXuLy.add(mniTraHang);

		JSeparator separator_8 = new JSeparator();
		mnXuLy.add(separator_8);

		mniInHoaDon = new JMenuItem("In lại hóa đơn");
		mniInHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnXuLy.add(mniInHoaDon);

		mnTimKiem = new JMenu("Tìm kiếm");
		mnTimKiem.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnTimKiem.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/TimKiem.png")));
		mnTimKiem.setFont(new Font("Dialog", Font.BOLD, 18));

		mniTimKiemSach = new JMenuItem("Sách");
		mniTimKiemSach.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemSach);

		JSeparator separator_10 = new JSeparator();
		mnTimKiem.add(separator_10);

		mniTimKiemVPP = new JMenuItem("Văn phòng phẩm");
		mniTimKiemVPP.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemVPP);

		JSeparator separator_5 = new JSeparator();
		mnTimKiem.add(separator_5);

		mniTimKiemNV = new JMenuItem("Nhân viên");
		mniTimKiemNV.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemNV);

		JSeparator separator_11 = new JSeparator();
		mnTimKiem.add(separator_11);

		mniTimKiemKH = new JMenuItem("Khách hàng");
		mniTimKiemKH.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemKH);

		JSeparator separator_12 = new JSeparator();
		mnTimKiem.add(separator_12);

		mniTimKiemHD = new JMenuItem("Hóa đơn\r\n");
		mniTimKiemHD.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemHD);

		JSeparator separator_13 = new JSeparator();
		mnTimKiem.add(separator_13);

		mniTimKiemHDTH = new JMenuItem("Hóa đơn\r\n trả hàng");
		mniTimKiemHDTH.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTimKiem.add(mniTimKiemHDTH);

		mnThongKe = new JMenu("Thống kê");
		mnThongKe.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnThongKe.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/ThongKe.png")));
		mnThongKe.setFont(new Font("Dialog", Font.BOLD, 18));

		mniThongKeNV = new JMenuItem("Nhân viên");
		mniThongKeNV.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		mniThongKeDT = new JMenuItem("Doanh thu");
		mniThongKeDT.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		mniThongKeKH = new JMenuItem("Khách hàng");
		mniThongKeKH.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		mniBaoCaoThuChi = new JMenuItem("Báo cáo thu chi trong ngày");
		mniBaoCaoThuChi.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		mnHoTro = new JMenu("Hỗ trợ");
		mnHoTro.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnHoTro.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/TroGiup.png")));
		mnHoTro.setFont(new Font("Dialog", Font.BOLD, 18));

		kiemTraChucVu(txtusername.getText().trim());
		System.out.println(chucNangQL);
		System.out.println(chucNangTN);
		if (chucNangTN) {
			mnbChucNang.add(mnTrangChu);
			mnbChucNang.add(mnXuLy);
			mnbChucNang.add(mnTimKiem);
			mnbChucNang.add(mnThongKe);
			mnThongKe.add(mniBaoCaoThuChi);

			if (chucNangQL) {
				mnbChucNang.add(mnQuanLy);
				mnThongKe.add(mniThongKeDT);
				mnThongKe.add(mniThongKeKH);
				mnThongKe.add(mniThongKeNV);
				mnThongKe.remove(mniBaoCaoThuChi);
			}

		}
		mnbChucNang.add(mnHoTro);
		pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(255, 255, 255));
		pnlMenu.setBounds(0, 0, 1914, 75);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);
		pnlMenu.add(mnbChucNang);

		JSeparator separator_18 = new JSeparator();
		separator_18.setBorder(new EmptyBorder(20, 20, 20, 20));
		mnbChucNang.add(separator_18);

		separator = new JSeparator();
		separator.setBorder(new EmptyBorder(20, 0, 0, 0));
		mnbChucNang.add(separator);

		JPanel pnlThongTinTaiKhoan = new JPanel();
		mnbChucNang.add(pnlThongTinTaiKhoan);
		pnlThongTinTaiKhoan.setBackground(new Color(255, 255, 255));
		pnlThongTinTaiKhoan.setLayout(null);

		txtTenTK = new JTextField();
		txtTenTK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtTenTK.setHorizontalAlignment(JTextField.RIGHT);
		txtTenTK.setEditable(false);
		txtTenTK.setBounds(-91, 19, 383, 40);
		txtTenTK.setBorder(null);
		pnlThongTinTaiKhoan.add(txtTenTK);
		// txtTenTK.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		txtTenTK.setBackground(new Color(255, 255, 255));
		txtTenTK.setColumns(10);

		mnTaiKhoan = new JMenu("");
		mnTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTaiKhoan.setMargin(new Insets(2, 2, 2, 30));
		mnTaiKhoan.setHorizontalAlignment(SwingConstants.TRAILING);
		mnbChucNang.add(mnTaiKhoan);
		mnTaiKhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
		mnTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnTaiKhoan.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/TaiKhoan.png")));

		mniThongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
		mniThongTinTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTaiKhoan.add(mniThongTinTaiKhoan);

		separator_19 = new JSeparator();
		mnTaiKhoan.add(separator_19);

		separator_20 = new JSeparator();
		mnTaiKhoan.add(separator_20);

		mniDangXuat = new JMenuItem("Đăng xuất");
		mniDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnTaiKhoan.add(mniDangXuat);

		tabNoiDung = new JTabbedPane();
		tabNoiDung.setAutoscrolls(true);
		tabNoiDung.setTabPlacement(JTabbedPane.BOTTOM);
		tabNoiDung.setBounds(0, 74, 1930, 1055);
		contentPane.add(tabNoiDung);

		pnlTrangChu = new JPanel();
		tabNoiDung.add(pnlTrangChu, BorderLayout.CENTER);
		pnlTrangChu.setLayout(null);

		JLabel lblTieuDe = new JLabel("");
		lblTieuDe.setIcon(new ImageIcon(GuiTrangChu.class.getResource("/image/thumnail.png")));
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTieuDe.setBounds(0, 0, 1915, 914);
		pnlTrangChu.add(lblTieuDe);

		// Xử lý sự kiện
		mnTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Object o = e.getSource();
				if (o.equals(mnTrangChu)) {
					tabNoiDung.remove(tabNoiDung.getSelectedComponent());
					tabNoiDung.add(pnlTrangChu);
				}
			}
		});
		nhanvien_dao = new DAO_NhanVien();
		chucvu_dao = new DAO_ChucVu();
		// Quan ly
		mniSanPham.addActionListener(this);
		mniKhachHang.addActionListener(this);
		mniLoaiSach.addActionListener(this);
		mniLoaiVPP.addActionListener(this);
		mniNhanVien.addActionListener(this);
		mniChucVu.addActionListener(this);
		mniNSX.addActionListener(this);
		mniMauSac.addActionListener(this);
		mniDangXuat.addActionListener(this);
		// Xu ly
		mniBanHang.addActionListener(this);
		mniTraHang.addActionListener(this);
		mniInHoaDon.addActionListener(this);
		// Tim kiem
		mniTimKiemSach.addActionListener(this);
		mniTimKiemHD.addActionListener(this);
		mniTimKiemKH.addActionListener(this);
		mniTimKiemNV.addActionListener(this);
		mniTimKiemHDTH.addActionListener(this);
		mniTimKiemVPP.addActionListener(this);
		mniThongTinTaiKhoan.addActionListener(this);
		// Thống kê
		mniThongKeDT.addActionListener(this);
		mniThongKeKH.addActionListener(this);
		mniThongKeNV.addActionListener(this);
		mniBaoCaoThuChi.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		// Chức năng quản lý
		if (o.equals(mniKhachHang)) {
			GuiQuanLyKhachHang guiQuanLyKhachHang = new GuiQuanLyKhachHang();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyKhachHang.contentPane);
		} else if (o.equals(mniSanPham)) {
			GuiQuanLySanPham guiQuanLySanPham = new GuiQuanLySanPham();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLySanPham.contentPane);
		} else if (o.equals(mniLoaiSach)) {
			GuiQuanLyLoaiSach guiQuanLyLoaiSach = new GuiQuanLyLoaiSach();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyLoaiSach.contentPane);
		} else if (o.equals(mniLoaiVPP)) {
			GuiQuanLyLoaiVanPhongPham guiQuanLyLoaiVPP = new GuiQuanLyLoaiVanPhongPham();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyLoaiVPP.contentPane);
		} else if (o.equals(mniNhanVien)) {
			GuiQuanLyNhanVien guiQuanLyNhanVien = new GuiQuanLyNhanVien();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyNhanVien.contentPane);
		} else if (o.equals(mniChucVu)) {
			GuiQuanLyChucVu guiQuanLyChucVu = new GuiQuanLyChucVu();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyChucVu.contentPane);
		} else if (o.equals(mniNSX)) {
			GuiQuanLyNSX guiQuanLyNSX = new GuiQuanLyNSX();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyNSX.contentPane);
		} else if (o.equals(mniMauSac)) {
			GuiQuanLyMauSac guiQuanLyMauSac = new GuiQuanLyMauSac();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiQuanLyMauSac.contentPane);
		}
		// Chức năng tìm kiếm
		else if (o.equals(mniTimKiemHDTH)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiTimKiemHoaDonTraHang guiHoadDonTraHang = new GuiTimKiemHoaDonTraHang(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(GuiTimKiemHoaDonTraHang.contentPane);
		} else if (o.equals(mniTimKiemHD)) {
			GuiTimKiemHoaDon guiTimKiemHoaDon = new GuiTimKiemHoaDon();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemHoaDon.contentPane);
		} else if (o.equals(mniTimKiemNV)) {
			GuiTimKiemNhanVien guiTimKiemNhanVien = new GuiTimKiemNhanVien();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemNhanVien.contentPane);
		} else if (o.equals(mniTimKiemKH)) {
			GuiTimKiemKhachHang guiTimKiemKhachHang = new GuiTimKiemKhachHang();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemKhachHang.contentPane);
		} else if (o.equals(mniTimKiemSach)) {
			GuiTimKiemSach guiTimKiemSach = new GuiTimKiemSach();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemSach.contentPane);
		} else if (o.equals(mniTimKiemVPP)) {
			GuiTimKiemVanPhongPham guiTimKiemVanPhongPham = new GuiTimKiemVanPhongPham();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemVanPhongPham.contentPane);
		} else if (o.equals(mniTimKiemTK)) {
			GuiTimKiemTaiKhoan guiTimKiemTaiKhoan = new GuiTimKiemTaiKhoan();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiTimKiemTaiKhoan.contentPane);
		}
		// Chức năng xử lý
		else if (o.equals(mniBanHang)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiBanHang guiBanHang = new GuiBanHang(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(guiBanHang.contentPane);

		} else if (o.equals(mniTraHang)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiTraHang traHang = new GuiTraHang(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(traHang.contentPane);

		} else if (o.equals(mniInHoaDon)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiInLaiHoaDon traHang = new GuiInLaiHoaDon(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(traHang.contentPane);

		} else if (o.equals(mniDangXuat)) {
			this.setVisible(false);
			GuiDangNhap guidangnhap = new GuiDangNhap();
			guidangnhap.setVisible(true);

		} else if (o.equals(mniThongTinTaiKhoan)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiThongTinNhanVien thongTinNV = new GuiThongTinNhanVien(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(thongTinNV.contentPane);

		}

		// Chức năng thống kê
		else if (o.equals(mniThongKeDT)) {
			GuiThongKeDoanhThu frmThongKeDoanhThu = new GuiThongKeDoanhThu();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(frmThongKeDoanhThu.pnlBorder);
		} else if (o.equals(mniThongKeKH)) {
			GuiThongKeKhachHang frmThongKeKH = new GuiThongKeKhachHang();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(frmThongKeKH.pnlBorder);
		} else if (o.equals(mniThongKeNV)) {
			GuiThongKeNhanVien frmTKNhanVien = new GuiThongKeNhanVien();
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(frmTKNhanVien.pnlBorder);
		} else if (o.equals(mniBaoCaoThuChi)) {
			DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
			NhanVien nv = dao_NhanVien.getNhanVienTheoMa2(txtusername.getText());
			GuiBaoCaoHangNgay traHang = new GuiBaoCaoHangNgay(nv);
			tabNoiDung.remove(tabNoiDung.getSelectedComponent());
			tabNoiDung.add(traHang.contentPane);

		}
		// Chức năng hỗ trợ

	}

	public void kiemTraChucVu(String maNhanVien) {
		NhanVien nhanVien = dao_NhanVien.getNhanVienTheoMa2(maNhanVien.trim());
		if (nhanVien.getChucVu().getMaChucVu().equals("CV001")) {
			chucNangQL = true;
			chucNangTN = true;
			return;
		}
		if (nhanVien.getChucVu().getMaChucVu().equals("CV002")) {
			chucNangTN = true;
			return;
		}

	}

}
