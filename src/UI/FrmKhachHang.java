package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_DAO;
import entity.KhachHang;
import entity.Regex;

public class FrmKhachHang extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */

	private JTable table;
	private DefaultTableModel model;
	private String[] cols = { "STT", "Mã khách hàng", "Tên", "Giới tính", "Địa chỉ", "Số điện thoại" };
	private JTextField txtTen;
	private JTextField txtMa;
	private Font font;
	private JTextField txtDiaChi;
	private JTextField txtSdt;
	private JComboBox<String> cbTim;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
	private KhachHang_DAO kh;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JLabel lblNu;
	private KhachHang_DAO khDAO = new KhachHang_DAO();
	private Set<String> generatedCodes = new HashSet<>();
	private Regex regex = new Regex();

	private static final long serialVersionUID = 1L;

	public FrmKhachHang() {
		setLayout(null);
		JPanel panelNorth = new JPanel();
		add(panelNorth);
		panelNorth.setBounds(0, 0, 970, 50);
		JLabel lblTitle;
		panelNorth.add(lblTitle = new JLabel("Quản lý thông tin khách hàng"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitle.setForeground(new Color(0, 40, 255));

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setBounds(5, 50, 970, 290);

		// Bảng
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.setBackground(Color.pink);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setPreferredSize(new Dimension(950, 270));

		// Thông tin khách hàng
		JPanel panelSouth = new JPanel();
		panelSouth.setBounds(7, 340, 580, 200);
		panelSouth.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		TitledBorder titledthongtin = (TitledBorder) panelSouth.getBorder();
		titledthongtin.setTitleColor(Color.blue);
		panelSouth.setLayout(null);

		JLabel lblMa = new JLabel("Mã khách hàng:");
		panelSouth.add(lblMa);
		lblMa.setBounds(20, 50, 150, 20);
		add(panelSouth);
		font = new Font("Arial", Font.PLAIN, 16);
		lblMa.setFont(font);
		panelSouth.add(txtMa = new JTextField());
		txtMa.setBounds(150, 50, 150, 23);
		txtMa.setFont(font);
		txtMa.setEditable(false);
		loadMa();

		JLabel lblTen = new JLabel("Tên:");
		panelSouth.add(lblTen);
		lblTen.setBounds(320, 50, 50, 20);
		lblTen.setFont(font);
		panelSouth.add(txtTen = new JTextField());
		txtTen.setBounds(370, 50, 190, 23);
		txtTen.setFont(font);

		JLabel lblPhai = new JLabel("Giới tính:");
		panelSouth.add(lblPhai);
		lblPhai.setBounds(20, 100, 150, 20);
		lblPhai.setFont(font);
		rdoNam = new JRadioButton();
		panelSouth.add(rdoNam);
		rdoNam.setBounds(100, 100, 25, 23);
		JLabel lblNam = new JLabel("Nam");
		panelSouth.add(lblNam);
		lblNam.setFont(font);
		lblNam.setBounds(130, 100, 50, 20);

		rdoNu = new JRadioButton();
		panelSouth.add(rdoNu);
		rdoNu.setBounds(200, 100, 25, 23);
		lblNu = new JLabel("Nữ");
		panelSouth.add(lblNu);
		lblNu.setFont(font);
		lblNu.setBounds(230, 100, 50, 20);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdoNam);
		bg.add(rdoNu);
		rdoNam.setSelected(true);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		panelSouth.add(lblSdt);
		lblSdt.setBounds(280, 100, 100, 20);
		lblSdt.setFont(font);
		panelSouth.add(txtSdt = new JTextField());
		txtSdt.setFont(font);
		txtSdt.setBounds(390, 100, 170, 23);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		panelSouth.add(lblDiaChi);
		lblDiaChi.setBounds(20, 150, 150, 20);
		lblDiaChi.setFont(font);
		panelSouth.add(txtDiaChi = new JTextField());
		txtDiaChi.setFont(font);
		txtDiaChi.setBounds(90, 150, 470, 23);

		// chức năng
		JPanel panelChucNang = new JPanel();
		panelChucNang.setLayout(null);
		panelChucNang.setBounds(595, 340, 375, 200);
		panelChucNang.setBorder(BorderFactory.createTitledBorder("Các chức năng"));
		TitledBorder titledchucnang = (TitledBorder) panelChucNang.getBorder();
		titledchucnang.setTitleColor(Color.blue);
		add(panelChucNang);

		JLabel lblTim = new JLabel("Chọn loại muốn tìm:");
		panelChucNang.add(lblTim);
		lblTim.setBounds(20, 30, 150, 20);
		lblTim.setFont(font);
		cbTim = new JComboBox<String>();
		cbTim.addItem("Tìm theo mã");
		cbTim.addItem("Tìm theo số điện thoại");
		cbTim.addItem("Tìm theo tên");
		panelChucNang.add(cbTim);
		cbTim.setBounds(175, 30, 180, 23);
		cbTim.setFont(new Font("Arial", Font.PLAIN, 15));

		JLabel lblNhapTT = new JLabel("Nhập thông tin:");
		panelChucNang.add(lblNhapTT);
		lblNhapTT.setBounds(20, 70, 150, 20);
		lblNhapTT.setFont(font);
		panelChucNang.add(txtTim = new JTextField());
		txtTim.setFont(font);
		txtTim.setBounds(140, 65, 220, 23);

		panelChucNang.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.setIcon(new ImageIcon("image\\tim.png"));
		btnTim.setFont(font);
		btnTim.setBounds(120, 100, 140, 25);

		panelChucNang.add(btnThem = new JButton("Thêm"));
		btnThem.setIcon(new ImageIcon("image\\add-icon.png"));
		btnThem.setFont(font);
		btnThem.setBounds(20, 135, 140, 25);

		panelChucNang.add(btnXoa = new JButton("Xóa"));
		btnXoa.setIcon(new ImageIcon("image\\delete-icon.png"));
		btnXoa.setFont(font);
		btnXoa.setForeground(Color.red);
		btnXoa.setBounds(20, 165, 140, 25);

		panelChucNang.add(btnXoaTrang = new JButton("Xóa trắng"));
		btnXoaTrang.setIcon(new ImageIcon("image\\icons8_x_24px.png"));
		btnXoaTrang.setFont(font);
		btnXoaTrang.setBounds(220, 135, 140, 25);

		panelChucNang.add(btnSua = new JButton("Sửa"));
		btnSua.setIcon(new ImageIcon("image\\sua.png"));
		btnSua.setFont(font);
		btnSua.setBounds(220, 165, 140, 25);
		loadData();

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				them();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			xoaTrang();
		} else if (o.equals(btnTim)) {
			tim();
		} else if (o.equals(btnSua)) {
			sua();
		}
	}

	public void loadData() {
		int i = 0;
		kh = new KhachHang_DAO();
		for (KhachHang x : kh.getAllKhachHang()) {
			i++;
			Object[] row = { i, x.getMaKH(), x.getTenKH(), x.getGioiTinh(), x.getDiaChi(), x.getSdt() };
			model.addRow(row);
		}
	}

	private String generateRandomCode() {
		String prefix = "KH";
		int maxNumber = 999999;
		int randomNum = new Random().nextInt(maxNumber);
		String suffix = String.format("%06d", randomNum);
		return prefix + suffix;
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtDiaChi.setText("");
		txtSdt.setText("");
		txtTen.setText("");
		txtTim.setText("");
	}

	private void them() throws SQLException {
		loadMa();

		String ma = txtMa.getText();
		String ten = txtTen.getText();
		String diaChi = txtDiaChi.getText();
		String sdt = txtSdt.getText();
		String phai = null;
		if (rdoNam.isSelected() == true)
			phai = "Nam";
		else
			phai = "Nu";
		KhachHang khsdt = khDAO.getKHTheoSDT(sdt);
		if (!regex.kiemTraRong(txtDiaChi) && !regex.kiemTraRong(txtTen) && !regex.kiemTraRong(txtSdt)) {
			if (!regex.RegexTen(txtTen) && !regex.RegexSDT(txtSdt)) {
				if (khsdt == null) {
					KhachHang kh = new KhachHang(ma, ten, phai, diaChi, sdt);
					if (khDAO.createKH(kh)) {
						model.addRow(new Object[] { model.getRowCount() + 1, kh.getMaKH(), kh.getTenKH(),
								kh.getGioiTinh(), kh.getDiaChi(), kh.getSdt() });
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						xoaTrang();
					} else {
						them();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Một số điện thoại chỉ được đăng ký cho một khách hàng");
				}
			}
		}
	}

	private void loadMa() {
		String code;
		do {
			code = generateRandomCode();
		} while (generatedCodes.contains(code));
		generatedCodes.add(code);
		txtMa.setText(code);
	}

	public void clearTable() {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	private void xoa() throws SQLException {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng cần xóa");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = table.getSelectedRow();
				if (khDAO.xoa(model.getValueAt(table.getSelectedRow(), 1).toString())) {
					model.removeRow(index);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Xóa không thành công. Lỗi không xác định");
				}
			}
		}
	}

	private void tim() {
		int selectedIndex = cbTim.getSelectedIndex();
		int i = 0;
		if (btnTim.getText().equals("Tìm kiếm")) {
			if (txtTim.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin cần tìm");
			} else {
				if (selectedIndex == 0) {
					KhachHang kh = khDAO.getKHTheoMa(txtTim.getText());
					if (kh != null) {
						btnTim.setText("Hủy tìm");
						clearTable();
						model.addRow(new Object[] { ++i, kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getDiaChi(),
								kh.getSdt() });
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy");
					}
				} else if (selectedIndex == 1) {
					KhachHang kh = khDAO.getKHTheoSDT(txtTim.getText());
					if (kh != null) {
						btnTim.setText("Hủy tìm");
						clearTable();
						model.addRow(new Object[] { ++i, kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getDiaChi(),
								kh.getSdt() });
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy");
					}
				} else if (selectedIndex == 2) {
					ArrayList<KhachHang> dskh = khDAO.getKHTheoTen(txtTim.getText());
					if (dskh != null) {
						btnTim.setText("Hủy tìm");
						clearTable();
						for (KhachHang kh : dskh) {
							model.addRow(new Object[] { ++i, kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(),
									kh.getDiaChi(), kh.getSdt() });
						}
					} else {
						JOptionPane.showMessageDialog(this, "Không tìm thấy");
					}
				}
			}
		} else {
			clearTable();
			loadData();
			btnTim.setText("Tìm kiếm");
		}

	}

	private void sua() {
		int row = table.getSelectedRow();
		if(row >= 0) {
			String ma = txtMa.getText();
			String ten = txtTen.getText();
			String sdt = txtSdt.getText();
			String phai = null;
			if (rdoNam.isSelected())
				phai = "Nam";
			else
				phai = "Nu";
			String diaChi = txtDiaChi.getText();
			if (!regex.kiemTraRong(txtDiaChi) && !regex.kiemTraRong(txtTen) && !regex.kiemTraRong(txtSdt)) {
				if (!regex.RegexTen(txtTen) && !regex.RegexSDT(txtSdt)) {
					KhachHang kh = new KhachHang(ma, ten, phai, diaChi, sdt);
					KhachHang khsdt = khDAO.getKHTheoSDT(sdt);
					KhachHang khma = khDAO.getKHTheoMa(ma);
					if (khma.getSdt().equals(sdt) || khsdt == null) {
						if (khDAO.update(kh)) {
							clearTable();
							loadData();
							JOptionPane.showMessageDialog(this, "Sửa thành công");
						}
					} else
						JOptionPane.showMessageDialog(this, "Một số điện thoại chỉ cho một khách hàng");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Bạn cần chọn dòng để sửa!!");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMa.setText(model.getValueAt(row, 1).toString());
		txtTen.setText(model.getValueAt(row, 2).toString());
		if (model.getValueAt(row, 3).toString().equals("Nam")) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
		txtDiaChi.setText(model.getValueAt(row, 4).toString());
		txtSdt.setText(model.getValueAt(row, 5).toString());
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
