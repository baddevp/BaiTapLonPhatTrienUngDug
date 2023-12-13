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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DAO_HoaDon;
import dao.DAO_KhachHang;
import entity.KhachHang;
import entity.KhachHangModelPainter;
import entity.NhanVien;
import entity.NhanVienModelPainter;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class GuiTimKiemKhachHang extends JFrame implements ActionListener {

	public static JPanel contentPane;
	private JTextField txtNhap;
	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	private JComboBox cmbLoc;
	private DefaultListModel modelList;
	private JList<KhachHang> lstKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTimKiemKhachHang frame = new GuiTimKiemKhachHang();
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
	public GuiTimKiemKhachHang() {
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
	
		//Tiêu đề
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBounds(15, 15, 1889, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);
		
		JLabel lblTieuDe = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));;
		
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
						
		JLabel lblNhap = new JLabel("Thông tin khách hàng:");
		lblNhap.setBounds(80, 27, 258, 36);
		lblNhap.setFont(font2);
		pnlChucNang.add(lblNhap);
		//Danh sách các thuộc tính của khách hàng
		cmbLoc = new JComboBox();
		cmbLoc.setBackground(new Color(255, 255, 255));
		cmbLoc.setBounds(1580, 22, 258, 46);
		cmbLoc.setModel(new DefaultComboBoxModel(new String[] { "Mã khách hàng", "Tên khách hàng", "SDT" }));
		cmbLoc.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		pnlChucNang.add(cmbLoc);
		
		JLabel lblLoc = new JLabel("Lọc theo :");
		lblLoc.setBounds(1436, 22, 124, 48);
		lblLoc.setFont(font2);
		pnlChucNang.add(lblLoc);
		
		//Thể hiện danh sách tìm kiếm được
		JPanel pnlBangKH = new JPanel();
		pnlBangKH.setBounds(15, 195, 1889, 770);
		contentPane.add(pnlBangKH);
		pnlBangKH.setBorder(BorderFactory.createTitledBorder("Danh sách thông tin khách hàng"));
		pnlBangKH.setLayout(null);
		
		modelList = new DefaultListModel<>();
		docThongTinVPPVaoModel(modelList);

		lstKH = new JList<KhachHang>(modelList);
		KhachHangModelPainter painter = new KhachHangModelPainter();
		lstKH.setCellRenderer(painter);
		lstKH.setVisibleRowCount(4);
		JScrollPane scrSach = new JScrollPane(lstKH);
		scrSach.setBounds(20, 20, 1850, 730);

		pnlBangKH.add(scrSach);
		
		txtNhap.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtNhap.getText().trim();
				String loai = cmbLoc.getSelectedItem().toString();
				if (tuKhoa.equals("")) {
					modelList.clear();
					docThongTinVPPVaoModel(modelList);
				}
				else if(loai.equals("Mã khách hàng")){
					timKiemTheoMaNhanVien(tuKhoa);
				} 
				else if(loai.equals("Tên khách hàng")){
					timKiemTheoTenNhanVien(tuKhoa);
				}
				else if(loai.equals("SDT")){
					timKiemTheoSoDienThoai(tuKhoa);
				}
			}

		});

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Đọc thông tin vpp vào model
			private void docThongTinVPPVaoModel(DefaultListModel<KhachHang> model) {
				for (KhachHang s : dao_KhachHang.getAllKH()) {
					model.addElement(s);
				}
			}
			// Tìm hóa đơn trả hàng theo mã
			public void timKiemTheoMaNhanVien(String tuKhoa) {
				modelList.clear();
				for (KhachHang s : dao_KhachHang.getAllKH()) {
					String maKH = s.getMaKH();
					if (maKH.toLowerCase().contains(tuKhoa)) {
						modelList.addElement(s);
					}
				}
			}

			// Tìm hóa đơn trả hàng theo tên
			public void timKiemTheoTenNhanVien(String tuKhoa) {
				modelList.clear();
				for (KhachHang s : dao_KhachHang.getAllKH()) {
					String tenKH = s.getTenKH();
					if (tenKH.toLowerCase().contains(tuKhoa)) {
						modelList.addElement(s);
					}
				}
			}
			// Tìm VPP theo tên thương hiệu
				public void timKiemTheoSoDienThoai(String tuKhoa) {
					modelList.clear();
					for (KhachHang s : dao_KhachHang.getAllKH()) {
						String sdt = s.getSdt();
						if (sdt.toLowerCase().contains(tuKhoa)) {
							modelList.addElement(s);
						}
					}
				}
}
