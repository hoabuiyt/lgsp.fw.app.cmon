package vn.lgsp.fw.app.cmon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.app.cmon.domain.ADanhMuc;
import vn.lgsp.fw.app.cmon.domain.constant.ECapDonViHanhChinh;

/**
 * Danh muc Don Vi Hanh Chinh
 * @author caltalys
 *
 */
@Entity
@Table(name="cmon_donvihanhchinh")
@Data
@ToString
@EqualsAndHashCode(callSuper=true)	
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CmonDonViHanhChinh extends ADanhMuc<CmonDonViHanhChinh>{
	
	private static final long serialVersionUID = -168197636045249911L;
	
	public static final String COLLECTION_NAME = "donvihanhchinhs";

	@Enumerated(EnumType.STRING)
	private ECapDonViHanhChinh cap;
	
	@ManyToOne
	@JoinColumn (name="cha_id")
	@JsonBackReference
	private CmonDonViHanhChinh cha;
	
	public CmonDonViHanhChinh() {};
	public CmonDonViHanhChinh(String ten, ECapDonViHanhChinh cap) {
		this.setTen(ten);
		this.setCap(cap);
	}
	
}
