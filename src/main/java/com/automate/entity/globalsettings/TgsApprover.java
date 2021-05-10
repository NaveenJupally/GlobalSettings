package com.automate.entity.globalsettings;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_dynamic_wf_approvers")
public class TgsApprover {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name="approver_id")
	private String approverId;
	@Column(name="approver_name")
	private String approverName;
	@Column(name="approver_type")
	private String approverType;

	

}
