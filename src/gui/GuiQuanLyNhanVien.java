package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import javax.print.Doc;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DAO_ChucVu;
import dao.DAO_HinhAnh;
import dao.DAO_NhanVien;
import entity.ChucVu;
import entity.HinhAnh;
import entity.KhachHang;
import entity.MauSac;
import entity.NhanVien;
import net.sf.jasperreports.compilers.GroovyClassFilter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;

public class GuiQuanLyNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final String IMAGES_DIRECTORY = "src//image";
	public static JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtCCCD;
	private JTextField txtSDT;
	private JTextField txtTimKiem;
	private DefaultTableModel modelKH;
	private JTable tblKH;
	private JDateChooser dtmNgaySinh;
	private JDateChooser dtmNgayVaoLam;
	private JButton btnChonAnh;
	private JTextField txtDiaChi;
	private JButton btnDatLai;
	private JButton btnLuu;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;
	private JComboBox cboChucVu;
	private JComboBox cboGioiTinh;
	private JLabel lblShowAnh;
	private DAO_HinhAnh hinhanh_dao;
	private DAO_NhanVien nhanvien_dao;
	private DAO_ChucVu chucvu_dao;
	private Date date1;
	DAO_HinhAnh dao_hinhanh = new DAO_HinhAnh();
	DAO_ChucVu dao_chucVu = new DAO_ChucVu();
	HinhAnh anhCapNhat = dao_hinhanh.getHinhAnhTheoMa("NV00000000000"); // ảnh mặc định có mã là NV00000000000;
	private String changeImagePath;
	private String selectedImagePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiQuanLyNhanVien frame = new GuiQuanLyNhanVien();
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
	public GuiQuanLyNhanVien() {
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
		Font font3 = new Font("Times New Roman", Font.PLAIN, 18);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);

		// Tiêu đề
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBounds(10, 10, 1894, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);

		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));

		JPanel pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBackground(new Color(255, 255, 255));

		pnlThongTinKH.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		pnlThongTinKH.setBounds(10, 80, 1894, 280);
		contentPane.add(pnlThongTinKH);
		pnlThongTinKH.setLayout(null);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(50, 35, 163, 30);
		pnlThongTinKH.add(lblMaNV);
		lblMaNV.setFont(font2);

		JLabel lblTenNV = new JLabel("Tên nhân viên :");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblTenNV.setBounds(450, 35, 187, 29);
		pnlThongTinKH.add(lblTenNV);

		JLabel lblCCCD = new JLabel("CMT/ CCCD :");
		lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblCCCD.setBounds(850, 35, 163, 29);
		pnlThongTinKH.add(lblCCCD);

		JLabel lblSDT = new JLabel("Số điện thoại :");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblSDT.setBounds(450, 130, 163, 29);
		pnlThongTinKH.add(lblSDT);

		JLabel lblNgayLap = new JLabel("Ngày lập :");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNgayLap.setBounds(850, 130, 163, 29);
		pnlThongTinKH.add(lblNgayLap);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblGioiTinh.setBounds(50, 130, 102, 29);
		pnlThongTinKH.add(lblGioiTinh);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMaNV.setEditable(false);
		txtMaNV.setBounds(50, 80, 300, 35);
		pnlThongTinKH.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(450, 80, 300, 35);
		pnlThongTinKH.add(txtTenNV);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(850, 80, 300, 35);
		pnlThongTinKH.add(txtCCCD);

		dtmNgayVaoLam = new JDateChooser();
		dtmNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		dtmNgayVaoLam.setDate(currentDate);
		dtmNgayVaoLam.setBounds(850, 175, 300, 35);
		pnlThongTinKH.add(dtmNgayVaoLam);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtSDT.setColumns(10);
		txtSDT.setBounds(450, 170, 300, 35);
		pnlThongTinKH.add(txtSDT);

		dtmNgaySinh = new JDateChooser();
		dtmNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		dtmNgaySinh.setBounds(1250, 80, 300, 35);
		pnlThongTinKH.add(dtmNgaySinh);

		JLabel lblNgySinh = new JLabel("Ngày sinh :");
		lblNgySinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNgySinh.setBounds(1250, 35, 163, 30);
		pnlThongTinKH.add(lblNgySinh);

		JPanel pnlAnhNhanVien = new JPanel();
		pnlAnhNhanVien.setBackground(new Color(255, 255, 255));
		pnlAnhNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlAnhNhanVien.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ảnh nhân viên", TitledBorder.CENTER, TitledBorder.TOP, font2, new Color(0, 0, 0)));
		pnlAnhNhanVien.setBounds(1603, 11, 214, 258);
		pnlThongTinKH.add(pnlAnhNhanVien);
		pnlAnhNhanVien.setLayout(null);

		lblShowAnh = new JLabel("");
		lblShowAnh.setBounds(10, 26, 194, 221);
		pnlAnhNhanVien.add(lblShowAnh);

		btnChonAnh = new JButton("");
		btnChonAnh.setBounds(73, 90, 70, 70);
		pnlAnhNhanVien.add(btnChonAnh);
		btnChonAnh.setBackground(new Color(255, 255, 255));
		btnChonAnh.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/ChonAnh.png")));

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblDiaChi.setBounds(50, 239, 123, 30);
		pnlThongTinKH.add(lblDiaChi);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cboGioiTinh.setBounds(50, 175, 299, 35);
		pnlThongTinKH.add(cboGioiTinh);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(142, 234, 1223, 35);
		pnlThongTinKH.add(txtDiaChi);

		cboChucVu = new JComboBox();
		cboChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cboChucVu.setBounds(1251, 175, 299, 35);
		pnlThongTinKH.add(cboChucVu);

		JLabel lblChcV = new JLabel("Chức vụ :");
		lblChcV.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblChcV.setBounds(1250, 130, 163, 29);
		pnlThongTinKH.add(lblChcV);

		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(Color.white);
		pnlTacVu.setBounds(10, 370, 1894, 80);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ:"));
		contentPane.add(pnlTacVu);
		pnlTacVu.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(100, 21, 180, 48);
		pnlTacVu.add(btnThem);
		btnThem.setFont(font2);
		btnThem.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/TacVu_Them1.png")));
		btnThem.setBackground(Color.WHITE);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnSua.setBounds(320, 21, 180, 48);
		pnlTacVu.add(btnSua);
		btnSua.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/TacVu_Sua.png")));
		btnSua.setBackground(Color.WHITE);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnXoa.setBounds(550, 21, 180, 48);
		pnlTacVu.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/TacVu_Xoa1.png")));
		btnXoa.setBackground(Color.WHITE);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuu.setBounds(780, 21, 180, 48);
		pnlTacVu.add(btnLuu);
		btnLuu.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/TacVu_Luu.png")));
		btnLuu.setBackground(Color.WHITE);

		btnDatLai = new JButton("Đặt lại");
		btnDatLai.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnDatLai.setBounds(1010, 21, 180, 48);
		pnlTacVu.add(btnDatLai);
		btnDatLai.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/TacVu_DatLai.png")));
		btnDatLai.setBackground(Color.WHITE);

		JPanel pnlTacVuCon = new JPanel();
		pnlTacVuCon.setBackground(Color.white);
		;
		pnlTacVuCon.setBounds(1286, 0, 608, 80);
		pnlTacVuCon.setBorder(BorderFactory.createTitledBorder("Tìm kiếm nhân viên : "));
		pnlTacVu.add(pnlTacVuCon);
		pnlTacVuCon.setLayout(null);

		txtTimKiem = new JTextField("Nhập thông tin cần tìm");
		txtTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		txtTimKiem.setBounds(244, 20, 344, 40);
		pnlTacVuCon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		ButtonGroup group = new ButtonGroup();

		JRadioButton radSDT = new JRadioButton("Số điện thoại");
		radSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radSDT.setBounds(19, 20, 137, 23);
		pnlTacVuCon.add(radSDT);

		JRadioButton radMaNV = new JRadioButton("Mã nhân viên");
		radMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		radMaNV.setBounds(19, 45, 137, 23);
		pnlTacVuCon.add(radMaNV);
		group.add(radMaNV);
		group.add(radSDT);

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
		txtTimKiem.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtTimKiem.getText().trim();

				if (tuKhoa.equals("")) {
					modelKH.setRowCount(0);
					DocDuLieuDatabase();

				} else {
					if (radMaNV.isSelected()) {
						timKiemTheoMaNV(tuKhoa);
					} else if (radSDT.isSelected()) {
						timKiemTheoSDT(tuKhoa);
					}
				}
			}

		});

		JPanel pnlBangKH = new JPanel();
		pnlBangKH.setBounds(10, 460, 1894, 480);
		contentPane.add(pnlBangKH);
		pnlBangKH.setBorder(BorderFactory.createTitledBorder("Bảng thông tin nhân viên"));
		pnlBangKH.setLayout(null);

		pnlBangKH.setBackground(Color.white);
		;
		modelKH = new DefaultTableModel();
		modelKH.addColumn("Mã nhân viên");
		modelKH.addColumn("Tên nhân viên");
		modelKH.addColumn("CCCD");
		modelKH.addColumn("Ngày Sinh");
		modelKH.addColumn("Số điện thoại");
		modelKH.addColumn("Địa chỉ");
		modelKH.addColumn("Ngày vào làm");
		tblKH = new JTable(modelKH);
		tblKH.setBackground(new Color(153, 204, 255));
		JScrollPane jScrollPane = new JScrollPane(tblKH);
		jScrollPane.setBounds(15, 15, 1869, 450);
		JTableHeader tbHeaderKH = tblKH.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(new Color(51, 204, 204));
		pnlBangKH.setLayout(null);
		tblKH.setFont(font2);
		tblKH.setRowHeight(35);
		pnlBangKH.add(jScrollPane);

		btnDatLai.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnDatLai.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		tblKH.addMouseListener(this);
		btnLuu.addActionListener(this);

		//
		DefaultComboBoxModel<String> gioiTinhModel = new DefaultComboBoxModel<>();

		// Add items to the model
		gioiTinhModel.addElement("Nam");
		gioiTinhModel.addElement("Nữ");

		// Set the model to the JComboBox
		cboGioiTinh.setModel(gioiTinhModel);

		//

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		hinhanh_dao = new DAO_HinhAnh();
		nhanvien_dao = new DAO_NhanVien();
		chucvu_dao = new DAO_ChucVu();
		dongMoNhapLieu(false);

		ArrayList<ChucVu> listTK = chucvu_dao.getAllCV();
		for (ChucVu cv : listTK) {
			cboChucVu.addItem(cv.getMaChucVu());
		}

		DocDuLieuDatabase();
		txtTimKiem.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtTimKiem.getText().trim();
				timKiem(tuKhoa);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDatLai)) {
			xoaRong();
		}
		if (o.equals(btnChonAnh)) {
			chonAnh();
		}
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				moNutThem();
			} else {
				tblKH.addMouseListener(this);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnDatLai.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaNV.setText("");
				xoaRong();
				modelKH.setRowCount(0);
				dongMoNhapLieu(false);
				DocDuLieuDatabase();
			}
		}
		if (o.equals(btnXoa)) {
			xoa();
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblKH.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					lblShowAnh.setIcon(null);
					btnChonAnh.setVisible(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnDatLai.setEnabled(true);
					btnLuu.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách khách hàng, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnDatLai.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaNV.setText("");
				xoaRong();
			}
		}
		if (o.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if (validData()) {
					themNV();
				}
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maNV = txtMaNV.getText();
					String tenNV = txtTenNV.getText();
					String cccd = txtCCCD.getText();
					java.util.Date ngaySinh = dtmNgaySinh.getDate();
					String diaChi = txtDiaChi.getText();
					String sdt = txtSDT.getText();
					java.util.Date ngayVaoLam = dtmNgayVaoLam.getDate();
					boolean gioiTinh = true;
					if (cboGioiTinh.getSelectedItem().equals("Nam")) {
						gioiTinh = false;
					}
					String chucVu = cboChucVu.getSelectedItem().toString();
					ChucVu maCV = chucvu_dao.getChucVuTheoMa(chucVu);
					HinhAnh maIMG = dao_hinhanh.getHinhAnhTheoMa(maNV);
					if (changeImagePath != null) {
						if (maIMG.getUrl().equalsIgnoreCase(changeImagePath) == false) {
							boolean f = dao_hinhanh.updateIMG(maIMG.getMaAnh(), changeImagePath);
							if (!f) {
								System.out.println(f);
								JOptionPane.showMessageDialog(this, "Sửa ảnh không thành công");
							}

						}
					} else {
						maIMG = dao_hinhanh.getHinhAnhTheoMa(maNV);
					}

					NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, diaChi, ngayVaoLam, sdt, cccd, gioiTinh, maCV,
							maIMG);

					if (nhanvien_dao.updateTTNhanVien(nv)) {
						modelKH.setRowCount(0);
						DocDuLieuDatabase();
						JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công");
						dongMoNhapLieu(false);
						btnSua.setText("Sửa");
						btnThem.setEnabled(true);
						btnXoa.setEnabled(true);
						btnDatLai.setEnabled(false);
						btnLuu.setEnabled(false);
					}
				}
			}
		}
	}

	public void chonAnh() {
		// Đặt thư mục ban đầu thành "/img"

		File initialDirectory = new File(IMAGES_DIRECTORY);
		JFileChooser fileChooser = new JFileChooser(initialDirectory);
		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			if (btnThem.getText().equals("Hủy")) {
				selectedImagePath = selectedFile.getName();
				ImageIcon icon = new ImageIcon(selectedImagePath);
				Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				lblShowAnh.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/" + selectedImagePath)));
				btnChonAnh.setVisible(false);
			}
			if (btnSua.getText().equals("Hủy")) {
				changeImagePath = "";
				changeImagePath = selectedFile.getName();
				ImageIcon icon = new ImageIcon(changeImagePath);
				Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				lblShowAnh.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/" + changeImagePath)));
				btnChonAnh.setVisible(false);
			}

		}
	}

	private void saveImageToDatabase() {

		String maAnh = txtMaNV.getText();
		String tenAnh = txtTenNV.getText();
		String imagePath = lblShowAnh.getIcon() != null ? lblShowAnh.getIcon().toString() : "";

		// Validate that required fields are not empty
		if (maAnh.isEmpty() || tenAnh.isEmpty() || imagePath.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Hãy thêm ảnh của nhân viên vào.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Use the image path obtained from choosing the image
		imagePath = selectedImagePath;

		// Create an instance of the HinhAnh class with the provided data
		HinhAnh hinhAnh = new HinhAnh(maAnh, tenAnh, imagePath);

		// Save the HinhAnh instance to the database
		hinhanh_dao.themHinhAnh(hinhAnh);

		// Display a success message
		JOptionPane.showMessageDialog(this, "Thêm thành công!");

	}

	public void themNV() {
		try {
			// Get values from the input fields
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			String cccd = txtCCCD.getText();
			java.util.Date ngaySinh = dtmNgaySinh.getDate();
			String diaChi = txtDiaChi.getText();
			String sdt = txtSDT.getText();
			java.util.Date ngayVaoLam = dtmNgayVaoLam.getDate();
			boolean gioiTinh = cboGioiTinh.getSelectedItem().equals("Nam"); // Assuming "Nam" is for male
			String chucVu = cboChucVu.getSelectedItem().toString();
			ChucVu cv = null;
			if (chucVu.equals("Quản lý")) {
				cv = dao_chucVu.getChucVuTheoMa("CV001");
			} else {
				cv = dao_chucVu.getChucVuTheoMa("CV002");
			}

			saveImageToDatabase();
			HinhAnh ha = dao_hinhanh.getHinhAnhByMaAnh(maNV);

			// Validate required fields
			if (maNV.isEmpty() || tenNV.isEmpty() || cccd.isEmpty() || ngaySinh == null || diaChi.isEmpty()
					|| sdt.isEmpty() || ngayVaoLam == null || chucVu.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// lưu ảnh vào csdl HINHANH

			// Create a new NhanVien object

			NhanVien nhanVien = new NhanVien(maNV, tenNV, ngaySinh, diaChi, ngayVaoLam, sdt, cccd, gioiTinh, cv, ha);
			if (nhanvien_dao.createNV(nhanVien)) {
				// Refresh the table with the updated data
				modelKH.setRowCount(0); // Clear the current rows
				DocDuLieuDatabase(); // Reload data from the database

				// Display a success message
				JOptionPane.showMessageDialog(this, "Employee added successfully.");

				// Clear input fields
				xoaRong();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void moNutThem() {
		txtMaNV.setText(generateMaNhanVien());
		dongMoNhapLieu(true);

		btnLuu.setEnabled(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnDatLai.setEnabled(true);
		btnThem.setText("Hủy");
		tblKH.removeMouseListener(this);
		xoaRong();
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaNV.setEnabled(false);
		txtTenNV.setEditable(b);
		txtSDT.setEditable(b);
		txtCCCD.setEditable(b);
		txtDiaChi.setEditable(b);
		btnChonAnh.setEnabled(b);

	}

	private boolean validData() {
		String tenNV = txtTenNV.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String sdt = txtSDT.getText().trim();

		if (tenNV.length() == 0) {
			showMessage(txtTenNV, "Nhập tên nhân viên!");
			return false;
		}
		if (!tenNV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]*\\s?)+$")) {
			showMessage(txtTenNV, "Tên nhân viên bao gồm chữ cái, không bao gồm chữ số tiếng Việt,  ký tự đặc biệt!");
			return false;
		}
		if (sdt.length() < 10 || sdt.length() > 11) {
			showMessage(txtSDT, "Nhập SDT gồm 10 hoặc 11 chữ số!");
			return false;
		}
		if (!sdt.matches("^(0[0-9]{9,10})$")) {
			showMessage(txtSDT, "Số điện thoại gồm 10 hoặc 11 chữ số, bắt đầu bằng 0!");
			return false;
		}
		if (cccd.length() != 12) {
			showMessage(txtCCCD, "Nhập CCCD gồm 12 chữ số!");
			return false;
		}
		if (!cccd.matches("^([0-9]{12})$")) {
			showMessage(txtCCCD, "Nhập CCCD gồm 12 chữ số!");
			return false;
		}

		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.setFocusable(true);
		JOptionPane.showMessageDialog(this, message);
	}

	private void timKiem(String tuKhoa) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelKH);
		tblKH.setRowSorter(sorter);

		if (tuKhoa.isEmpty()) {
			sorter.setRowFilter(null);
		} else {
			RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa), 4, 1,
					6);

			sorter.setRowFilter(filter);
		}
	}

	private void DocDuLieuDatabase() {
		nhanvien_dao = new DAO_NhanVien();
		tblKH.setRowHeight(25);
		for (NhanVien nv : nhanvien_dao.getAllNV()) {

			modelKH.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getCccd(), nv.getNgaySinh(), nv.getSdt(),
					nv.getDiaChi(), nv.getNgayVaoLam() });
		}
	}

	private String generateMaNhanVien() {

		java.util.Date selectedDate = dtmNgayVaoLam.getDate();

		if (selectedDate != null) {
			try {
				// Format selected date to get the part of the ID
				SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
				String datePart = dateFormat.format(selectedDate);

				boolean f = soSanhNgay();

				if (f == true) {

					int sequenceNumber = 1;
					String sequencePart = String.format("%03d", sequenceNumber).trim();
					return "NV" + datePart + sequencePart;
				}
				// Hóa đơn trong ngày
				else {
					int soCu = nhanvien_dao.getCurrentSequenceNumber2();
					soCu++;
					String sequencePart = String.format("%03d", soCu).trim();
					return "NV" + datePart + sequencePart;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	private boolean soSanhNgay() {
		java.util.Date ngayHienTai = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String ngayHT = dateFormat.format(ngayHienTai);
		String NVCu = nhanvien_dao.layNgayNhanVienTruoc();
		// Hóa đơn đầu tiên
		if (NVCu == null) {
			return true;
		}
		// 01 01 2023
		// So sánh năm
		String cu1 = NVCu.substring(4, 7);
		String moi1 = ngayHT.substring(4, 7);
		int namCu = Integer.parseInt(cu1);
		int namMoi = Integer.parseInt(moi1);
		if (namCu < namMoi)
			return true;
		// So sánh tháng
		String cu2 = NVCu.substring(2, 4);
		String moi2 = ngayHT.substring(2, 4);
		int thangCu = Integer.parseInt(cu2);
		int thangMoi = Integer.parseInt(moi2);
		if (thangCu < thangMoi)
			return true;
		// So sánh ngày
		String cu3 = NVCu.substring(0, 2);
		String moi3 = ngayHT.substring(0, 2);
		int ngayCu = Integer.parseInt(cu3);
		int ngayMoi = Integer.parseInt(moi3);
		if (ngayCu < ngayMoi)
			return true;

		return false;
	}

	public void xoaRong() {
		lblShowAnh.setIcon(null);
		btnChonAnh.setVisible(true);
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtCCCD.setText("");
		txtTimKiem.setText("");
		txtSDT.setText("");
		txtTenNV.requestFocus();
	}

	public void xoa() {
		int row = tblKH.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên cần xoá");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {

				// xoá ảnh trong csdl HINHANH
				String maNV = modelKH.getValueAt(row, 0).toString();
				String maAnh = nhanvien_dao.getMaAnhByMaNV(maNV);
				HinhAnh hinhAnh = nhanvien_dao.getHinhAnhByMaAnh(maAnh);
				hinhanh_dao.xoaIMG(maAnh);

				//
				int index = tblKH.getSelectedRow();
				nhanvien_dao.xoaNV(modelKH.getValueAt(tblKH.getSelectedRow(), 0).toString());
				hinhanh_dao.xoaIMG(maAnh);
				modelKH.removeRow(index);
				xoaRong();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		dongMoNhapLieu(true);
		int row = tblKH.getSelectedRow();
		if (row >= 0) {

			String maNV = modelKH.getValueAt(row, 0).toString();
			NhanVien nv = nhanvien_dao.getNhanVienTheoMa2(maNV);
			// Hiển thị ảnh lên lblShowAnh
			if (nv != null) {
				lblShowAnh.setIcon(
						new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/" + nv.getHinhAnh().getUrl())));
			} else {
				lblShowAnh.setIcon(new ImageIcon(GuiQuanLyNhanVien.class.getResource("/image/anhmacdinh.png")));
			}

			//

			txtMaNV.setText(modelKH.getValueAt(row, 0).toString());
			txtTenNV.setText(modelKH.getValueAt(row, 1).toString());
			txtCCCD.setText(modelKH.getValueAt(row, 2).toString());
			txtDiaChi.setText(modelKH.getValueAt(row, 5).toString());
			txtSDT.setText(modelKH.getValueAt(row, 4).toString());

			String ngaySinh = modelKH.getValueAt(row, 3).toString();
			String ngayVaoLam = modelKH.getValueAt(row, 6).toString();

			java.util.Date date;
			try {
				date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
				date1 = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(ngayVaoLam);
				dtmNgaySinh.setDate(date);
				dtmNgayVaoLam.setDate(date1);
			} catch (java.text.ParseException ex) {
				ex.printStackTrace();
			}

			//

			dtmNgayVaoLam.setEnabled(false);
			btnChonAnh.setVisible(false);

			String gioiTinh = nhanvien_dao.getGioiTinhByMaNV(maNV);
			String chucvu = nhanvien_dao.getGioiTinhByMaCV(maNV);

			// Đưa giới tính vào combobox
			cboGioiTinh.setSelectedItem(gioiTinh);
			cboChucVu.setSelectedItem(chucvu);

		}
	}

	// Tìm nsx trả hàng theo mã
	public void timKiemTheoMaNV(String tuKhoa) {
		modelKH.setRowCount(0);
		for (NhanVien nv : nhanvien_dao.getAllNV()) {
			String ma = nv.getMaNV();
			if (ma.toLowerCase().contains(tuKhoa)) {
				modelKH.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getCccd(), nv.getNgaySinh(), nv.getSdt(),
						nv.getDiaChi(), nv.getNgayVaoLam() });
			}
		}
	}

	// Tìm nsx trả hàng theo mã
	public void timKiemTheoSDT(String tuKhoa) {
		modelKH.setRowCount(0);
		for (NhanVien nv : nhanvien_dao.getAllNV()) {
			String ten = nv.getSdt();
			if (ten.toLowerCase().contains(tuKhoa)) {
				modelKH.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getCccd(), nv.getNgaySinh(), nv.getSdt(),
						nv.getDiaChi(), nv.getNgayVaoLam() });
			}
		}
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
}
