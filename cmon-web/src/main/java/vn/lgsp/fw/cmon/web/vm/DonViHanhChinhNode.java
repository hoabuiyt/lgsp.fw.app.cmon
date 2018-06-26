package vn.lgsp.fw.cmon.web.vm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.wso2.client.api.ApiException;
import org.wso2.client.api.Administratives.DefaultApi;
import org.wso2.client.model.Administratives.DonViHanhChinh;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

public class DonViHanhChinhNode extends DefaultTreeNode<DonViHanhChinh> implements TreeNode<DonViHanhChinh>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultApi service = LgspService.getService();
	
	public DonViHanhChinhNode(DonViHanhChinh data) {
		super(data);
	}
	
	public DonViHanhChinhNode(DonViHanhChinh data, Collection<DonViHanhChinhNode> children) {
		super(data, children);
	}
	
	public DonViHanhChinhNode(DonViHanhChinh data, boolean nullAsMax) {
		super(data, nullAsMax);
	}


	public DonViHanhChinhNode(DonViHanhChinh data, Collection<DonViHanhChinhNode> children, boolean nullAsMax) {
		super(data, children, nullAsMax);
	}
	
	@Command
	public void loadChild() {
		if(this.getChildCount() == 0) {
			for(DonViHanhChinh dv : getChildList()) {
				DonViHanhChinhNode node = new DonViHanhChinhNode(dv, new ArrayList<DonViHanhChinhNode>());
				this.add(node);
			}
		}
	}
	
	@Override
	public boolean isLeaf() {
		if(this.getData() != null && this.getData().getChildCount() != null && this.getData().getChildCount().intValue() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<DonViHanhChinh> getChildList() {
		List<DonViHanhChinh> list = new ArrayList<DonViHanhChinh>();
        try {
            list.addAll(service.getChildsChaIdGet(this.getData().getId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
		return list;
	}
	
	public DonViHanhChinh newDVHC(Long id, String name, String code, String level, Long parentId) {
		DonViHanhChinh dvhc = new DonViHanhChinh();
		dvhc.setId(BigDecimal.valueOf(id));
		dvhc.setTen(name);
		dvhc.setMa(code);
		dvhc.setCap(level);
		dvhc.setChaId(BigDecimal.valueOf(parentId));
		dvhc.setChildCount(BigDecimal.valueOf(0));
		return dvhc;
	}
	
}
