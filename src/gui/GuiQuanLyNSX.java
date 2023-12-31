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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.DAO_NSX;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GuiQuanLyNSX extends JFrame implements ActionListener, MouseListener, ListSelectionListener{

	public static JPanel contentPane;
	private JTextField txtMaNSX;
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtThanhPho;
	private JTextField txtTenNSX;
	private JTextField txtTimKiem;
	private DefaultTableModel modelNSX;
	private JTable tblNSX;
	private ButtonGroup group;
	private DAO_NSX nsx_dao;
	private JButton btnDatLai;
	private JButton btnLuu;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiQuanLyNSX frame = new GuiQuanLyNSX();
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
	public GuiQuanLyNSX() {
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
	
		//Tiêu đề
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBounds(10, 10, 1891, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÀ SẢN XUẤT");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));
		
		JPanel pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBackground(new Color(255, 255, 255));
		
		pnlThongTinKH.setBorder(BorderFactory.createTitledBorder("Thông tin nhà sản xuất"));
		pnlThongTinKH.setBounds(10, 80, 350, 855);
		contentPane.add(pnlThongTinKH);
		pnlThongTinKH.setLayout(null);
		
		JLabel lblMaNSX = new JLabel("Mã nhà sản xuất:");
		lblMaNSX.setBounds(10, 35, 319, 30);
		pnlThongTinKH.add(lblMaNSX);
		lblMaNSX.setFont(font2);
		
		JLabel lblTenNSX = new JLabel("Tên nhà sản xuất :");
		lblTenNSX.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblTenNSX.setBounds(10, 175, 187, 30);
		pnlThongTinKH.add(lblTenNSX);
		
		JLabel lblSDT = new JLabel("Số điện thoại :");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblSDT.setBounds(10, 595, 163, 30);
		pnlThongTinKH.add(lblSDT);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblEmail.setBounds(10, 455, 163, 30);
		pnlThongTinKH.add(lblEmail);
		
		JLabel lblThanhPho = new JLabel("Thành phố :");
		lblThanhPho.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblThanhPho.setBounds(10, 315, 163, 30);
		pnlThongTinKH.add(lblThanhPho);
		
		txtMaNSX = new JTextField();
		txtMaNSX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaNSX.setEditable(false);
		txtMaNSX.setBounds(20, 100, 319, 40);
		pnlThongTinKH.add(txtMaNSX);
		txtMaNSX.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(20, 520, 319, 40);
		pnlThongTinKH.add(txtEmail);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSDT.setColumns(10);
		txtSDT.setBounds(20, 650, 314, 40);
		pnlThongTinKH.add(txtSDT);
		
		txtThanhPho = new JTextField();
		txtThanhPho.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtThanhPho.setColumns(10);
		txtThanhPho.setBounds(20, 380, 319, 40);
		pnlThongTinKH.add(txtThanhPho);
		
		txtTenNSX = new JTextField();
		txtTenNSX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTenNSX.setColumns(10);
		txtTenNSX.setBounds(20, 240, 319, 40);
		pnlThongTinKH.add(txtTenNSX);
		
		JPanel pnlTacVuCon = new JPanel();
		pnlTacVuCon.setBounds(9, 741, 330, 103);
		pnlThongTinKH.add(pnlTacVuCon);
		pnlTacVuCon.setBackground(Color.white);
		pnlTacVuCon.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo : "));
		pnlTacVuCon.setLayout(null);
		
		txtTimKiem = new JTextField("Nhập thông tin cần tìm");
		txtTimKiem.setBounds(10, 39, 308, 40);
		pnlTacVuCon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
				group = new ButtonGroup();
				
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
							modelNSX.setRowCount(0);
							DocDuLieuDatabase();

						}
						else {
							timKiemTheoMaNSX(tuKhoa);
							}
						}
						
						

				});
				
		
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(Color.white);
		pnlTacVu.setBounds(370, 855, 1530, 80);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ:"));
		contentPane.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(140, 21, 180, 48);
		pnlTacVu.add(btnThem);
		btnThem.setFont(font2);
		btnThem.setIcon(new ImageIcon(GuiQuanLyNSX.class.getResource("/image/TacVu_Them1.png")));
		btnThem.setBackground(Color.WHITE);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnSua.setBounds(410, 21, 180, 48);
		pnlTacVu.add(btnSua);
		btnSua.setIcon(new ImageIcon(GuiQuanLyNSX.class.getResource("/image/TacVu_Sua.png")));
		btnSua.setBackground(Color.WHITE);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnXoa.setBounds(680, 21, 180, 48);
		pnlTacVu.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(GuiQuanLyNSX.class.getResource("/image/TacVu_Xoa1.png")));
		btnXoa.setBackground(Color.WHITE);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuu.setBounds(950, 21, 180, 48);
		pnlTacVu.add(btnLuu);
		btnLuu.setIcon(new ImageIcon(GuiQuanLyNSX.class.getResource("/image/TacVu_Luu.png")));
		btnLuu.setBackground(Color.WHITE);
		
		btnDatLai = new JButton("Đặt lại");
		btnDatLai.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnDatLai.setBounds(1220, 21, 180, 48);
		pnlTacVu.add(btnDatLai);
		btnDatLai.setIcon(new ImageIcon(GuiQuanLyNSX.class.getResource("/image/TacVu_DatLai.png")));
		btnDatLai.setBackground(Color.WHITE);;
		
		
		JPanel pnlBangNSX = new JPanel();
		pnlBangNSX.setBounds(370, 80, 1534, 765);
		contentPane.add(pnlBangNSX);
		pnlBangNSX.setBorder(BorderFactory.createTitledBorder("Bảng thông tin nhà sản xuất"));
		pnlBangNSX.setLayout(null);
		
		

		pnlBangNSX.setBackground(Color.white);;
		modelNSX = new DefaultTableModel();
		modelNSX.addColumn("Mã nhà sản xuất");
		modelNSX.addColumn("Tên nhà sản xuất");
		modelNSX.addColumn("Thành phố");
		modelNSX.addColumn("Email");
		modelNSX.addColumn("Số điện thoại");
		tblNSX = new JTable(modelNSX);
		tblNSX.setBackground(new Color(153, 204, 255));
		JScrollPane jScrollPane = new JScrollPane(tblNSX);
		jScrollPane.setBounds(20, 20, 1493, 725);
		JTableHeader tbHeaderNSX = tblNSX.getTableHeader();
		tbHeaderNSX.setFont(font2);
		tbHeaderNSX.setBackground(new Color(51, 204, 204));
		pnlBangNSX.setLayout(null);
		tblNSX.setFont(font2);
		tblNSX.setRowHeight(35);
		// String row[] = {"KH001","Nguyễn Văn B","0123456789","0","Thường"," "};
		// modelKH.addRow(row);
		pnlBangNSX.add(jScrollPane);
		
		//
		tblNSX.addMouseListener(this);
		btnDatLai.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		
		// ket noi sql
		nsx_dao = new DAO_NSX();
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//
		tblNSX.addMouseListener(this);
		
		//xu lý
		DocDuLieuDatabase();
		dongMoNhapLieu(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDatLai)) {
			xoaRong();
		}
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				moNutThem();
				String maMoi = nsx_dao.taoMaNSX();
				txtMaNSX.setText(maMoi);
			} else {
				tblNSX.addMouseListener(this);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnDatLai.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaNSX.setText("");
				xoaRong();
				dongMoNhapLieu(false);
			}
		}
		if (o.equals(btnXoa)) {
			xoa();
		}if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblNSX.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
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
				txtMaNSX.setText("");
				xoaRong();
			}
		}if (o.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if(validData()) {
					themNSX();
				}
				
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String ma = txtMaNSX.getText().trim();
					String ten = txtTenNSX.getText().trim();
					String thanhPho = txtThanhPho.getText().trim();
					String sdt = txtSDT.getText().trim();
					String email = txtEmail.getText().trim();
					NhaSanXuat nsx = new NhaSanXuat(ma, ten, thanhPho, email, sdt);
					if (nsx_dao.updateNhaSanXuat(nsx)) {
						DocDuLieuDatabase();
						JOptionPane.showMessageDialog(this, "Sửa thông tin màu sắc thành công");
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
	
	//
	
	public void DocDuLieuDatabase() {
		nsx_dao = new DAO_NSX();
		for(NhaSanXuat nsx : nsx_dao.getAllNSX()) {
			modelNSX.addRow(new Object[] {nsx.getMaNSX(), nsx.getTenNSX(),nsx.getThanhPho(),
					                      nsx.getEmail(), nsx.getSdt()});
		}
	}
	public void xoaRong() {
		txtEmail.setText("");
		txtMaNSX.setText("");
		txtSDT.setText("");
		txtTenNSX.setText("");
		txtThanhPho.setText("");
		txtTimKiem.setText("");
		txtEmail.setEditable(true);
		txtSDT.setEditable(true);
		txtTenNSX.setEditable(true);
		txtThanhPho.setEditable(true);
		txtMaNSX.setEditable(true);
	}
	
	private void themNSX() {
		String maNSX = txtMaNSX.getText();
		String tenNSX = txtTenNSX.getText();
		String city = txtThanhPho.getText();
		String email = txtEmail.getText();
		String sdt = txtSDT.getText();
		
		NhaSanXuat nsx = new NhaSanXuat(maNSX, tenNSX, city, email, sdt);
		if (nsx_dao.createNSX(nsx)) {
			modelNSX.addRow(new Object[] { nsx.getMaNSX(), nsx.getTenNSX(), nsx.getThanhPho(), nsx.getEmail(), nsx.getSdt()});	
			JOptionPane.showMessageDialog(this, "Thêm nhà sản xuất thành công");
			btnThem.setText("Thêm");
			xoaRong();
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			btnDatLai.setEnabled(false);
			btnLuu.setEnabled(false);
			txtMaNSX.setText("");
			tblNSX.addMouseListener(this);
			dongMoNhapLieu(false);
		}else {
			JOptionPane.showMessageDialog(this, "Không thành công");
		}
	}
	
	public void xoa() {
		int row = tblNSX.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn nhà sản xuất cần xoá");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhà sản xuất này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = tblNSX.getSelectedRow();
				nsx_dao.xoaNSX(modelNSX.getValueAt(tblNSX.getSelectedRow(), 0).toString());
				modelNSX.removeRow(index);
				xoaRong();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tblNSX.getSelectedRow();
		txtMaNSX.setText(tblNSX.getValueAt(row, 0).toString());
		txtTenNSX.setText(tblNSX.getValueAt(row, 1).toString());
		txtThanhPho.setText(tblNSX.getValueAt(row, 2).toString());
		txtEmail.setText(tblNSX.getValueAt(row, 3).toString());
		txtSDT.setText(tblNSX.getValueAt(row, 4).toString());
		txtEmail.setEditable(false);
		txtSDT.setEditable(false);
		txtTenNSX.setEditable(false);
		txtThanhPho.setEditable(false);
		txtMaNSX.setEditable(false);
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
	
	public void moNutThem() {
		dongMoNhapLieu(true);
		btnLuu.setEnabled(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnDatLai.setEnabled(true);
		btnThem.setText("Hủy");
		tblNSX.removeMouseListener(this);
		xoaRong();
	}
	private void dongMoNhapLieu(Boolean b) {
		txtMaNSX.setEditable(false);
		txtMaNSX.setEnabled(false);
		txtTenNSX.setEditable(b);
		txtThanhPho.setEditable(b);
		txtEmail.setEditable(b);
		txtSDT.setEditable(b);
	}
	private boolean validData() {
		String tenMau  = txtTenNSX.getText().trim();
		String thanhPho = txtThanhPho.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSDT.getText().trim();
		if (tenMau.length() == 0) {
			showMessage(txtTenNSX, "Nhập tên nhà sản xuất!");
			return false;
		}
		if (!tenMau.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenNSX, "Tên nhà sản xuất bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (thanhPho.length() == 0) {
			showMessage(txtThanhPho, "Nhập tên thành phố!");
			return false;
		}
		if (!thanhPho.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]*\\s?)+$")) {
			showMessage(txtThanhPho, "Tên thành phố bao gồm chữ cái, không bao gồm chữ số, ký tự đặc biệt!");
			return false;
		}
		if (email.length() == 0) {
			showMessage(txtEmail, "Nhập email nhà sản xuất!");
			return false;
		}
		if (!email.matches(
				"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			showMessage(txtEmail, "Vui lòng nhập lại email!");
			return false;
		}
		if (sdt.length() < 10 || sdt.length() > 11) {
			showMessage(txtSDT, "Nhập SDT gồm 10 hoặc 11 chữ số!");
			return false;}
		if (!sdt.matches("^(0[0-9]{9,10})$") ) {
			showMessage(txtSDT, "Số điện thoại gồm 10 hoặc 11 chữ số, bắt đầu bằng 0!");
			return false;
		}
		
		return true;
	}
	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
	// Tìm nsx trả hàng theo mã
	public void timKiemTheoMaNSX(String tuKhoa) {
		modelNSX.setRowCount(0);
		for (NhaSanXuat nsx : nsx_dao.getAllNSX()) {
			String maNSX = nsx.getMaNSX();
			if (maNSX.toLowerCase().contains(tuKhoa)) {
				modelNSX.addRow(new Object[] {nsx.getMaNSX(), nsx.getTenNSX(),nsx.getThanhPho(),
	                      nsx.getEmail(), nsx.getSdt()});
			}
		}
	}
}
