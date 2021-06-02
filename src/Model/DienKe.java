package Model;

import java.util.Date;

public class DienKe {
	private String maDK, maKH;
	private Date ngaythang;
	int cs_cu, cs_moi;
	public DienKe(String maDK, String maKH, Date ngaythang, int cs_cu, int cs_moi) {
		this.maDK = maDK;
		this.maKH = maKH;
		this.ngaythang = ngaythang;
		this.cs_cu = cs_cu;
		this.cs_moi = cs_moi;
	}
	
}
