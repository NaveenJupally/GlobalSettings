package com.automate.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automate.entity.globalsettings.TGsAssetGroup;
import com.automate.entity.globalsettings.TGsAssetNature;
import com.automate.entity.globalsettings.TGsBusinessUnit;
import com.automate.entity.globalsettings.TGsCostCenter;
import com.automate.entity.globalsettings.TGsExpenditureGlHead;
import com.automate.entity.globalsettings.TGsGlGroup;
import com.automate.entity.globalsettings.TGsGlSubGroup;
import com.automate.entity.globalsettings.TGsIncomeGlHead;
import com.automate.entity.globalsettings.TGsLiabilityGlHead;
import com.automate.entity.globalsettings.TGsLiabilityGlSubhead;
import com.automate.entity.globalsettings.TGsOrganaization;
import com.automate.entity.globalsettings.TGsProfitCenter;
import com.automate.entity.globalsettings.TGsVertical;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.AssetGroup;
import com.automate.model.globalsettings.AssetNature;
import com.automate.model.globalsettings.BusinessUnit;
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
import com.automate.model.globalsettings.TaxGroup;
import com.automate.model.globalsettings.TaxMapping;
import com.automate.model.globalsettings.TaxSection;
import com.automate.model.globalsettings.UserOrganisation;
import com.automate.model.globalsettings.Vendor;
import com.automate.model.globalsettings.Vertical;
import com.automate.response.CustomResponse;
import com.automate.response.ExpenditureGlHeadResponse;
import com.automate.response.IncomeGlHeadResponse;
import com.automate.service.GlobalBusinessService;
import com.automate.service.GsService;

import io.swagger.annotations.ApiOperation;

/**
 * @author srujan
 *
 */

@RestController
@RequestMapping(value = "/global-settings")
@CrossOrigin
public class GsController {

	private static Logger log = LoggerFactory.getLogger(GsController.class);

	@Autowired
	GlobalBusinessService globalBusinessService;

	@Autowired
	Environment env;

	@Autowired
	GsService gsService;

	/**
	 * Controller to validate login credentials.
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public Login logIn(@RequestBody LoginRequest request) throws GlobalSettingsServiceException {
		Login response = null;
		if (Optional.of(request).isPresent()) {
			response = gsService.validateLogIn(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	/**
	 * Controller to fetch organization names
	 * 
	 * @param userId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/get/orgNames/{userid}/{parentorgid}")
	public ResponseEntity<UserOrganisation> getOrg(@PathVariable(name = "userid") int userId,
			@PathVariable(name = "parentorgid") int parentorgid) throws GlobalSettingsServiceException {
		UserOrganisation response = null;
		if (Optional.of(parentorgid).isPresent()) {
			response = gsService.getUserOrganisation(userId, parentorgid);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch vertical names
	 * 
	 * @param globalOrgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/get/verticalNames/{userid}/{parentorgid}/{orgId}")
	public ResponseEntity<String> get(@PathVariable(name = "userid") int userId,
			@PathVariable(name = "parentorgid") int parentOrgId, @PathVariable(name = "orgId") int orgId)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(orgId).isPresent()) {
			response = gsService.getVerticals(orgId, userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch business units
	 * 
	 * @param verticalId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/get/businessUnits")
	public ResponseEntity<BusinessUnits> getBusinessUnits(@RequestBody GetBusinessUnit request)
			throws GlobalSettingsServiceException {
		BusinessUnits response = null;
		if (Optional.of(request).isPresent()) {
			response = gsService.getBusinessUnits(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * Controller to fetch financial year
	 * 
	 * @param parentOrgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/getfinancialyear/{parentorgid}/{userid}")
	public ResponseEntity<String> getFinancialYear(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getFinancialYear(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save financial year
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/savefinancialyear")
	public ResponseEntity<String> saveFinancialYear(@RequestBody SaveFinancialYear request)
			throws GlobalSettingsServiceException {
		String response = "";

		if (Optional.of(request).isPresent()) {
			response = gsService.saveFinancialYear(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update financial year
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/updatefinancialyear")
	public ResponseEntity<String> updateFinancialYear(@RequestBody FinancialYear request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateFinancialYear(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed financial year id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/delete_financial_year/{id}")
	public ResponseEntity<CustomResponse> deleteFinancialYear(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;

		if (Optional.of(id).isPresent()) {
			response = gsService.deleteFinancialYear(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch calendar year
	 * 
	 * @param gsOrgid
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/get_calendar_year/{parentorgid}/{userid}")
	public ResponseEntity<String> getCalendar(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getCalendarYear(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save calendar year
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/save_calendar_year")
	public ResponseEntity<String> saveCalendar(@RequestBody SaveCalendarYear request)
			throws GlobalSettingsServiceException {
		String response = "";

		if (Optional.of(request).isPresent()) {
			response = gsService.saveCalendarYear(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update calendar year
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/update_calendar_year")
	public ResponseEntity<String> updateCalendar(@RequestBody CalendarYear request)
			throws GlobalSettingsServiceException {

		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateCalendarYear(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed calendar year id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/delete_calendar_year/{id}")
	public ResponseEntity<CustomResponse> deleteCalendar(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteCalendarYear(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch financial & calendar year configuration
	 * 
	 * @param orgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/get_modulemapping/{orgId}")
	public ResponseEntity<String> getFYandCYCongfiguration(@PathVariable(name = "orgId") int orgId)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(orgId).isPresent()) {
			response = gsService.getFYandCYCongfiguration(orgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update financial & calendar year configuration
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/update_modulemapping")
	public ResponseEntity<String> updateFYandCY(@RequestBody FYandCYConfiguration request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateFYandCYCongfiguration(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save financial & calendar year configuration
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/save_modulemapping")
	public ResponseEntity<String> saveFYandCY(@RequestBody FYandCYConfiguration request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.saveFYandCYCongfiguration(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed id financial & calendar year configuration
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/delete_modulemapping/{id}")
	public ResponseEntity<CustomResponse> deleteFYandCY(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteFYandCYCongfiguration(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch countries
	 * 
	 * @param orgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/getcountry/{parentorgid}/{userid}")
	public ResponseEntity<CountryInfo> getCountry(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		CountryInfo response = null;
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getCountry(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

		// TODO need to add failure cases.
	}

	/**
	 * Controller to save countries
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/savecountry")
	public ResponseEntity<String> saveCountry(@RequestBody CountryInfo request) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.saveCountry(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update countries
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceExcepti
	 */
	@CrossOrigin
	@PutMapping(value = "/updatecountry")
	public ResponseEntity<String> updateCountry(@RequestBody Country request) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateCountry(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed country id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/deletecountry/{id}")
	public ResponseEntity<CustomResponse> deleteCountry(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteCountry(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch currency info
	 * 
	 * @param orgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/getcurrency/{parentorgid}/{userid}")
	public ResponseEntity<Currency> getCurrency(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		Currency response = null;
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getCurrency(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save currency info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/savecurrency")
	public ResponseEntity<String> saveCurrency(@RequestBody Currency request) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.saveCurrency(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update currency info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/updatecurrency")
	public ResponseEntity<String> updateCurrency(@RequestBody Currency request) throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateCurrency(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed currency id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/deletecurrency/{id}")
	public ResponseEntity<CustomResponse> deleteCurrency(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteCurrency(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch language info
	 * 
	 * @param orgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/getlanguage/{parentorgid}/{userid}")
	public ResponseEntity<List<Language>> getLanguage(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<Language> response = null;
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getLanguage(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save language info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/savelanguage")
	public ResponseEntity<String> saveLanguage(@RequestBody List<Language> request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.saveLanguage(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update language info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/updatelanguage")
	public ResponseEntity<String> updateLanguage(@RequestBody List<Language> request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.updateLanguageCurrency(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed language id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/deletelanguage/{id}")
	public ResponseEntity<CustomResponse> deleteLanguage(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteLanguage(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to fetch rule configuration info
	 * 
	 * @param orgId
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@GetMapping(value = "/get_ruleconfig/{parentorgid}/{userid}")
	public ResponseEntity<List<RuleConfiguration>> getRule(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<RuleConfiguration> response = null;
		if (Optional.of(parentOrgId).isPresent()) {
			response = gsService.getRuleCongfiguration(parentOrgId, userId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to save rule configuration info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PostMapping(value = "/saverule_config")
	public ResponseEntity<String> saveRuleCongfiguration(@RequestBody RuleConfigurationInfo request)
			throws GlobalSettingsServiceException {
		String response = "";
		if (Optional.of(request).isPresent()) {
			response = gsService.saveRuleCongfiguration(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to update rule configuration info
	 * 
	 * @param request
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@PutMapping(value = "/update_rule_config")
	public ResponseEntity<RuleConfiguration> updateRule(@RequestBody RuleConfiguration request)
			throws GlobalSettingsServiceException {
		RuleConfiguration response = null;
		if (Optional.of(request).isPresent()) {
			response = gsService.updateRuleCongfiguration(request);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Controller to delete passed rule configuration id
	 * 
	 * @param id
	 * @return
	 * @throws GlobalSettingsServiceException
	 */
	@CrossOrigin
	@DeleteMapping(value = "/delete_rule_config/{id}")
	public ResponseEntity<CustomResponse> deleteRule(@PathVariable(name = "id") int id)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(id).isPresent()) {
			response = gsService.deleteRuleCongfiguration(id);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/*
	 * @CrossOrigin
	 * 
	 * @PostMapping(value = "save/global_org") public ResponseEntity<?>
	 * saveGlobalOrg(@RequestBody GlobalOrg globalOrg) throws
	 * GlobalSettingsServiceException { GlobalOrg response = null; if
	 * (Optional.of(globalOrg).isPresent()) { response =
	 * globalBusinessService.saveGlobalOrg(globalOrg); } else { return new
	 * ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response, HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @PutMapping(value = "update/global_org") public ResponseEntity<?>
	 * updateGlobalOrg(@RequestBody GlobalOrg globalOrg) throws
	 * GlobalSettingsServiceException { GlobalOrg response = null; if
	 * (Optional.of(globalOrg).isPresent()) { response =
	 * globalBusinessService.saveGlobalOrg(globalOrg); } else { return new
	 * ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response, HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @DeleteMapping(value = "delete/{gl_org_id}") public ResponseEntity<?>
	 * updateGlobalOrg(@PathParam("gl_org_id") Integer id) throws
	 * GlobalSettingsServiceException { GlobalOrg response = null; if
	 * (Optional.of(id).isPresent()) { response =
	 * globalBusinessService.deleteGlobalOrg(id); } else { return new
	 * ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response, HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value = "get/{gl_org_id}") public ResponseEntity<?>
	 * getGlobalOrg(@PathParam("gl_org_id") Integer id) throws
	 * GlobalSettingsServiceException { GlobalOrg response = null; if
	 * (Optional.of(id).isPresent()) { response =
	 * globalBusinessService.getGlobalOrg(id); } else { return new
	 * ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response, HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value = "get/AllGlobalOrgs") public ResponseEntity<?>
	 * getAllGlobalOrg(@RequestParam(defaultValue = "0") int pageNo,
	 * 
	 * @RequestParam(defaultValue = "10") int pageSize) throws
	 * GlobalSettingsServiceException { List<TGsOrganaization> response = null;
	 * 
	 * response = globalBusinessService.getAllGlobalOrg(pageNo, pageSize);
	 * 
	 * return new ResponseEntity<>(response, HttpStatus.OK); }
	 */

	@CrossOrigin
	@ApiOperation(value = "Tax Group Save API (Screen 17)", tags = "Tax Group Save API (Screen 17)")
	@PostMapping(value = "save/create_tax_group")
	public ResponseEntity<CustomResponse> createTaxGroup(@RequestBody TaxGroup taxGroup)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(taxGroup).isPresent()) {
			response = gsService.createTaxGroup(taxGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "save/create_tax_account")
	public ResponseEntity<CustomResponse> createTaxAccount(@RequestBody TaxAccount taxAccount)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(taxAccount).isPresent()) {
			response = gsService.createTaxAccount(taxAccount);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/*
	 * @CrossOrigin
	 * 
	 * @PostMapping(value = "save/create_tax_account_nature") public
	 * ResponseEntity<CustomResponse> createTaxAccountNature(@RequestBody
	 * TaxAccountNature taxAccountNature) throws GlobalSettingsServiceException {
	 * CustomResponse response = null; if
	 * (Optional.of(taxAccountNature).isPresent()) { response =
	 * gsService.createTaxAccountNature(taxAccountNature); } else { throw new
	 * GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
	 * HttpStatus.BAD_REQUEST); } return new ResponseEntity<>(response,
	 * HttpStatus.CREATED); }
	 */

	@CrossOrigin
	@PostMapping(value = "save/create_tax_section")
	public ResponseEntity<CustomResponse> createTaxSection(@RequestBody TaxSection taxSection)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(taxSection).isPresent()) {
			response = gsService.createTaxSection(taxSection);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "save/create_tax_mapping")
	public ResponseEntity<CustomResponse> createTaxMapping(@RequestBody TaxMapping taxMapping)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(taxMapping).isPresent()) {
			response = gsService.createTaxMapping(taxMapping);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "save/create_gl_acct")
	public ResponseEntity<CustomResponse> createGLAccount(@RequestBody GLAccount glAccount)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(glAccount).isPresent()) {
			response = gsService.createGlAccount(glAccount);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@GetMapping(value = "get/gl_acct")
	public ResponseEntity<List<GLAccount>> getGlAccountData(int pageNo, int size)
			throws GlobalSettingsServiceException {
		List<GLAccount> list = null;
		if (Optional.of(pageNo).isPresent() && Optional.of(size).isPresent()) {
			list = gsService.getGlAccountData(pageNo, size);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/gl_acct")
	public ResponseEntity<GLAccount> updatelAccountData(@RequestBody GLAccount glAccount)
			throws GlobalSettingsServiceException {
		GLAccount responseObj = null;
		if (Optional.of(glAccount).isPresent()) {
			responseObj = gsService.updateGlAccountData(glAccount);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseObj, HttpStatus.OK);
	}

	/*
	 * @CrossOrigin
	 * 
	 * @GetMapping(value="get/tax_account_nature") public
	 * ResponseEntity<List<TaxAccountNature>> getTaxAccNature(int pageNo,int size)
	 * throws GlobalSettingsServiceException{ List<TaxAccountNature> list = null;
	 * if(Optional.of(pageNo).isPresent() && Optional.of(size).isPresent()) { list =
	 * gsService.getTaxAccNature(pageNo,size); }else { throw new
	 * GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),HttpStatus.
	 * BAD_REQUEST); } return new ResponseEntity<>(list,HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @PutMapping(value="update/tax_account_nature") public
	 * ResponseEntity<TaxAccountNature> updateTaxAccountNature(@RequestBody
	 * TaxAccountNature taxAccountNature) throws GlobalSettingsServiceException{
	 * TaxAccountNature responseObj = null;
	 * if(Optional.of(taxAccountNature).isPresent()) { responseObj =
	 * gsService.updateTaxAccountNature(taxAccountNature); }else { throw new
	 * GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),HttpStatus.
	 * BAD_REQUEST); } return new ResponseEntity<>(responseObj,HttpStatus.OK); }
	 */
	@CrossOrigin
	@GetMapping(value = "get/tax_accounts")
	public ResponseEntity<List<TaxAccount>> getTaxAccNature(int pageNo, int size)
			throws GlobalSettingsServiceException {
		List<TaxAccount> list = null;
		if (Optional.of(pageNo).isPresent() && Optional.of(size).isPresent()) {
			list = gsService.getTaxAccList(pageNo, size);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/tax_accounts")
	public ResponseEntity<TaxAccount> updateTaxAccountNature(@RequestBody TaxAccount taxAccount)
			throws GlobalSettingsServiceException {
		TaxAccount responseObj = null;
		if (Optional.of(taxAccount).isPresent()) {
			responseObj = gsService.updateTaxAccounts(taxAccount);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseObj, HttpStatus.OK);
	}

	/*
	 * @CrossOrigin
	 * 
	 * @GetMapping(value="get/tax_mapping") public ResponseEntity<List<TaxMapping>>
	 * getTaxMapping(int pageNo,int size) throws GlobalSettingsServiceException{
	 * List<TaxMapping> list = null; if(Optional.of(pageNo).isPresent() &&
	 * Optional.of(size).isPresent()) { list = gsService.getTaxMapping(pageNo,size);
	 * }else { throw new
	 * GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),HttpStatus.
	 * BAD_REQUEST); } return new ResponseEntity<>(list,HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @PutMapping(value="update/tax_mapping") public ResponseEntity<TaxMapping>
	 * updateTaxMapping(@RequestBody TaxMapping taxMapping) throws
	 * GlobalSettingsServiceException{ TaxMapping responseObj = null;
	 * if(Optional.of(taxMapping).isPresent()) { responseObj =
	 * gsService.updateTaxMapping(taxMapping); }else { throw new
	 * GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),HttpStatus.
	 * BAD_REQUEST); } return new ResponseEntity<>(responseObj,HttpStatus.OK); }
	 */

	@CrossOrigin
	@GetMapping(value = "get/tax_mapping")
	public ResponseEntity<List<TaxMapping>> getTaxMapping(int pageNo, int size) throws GlobalSettingsServiceException {
		List<TaxMapping> list = null;
		if (Optional.of(pageNo).isPresent() && Optional.of(size).isPresent()) {
			list = gsService.getTaxMapping(pageNo, size);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/tax_mapping")
	public ResponseEntity<TaxMapping> updateTaxMapping(@RequestBody TaxMapping taxMapping)
			throws GlobalSettingsServiceException {
		TaxMapping responseObj = null;
		if (Optional.of(taxMapping).isPresent()) {
			responseObj = gsService.updateTaxMapping(taxMapping);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responseObj, HttpStatus.OK);
	}
	/*
	 * 
	 * @CrossOrigin
	 * 
	 * @PutMapping(value="update/global_org") public ResponseEntity<?>
	 * updateGlobalOrg(@RequestBody GlobalOrg globalOrg) throws
	 * GlobalSettingsServiceException{ GlobalOrg response = null;
	 * if(Optional.of(globalOrg).isPresent()) { response =
	 * gsService.saveGlobalOrg(globalOrg); }else { return new
	 * ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response,HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @DeleteMapping(value="delete/{gl_org_id}") public ResponseEntity<?>
	 * updateGlobalOrg(@PathParam("gl_org_id") Integer id) throws
	 * GlobalSettingsServiceException{ GlobalOrg response = null;
	 * if(Optional.of(id).isPresent()) { response = gsService.deleteGlobalOrg(id);
	 * }else { return new
	 * ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response,HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value="get/{gl_org_id}") public ResponseEntity<?>
	 * getGlobalOrg(@PathParam("gl_org_id") Integer id) throws
	 * GlobalSettingsServiceException{ GlobalOrg response = null;
	 * if(Optional.of(id).isPresent()) { response = gsService.getGlobalOrg(id);
	 * }else { return new
	 * ResponseEntity<>("Invalid Request",HttpStatus.BAD_REQUEST); } return new
	 * ResponseEntity<>(response,HttpStatus.OK); }
	 * 
	 * @CrossOrigin
	 * 
	 * @GetMapping(value="get/AllGlobalOrgs") public ResponseEntity<?>
	 * getAllGlobalOrg(@RequestParam(defaultValue = "0") int pageNo,
	 * 
	 * @RequestParam(defaultValue = "10") int pageSize) throws
	 * GlobalSettingsServiceException{ List<TGsOrganaization> response = null;
	 * 
	 * response = globalBusinessService.getAllGlobalOrg(pageNo,pageSize);
	 * 
	 * return new ResponseEntity<>(response,HttpStatus.OK); }
	 */

	@CrossOrigin
	@PostMapping(value = "save/dynamic_workflow")
	public ResponseEntity<?> saveDynamicWorkflow(@RequestBody DynamicWorkflow dynamicWorkflow)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(dynamicWorkflow).isPresent()) {
			response = gsService.saveDynamicWorkflow(dynamicWorkflow);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "save/vendor_master")
	public ResponseEntity<CustomResponse> saveVendorMaster(@RequestBody Vendor vendor)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(vendor).isPresent()) {
			response = gsService.saveVendorMaster(vendor);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// swathi changes starts
	@CrossOrigin
	@PostMapping(value = "save/create_cost_center")
	public ResponseEntity<CustomResponse> createCostCenter(@RequestBody CostCenter costcenter)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(costcenter).isPresent()) {
			response = gsService.createCostCenter(costcenter);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@GetMapping(value = "/get/cost_center/{costCenterid}")
	public ResponseEntity<CostCenter> getCostCenter(@PathVariable("costCenterid") int costCenterid)
			throws GlobalSettingsServiceException {
		CostCenter costcenter = null;
		if (Optional.of(costCenterid).isPresent()) {
			costcenter = gsService.getCostCenter(costCenterid);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(costcenter, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/cost_center")
	public ResponseEntity<TGsCostCenter> updateCostCenterData(@RequestBody CostCenter costCenter)
			throws GlobalSettingsServiceException {
		TGsCostCenter costCent = null;
		if (Optional.of(costCenter).isPresent()) {
			costCent = gsService.updateCostCenterData(costCenter);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(costCent, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping("/delete/cost_center/{costCenterId}")
	private void deleteCostCenter(@PathVariable("costCenterId") int costCenterId) {
		try {
			gsService.deleteCostCenter(costCenterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/////////

	@CrossOrigin
	@GetMapping(value = "/get/profit_center/{profitCenterid}")
	public ResponseEntity<ProfitCenter> getProfitCenter(@PathVariable("profitCenterid") int profitCenterid)
			throws GlobalSettingsServiceException {
		ProfitCenter profitcenter = null;
		if (Optional.of(profitCenterid).isPresent()) {
			profitcenter = gsService.getProfitCenter(profitCenterid);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(profitcenter, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping("/delete/profit_center/{profitCenterId}")
	private void deleteProfitCenter(@PathVariable("profitCenterId") int profitCenterId) {
		try {
			gsService.deleteProfitCenter(profitCenterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CrossOrigin
	@PostMapping(value = "save/profit_center")
	public ResponseEntity<CustomResponse> saveProfitCenter(@RequestBody ProfitCenter profitcenter)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(profitcenter).isPresent()) {
			response = gsService.createProfitCenter(profitcenter);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping(value = "update/profit_center")
	public ResponseEntity<TGsProfitCenter> updateProfitCenterData(@RequestBody ProfitCenter profitCenter)
			throws GlobalSettingsServiceException {
		TGsProfitCenter profitCentr = null;
		if (Optional.of(profitCenter).isPresent()) {
			profitCentr = gsService.updateProfitcenterData(profitCenter);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(profitCentr, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "save/global_orgs")
	public ResponseEntity<?> saveGlobalOrg(@RequestBody List<GlobalOrg> globalOrgs)
			throws GlobalSettingsServiceException {
		List<GlobalOrg> response = null;
		if (Optional.of(globalOrgs).isPresent() && !globalOrgs.isEmpty()) {
			response = globalBusinessService.saveGlobalOrg(globalOrgs);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/global_orgs")
	public ResponseEntity<?> updateGlobalOrg(@RequestBody GlobalOrg globalOrgs) throws GlobalSettingsServiceException {
		GlobalOrg response = null;
		if (Optional.of(globalOrgs).isPresent()) {
			response = globalBusinessService.updateGlobalOrg(globalOrgs);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(value = "global_orgs/{gl_org_id}")
	public ResponseEntity<?> deleteGlobalOrg(@PathVariable("gl_org_id") Integer id)
			throws GlobalSettingsServiceException {
		GlobalOrg response = null;
		if (Optional.of(id).isPresent()) {
			response = globalBusinessService.deleteGlobalOrg(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "global_orgs/{gl_org_id}")
	public ResponseEntity<?> getGlobalOrg(@PathVariable("gl_org_id") Integer id) throws GlobalSettingsServiceException {
		GlobalOrg response = null;
		if (Optional.of(id).isPresent()) {
			response = globalBusinessService.getGlobalOrg(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "get/AllGlobalOrgs")
	public ResponseEntity<?> getAllGlobalOrg(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) throws GlobalSettingsServiceException {
		List<TGsOrganaization> response = null;

		response = globalBusinessService.getAllGlobalOrg(pageNo, pageSize);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "save-verticles")
	public ResponseEntity<?> saveVerticle(@RequestBody List<Vertical> verticals) throws GlobalSettingsServiceException {
		List<Vertical> response = null;
		if (null != verticals && !verticals.isEmpty()) {
			response = globalBusinessService.saveVerticle(verticals);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update-verticle")
	public ResponseEntity<?> updateVerticle(@RequestBody Vertical vertical) throws GlobalSettingsServiceException {
		Vertical response = null;
		if (Optional.of(vertical).isPresent()) {
			response = globalBusinessService.updateVerticle(vertical);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(value = "delete-vertical/{verticle_id}")
	public ResponseEntity<?> deleteVerticle(@PathVariable("verticle_id") Integer id)
			throws GlobalSettingsServiceException {
		Vertical response = null;
		if (Optional.of(id).isPresent()) {
			response = globalBusinessService.deleteVerticle(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "get-vertical/{verticle_id}")
	public ResponseEntity<?> getVerticle(@PathVariable("verticle_id") Integer id)
			throws GlobalSettingsServiceException {
		Vertical vertical = null;
		if (Optional.of(id).isPresent()) {
			vertical = globalBusinessService.getVerticle(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vertical, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "get/AllVerticles")
	public ResponseEntity<?> getAllVerticles(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) throws GlobalSettingsServiceException {
		List<TGsVertical> response = null;

		response = globalBusinessService.getAllVerticles(pageNo, pageSize);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(value = "save-bunit/bunits")
	public ResponseEntity<?> saveBusinessUnit(@RequestBody List<BusinessUnit> businessUnits)
			throws GlobalSettingsServiceException {
		List<BusinessUnit> response = null;
		if (null != businessUnits && !businessUnits.isEmpty()) {
			response = globalBusinessService.saveBusinessUnit(businessUnits);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update-bunit/bunit")
	public ResponseEntity<?> updateBusinessUnit(@RequestBody BusinessUnit businessUnit)
			throws GlobalSettingsServiceException {
		BusinessUnit response = null;
		if (Optional.of(businessUnit).isPresent()) {
			response = globalBusinessService.updateBusinessUnit(businessUnit);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(value = "delete-bunit/{bunit_id}")
	public ResponseEntity<?> deleteBusinessUnit(@PathVariable("bunit_id") Integer id)
			throws GlobalSettingsServiceException {
		TGsBusinessUnit response = null;
		if (Optional.of(id).isPresent()) {
			response = globalBusinessService.deleteBusinessUnit(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "get-bunit/{bunit_id}")
	public ResponseEntity<?> getBusinessUnit(@PathVariable("bunit_id") Integer id)
			throws GlobalSettingsServiceException {
		BusinessUnit tGsBusinessUnit = null;
		if (Optional.of(id).isPresent()) {
			tGsBusinessUnit = globalBusinessService.getBusinessUnit(id);
		} else {
			return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsBusinessUnit, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "get/AllBusUnits")
	public ResponseEntity<?> getBusinessUnits(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) throws GlobalSettingsServiceException {
		List<TGsBusinessUnit> response = null;

		response = globalBusinessService.getAllBusinessUnits(pageNo, pageSize);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Swathi changes

	@CrossOrigin
	@GetMapping(value = "/get/incomeglhead/{incomeGlHeadId}")
	public ResponseEntity<IncomeGLHead> getIncomeGLHead(@PathVariable("incomeGlHeadId") int incomeGlHeadId)
			throws GlobalSettingsServiceException {
		IncomeGLHead incomeGlHeadIdObj = null;
		if (Optional.of(incomeGlHeadId).isPresent()) {
			incomeGlHeadIdObj = gsService.getIncomeGLHead(incomeGlHeadId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(incomeGlHeadIdObj, HttpStatus.OK);
	}

	@DeleteMapping("/delete/incomeglhead/{incomeGlHeadId}")
	private void deleteIncomeGLHead(@PathVariable("incomeGlHeadId") int incomeGlHeadId) {
		try {
			gsService.deleteIncomeGLHead(incomeGlHeadId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CrossOrigin
	@PostMapping(value = "save/incomeglhead")
	public ResponseEntity<IncomeGlHeadResponse> saveIncomeGlHead(@RequestBody IncomeGLHead incomeglhead)
			throws GlobalSettingsServiceException {
		IncomeGlHeadResponse response = null;
		if (Optional.of(incomeglhead).isPresent()) {
			response = gsService.createIncomeGLHead(incomeglhead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping(value = "update/incomeglhead")
	public ResponseEntity<TGsIncomeGlHead> updateIncomeGlHead(@RequestBody IncomeGLHead incomeglhead)
			throws GlobalSettingsServiceException {
		TGsIncomeGlHead incomeglheadObj = null;
		if (Optional.of(incomeglhead).isPresent()) {
			incomeglheadObj = gsService.updateIncomeGlHead(incomeglhead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(incomeglheadObj, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/expenditureGlHead/{expenditureGlHeadId}")
	public ResponseEntity<ExpenditureGLHead> getExpenditureGLHead(
			@PathVariable("expenditureGlHeadId") int expenditureGlHeadId) throws GlobalSettingsServiceException {
		ExpenditureGLHead expenditurehead = null;
		if (Optional.of(expenditureGlHeadId).isPresent()) {
			expenditurehead = gsService.getExpenditureGLHead(expenditureGlHeadId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(expenditurehead, HttpStatus.OK);
	}

	@DeleteMapping("/delete/expenditureGlHead/{expenditureGlHeadId}")
	private void deleteExpenditureGLHead(@PathVariable("expenditureGlHeadId") int expenditureGlHeadId) {
		try {
			gsService.deleteExpenditureGLHead(expenditureGlHeadId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CrossOrigin
	@PostMapping(value = "/save/expenditureGlHead")
	public ResponseEntity<ExpenditureGlHeadResponse> createExpenditureGlHead(
			@RequestBody ExpenditureGLHead expenditureglhead) throws GlobalSettingsServiceException {
		ExpenditureGlHeadResponse response = null;
		if (Optional.of(expenditureglhead).isPresent()) {
			response = gsService.createExpenditureGlHead(expenditureglhead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping(value = "/update/expenditureGlHead")
	public ResponseEntity<TGsExpenditureGlHead> updateIncomeGlHead(@RequestBody ExpenditureGLHead expenditureglhead)
			throws GlobalSettingsServiceException {
		TGsExpenditureGlHead tGsExpenditureGlHeadObj = null;
		if (Optional.of(expenditureglhead).isPresent()) {
			tGsExpenditureGlHeadObj = gsService.updateExpenditureGLHead(expenditureglhead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsExpenditureGlHeadObj, HttpStatus.OK);
	}

	///////

	@CrossOrigin
	@GetMapping(value = "/get/asset_nature/{assetNatureId}")
	public ResponseEntity<AssetNature> getAssetNature(@PathVariable("assetNatureId") int assetNatureId)
			throws GlobalSettingsServiceException {
		AssetNature assetNature = null;
		if (Optional.of(assetNatureId).isPresent()) {
			assetNature = gsService.getAssetNature(assetNatureId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assetNature, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/asset_group/{assetGroupId}")
	public ResponseEntity<AssetGroup> getAssetGroup(@PathVariable("assetGroupId") int assetGroupId)
			throws GlobalSettingsServiceException {
		AssetGroup assetGroup = null;
		if (Optional.of(assetGroupId).isPresent()) {
			assetGroup = gsService.getAssetGroup(assetGroupId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assetGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/liability_GLHead/{liabilityGLHeadId}")
	public ResponseEntity<LiabilityGLHead> getLiabilityGLHead(@PathVariable("liabilityGLHeadId") int liabilityGLHeadId)
			throws GlobalSettingsServiceException {
		LiabilityGLHead liabilityGLHead = null;
		if (Optional.of(liabilityGLHeadId).isPresent()) {
			liabilityGLHead = gsService.getLiabilityGLHead(liabilityGLHeadId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(liabilityGLHead, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/liability_GLSubHead/{liabilityGLSubHeadId}")
	public ResponseEntity<LiabilityGLSubHead> getLiabilityGLSubHead(
			@PathVariable("liabilityGLSubHeadId") int liabilityGLSubHeadId) throws GlobalSettingsServiceException {
		LiabilityGLSubHead liabilityGLSubHead = null;
		if (Optional.of(liabilityGLSubHeadId).isPresent()) {
			liabilityGLSubHead = gsService.getLiabilityGLSubHead(liabilityGLSubHeadId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(liabilityGLSubHead, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/tGsGlGroup/{tGsGlGroupId}")
	public ResponseEntity<GLGroup> getGLGroup(@PathVariable("tGsGlGroupId") int tGsGlGroupId)
			throws GlobalSettingsServiceException {
		GLGroup glGroup = null;
		if (Optional.of(tGsGlGroupId).isPresent()) {
			glGroup = gsService.getGLGroup(tGsGlGroupId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(glGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/tGsGlSubGroup/{tGsGlSubGroupId}")
	public ResponseEntity<GLSubGroup> getGLSubGroup(@PathVariable("tGsGlSubGroupId") int tGsGlSubGroupId)
			throws GlobalSettingsServiceException {
		GLSubGroup gLSubGroup = null;
		if (Optional.of(tGsGlSubGroupId).isPresent()) {
			gLSubGroup = gsService.getGLSubGroup(tGsGlSubGroupId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gLSubGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/tGsGlAccount/{tGsGlAccountId}")
	public ResponseEntity<GLAccount> getGLAccount(@PathVariable("tGsGlAccountId") int tGsGlAccountId)
			throws GlobalSettingsServiceException {
		GLAccount gLAccount = null;
		if (Optional.of(tGsGlAccountId).isPresent()) {
			gLAccount = gsService.getGLAccount(tGsGlAccountId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gLAccount, HttpStatus.OK);
	}

//////delete

	@DeleteMapping("/delete/assetNature/{assetNatureId}")
	private void deleteAssetNature(@PathVariable("assetNatureId") int assetNatureId) {
		try {
			gsService.deleteAssetNature(assetNatureId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/assetGroup/{assetGroupId}")
	private void deleteAssetGroup(@PathVariable("assetGroupId") int assetGroupId) {
		try {
			gsService.deleteAssetGroup(assetGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/liabilityGlHead/{liabilityGlHeadId}")
	private void deleteLiabilityGlHead(@PathVariable("liabilityGlHeadId") int liabilityGlHeadId) {
		try {
			gsService.deleteLiabilityGlHead(liabilityGlHeadId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/liabilityGlSubhead/{liabilityGlSubheadId}")
	private void deleteLiabilityGlSubhead(@PathVariable("liabilityGlSubheadId") int liabilityGlSubheadId) {
		try {
			gsService.deleteLiabilityGlSubhead(liabilityGlSubheadId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/glGroup/{glGroupId}")
	private void deleteGlGroup(@PathVariable("glGroupId") int glGroupId) {
		try {
			gsService.deleteGlGroup(glGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/glSubGroup/{glSubGroupId}")
	private void deleteGlSubGroup(@PathVariable("glSubGroupId") int glSubGroupId) {
		try {
			gsService.deleteGlSubGroup(glSubGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/delete/glAccount/{glAccountId}")
	private void deleteGlAccount(@PathVariable("glAccountId") int glAccountId) {
		try {
			gsService.deleteGlAccount(glAccountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@CrossOrigin
	@PostMapping(value = "/save/assetnature")
	public ResponseEntity<CustomResponse> createAssetNature(@RequestBody AssetNature assetnature)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(assetnature).isPresent()) {
			response = gsService.createAssetNature(assetnature);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/save/assetgroup")
	public ResponseEntity<CustomResponse> createAssetGroup(@RequestBody AssetGroup assetgroup)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(assetgroup).isPresent()) {
			response = gsService.createAssetGroup(assetgroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/save/liabilityGlHead")
	public ResponseEntity<CustomResponse> createLiabilityGlHead(@RequestBody LiabilityGLHead liabilityGLHead)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(liabilityGLHead).isPresent()) {
			response = gsService.createLiabilityGlHead(liabilityGLHead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/save/liabilityGlSubhead")
	public ResponseEntity<CustomResponse> createLiabilityGlSubhead(@RequestBody LiabilityGLSubHead liabilityGLSubHead)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(liabilityGLSubHead).isPresent()) {
			response = gsService.createLiabilityGlSubhead(liabilityGLSubHead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/save/glGroup")
	public ResponseEntity<CustomResponse> createGlGroup(@RequestBody GLGroup gLGroup)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(gLGroup).isPresent()) {
			response = gsService.createGlGroup(gLGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PostMapping(value = "/save/glSubGroup")
	public ResponseEntity<CustomResponse> createGlSubGroup(@RequestBody GLSubGroup gLSubGroup)
			throws GlobalSettingsServiceException {
		CustomResponse response = null;
		if (Optional.of(gLSubGroup).isPresent()) {
			response = gsService.createGlSubGroup(gLSubGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping(value = "update/asset_nature")
	public ResponseEntity<TGsAssetNature> updateAssetNature(@RequestBody AssetNature assetNature)
			throws GlobalSettingsServiceException {
		TGsAssetNature tGsAssetNature = null;
		if (Optional.of(assetNature).isPresent()) {
			tGsAssetNature = gsService.updateAssetNature(assetNature);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsAssetNature, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/asset_group")
	public ResponseEntity<TGsAssetGroup> updateAssetGroup(@RequestBody AssetGroup assetGroup)
			throws GlobalSettingsServiceException {
		TGsAssetGroup tGsAssetGroup = null;
		if (Optional.of(assetGroup).isPresent()) {
			tGsAssetGroup = gsService.updateAssetGroup(assetGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsAssetGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/liability_glhead")
	public ResponseEntity<TGsLiabilityGlHead> updateLiabilityGlHead(@RequestBody LiabilityGLHead liabilityGLHead)
			throws GlobalSettingsServiceException {
		TGsLiabilityGlHead tGsLiabilityGlHead = null;
		if (Optional.of(liabilityGLHead).isPresent()) {
			tGsLiabilityGlHead = gsService.updateLiabilityGlHead(liabilityGLHead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsLiabilityGlHead, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/liability_sub_glhead")
	public ResponseEntity<TGsLiabilityGlSubhead> updateLiabilityGlSubhead(
			@RequestBody LiabilityGLSubHead liabilityGLSubHead) throws GlobalSettingsServiceException {
		TGsLiabilityGlSubhead tGsLiabilityGlSubhead = null;
		if (Optional.of(liabilityGLSubHead).isPresent()) {
			tGsLiabilityGlSubhead = gsService.updateLiabilityGlSubhead(liabilityGLSubHead);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsLiabilityGlSubhead, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/glgroup")
	public ResponseEntity<TGsGlGroup> updateGlGroup(@RequestBody GLGroup gLGroup)
			throws GlobalSettingsServiceException {
		TGsGlGroup tGsGlGroup = null;
		if (Optional.of(gLGroup).isPresent()) {
			tGsGlGroup = gsService.updateGlGroup(gLGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsGlGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping(value = "update/glsubgroup")
	public ResponseEntity<TGsGlSubGroup> updateGlSubGroup(@RequestBody GLSubGroup gLSubGroup)
			throws GlobalSettingsServiceException {
		TGsGlSubGroup tGsGlSubGroup = null;
		if (Optional.of(tGsGlSubGroup).isPresent()) {
			tGsGlSubGroup = gsService.updateGlSubGroup(gLSubGroup);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tGsGlSubGroup, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get_cost_center/{parentorgid}/{userid}")
	public ResponseEntity<List<CostCenter>> getCostCenterData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<CostCenter> costcenter = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			costcenter = gsService.getCostCenterData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(costcenter, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get_profit_center/{parentorgid}/{userid}")
	public ResponseEntity<List<ProfitCenter>> getProfitCenterData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<ProfitCenter> profitcenter = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			profitcenter = gsService.getProfitCenterData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(profitcenter, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get_income_glhead/{parentorgid}/{userid}")
	public ResponseEntity<List<IncomeGLHead>> getIncomeGlHead(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<IncomeGLHead> incomeGLHead = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			incomeGLHead = gsService.getIncomeGlHead(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(incomeGLHead, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/expenditureglhead/{parentorgid}/{userid}")
	public ResponseEntity<List<ExpenditureGLHead>> getExpenditureGlHead(
			@PathVariable(name = "parentorgid") int parentOrgId, @PathVariable(name = "userid") int userId)
			throws GlobalSettingsServiceException {
		List<ExpenditureGLHead> expenditureGLHead = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			expenditureGLHead = gsService.getExpenditureGlHead(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(expenditureGLHead, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/get/assetnature/{parentorgid}/{userid}")
	public ResponseEntity<List<AssetNature>> getAssetNatureData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<AssetNature> assetNatureList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			assetNatureList = gsService.getAssetNatureData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assetNatureList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/assetgroup/{parentorgid}/{userid}")
	public ResponseEntity<List<AssetGroup>> getAssetGroupData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<AssetGroup> assetGroupList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			assetGroupList = gsService.getAssetGroupData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assetGroupList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/liabilityGLHead/{parentorgid}/{userid}")
	public ResponseEntity<List<LiabilityGLHead>> getLiabilityGLHeadData(
			@PathVariable(name = "parentorgid") int parentOrgId, @PathVariable(name = "userid") int userId)
			throws GlobalSettingsServiceException {
		List<LiabilityGLHead> liabilityGLHeadList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			liabilityGLHeadList = gsService.getLiabilityGLHeadData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(liabilityGLHeadList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/liabilityGLSubHead/{parentorgid}/{userid}")
	public ResponseEntity<List<LiabilityGLSubHead>> getLiabilityGLSubHeadData(
			@PathVariable(name = "parentorgid") int parentOrgId, @PathVariable(name = "userid") int userId)
			throws GlobalSettingsServiceException {
		List<LiabilityGLSubHead> liabilityGLSubHeadList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			liabilityGLSubHeadList = gsService.getLiabilityGLSubHeadData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(liabilityGLSubHeadList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/glgroup/{parentorgid}/{userid}")
	public ResponseEntity<List<GLGroup>> getGLGroupData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<GLGroup> gLGroupList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			gLGroupList = gsService.getGLGroupData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gLGroupList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/glsubgroup/{parentorgid}/{userid}")
	public ResponseEntity<List<GLSubGroup>> getGLSubGroupData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<GLSubGroup> gLSubGroupList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			gLSubGroupList = gsService.getGLSubGroupData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gLSubGroupList, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping(value = "/get/glaccount/{parentorgid}/{userid}")
	public ResponseEntity<List<GLAccount>> getGLAccountData(@PathVariable(name = "parentorgid") int parentOrgId,
			@PathVariable(name = "userid") int userId) throws GlobalSettingsServiceException {
		List<GLAccount> gLAccountList = null;
		if (Optional.of(userId).isPresent() || Optional.of(parentOrgId).isPresent()) {
			gLAccountList = gsService.getGLAccountData(userId, parentOrgId);
		} else {
			throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(gLAccountList, HttpStatus.OK);

	}

}
