package com.automate.service.impl;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.automate.constants.GsAppConstants;
import com.automate.constants.Responsecode;
import com.automate.dao.BusinessUnitRepo;
import com.automate.dao.CalendarYearRepo;
import com.automate.dao.CountryRepo;
import com.automate.dao.CurrencyRepo;
import com.automate.dao.FYandCYConfigurationRepo;
import com.automate.dao.FinancialYearRepo;
import com.automate.dao.GsDynamicWfRepository;
import com.automate.dao.GsGlAccountRepository;
import com.automate.dao.GsModuleRepo;
import com.automate.dao.GsSubModuleRepo;
import com.automate.dao.GsTaxAccountNatureRepository;
import com.automate.dao.GsTaxAccountRepository;
import com.automate.dao.GsTaxGroupRepository;
import com.automate.dao.GsTaxMappingRepository;
import com.automate.dao.GsTaxSectionRepository;
import com.automate.dao.GsVendorRepository;
import com.automate.dao.LanguageRepo;
import com.automate.dao.ModuleRepo;
import com.automate.dao.OrgMappingRepo;
import com.automate.dao.OrganaizationRepo;
import com.automate.dao.RuleConfigurationRepo;
import com.automate.dao.SubModuleRepo;
import com.automate.dao.TGsAssetGroupRepo;
import com.automate.dao.TGsAssetNatureRepo;
import com.automate.dao.TGsCostCenterRepo;
import com.automate.dao.TGsExpenditureGlHeadRepo;
import com.automate.dao.TGsGLAccountRepo;
import com.automate.dao.TGsGLGroupRepo;
import com.automate.dao.TGsGLSubGroupRepo;
import com.automate.dao.TGsIncomeGlHeadRepo;
import com.automate.dao.TGsLiabilityGLHeadRepo;
import com.automate.dao.TGsLiabilityGLSubHeadRepo;
import com.automate.dao.TGsProfitCenterRepo;
import com.automate.dao.TgsUserRepo;
import com.automate.dao.VerticalRepo;
import com.automate.entity.globalsettings.CalendarYearMapping;
import com.automate.entity.globalsettings.CountryLocation;
import com.automate.entity.globalsettings.FinancialYearMapping;
import com.automate.entity.globalsettings.GsGLAccount;
import com.automate.entity.globalsettings.GsTaxAccountNature;
import com.automate.entity.globalsettings.GsTaxSection;
import com.automate.entity.globalsettings.GsVendor;
import com.automate.entity.globalsettings.ModuleMapping;
import com.automate.entity.globalsettings.OrgMapping;
import com.automate.entity.globalsettings.TGsAssetGroup;
import com.automate.entity.globalsettings.TGsAssetNature;
import com.automate.entity.globalsettings.TGsBusinessUnit;
import com.automate.entity.globalsettings.TGsCalendarYear;
import com.automate.entity.globalsettings.TGsCostCenter;
import com.automate.entity.globalsettings.TGsCountry;
import com.automate.entity.globalsettings.TGsCurrency;
import com.automate.entity.globalsettings.TGsDynamicWorkflow;
import com.automate.entity.globalsettings.TGsExpenditureGlHead;
import com.automate.entity.globalsettings.TGsFinancialYear;
import com.automate.entity.globalsettings.TGsGlAccount;
import com.automate.entity.globalsettings.TGsGlGroup;
import com.automate.entity.globalsettings.TGsGlSubGroup;
import com.automate.entity.globalsettings.TGsIncomeGlHead;
import com.automate.entity.globalsettings.TGsLanguage;
import com.automate.entity.globalsettings.TGsLiabilityGlHead;
import com.automate.entity.globalsettings.TGsLiabilityGlSubhead;
import com.automate.entity.globalsettings.TGsModule;
import com.automate.entity.globalsettings.TGsOrganaization;
import com.automate.entity.globalsettings.TGsProfitCenter;
import com.automate.entity.globalsettings.TGsRuleConfiguration;
import com.automate.entity.globalsettings.TGsSubModule;
import com.automate.entity.globalsettings.TGsTaxAccount;
import com.automate.entity.globalsettings.TGsTaxGroup;
import com.automate.entity.globalsettings.TGsTaxMapping;
import com.automate.entity.globalsettings.TGsUser;
import com.automate.entity.globalsettings.TGsVertical;
import com.automate.entity.globalsettings.TgsApprover;
import com.automate.enums.Status;
import com.automate.enums.YearType;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.AssetGroup;
import com.automate.model.globalsettings.AssetNature;
import com.automate.model.globalsettings.BusinessUnits;
import com.automate.model.globalsettings.CalendarYear;
import com.automate.model.globalsettings.CostCenter;
import com.automate.model.globalsettings.Country;
import com.automate.model.globalsettings.CountryInfo;
import com.automate.model.globalsettings.CountryLocInfo;
import com.automate.model.globalsettings.Currency;
import com.automate.model.globalsettings.CurrencyCountryInfo;
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
import com.automate.model.globalsettings.Module;
import com.automate.model.globalsettings.ProfitCenter;
import com.automate.model.globalsettings.RuleConfiguration;
import com.automate.model.globalsettings.RuleConfigurationInfo;
import com.automate.model.globalsettings.SaveCalendarYear;
import com.automate.model.globalsettings.SaveFinancialYear;
import com.automate.model.globalsettings.SubModule;
import com.automate.model.globalsettings.TaxAccount;
import com.automate.model.globalsettings.TaxAccountNature;
import com.automate.model.globalsettings.TaxGroup;
import com.automate.model.globalsettings.TaxMapping;
import com.automate.model.globalsettings.TaxSection;
import com.automate.model.globalsettings.UserOrganisation;
import com.automate.model.globalsettings.Vendor;
import com.automate.model.globalsettings.Verticals;
import com.automate.model.globalsettings.YearsPeriod;
import com.automate.response.CustomResponse;
import com.automate.response.ExpenditureGlHeadResponse;
import com.automate.response.IncomeGlHeadResponse;
import com.automate.service.GsService;
import com.automate.util.GsUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author sruja
 *
 */

@Service
@Slf4j
public class GsServiceImpl implements GsService {

	@Autowired
	TGsCostCenterRepo gsCostCenter;

	@Autowired
	TGsProfitCenterRepo gsProfitCenterRepo;

	@Autowired
	TGsIncomeGlHeadRepo gsIncomeGlHeadRepo;

	@Autowired
	TGsExpenditureGlHeadRepo gsExpenditureGlHeadRepo;

	@Autowired
	TGsAssetGroupRepo gsAssetGroupRepo;

	@Autowired
	TGsAssetNatureRepo gsAssetNatureRepo;

	@Autowired
	TGsLiabilityGLHeadRepo gsLiabilityGLHeadRepo;

	@Autowired
	TGsLiabilityGLSubHeadRepo gsLiabilityGLSubHeadRepo;

	@Autowired
	TGsGLAccountRepo gsGLAccountRepo;

	@Autowired
	TGsGLGroupRepo gsGLGroupRepo;

	@Autowired
	TGsGLSubGroupRepo gsGLSubGroupRepo;

	@Autowired
	GsUtil gsUtil;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ModuleRepo moduleRepo;

	@Autowired
	SubModuleRepo subModuleRepo;

	@Autowired
	RuleConfigurationRepo ruleConfigRepo;

	@Autowired
	FinancialYearRepo financialYearRepo;

	@Autowired
	CalendarYearRepo calendarRepo;

	@Autowired
	GsVendorRepository gsVendorRepository;

	@Autowired
	GsTaxGroupRepository gsTaxGroupRepository;

	@Autowired
	GsTaxAccountRepository gsTaxAccountRepository;

	@Autowired
	GsTaxAccountNatureRepository gsTaxAccountNatureRepository;

	@Autowired
	GsTaxSectionRepository gsTaxSectionRepository;

	@Autowired
	GsTaxMappingRepository gsTaxMappingRepository;

	@Autowired
	GsGlAccountRepository gsGlAccountRepository;

	@Resource
	OrganaizationRepo organizationRepo;

	@Resource
	BusinessUnitRepo businessUnitRepo;

	@Resource
	FYandCYConfigurationRepo fycyRepo;

	@Autowired
	OrganaizationRepo organaizationRepo;

	@Resource
	OrgMappingRepo orgMappingRepo;

	@Autowired
	GsModuleRepo gsModuleRepo;

	@Autowired
	GsSubModuleRepo gsSubModuleRepo;

	@Resource
	GsUtil gsutil;

	@Resource
	FYandCYConfigurationRepo fYandCYRepo;

	@Autowired
	CountryRepo countryRepo;

	@Autowired
	CurrencyRepo currencyRepo;

	@Autowired
	LanguageRepo languageRepo;

	@Autowired
	VerticalRepo verticalRepo;

	@Autowired
	Environment env;

	@Autowired
	GsDynamicWfRepository gsDynamicWfRepository;

	@Autowired
	TgsUserRepo userRepo;

	@Override
	public Login validateLogIn(LoginRequest request) throws GlobalSettingsServiceException {
		Login response = new Login();
		TGsUser user = new TGsUser();
		boolean isUserPassValid = false;
		log.info("Request for login user  " + request.getUsername());
		try {
			// checking passed user name and password is available or not
			user = userRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

			if (user != null) {
				response.setUserId(user.getUserid());
				response.setParentOrgId(user.getParentOrgId());
				user.setCreatedAt(gsutil.getCurrentTmeStamp());
				user.setUpdatedAt(gsutil.getCurrentTmeStamp());
				response.setStatus(Status.SUCCESS);
			} else {
				response.setStatus(Status.FAILED);
				throw new GlobalSettingsServiceException("user not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to process login in request ", e);
			response.setStatus(Status.FAILED);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public CustomResponse saveVendorMaster(Vendor vendor) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.saveVendorMaster()");
		try {
			GsVendor gsVendor = gsUtil.buildVendorMaster(vendor);
			gsVendor.setTGsOrganaization(getOrg(vendor.getParentOrgId()));
			gsVendor.setTGsVertical(getVertical(vendor.getVerticalId()));
			gsVendor.setTGsBusinessUnit(getBU(vendor.getBusinessUnitId()));
			;
			gsVendorRepository.save(gsVendor);
		} catch (DataAccessException e) {
			log.error("Exception occured in saveVendorMaster ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	public TGsOrganaization getOrg(int id) throws GlobalSettingsServiceException {
		TGsOrganaization tGsOrganaization = null;
		Optional<TGsOrganaization> opt = organaizationRepo.findById(id);
		if (opt.isPresent()) {
			tGsOrganaization = new TGsOrganaization();
			tGsOrganaization = opt.get();
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("INVALID_ORG_ID"), HttpStatus.BAD_REQUEST);
		}
		return tGsOrganaization;
	}

	public TGsVertical getVertical(int id) throws GlobalSettingsServiceException {
		TGsVertical tGsVertical = null;
		Optional<TGsVertical> opt = verticalRepo.findById(id);
		if (opt.isPresent()) {
			tGsVertical = new TGsVertical();
			tGsVertical = opt.get();
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("INVALID_VERTICAL_ID"), HttpStatus.BAD_REQUEST);
		}

		return tGsVertical;
	}

	public TGsBusinessUnit getBU(int id) throws GlobalSettingsServiceException {
		TGsBusinessUnit tGsBusinessUnit = null;
		Optional<TGsBusinessUnit> opt = businessUnitRepo.findById(id);
		if (opt.isPresent()) {
			tGsBusinessUnit = new TGsBusinessUnit();
			tGsBusinessUnit = opt.get();
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("INVALID_BU_ID"), HttpStatus.BAD_REQUEST);
		}
		return tGsBusinessUnit;

	}

	@Override
	public CustomResponse createTaxGroup(TaxGroup taxGroup) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createTaxGroup()");
		try {
			TGsTaxGroup gsTaxGroup = gsUtil.buildTaxGroup(taxGroup);
			gsTaxGroup.setTGsOrganaization(getOrg(taxGroup.getParentOrgId()));
			gsTaxGroup.setTGsVertical(getVertical(taxGroup.getVerticalId()));
			gsTaxGroup.setTGsBusinessUnit(getBU(taxGroup.getBusinessUnitId()));

			gsTaxGroupRepository.save(gsTaxGroup);
		} catch (DataAccessException e) {
			log.error("Exception occured in createTaxGroup ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createTaxAccount(TaxAccount taxAccount) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createTaxAccount()");
		try {
			TGsTaxAccount gsTaxAccount = gsUtil.buildTaxAccount(taxAccount);
			gsTaxAccount.setTGsOrganaization(getOrg(taxAccount.getParentOrgId()));
			gsTaxAccount.setTGsVertical(getVertical(taxAccount.getVerticalId()));
			gsTaxAccount.setTGsBusinessUnit(getBU(taxAccount.getBusinessUnitId()));
			gsTaxAccountRepository.save(gsTaxAccount);

		} catch (DataAccessException e) {
			log.error("Exception occured in createTaxAccount ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createTaxAccountNature(TaxAccountNature taxAccountNature)
			throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createTaxAccountNature()");
		try {
			gsTaxAccountNatureRepository.save(gsUtil.buildTaxAccountNature(taxAccountNature));
		} catch (DataAccessException e) {
			log.error("Exception occured in createTaxAccountNature ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createTaxSection(TaxSection taxSection) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createTaxSection()");
		try {
			GsTaxSection gsTaxSection = gsUtil.buildTaxSection(taxSection);
			gsTaxSection.setTGsOrganaization(getOrg(taxSection.getParentOrgId()));
			gsTaxSection.setTGsVertical(getVertical(taxSection.getVerticalId()));
			gsTaxSection.setTGsBusinessUnit(getBU(taxSection.getBusinessUnitId()));
			gsTaxSectionRepository.save(gsTaxSection);
		} catch (DataAccessException e) {
			log.error("Exception occured in createTaxSection ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createTaxMapping(TaxMapping taxMapping) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createTaxMapping()");
		try {
			TGsTaxMapping gsTaxMapping = gsUtil.buildTaxMapping(taxMapping);
			gsTaxMapping.setTGsOrganaization(getOrg(taxMapping.getParentOrgId()));
			gsTaxMapping.setTGsVertical(getVertical(taxMapping.getVerticalId()));
			gsTaxMapping.setTGsBusinessUnit(getBU(taxMapping.getBusinessUnitId()));
			gsTaxMappingRepository.save(gsTaxMapping);
		} catch (DataAccessException e) {
			log.error("Exception occured in createTaxMapping ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createGlAccount(GLAccount glAccount) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createGlAccount()");
		try {
			gsGlAccountRepository.save(gsUtil.buildGlAccount(glAccount));
		} catch (DataAccessException e) {
			log.error("Exception occured in createGlAccount ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public List<GLAccount> getGlAccountData(int pageNo, int size) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, size);
		List<GLAccount> responseList = null;
		try {
			responseList = Arrays
					.asList(modelMapper.map(gsGlAccountRepository.findAll(pageable).toList(), GLAccount[].class));
		} catch (DataAccessException e) {
			log.error("Exception occured in getGlAccountData ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public GLAccount updateGlAccountData(GLAccount glAccount) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.updateGlAccountData()");

		try {
			Optional<GsGLAccount> optional = gsGlAccountRepository.findById(glAccount.getId());
			if (optional.isPresent()) {
				GsGLAccount gsGLAccount = optional.get();
				gsGLAccount.setGlAcctName(glAccount.getGlAcctName());
				gsGLAccount.setGlGroupName(gsGLAccount.getGlGroupName());
				gsGLAccount.setGlSubGroupName(gsGLAccount.getGlSubGroupName());
				gsGLAccount.setGlVisibleToVendor(gsGLAccount.getGlVisibleToVendor());
				gsGLAccount.setStatus(gsGLAccount.getStatus());
				gsGLAccount.setUpdatedAt(gsutil.getCurrentTmeStamp());
				gsGLAccount.setUpdatedBy(glAccount.getUpdatedBy());

				GsGLAccount gsGLAccountResponse = gsGlAccountRepository.save(gsGLAccount);
				glAccount = modelMapper.map(gsGLAccountResponse, GLAccount.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (DataAccessException e) {
			log.error("Exception occured in createGlAccount ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return glAccount;
	}

	@Override
	public List<TaxAccountNature> getTaxAccNature(int pageNo, int size) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, size);
		List<TaxAccountNature> responseList = null;
		try {
			responseList = Arrays.asList(
					modelMapper.map(gsTaxAccountNatureRepository.findAll(pageable).toList(), TaxAccountNature[].class));
		} catch (DataAccessException e) {
			log.error("Exception occured in getTaxAccNature ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;

	}

	@Override
	public TaxAccountNature updateTaxAccountNature(TaxAccountNature taxAccountNature)
			throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.updateTaxAccountNature()");
		try {
			GsTaxAccountNature gsTaxAccountNature = gsTaxAccountNatureRepository
					.save(modelMapper.map(taxAccountNature, GsTaxAccountNature.class));
			taxAccountNature = modelMapper.map(gsTaxAccountNature, TaxAccountNature.class);
		} catch (DataAccessException e) {
			log.error("Exception occured in updateTaxAccountNature ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return taxAccountNature;

	}

	@Override
	public List<TaxMapping> getTaxMapping(int pageNo, int size) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, size);
		List<TaxMapping> responseList = null;
		try {
			responseList = Arrays
					.asList(modelMapper.map(gsTaxMappingRepository.findAll(pageable).toList(), TaxMapping[].class));
		} catch (DataAccessException e) {
			log.error("Exception occured in getTaxMapping ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public TaxMapping updateTaxMapping(TaxMapping taxMapping) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.updateTaxMapping()");

		try {
			Optional<TGsTaxMapping> optional = gsTaxMappingRepository.findById(taxMapping.getGsTaxMappingId());
			if (optional.isPresent()) {
				TGsTaxMapping tGsTaxMapping = optional.get();
				tGsTaxMapping.setEffectiveFrom(taxMapping.getEffectiveFrom());
				tGsTaxMapping.setEffectiveTo(taxMapping.getEffectiveTo());
				tGsTaxMapping.setServiceType(taxMapping.getServiceType());
				tGsTaxMapping.setSupplyType(taxMapping.getSupplyType());
				tGsTaxMapping.setTaxAccount(taxMapping.getTaxAccount());
				tGsTaxMapping.setTaxGroup(taxMapping.getTaxGroup());
				tGsTaxMapping.setTaxRate(taxMapping.getTaxRatePerc());
				tGsTaxMapping.setTaxSection(taxMapping.getTaxSection());
				tGsTaxMapping.setUpdatedAt(gsutil.getCurrentTmeStamp());
				tGsTaxMapping.setUpdatedBy(taxMapping.getUpdatedBy());
				tGsTaxMapping.setVendorType(taxMapping.getVendorType());

				TGsTaxMapping tGsTaxMappingResponse = gsTaxMappingRepository.save(tGsTaxMapping);
				taxMapping = modelMapper.map(tGsTaxMappingResponse, TaxMapping.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

		} catch (DataAccessException e) {
			log.error("Exception occured in updateTaxMapping ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return taxMapping;
	}

	@Override
	public List<TaxAccount> getTaxAccList(int pageNo, int size) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, size);
		List<TaxAccount> responseList = null;
		try {
			responseList = Arrays
					.asList(modelMapper.map(gsTaxAccountRepository.findAll(pageable).toList(), TaxAccount[].class));
		} catch (DataAccessException e) {
			log.error("Exception occured in getTaxAccNature ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public TaxAccount updateTaxAccounts(TaxAccount taxAccount) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.updateTaxAccountNature()");
		try {
			Optional<TGsTaxAccount> optional = gsTaxAccountRepository.findById(taxAccount.getGsTaxAccountId());
			if (optional.isPresent()) {
				TGsTaxAccount tGsTaxAccount = optional.get();
				tGsTaxAccount.setAccountType(taxAccount.getAccountType());
				tGsTaxAccount.setTaxAccount(taxAccount.getTaxAccount());
				tGsTaxAccount.setTaxAccountName(taxAccount.getTaxAccountName());
				tGsTaxAccount.setTaxGroup(taxAccount.getTaxGroup());
				tGsTaxAccount.setTaxNature(taxAccount.getTaxNature());
				tGsTaxAccount.setUpdatedAt(gsutil.getCurrentTmeStamp());
				tGsTaxAccount.setUpdatedBy(taxAccount.getUpdatedBy());

				TGsTaxAccount tGsTaxAccountResponse = gsTaxAccountRepository.save(tGsTaxAccount);
				taxAccount = modelMapper.map(tGsTaxAccountResponse, TaxAccount.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

		} catch (DataAccessException e) {
			log.error("Exception occured in updateTaxAccounts ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return taxAccount;
	}

	@Override
	public CustomResponse saveDynamicWorkflow(DynamicWorkflow dynamicWorkflow) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.saveDynamicWorkflow()");
		try {
			gsDynamicWfRepository.save(buildDynamicWf(dynamicWorkflow));
		} catch (DataAccessException e) {
			log.error("Exception occured in saveDynamicWorkflow ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	public TGsDynamicWorkflow buildDynamicWf(DynamicWorkflow dynamicWorkflow) throws GlobalSettingsServiceException {
		TGsDynamicWorkflow tGsDynamicWorkflow = new TGsDynamicWorkflow();
		tGsDynamicWorkflow.setCreatedAt(gsUtil.getCurrentTmeStamp());
		tGsDynamicWorkflow.setCreatedBy(dynamicWorkflow.getCreatedBy());
		tGsDynamicWorkflow.setUpdateBy(dynamicWorkflow.getUpdatedBy());
		tGsDynamicWorkflow.setUpdatedAt(gsutil.getCurrentTmeStamp());

		tGsDynamicWorkflow.setTGsModule(gsModuleRepo.findById(dynamicWorkflow.getModuleId()).orElse(new TGsModule()));
		tGsDynamicWorkflow
				.setTGsSubModule(gsSubModuleRepo.findById(dynamicWorkflow.getSubModulId()).orElse(new TGsSubModule()));
		List<TgsApprover> tGsApproverList = new ArrayList<>();
		dynamicWorkflow.getApproverList().forEach(x -> {
			TgsApprover tgsApprover = new TgsApprover();
			tgsApprover.setApproverName(x.getApproverName());
			tgsApprover.setApproverId(x.getApproverId());
			tgsApprover.setApproverType(x.getApproverType());
			tGsApproverList.add(tgsApprover);
		});
		tGsDynamicWorkflow.setApprovers(tGsApproverList);
		tGsDynamicWorkflow.setTGsOrganaization(getOrg(dynamicWorkflow.getParentOrgId()));
		tGsDynamicWorkflow.setTGsVertical(getVertical(dynamicWorkflow.getVerticalId()));
		tGsDynamicWorkflow.setTGsBusinessUnit(getBU(dynamicWorkflow.getBusinessUnitId()));
		;
		return tGsDynamicWorkflow;
	}

	/*
	 * fetches organization's financial year
	 */
	@Override
	public String getFinancialYear(int parentOrgId, int userId) throws GlobalSettingsServiceException {
		FinancialYear fcYear = new FinancialYear();
		Gson gson = new Gson();

		TGsFinancialYear fcyEntity = null;
		try {
			fcyEntity = financialYearRepo.findByParentOrgIdAndUserId(parentOrgId, userId);

			if (fcyEntity != null) {
				fcYear.setEndDate(gsutil.convertDateToString(fcyEntity.getEndDate()));
				fcYear.setStartDate(gsutil.convertDateToString(fcyEntity.getStartDate()));
				fcYear.setFinancialYear(fcyEntity.getFinancialYear());
				fcYear.setFinancialYearId(fcyEntity.getGsFinancialYearId());
				fcYear.setParentOrgId(parentOrgId);

				List<YearsPeriod> fypList = new ArrayList<YearsPeriod>();
				for (FinancialYearMapping fym : fcyEntity.getFinancialYearMappings()) {
					YearsPeriod fyp = new YearsPeriod();
					fyp.setId(fym.getId());
					fyp.setCalendarPeriod(fym.getCalendarPeriod());
					fyp.setMonth(fym.getMonth());
					fyp.setPostingPeriod(fym.getPostingPeriod());

					fypList.add(fyp);
				}
				fcYear.setFcyPeriod(fypList);
				fcYear.setStatus(Status.SUCCESS);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to get financial year info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gson.toJson(fcYear).toString();
	}

	/*
	 * saves financial year
	 * 
	 */
	@Override
	public String saveFinancialYear(SaveFinancialYear saveFcy) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();
		TGsOrganaization orgEntity = null;
		try {
			orgEntity = organizationRepo.findByParentOrgId(saveFcy.getOrgId());

			TGsFinancialYear fcyEntity = null;
			FinancialYearMapping fcyMappingEntity = null;
			List<FinancialYearMapping> fcymList = null;
			;
			String startDateString = saveFcy.getStartDate();
			String endDateString = saveFcy.getEndDate();
			log.debug("startDateString::" + startDateString + ",endDateString:" + endDateString);

			Date startDate = gsutil.covertStringToDate(startDateString);
			Date endDate = gsutil.covertStringToDate(endDateString);

			Calendar startCal = Calendar.getInstance();
			startCal.setTimeInMillis(startDate.getTime());

			Calendar endCal = Calendar.getInstance();
			endCal.setTimeInMillis(endDate.getTime());

			int startMonth = startCal.get(startCal.MONTH) + 1;
			int endMonth = endCal.get(endCal.MONTH) + 1;
			int StartYear = startCal.get(startCal.YEAR);
			int endYear = endCal.get(endCal.YEAR);

			if (StartYear == endYear && startMonth != 1) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "Not Desired Calander");
				return response.toString();
			}

			if (StartYear > endYear) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "INVALID START YEAR");
				return response.toString();
			}
			int totalMonaths = (12 - startMonth) + (endMonth);
			// TODO total month range should be configure
			if (totalMonaths < 12 || totalMonaths > 14) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "INVALID CALENDAR MONTHS");
				return response.toString();
			}

			fcyEntity = new TGsFinancialYear();
			fcyEntity.setEndDate(endDate);
			fcyEntity.setStartDate(startDate);
			fcyEntity.setFinancialYear(String.valueOf(StartYear));
			fcyEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
			fcyEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
			fcymList = new ArrayList<FinancialYearMapping>();

			int year = 0;
			int postingCounter = 1;
			int calanderPeriod = 0;

			for (int i = startMonth; i <= totalMonaths; i++) {

				fcyMappingEntity = new FinancialYearMapping();
				// Fetching month name
				String month = new DateFormatSymbols().getMonths()[i];
				if (StartYear == endYear) {
					year = StartYear;
				} else {

					if (year == 0) {
						year = StartYear;
					}
					// if start and end year are different reseting the counter
					if (i == 12 && year == StartYear) {
						i = 0;
					}

				}
				// if 3 entries in list will increasing quarter counter.
				if (fcymList.size() % 3 == 0) {
					calanderPeriod++;
				}

				fcyMappingEntity.setMonth(month + year);

				fcyMappingEntity.setPostingPeriod(postingCounter);
				fcyMappingEntity.setTGsFinancialYear(fcyEntity);
				fcymList.add(fcyMappingEntity);
				if (fcymList.size() <= 12) {
					fcyMappingEntity.setCalendarPeriod("Q" + calanderPeriod);
				} else {
					fcyMappingEntity.setCalendarPeriod("PostingPeriod");
				}
				postingCounter++;

				// after reseting the count assigning end year to year.
				if (i == 0) {
					year = endYear;
					totalMonaths = totalMonaths - fcymList.size();
				}
			}
			fcyEntity.setFinancialYearMappings(fcymList);
			log.debug("Saving financialYearRepo");
			financialYearRepo.save(fcyEntity);
			response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to get country info", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * update financial year
	 * 
	 */
	@Override
	public String updateFinancialYear(FinancialYear fcYear) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		TGsFinancialYear fcyEntity = null;
		try {
			fcyEntity = financialYearRepo.findByGsFinancialYearId(fcYear.getFinancialYearId());
			if (fcyEntity != null) {
				fcyEntity.setEndDate(gsutil.covertStringToDate(fcYear.getEndDate()));
				fcyEntity.setStartDate(gsutil.covertStringToDate(fcYear.getStartDate()));
				fcyEntity.setFinancialYear(fcYear.getFinancialYear());
				fcyEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
				for (FinancialYearMapping fyMapping : fcyEntity.getFinancialYearMappings()) {
					YearsPeriod period = fcYear.getFcyPeriod().stream().filter(f -> f.getId() == fyMapping.getId())
							.findAny().orElse(null);
					if (period != null) {
						fyMapping.setCalendarPeriod(period.getCalendarPeriod());
						fyMapping.setMonth(period.getMonth());
						fyMapping.setPostingPeriod(period.getPostingPeriod());
						fyMapping.setTGsFinancialYear(fcyEntity);

					}
				}
				financialYearRepo.save(fcyEntity);
				response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to update financial year ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * delete passed id financial year
	 * 
	 */
	@Override
	public CustomResponse deleteFinancialYear(int id) throws GlobalSettingsServiceException {
		TGsFinancialYear fyEntity = new TGsFinancialYear();
		try {
			TGsFinancialYear fcy = financialYearRepo.findByGsFinancialYearId(id);
			if (fcy != null) {
				financialYearRepo.delete(fcy);
				fyEntity.setStatus(GsAppConstants.INACTIVE);
				financialYearRepo.save(fyEntity);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete financial year id ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetches calendar year
	 */
	@Override
	public String getCalendarYear(int parentOrgId, int userId) throws GlobalSettingsServiceException {
		CalendarYear cYear = new CalendarYear();

		Gson gson = new Gson();

		TGsCalendarYear cyEntity = null;

		try {
			cyEntity = calendarRepo.findByParentOrgIdAndUserId(parentOrgId, userId);
			if (cyEntity != null) {
				cYear.setEndDate(gsutil.convertDateToString(cyEntity.getEndDate()));
				cYear.setStartDate(gsutil.convertDateToString(cyEntity.getStartDate()));
				cYear.setCalendarYear(cyEntity.getCalendarYear());
				cYear.setCalendarYearId(cyEntity.getGsCalendarYearId());
				cYear.setParentOrgId(parentOrgId);

				List<YearsPeriod> cypList = new ArrayList<YearsPeriod>();
				for (CalendarYearMapping cym : cyEntity.getCalendarYearMappings()) {
					YearsPeriod cyp = new YearsPeriod();
					cyp.setId(cym.getId());
					cyp.setCalendarPeriod(cym.getCalendarPeriod());
					cyp.setMonth(cym.getMonth());
					cyp.setPostingPeriod(cym.getPostingPeriod());
					cypList.add(cyp);
				}
				cYear.setYearsPeriods(cypList);
				cYear.setStatus(Status.SUCCESS);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to get calendar year info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gson.toJson(cYear).toString();
	}

	/*
	 * saves calendar year
	 */
	@Override
	public String saveCalendarYear(SaveCalendarYear saveCy) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();
		TGsOrganaization orgEntity = null;
		try {
			orgEntity = organizationRepo.findByParentOrgId(saveCy.getOrgId());
			TGsCalendarYear cyEntity = null;
			CalendarYearMapping cyMappingEntity = null;
			List<CalendarYearMapping> cymList = null;

			String startDateString = saveCy.getStartDate();
			String endDateString = saveCy.getEndDate();

			Date startDate = gsutil.covertStringToDate(startDateString);
			Date endDate = gsutil.covertStringToDate(endDateString);

			Calendar startCal = Calendar.getInstance();
			startCal.setTimeInMillis(startDate.getTime());

			Calendar endCal = Calendar.getInstance();
			endCal.setTimeInMillis(endDate.getTime());

			int startMonth = startCal.get(startCal.MONTH) + 1;
			int endMonth = endCal.get(endCal.MONTH) + 1;
			int StartYear = startCal.get(startCal.YEAR);
			int endYear = endCal.get(endCal.YEAR);

			if (StartYear == endYear && startMonth != 1) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "Not Desired Calander");
				return response.toString();
			}

			if (StartYear > endYear) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "INVALID START YEAR");
				return response.toString();
			}
			// Added +1 because if it is a 7th month we need to consider it also.
			int totalMonaths = (12 - startMonth) + (endMonth);
			// TODO total month range should be configure
			if (totalMonaths < 12 || totalMonaths > 12) {
				response.addProperty("status", Status.FAILED.name());
				response.addProperty("errorCode", "INVALID CALENDAR MONTHS");
				return response.toString();
			}

			cyEntity = new TGsCalendarYear();
			cyEntity.setEndDate(endDate);
			cyEntity.setStartDate(startDate);
			cyEntity.setCalendarYear(String.valueOf(StartYear));
			cyEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
			cyEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());

			cymList = new ArrayList<CalendarYearMapping>();
			int year = 0;
			int postingCounter = 1;
			int calanderPeriod = 0;
			for (int i = startMonth; i <= totalMonaths; i++) {

				cyMappingEntity = new CalendarYearMapping();
				// Fetching month name
				String month = new DateFormatSymbols().getMonths()[i];
				if (StartYear == endYear) {
					year = StartYear;
				} else {

					if (year == 0) {
						year = StartYear;
					}
					// if start and end year are different reseting the counter
					if (i == 12 && year == StartYear) {
						i = 0;
					}

				}
				// if 3 entries in list will increasing quarter counter.
				if (cymList.size() % 3 == 0) {
					calanderPeriod++;
				}

				cyMappingEntity.setMonth(month + year);

				cyMappingEntity.setPostingPeriod(postingCounter);
				cyMappingEntity.setTGsCalendarYear(cyEntity);
				cymList.add(cyMappingEntity);
				if (cymList.size() <= 12) {
					cyMappingEntity.setCalendarPeriod("Q" + calanderPeriod);
				}
				postingCounter++;
				// after reseting the count assing end year to year.
				if (i == 0) {
					year = endYear;
					totalMonaths = totalMonaths - cymList.size();
				}
			}
			cyEntity.setCalendarYearMappings(cymList);
			calendarRepo.save(cyEntity);
			response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
		} catch (Exception e) {
			log.error("Failed to save calendar year info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * updated calendar year
	 */
	@Override
	public String updateCalendarYear(CalendarYear cYear) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		TGsCalendarYear cyEntity = null;
		try {
			cyEntity = calendarRepo.findByGsCalendarYearId(cYear.getCalendarYearId());
			if (cyEntity != null) {
				cyEntity.setEndDate(gsutil.covertStringToDate(cYear.getEndDate()));
				cyEntity.setStartDate(gsutil.covertStringToDate(cYear.getStartDate()));
				cyEntity.setCalendarYear(cYear.getCalendarYear());
				cyEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
				for (CalendarYearMapping cyMapping : cyEntity.getCalendarYearMappings()) {
					YearsPeriod period = cYear.getYearsPeriods().stream().filter(f -> f.getId() == cyMapping.getId())
							.findAny().orElse(null);
					if (period != null) {
						cyMapping.setCalendarPeriod(period.getCalendarPeriod());
						cyMapping.setMonth(period.getMonth());
						cyMapping.setPostingPeriod(period.getPostingPeriod());
						cyMapping.setTGsCalendarYear(cyEntity);

					}
				}
				calendarRepo.save(cyEntity);
				response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to update calendar year ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deletes calendar year
	 */
	@Override
	public CustomResponse deleteCalendarYear(int id) throws GlobalSettingsServiceException {
		TGsCalendarYear cyEntity = new TGsCalendarYear();
		try {
			TGsCalendarYear fcy = calendarRepo.findByGsCalendarYearId(id);
			if (fcy != null) {
				calendarRepo.delete(fcy);
				cyEntity.setStatus(GsAppConstants.INACTIVE);
				calendarRepo.save(cyEntity);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete calendar year info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetches calendar year & financial year configuration
	 */
	@Override
	public String getFYandCYCongfiguration(int orgId) throws GlobalSettingsServiceException {

		FYandCYConfiguration response = new FYandCYConfiguration();
		List<ModuleMapping> moduleMappinglist = null;
		List<SubModule> subModuleList = null;

		List<TGsSubModule> subModuleEntityList = null;
		List<Module> moduleList = null;
		Module module = null;
		SubModule subModule = null;

		Gson gson = new Gson();
		try {
			moduleMappinglist = fycyRepo.findByTGsOrganaization_parentOrgId(orgId);

			if (moduleMappinglist != null && !moduleMappinglist.isEmpty()) {
				moduleList = new ArrayList<>();
				for (ModuleMapping moduleMapping : moduleMappinglist) {
					TGsModule moduelEntity = moduleMapping.getTGsModule();

					module = new Module();
					module.setModuleId(moduelEntity.getGsModuleId());
					module.setModuleName(moduelEntity.getModuleName());
					subModuleEntityList = moduelEntity.getTGsSubModules();

					if (subModuleEntityList != null && !subModuleEntityList.isEmpty()) {
						subModuleList = new ArrayList<>();
						for (TGsSubModule tGsSubModule : subModuleEntityList) {
							subModule = new SubModule();
							subModule.setSubModuleId(tGsSubModule.getGsSubModuleId());
							subModule.setSubModuleName(tGsSubModule.getSubModuleName());
							String relationName = tGsSubModule.getModuleMappings().getRelationName();
							if (relationName.equals("Fianacial")) {
								subModule.setYearType(YearType.Financial.name());
							} else {
								subModule.setYearType(YearType.Calander.name());
							}
							subModuleList.add(subModule);
						}
					}
					module.setSubModules(subModuleList);
					moduleList.add(module);
				}
				response.setModules(moduleList);
				response.setParentOrgId(orgId);
				response.setStatus(Status.SUCCESS);

			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Failed to get modules configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gson.toJson(response).toString();
	}

	/*
	 * updates calendar year & financial year configuration
	 */
	@Override
	public String updateFYandCYCongfiguration(FYandCYConfiguration fycycnfig) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<Module> moduleList = null;
		List<SubModule> subModuleList = null;

		List<TGsModule> moduleEntityList = null;
		List<TGsSubModule> subModuleEntityList = null;

		try {

			moduleList = fycycnfig.getModules();
			if (moduleList != null && !moduleList.isEmpty()) {
				List<Integer> moduleIds = moduleList.stream().map(m -> m.getModuleId()).collect(Collectors.toList());
				moduleEntityList = moduleRepo.findByGsModuleIdIn(moduleIds);

				if (moduleEntityList != null && !moduleEntityList.isEmpty()) {

					for (TGsModule me : moduleEntityList) {

						Module m = moduleList.stream().filter(ml -> ml.getModuleId() == me.getGsModuleId()).findAny()
								.orElse(null);
						me.setUpdatedAt(gsutil.getCurrentTmeStamp());
						subModuleList = m.getSubModules();
						subModuleEntityList = me.getTGsSubModules();

						for (TGsSubModule sme : subModuleEntityList) {
							SubModule sm = subModuleList.stream()
									.filter(sml -> sml.getSubModuleId() == sme.getGsSubModuleId()).findAny()
									.orElse(null);
							sme.setCreatedAt(gsutil.getCurrentTmeStamp());
							sme.setUpdatedAt(gsutil.getCurrentTmeStamp());
							if (sm != null) {
								sme.setSubModuleName(sm.getSubModuleName());
								sme.getModuleMappings().setRelationName(sm.getYearType());
								me.setUpdatedAt(gsutil.getCurrentTmeStamp());
							}
						}

					}
					moduleRepo.saveAll(moduleEntityList);
					response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
				}

			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to update modules configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * saves calendar year & financial year configuration
	 */
	@Override
	public String saveFYandCYCongfiguration(FYandCYConfiguration fycyConfig) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<Module> moduleList = null;
		List<SubModule> subModuleList = null;

		List<TGsModule> moduleEntityList = null;
		List<TGsSubModule> subModuleEntityList = null;

		TGsModule moduleEntity = null;
		TGsSubModule subModuleEntity = null;
		TGsOrganaization orgEntity = null;
		try {
			moduleList = fycyConfig.getModules();
			if (moduleList != null && !moduleList.isEmpty()) {
				orgEntity = organizationRepo.findByParentOrgId(fycyConfig.getParentOrgId());
				moduleEntityList = new ArrayList<>();
				for (Module module : moduleList) {

					moduleEntity = new TGsModule();
					moduleEntity.setModuleName(module.getModuleName());
					moduleEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
					moduleEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
					moduleEntity.setUpdatedBy("user");
					moduleEntity.setCreatedBy("user");

					subModuleList = module.getSubModules();

					if (subModuleList != null && !subModuleList.isEmpty()) {
						subModuleEntityList = new ArrayList<>();
						for (SubModule subModule : subModuleList) {
							subModuleEntity = new TGsSubModule();
							ModuleMapping mm = new ModuleMapping();
							subModuleEntity.setSubModuleName(subModule.getSubModuleName());
							subModuleEntity.setTGsModule(moduleEntity);
							subModuleEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
							subModuleEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
							subModuleEntity.setCreatedBy("user");
							subModuleEntity.setUpdatedBy("user");
							mm.setTGsModule(moduleEntity);
							mm.setTGsSubModule(subModuleEntity);
							mm.setTGsOrganaization(orgEntity);

							mm.setCreatedAt(gsutil.getCurrentTmeStamp());
							mm.setUpdatedAt(gsutil.getCurrentTmeStamp());
							mm.setCreatedBy("user");
							mm.setUpdatedBy("user");
							subModuleEntity.setModuleMappings(mm);

							subModuleEntityList.add(subModuleEntity);

							fycyRepo.save(mm);
						}
						moduleEntity.setTGsSubModules(subModuleEntityList);
					}
					moduleEntityList.add(moduleEntity);
				}
				response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Failed to save modules configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deletes calendar year & financial year configuration
	 */
	@Override
	public CustomResponse deleteFYandCYCongfiguration(int id) throws GlobalSettingsServiceException {
		TGsModule moduleEntity = null;
		try {

			moduleEntity = moduleRepo.findByGsModuleId(id);
			if (moduleEntity != null) {
				moduleRepo.delete(moduleEntity);
				moduleEntity.setStatus(GsAppConstants.INACTIVE);
				moduleRepo.save(moduleEntity);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete modules configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetches countries info.
	 */
	@Override
	public CountryInfo getCountry(int parentOrgId, int userId) throws GlobalSettingsServiceException {
		CountryInfo countryInfo = new CountryInfo();
		Gson gson = new Gson();
		CountryLocInfo cntryLocInfo = null;
		Country country = null;
		List<Country> countryList = null;
		List<CountryLocInfo> cntryLocInfoList = null;

		List<TGsCountry> cntryEntityList = null;
		TGsOrganaization orgEntity = null;
		try {

			cntryEntityList = countryRepo.findByParentOrgIdAndUserId(parentOrgId, userId);
			if (cntryEntityList != null && !cntryEntityList.isEmpty()) {
				countryList = new ArrayList<>();
				for (TGsCountry tGsCountry : cntryEntityList) {
					country = new Country();
					cntryLocInfoList = new ArrayList<>();
					for (CountryLocation cl : tGsCountry.getCountryLocations()) {
						cntryLocInfo = new CountryLocInfo();
						cntryLocInfo.setId(cl.getCountryLocationId());
						cntryLocInfo.setCity(cl.getCity());
						cntryLocInfo.setLocation(cl.getLocation());
						cntryLocInfo.setMandal(cl.getMandal());
						cntryLocInfo.setPincode(cl.getPincode());
						cntryLocInfo.setState(cl.getState());
						cntryLocInfo.setCreatedBy(tGsCountry.getCreatedBy());
						cntryLocInfo.setUpdatedBy(tGsCountry.getUpdatedBy());
						cntryLocInfoList.add(cntryLocInfo);
					}
					country.setCountry(tGsCountry.getCountry());
					country.setCountryId(tGsCountry.getGsCountryId());
					country.setCountryLocs(cntryLocInfoList);
					countryList.add(country);
				}
				countryInfo.setCountryInfo(countryList);
				countryInfo.setParentOrgId(parentOrgId);
				countryInfo.setUserId(userId);
			}

		} catch (Exception e) {
			log.error("Failed to get country info", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return countryInfo;
	}

	/*
	 * updates countries info.
	 */
	@Override
	public String updateCountry(Country country) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();
		List<CountryLocInfo> cuntryLocInfo = null;

		TGsCountry cntryEntity = null;
		try {
			cntryEntity = countryRepo.findByGsCountryId(country.getCountryId());
			if (cntryEntity != null) {
				cuntryLocInfo = country.getCountryLocs();
				cntryEntity.setCountry(country.getCountry());
				cntryEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
				for (CountryLocation clEntity : cntryEntity.getCountryLocations()) {
					CountryLocInfo clInfo = cuntryLocInfo.stream()
							.filter(cl -> cl.getId() == clEntity.getCountryLocationId()).findAny().orElse(null);

					if (clInfo != null) {
						clEntity.setCity(clInfo.getCity());
						clEntity.setLocation(clInfo.getLocation());
						clEntity.setMandal(clInfo.getMandal());
						clEntity.setPincode(clInfo.getPincode());
						clEntity.setState(clInfo.getState());

					}
				}
				countryRepo.save(cntryEntity);
				response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
			} else {
				response.addProperty(GsAppConstants.STATUS, Status.FAILED.name());
				response.addProperty(GsAppConstants.ERROR_CODE, Responsecode.INVALID_ID);
			}
		} catch (Exception e) {
			log.error("Failed to update country ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * saves countries info
	 */
	@Override
	public String saveCountry(CountryInfo countriesInfo) throws GlobalSettingsServiceException {

		JsonObject response = new JsonObject();

		List<CountryLocInfo> countryLocInfo = null;

		List<TGsCountry> cntryEntityList = null;
		List<CountryLocation> cntryLocEntityList = null;
		TGsOrganaization organisationEntity = null;
		TGsCountry countryEntity = null;
		CountryLocation cuntryLocEntity = null;
		try {
			organisationEntity = organizationRepo.findByParentOrgId(countriesInfo.getParentOrgId());

			if (organisationEntity != null) {
				for (Country country : countriesInfo.getCountryInfo()) {
					countryLocInfo = country.getCountryLocs();
					if (countryLocInfo != null && !countryLocInfo.isEmpty()) {
						cntryEntityList = new ArrayList<>();
						cntryLocEntityList = new ArrayList<>();
						countryEntity = new TGsCountry();
						for (CountryLocInfo countryInfo : countryLocInfo) {
							cuntryLocEntity = new CountryLocation();
							cuntryLocEntity.setCity(countryInfo.getCity());
							cuntryLocEntity.setLocation(countryInfo.getLocation());
							cuntryLocEntity.setMandal(countryInfo.getMandal());
							cuntryLocEntity.setPincode(countryInfo.getPincode());
							cuntryLocEntity.setState(countryInfo.getState());
							cuntryLocEntity.setTGsCountry(countryEntity);
							cntryLocEntityList.add(cuntryLocEntity);

						}
						countryEntity.setCountry(country.getCountry());
						countryEntity.setCountryLocations(cntryLocEntityList);
						countryEntity.setParentOrgId(countriesInfo.getParentOrgId());
						countryEntity.setUserId(countriesInfo.getUserId());
						cntryEntityList.add(countryEntity);

						countryEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
						countryEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
						countryEntity.setUpdatedBy(country.getUpdatedBy());
						countryEntity.setCreatedBy(country.getCreatedBy());
						countryRepo.saveAndFlush(countryEntity);
						response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
					} else {
						response.addProperty(GsAppConstants.STATUS, Status.FAILED.name());
						response.addProperty(GsAppConstants.ERROR_CODE, Responsecode.INVALID_REQUEST);
					}
				}
			} else {
				response.addProperty(GsAppConstants.STATUS, Status.FAILED.name());
				response.addProperty(GsAppConstants.ERROR_CODE, Responsecode.INVALID_ID);
			}
		} catch (Exception e) {
			log.error("Failed to save countyr info", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deleting passed id country
	 */
	@Override
	public CustomResponse deleteCountry(int id) throws GlobalSettingsServiceException {
		TGsCountry countryEntity = null;
		try {
			countryEntity = countryRepo.findByGsCountryId(id);
			if (countryEntity != null) {
				countryRepo.delete(countryEntity);
				countryEntity.setStatus(GsAppConstants.INACTIVE);
				countryRepo.save(countryEntity);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete country info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetch currency info.
	 */
	@Override
	public Currency getCurrency(int parentOrgId, int userId) throws GlobalSettingsServiceException {

		Gson gson = new Gson();
		Currency currency = new Currency();
		CurrencyCountryInfo ccInfo = null;
		List<CurrencyCountryInfo> ccInfoList = null;
		List<TGsCurrency> currencyEntityList = null;
		try {
			currencyEntityList = currencyRepo.findByParentOrgIdAndUserId(parentOrgId, userId);
			ccInfoList = new ArrayList<>();
			for (TGsCurrency tGsCurrency : currencyEntityList) {
				ccInfo = new CurrencyCountryInfo();
				ccInfo.setGsCurrencyId(tGsCurrency.getGsCurrencyId());
				ccInfo.setCountry(tGsCurrency.getCountry());
				ccInfo.setCurrency(tGsCurrency.getCurrency());
				ccInfoList.add(ccInfo);
			}
			currency.setParentOrgId(parentOrgId);
			currency.setUserId(userId);
			currency.setCurAndCntryInfo(ccInfoList);
		} catch (Exception e) {
			log.error("Failed to get currency info", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerError"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return currency;
	}

	/*
	 * updates currency info.
	 */
	@Override
	public String updateCurrency(Currency currency) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<CurrencyCountryInfo> curAndCntryInfo = null;
		CurrencyCountryInfo ccInfo = null;

		List<TGsCurrency> currenceyEntityList = null;
		try {
			if (currency != null) {
				curAndCntryInfo = currency.getCurAndCntryInfo();

				List<Integer> currencyIds = curAndCntryInfo.stream().map(cc -> cc.getGsCurrencyId())
						.collect(Collectors.toList());
				currenceyEntityList = currencyRepo.findByGsCurrencyIdIn(currencyIds);
				if (currenceyEntityList != null && currenceyEntityList.isEmpty()) {
					for (TGsCurrency ce : currenceyEntityList) {
						ccInfo = curAndCntryInfo.stream().filter(c -> c.getGsCurrencyId() == ce.getGsCurrencyId())
								.findAny().orElse(null);
						if (ccInfo != null) {
							ce.setCountry(ccInfo.getCountry());
							ce.setCurrency(ccInfo.getCurrency());
							ce.setUpdatedAt(gsutil.getCurrentTmeStamp());
						}
					}
				}
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
			currencyRepo.saveAll(currenceyEntityList);
			response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());

		} catch (Exception e) {
			log.error("Failed to update country ", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerErrir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * saves currency info.
	 */
	@Override
	public String saveCurrency(Currency currency) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<CurrencyCountryInfo> curAndCntryInfo = null;

		List<TGsCurrency> currencyEntityList = null;
		TGsOrganaization orgEntity = null;
		TGsCurrency currenceyEntity = null;
		try {
			orgEntity = organizationRepo.findByParentOrgId(currency.getParentOrgId());
			if (orgEntity != null) {
				curAndCntryInfo = currency.getCurAndCntryInfo();

				if (curAndCntryInfo != null && !curAndCntryInfo.isEmpty()) {
					currencyEntityList = new ArrayList<>();

					for (CurrencyCountryInfo ccInfo : curAndCntryInfo) {
						currenceyEntity = new TGsCurrency();
						currenceyEntity.setCountry(ccInfo.getCountry());
						currenceyEntity.setCurrency(ccInfo.getCurrency());
						currenceyEntity.setParentOrgId(currency.getParentOrgId());
						currenceyEntity.setUserId(currency.getUserId());
						currencyEntityList.add(currenceyEntity);
						currenceyEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
						currenceyEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
						currenceyEntity.setCreatedBy(ccInfo.getCreatedBy());
						currenceyEntity.setUpdatedBy(ccInfo.getUpdatedBy());

					}
					currencyRepo.saveAll(currencyEntityList);
					response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
				} else {
					throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
				}
			}

		} catch (Exception e) {
			log.error("Failded to save currency info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerErrir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deleting passed id currency
	 */
	@Override
	public CustomResponse deleteCurrency(int id) throws GlobalSettingsServiceException {
		TGsCurrency currency = null;
		try {
			currency = currencyRepo.findByGsCurrencyId(id);
			if (currency != null) {
				currencyRepo.delete(currency);
				currency.setStatus(GsAppConstants.INACTIVE);
				currencyRepo.save(currency);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete currency info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerErrir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetch language info
	 */
	@Override
	public List<Language> getLanguage(int parentOrgId, int userId) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();
		List<Language> languageList = null;
		Language language = null;
		List<TGsLanguage> languageEntity = null;
		try {
			if (gsutil.isValidUserAndParenetOrg(userId, parentOrgId)) {
				languageEntity = languageRepo.findByParentOrgIdAndUserId(parentOrgId, userId);
				if (languageEntity != null && !languageEntity.isEmpty()) {
					languageList = new ArrayList<>();
					for (TGsLanguage tGsLanguage : languageEntity) {
						language = new Language();
						language.setCurrency(tGsLanguage.getCurrency());
						language.setGsLanguageId(tGsLanguage.getGsLanguageId());
						language.setLanguageInfo(Arrays.asList(tGsLanguage.getLanguage(), ","));
						language.setSelectCountry(tGsLanguage.getSelectCountry());
						languageList.add(language);
						response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
					}

				} else {
					throw new GlobalSettingsServiceException("No Records found", HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new GlobalSettingsServiceException("invalid userid or parentOrg id", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to get language info", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerErrir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return languageList;

	}

	/*
	 * updates language info
	 */
	@Override
	public String updateLanguageCurrency(List<Language> request) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		Language language = null;
		List<Language> languageList = null;
		List<Integer> languageIds = null;

		List<TGsLanguage> languageEntityList = null;
		try {

			languageList = request;
			languageIds = languageList.stream().map(l -> l.getGsLanguageId()).collect(Collectors.toList());
			languageEntityList = languageRepo.findByGsLanguageIdIn(languageIds);
			for (TGsLanguage le : languageEntityList) {
				language = languageList.stream().filter(c -> c.getGsLanguageId() == le.getGsLanguageId()).findAny()
						.orElse(null);
				if (language != null) {
					le.setCurrency(language.getCurrency());
					le.setSelectCountry(language.getSelectCountry());
					le.setLanguage(String.join(",", language.getLanguageInfo()));
					le.setUpdatedAt(gsutil.getCurrentTmeStamp());
				}
			}
			languageRepo.saveAll(languageEntityList);
			response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
		} catch (Exception e) {
			log.error("Failed to update language ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * saves language info
	 */
	@Override
	public String saveLanguage(List<Language> lanInfo) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<Language> languageList = null;

		List<TGsLanguage> langEntityList = null;
		TGsLanguage langEntity = null;
		TGsOrganaization orgEntity = null;
		try {

			if (lanInfo != null) {
				languageList = lanInfo;
				orgEntity = organizationRepo.findByParentOrgId(lanInfo.get(0).getParentOrgId());
				if (languageList != null && !languageList.isEmpty()) {
					langEntityList = new ArrayList<>();
					for (Language lang : languageList) {
						langEntity = new TGsLanguage();
						langEntity.setCurrency(lang.getCurrency());
						langEntity.setLanguage(String.join(", ", lang.getLanguageInfo()));
						langEntity.setSelectCountry(lang.getSelectCountry());
						langEntityList.add(langEntity);
						langEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
						langEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
						langEntity.setCreatedBy(lang.getCreatedBy());
						langEntity.setUpdatedBy(lang.getUpdatedBy());
					}
					languageRepo.saveAll(langEntityList);
				} else {
					throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
				}
			}

		} catch (Exception e) {
			log.error("Failed to saves language ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deleting passed id language
	 */
	@Override
	public CustomResponse deleteLanguage(int id) throws GlobalSettingsServiceException {
		TGsLanguage language = null;
		try {
			language = languageRepo.findByGsLanguageId(id);
			if (language != null) {
				languageRepo.delete(language);
				language.setStatus(GsAppConstants.INACTIVE);
				languageRepo.save(language);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Failed to delete language ", e);
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerErrir"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetches rule configuration
	 */
	@Override
	public List<RuleConfiguration> getRuleCongfiguration(int parentOrgId, int userId)
			throws GlobalSettingsServiceException {
		List<RuleConfiguration> rcList = null;
		try {
			rcList = Arrays.asList(modelMapper.map(ruleConfigRepo.findByParentOrgIdAndUserId(parentOrgId, userId),
					RuleConfiguration[].class));
			rcList.stream().forEach(x -> x.setParentOrgId(parentOrgId));
		} catch (DataAccessException e) {
			log.error("Failed to get rule configuration info ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rcList;
	}

	/*
	 * updates rule configuration
	 */
	@Override
	public RuleConfiguration updateRuleCongfiguration(RuleConfiguration ruleconfig)
			throws GlobalSettingsServiceException {
		log.debug("updateRuleCongfiguration()");
		RuleConfiguration ruleConfiguration = null;
		try {
			Optional<TGsRuleConfiguration> opt = ruleConfigRepo.findById(ruleconfig.getRuleConfigurationId());
			if (opt.isPresent()) {
				TGsRuleConfiguration tGsRuleConfiguration = opt.get();
				tGsRuleConfiguration.setParentOrgId(ruleconfig.getParentOrgId());
				tGsRuleConfiguration.setUserId(ruleconfig.getUserId());
				tGsRuleConfiguration.setApproverName(ruleconfig.getApprovalName());
				tGsRuleConfiguration.setDepartment(ruleconfig.getDepartment());
				tGsRuleConfiguration.setRequiredApprovers(ruleconfig.getApproversRequired());
				tGsRuleConfiguration.setRuleCondition(ruleconfig.getRuleCondition());
//				tGsRuleConfiguration.setRuleId(ruleconfig.getRuleId());
				tGsRuleConfiguration.setRuleName(ruleconfig.getRuleName());
				tGsRuleConfiguration.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsRuleConfiguration.setThreshold(ruleconfig.getThreshold());
				tGsRuleConfiguration.setUpdatedBy(ruleconfig.getUpdatedBy());
				tGsRuleConfiguration.setType(ruleconfig.getType());
				ruleConfiguration = modelMapper.map(ruleConfigRepo.save(tGsRuleConfiguration), RuleConfiguration.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			log.error("Exception occured in createGlAccount ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ruleConfiguration;
	}

	/*
	 * saves rule configuration
	 */
	@Override
	public String saveRuleCongfiguration(RuleConfigurationInfo ruleINfo) throws GlobalSettingsServiceException {
		JsonObject response = new JsonObject();

		List<RuleConfiguration> ruleConfigList = null;
		TGsOrganaization orgEntity = null;

		List<TGsRuleConfiguration> ruleConfigEntityList = null;
		TGsRuleConfiguration ruleConfigEntity = null;
		TGsModule moduleEntity = null;
		TGsSubModule subModuleEntity = null;

		try {
			orgEntity = organizationRepo.findByParentOrgId(ruleINfo.getParentOrgId());
			ruleConfigList = ruleINfo.getRuleConfigurations();
			if (ruleConfigList != null && !ruleConfigList.isEmpty()) {
				ruleConfigEntityList = new ArrayList<>();
				for (RuleConfiguration rule : ruleConfigList) {
					ruleConfigEntity = new TGsRuleConfiguration();
					ruleConfigEntity.setParentOrgId(ruleINfo.getParentOrgId());
					ruleConfigEntity.setUserId(ruleINfo.getUserId());
					moduleEntity = moduleRepo.findByGsModuleId(rule.getModuleId());
					subModuleEntity = subModuleRepo.findByGsSubModuleId(rule.getSubModuleId());
					ruleConfigEntity.setApproverName(rule.getApprovalName());
					ruleConfigEntity.setDepartment(rule.getDepartment());
					ruleConfigEntity.setRequiredApprovers(rule.getApproversRequired());
					ruleConfigEntity.setRuleCondition(rule.getRuleCondition());
					ruleConfigEntity.setRuleName(rule.getRuleName());
					ruleConfigEntity.setThreshold(rule.getThreshold());
					ruleConfigEntity.setType(rule.getType());
					ruleConfigEntity.setCreatedAt(gsutil.getCurrentTmeStamp());
					ruleConfigEntity.setUpdatedAt(gsutil.getCurrentTmeStamp());
					ruleConfigEntity.setCreatedBy(rule.getCreatedBy());
					ruleConfigEntity.setUpdatedBy(rule.getUpdatedBy());
					ruleConfigEntity.setStatus(GsAppConstants.ACTIVE);
					/*
					 * if (moduleEntity != null) { ruleConfigEntity.setTGsModule(moduleEntity); } if
					 * (subModuleEntity != null) {
					 * ruleConfigEntity.setTGsSubModule(subModuleEntity); }
					 */
					ruleConfigEntityList.add(ruleConfigEntity);
				}
				ruleConfigRepo.saveAll(ruleConfigEntityList);
				response.addProperty(GsAppConstants.STATUS, Status.SUCCESS.name());
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Exception occured in save rule Configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response.toString();
	}

	/*
	 * deleting passed id rule Configuration
	 */
	public CustomResponse deleteRuleCongfiguration(int id) throws GlobalSettingsServiceException {
		TGsRuleConfiguration ruleConfigEntity = null;
		try {
			Optional<TGsRuleConfiguration> opt = ruleConfigRepo.findById(id);
			if (opt.isPresent()) {
				ruleConfigEntity = opt.get();
				ruleConfigEntity.setStatus(GsAppConstants.INACTIVE);
				ruleConfigRepo.save(ruleConfigEntity);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			log.error("Exception occured in delete rule Configuration ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse("Deleted Successfully", HttpStatus.CREATED.toString());
	}

	/*
	 * fetches user organizations
	 */
	@Override
	public UserOrganisation getUserOrganisation(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		UserOrganisation userOrg = new UserOrganisation();
		Gson gson = new Gson();
		List<TGsOrganaization> organizationsList = null;
		Map<Integer, String> organisationNames = null;
		TGsOrganaization orgEntity = null;
		try {
			if (gsutil.isValidUserAndParenetOrg(userId, parentOrgId)) {

				orgEntity = organizationRepo.findByParentOrgId(parentOrgId);
				if (orgEntity != null) {
					userOrg.setOrgId(orgEntity.getParentOrgId());
					userOrg.setOrgName(orgEntity.getOrgName());
					userOrg.setStatus(Status.SUCCESS);
				}

			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}
		} catch (

		Exception e) {
			log.error("Failed to get user organisations ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userOrg;
	}

	/*
	 * fetches organization's verticals
	 */

	@Override
	public String getVerticals(int orgId, int userId, int parentOrgId) throws GlobalSettingsServiceException {
		Verticals vertical = new Verticals();
		Gson gson = new Gson();
		Map<Integer, String> verticalNames = null;

		List<TGsVertical> verticalsList = null;
		try {
			if (gsutil.isValidUserAndParenetOrg(userId, parentOrgId)) {
				verticalsList = verticalRepo.findBytGsOrganaization_parentOrgId(orgId);
				verticalNames = new HashMap<>();
				for (TGsVertical tGsVertical : verticalsList) {
					// setting vertical name and id as key value pair for response.
					verticalNames.put(tGsVertical.getGsVerticalId(), tGsVertical.getOrgName());
				}
				vertical.setVerticals(verticalNames);
			} else {
				throw new GlobalSettingsServiceException("invalid userid or parentorgId", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			throw new GlobalSettingsServiceException(env.getProperty("InternalServerError"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gson.toJson(vertical).toString();
	}

	/*
	 * fetches organization's business units
	 */
	@Override
	public BusinessUnits getBusinessUnits(GetBusinessUnit request) throws GlobalSettingsServiceException {

		BusinessUnits businessUnits = new BusinessUnits();
		Map<Integer, Map<Integer, String>> verticalBusinessUnits;
		Gson gson = new Gson();
		List<TGsBusinessUnit> businessUnitList = null;

		try {
			if (gsutil.isValidUserAndParenetOrg(request.getUserId(), request.getParentOrgId())) {
				businessUnitList = businessUnitRepo.findBusinessUnits(request.getVerticalIds());

				if (businessUnitList != null && !businessUnitList.isEmpty()) {
					verticalBusinessUnits = new HashMap<>();
					for (TGsBusinessUnit tGsBusinessUnit : businessUnitList) {
						int veticalId = tGsBusinessUnit.getTGsVertical().getGsVerticalId();
						int businessId = tGsBusinessUnit.getGsBusinessUnitId();

						if (verticalBusinessUnits.containsKey(veticalId)) {
							verticalBusinessUnits.get(veticalId).put(businessId, tGsBusinessUnit.getBusinessUnitName());
						} else {
							Map<Integer, String> businessNames = new HashMap<>();
							businessNames.put(businessId, tGsBusinessUnit.getBusinessUnitName());
							verticalBusinessUnits.put(veticalId, businessNames);
						}

					}
					businessUnits.setBusinessUnits(verticalBusinessUnits);
					businessUnits.setStatus(Status.SUCCESS);
				} else {
					throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
				}
			} else {

			}
		} catch (Exception e) {
			log.error("Failed to fetch Business units ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return businessUnits;
	}

	@Override
	public String saveGlobalOrg(GlobalOrg globalOrg) throws GlobalSettingsServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	// swati changes starts

	@Override
	public CustomResponse createCostCenter(CostCenter costCenter) throws GlobalSettingsServiceException {
		log.debug("GsServiceImpl.createCostCenter()");
		try {
			gsCostCenter.save(gsUtil.buildCostCenter(costCenter));
		} catch (DataAccessException e) {
			log.error("Exception occured in createCostCenter ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CostCenter getCostCenter(int costCenterid) throws GlobalSettingsServiceException {

		log.debug("getCostCenter::");
		;
		Optional<TGsCostCenter> costcenter = null;
		CostCenter costcentr = null;
		try {
			;
			costcenter = gsCostCenter.findById(costCenterid);
			if (costcenter.isPresent()) {
				TGsCostCenter tGsCostCenter = costcenter.get();
				costcentr = new CostCenter();
				// TGsOrganaization tgs = tGsCostCenter.getTGsOrganaization();
				// costcentr.setOrgName(tgs.getOrgName());
				costcentr.setCostCenterId(tGsCostCenter.getGsCostCenterId());
				costcentr.setCostCenterName(tGsCostCenter.getCostCenterName());
				// costcentr.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return costcentr;
	}

	@Override
	public TGsCostCenter updateCostCenterData(CostCenter costCenter) throws GlobalSettingsServiceException {

		TGsCostCenter costCentr = gsCostCenter.getOne(costCenter.getCostCenterId());

		costCentr.setCostCenterName(costCenter.getCostCenterName());
		TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(costCenter.getOrgId());
		// costCentr.setTGsOrganaization(tgsOrg);
		TGsCostCenter costCen = gsCostCenter.save(costCentr);
		return costCen;
	}

	@Override
	public void deleteCostCenter(int costCenterId) throws GlobalSettingsServiceException {

		gsCostCenter.deleteById(costCenterId);
	}

	@Override
	public ProfitCenter getProfitCenter(int profitCenterid) throws GlobalSettingsServiceException {
		log.debug("getProfitCenter::");
		;
		Optional<TGsProfitCenter> profitcenter = null;
		ProfitCenter pc = null;
		try {
			;
			profitcenter = gsProfitCenterRepo.findById(profitCenterid);
			if (profitcenter.isPresent()) {
				TGsProfitCenter tGsProfitCenter = profitcenter.get();
				pc = new ProfitCenter();
				// TGsOrganaization tgs = tGsProfitCenter.getTGsOrganaization();
				// pc.setOrgName(tgs.getOrgName());
				// pc.setProfitCenterId(tGsProfitCenter.getGsProfitCenterId());
				// pc.setProfitCenterName(tGsProfitCenter.getProfitCenterName());
				// pc.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return pc;
	}

	@Override
	public void deleteProfitCenter(int profitCenterId) throws GlobalSettingsServiceException {
		gsProfitCenterRepo.deleteById(profitCenterId);
	}

	@Override
	public CustomResponse createProfitCenter(ProfitCenter profitcenter) throws GlobalSettingsServiceException {

		log.debug("GsServiceImpl.createprofitcenter()");
		try {
			gsProfitCenterRepo.save(gsUtil.buildProfitCenter(profitcenter));
		} catch (DataAccessException e) {
			log.error("Exception occured in profitcenter ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public TGsProfitCenter updateProfitcenterData(ProfitCenter profitcenter) throws GlobalSettingsServiceException {

		TGsProfitCenter gsProfitCenter = gsProfitCenterRepo.getOne(profitcenter.getProfitCenterId());

		gsProfitCenter.setProfitCenterName(profitcenter.getProfitCenterName());
		TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(profitcenter.getOrgId());
		// gsProfitCenter.setTGsOrganaization(tgsOrg);
		gsProfitCenter.setStatus(profitcenter.getStatus());
		TGsProfitCenter profitCenter = gsProfitCenterRepo.save(gsProfitCenter);
		return profitCenter;
	}

	@Override
	public IncomeGLHead getIncomeGLHead(int incomeGlHeadId) throws GlobalSettingsServiceException {

		log.debug("getIncomeGLHead::");
		;
		Optional<TGsIncomeGlHead> incomeglhead = null;
		IncomeGLHead incomeGlHd = null;
		try {
			;
			incomeglhead = gsIncomeGlHeadRepo.findById(incomeGlHeadId);
			if (incomeglhead.isPresent()) {
				TGsIncomeGlHead tGsIncomeGlHead = incomeglhead.get();
				incomeGlHd = new IncomeGLHead();
				// TGsOrganaization tgs = tGsIncomeGlHead.getTGsOrganaization();
				// incomeGlHd.setOrgName(tgs.getOrgName());
				// incomeGlHd.setIncomeGlHeadId(tGsIncomeGlHead.getGsIncomeGlHeadId());
				incomeGlHd.setIncomeGlHeadName(tGsIncomeGlHead.getIncomeGlHeadName());
				// incomeGlHd.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return incomeGlHd;

	}

	@Override
	public void deleteIncomeGLHead(int incomeGlHeadId) throws GlobalSettingsServiceException {
		gsIncomeGlHeadRepo.deleteById(incomeGlHeadId);
	}

	@Override
	public IncomeGlHeadResponse createIncomeGLHead(IncomeGLHead incomeglhead) throws GlobalSettingsServiceException {

		try {
			gsIncomeGlHeadRepo.save(gsUtil.buildIncomeGlHead(incomeglhead));
		} catch (DataAccessException e) {
			log.error("Exception occured in createIncomeGLHead ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new IncomeGlHeadResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());

	}

	public TGsIncomeGlHead updateIncomeGlHead(IncomeGLHead incomeglhead) throws GlobalSettingsServiceException {

		TGsIncomeGlHead tgsIncomGlHead = gsIncomeGlHeadRepo.getOne(incomeglhead.getIncomeGlHeadId());

		tgsIncomGlHead.setIncomeGlHeadName(incomeglhead.getIncomeGlHeadName());
		TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(incomeglhead.getParentOrgId());
		// tgsIncomGlHead.setTGsOrganaization(tgsOrg);
		tgsIncomGlHead.setParentOrgId(incomeglhead.getParentOrgId());
		tgsIncomGlHead.setStatus(incomeglhead.getStatus());
		TGsIncomeGlHead incomeGlHead = gsIncomeGlHeadRepo.save(tgsIncomGlHead);
		return incomeGlHead;
	}

	@Override
	public void deleteExpenditureGLHead(int expenditureGlHeadId) throws GlobalSettingsServiceException {
		gsExpenditureGlHeadRepo.deleteById(expenditureGlHeadId);

	}

	@Override
	public ExpenditureGLHead getExpenditureGLHead(int expenditureGlHeadId) throws GlobalSettingsServiceException {
		Optional<TGsExpenditureGlHead> expenditureHead = gsExpenditureGlHeadRepo.findById(expenditureGlHeadId);
		ExpenditureGLHead expenditureGLHead = new ExpenditureGLHead();
		if (expenditureHead.isPresent()) {
			TGsExpenditureGlHead obj = expenditureHead.get();
			expenditureGLHead.setExpenditureGlHeadId(obj.getGsExpenditureGlHeadId());
			expenditureGLHead.setExpenditureGlHeadName(obj.getExpenditureGlHeadName());
			// TGsOrganaization tgs = obj.getTGsOrganaization();
			// expenditureGLHead.setOrgId(tgs.getParentOrgId());
			// expenditureGLHead.setOrgName(tgs.getOrgName());
			// expenditureGLHead.setStatus(obj.get);

			// expenditureGLHead
		}
		return expenditureGLHead;
	}

	@Override
	public ExpenditureGlHeadResponse createExpenditureGlHead(ExpenditureGLHead expenditureglhead)
			throws GlobalSettingsServiceException {
		try {
			gsExpenditureGlHeadRepo.save(gsUtil.buildExpenditureglhead(expenditureglhead));
		} catch (DataAccessException e) {
			log.error("Exception occured in createExpenditureGlHead ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ExpenditureGlHeadResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public TGsExpenditureGlHead updateExpenditureGLHead(ExpenditureGLHead expenditureglhead) {
		TGsExpenditureGlHead tGsExpenditureGlHead = gsExpenditureGlHeadRepo
				.getOne(expenditureglhead.getExpenditureGlHeadId());

		tGsExpenditureGlHead.setExpenditureGlHeadName(expenditureglhead.getExpenditureGlHeadName());
		TGsOrganaization tgsOrg = new TGsOrganaization();
		tgsOrg.setParentOrgId(expenditureglhead.getOrgId());
		// tGsExpenditureGlHead.setTGsOrganaization(tgsOrg);
		TGsExpenditureGlHead tGsExpenditureGlHed = gsExpenditureGlHeadRepo.save(tGsExpenditureGlHead);
		return tGsExpenditureGlHed;

	}

	@Override
	public AssetNature getAssetNature(int assetNatureId) throws GlobalSettingsServiceException {

		log.debug("getAssetNature::");
		;
		Optional<TGsAssetNature> assetNature = null;
		AssetNature assetNatur = null;
		try {
			;
			assetNature = gsAssetNatureRepo.findById(assetNatureId);
			if (assetNature.isPresent()) {
				TGsAssetNature tGsAssetNature = assetNature.get();
				assetNatur = new AssetNature();
				//TGsOrganaization tgs = tGsAssetNature.getTGsOrganaization();
				// assetNatur.setOrgName(tgs.getOrgName());
				assetNatur.setAssetNatureId(tGsAssetNature.getGsAssetNatureId());
				assetNatur.setAssetNatureName(tGsAssetNature.getAssetNatureName());
				// assetNatur.setOrgId(tgs.getParentOrgId());
				
				//myedit
				assetNatur.setOrgName(tGsAssetNature.getOrgName());
				assetNatur.setAssetGroup(tGsAssetNature.getAssetGroup());
				assetNatur.setParentOrgId(tGsAssetNature.getParentOrgId());
				assetNatur.setUserId(tGsAssetNature.getUserId());
				assetNatur.setStatus(tGsAssetNature.getStatus());
				//assetNatur.setOrgId(tGsAssetNature.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return assetNatur;

	}

	@Override
	public AssetGroup getAssetGroup(int assetGroupId) throws GlobalSettingsServiceException {

		log.debug("getAssetGroup::");
		;
		Optional<TGsAssetGroup> assetGroup = null;
		AssetGroup assetGroupObj = null;
		try {
			;
			assetGroup = gsAssetGroupRepo.findById(assetGroupId);
			if (assetGroup.isPresent()) {
				TGsAssetGroup tGsAssetGroup = assetGroup.get();
				assetGroupObj = new AssetGroup();
				assetGroupObj.setParentOrgId(tGsAssetGroup.getParentOrgId());
				assetGroupObj.setAssetGroupId(tGsAssetGroup.getGsAssetGroupId());
				assetGroupObj.setAssetClassName(tGsAssetGroup.getAssetClassName());
				// assetGroupObj.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return assetGroupObj;
	}

	@Override
	public LiabilityGLHead getLiabilityGLHead(int liablityGLHeadId) throws GlobalSettingsServiceException {
		log.debug("getLiabilityGLHead::");
		;
		Optional<TGsLiabilityGlHead> liabilityGlHead = null;
		LiabilityGLHead liabilityGLHeadobj = null;
		try {
			liabilityGlHead = gsLiabilityGLHeadRepo.findById(liablityGLHeadId);
			if (liabilityGlHead.isPresent()) {
				TGsLiabilityGlHead tGsLiabilityGlHead = liabilityGlHead.get();
				liabilityGLHeadobj = new LiabilityGLHead();
				// TGsOrganaization tgs = tGsLiabilityGlHead.getTGsOrganaization();
				// liabilityGLHeadobj.setOrgName(tgs.getOrgName());
				liabilityGLHeadobj.setLiabilityGLHeadId(tGsLiabilityGlHead.getGsLiabilityGlHeadId());
				liabilityGLHeadobj.setLiabilityGLHeadName(tGsLiabilityGlHead.getLiabilityGlHeadName());
				// liabilityGLHeadobj.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return liabilityGLHeadobj;

	}

	@Override
	public LiabilityGLSubHead getLiabilityGLSubHead(int liablityGLSubHeadId) throws GlobalSettingsServiceException {

		log.debug("getLiabilityGLSubHead::");
		;
		Optional<TGsLiabilityGlSubhead> liabilityGLSubHead = null;
		LiabilityGLSubHead liabilityGLSubHeadObj = null;
		try {
			liabilityGLSubHead = gsLiabilityGLSubHeadRepo.findById(liablityGLSubHeadId);
			if (liabilityGLSubHead.isPresent()) {
				TGsLiabilityGlSubhead tGsLiabilityGlSubhead = liabilityGLSubHead.get();
				liabilityGLSubHeadObj = new LiabilityGLSubHead();
				//liabilityGLSubHeadObj.setOrgName(tGsLiabilityGlSubhead.ge);
				liabilityGLSubHeadObj.setLiabilityGlHead(tGsLiabilityGlSubhead.getLiabilityGlHead());
				liabilityGLSubHeadObj.setParentOrgId(tGsLiabilityGlSubhead.getParentOrgId());
				liabilityGLSubHeadObj.setUserId(tGsLiabilityGlSubhead.getUserId());
				liabilityGLSubHeadObj.setGsLiabilityGlSubheadId(tGsLiabilityGlSubhead.getGsLiabilityGlSubheadId());
				liabilityGLSubHeadObj.setLiabilityGLSubHeadName(tGsLiabilityGlSubhead.getLiabilityGlSubheadName());
				// TGsOrganaization tgs = tGsLiabilityGlSubhead.getTGsOrganaization();
				// liabilityGLSubHeadObj.setOrgName(tgs.getOrgName());
				
				// liabilityGLSubHeadObj.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return liabilityGLSubHeadObj;
	
	}

	@Override
	public GLGroup getGLGroup(int glGroupId) throws GlobalSettingsServiceException {

		log.debug("getGLGroup::");
		;
		Optional<TGsGlGroup> tGsGlGrup = null;
		GLGroup gLGroup = null;
		try {
			tGsGlGrup = gsGLGroupRepo.findById(glGroupId);
			if (tGsGlGrup.isPresent()) {
				TGsGlGroup tGsGlGroup = tGsGlGrup.get();
				gLGroup = new GLGroup();
				// TGsOrganaization tgs = tGsGlGroup.getTGsOrganaization();
				// gLGroup.setOrgName(tgs.getOrgName());
				gLGroup.setGsGlGroupId(tGsGlGroup.getGsGlGroupId());
				gLGroup.setGlGropName(tGsGlGroup.getGlGroupName());
				gLGroup.setAccountType(tGsGlGroup.getAccountType());
				gLGroup.setGlHead(tGsGlGroup.getGlHead());
				gLGroup.setParentOrgId(tGsGlGroup.getParentOrgId());
				gLGroup.setOrgName(tGsGlGroup.getOrgName());
				gLGroup.setStatus(tGsGlGroup.getStatus());
				gLGroup.setUserId(tGsGlGroup.getUserId());
				gLGroup.setOrgId(tGsGlGroup.getParentOrgId());
				// gLGroup.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLGroup;
	}

	@Override
	public GLSubGroup getGLSubGroup(int glSubGroupId) throws GlobalSettingsServiceException {

		log.debug("getGLGroup::");
		;
		Optional<TGsGlSubGroup> tGsGlSubGrup = null;
		GLSubGroup gLSubGroup = null;
		try {
			tGsGlSubGrup = gsGLSubGroupRepo.findById(glSubGroupId);
			if (tGsGlSubGrup.isPresent()) {
				TGsGlSubGroup tGsGlSubGroup = tGsGlSubGrup.get();
				gLSubGroup = new GLSubGroup();
				// TGsOrganaization tgs = tGsGlSubGroup.getTGsOrganaization();
				// gLSubGroup.setOrgName(tgs.getOrgName());
				gLSubGroup.setGlSubGroupId(tGsGlSubGroup.getGsGlSubGroupId());
				gLSubGroup.setGlSubGropName(tGsGlSubGroup.getGlSubGroupName());
				gLSubGroup.setParentOrgId(tGsGlSubGroup.getParentOrgId());
				gLSubGroup.setGlGroupName(tGsGlSubGroup.getGlGroupName());
				gLSubGroup.setUserId(tGsGlSubGroup.getUserId());
				gLSubGroup.setOrgId(tGsGlSubGroup.getParentOrgId());;
				//gLSubGroup.setOrgName(tGsGlSubGroup.ge);
				
				// gLSubGroup.setOrgId(tgs.getParentOrgId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLSubGroup;
	}

	@Override
	public GLAccount getGLAccount(int glAccountId) throws GlobalSettingsServiceException {

		log.debug("getGLAccount::");
		Optional<TGsGlAccount> tGsGlAcount = null;
		GLAccount gLAccount = null;
		try {
			tGsGlAcount = gsGLAccountRepo.findById(glAccountId);
			if (tGsGlAcount.isPresent()) {
				TGsGlAccount tGsGlAccount = tGsGlAcount.get();
				gLAccount = new GLAccount();
				// TGsOrganaization tgs = tGsGlAccount.getTGsOrganaization();
				gLAccount.setGlAcctName((tGsGlAccount.getGlAccountName()));
				gLAccount.setGlGroupName(tGsGlAccount.getGlGroupName());
				gLAccount.setGlSubGroupName(tGsGlAccount.getGlSubGroupName());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLAccount;
	}

	@Override
	public void deleteAssetNature(int assetNatureId) throws GlobalSettingsServiceException {
		gsAssetNatureRepo.deleteById(assetNatureId);
	}

	@Override
	public void deleteAssetGroup(int assetGroupId) throws GlobalSettingsServiceException {
		gsAssetGroupRepo.deleteById(assetGroupId);
	}

	@Override
	public void deleteLiabilityGlHead(int liabilityGlHeadId) throws GlobalSettingsServiceException {
		gsLiabilityGLHeadRepo.deleteById(liabilityGlHeadId);
	}

	@Override
	public void deleteLiabilityGlSubhead(int liabilityGlSubheadId) throws GlobalSettingsServiceException {
		gsLiabilityGLSubHeadRepo.deleteById(liabilityGlSubheadId);
	}

	@Override
	public void deleteGlGroup(int glGroupId) throws GlobalSettingsServiceException {
		gsGLGroupRepo.deleteById(glGroupId);
	}

	@Override
	public void deleteGlSubGroup(int glSubGroupId) throws GlobalSettingsServiceException {
		gsGLSubGroupRepo.deleteById(glSubGroupId);
	}

	@Override
	public void deleteGlAccount(int glAccountId) throws GlobalSettingsServiceException {
		gsGLAccountRepo.deleteById(glAccountId);

	}

	@Override
	public CustomResponse createAssetNature(AssetNature assetNature) throws GlobalSettingsServiceException {
		try {
			gsAssetNatureRepo.save(gsUtil.buildAssetNature(assetNature));
		} catch (DataAccessException e) {
			log.error("Exception occured in createAssetNature ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());

	}

	@Override
	public CustomResponse createAssetGroup(AssetGroup assetGroup) throws GlobalSettingsServiceException {
		try {
			gsAssetGroupRepo.save(gsUtil.buildAssetGroup(assetGroup));
		} catch (DataAccessException e) {
			log.error("Exception occured in createAssetGroup ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createLiabilityGlHead(LiabilityGLHead liabilityGLHead) throws GlobalSettingsServiceException {
		try {
			gsLiabilityGLHeadRepo.save(gsUtil.buildLiabilityGlHead(liabilityGLHead));
		} catch (DataAccessException e) {
			log.error("Exception occured in createLiabilityGlHead ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createLiabilityGlSubhead(LiabilityGLSubHead liabilityGLSubHead)
			throws GlobalSettingsServiceException {
		try {
			gsLiabilityGLSubHeadRepo.save(gsUtil.buildLiabilityGlSubhead(liabilityGLSubHead));
		} catch (DataAccessException e) {
			log.error("Exception occured in createLiabilityGlSubhead ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createGlGroup(GLGroup gLGroup) throws GlobalSettingsServiceException {
		try {
			gsGLGroupRepo.save(gsUtil.buildGlGroup(gLGroup));
		} catch (DataAccessException e) {
			log.error("Exception occured in createGlGroup ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public CustomResponse createGlSubGroup(GLSubGroup gLSubGroup) throws GlobalSettingsServiceException {
		try {
			gsGLSubGroupRepo.save(gsUtil.buildGlSubGroup(gLSubGroup));
		} catch (DataAccessException e) {
			log.error("Exception occured in createGlSubGroup ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new CustomResponse(GsAppConstants.CREATED, HttpStatus.CREATED.toString());
	}

	@Override
	public TGsAssetNature updateAssetNature(AssetNature assetNature) throws GlobalSettingsServiceException {
		TGsAssetNature tGsAssetNature = gsAssetNatureRepo.getOne(assetNature.getAssetNatureId());

		tGsAssetNature.setAssetNatureName(assetNature.getAssetNatureName());
		// TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(assetNature.getOrgId());
		// tGsAssetNature.setTGsOrganaization(tgsOrg);
		tGsAssetNature.setStatus(assetNature.getStatus());
		TGsAssetNature tGsAssetNatur = gsAssetNatureRepo.save(tGsAssetNature);
		return tGsAssetNatur;
	}

	@Override
	public TGsAssetGroup updateAssetGroup(AssetGroup assetGroup) throws GlobalSettingsServiceException {
		TGsAssetGroup tGsAssetGroup = gsAssetGroupRepo.getOne(assetGroup.getAssetGroupId());

		tGsAssetGroup.setAssetClassName(assetGroup.getAssetClassName());
		tGsAssetGroup.setAssetNature(assetGroup.getAssetNature());
		// TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(assetGroup.getOrgId());
		// tGsAssetGroup.setTGsOrganaization(tgsOrg);
		TGsAssetGroup tGsAssetGrup = gsAssetGroupRepo.save(tGsAssetGroup);
		return tGsAssetGrup;
	}

	@Override
	public TGsLiabilityGlHead updateLiabilityGlHead(LiabilityGLHead liabilityGLHead)
			throws GlobalSettingsServiceException {
		TGsLiabilityGlHead tGsLiabilityGlHead = gsLiabilityGLHeadRepo.getOne(liabilityGLHead.getLiabilityGLHeadId());

		tGsLiabilityGlHead.setLiabilityGlHeadName(liabilityGLHead.getLiabilityGLHeadName());
		// TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(liabilityGLHead.getOrgId());
		// tGsLiabilityGlHead.setTGsOrganaization(tgsOrg);
		TGsLiabilityGlHead tGsLiabilityGlHed = gsLiabilityGLHeadRepo.save(tGsLiabilityGlHead);
		return tGsLiabilityGlHed;
	}

	@Override
	public TGsLiabilityGlSubhead updateLiabilityGlSubhead(LiabilityGLSubHead liabilityGLSubHead)
			throws GlobalSettingsServiceException {
		TGsLiabilityGlSubhead tGsLiabilityGlSubhead = gsLiabilityGLSubHeadRepo
				.getOne(liabilityGLSubHead.getGsLiabilityGlSubheadId());

		tGsLiabilityGlSubhead.setLiabilityGlSubheadName(liabilityGLSubHead.getLiabilityGLSubHeadName());
		// TGsOrganaization tgsOrg = new TGsOrganaization();
		// tgsOrg.setParentOrgId(liabilityGLSubHead.getOrgId());
		// tGsLiabilityGlSubhead.setTGsOrganaization(tgsOrg);
		TGsLiabilityGlSubhead tGsLiabilityGlSubhed = gsLiabilityGLSubHeadRepo.save(tGsLiabilityGlSubhead);
		return tGsLiabilityGlSubhed;
	}

	@Override
	public TGsGlGroup updateGlGroup(GLGroup gLGroup) throws GlobalSettingsServiceException {
		TGsGlGroup tGsGlGroup = gsGLGroupRepo.getOne(gLGroup.getGsGlGroupId());

		tGsGlGroup.setGlGroupName(gLGroup.getGlGropName());
		/*
		 * TGsOrganaization tgsOrg = new TGsOrganaization();
		 * tgsOrg.setParentOrgId(gLGroup.getOrgId());
		 * tGsGlGroup.setTGsOrganaization(tgsOrg);
		 */
		TGsGlGroup tGsGlGrup = gsGLGroupRepo.save(tGsGlGroup);
		return tGsGlGrup;
	}

	@Override
	public TGsGlSubGroup updateGlSubGroup(GLSubGroup gLSubGroup) throws GlobalSettingsServiceException {
		TGsGlSubGroup tGsGlSubGroup = gsGLSubGroupRepo.getOne(gLSubGroup.getGlSubGroupId());

		tGsGlSubGroup.setGlSubGroupName(gLSubGroup.getGlSubGropName());
		/*
		 * TGsOrganaization tgsOrg = new TGsOrganaization();
		 * tgsOrg.setParentOrgId(gLSubGroup.getOrgId());
		 * tGsGlSubGroup.setTGsOrganaization(tgsOrg);
		 */
		TGsGlSubGroup tGsGlSubGrup = gsGLSubGroupRepo.save(tGsGlSubGroup);
		return tGsGlSubGrup;

	}

	@Override
	public List<CostCenter> getCostCenterData(int userId, int parentOrgId) throws GlobalSettingsServiceException {

		log.debug("getCostCenterData::");
		;
		List<TGsCostCenter> tGScostCenterList = null;
		List<CostCenter> costCenterList = null;
		try {
			tGScostCenterList = gsCostCenter.findByUserIdAndParentOrgId(userId, parentOrgId);

			costCenterList = Arrays.asList(modelMapper.map(tGScostCenterList, CostCenter[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return costCenterList;
	}

	@Override
	public List<ProfitCenter> getProfitCenterData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getProfitCenterData::");
		;
		List<TGsProfitCenter> tGsProfitCenterList = null;
		List<ProfitCenter> profitCenterList = null;
		try {
			tGsProfitCenterList = gsProfitCenterRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			profitCenterList = Arrays.asList(modelMapper.map(tGsProfitCenterList, ProfitCenter[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return profitCenterList;
	}

	@Override
	public List<IncomeGLHead> getIncomeGlHead(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getIncomeGlHead::");
		;
		List<TGsIncomeGlHead> tGsIncomeGlHeadList = null;
		List<IncomeGLHead> incomeGLHeadList = null;
		try {
			tGsIncomeGlHeadList = gsIncomeGlHeadRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			incomeGLHeadList = Arrays.asList(modelMapper.map(tGsIncomeGlHeadList, IncomeGLHead[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return incomeGLHeadList;
	}

	@Override
	public List<ExpenditureGLHead> getExpenditureGlHead(int userId, int parentOrgId)
			throws GlobalSettingsServiceException {
		log.debug("getExpenditureGlHead::");
		;
		List<TGsExpenditureGlHead> tGsExpenditureGlHeadList = null;
		List<ExpenditureGLHead> expenditureGLHeadList = null;
		try {
			tGsExpenditureGlHeadList = gsExpenditureGlHeadRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			expenditureGLHeadList = Arrays.asList(modelMapper.map(tGsExpenditureGlHeadList, ExpenditureGLHead[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return expenditureGLHeadList;
	}

	@Override
	public List<AssetNature> getAssetNatureData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getExpenditureGlHead::");
		List<TGsAssetNature> tGsAssetNatureList = null;
		List<AssetNature> assetNatureList = null;
		try {
			tGsAssetNatureList = gsAssetNatureRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			assetNatureList = Arrays.asList(modelMapper.map(tGsAssetNatureList, AssetNature[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return assetNatureList;
	}

	//////
	@Override
	public List<AssetGroup> getAssetGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getAssetGroupData::");
		List<TGsAssetGroup> tGsAssetGroupList = null;
		List<AssetGroup> assetGroupList = null;
		try {
			tGsAssetGroupList = gsAssetGroupRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			assetGroupList = Arrays.asList(modelMapper.map(tGsAssetGroupList, AssetGroup[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return assetGroupList;
	}

	@Override
	public List<LiabilityGLHead> getLiabilityGLHeadData(int userId, int parentOrgId)
			throws GlobalSettingsServiceException {
		log.debug("getLiabilityGLHeadData::");
		List<TGsLiabilityGlHead> tGsLiabilityGlHeadList = null;
		List<LiabilityGLHead> liabilityGLHeadList = null;
		try {
			tGsLiabilityGlHeadList = gsLiabilityGLHeadRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			liabilityGLHeadList = Arrays.asList(modelMapper.map(tGsLiabilityGlHeadList, LiabilityGLHead[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return liabilityGLHeadList;
	}

	@Override
	public List<LiabilityGLSubHead> getLiabilityGLSubHeadData(int userId, int parentOrgId)
			throws GlobalSettingsServiceException {
		log.debug("getLiabilityGLHeadData::");
		List<TGsLiabilityGlSubhead> tGsLiabilityGlSubheadList = null;
		List<LiabilityGLSubHead> liabilityGLSubHeadList = null;
		try {
			tGsLiabilityGlSubheadList = gsLiabilityGLSubHeadRepo.findByUserIdAndParentOrgId(userId, parentOrgId);

			liabilityGLSubHeadList = Arrays
					.asList(modelMapper.map(tGsLiabilityGlSubheadList, LiabilityGLSubHead[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return liabilityGLSubHeadList;
	}

	@Override
	public List<GLGroup> getGLGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getLiabilityGLHeadData::");
		List<TGsGlGroup> tGsGlGroupList = null;
		List<GLGroup> gLGroupList = null;
		try {
			tGsGlGroupList = gsGLGroupRepo.findByUserIdAndParentOrgId(userId, parentOrgId);
			gLGroupList = Arrays.asList(modelMapper.map(tGsGlGroupList, GLGroup[].class));

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLGroupList;
	}

	@Override
	public List<GLSubGroup> getGLSubGroupData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getGLSubGroupData::");
		List<TGsGlSubGroup> tGsGlSubGroupList = null;
		List<GLSubGroup> gLSubGroupList = null;
		try {
			tGsGlSubGroupList = gsGLSubGroupRepo.findByUserIdAndParentOrgId(userId, parentOrgId);
			gLSubGroupList = Arrays.asList(modelMapper.map(tGsGlSubGroupList, GLSubGroup[].class));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLSubGroupList;
	}

	@Override
	public List<GLAccount> getGLAccountData(int userId, int parentOrgId) throws GlobalSettingsServiceException {
		log.debug("getGLSubGroupData::");
		List<TGsGlAccount> tGsGlAccountList = null;
		List<GLAccount> gLAccountList = null;
		try {
			tGsGlAccountList = gsGLAccountRepo.findByUserIdAndParentOrgId(userId, parentOrgId);
			gLAccountList = Arrays.asList(modelMapper.map(tGsGlAccountList, GLAccount[].class));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GlobalSettingsServiceException("Something Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return gLAccountList;
	}

}
