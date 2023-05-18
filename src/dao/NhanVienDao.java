package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVienDao {

	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> ds = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM NhanVien";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String manv = rs.getString(1);
				String honv = rs.getString(2);
				String tennv = rs.getString(3);
				Integer tuoi = rs.getInt(4);
				Boolean phai = rs.getBoolean(5);
				Float tienluong = rs.getFloat(7);
				PhongBan phongban = new PhongBan(rs.getString(6));
				NhanVien nVien = new NhanVien(manv, honv, tennv, tuoi, phai, honv, tienluong, phongban);
				ds.add(nVien);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ds;
	}

	public boolean addNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		String sql = "insert into NhanVien(maNV,ho,ten,tuoi,phai,maPhong,tienLuong) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, nv.getMaNV());
			pst.setString(2, nv.getHo());
			pst.setString(3, nv.getTen());
			pst.setInt(4, nv.getTuoi());
			pst.setBoolean(5, nv.getPhai());
			pst.setString(6, nv.getPhong().getMaPhong());
			pst.setFloat(7, nv.getTienLuong());
			n = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	public void updateNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();

		String sql = "update NhanVien  set ho = ?,ten=?,tuoi=?,phai=?,maPhong=?,tienLuong =? where maNV =?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, nv.getHo());
			pst.setString(2, nv.getTen());
			pst.setInt(3, nv.getTuoi());
			pst.setBoolean(4, nv.getPhai());
			pst.setString(5, nv.getPhong().getMaPhong());
			pst.setFloat(6, nv.getTienLuong());
			pst.setString(7, nv.getMaNV());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}

	public void DeleteNV(String manv) {
		ConnectDB.getInstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();

		String sql = "DELETE FROM NhanVien WHERE maNV =?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, manv);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}

	private void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
