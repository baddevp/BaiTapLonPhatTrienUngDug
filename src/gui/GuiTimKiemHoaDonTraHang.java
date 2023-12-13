package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DAO_HoaDonTraHang;
import entity.HoaDon;
import entity.HoaDonHoanTra;
import entity.HoaDonModelPainter;
import entity.HoaDonTraHangModelPainter;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuiTimKiemHoaDonTraHang extends JFrame implements ActionListener {

	public static JPanel contentPane;
	private JTextField txtNhap;
	private DefaultTableModel modelKH;
	private JComboBox cmbLoc;
	private DefaultListModel modelList;
	private JList<HoaDonHoanTra> lstHD;
	DAO_HoaDonTraHang dao_Trahang = new DAO_HoaDonTraHang();
	static NhanVien nv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTimKiemHoaDonTraHang frame = new GuiTimKiemHoaDonTraHang(nv);
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
	public GuiTimKiemHoaDonTraHang(NhanVien nv) {
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
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);

		// Tiêu đề
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBounds(15, 15, 1889, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);

		JLabel lblTieuDe = new JLabel("TÌM KIẾM HÓA ĐƠN TRẢ HÀNG");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));
		;

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBounds(15, 90, 1889, 90);
		contentPane.add(pnlChucNang);
		pnlChucNang.setBackground(Color.white);
		pnlChucNang.setBorder(BorderFactory.createTitledBorder("Tìm kiếm: "));
		pnlChucNang.setLayout(null);

		txtNhap = new JTextField("Nhập thông tin cần tìm");
		txtNhap.setBounds(348, 22, 1026, 48);
		txtNhap.setFont(font2);
		pnlChucNang.add(txtNhap);
		txtNhap.setColumns(10);
		// Danh sách các thuộc tính của khách hàng
		cmbLoc = new JComboBox<>();
		cmbLoc.setModel(new DefaultComboBoxModel(new String[] {"Mã trả hàng", "Nhân viên", "Khách hàng", "Mã hóa đơn"}));
		cmbLoc.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cmbLoc.setBounds(1580, 22, 258, 46);
		cmbLoc.setBackground(new Color(255, 255, 255));
		pnlChucNang.add(cmbLoc);

		txtNhap.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNhap.getText().equals("Nhập thông tin cần tìm")) {
					txtNhap.setText("");
					txtNhap.setForeground(Color.BLACK); // Đổi màu chữ khi có focus
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNhap.getText().isEmpty()) {
					txtNhap.setText("Nhập thông tin cần tìm");
					txtNhap.setForeground(Color.GRAY); // Đổi màu chữ gợi ý khi mất focus
				}
			}
		});
		txtNhap.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtNhap.getText().trim();
				String loai = cmbLoc.getSelectedItem().toString();
				if (tuKhoa.equals("")) {
					modelList.clear();
					docThongTinHoaDonTraHangVaoModel(modelList);
				}
				else if(loai.equals("Mã trả hàng")){
					timKiemTheoMaTraHang(tuKhoa);
				} 
				else if(loai.equals("Nhân viên")){
					timKiemTheoTenNhanVien(tuKhoa);
				}
				else if(loai.equals("Khách hàng")){
					timKiemTheoTenKhachHang(tuKhoa);
				}
				else{
					timKiemTheoMaHoaDon(tuKhoa);
				}
			}

		});

		JLabel lblNhap = new JLabel("Thông tin hóa đơn :");
		lblNhap.setBounds(80, 27, 258, 36);
		lblNhap.setFont(font2);
		pnlChucNang.add(lblNhap);

		JLabel lblLoc = new JLabel("Lọc theo :");
		lblLoc.setBounds(1436, 22, 124, 48);
		lblLoc.setFont(font2);
		pnlChucNang.add(lblLoc);

		// Thể hiện danh sách tìm kiếm được
		JPanel pnlBangHDTH = new JPanel();
		pnlBangHDTH.setBounds(15, 191, 1889, 770);
		contentPane.add(pnlBangHDTH);
		pnlBangHDTH.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn trả hàng"));
		pnlBangHDTH.setLayout(null);

		modelList = new DefaultListModel<>();
		docThongTinHoaDonTraHangVaoModel(modelList);

		lstHD = new JList<HoaDonHoanTra>(modelList);
		HoaDonTraHangModelPainter painter = new HoaDonTraHangModelPainter();
		lstHD.setCellRenderer(painter);
		lstHD.setVisibleRowCount(4);
		JScrollPane scrSach = new JScrollPane(lstHD);
		scrSach.setBounds(20, 20, 1850, 730);

		pnlBangHDTH.add(scrSach);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	// Đọc thông tin sách vào model
			private void docThongTinHoaDonTraHangVaoModel(DefaultListModel<HoaDonHoanTra> model) {
				for (HoaDonHoanTra s : dao_Trahang.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
					model.addElement(s);
				}
			}
		// Tìm hóa đơn trả hàng theo mã
		public void timKiemTheoMaTraHang(String tuKhoa) {
			modelList.clear();
			for (HoaDonHoanTra s : dao_Trahang.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
				String maTH = s.getMaYeuCauTraHang();
				if (maTH.toLowerCase().contains(tuKhoa)) {
					modelList.addElement(s);
				}
			}
		}

		// Tìm hóa đơn trả hàng theo tên
		public void timKiemTheoTenNhanVien(String tuKhoa) {
			modelList.clear();
			for (HoaDonHoanTra s : dao_Trahang.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
				String tenNV = s.getNhanVien().getTenNV();
				if (tenNV.toLowerCase().contains(tuKhoa)) {
					modelList.addElement(s);
				}
			}
		}
		// Tìm hóa đơn trả hàng theo tên
			public void timKiemTheoTenKhachHang(String tuKhoa) {
				modelList.clear();
				for (HoaDonHoanTra s : dao_Trahang.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
					String tenNV = s.getKhachHang().getTenKH();
					if (tenNV.toLowerCase().contains(tuKhoa)) {
						modelList.addElement(s);
					}
				}
			}
		// Tìm hóa đơn trả hàng theo tên
			public void timKiemTheoMaHoaDon(String tuKhoa) {
				modelList.clear();
				for (HoaDonHoanTra s : dao_Trahang.getHDTraHangNhanVienBanDuocTheoNgay(LocalDateTime.now(), nv)) {
					String maHD = s.getHoaDon().getMaHoaDon();
					if (maHD.toLowerCase().contains(tuKhoa)) {
						modelList.addElement(s);
					}
				}
			}
		

}
