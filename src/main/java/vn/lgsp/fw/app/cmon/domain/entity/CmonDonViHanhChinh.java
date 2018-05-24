package vn.lgsp.fw.app.cmon.domain.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE) 	
@JsonIgnoreProperties(ignoreUnknown = true) 		
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CmonDonViHanhChinh extends ADanhMuc<CmonDonViHanhChinh>{
	
	private static final long serialVersionUID = -168197636045249911L;

	private ECapDonViHanhChinh cap;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cha_id",insertable=false,updatable=false)
	private CmonDonViHanhChinh cha;
	
	@OneToMany
	@OrderColumn(name="stt")
	@JoinColumn(name = "cha_id")
	private List<CmonDonViHanhChinh> children = new LinkedList<CmonDonViHanhChinh>();
	
}
