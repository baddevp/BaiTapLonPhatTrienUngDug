package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DAO_ChucVu;
import dao.DAO_HinhAnh;
import dao.DAO_NhanVien;
import dao.DAO_TaiKhoan;
import entity.ChucVu;
import entity.HinhAnh;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;

public class GuiThongTinNhanVien extends JFrame implements ActionListener {

	public static JPanel contentPane;

	private JPanel pnThongTinTK;
	private JPanel pnTTTK;
	private JPanel pnDoiMK;
	private JTextField txtHoTen;
	private JTextField txtCMND;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JLabel lbMKC;
	private JLabel lbMKM;
	private JLabel lbXNMK;
	private JButton btnDoiMK;
	private JPasswordField txtMKC;
	private JPasswordField txtMKM;
	private JPasswordField txtXNMK;
	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private DAO_TaiKhoan dao_TaiKhoan = new DAO_TaiKhoan();
	DAO_HinhAnh dao_hinhAnh = new DAO_HinhAnh();
	DAO_ChucVu dao_chucVu = new DAO_ChucVu();

	private JComboBox cbGioiTinh;
	private JLabel lblHinhAnh;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private static NhanVien nv;
	private String tentk;
	private JButton btnLuuMK;
	private JRadioButton rdbtnHienMK;

	private JDateChooser txtNgaySinh;

	public static void main(String[] args) {
		new GuiThongTinNhanVien(nv).setVisible(true);
	}

	public GuiThongTinNhanVien(NhanVien nv) {
		this.nv = nv;
		tentk = nv.getMaNV();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ

		Color color2 = Color.white;

		// Nội dung
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 221, 244));
		contentPane.setLayout(null);

		// pnBorder = new JTabbedPane(JTabbedPane.EAST);
		contentPane.setBorder(null);
		contentPane.setBackground(Color.WHITE);
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout());
		// pnBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(color2);

		pnThongTinTK = new JPanel();
		pnThongTinTK.setBackground(Color.WHITE);
		pnThongTinTK.setBorder(null);
		contentPane.add(pnThongTinTK);
		pnThongTinTK.setLayout(null);

		pnTTTK = new JPanel();
		pnTTTK.setBackground(Color.WHITE);
		pnTTTK.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Thông tin tài khoản", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnTTTK.setBounds(0, 0, 1923, 528);
		pnThongTinTK.add(pnTTTK);
		pnTTTK.setLayout(null);

		JLabel lbHoTen = new JLabel("Họ tên:");
		lbHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbHoTen.setBounds(30, 71, 154, 29);
		pnTTTK.add(lbHoTen);

		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbCMND.setBounds(30, 271, 154, 29);
		pnTTTK.add(lbCMND);

		JLabel lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbDiaChi.setBounds(30, 171, 154, 29);
		pnTTTK.add(lbDiaChi);

		txtHoTen = new JTextField(17);
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtHoTen.setBackground(new Color(255, 255, 255));
		txtHoTen.setBounds(194, 68, 600, 35);
		pnTTTK.add(txtHoTen);

		JLabel lbNgaySinh = new JLabel("Ngày sinh:");
		lbNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbNgaySinh.setBounds(866, 71, 154, 29);
		pnTTTK.add(lbNgaySinh);

		txtCMND = new JTextField(17);
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtCMND.setBackground(new Color(255, 255, 255));
		txtCMND.setBounds(194, 268, 600, 35);
		pnTTTK.add(txtCMND);

		txtDiaChi = new JTextField(17);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtDiaChi.setBackground(new Color(255, 255, 255));
		txtDiaChi.setBounds(194, 168, 600, 35);
		pnTTTK.add(txtDiaChi);

		JLabel lbGioiTinh = new JLabel("Giới tính:");
		lbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbGioiTinh.setBounds(866, 171, 154, 29);
		pnTTTK.add(lbGioiTinh);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbSDT.setBounds(866, 271, 154, 29);
		pnTTTK.add(lbSDT);

		txtSDT = new JTextField(17);
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtSDT.setBackground(new Color(255, 255, 255));
		txtSDT.setBounds(1030, 268, 600, 35);
		pnTTTK.add(txtSDT);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnCapNhat.setBackground(new Color(51, 204, 204));
		btnCapNhat.setBounds(760, 456, 159, 37);
		pnTTTK.add(btnCapNhat);

		cbGioiTinh = new JComboBox();
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbGioiTinh.setBounds(1030, 167, 600, 37);
		pnTTTK.add(cbGioiTinh);
		cbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cbGioiTinh.setBackground(new Color(233, 221, 244));

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBackground(Color.WHITE);
		lblHinhAnh.setBounds(1686, 71, 195, 229);
		pnTTTK.add(lblHinhAnh);

		btnLuu = new JButton("Lưu");
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuu.setBackground(new Color(51, 204, 204));
		btnLuu.setBounds(980, 456, 159, 37);
		pnTTTK.add(btnLuu);

		pnDoiMK = new JPanel();
		pnDoiMK.setBackground(Color.WHITE);
		pnDoiMK.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u0110\u1ED5i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnDoiMK.setBounds(0, 539, 1923, 418);
		pnThongTinTK.add(pnDoiMK);
		pnDoiMK.setLayout(null);

		lbMKC = new JLabel("Mật khẩu cũ:");
		lbMKC.setBounds(30, 58, 253, 29);
		lbMKC.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		pnDoiMK.add(lbMKC);

		lbMKM = new JLabel("Mật khẩu mới:");
		lbMKM.setBackground(Color.WHITE);
		lbMKM.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbMKM.setBounds(30, 145, 253, 29);
		pnDoiMK.add(lbMKM);

		lbXNMK = new JLabel("Xác nhận mật khẩu mới:");
		lbXNMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbXNMK.setBounds(30, 232, 253, 29);
		pnDoiMK.add(lbXNMK);

		btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDoiMK.setForeground(Color.WHITE);
		btnDoiMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnDoiMK.setBackground(new Color(51, 204, 204));
		btnDoiMK.setBounds(673, 319, 238, 37);
		pnDoiMK.add(btnDoiMK);

		txtMKC = new JPasswordField();
		txtMKC.setBackground(new Color(255, 255, 255));
		txtMKC.setBounds(293, 52, 1595, 34);
		pnDoiMK.add(txtMKC);

		txtMKM = new JPasswordField();
		txtMKM.setBackground(new Color(255, 255, 255));
		txtMKM.setBounds(293, 146, 1595, 34);
		pnDoiMK.add(txtMKM);

		txtXNMK = new JPasswordField();
		txtXNMK.setBackground(new Color(255, 255, 255));
		txtXNMK.setBounds(293, 233, 1595, 34);
		pnDoiMK.add(txtXNMK);
		rdbtnHienMK = new JRadioButton("Hiển thị mật khẩu");
		rdbtnHienMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHienMK.setBackground(Color.WHITE);
		rdbtnHienMK.setBounds(1682, 271, 206, 30);
		rdbtnHienMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnHienMK.isSelected()) {
					txtMKC.setEchoChar((char) 0);
					txtMKM.setEchoChar((char) 0);
					txtXNMK.setEchoChar((char) 0);
				} else if (rdbtnHienMK.isSelected() == false) {
					txtMKC.setEchoChar('*');
					txtMKM.setEchoChar('*');
					txtXNMK.setEchoChar('*');
				}

			}
		});
		pnDoiMK.add(rdbtnHienMK);

		btnLuuMK = new JButton("Lưu mật khẩu mới");
		btnLuuMK.setForeground(Color.WHITE);
		btnLuuMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuuMK.setBackground(new Color(51, 204, 204));
		btnLuuMK.setBounds(977, 319, 238, 37);
		pnDoiMK.add(btnLuuMK);
		txtNgaySinh = new JDateChooser();
		txtNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setPreferredSize(new Dimension(30, 35));
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		txtNgaySinh.setEnabled(false);
		txtNgaySinh.setBackground(new Color(233, 221, 244));
		txtNgaySinh.setBounds(1030, 71, 600, 35);
		pnTTTK.add(txtNgaySinh);
		btnLuuMK.setEnabled(false);

		hienThiTT();
		// getMa(maNV);

		btnLuu.setEnabled(false);

		dongMoNhapLieu(false);
		dongMoNhapLieuMK(false);

		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLuuMK.addActionListener(this);
		btnDoiMK.addActionListener(this);

	}

	private void dongMoNhapLieu(Boolean b) {
		txtHoTen.setEditable(b);
		cbGioiTinh.setEnabled(b);
		txtDiaChi.setEditable(b);
		txtSDT.setEditable(b);
		txtCMND.setEditable(b);
		txtNgaySinh.setEnabled(b);
	}

	private void dongMoNhapLieuMK(Boolean b) {
		txtMKC.setEditable(b);
		txtMKM.setEditable(b);
		txtXNMK.setEditable(b);

	}

	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);

		return image;
	}

	private void hienThiTT() {
		
			
				txtHoTen.setText(nv.getTenNV());
				boolean gt = nv.getGioiTinh();
				if (gt == true) {
					cbGioiTinh.setSelectedIndex(0);
				}
				if (gt == false) {
					cbGioiTinh.setSelectedIndex(1);
				}
				txtCMND.setText(nv.getCccd());
				txtDiaChi.setText(nv.getDiaChi());


				try {
					
						
					txtNgaySinh.setDate(nv.getNgaySinh());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSDT.setText(nv.getSdt());
			
			}
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnCapNhat)) {
			if (btnCapNhat.getText().equals("Cập nhật")) {
				dongMoNhapLieu(true);
				btnCapNhat.setText("Hủy");
				btnLuu.setEnabled(true);
			} else {
				dongMoNhapLieu(false);
				btnCapNhat.setText("Cập nhật");
				btnCapNhat.setEnabled(true);
				btnLuu.setEnabled(false);
			}
		}
		if (obj.equals(btnDoiMK)) {
			if (btnDoiMK.getText().equals("Đổi mật khẩu")) {
				dongMoNhapLieuMK(true);
				btnDoiMK.setText("Hủy");
				btnLuuMK.setEnabled(true);
			} else {
				dongMoNhapLieuMK(false);
				btnDoiMK.setText("Đổi mật khẩu");
				btnDoiMK.setEnabled(true);
				btnLuuMK.setEnabled(false);
			}
		}
		if (obj.equals(btnLuu)) {
			if (btnCapNhat.getText().equals("Hủy")) {
				if (validData()) {
					String maNV = nv.getMaNV();
					String tenNV = txtHoTen.getText().trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date ngaySinh = txtNgaySinh.getDate();
					String cmnd = txtCMND.getText().trim();
					String diaChi = txtDiaChi.getText().trim();
					String sdt = txtSDT.getText().trim();
					boolean gioiTinh = cbGioiTinh.getSelectedItem().toString().equals("Nam");
					HinhAnh ha = dao_hinhAnh.getHinhAnhByMaAnh(nv.getHinhAnh().getMaAnh());
					ChucVu cv = dao_chucVu.getChucVuTheoMa(nv.getChucVu().getMaChucVu());
					NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, diaChi, ngaySinh, sdt, cmnd, gioiTinh, cv, ha);
					if (dao_NhanVien.updateTTNhanVien(nv)) {
						JOptionPane.showMessageDialog(this, "Sửa thông tin thành công");
						dongMoNhapLieu(false);
						btnCapNhat.setText("Cập nhật");
						btnLuu.setEnabled(false);
					}
				}
			}
		}
		if (obj.equals(btnLuuMK)) {
			if (btnDoiMK.getText().equals("Hủy")) {
				if (validDataMK()) {
					String ten = nv.getMaNV();
					String mk = txtMKM.getText().trim();
					String cv = nv.getChucVu().getMaChucVu();
					if (dao_TaiKhoan.capNhapMatKhau(new TaiKhoan(ten, mk, cv))) {
						JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công");
						dongMoNhapLieuMK(false);
						btnDoiMK.setText("Đổi mật khẩu");
						btnLuuMK.setEnabled(false);
					}
				}
			}
		}
	}

	private boolean validData() {
		String tenNV = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (tenNV.length() == 0) {
			showMessage(txtHoTen, "Nhập tên nhân viên!");
			return false;
		}
		if (!tenNV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtHoTen, "Tên nhân viên bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtCMND, "Nhập CMND!");
			return false;
		}
		if (!cmnd.matches("^\\d{9,12}$") && Integer.parseInt(cmnd) < 0) {
			showMessage(txtCMND, "CMND là số nguyên (9-12 số)");
			return false;
		}
		if (sdt.length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^\\d{10}$")) {
			showMessage(txtSDT, "Số điện thoại là số nguyên (10 số)");
			return false;
		}
		if (diaChi.length() == 0) {
			showMessage(txtDiaChi, "Nhập địa chỉ!");
			return false;
		}

		return true;
	}

	private boolean validDataMK() {
		TaiKhoan tk = dao_TaiKhoan.getTaiKhoanTheoMa(tentk);
		String mkc = tk.getMatKhau().trim();
		String matKhauCu = txtMKC.getText().trim();
		String matKhauMoi = txtMKM.getText().trim();
		String xacNhanMK = txtXNMK.getText().trim();
		if (matKhauCu.length() == 0) {
			showMessage(txtMKC, "Nhập mật khẩu cũ!");
			return false;
		}
		if (!mkc.equals(matKhauCu)) {
			showMessage(txtMKC, "Mật khẩu cũ không đúng!");
			return false;
		}
		if (matKhauMoi.length() == 0 && matKhauMoi.length() <= 10) {
			showMessage(txtMKM, "Mật khẩu mới gồm 10 ký tự");
			return false;
		}
//		if (!matKhauMoi.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
//			showMessage(txtMKM,
//					"Mật khẩu có ít nhất 1 ký tự viết hoa,1 ký tự viết thường, 1 chữ số, 1 ký tự đặc biệt. Có 6 ký tự");
//			return false;
//		}
		if (xacNhanMK.length() == 0) {
			showMessage(txtXNMK, "Nhập xác nhân mật khẩu mới!");
			return false;
		}
		if (!matKhauMoi.equals(xacNhanMK)) {
			showMessage(txtMKC, "Xác nhận mật khẩu mới không đúng!");
			return false;
		}

		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
}
