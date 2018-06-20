package vn.lgsp.fw.cmon.web.vm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.wso2.client.model.Administrative.DVHCItem;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

public class DonViHanhChinhNode extends DefaultTreeNode<DVHCItem> implements TreeNode<DVHCItem>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DonViHanhChinhNode(DVHCItem data) {
		super(data);
	}
	
	public DonViHanhChinhNode(DVHCItem data, Collection<DonViHanhChinhNode> children) {
		super(data, children);
	}
	
	public DonViHanhChinhNode(DVHCItem data, boolean nullAsMax) {
		super(data, nullAsMax);
	}


	public DonViHanhChinhNode(DVHCItem data, Collection<DonViHanhChinhNode> children, boolean nullAsMax) {
		super(data, children, nullAsMax);
	}
	
	@Command
	public void loadChild() {
		if(this.getChildCount() == 0) {
			for(DVHCItem dv : getChildList()) {
				DonViHanhChinhNode node = new DonViHanhChinhNode(dv, new ArrayList<DonViHanhChinhNode>());
				this.add(node);
			}
		}
	}
	
	@Override
	public boolean isLeaf() {
		if(this.getData().getPopulation() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<DVHCItem> getChildList() {
		List<DVHCItem> tree = new ArrayList<DVHCItem>();
		tree.add(newDVHC(4, "Xã Tam Thanh", "HUYEN", 2, 1, 1));
		tree.add(newDVHC(5, "Xã Điện Ngọc", "HUYEN", 2, 1, 1));
		tree.add(newDVHC(6, "Xã Tiên Cảnh", "HUYEN", 2, 1, 0));
		return tree;
	}
	
	public DVHCItem newDVHC(Integer id, String name, String code, Integer level, Integer parentId, Integer population) {
		DVHCItem dvhc = new DVHCItem();
		dvhc.setId(id);
		dvhc.setName(name);
		dvhc.setCode(code);
		dvhc.setLevel(level);
		dvhc.setParentId(parentId);
		dvhc.setPopulation(population);
		return dvhc;
	}
}
