package entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

public class KhachHangModelPainter extends JPanel implements ListCellRenderer<KhachHang> {
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private JLabel maSach;
	private JLabel tenSach;
	private JLabel tacGia;
	private JLabel loaiSach;

	private JLabel ngayNhap;
	private JLabel giaBan;
	private JLabel soLuong;

	private JPanel pnl1;
	private JPanel pnl2;

	public KhachHangModelPainter() {
		// TODO Auto-generated constructor stub
		this.setSize(1850, 200);
		this.setBackground(new Color(255, 255, 255));
		Dimension preferredSize = new Dimension(750, 30);

		pnl1 = new JPanel(new GridLayout(5, 1));
		pnl1.setSize(750, 200);
		pnl1.setBackground(Color.white);
		this.add(pnl1, BorderLayout.WEST);
		maSach = new JLabel("Mã sách:");
		maSach.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		maSach.setBounds(300, 15, 725, 35);
		maSach.setPreferredSize(preferredSize);
		pnl1.add(maSach, BorderLayout.WEST);

		tenSach = new JLabel(" sách:");
		tenSach.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		tenSach.setBounds(300, 65, 725, 35);
		pnl1.add(tenSach, BorderLayout.WEST);

		tacGia = new JLabel("Thông tin sách:");
		tacGia.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		tacGia.setBounds(300, 115, 725, 35);
		pnl1.add(tacGia, BorderLayout.WEST);

		loaiSach = new JLabel("Thông tin sách:");
		loaiSach.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		loaiSach.setBounds(300, 165, 725, 35);
		pnl1.add(loaiSach, BorderLayout.WEST);

		pnl2 = new JPanel(new GridLayout(5, 1));
		pnl2.setSize(750, 200);
		pnl2.setBackground(Color.white);
		this.add(pnl2, BorderLayout.CENTER);

		ngayNhap = new JLabel("Thông tin sách:");
		ngayNhap.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		ngayNhap.setBounds(1115, 65, 725, 35);//65
		ngayNhap.setPreferredSize(preferredSize);
		pnl2.add(ngayNhap, BorderLayout.WEST);

		giaBan = new JLabel("Thông tin sách:");
		giaBan.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		giaBan.setBounds(1115, 115, 725, 35);//115
		pnl2.add(giaBan, BorderLayout.WEST);

		soLuong = new JLabel("Thông tin sách:");
		soLuong.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		soLuong.setBounds(1115, 165, 725, 35);//165
		pnl2.add(soLuong, BorderLayout.WEST);

		setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
		setOpaque(true);

	}


	@Override
	public Component getListCellRendererComponent(JList<? extends KhachHang> list, KhachHang value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
				maSach.setText  ("Mã khách hàng :                         " + value.getMaKH());
				tenSach.setText ("Tên khách hàng :                        " + value.getTenKH());
				tacGia.setText  ("Số điện thoại :                             " + value.getSdt());
				loaiSach.setText("Địa chỉ :                                      " + value.getDiaChi());
				ngayNhap.setText("Ngày lập :                                    " + dtf.format(value.getNgayLap()));
				soLuong.setText ("Email :                                         " + value.getEmail());
				giaBan.setText  ("Điểm tích lũy :                             " + value.getDiemTL());
				
				if (isSelected) {
					setBackground(new Color(51, 204, 204));
					pnl1.setBackground(new Color(51, 204, 204));
					pnl2.setBackground(new Color(51, 204, 204));
					maSach.setForeground(Color.white);
					tenSach.setForeground(Color.white);
					tacGia.setForeground(Color.white);
					giaBan.setForeground(Color.white);
					soLuong.setForeground(Color.white);
					loaiSach.setForeground(Color.white);
					ngayNhap.setForeground(Color.white);
				} else {
					setBackground(Color.white);
					pnl1.setBackground(Color.white);
					pnl2.setBackground(Color.white);
					maSach.setForeground(Color.black);
					tenSach.setForeground(Color.black);
					tacGia.setForeground(Color.black);
					giaBan.setForeground(Color.black);
					soLuong.setForeground(Color.black);
					loaiSach.setForeground(Color.black);
					ngayNhap.setForeground(Color.black);
				}
				setEnabled(list.isEnabled());
				setOpaque(true);
				if (cellHasFocus) {
					setBorder(BorderFactory.createLineBorder(Color.red));
				} else if (!cellHasFocus) {
					setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
				}
				return this;
	}
}
