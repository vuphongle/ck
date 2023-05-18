package UI;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVienDao;
import dao.PhongBanDao;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_GUI extends JFrame implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;

	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien = new DefaultTableModel();

	private JTextField txtMaNV;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtTuoi;
	private JTextField txtTienLuong;
	private JTextField txtTim;
	private JButton bttTim;
	private JButton bttThem;
	private JButton bttXoa;
	private JButton bttLuu;
	private JButton bttSua;
	//private JButton bttXoaTrang;
	
	private JCheckBox chkNu,chkNam;
	private JComboBox<String> cboPhongBan;
	private NhanVienDao nvdao = new NhanVienDao();
	private PhongBanDao pbdao = new PhongBanDao();
	int edit;
	public NhanVien_GUI() {

		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		setTitle("^-^");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel lblTieuDe;
		lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);

		Box b = Box.createVerticalBox();

		Box b11, b1, b2, b3, b4;
		JLabel lblMaNV, lblHo, lblTuoi, lblPhai, lblTienLuong, lblTim = null;
		;

		b.add(b11 = Box.createHorizontalBox());
		b11.add(lblTieuDe);
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("Mã nhân viên:   "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblHo = new JLabel("Họ "));
		b2.add(txtHo = new JTextField());
		b2.add(new JLabel("Tên nhân viên: "));
		b2.add(txtTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuổi: "));
		b3.add(txtTuoi = new JTextField());
		b3.add(lblPhai = new JLabel("Phái: "));
		b3.add(chkNam = new JCheckBox("Nam"));
		b3.add(chkNu = new JCheckBox("Nữ"));
		ButtonGroup btgr = new ButtonGroup();
		chkNam.setEnabled(true);
		btgr.add(chkNam);
		btgr.add(chkNu);
		
		chkNam.setSelected(true);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTienLuong = new JLabel("Tiền lương: "));
		b4.add(txtTienLuong = new JTextField());
		b4.add(new JLabel("Phòng Ban: "));
		
		//Tạo và đổ dữ liệu vào comboBox
		b4.add(cboPhongBan = new JComboBox<String>());
		cboPhongBan.setEditable(true);		
		for (PhongBan pb : pbdao.getallphongban()) {
			cboPhongBan.addItem(pb.getMaPhong());
		}

		lblHo.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
		lblTienLuong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());
		cboPhongBan.setPreferredSize(lblMaNV.getPreferredSize());
		b.add(Box.createVerticalStrut(10));
		add(b, BorderLayout.NORTH);

		String[] colHeader = { "Mã NV", "Họ NV", "Tên NV", "Tuổi", "Phái", "Tiền Lương","Phòng Ban"  };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		add(new JScrollPane(tableNhanVien), BorderLayout.CENTER);

		JPanel p = new JPanel();
		add(p, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		p.add(pnlLeft = new JPanel());
		p.add(pnlRight = new JPanel());

		pnlLeft.add(lblTim = new JLabel("Nhập mã số cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(bttTim = new JButton("Tìm"));

		pnlRight.add(bttThem = new JButton("Thêm"));
		pnlRight.add(bttLuu = new JButton("Lưu"));
		pnlRight.add(bttSua = new JButton("Sửa"));
		pnlRight.add(bttXoa = new JButton("Xóa"));

		loaddata();
		bttTim.addActionListener(this);
		bttThem.addActionListener(this);
		bttXoa.addActionListener(this);
		bttLuu.addActionListener(this);
		bttSua.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		bttLuu.setEnabled(false);
	}

	public static void main(String[] args) {
		new NhanVien_GUI().setVisible(true);
	}
	private void loaddata() {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : nvdao.getAllNhanVien()) {
			String phai;
			if (nv.getPhai()) {
				phai = "Nam";
			} else {
				phai ="Nữ";
			}
			String[] row= {nv.getMaNV(),nv.getHo(),nv.getTen(),String.valueOf(nv.getTuoi()),phai,
					String.valueOf(nv.getTienLuong()),nv.getPhong().getMaPhong()};
			modelNhanVien.addRow(row);
					}	
			}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(bttTim)) {
			timkiem();
		}
		if (o.equals(bttThem)) {
			edit =1;
			if (bttThem.getText().equals("Thêm")) {
				bttThem.setText("Huỷ");
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttSua.setEnabled(false);
			}
			else if (bttThem.getText().equals("Huỷ")) {
				bttThem.setText("Thêm");
				bttLuu.setEnabled(false);
				bttXoa.setEnabled(true);
				bttSua.setEnabled(true);
			}
		}
		else if (o.equals(bttLuu)) {
				updateNV();
			
		}
		else if (o.equals(bttXoa)) {
			int r = tableNhanVien.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
			} else {
				try {
					int tb = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?","Cảnh báo",JOptionPane.YES_NO_OPTION);
					if (tb == JOptionPane.YES_OPTION) {
						modelNhanVien.removeRow(r);
						String manv = txtMaNV.getText();
						nvdao.DeleteNV(manv);
						JOptionPane.showMessageDialog(this, "Xoá thành công");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		}
		else if (o.equals(bttSua)) {
			edit =2;
			
			if (bttSua.getText().equals("Sửa")) {
				bttSua.setText("Huỷ");
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttThem.setEnabled(false);
				txtMaNV.setEditable(false);
			}
			else if (bttSua.getText().equals("Huỷ")) {
				bttSua.setText("Sửa");
				bttLuu.setEnabled(false);
				bttXoa.setEnabled(true);
				bttThem.setEnabled(true);
				txtMaNV.setEditable(true);		
				}
		}

	}

	private void updateNV() {
		// TODO Auto-generated method stub
		if (edit == 1) {
			// Thêm nhân viên
			if (txtHo.getText().equals("") ||txtMaNV.getText().equals("")||txtTen.getText().equals("")||
					txtTienLuong.getText().equals("")||txtTuoi.getText().equals("")||!chkNam.isSelected() && !chkNu.isSelected()) {
				JOptionPane.showMessageDialog(this, "Bạn chưa điền đủ thông tin");
				return;
			}
			
			String manv = txtMaNV.getText();
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String phai;
			boolean kiemtra=false;
			for (int i = 0; i < modelNhanVien.getRowCount(); i++) {
				if (modelNhanVien.getValueAt(i, 0).equals(manv)) {
					JOptionPane.showMessageDialog(this, "Mã Bị Trùng Vui Lòng Nhập Lại");
					return;
				}
			}
			if (chkNam.isSelected()) {
				phai ="Nam";
				kiemtra = true;
			} else if (chkNu.isSelected()) {
				phai = "Nữ";
				kiemtra=false;
			} else {
				JOptionPane.showMessageDialog(this, "chưa chọn giới tính");
				return;
			}
		
			
			float luong = Float.parseFloat(txtTienLuong.getText());
			String phongban = cboPhongBan.getSelectedItem().toString();
			String[] row= {manv,ho,ten,String.valueOf(tuoi),phai,String.valueOf(luong),phongban};
			NhanVien nv = new NhanVien(manv, ho, ten, tuoi, kiemtra, phongban, luong, new PhongBan(phongban));
			
			try {
					nvdao.addNhanVien(nv);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					modelNhanVien.addRow(row);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				//JOptionPane.showMessageDialog(this, "maLop Phai duy nhat");
			}
		}
		if (edit ==2) {
			String manv = txtMaNV.getText();
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			int tuoi = Integer.parseInt(txtTuoi.getText());
			String phai;
			boolean kiemtra=false;
			if (chkNam.isSelected()) {
				phai ="Nam";
				kiemtra = true;
			} else if (chkNu.isSelected()) {
				phai = "Nữ";
				kiemtra=false;
			} else {
				JOptionPane.showMessageDialog(this, "chưa chọn giới tính");
				return;
			}
			float luong = Float.parseFloat(txtTienLuong.getText());
			String phongban = cboPhongBan.getSelectedItem().toString();
			NhanVien nv = new NhanVien(manv, ho, ten, tuoi, kiemtra, phongban, luong, new PhongBan(phongban));
			nvdao.updateNhanVien(nv);
			loaddata();
			JOptionPane.showMessageDialog(this, "Sửa Thành Công");
		}	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		txtMaNV.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtHo.setText(modelNhanVien.getValueAt(row, 1).toString());
		txtTen.setText(modelNhanVien.getValueAt(row, 2).toString());
		txtTuoi.setText(modelNhanVien.getValueAt(row, 3).toString());
		chkNam.setSelected(modelNhanVien.getValueAt(row, 4).toString().equalsIgnoreCase("Nam") ? true : false);
		chkNu.setSelected(modelNhanVien.getValueAt(row, 4).toString().equalsIgnoreCase("Nữ") ? true : false);
		txtTienLuong.setText(modelNhanVien.getValueAt(row, 5).toString());
		cboPhongBan.setSelectedItem(modelNhanVien.getValueAt(row, 6).toString());
	
		
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
	public int timkiemma(String ma) {
		List<NhanVien> nv1= nvdao.getAllNhanVien();
		for (NhanVien nhanvien : nv1) {
			if (nhanvien.getMaNV().equals(ma)) {
				return nv1.indexOf(nhanvien);
			}
		}
		return -1;
	}
	private void timkiem() {
		String ma = txtTim.getText();
		int index = timkiemma(ma);
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "Không tim thấy");
		} else {
			tableNhanVien.setRowSelectionInterval(index, index);
		}
	}
}
