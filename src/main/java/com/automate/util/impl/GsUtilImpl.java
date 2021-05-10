package com.automate.util.impl;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.automate.constants.GsAppConstants;
import com.automate.dao.TgsUserRepo;
import com.automate.entity.globalsettings.GsGLAccount;
import com.automate.entity.globalsettings.GsTaxAccountNature;
import com.automate.entity.globalsettings.GsTaxSection;
import com.automate.entity.globalsettings.GsVendor;
import com.automate.entity.globalsettings.GsVendorDirector;
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
import com.automate.entity.globalsettings.TGsOrganaization;
import com.automate.entity.globalsettings.TGsProfitCenter;
import com.automate.entity.globalsettings.TGsTaxAccount;
import com.automate.entity.globalsettings.TGsTaxGroup;
import com.automate.entity.globalsettings.TGsTaxMapping;
import com.automate.entity.globalsettings.TGsUser;
import com.automate.model.globalsettings.AssetGroup;
import com.automate.model.globalsettings.AssetNature;
import com.automate.model.globalsettings.CostCenter;
import com.automate.model.globalsettings.Director;
import com.automate.model.globalsettings.ExpenditureGLHead;
import com.automate.model.globalsettings.GLAccount;
import com.automate.model.globalsettings.GLGroup;
import com.automate.model.globalsettings.GLSubGroup;
import com.automate.model.globalsettings.IncomeGLHead;
import com.automate.model.globalsettings.LiabilityGLHead;
import com.automate.model.globalsettings.LiabilityGLSubHead;
import com.automate.model.globalsettings.ProfitCenter;
import com.automate.model.globalsettings.TaxAccount;
import com.automate.model.globalsettings.TaxAccountNature;
import com.automate.model.globalsettings.TaxGroup;
import com.automate.model.globalsettings.TaxMapping;
import com.automate.model.globalsettings.TaxSection;
import com.automate.model.globalsettings.Vendor;
import com.automate.util.GsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author sruja
 *
 */
@Slf4j
public class GsUtilImpl implements GsUtil{
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TgsUserRepo userRepo;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = simpleDateFormat.format(Calendar.getInstance().getTime());
	Timestamp currenttimestamp = Timestamp.valueOf( date );
	
	
	@Override
	public GsVendor buildVendorMaster(Vendor vendor) {
		
		GsVendor gsVendor = new GsVendor();
		gsVendor.setAccountNo(vendor.getAccountNo());
		gsVendor.setAttachment(vendor.getAttachment());
		gsVendor.setBankAddress(vendor.getBankAddress());
		gsVendor.setBankName(vendor.getBankName());
		gsVendor.setBranchName(vendor.getBranchName());
		gsVendor.setCompanyName(vendor.getCompanyName());
		gsVendor.setCompanyPANNo(vendor.getCompanyPANNo());
		gsVendor.setCompanyWebSite(vendor.getCompanyWebSite());
		gsVendor.setContactNo(vendor.getContactNo());
		gsVendor.setDateOfRegistration(vendor.getDateOfRegistration());
		gsVendor.setDesignation(vendor.getDesignation());
		gsVendor.setEmailId(vendor.getEmailId());
		gsVendor.setExperience(vendor.getExperience());
		gsVendor.setFirstName(vendor.getFirstName());
		gsVendor.setGstNo(vendor.getGstNo());
		gsVendor.setIfscCode(vendor.getIfscCode());
		gsVendor.setLastName(vendor.getLastName());
		gsVendor.setNameOfRegAuthority(vendor.getNameOfRegAuthority());
		gsVendor.setOrganization(vendor.getOrganization());
		gsVendor.setPlaceOfRegistration(vendor.getPlaceOfRegistration());
		gsVendor.setRegistrationNo(vendor.getRegistrationNo());
		gsVendor.setServiceTax(vendor.getServiceTax());
		gsVendor.setStatusOfPerson(vendor.getStatusOfPerson());
		gsVendor.setTanNo(vendor.getTanNo());
		gsVendor.setTurnOver(vendor.getTurnOver());
		gsVendor.setServiceTax(vendor.getServiceTax());
		gsVendor.setCreatedAt(getCurrentTmeStamp());
		gsVendor.setCreatedBy(vendor.getCreatedBy());
		gsVendor.setUpdatedAt(getCurrentTmeStamp());
		gsVendor.setUpdatedBy(vendor.getUpdatedBy());
		List<GsVendorDirector> vendorDirectorList = new ArrayList<>();
		for(Director director:vendor.getDirectors()) {
			GsVendorDirector obj = new GsVendorDirector();
			obj.setDirAddressLine(director.getDirAddressLine());
			obj.setDirContactNo(director.getDirContactNo());
			obj.setDirDesignation(director.getDirDesignation());
			obj.setDirEmailId(obj.getDirEmailId());
			obj.setDirFirstName(obj.getDirFirstName());
			obj.setDirLastName(obj.getDirLastName());
			obj.setDirPanNo(obj.getDirPanNo());
			obj.setDirPinCode(obj.getDirPinCode());
			vendorDirectorList.add(obj);
		}
		gsVendor.setDirectors(vendorDirectorList);
		
		
		return gsVendor;
	}

	@Override
	public TGsTaxGroup buildTaxGroup(TaxGroup taxGroup) {
		TGsTaxGroup obj = new TGsTaxGroup();
		obj.setCreatedAt(getCurrentTmeStamp());
		obj.setUpdatedAt(getCurrentTmeStamp());
		obj.setCreatedBy(taxGroup.getCreated_by());
		obj.setGroupName(taxGroup.getGroup_name());
		obj.setSelectOrg(taxGroup.getSelect_org());
		obj.setUpdatedBy(taxGroup.getUpdated_by());
		//obj.setTGsOrganaization(new TGsOrganaization().getParentOrgId());
		return obj;
	}

	@Override
	public TGsTaxAccount buildTaxAccount(TaxAccount taxAccount) {
		TGsTaxAccount tGsTaxAccount = new TGsTaxAccount();
		tGsTaxAccount.setAccountType(taxAccount.getAccountType());
		tGsTaxAccount.setCreatedAt(getCurrentTmeStamp());
		tGsTaxAccount.setCreatedBy(taxAccount.getCreatedBy());
		tGsTaxAccount.setTaxAccount(taxAccount.getTaxAccount());
		tGsTaxAccount.setTaxAccountName(taxAccount.getTaxAccountName());
		tGsTaxAccount.setTaxGroup(taxAccount.getTaxGroup());
		tGsTaxAccount.setTaxNature(taxAccount.getTaxNature());
		//tGsTaxAccount.setTGsOrganaization(null);
		tGsTaxAccount.setUpdatedAt(getCurrentTmeStamp());
		tGsTaxAccount.setUpdatedBy(taxAccount.getUpdatedBy());
		tGsTaxAccount.setStatus(GsAppConstants.ACTIVE);
		return tGsTaxAccount;
	}

	@Override
	public GsTaxAccountNature buildTaxAccountNature(TaxAccountNature taxAccountNature) {
		GsTaxAccountNature gsTaxAccountNature = new GsTaxAccountNature();
		gsTaxAccountNature.setTaxAccount(taxAccountNature.getTaxAccount());
		gsTaxAccountNature.setTaxAccountType(taxAccountNature.getTaxAccountType());
		gsTaxAccountNature.setTaxGroup(taxAccountNature.getTaxGroup());
		gsTaxAccountNature.setTaxAccountType(taxAccountNature.getTaxAccountType());
		return gsTaxAccountNature;
	}

	@Override
	public GsTaxSection buildTaxSection(TaxSection taxSection) {
		GsTaxSection gsTaxSection = new GsTaxSection();
		gsTaxSection.setTaxSectionName(taxSection.getStatus());
		gsTaxSection.setStatus(GsAppConstants.ACTIVE);
		return gsTaxSection;
	}

	@Override
	public TGsTaxMapping buildTaxMapping(TaxMapping taxMapping) {
		 ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(TaxMapping.class,TGsTaxMapping.class).addMappings(mapper -> {
		        mapper.skip(TGsTaxMapping::setGsTaxMappingId);
		 });
		 TGsTaxMapping tGsTaxMapping = modelMapper.map(taxMapping,TGsTaxMapping.class);
		 tGsTaxMapping.setCreatedAt(getCurrentTmeStamp());
		 tGsTaxMapping.setUpdatedAt(getCurrentTmeStamp());
		return tGsTaxMapping;
	}

	@Override
	public GsGLAccount buildGlAccount(GLAccount glAccount) {
		ModelMapper modelMapper = new ModelMapper();
		 modelMapper.typeMap(GLAccount.class,GsGLAccount.class).addMappings(mapper -> {
		        mapper.skip(GsGLAccount::setId);
		 });
		 GsGLAccount gsGLAccount = modelMapper.map(glAccount,GsGLAccount.class);
		 gsGLAccount.setCreatedAt(getCurrentTmeStamp());
		 gsGLAccount.setUpdatedAt(getCurrentTmeStamp());
		 return gsGLAccount;
	}

	
	/*
	 * Coverts date to string
	 */
	@Override
	public String convertDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	/*
	 * converts string date
	 */
	@Override
	public java.util.Date covertStringToDate(String date) throws ParseException {
		log.debug("covertStringToDate::"+date);
		//return Date.valueOf(date);
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}

	/*
	@Override
	public java.util.Date convertStringToDate(String date) throws ParseException {
		log.debug("covertStringToDate::"+date);
		//return Date.valueOf(date);
		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}
*/
	@Override
	public Timestamp getCurrentTmeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}


	

	@Override
	public TGsAssetNature buildAssetNature(AssetNature assetNature) {
		TGsAssetNature tGsAssetNature = new TGsAssetNature();
		tGsAssetNature.setGsAssetNatureId(assetNature.getAssetNatureId());
		tGsAssetNature.setAssetNatureName(assetNature.getAssetNatureName());
		tGsAssetNature.setOrgName(assetNature.getOrgName());
		tGsAssetNature.setUserId(assetNature.getUserId());
		tGsAssetNature.setAssetGroup(assetNature.getAssetGroup());
		tGsAssetNature.setStatus(assetNature.getStatus());
		tGsAssetNature.setParentOrgId(assetNature.getParentOrgId());
		tGsAssetNature.setUpdatedAt(currenttimestamp);
		tGsAssetNature.setCreatedAt(currenttimestamp);
		tGsAssetNature.setCreatedBy("user");
		tGsAssetNature.setUpdatedBy("user");
		
//		TGsOrganaization tgOrganization = new TGsOrganaization();
//		tgOrganization.setParentOrgId(assetNature.getParentOrgId());
//		tgOrganization.setOrgName(assetNature.getOrgName());
//		tGsAssetNature.setTGsOrganaization(tgOrganization);

		return tGsAssetNature;
	}

	@Override
	public TGsAssetGroup buildAssetGroup(AssetGroup assetGroup) {
		TGsAssetGroup tGsAssetGroup = new TGsAssetGroup();
		//tGsAssetGroup.setGsAssetGroupId(assetGroup.getAssetGroupId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(assetGroup.getOrgId());
		tGsAssetGroup.setAssetClassName(assetGroup.getAssetClassName());
		tGsAssetGroup.setAssetNature(assetGroup.getAssetNature());
		//tGsAssetGroup.setTGsOrganaization(tgOrganization);
		tGsAssetGroup.setUserId(assetGroup.getUserId());
		tGsAssetGroup.setStatus(assetGroup.getStatus());
		tGsAssetGroup.setParentOrgId(assetGroup.getParentOrgId());
		tGsAssetGroup.setUpdatedAt(currenttimestamp);
		tGsAssetGroup.setCreatedAt(currenttimestamp);
		tGsAssetGroup.setCreatedBy("user");
		tGsAssetGroup.setUpdatedBy("user");

		return tGsAssetGroup;
	}

	@Override
	public TGsLiabilityGlHead buildLiabilityGlHead(LiabilityGLHead liabilityGlHead) {
		TGsLiabilityGlHead tGsLiabilityGlHead = new TGsLiabilityGlHead();
		//tGsLiabilityGlHead.setGsLiabilityGlHeadId(liabilityGlHead.getLiabilityGLHeadId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(liabilityGlHead.getOrgId());
		tGsLiabilityGlHead.setLiabilityGlHeadName(liabilityGlHead.getLiabilityGLHeadName());
		//tGsLiabilityGlHead.setTGsOrganaization(tgOrganization);
		tGsLiabilityGlHead.setUserId(liabilityGlHead.getUserId());
		tGsLiabilityGlHead.setStatus(liabilityGlHead.getStatus());
		tGsLiabilityGlHead.setParentOrgId(liabilityGlHead.getParentOrgId());
		tGsLiabilityGlHead.setUpdatedAt(currenttimestamp);
		tGsLiabilityGlHead.setCreatedAt(currenttimestamp);
		tGsLiabilityGlHead.setCreatedBy("user");
		tGsLiabilityGlHead.setUpdatedBy("user");
		return tGsLiabilityGlHead;
	}

	@Override
	public TGsLiabilityGlSubhead buildLiabilityGlSubhead(LiabilityGLSubHead liabilityGlSubhead) {
		TGsLiabilityGlSubhead tGsLiabilityGlSubhead = new TGsLiabilityGlSubhead();
		tGsLiabilityGlSubhead.setGsLiabilityGlSubheadId(tGsLiabilityGlSubhead.getGsLiabilityGlSubheadId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(liabilityGlSubhead.getOrgId());
		tGsLiabilityGlSubhead.setLiabilityGlHead(liabilityGlSubhead.getLiabilityGlHead());
		tGsLiabilityGlSubhead.setLiabilityGlSubheadName(liabilityGlSubhead.getLiabilityGLSubHeadName());
		//tGsLiabilityGlSubhead.set(liabilityGlSubhead.getOrgName());
		//tGsLiabilityGlSubhead.setTGsOrganaization(tgOrganization);
		tGsLiabilityGlSubhead.setUserId(liabilityGlSubhead.getUserId());
		tGsLiabilityGlSubhead.setStatus(liabilityGlSubhead.getStatus());
		tGsLiabilityGlSubhead.setParentOrgId(liabilityGlSubhead.getParentOrgId());
		tGsLiabilityGlSubhead.setUpdatedAt(currenttimestamp);
		tGsLiabilityGlSubhead.setCreatedAt(currenttimestamp);
		tGsLiabilityGlSubhead.setCreatedBy("user");
		tGsLiabilityGlSubhead.setUpdatedBy("user");
		return tGsLiabilityGlSubhead;
	}

	@Override
	public TGsGlGroup buildGlGroup(GLGroup glGroup) {
		TGsGlGroup tGsGlGroup = new TGsGlGroup();
		//tGsGlGroup.setGsGlGroupId(glGroup.getGlGroupId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(glGroup.getOrgId());
		tGsGlGroup.setGlGroupName(glGroup.getGlGropName());
		//tGsGlGroup.setTGsOrganaization(tgOrganization);
		tGsGlGroup.setUserId(glGroup.getUserId());
		tGsGlGroup.setStatus(glGroup.getStatus());
		tGsGlGroup.setParentOrgId(glGroup.getParentOrgId());
		tGsGlGroup.setAccountType(glGroup.getAccountType());
		tGsGlGroup.setGlGroupName(glGroup.getGlGropName());
		tGsGlGroup.setOrgName(glGroup.getOrgName());
		tGsGlGroup.setGlHead(glGroup.getGlHead());
		tGsGlGroup.setUpdatedAt(currenttimestamp);
		tGsGlGroup.setCreatedAt(currenttimestamp);
		tGsGlGroup.setCreatedBy("user");
		tGsGlGroup.setUpdatedBy("user");
		return tGsGlGroup;
		
	}

	@Override
	public TGsGlSubGroup buildGlSubGroup(GLSubGroup glSubGroup) {
		TGsGlSubGroup tGsGlSubGroup = new TGsGlSubGroup();
		//tGsGlSubGroup.setGsGlSubGroupId(glSubGroup.getGlSubGroupId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(glSubGroup.getOrgId());
		tGsGlSubGroup.setGlGroupName(glSubGroup.getGlGroupName());
		tGsGlSubGroup.setGlSubGroupName(glSubGroup.getGlSubGropName());
		tGsGlSubGroup.setUserId(glSubGroup.getUserId());
		tGsGlSubGroup.setParentOrgId(glSubGroup.getParentOrgId());
		tGsGlSubGroup.setStatus(glSubGroup.getStatus());
		//tGsGlSubGroup.setTGsOrganaization(tgOrganization);
		tGsGlSubGroup.setUpdatedAt(currenttimestamp);
		tGsGlSubGroup.setCreatedAt(currenttimestamp);
		tGsGlSubGroup.setCreatedBy("user");
		tGsGlSubGroup.setUpdatedBy("user");
		return tGsGlSubGroup;
		
	}

	@Override
	public TGsGlAccount buildGlSubGroup(GLAccount glAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TGsCostCenter buildCostCenter(CostCenter costCenter) {

		TGsCostCenter gsCostCenter = new TGsCostCenter();
		gsCostCenter.setCostCenterName(costCenter.getCostCenterName());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(costCenter.getOrgId());
		//gsCostCenter.setTGsOrganaization(tgOrganization);
		gsCostCenter.setUpdatedAt(currenttimestamp);
		gsCostCenter.setCreatedAt(currenttimestamp);
		gsCostCenter.setCreatedBy("user");
		gsCostCenter.setUpdatedBy("user");
		gsCostCenter.setParentOrgId(costCenter.getParentOrgId());
		gsCostCenter.setUserId(costCenter.getUserId());
		

		return gsCostCenter;
	}
	@Override
	public TGsProfitCenter buildProfitCenter(ProfitCenter profitCenter) {

		TGsProfitCenter gsProfitCenter = new TGsProfitCenter();
		gsProfitCenter.setProfitCenterName(profitCenter.getProfitCenterName());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(profitCenter.getOrgId());
		//gsProfitCenter.setTGsOrganaization(tgOrganization);
		gsProfitCenter.setUpdatedAt(currenttimestamp);
		gsProfitCenter.setCreatedAt(currenttimestamp);
		gsProfitCenter.setCreatedBy("user");
		gsProfitCenter.setUpdatedBy("user");
		gsProfitCenter.setStatus(profitCenter.getStatus());
		gsProfitCenter.setParentOrgId(profitCenter.getParentOrgId());
		gsProfitCenter.setUserId(profitCenter.getUserId());
		return gsProfitCenter;
	}

	@Override
	public TGsIncomeGlHead buildIncomeGlHead(IncomeGLHead incomeGLHead) {

		TGsIncomeGlHead gsIncomeGlHead = new TGsIncomeGlHead();
		//gsIncomeGlHead.setGsIncomeGlHeadId(incomeGLHead.getIncomeGlHeadId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(incomeGLHead.getOrgId());
		//gsIncomeGlHead.setTGsOrganaization(tgOrganization);
		gsIncomeGlHead.setParentOrgId(incomeGLHead.getParentOrgId());
		gsIncomeGlHead.setIncomeGlHeadName(incomeGLHead.getIncomeGlHeadName());
		gsIncomeGlHead.setUpdatedAt(currenttimestamp);
		gsIncomeGlHead.setCreatedAt(currenttimestamp);
		gsIncomeGlHead.setCreatedBy("user");
		gsIncomeGlHead.setUpdatedBy("user");
		gsIncomeGlHead.setStatus(incomeGLHead.getStatus());
		gsIncomeGlHead.setUserId(incomeGLHead.getUserId());

		return gsIncomeGlHead;
	}

	@Override
	public TGsExpenditureGlHead buildExpenditureglhead(ExpenditureGLHead expendtureGLHead) {

		TGsExpenditureGlHead gsExpenditureGlHead = new TGsExpenditureGlHead();
		//gsExpenditureGlHead.setGsExpenditureGlHeadId(expendtureGLHead.getExpenditureGlHeadId());
		//TGsOrganaization tgOrganization = new TGsOrganaization();
		//tgOrganization.setParentOrgId(expendtureGLHead.getOrgId());
		gsExpenditureGlHead.setExpenditureGlHeadName(expendtureGLHead.getExpenditureGlHeadName());
		//gsExpenditureGlHead.setTGsOrganaization(tgOrganization);
		gsExpenditureGlHead.setStatus(expendtureGLHead.getStatus());
		gsExpenditureGlHead.setParentOrgId(expendtureGLHead.getParentOrgId());
		gsExpenditureGlHead.setUserId(expendtureGLHead.getUserId());
		gsExpenditureGlHead.setUpdatedAt(currenttimestamp);
		gsExpenditureGlHead.setCreatedAt(currenttimestamp);
		gsExpenditureGlHead.setCreatedBy(expendtureGLHead.getCreatedBy());
		gsExpenditureGlHead.setUpdatedBy(expendtureGLHead.getCreatedBy());

	
		
		
		return gsExpenditureGlHead;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.automate.util.GsUtil#isValidUserAndParenetOrg(int, int)
	 * 
	 * Validates passid userid and parent orgid is valid or not.
	 * 
	 */
	@Override
	public boolean isValidUserAndParenetOrg(int userId, int parentOrgId) {

		boolean flag = false;
		TGsUser user = userRepo.findByuseridAndParentOrgId(userId, parentOrgId);

		if (user != null) {
			flag = true;
		}
		return flag;
	}
}
