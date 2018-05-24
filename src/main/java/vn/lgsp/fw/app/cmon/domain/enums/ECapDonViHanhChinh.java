package vn.lgsp.fw.app.cmon.domain.enums;

public enum ECapDonViHanhChinh {

	// Cap DVHC
	TINH("Tỉnh", "CAP_TINH"), 
	THANH_PHO_TRUNG_UONG("Thành phố Trung ương", "CAP_TINH"), 
	QUAN("Quận", "CAP_HUYEN"), 
	HUYEN("Huyện", "CAP_HUYEN"), 
	THANH_PHO("Thành phố", "CAP_HUYEN"), 
	THI_XA("Thị Xã", "CAP_HUYEN"), 
	XA("Xã", "CAP_XA"), 
	PHUONG("Phường", "CAP_XA"), 
	THI_TRAN("Thị trấn", "CAP_XA"),

	CAP_TINH("Cấp Tỉnh", ""), // 01
	CAP_HUYEN("Cấp Huyện", ""), // 001
	CAP_XA("Cấp Xã", ""); // 00001

	private final String tenCap;
	private final String capCha;

	ECapDonViHanhChinh(String tenCap, String capCha) {
		this.tenCap = tenCap;
		this.capCha = capCha;
	}

	public String getTenCap() {
		return tenCap;
	}

	public String getCapCha() {
		return capCha;
	}

}
