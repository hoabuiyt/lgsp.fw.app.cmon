package vn.lgsp.fw.cmon.web.vm;

public class DonViHanhChinh {

	private String name;
	private String cap;
	private int childCount;
	
	DonViHanhChinh() {
		
	}
	
	DonViHanhChinh(String _name, String _cap, int _childCount) {
		name = _name;
		cap = _cap;
		childCount = _childCount;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
}
