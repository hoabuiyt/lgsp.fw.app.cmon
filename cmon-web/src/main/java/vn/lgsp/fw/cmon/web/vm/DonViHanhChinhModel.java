package vn.lgsp.fw.cmon.web.vm;

import org.wso2.client.model.Administrative.DVHCItem;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.ext.TreeSelectableModel;

public class DonViHanhChinhModel extends DefaultTreeModel<DVHCItem> implements TreeSelectableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DonViHanhChinhModel(TreeNode<DVHCItem> root) {
		super(root);
	}
	
}
