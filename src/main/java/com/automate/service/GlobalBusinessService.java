package com.automate.service;


import java.util.List;

import com.automate.entity.globalsettings.TGsBusinessUnit;
import com.automate.entity.globalsettings.TGsOrganaization;
import com.automate.entity.globalsettings.TGsVertical;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.BusinessUnit;
import com.automate.model.globalsettings.GlobalOrg;
import com.automate.model.globalsettings.Vertical;
import com.automate.request.GetBusinessUnitRequest;
import com.automate.request.GetOrganaizationRequest;
import com.automate.request.SaveBusinessUnitRequest;
import com.automate.request.SaveVerticalRequest;
import com.automate.request.UpdateBusinessUnitRequest;
import com.automate.request.UpdateVerticalRequest;
import com.automate.response.DeleteBusinessUnitResponse;
import com.automate.response.GetBusinessUnitResponse;
import com.automate.response.GetVerticalResponse;
import com.automate.response.SaveBusinessUnitResponse;
import com.automate.response.SaveVerticalResponse;
import com.automate.response.UpdateBusinessUnitResponse;
import com.automate.response.UpdateVerticalResponse;

/**
 * @author sindh
 *
 */
public interface GlobalBusinessService {

	public GetBusinessUnitResponse GetBusinessUnit(GetBusinessUnitRequest request);

	public SaveBusinessUnitResponse savebusinessunit(SaveBusinessUnitRequest request);

	public UpdateBusinessUnitResponse updatBusinessUnit(UpdateBusinessUnitRequest request);

	public GetOrganaizationRequest getOrganizationUnit();

	public void saveOrganisationUnit();

	public void updateOrganisationUnit();

	public void deleteOrganisationUnit(int id);

	public GetVerticalResponse getVerticals();

	public UpdateVerticalResponse updateVertical(UpdateVerticalRequest request);

	public SaveVerticalResponse saveVertical(SaveVerticalRequest request);

	public void deleteVertical(int id);

	public DeleteBusinessUnitResponse deletBusinessUnit(int id);

	public List<GlobalOrg> saveGlobalOrg(List<GlobalOrg> globalOrgs) throws GlobalSettingsServiceException;
	public GlobalOrg updateGlobalOrg(GlobalOrg globalOrgs) throws GlobalSettingsServiceException;

	public GlobalOrg deleteGlobalOrg(Integer id) throws GlobalSettingsServiceException;

	public GlobalOrg getGlobalOrg(Integer id) throws GlobalSettingsServiceException;

	public List<TGsOrganaization> getAllGlobalOrg(int pageNo, int pageSize) throws GlobalSettingsServiceException;

	public List<Vertical> saveVerticle(List<Vertical> verticals) throws GlobalSettingsServiceException;
	
	public Vertical updateVerticle(Vertical vertical) throws GlobalSettingsServiceException;

	public Vertical deleteVerticle(Integer id)throws GlobalSettingsServiceException;

	public Vertical getVerticle(Integer id) throws GlobalSettingsServiceException;

	public List<TGsVertical> getAllVerticles(int pageNo, int pageSize) throws GlobalSettingsServiceException;

	public List<BusinessUnit> saveBusinessUnit(List<BusinessUnit> businessUnits) throws GlobalSettingsServiceException;

	public TGsBusinessUnit deleteBusinessUnit(Integer id) throws GlobalSettingsServiceException;

	public BusinessUnit getBusinessUnit(Integer id) throws GlobalSettingsServiceException;

	public List<TGsBusinessUnit> getAllBusinessUnits(int pageNo, int pageSize) throws GlobalSettingsServiceException;

	public BusinessUnit updateBusinessUnit(BusinessUnit businessUnit) throws GlobalSettingsServiceException;

	
	

}
