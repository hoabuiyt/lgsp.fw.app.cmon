package vn.lgsp.fw.app.cmon.domain.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.core.BaseEntity;

/**
 * Danh muc Don Vi Hanh Chinh
 * @author caltalys
 *
 */
@Entity
@Table(name="app_nguoidung")
@Data
@ToString
@EqualsAndHashCode(callSuper=true)	
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class NguoiDung extends BaseEntity<NguoiDung>{
	
	private static final long serialVersionUID = -2486736736217544231L;
	
	@NotBlank
	@Size(max=255)
	private String hoVaTen = "";
	@NotBlank
	@Size(max=255)
	private String email = "";
	@NotBlank
	@Size(max=255)
	@JsonIgnore
	private String matKhau = "";
	@Size(max=255)
	@JsonIgnore
	private String salkey = "";
	
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "app_nguoidung_vaitro", joinColumns = @JoinColumn(name = "nguoidung_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "vaitro_id", referencedColumnName = "id"))
	private Set<VaiTro> vaiTros;
	
	
}
