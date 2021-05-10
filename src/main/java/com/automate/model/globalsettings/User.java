package com.automate.model.globalsettings;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private Timestamp createdAt;

	private String createdBy;

	private String password;

	private Timestamp updatedAt;

	private String updatedBy;

	private String username;

	//bi-directional one-to-one association to TGsOrganaization
	private GlobalOrg globalOrg;


}
