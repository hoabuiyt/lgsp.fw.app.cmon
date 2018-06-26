package vn.lgsp.fw.cmon.web.vm;

import org.wso2.client.model.Administratives.DonViHanhChinh;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.ext.TreeSelectableModel;

public class DonViHanhChinhModel extends DefaultTreeModel<DonViHanhChinh> implements TreeSelectableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DonViHanhChinhModel(TreeNode<DonViHanhChinh> root) {
		super(root);
	}
	
}
