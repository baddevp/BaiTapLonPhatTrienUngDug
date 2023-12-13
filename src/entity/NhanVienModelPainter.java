package entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.DAO_HinhAnh;
import gui.GuiQuanLySanPham;


public class NhanVienModelPainter extends JPanel implements javax.swing.ListCellRenderer<NhanVien> {
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private JLabel maSach;
	private JLabel tenSach;
	private JLabel tacGia;
	private JLabel loaiSach;
	private JPanel anhSP;
	private JLabel ngayNhap;
	private JLabel giaBan;
	private JLabel soLuong;
	private JLabel moTa;
	private JPanel pnl1;
	private JPanel pnl2;
	private JLabel lblAnh;
	DAO_HinhAnh dao_anh = new DAO_HinhAnh();
	public NhanVienModelPainter() {
		// TODO Auto-generated constructor stub
		this.setSize(1850, 260);
		this.setBackground(new Color(255, 255, 255));
		Dimension preferredSize = new Dimension(750, 30);

		
		pnl1 = new JPanel(new GridLayout(5,1));
		pnl1.setSize(750, 260);
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
		
		moTa = new JLabel("Thông tin sách:");
		moTa.setFont(new Font("Times New Roman", Font.PLAIN, 24));
//		moTa.setBounds(300, 215, 725, 35);
		pnl1.add(moTa, BorderLayout.WEST);
	
		
		pnl2 = new JPanel(new GridLayout(5,1));
		pnl2.setSize(750, 260);
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
		
		
		anhSP = new JPanel(new BorderLayout());
		anhSP.setBackground(new Color(200, 200, 200));
		anhSP.setPreferredSize(new Dimension(200, 200));
		lblAnh = new JLabel();
		lblAnh.setPreferredSize(new Dimension(200, 200));
		anhSP.add(lblAnh);
		this.add(anhSP, BorderLayout.EAST );
	}

	private String strTrangThai(NhanVien s) {
		// TODO Auto-generated method stub
		if(s.getGioiTinh() == true) {
			return "Nữ";
		
		}else {
			return "Nam";
		}
		
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends NhanVien> list, NhanVien value, int index,
			boolean isSelected, boolean cellHasFocus) {
		maSach.setText  ("Mã nhân viên :                         " +  value.getMaNV());//pnl 1
        tenSach.setText ("Tên nhân viên :                        " +  value.getTenNV());//1
        tacGia.setText  ("Ngày sinh :                              " + dtf.format(value.getNgaySinh()));//1
        giaBan.setText  ("SDT:                                       " + value.getSdt());
        moTa.setText    ("CCCD :                                   " + value.getCccd());//1
        soLuong.setText ("Chức vụ :                               " + value.getChucVu().getTenChucVu());
        loaiSach.setText("Giới tính :                                " + strTrangThai(value));//1
        ngayNhap.setText("Ngày vào làm :                       " + dtf.format(value.getNgayVaoLam()));
        try {
        	HinhAnh ha = dao_anh.getHinhAnhByMaAnh(value.getMaNV());
        	ImageIcon icon = new ImageIcon(NhanVienModelPainter.class.getResource("/image/"+ ha.getUrl()));
        	lblAnh.setIcon(icon);
		} catch (Exception e) {
			// TODO: handle exception
			ImageIcon icon = new ImageIcon(NhanVienModelPainter.class.getResource("/image/NhanVien1.png"));
            lblAnh.setIcon(icon);
		}
        
        if (isSelected) {
            setBackground(new Color(51, 204, 204));
            pnl1.setBackground(new Color(51, 204, 204));
            pnl2.setBackground(new Color(51, 204, 204));
            maSach.setForeground(Color.white);
	        tenSach.setForeground(Color.white);
	        tacGia.setForeground(Color.white);
	        giaBan.setForeground(Color.white);
	        moTa.setForeground(Color.white);  
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
	        moTa.setForeground(Color.black);	   
	        soLuong.setForeground(Color.black);
	        loaiSach.setForeground(Color.black);
	        ngayNhap.setForeground(Color.black);
        }
        setEnabled(list.isEnabled());
        setOpaque(true);
        if(cellHasFocus) {
        	setBorder(BorderFactory.createLineBorder(Color.red));
        }else if(!cellHasFocus) {
        	setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        }
	return this;

	}

}
