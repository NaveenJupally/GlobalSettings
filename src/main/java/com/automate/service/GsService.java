package com.automate.service;

import java.util.List;
import java.util.Optional;

import com.automate.entity.globalsettings.TGsAssetGroup;
import com.automate.entity.globalsettings.TGsAssetNature;
import com.automate.entity.globalsettings.TGsCostCenter;
import com.automate.entity.globalsettings.TGsExpenditureGlHead;
import com.automate.entity.globalsettings.TGsGlAccount;
import com.automate.entity.globalsettings.TGsGlGroup;
import com.automate.entity.globalsettings.TGsGlSubGroup;
import com.automate.entity.globalsettings.TGsIncomeGlHead;
import com.automate.entity.globalsettings.TGsLiabilityGlHead;
import com.automate.entity.globalsettings.TGsLiabilityGlSubhead;
import com.automate.entity.globalsettings.TGsProfitCenter;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.AssetGroup;
import com.automate.model.globalsettings.AssetNature;
import com.automate.model.globalsettings.BusinessUnits;
import com.automate.model.globalsettings.CalendarYear;
import com.automate.model.globalsettings.CostCenter;
import com.automate.model.globalsettings.Country;
import com.automate.model.globalsettings.CountryInfo;
import com.automate.model.globalsettings.Currency;
import com.automate.model.globalsettings.DynamicWorkflow;
import com.automate.model.globalsettings.ExpenditureGLHead;
import com.automate.model.globalsettings.FYandCYConfiguration;
import com.automate.model.globalsettings.FinancialYear;
import com.automate.model.globalsettings.GLAccount;
import com.automate.model.globalsettings.GLGroup;
import com.automate.model.globalsettings.GLSubGroup;
import com.automate.model.globalsettings.GetBusinessUnit;
import com.automate.model.globalsettings.GlobalOrg;
import com.automate.model.globalsettings.IncomeGLHead;
import com.automate.model.globalsettings.Language;
import com.automate.model.globalsettings.LiabilityGLHead;
import com.automate.model.globalsettings.LiabilityGLSubHead;
import com.automate.model.globalsettings.Login;
import com.automate.model.globalsettings.LoginRequest;
import com.automate.model.globalsettings.ProfitCenter;
import com.automate.model.globalsettings.RuleConfiguration;
import com.automate.model.globalsettings.RuleConfigurationInfo;
import com.automate.model.globalsettings.SaveCalendarYear;
import com.automate.model.globalsettings.SaveFinancialYear;
import com.automate.model.globalsettings.TaxAccount;
import com.automate.model.globalsettings.TaxAccountNature;
import com.automate.model.globalsettings.TaxGroup;
import com.automate.model.globalsettings.TaxMapping;
import com.automate.model.globalsettings.TaxSection;
import com.automate.model.globalsettings.UserOrganisation;
import com.automate.model.globalsettings.Vendor;
import com.automate.response.CustomResponse;
import com.automate.response.ExpenditureGlHeadResponse;
import com.automate.response.IncomeGlHeadResponse;

public interface GsService {

	public CustomResponse saveVendorMaster(Vendor vendor) throws GlobalSettingsServiceException;

	public CustomResponse createTaxGroup(TaxGroup taxGroup) throws GlobalSettingsServiceException;

	public CustomResponse createTaxAccount(TaxAccount taxAccount) throws GlobalSettingsServiceException;

	public CustomResponse createTaxAccountNature(TaxAccountNature taxAccountNature)
			throws GlobalSettingsServiceException;

	public CustomResponse createTaxSection(TaxSection taxSection) throws GlobalSettingsServiceException;

	public CustomResponse createTaxMapping(TaxMapping taxMapping) throws GlobalSettingsServiceException;

	public CustomResponse createGlAccount(GLAccount glAccount) throws GlobalSettingsServiceException;

	public List<GLAccount> getGlAccountData(int pageNo, int size) throws GlobalSettingsServiceException;

	public GLAccount updateGlAccountData(GLAccount glAccount) throws GlobalSettingsServiceException;

	public List<TaxAccountNature> getTaxAccNature(int pageNo, int size) throws GlobalSettingsServiceException;

	public TaxAccountNature updateTaxAccountNature(TaxAccountNature taxAccountNature)
			throws GlobalSettingsServiceException;

	public List<TaxMapping> getTaxMapping(int pageNo, int size) throws GlobalSettingsServiceException;

	public TaxMapping updateTaxMapping(TaxMapping taxMapping) throws GlobalSettingsServiceException;

	public CustomResponse saveDynamicWorkflow(DynamicWorkflow dynamicWorkflow) throws GlobalSettingsServiceException;

	public String saveGlobalOrg(GlobalOrg globalOrg) throws GlobalSettingsServiceException;

	public List<TaxAccount> getTaxAccList(int pageNo, int size) throws GlobalSettingsServiceException;

	public TaxAccount updateTaxAccounts(TaxAccount taxAccount) throws GlobalSettingsServiceException;

	public String getFinancialYear(int gsOrgid, int userId) throws GlobalSettingsServiceException;

	public String saveFinancialYear(SaveFinancialYear request) throws GlobalSettingsServiceException;

	public String updateFinancialYear(FinancialYear request) throws GlobalSettingsServiceException;

	public CustomResponse deleteFinancialYear(int id) throws GlobalSettingsServiceException;

	public String getCalendarYear(int parentOrgId, int userId) throws GlobalSettingsServiceException;

	public String saveCalendarYear(SaveCalendarYear request) throws GlobalSettingsServiceException;

	public String updateCalendarYear(CalendarYear request) throws GlobalSettingsServiceException;

	public CustomResponse deleteCalendarYear(int id) throws GlobalSettingsServiceException;

	public String getFYandCYCongfiguration(int orgId) throws GlobalSettingsServiceException;

	public String updateFYandCYCongfiguration(FYandCYConfiguration request) throws GlobalSettingsServiceException;

	public String saveFYandCYCongfiguration(FYandCYConfiguration request) throws GlobalSettingsServiceException;

	public CustomResponse deleteFYandCYCongfiguration(int id) throws GlobalSettingsServiceException;
	
	
	public Login validateLogIn(LoginRequest request) throws GlobalSettingsServiceException;

	public CountryInfo getCountry(int parentOrgId, int userId) throws GlobalSettingsServiceException;

	public String updateCountry(Country request) throws GlobalSettingsServiceException;

	public String saveCountry(CountryInfo request) throws GlobalSettingsServiceException;

	public CustomResponse deleteCountry(int id) throws GlobalSettingsServiceException;

	public Currency getCurrency(int parentOrgId, int userId) throws GlobalSettingsServiceException;

	public String updateCurrency(Currency request) throws GlobalSettingsServiceException;

	public String saveCurrency(Currency request) throws GlobalSettingsServiceException;

	public CustomResponse deleteCurrency(int id) throws GlobalSettingsServiceException;

	public List<Language> getLanguage(int parentOrgId, int userId) throws GlobalSettingsServiceException;

	public String updateLanguageCurrency(List<Language> request) throws GlobalSettingsServiceException;

	public String saveLanguage(List<Language> languages) throws GlobalSettingsServiceException;

	public CustomResponse deleteLanguage(int id) throws GlobalSettingsServiceException;

	public UserOrganisation getUserOrganisation(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	public String getVerticals(int orgId, int userId, int parentOrgId) throws GlobalSettingsServiceException;
	
	public BusinessUnits getBusinessUnits(GetBusinessUnit request) throws GlobalSettingsServiceException;

	public List<RuleConfiguration> getRuleCongfiguration(int parentOrgId, int userId) throws GlobalSettingsServiceException;

	public RuleConfiguration updateRuleCongfiguration(RuleConfiguration request) throws GlobalSettingsServiceException;

	public String saveRuleCongfiguration(RuleConfigurationInfo request) throws GlobalSettingsServiceException;

	public CustomResponse deleteRuleCongfiguration(int id) throws GlobalSettingsServiceException;


	// swati chnages starts

	public CustomResponse createCostCenter(CostCenter costCenter) throws GlobalSettingsServiceException;

	public CostCenter getCostCenter(int costCenterid) throws GlobalSettingsServiceException;

	public TGsCostCenter updateCostCenterData(CostCenter costCenter) throws GlobalSettingsServiceException;

	public void deleteCostCenter(int costCenterId) throws GlobalSettingsServiceException;

	public ProfitCenter getProfitCenter(int profitCenterid) throws GlobalSettingsServiceException;

	public void deleteProfitCenter(int profitCenterId) throws GlobalSettingsServiceException;

	public CustomResponse createProfitCenter(ProfitCenter profitcenter) throws GlobalSettingsServiceException;

	public TGsProfitCenter updateProfitcenterData(ProfitCenter profitcenter) throws GlobalSettingsServiceException;

	public IncomeGLHead getIncomeGLHead(int incomeGlHeadId) throws GlobalSettingsServiceException;
	

	public void deleteIncomeGLHead(int incomeGlHeadId) throws GlobalSettingsServiceException;

	public IncomeGlHeadResponse createIncomeGLHead(IncomeGLHead incomeglhead) throws GlobalSettingsServiceException;

	public TGsIncomeGlHead updateIncomeGlHead(IncomeGLHead incomeglhead) throws GlobalSettingsServiceException;

	public ExpenditureGLHead getExpenditureGLHead(int expenditureGlHeadId) throws GlobalSettingsServiceException;

	public void deleteExpenditureGLHead(int incomeGlHeadId) throws GlobalSettingsServiceException;

	public ExpenditureGlHeadResponse createExpenditureGlHead(ExpenditureGLHead expenditureglhead)
			throws GlobalSettingsServiceException;

	public TGsExpenditureGlHead updateExpenditureGLHead(ExpenditureGLHead expenditureglhead);

    
	public AssetNature getAssetNature(int assetNatureId) throws GlobalSettingsServiceException;
	
	public AssetGroup getAssetGroup(int assetGroupId) throws GlobalSettingsServiceException;
	
	public LiabilityGLHead getLiabilityGLHead(int liablityGLHeadId) throws GlobalSettingsServiceException;
	
	public LiabilityGLSubHead getLiabilityGLSubHead(int liablityGLSubHeadId) throws GlobalSettingsServiceException;
	
	public GLGroup getGLGroup(int glGroupId) throws GlobalSettingsServiceException;
	
	public GLSubGroup getGLSubGroup(int glGroupId) throws GlobalSettingsServiceException;
	
	public GLAccount getGLAccount(int glGroupId) throws GlobalSettingsServiceException;	

	public void deleteAssetNature(int assetNatureId) throws GlobalSettingsServiceException;

	public void deleteAssetGroup(int assetGroupId) throws GlobalSettingsServiceException;

	public void deleteLiabilityGlHead(int liabilityGlHeadId) throws GlobalSettingsServiceException;

	public void deleteLiabilityGlSubhead(int liabilityGlSubheadId) throws GlobalSettingsServiceException;

	public void deleteGlGroup(int glGroupId) throws GlobalSettingsServiceException;

	public void deleteGlSubGroup(int glSubGroupId) throws GlobalSettingsServiceException;
	
	public void deleteGlAccount(int glAccountId) throws GlobalSettingsServiceException;	
	
	
	public CustomResponse createAssetNature(AssetNature assetNature)throws GlobalSettingsServiceException;
	
	public CustomResponse createAssetGroup(AssetGroup assetGroup)throws GlobalSettingsServiceException;
	
	public CustomResponse createLiabilityGlHead(LiabilityGLHead liabilityGLHead)throws GlobalSettingsServiceException;
	
	public CustomResponse createLiabilityGlSubhead(LiabilityGLSubHead liabilityGLSubHead)throws GlobalSettingsServiceException;
	
	public CustomResponse createGlGroup(GLGroup gLGroup)throws GlobalSettingsServiceException;
	
	public CustomResponse createGlSubGroup(GLSubGroup gLSubGroup)throws GlobalSettingsServiceException;
	
	public TGsAssetNature updateAssetNature(AssetNature assetNature) throws GlobalSettingsServiceException;
	
	public TGsAssetGroup updateAssetGroup(AssetGroup assetGroup) throws GlobalSettingsServiceException;
	
	public TGsLiabilityGlHead updateLiabilityGlHead(LiabilityGLHead liabilityGLHead) throws GlobalSettingsServiceException;
	
	public TGsLiabilityGlSubhead updateLiabilityGlSubhead(LiabilityGLSubHead liabilityGLSubHead) throws GlobalSettingsServiceException;
	
	public TGsGlGroup updateGlGroup(GLGroup gLGroup) throws GlobalSettingsServiceException;
	
	public TGsGlSubGroup updateGlSubGroup(GLSubGroup gLSubGroup) throws GlobalSettingsServiceException;

	public List<CostCenter> getCostCenterData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	public List<ProfitCenter> getProfitCenterData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	public List<IncomeGLHead> getIncomeGlHead(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	public List<ExpenditureGLHead> getExpenditureGlHead(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	public List<AssetNature> getAssetNatureData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	List<AssetGroup> getAssetGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	List<LiabilityGLHead> getLiabilityGLHeadData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	List<LiabilityGLSubHead> getLiabilityGLSubHeadData(int userId, int parentOrgId)
			throws GlobalSettingsServiceException;

	List<GLGroup> getGLGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	List<GLSubGroup> getGLSubGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException;

	List<GLAccount> getGLAccountData(int userId, int parentOrgId) throws GlobalSettingsServiceException;;
	
	
	
	
	
	

}
