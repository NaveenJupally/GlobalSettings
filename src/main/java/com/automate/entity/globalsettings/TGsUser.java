package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the t_gs_user database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_gs_user")
@NamedQuery(name = "TGsUser.findAll", query = "SELECT t FROM TGsUser t")
public class TGsUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	private String password;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;
	@Id
	private int userid;

	private String username;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;

	/*@OneToMany(targetEntity = OrgMapping.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="VENDOR_ID")
	private List<OrgMapping> OrgMapping;
*/
	
}