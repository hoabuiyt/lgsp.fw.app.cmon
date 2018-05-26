package vn.lgsp.fw.app.cmon.web.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.enums.ECapDonViHanhChinh;

public class CmonDonViHanhChinhVM {
	private CmonDonViHanhChinh dvhc;

	/**
	 * Get entity, auto new instance if entity be null
	 * @return CmonDonViHanhChinh
	 */
	public CmonDonViHanhChinh getEntity() {
		if (dvhc == null) {
			dvhc = new CmonDonViHanhChinh();
		}
		return dvhc;
	}

	/**
	 * Validate form entity
	 * @return org.zkoss.bind.validator.AbstractValidator
	 */
	public Object getFormValidator() {
		return new org.zkoss.bind.validator.AbstractValidator() {
			public void validate(org.zkoss.bind.ValidationContext ctx) {
				LinkedHashMap<String[], Object> map = new LinkedHashMap<String[], Object>();
				map.put(new String[] { "cap", "no empty" },
						((CmonDonViHanhChinh) ctx.getProperty().getValue()).getCap());
				map.put(new String[] { "ten", "no empty" },
						((CmonDonViHanhChinh) ctx.getProperty().getValue()).getTen());
				map.put(new String[] { "ma", "no empty" },
						((CmonDonViHanhChinh) ctx.getProperty().getValue()).getMa());
				for (Entry<String[], Object> entry : map.entrySet()) {
					try {
						System.out.println(entry.getKey()[0] + " " + entry.getKey()[1] + " => " + entry.getValue());
						new org.zkoss.zul.SimpleConstraint(entry.getKey()[1]).validate(null, entry.getValue());
					} catch (org.zkoss.zk.ui.WrongValueException ex) {
						System.out.println(entry.getKey()[0] + " => " + ex.getMessage());
						addInvalidMessage(ctx, entry.getKey()[0], ex.getMessage());
					}
				}
			}
		};
	}

	/**
	 * Save entity
	 */
	@Command
	public void save(@BindingParam("wdn") Window wdn) {
		System.out.println("Entity is "+ this.getEntity().getTen());
		if(wdn != null) {
			wdn.detach();
		}
		Clients.showNotification("Đã lưu");
	}
	
	/**
	 * Get list of enum cấp đơn vị hành chính
	 */
	public List<ECapDonViHanhChinh> getCapDonViHanhChinhs() {
		List<ECapDonViHanhChinh> caps = new ArrayList<ECapDonViHanhChinh>();
		caps.add(null);
		caps.addAll(Arrays.asList(ECapDonViHanhChinh.values()));
		return caps;
	}
	
	public DefaultTreeModel<CmonDonViHanhChinh> getTreeDonViHanhChinh() {
		CmonDonViHanhChinh dvhc = new CmonDonViHanhChinh();
		dvhc.setTen("hihihi");
		TreeNode<CmonDonViHanhChinh> rootNode = new DefaultTreeNode<CmonDonViHanhChinh>(dvhc, new ArrayList<DefaultTreeNode<CmonDonViHanhChinh>>());
		DefaultTreeModel<CmonDonViHanhChinh> tree = new DefaultTreeModel<>(rootNode);
		
		return tree;
	}
	
	/**
	 * Create a component of zkoss
	 * @param zul
	 * @param vmArgs
	 * @param vm
	 * @param nhom
	 */
	@Command
	public void redirectPage(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}

}
