package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the t_gs_dynamic_workflow database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_dynamic_workflow")
@NamedQuery(name="TGsDynamicWorkflow.findAll", query="SELECT t FROM TGsDynamicWorkflow t")
public class TGsDynamicWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_dynamic_workflow_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsDynamicWorkflowId;
	


	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	
	@Column(name="update_by")
	private String updateBy;

	@Column(name="updated_at")
	private Timestamp updatedAt;
	
	@OneToMany(targetEntity = TgsApprover.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="gs_dynamic_workflow_id")
	private List<TgsApprover> approvers;
	
	
	//bi-directional many-to-one association to TGsOrganaization
	@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;
	
	
	@ManyToOne
	@JoinColumn(name="gs_vertical_id")
	private TGsVertical tGsVertical;
	
	
	@ManyToOne
	@JoinColumn(name="gs_business_unit_id")
	private TGsBusinessUnit tGsBusinessUnit;
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "module_id", nullable = false)
	private TGsModule tGsModule;
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sub_module_id", nullable = false)
	private TGsSubModule tGsSubModule;
	
	
}