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
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import connectDB.ConnectDB;
import dao.DAO_HoaDon;
import entity.HoaDon;
import entity.HoaDonModelPainter;
import entity.Sach;
import entity.SachModelPainter;

public class GuiTimKiemHoaDon extends JFrame implements ActionListener {

	public static JPanel contentPane;
	private JTextField txtNhap;
	
	private JTable table;
	private DefaultTableModel modelHD;
	private JTable tblHD;
	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
	private JComboBox cmbLoc;
	private DefaultListModel modelList;
	private JList<HoaDon> lstHD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTimKiemHoaDon frame = new GuiTimKiemHoaDon();
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
	public GuiTimKiemHoaDon() {
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
		
		JLabel lblTieuDe = new JLabel("TÌM KIẾM HÓA ĐƠN");
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
						
		JLabel lblNhap = new JLabel("Thông tin hóa đơn :");
		lblNhap.setBounds(80, 27, 258, 36);
		lblNhap.setFont(font2);
		pnlChucNang.add(lblNhap);
		
		cmbLoc = new JComboBox<>(new String[] {"Mã hóa đơn", "Nhân viên", "Khách hàng", "Chờ thanh toán"});
		cmbLoc.setBounds(1580, 22, 258, 46);
		cmbLoc.setBackground(new Color(255, 255, 255));
		pnlChucNang.add(cmbLoc);
		
		
		JLabel lblLoc = new JLabel("Lọc theo :");
		lblLoc.setBounds(1436, 22, 124, 48);
		lblLoc.setFont(font2);
		pnlChucNang.add(lblLoc);
		
		//Thể hiện danh sách tìm kiếm được
		JPanel pnlBangHD = new JPanel();
		pnlBangHD.setBounds(15, 191, 1889, 770);
		contentPane.add(pnlBangHD);
		pnlBangHD.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));
		pnlBangHD.setLayout(null);
		
		modelList = new DefaultListModel<>();
		docThongTinHoaDonVaoModel(modelList);

		lstHD = new JList<HoaDon>(modelList);
		HoaDonModelPainter painter = new HoaDonModelPainter();
		lstHD.setCellRenderer(painter);
		lstHD.setVisibleRowCount(4);
		JScrollPane scrSach = new JScrollPane(lstHD);
		scrSach.setBounds(20, 20, 1850, 730);

		pnlBangHD.add(scrSach);
		

		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		txtNhap.addKeyListener((KeyListener) new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tuKhoa = txtNhap.getText().trim();
				String loai = cmbLoc.getSelectedItem().toString();
				if (tuKhoa.equals("")) {
					modelList.clear();
					docThongTinHoaDonVaoModel(modelList);
				}
				else if(loai.equals("Mã hóa đơn")){
					timKiemTheoMaHD(tuKhoa);
				} 
				else if(loai.equals("Nhân viên")){
					timKiemTheoTenNhanVien(tuKhoa);
				}
				else if(loai.equals("Khách hàng")){
					timKiemTheoTenKhachHang(tuKhoa);
				}
				else{
					timKiemTheoTrangThai();
				}
			}

		});


		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Đọc thông tin sách vào model
		private void docThongTinHoaDonVaoModel(DefaultListModel<HoaDon> model) {
			for (HoaDon s : dao_HoaDon.getAllHDBanDuocTrongNgay(LocalDateTime.now())) {
				model.addElement(s);
			}
		}
	// Tìm hóa đơn trả hàng theo mã
	public void timKiemTheoMaHD(String tuKhoa) {
		modelList.clear();
		for (HoaDon s : dao_HoaDon.getAllHDBanDuocTrongNgay(LocalDateTime.now())) {
			String maSach = s.getMaHoaDon();
			if (maSach.toLowerCase().contains(tuKhoa)) {
				modelList.addElement(s);
			}
		}
	}

	// Tìm hóa đơn trả hàng theo tên
	public void timKiemTheoTenNhanVien(String tuKhoa) {
		modelList.clear();
		for (HoaDon s : dao_HoaDon.getAllHDBanDuocTrongNgay(LocalDateTime.now())) {
			String tenNV = s.getNhanVien().getTenNV();
			if (tenNV.toLowerCase().contains(tuKhoa)) {
				modelList.addElement(s);
			}
		}
	}
	// Tìm hóa đơn trả hàng theo tên
		public void timKiemTheoTenKhachHang(String tuKhoa) {
			modelList.clear();
			for (HoaDon s : dao_HoaDon.getAllHDBanDuocTrongNgay(LocalDateTime.now())) {
				String tenNV = s.getKhachHang().getTenKH();
				if (tenNV.toLowerCase().contains(tuKhoa)) {
					modelList.addElement(s);
				}
			}
		}
	// Tìm hóa đơn trả hàng theo tên
		public void timKiemTheoTrangThai() {
			modelList.clear();
			for (HoaDon s : dao_HoaDon.getAllHDBanDuocTrongNgay(LocalDateTime.now())) {
				if (s.isTrangThai() == false) {
					modelList.addElement(s);
				}
			}
		}
	

	
}
