package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.PhongBan;


public class PhongBanDao {
	 
	public List<PhongBan> getallphongban(){
		List<PhongBan> ds = new ArrayList<PhongBan>();
		
		try {
			ConnectDB.getInstance();
			 Connection con = ConnectDB.getConnection();
			String sql ="SELECT * FROM PhongBan";
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String mapb=rs.getString(1);
				String tenpb=rs.getString(2);
				PhongBan phongBan = new PhongBan(mapb,tenpb);
				ds.add(phongBan);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ds;
	}
	public boolean addNhanVien(PhongBan lh) {
		ConnectDB.getInstance();
		 Connection con = ConnectDB.getConnection();
		
		 String sql ="insert into PhongBan(maPhong,tenPhong) VALUES(?,?)";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, lh.getMaPhong());
			pst.setString(2, lh.getTenPhong());
			
			return pst.executeUpdate() >0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateLopHoc(String malop,String tenlopnew,String gvcnnew) {
		ConnectDB.getInstance();
		 Connection con = ConnectDB.getConnection();
		
		 String sql ="UPDATE dbo.PhongBan SET tenLop=?,giaovienCN= ? WHERE maLop =?";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tenlopnew);
			pst.setString(2, gvcnnew);
			pst.setString(3, malop);
			
			return pst.executeUpdate() >0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public boolean Delete(String tenlopnew) {
		ConnectDB.getInstance();
		 Connection con = ConnectDB.getConnection();
		
		 String sql ="DELETE FROM LopHoc WHERE tenLop =?";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tenlopnew);
			return pst.executeUpdate() >0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
