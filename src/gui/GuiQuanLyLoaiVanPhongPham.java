package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.regex.Pattern;

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
import dao.DAO_QuanLyLoaiSach;
import dao.DAO_QuanLyLoaiVPP;
import entity.LoaiSach;
import entity.LoaiVanPhongPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GuiQuanLyLoaiVanPhongPham extends JFrame implements ActionListener, MouseListener {

	public static JPanel contentPane;
	private JTextField txtMaLoaiVPP;
	private JTextField txtVAT;
	private JTextField txtMoTa;
	private JTextField txtTenLoaiVPP;
	private JTextField txtTimKiem;
	private DefaultTableModel modelLoaiVPP;
	private JTable tblVPP;

	private ButtonGroup group;
	private JRadioButton radTenLoaiVPP;
	private JRadioButton radMaLoaiVPP;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnDatLai;
	private JPanel pnlBangLoaiVPP;
	private JPanel pnlTacVu;
	private JPanel pnlThongTinLoaiVPP;
	private JButton btnLuu;
	DAO_QuanLyLoaiVPP dao_QLyLoai = new DAO_QuanLyLoaiVPP();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiQuanLyLoaiVanPhongPham frame = new GuiQuanLyLoaiVanPhongPham();
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
	public GuiQuanLyLoaiVanPhongPham() {
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
		pnlTieuDe.setBounds(10, 10, 1891, 60);
		pnlTieuDe.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlTieuDe);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ LOẠI VĂN PHÒNG PHẨM");
		lblTieuDe.setBackground(new Color(51, 204, 255));
		pnlTieuDe.add(lblTieuDe);
		lblTieuDe.setFont(font1);
		lblTieuDe.setForeground(new Color(0, 204, 204));
		
		pnlThongTinLoaiVPP = new JPanel();
		pnlThongTinLoaiVPP.setBackground(new Color(255, 255, 255));
		
		pnlThongTinLoaiVPP.setBorder(BorderFactory.createTitledBorder("Thông tin loại văn phòng phẩm"));
		pnlThongTinLoaiVPP.setBounds(10, 80, 350, 855);
		contentPane.add(pnlThongTinLoaiVPP);
		pnlThongTinLoaiVPP.setLayout(null);
		
		JLabel lblMaLoaiVPP = new JLabel("Mã loại sách:");
		lblMaLoaiVPP.setBounds(10, 35, 319, 30);
		pnlThongTinLoaiVPP.add(lblMaLoaiVPP);
		lblMaLoaiVPP.setFont(font2);
		
		JLabel lblTenLoaiVPP = new JLabel("Tên loại sách :");
		lblTenLoaiVPP.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblTenLoaiVPP.setBounds(10, 175, 187, 30);
		pnlThongTinLoaiVPP.add(lblTenLoaiVPP);
		
		JLabel lblVAT = new JLabel("VAT :");
		lblVAT.setIgnoreRepaint(true);
		lblVAT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblVAT.setBounds(10, 495, 163, 30);
		pnlThongTinLoaiVPP.add(lblVAT);
		
		JLabel lblMoTa = new JLabel("Mô tả :");
		lblMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblMoTa.setBounds(10, 315, 163, 30);
		pnlThongTinLoaiVPP.add(lblMoTa);
		
		txtMaLoaiVPP = new JTextField();
		txtMaLoaiVPP.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMaLoaiVPP.setEditable(false);
		txtMaLoaiVPP.setBounds(20, 100, 319, 40);
		pnlThongTinLoaiVPP.add(txtMaLoaiVPP);
		txtMaLoaiVPP.setColumns(10);
		
		txtVAT = new JTextField();
		txtVAT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtVAT.setColumns(10);
		txtVAT.setBounds(20, 560, 319, 40);
		pnlThongTinLoaiVPP.add(txtVAT);
		
		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(20, 380, 320, 80);
		pnlThongTinLoaiVPP.add(txtMoTa);
		
		txtTenLoaiVPP = new JTextField();
		txtTenLoaiVPP.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtTenLoaiVPP.setColumns(10);
		txtTenLoaiVPP.setBounds(20, 240, 319, 40);
		pnlThongTinLoaiVPP.add(txtTenLoaiVPP);
		
		JPanel pnlTacVuCon = new JPanel();
		pnlTacVuCon.setBounds(10, 680, 330, 157);
		pnlThongTinLoaiVPP.add(pnlTacVuCon);
		pnlTacVuCon.setBackground(Color.white);
		pnlTacVuCon.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo : "));
		pnlTacVuCon.setLayout(null);
		
		txtTimKiem = new JTextField("Nhập thông tin cần tìm");
		txtTimKiem.setBounds(10, 95, 308, 40);
		pnlTacVuCon.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
				
				radTenLoaiVPP = new JRadioButton("Tên loại văn phòng phẩm");
				radTenLoaiVPP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				radTenLoaiVPP.setBackground(new Color(255, 255, 255));
				radTenLoaiVPP.setBounds(10, 32, 226, 23);
				pnlTacVuCon.add(radTenLoaiVPP);
		
				group = new ButtonGroup();
				group.add(radTenLoaiVPP);
				
				radMaLoaiVPP = new JRadioButton("Mã loại văn phòng phẩm");
				radMaLoaiVPP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				radMaLoaiVPP.setBackground(new Color(255, 255, 255));
				radMaLoaiVPP.setBounds(10, 58, 204, 23);
				pnlTacVuCon.add(radMaLoaiVPP);
				group.add(radMaLoaiVPP);
				
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
				
		
		pnlTacVu = new JPanel();
		pnlTacVu.setBackground(Color.white);
		pnlTacVu.setBounds(370, 855, 1530, 80);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ:"));
		contentPane.add(pnlTacVu);
		pnlTacVu.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(140, 21, 180, 48);
		pnlTacVu.add(btnThem);
		btnThem.setFont(font2);
		btnThem.setIcon(new ImageIcon(GuiQuanLyLoaiVanPhongPham.class.getResource("/image/TacVu_Them1.png")));
		btnThem.setBackground(Color.WHITE);
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnSua.setBounds(410, 21, 180, 48);
		pnlTacVu.add(btnSua);
		btnSua.setIcon(new ImageIcon(GuiQuanLyLoaiVanPhongPham.class.getResource("/image/TacVu_Sua.png")));
		btnSua.setBackground(Color.WHITE);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnXoa.setBounds(680, 21, 180, 48);
		pnlTacVu.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(GuiQuanLyLoaiVanPhongPham.class.getResource("/image/TacVu_Xoa1.png")));
		btnXoa.setBackground(Color.WHITE);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuu.setBounds(950, 21, 180, 48);
		pnlTacVu.add(btnLuu);
		btnLuu.setIcon(new ImageIcon(GuiQuanLyLoaiVanPhongPham.class.getResource("/image/TacVu_Luu.png")));
		btnLuu.setBackground(Color.WHITE);
		
		btnDatLai = new JButton("Đặt lại");
		btnDatLai.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnDatLai.setBounds(1220, 21, 180, 48);
		pnlTacVu.add(btnDatLai);
		btnDatLai.setIcon(new ImageIcon(GuiQuanLyLoaiVanPhongPham.class.getResource("/image/TacVu_DatLai.png")));
		btnDatLai.setBackground(Color.WHITE);;
		
		
		pnlBangLoaiVPP = new JPanel();
		pnlBangLoaiVPP.setBounds(370, 80, 1531, 765);
		contentPane.add(pnlBangLoaiVPP);
		pnlBangLoaiVPP.setBorder(BorderFactory.createTitledBorder("Bảng thông tin loại văn phòng phẩm"));
		pnlBangLoaiVPP.setLayout(null);
		
		

		pnlBangLoaiVPP.setBackground(Color.white);;
		modelLoaiVPP = new DefaultTableModel();
		modelLoaiVPP.addColumn("Mã loại văn phòng phẩm");
		modelLoaiVPP.addColumn("Tên loại văn phòng phẩm");
		modelLoaiVPP.addColumn("Mô tả");
		modelLoaiVPP.addColumn("VAT");
		tblVPP = new JTable(modelLoaiVPP);
		tblVPP.setBackground(new Color(153, 204, 255));
		JScrollPane jScrollPane = new JScrollPane(tblVPP);
		jScrollPane.setBounds(20, 20, 1489, 728);
		JTableHeader tbHeaderVPP = tblVPP.getTableHeader();
		tbHeaderVPP.setFont(font2);
		tbHeaderVPP.setBackground(new Color(51, 204, 204));
		pnlBangLoaiVPP.setLayout(null);
		tblVPP.setFont(font2);
		tblVPP.setRowHeight(35);
		pnlBangLoaiVPP.add(jScrollPane);
		
		try {
			ConnectDB.getInstance().connect();
			DocDuLieuDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Xử lý
		btnDatLai.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		txtTimKiem.addKeyListener((KeyListener) new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        String tuKhoa = txtTimKiem.getText().trim();
		        timKiem(tuKhoa);
		    }
		}); 
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
				btnLuu.setEnabled(true);
				txtMaLoaiVPP.setText(dao_QLyLoai.TuPhatSinhMaLoaiVPP());
			} else {
				tblVPP.addMouseListener(this);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnDatLai.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaLoaiVPP.setText("");
				xoaRong();
				dongMoNhapLieu(false);
				modelLoaiVPP.setRowCount(0);
				DocDuLieuDatabase();
			}
		}
		if (o.equals(btnXoa)) {
			xoa();
		}if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblVPP.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnDatLai.setEnabled(true);
					btnLuu.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách lọai sách, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnDatLai.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaLoaiVPP.setText("");
				xoaRong();
			}
		}if (o.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if(validData()) {
					themLoaiVPP();
				}
				
				
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maVPP = txtMaLoaiVPP.getText();
					String tenVPP = txtTenLoaiVPP.getText();
					String moTa = txtMoTa.getText();
					double vat = Double.parseDouble(txtVAT.getText());

					if (dao_QLyLoai.updateLoaiVPP(new LoaiVanPhongPham(maVPP, tenVPP, moTa, vat))){
						modelLoaiVPP.setRowCount(0);
						DocDuLieuDatabase();
						JOptionPane.showMessageDialog(this, "Sửa thông tin loại sách thành công");
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
	private boolean validData() {
		String tenLoai  = txtTenLoaiVPP.getText().trim();
		String moTa  = txtMoTa.getText().trim();
		String vat = txtVAT.getText().trim();
		double soVAT = Double.parseDouble(vat);
		if (tenLoai.length() == 0) {
			showMessage(txtTenLoaiVPP, "Nhập tên loại văn phòng phẩm!");
			return false;
		}
		if (!tenLoai.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenLoaiVPP, "Tên loại văn phòng phẩm bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (moTa.length() == 0) {
			showMessage(txtMoTa, "Nhập mô tả!");
			return false;
		}
		if (!moTa.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtMoTa, "Mô tả bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (vat.length() == 0) {
			showMessage(txtVAT, "Nhập thuế vat!");
			return false;
		}
		if (soVAT < 0 | soVAT > 1) {
			showMessage(txtVAT, "Thuế giá trị gia tăng đúng là từ [0-1]");
			return false;
		}
		
		return true;
	}
	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
	public void moNutThem() {
		dongMoNhapLieu(true);
		btnLuu.setEnabled(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnDatLai.setEnabled(true);
		btnThem.setText("Hủy");
		tblVPP.removeMouseListener(this);
		xoaRong();
	}
	private void dongMoNhapLieu(Boolean b) {
		txtMaLoaiVPP.setEditable(false);
		txtMaLoaiVPP.setEnabled(false);
		txtTenLoaiVPP.setEditable(b);
		txtMoTa.setEditable(b);
		txtVAT.setEditable(b);
	}
	public void DocDuLieuDatabase() {
		dao_QLyLoai = new DAO_QuanLyLoaiVPP();
		//tblCV.setRowHeight(25);
		for(LoaiVanPhongPham ls : dao_QLyLoai.getALLLoaiVPP()) {
			modelLoaiVPP.addRow(new Object[] {ls.getMaLoaiVPP(), ls.getTenLoaiVPP(), ls.getMoTa(), ls.getVat()});
		}
	}
	
	// phát sinh mã tự động
	public void xoaRong() {
		txtMaLoaiVPP.setText(dao_QLyLoai.TuPhatSinhMaLoaiVPP());
		txtTimKiem.setText("");
		txtTenLoaiVPP.setText("");
		txtMoTa.setText("");
		txtVAT.setText("");
		txtTenLoaiVPP.requestFocus();
		txtTenLoaiVPP.setEditable(true);
		txtMoTa.setEditable(true);
		txtVAT.setEditable(true);
		btnLuu.setEnabled(false);
		btnSua.setEnabled(true);
	}
	
	private void themLoaiVPP() {
		String ten = txtTenLoaiVPP.getText();
		String newMa = txtMaLoaiVPP.getText().trim();
		
		String mota = txtMoTa.getText();
		Double vat = Double.parseDouble(txtVAT.getText());
		LoaiVanPhongPham ls = new LoaiVanPhongPham(newMa, ten, mota, vat);
		if (dao_QLyLoai.addLoaiVPP(ls)) {
			modelLoaiVPP.addRow(new Object[] { ls.getMaLoaiVPP(), ls.getTenLoaiVPP(), ls.getMoTa(), ls.getVat()});	
			JOptionPane.showMessageDialog(this, "Thêm văn phòng phẩm thành công");
			xoaRong();
		}else {
			JOptionPane.showMessageDialog(this, "Không thành công");
		}
	}
	
	public void xoa() {
		int row = tblVPP.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn loại sách cần xoá");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa loại sách này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = tblVPP.getSelectedRow();
				dao_QLyLoai.deleteLoaiVPP(modelLoaiVPP.getValueAt(tblVPP.getSelectedRow(), 0).toString());
				modelLoaiVPP.removeRow(index);
				xoaRong();
			}
		}
	}
	
	private void timKiem(String tuKhoa) {
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelLoaiVPP);
	    tblVPP.setRowSorter(sorter);

	    if (tuKhoa.isEmpty()) {
	        sorter.setRowFilter(null);
	    } else {
	        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa), 1, 3);
	        sorter.setRowFilter(filter);
	    }
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = 	tblVPP.getSelectedRow();
		txtMaLoaiVPP.setText(tblVPP.getValueAt(row, 0).toString());
		txtTenLoaiVPP.setText(tblVPP.getValueAt(row, 1).toString());
		txtMoTa.setText(tblVPP.getValueAt(row, 2).toString());
		txtVAT.setText(tblVPP.getValueAt(row, 3).toString());
		txtMoTa.setEditable(false);
		txtVAT.setEditable(false);
		txtTenLoaiVPP.setEditable(false);
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

