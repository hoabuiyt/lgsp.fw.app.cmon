package vn.lgsp.fw.app.cmon.web.vm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.wso2.client.api.ApiClient;
import org.wso2.client.api.ApiException;
import org.wso2.client.api.Administrative.DefaultApi;
import org.wso2.client.model.Administrative.DVHCItem;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Window;

import vn.lgsp.fw.app.cmon.domain.entity.CmonDonViHanhChinh;
import vn.lgsp.fw.app.cmon.domain.enums.ECapDonViHanhChinh;

public class CmonDonViHanhChinhVM {
	private CmonDonViHanhChinh entity;

	/**
	 * Get entity, auto new instance if entity be null
	 */
	public CmonDonViHanhChinh getEntity() {
		if (entity == null) {
			entity = new CmonDonViHanhChinh();
		}
		return entity;
	}

	/**
	 * Get entity, auto new instance if entity be null
	 */
	public CmonDonViHanhChinh setEntity(CmonDonViHanhChinh entity) {
		this.entity = entity;
		return entity;
	}

	/**
	 * Validate form entity
	 */
	public Object getFormValidator() {
		return new org.zkoss.bind.validator.AbstractValidator() {
			public void validate(org.zkoss.bind.ValidationContext ctx) {
				LinkedHashMap<String[], Object> map = new LinkedHashMap<String[], Object>();
				map.put(new String[] { "cap", "no empty" },
						((CmonDonViHanhChinh) ctx.getProperty().getValue()).getCap());
				map.put(new String[] { "ten", "no empty" },
						((CmonDonViHanhChinh) ctx.getProperty().getValue()).getTen());
				map.put(new String[] { "ma", "no empty" }, ((CmonDonViHanhChinh) ctx.getProperty().getValue()).getMa());
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
		System.out.println("Entity is " + this.getEntity().getTen());
		if (wdn != null) {
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
		CmonDonViHanhChinh nodeGoc = new CmonDonViHanhChinh();
		DefaultTreeModel<CmonDonViHanhChinh> tree = new DefaultTreeModel<>(nodeGoc.getNode(), true);
		for (CmonDonViHanhChinh cat : getDsDonViHanhChinhs()) {
			nodeGoc.getNode().add(cat.getNode());
		}
		return tree;
	}

	public List<CmonDonViHanhChinh> getDsDonViHanhChinhs() {
		DefaultApi defaultApi = new DefaultApi();
		ApiClient apiClient = defaultApi.getApiClient();
		String accessToken = "d101a6db-19f9-3f52-bf04-db61d3449026";
		apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);
		apiClient.setBasePath("http://192.168.1.104:8284/administratives/v1.0.0");
		defaultApi.setApiClient(apiClient);
		try {
			List<DVHCItem> result = defaultApi.getDVHCCap1Get();
			System.out.println(result);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		// Sample data
		List<CmonDonViHanhChinh> list = new ArrayList<CmonDonViHanhChinh>();
		list.add(new CmonDonViHanhChinh("Thành phố Tam Kỳ", ECapDonViHanhChinh.THANH_PHO));
		list.add(new CmonDonViHanhChinh("Huyện Núi Thành", ECapDonViHanhChinh.HUYEN));
		list.add(new CmonDonViHanhChinh("Huyện Thăng Bình", ECapDonViHanhChinh.HUYEN));
		return list;
	}
	
	public DefaultTreeModel<DVHCItem> getModel() {
		DonViHanhChinhNode rootNode = new DonViHanhChinhNode(newDVHC(1, "root", "root", 0, null, 1), new ArrayList<DonViHanhChinhNode>());
		DonViHanhChinhModel model = new DonViHanhChinhModel(rootNode);
		for(DVHCItem dv : getList()) {
			DonViHanhChinhNode node = new DonViHanhChinhNode(dv, new ArrayList<DonViHanhChinhNode>());
			rootNode.add(node);
		}
		return model;
	}
	
	public List<DVHCItem> getList() {
		List<DVHCItem> tree = new ArrayList<DVHCItem>();
		tree.add(newDVHC(1, "Thành phố Tam Kỳ", "THANH_PHO", 1, null, 1));
		tree.add(newDVHC(1, "Huyện Núi Thành", "THANH_PHO", 1, null, 1));
		tree.add(newDVHC(1, "Huyện Thăng Bình", "THANH_PHO", 1, null, 1));
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
	
	/**
	 * Create a component of zkoss
	 */
	@Command
	public void redirectPage(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}
}