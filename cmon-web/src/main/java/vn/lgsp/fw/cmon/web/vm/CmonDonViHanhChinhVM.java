package vn.lgsp.fw.cmon.web.vm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.springframework.web.bind.ServletRequestUtils;
import org.wso2.client.api.ApiException;
import org.wso2.client.api.Administratives.DefaultApi;
import org.wso2.client.model.Administratives.DonViHanhChinh;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.lang.Integers;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Window;

import vn.lgsp.fw.cmon.domain.constant.ECapDonViHanhChinh;
import vn.lgsp.fw.cmon.domain.entity.CmonDonViHanhChinh;

public class CmonDonViHanhChinhVM extends BaseObject {
	private DonViHanhChinh entity;
	DefaultApi service = LgspService.getService();

	/**
	 * Get entity, auto new instance if entity be null
	 */
	public DonViHanhChinh getEntity() {
		if (entity == null) {
			entity = new DonViHanhChinh();
		}
		return entity;
	}
	
	
	public DonViHanhChinh createEntityHasParent(Long parentId) {
		DonViHanhChinh entity = new DonViHanhChinh();
		entity.setChaId(BigDecimal.valueOf(parentId));
		return entity;
	}

	/**
	 * Get entity, auto new instance if entity be null
	 */
	public DonViHanhChinh setEntity(DonViHanhChinh entity) {
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
						((DonViHanhChinh) ctx.getProperty().getValue()).getCap());
				map.put(new String[] { "name", "no empty" },
						((DonViHanhChinh) ctx.getProperty().getValue()).getTen());
				map.put(new String[] { "code", "no empty" }, ((DonViHanhChinh) ctx.getProperty().getValue()).getMa());
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
	public void save(@BindingParam("model") CmonDonViHanhChinh model ,@BindingParam("wdn") Window wdn) {
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

	public List<DonViHanhChinh> getDsDonViHanhChinhs() {        
		List<DonViHanhChinh> result = new ArrayList<DonViHanhChinh>();
		try {
			result.addAll(service.getCap1Get());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public DefaultTreeModel<DonViHanhChinh> getModel() {
		DonViHanhChinh dvRoot = newDVHC(1l, "root", "root", "CAP_TINH", 1l);
		dvRoot.setChildCount(BigDecimal.valueOf(1));
		DonViHanhChinhNode rootNode = new DonViHanhChinhNode(dvRoot, new ArrayList<DonViHanhChinhNode>());
		DonViHanhChinhModel model = new DonViHanhChinhModel(rootNode);
		for(DonViHanhChinh dv : getDsDonViHanhChinhs()) {
			DonViHanhChinhNode node = new DonViHanhChinhNode(dv, new ArrayList<DonViHanhChinhNode>());
			rootNode.add(node);
		}
		return model;
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
	
	private HashMap<Object, Object> args;
	public HashMap<Object, Object> getArgs() {
		if (args == null) {
			args = new HashMap<Object, Object>();
			args.put("page", Integers.ZERO); 
			args.put("pagesize", Integer.valueOf(ServletRequestUtils
					.getIntParameter((ServletRequest) Executions.getCurrent()
							.getNativeRequest(), "pagesize", 10)));
			args.putAll(Executions.getCurrent().getArg());
		}
		return args;
	}
}