package vn.lgsp.fw.app.cmon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.app.cmon.domain.enums.ECapDonViHanhChinh;

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

	@Enumerated(EnumType.STRING)
	private ECapDonViHanhChinh cap;
	
	@ManyToOne
	@JoinColumn (name="cha_id")
	@JsonBackReference
	private CmonDonViHanhChinh cha;
	
	
	//@OneToMany(mappedBy="cha")
	//@OrderColumn(name="stt")
	//private Set<CmonDonViHanhChinh> children = new HashSet<CmonDonViHanhChinh>();

	
}
