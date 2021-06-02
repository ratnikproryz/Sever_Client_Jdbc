package Model;

import java.util.Date;

public class HoaDon {
	private  String maHD,maKH, maDK;
	private Date ngaythang;
	private int lgDien;//lượng điện
	private float total; //tổng tiền hoá đơn(đã cộng thuế)
	public HoaDon(String maHD, String maKH, String maDK, Date ngaythang, int lgDien, float total) {
		this.maHD = maHD;
		this.maKH = maKH;
		this.maDK = maDK;
		this.ngaythang = ngaythang;
		this.lgDien = lgDien;
		this.total = total;
	}
	
}
