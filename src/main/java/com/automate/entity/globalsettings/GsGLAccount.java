package com.automate.entity.globalsettings;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="GS_GL_ACCOUNT")
public class GsGLAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="GL_ACCOUNT_NAME")
	private String glAcctName;
	@Column(name="GL_GROUP_NAME")
	private String glGroupName;
	@Column(name="GL_SUB_GROUP_NAME")
	private String glSubGroupName;
	@Column(name="GL_VISIBLE_TO_VENDOR")
	private String glVisibleToVendor;
	@Column(name="GL_STATUS")
	private String status;
	@Column(name="updated_at")
	private Timestamp updatedAt;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="created_by")
	private String createdBy;
}
