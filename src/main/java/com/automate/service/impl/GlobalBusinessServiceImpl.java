package com.automate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import com.automate.dao.AddressRepo;
import com.automate.dao.BusinessUnitRepo;
import com.automate.dao.OrganaizationRepo;
import com.automate.dao.TgsUserRepo;
import com.automate.dao.VerticalRepo;
import com.automate.entity.globalsettings.Address;
import com.automate.entity.globalsettings.TGsBusinessUnit;
import com.automate.entity.globalsettings.TGsContactDetail;
import com.automate.entity.globalsettings.TGsOrganaization;
import com.automate.entity.globalsettings.TGsSubDealer;
import com.automate.entity.globalsettings.TGsUser;
import com.automate.entity.globalsettings.TGsVertical;
import com.automate.enums.Status;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.model.globalsettings.AddressModel;
import com.automate.model.globalsettings.BusinessUnit;
import com.automate.model.globalsettings.CommunicationAddr;
import com.automate.model.globalsettings.ContactDetails;
import com.automate.model.globalsettings.CorrespondenceAddr;
import com.automate.model.globalsettings.GlobalOrg;
import com.automate.model.globalsettings.SubDealar;
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
import com.automate.service.GlobalBusinessService;
import com.automate.util.GsUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GlobalBusinessServiceImpl implements GlobalBusinessService {

	@Autowired
	private ModelMapper modelMapper;

	@Resource
	BusinessUnitRepo businessUnityRepo;

	@Resource
	VerticalRepo verticalRepo;

	@Resource
	OrganaizationRepo organaizationRepo;
	
	@Autowired
	TgsUserRepo tgsUserRepo;
	
	@Autowired
	GsUtil gsUtil;

	@Autowired
	Environment env;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.automate.globalsettings.service.GlobalBusinessService#GetBusinessUnit()
	 */
	@Override
	public GetBusinessUnitResponse GetBusinessUnit(GetBusinessUnitRequest request) {

		GetBusinessUnitResponse response = new GetBusinessUnitResponse();

		List<BusinessUnit> businessUnitList = null;
		BusinessUnit businessUnit = null;

		List<TGsBusinessUnit> businessUnitEntityList;

		try {
			// Need to conform with Srinivas, Need to fetch whole db records or any
			// condition
			// base.
			businessUnitEntityList = businessUnityRepo.findAll();

			if (businessUnitEntityList != null && !businessUnitEntityList.isEmpty()) {
				businessUnitList = new ArrayList<BusinessUnit>();
				for (TGsBusinessUnit tGsBusinessUnit : businessUnitEntityList) {
					businessUnit = new BusinessUnit();
					businessUnit.setBusinessUnitName(tGsBusinessUnit.getBusinessUnitName());
					businessUnit.setBusinessUnitDesc(tGsBusinessUnit.getBusinessUnitDesc());
					businessUnit.setBusinessUnitLogo(tGsBusinessUnit.getBusinessUnitLogo());
					businessUnit.setOemType(tGsBusinessUnit.getOemType());
					// businessUnit.setParentDealerCompany(tGsBusinessUnit.getParentDealerCompany());
					//businessUnit.setDealerCode(tGsBusinessUnit.getDealerCode());
					/*
					 * businessUnit.setBuCommunicationAddress(tGsBusinessUnit.
					 * getBUCommunicationAddress());
					 * businessUnit.setBuCorrespondenceAddress(tGsBusinessUnit.
					 * getBUCorrespondenceAddress());
					 * businessUnit.setPdCity(tGsBusinessUnit.getPDCity());
					 * businessUnit.setPdState(tGsBusinessUnit.getPDState()); //
					 * businessUnit.setPdMandal(tGsBusinessUnit.getpDMandal()); //
					 * businessUnit.setPdLocation(tGsBusinessUnit.getpDLocation());
					 * businessUnit.setPdPincode(tGsBusinessUnit.getPDPincode());
					 * businessUnit.setPdCountry(tGsBusinessUnit.getPDPincode());
					 * businessUnit.setSubDealerName(tGsBusinessUnit.getSubDealerName());
					 * businessUnit.setSubDealerCode(tGsBusinessUnit.getSubDealerCode());
					 * businessUnit.setSdCommunicationAddress(tGsBusinessUnit.
					 * getSDCommunicationAddress());
					 * businessUnit.setSdCorrespondenceAddress(tGsBusinessUnit.
					 * getSDCorrespondenceAddress());
					 * businessUnit.setSdCity(tGsBusinessUnit.getSDCity());
					 * businessUnit.setSdState(tGsBusinessUnit.getSDState());
					 * businessUnit.setSdPincode(tGsBusinessUnit.getSDPincode());
					 * businessUnit.setSdCountry(tGsBusinessUnit.getSDCountry());
					 * businessUnit.setContactNo(tGsBusinessUnit.getContactNo());
					 * businessUnit.setPan(tGsBusinessUnit.getPan());
					 * businessUnit.setGst(tGsBusinessUnit.getGst());
					 * businessUnit.setPrimaryContactPerson(tGsBusinessUnit.getPrimaryContactPerson(
					 * ));
					 * businessUnit.setPrimaryContactNumber(tGsBusinessUnit.getPrimaryContactNo());
					 * businessUnit.setPrimaryContactEmailId(tGsBusinessUnit.getPrimaryContactEmail(
					 * )); businessUnit.setSecondaryContactPerson1(tGsBusinessUnit.
					 * getSecondaryContactPerson1());
					 * businessUnit.setSecondaryContactNumber(tGsBusinessUnit.getSecondaryContactNo(
					 * )); businessUnit.setSecondaryContactEmailId(tGsBusinessUnit.
					 * getSecondaryContactEmail());
					 * businessUnit.setSdCommunicationAddress(tGsBusinessUnit.
					 * getSDCommunicationAddress());
					 * businessUnit.setSdCorrespondenceAddress(tGsBusinessUnit.
					 * getSDCorrespondenceAddress());
					 */
					businessUnit.setWebsiteUrl(tGsBusinessUnit.getWebsiteUrl());
					businessUnit.setGoogleLocation(tGsBusinessUnit.getGoogleLocation());

					businessUnitList.add(businessUnit);
				}
				response.setBusinessUnits(businessUnitList);
				response.setStatus(Status.SUCCESS);
				response.setResponseCode(Responsecode.SUCCESS);

			} else {
				response.setStatus(Status.FAILED);
				response.setResponseCode(Responsecode.NO_RECORDS_FOUND);
			}

		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to get business unit info ", e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.automate.globalsettings.service.GlobalBusinessService#savebusinessunit(
	 * com.automate.globalsettings.Request.SaveBusinessUnitRequest)
	 */
	@Override
	public SaveBusinessUnitResponse savebusinessunit(SaveBusinessUnitRequest request) {

		SaveBusinessUnitResponse response = new SaveBusinessUnitResponse();
		List<TGsBusinessUnit> businessEntityList = null;
		TGsBusinessUnit businessUnityEntity = null;

		List<BusinessUnit> businessUnits = null;

		try {
			businessUnits = request.getBusinessUnits();
			if (businessUnits != null && !businessUnits.isEmpty()) {
				businessEntityList = new ArrayList<>();
				for (BusinessUnit businessUnit : businessUnits) {
					businessUnityEntity = new TGsBusinessUnit();
					businessUnityEntity.setBusinessUnitName(businessUnit.getBusinessUnitName());
					businessUnityEntity.setBusinessUnitDesc(businessUnit.getBusinessUnitDesc());
					businessUnityEntity.setBusinessUnitLogo(businessUnit.getBusinessUnitLogo());
					businessUnityEntity.setOemType(businessUnit.getOemType());
					// businessUnityEntity.setParentDealerCompany(businessUnit.getParentDealerCompany());
					/*
					 * businessUnityEntity.setDealerCode(businessUnit.getDealerCode());
					 * businessUnityEntity.setPDCommunicationAddress(businessUnit.
					 * getPdCommunicationAddress());
					 * businessUnityEntity.setPDCorrespondenceAddress(businessUnit.
					 * getPdCorrespondenceAddress());
					 * businessUnityEntity.setPDCity(businessUnit.getPdCity());
					 * businessUnityEntity.setPDState(businessUnit.getPdState()); //
					 * businessUnityEntity.setpDMandal(businessUnit.getPdMandal()); //
					 * businessUnityEntity.setpDLocation(businessUnit.getPdLocation());
					 * businessUnityEntity.setPDPincode(businessUnit.getPdPincode());
					 * businessUnityEntity.setPDCountry(businessUnit.getPdCountry());
					 * businessUnityEntity.setSubDealerName(businessUnit.getSubDealerName());
					 * businessUnityEntity.setSubDealerCode(businessUnit.getSubDealerCode());
					 * businessUnityEntity.setSDCommunicationAddress(businessUnit.
					 * getSdCommunicationAddress());
					 * businessUnityEntity.setSDCorrespondenceAddress(businessUnit.
					 * getSdCorrespondenceAddress());
					 * businessUnityEntity.setSDCity(businessUnit.getSdCity());
					 * businessUnityEntity.setSDState(businessUnit.getSdState());
					 * businessUnityEntity.setSDPincode(businessUnit.getSdPincode());
					 * businessUnityEntity.setSDCountry(businessUnit.getSdCountry());
					 * businessUnityEntity.setContactNo(businessUnit.getContactNo());
					 * businessUnityEntity.setPan(businessUnit.getPan());
					 * businessUnityEntity.setGst(businessUnit.getGst());
					 * businessUnityEntity.setPrimaryContactPerson(businessUnit.
					 * getPrimaryContactPerson());
					 * businessUnityEntity.setPrimaryContactNo(businessUnit.getPrimaryContactNumber(
					 * )); businessUnityEntity.setPrimaryContactEmail(businessUnit.
					 * getPrimaryContactEmailId());
					 * businessUnityEntity.setSecondaryContactPerson1(businessUnit.
					 * getSecondaryContactPerson1());
					 * businessUnityEntity.setSecondaryContactNo(businessUnit.
					 * getSecondaryContactNumber());
					 * businessUnityEntity.setSecondaryContactEmail(businessUnit.
					 * getSecondaryContactEmailId());
					 * businessUnityEntity.setSecondaryContactPerson2(businessUnit.
					 * getSecondaryContactPerson2());
					 * businessUnityEntity.setSecondaryContactNo2(businessUnit.
					 * getSecondaryContactNumber2());
					 * businessUnityEntity.setSecondaryContactEmail2(businessUnit.
					 * getSecondaryContactEmailId2());
					 * businessUnityEntity.setBUCommunicationAddress(businessUnit.
					 * getBuCommunicationAddress());
					 * businessUnityEntity.setBUCorrespondenceAddress(businessUnit.
					 * getBuCorrespondenceAddress());
					 */
					businessUnityEntity.setWebsiteUrl(businessUnit.getWebsiteUrl());
					businessUnityEntity.setGoogleLocation(businessUnit.getGoogleLocation());
					businessEntityList.add(businessUnityEntity);
				}
				businessUnityRepo.saveAll(businessEntityList);
			}
		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to save business unit info ", e);
		}
		return response;
	}

	@Override
	public DeleteBusinessUnitResponse deletBusinessUnit(int id) {
		DeleteBusinessUnitResponse response = new DeleteBusinessUnitResponse();

		TGsBusinessUnit businessUnit = null;
		try {
			businessUnit = businessUnityRepo.findByGsBusinessUnitId(id);
			if (businessUnit != null) {
				businessUnityRepo.delete(businessUnit);
				response.setStatus(Status.SUCCESS);
				response.setResponseCode(Responsecode.SUCCESS);
			} else {
				response.setStatus(Status.FAILED);
				response.setResponseCode(Responsecode.INVALID_ID);
			}

		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to delete business unit info ", e);
		}
		return response;
	}

	@Override
	public UpdateBusinessUnitResponse updatBusinessUnit(UpdateBusinessUnitRequest request) {
		UpdateBusinessUnitResponse response = new UpdateBusinessUnitResponse();

		BusinessUnit businessUnit = null;
		TGsBusinessUnit businessUnitEntity = null;
		try {
			businessUnit = request.getBusinessUnit();
			if (businessUnit != null) {
				businessUnitEntity = new TGsBusinessUnit();
				businessUnitEntity.setBusinessUnitName(businessUnit.getBusinessUnitName());
				businessUnitEntity.setBusinessUnitDesc(businessUnit.getBusinessUnitDesc());
				businessUnitEntity.setBusinessUnitLogo(businessUnit.getBusinessUnitLogo());
				businessUnitEntity.setOemType(businessUnit.getOemType());
				// businessUnitEntity.setParentDealerCompany(businessUnit.getParentDealerCompany());
				//businessUnitEntity.setDealerCode(businessUnit.getDealerCode());
				/*
				 * businessUnitEntity.setPDCommunicationAddress(businessUnit.
				 * getPdCommunicationAddress());
				 * businessUnitEntity.setPDCorrespondenceAddress(businessUnit.
				 * getPdCorrespondenceAddress());
				 * businessUnitEntity.setPDCity(businessUnit.getPdCity());
				 * businessUnitEntity.setPDState(businessUnit.getPdState()); //
				 * businessUnitEntity.setpDMandal(businessUnit.getPdMandal()); //
				 * businessUnitEntity.setpDLocation(businessUnit.getPdLocation());
				 * businessUnitEntity.setPDPincode(businessUnit.getPdPincode());
				 * businessUnitEntity.setPDCountry(businessUnit.getPdCountry());
				 * businessUnitEntity.setSubDealerName(businessUnit.getSubDealerName());
				 * businessUnitEntity.setSubDealerCode(businessUnit.getSubDealerCode());
				 * businessUnitEntity.setSDCommunicationAddress(businessUnit.
				 * getSdCommunicationAddress());
				 * businessUnitEntity.setSDCorrespondenceAddress(businessUnit.
				 * getSdCorrespondenceAddress());
				 * businessUnitEntity.setSDCity(businessUnit.getSdCity());
				 * businessUnitEntity.setSDState(businessUnit.getSdState());
				 * businessUnitEntity.setSDPincode(businessUnit.getSdPincode());
				 * businessUnitEntity.setSDCountry(businessUnit.getSdCountry());
				 * businessUnitEntity.setContactNo(businessUnit.getContactNo());
				 * businessUnitEntity.setPan(businessUnit.getPan());
				 * businessUnitEntity.setGst(businessUnit.getGst());
				 * businessUnitEntity.setPrimaryContactPerson(businessUnit.
				 * getPrimaryContactPerson());
				 * businessUnitEntity.setPrimaryContactNo(businessUnit.getPrimaryContactNumber()
				 * ); businessUnitEntity.setPrimaryContactEmail(businessUnit.
				 * getPrimaryContactEmailId());
				 * businessUnitEntity.setSecondaryContactPerson1(businessUnit.
				 * getSecondaryContactPerson1());
				 * businessUnitEntity.setSecondaryContactNo(businessUnit.
				 * getSecondaryContactNumber());
				 * businessUnitEntity.setSecondaryContactEmail(businessUnit.
				 * getSecondaryContactEmailId());
				 * businessUnitEntity.setSecondaryContactPerson2(businessUnit.
				 * getSecondaryContactPerson2());
				 * businessUnitEntity.setSecondaryContactNo2(businessUnit.
				 * getSecondaryContactNumber2());
				 * businessUnitEntity.setSecondaryContactEmail2(businessUnit.
				 * getSecondaryContactEmailId2());
				 * businessUnitEntity.setBUCommunicationAddress(businessUnit.
				 * getBuCommunicationAddress());
				 * businessUnitEntity.setBUCorrespondenceAddress(businessUnit.
				 * getBuCorrespondenceAddress());
				 */
				businessUnitEntity.setWebsiteUrl(businessUnit.getWebsiteUrl());
				businessUnitEntity.setGoogleLocation(businessUnit.getGoogleLocation());
				businessUnityRepo.save(businessUnitEntity);
			}

		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to save business unit info ", e);
		}

		return response;
	}

	@Override
	public GetOrganaizationRequest getOrganizationUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrganisationUnit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrganisationUnit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrganisationUnit(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public GetVerticalResponse getVerticals() {
		GetVerticalResponse response = new GetVerticalResponse();

		List<Vertical> verticalList = null;
		Vertical vertical = null;

		List<TGsVertical> verticalEntityList = null;
		try {
			// Need to conform with Srinivas, Need to fetch whole db records or any
			// condition
			// base.
			verticalEntityList = verticalRepo.findAll();

			if (verticalEntityList != null && !verticalEntityList.isEmpty()) {
				verticalList = new ArrayList<Vertical>();
				for (TGsVertical verticalEnyity : verticalEntityList) {
					vertical = new Vertical();
					vertical.setOrgName(verticalEnyity.getOrgName());

					verticalList.add(vertical);
				}
				response.setVerticalInfo(verticalList);
				response.setStatus(Status.SUCCESS);
				response.setResponseCode(Responsecode.SUCCESS);

			} else {
				response.setStatus(Status.FAILED);
				response.setResponseCode(Responsecode.NO_RECORDS_FOUND);
			}

		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to get business unit info ", e);
		}
		return response;

	}

	@Override
	public UpdateVerticalResponse updateVertical(UpdateVerticalRequest request) {
		UpdateVerticalResponse response = new UpdateVerticalResponse();

		Vertical vertical = null;
		TGsVertical verticalEntity = null;
		try {
			vertical = request.getVerticalInfo();
			if (vertical != null) {
				verticalEntity = new TGsVertical();

				verticalRepo.save(verticalEntity);
			}

		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to save business unit info ", e);
		}

		return response;

	}

	@Override
	public SaveVerticalResponse saveVertical(SaveVerticalRequest request) {

		SaveVerticalResponse response = new SaveVerticalResponse();

		List<TGsVertical> verticalEntityList = null;
		TGsVertical verticalEntity = null;

		List<Vertical> verticals = null;

		try {
			verticals = request.getVerticals();
			if (verticals != null && !verticals.isEmpty()) {
				verticalEntityList = new ArrayList<>();
				for (Vertical verticalInfo : verticals) {
					verticalEntity = new TGsVertical();
					verticalEntity.setOrgName(verticalInfo.getOrgName());

					verticalEntityList.add(verticalEntity);
				}
				verticalRepo.saveAll(verticalEntityList);
			}
		} catch (Exception e) {
			response.setStatus(Status.FAILED);
			response.setResponseCode(Responsecode.INTERNAL_SERVER_ERROR);
			log.error("Failed to save business unit info ", e);
		}
		return response;
	}

	@Override
	public void deleteVertical(int id) {
		// TODO Auto-generated method stub

	}
	
	@Autowired
	AddressRepo addressRepo;

	@Override
	public List<GlobalOrg> saveGlobalOrg(List<GlobalOrg> globalOrgs) throws GlobalSettingsServiceException {
		log.debug("GlobalBusinessServiceImpl.saveGlobalOrg()");
		ModelMapper orgModelMapper = new ModelMapper();
		
		List<TGsOrganaization> tGsOrganaizations = new ArrayList<>();
		for (GlobalOrg globalOrg : globalOrgs) {
		
			TGsOrganaization tGsOrganaization = orgModelMapper.map(globalOrg, TGsOrganaization.class);
			if (Objects.nonNull(tGsOrganaization)) {
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper
						.map(globalOrg.getContactDetails(), TGsContactDetail.class);
				tGsOrganaization.setTGsContactDetail(tGsContactDetail);
				//List<Address> addressList = globalOrg.getAddressModels().stream().map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				//addressList = addressRepo.saveAll(addressList);
				tGsOrganaization.setAddresses(buildAddress(globalOrg.getAddressModels()));
				tGsOrganaization.setCreatedAt(gsUtil.getCurrentTmeStamp());
				tGsOrganaization.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsOrganaization.setStatus(GsAppConstants.ACTIVE);
				
				Optional<TGsUser> tgsUserOpt = tgsUserRepo.findById(globalOrg.getUserId());
//				if(tgsUserOpt.isPresent()) {
//					tGsOrganaization.setTGsUser(tgsUserOpt.get());
//				}
			}
			tGsOrganaizations.add(tGsOrganaization);
		}

		try {

			return convertTgsOrgToGlobalorg(organaizationRepo.saveAll(tGsOrganaizations));
		} catch (DataAccessException e) {
			log.error(" eror while deleting the global organization  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private List<GlobalOrg> convertTgsOrgToGlobalorg(List<TGsOrganaization> inputList) {
		List<GlobalOrg> list = new ArrayList<>();
		for(TGsOrganaization org:inputList) {
			GlobalOrg globalOrg = new GlobalOrg();
			globalOrg = modelMapper.map(org, GlobalOrg.class);
			ContactDetails contactDetails = (ContactDetails) this.modelMapper.map(org.getTGsContactDetail(),
					ContactDetails.class);
			globalOrg.setContactDetails(contactDetails);
			//List<AddressModel> addressList = org.getAddresses().stream().map(address -> modelMapper.map(address, AddressModel.class)).collect(Collectors.toList());
			globalOrg.setAddressModels(buildAddressModel(org.getAddresses()));
			list.add(globalOrg);
		}
		
		
		return list;
	}

	@Override
	public GlobalOrg updateGlobalOrg(GlobalOrg globalOrg) throws GlobalSettingsServiceException {
		GlobalOrg globalOrgResponse = null;
		try {
			Optional<TGsOrganaization> tsOrgOpt = organaizationRepo.findById(globalOrg.getOrgId());
			if (tsOrgOpt.isPresent()) {
				TGsOrganaization tGsOrganaization = tsOrgOpt.get();
				
				tGsOrganaization.setOrgDesc(globalOrg.getOrgDesc());
				tGsOrganaization.setOrgLogo(globalOrg.getOrgLogo());
				tGsOrganaization.setOrgName(globalOrg.getOrgName());
				tGsOrganaization.setUpdatedBy(globalOrg.getUpdatedBy());
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper.map(globalOrg.getContactDetails(), TGsContactDetail.class);
				tGsOrganaization.setTGsContactDetail(tGsContactDetail);
				//List<Address> addressList = globalOrg.getAddressModels().stream()
						//.map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				tGsOrganaization.setAddresses(buildAddress(globalOrg.getAddressModels()));
				tGsOrganaization.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsOrganaization.setStatus(GsAppConstants.ACTIVE);
				tGsOrganaization.setParentOrgId(globalOrg.getOrgId());
				System.out.println("tGsOrganaization::"+tGsOrganaization.getOrgName());;
				TGsOrganaization tGsOrganaizationRes = organaizationRepo.save(tGsOrganaization);
				globalOrgResponse = modelMapper.map(tGsOrganaizationRes, GlobalOrg.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (DataAccessException e) {
			log.error(" eror while deleting the global organization  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return globalOrgResponse;
	}
	
	@Override
	public GlobalOrg deleteGlobalOrg(Integer id) throws GlobalSettingsServiceException {
		GlobalOrg globalOrg = null;
		try {
			Optional<TGsOrganaization> tGsOrganaizationOpt = organaizationRepo.findById(id);
			if (tGsOrganaizationOpt.isPresent()) {
				TGsOrganaization org = tGsOrganaizationOpt.get();
				org.setStatus(GsAppConstants.INACTIVE);
				organaizationRepo.save(org);;
			}
		} catch (DataAccessException e) {
			log.error(" eror while deleting the global organization  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return globalOrg;
	}
	@Override
	public GlobalOrg getGlobalOrg(Integer id) throws GlobalSettingsServiceException {
		GlobalOrg globalOrg = null;
		try {
			globalOrg = new GlobalOrg();
			Optional<TGsOrganaization> tGsOrganaizationOpt = organaizationRepo.findById(id);
			if (tGsOrganaizationOpt.isPresent()) {
				TGsOrganaization org = tGsOrganaizationOpt.get();
				
				globalOrg = modelMapper.map(org, GlobalOrg.class);
				ContactDetails contactDetails = (ContactDetails) this.modelMapper.map(org.getTGsContactDetail(),
						ContactDetails.class);
				globalOrg.setContactDetails(contactDetails);
				//List<AddressModel> addressList = org.getAddresses().stream().map(address -> modelMapper.map(address, AddressModel.class)).collect(Collectors.toList());
				globalOrg.setAddressModels(buildAddressModel(org.getAddresses()));

			}else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (DataAccessException e) {
			log.error(" eror while selecting the global organization  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return globalOrg;
	}

	@Override
	public List<TGsOrganaization> getAllGlobalOrg(int pageNo, int pageSize) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<TGsOrganaization> responseList = null;
		try {
			responseList = organaizationRepo.findAll(pageable).toList();
		} catch (DataAccessException e) {
			log.error("Exception occured in getGlAccountData ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public Vertical getVerticle(Integer id) throws GlobalSettingsServiceException {
		Vertical vertical = null;
		log.debug("inside getVerticle,Id is:: "+id);
		try {
			vertical = new Vertical();
			Optional<TGsVertical> tGsVerticalOpt = verticalRepo.findById(id);
			System.out.println("tGsVerticalOpt::"+tGsVerticalOpt.isPresent());
			if (tGsVerticalOpt.isPresent()) {
				TGsVertical tGsVertical = tGsVerticalOpt.get();
				
				vertical = modelMapper.map(tGsVertical, Vertical.class);
				
				System.out.println("tGsVertical.getTGsContactDetail()::"+tGsVertical.getTGsContactDetail().getContactId());
				ContactDetails contactDetails = (ContactDetails) this.modelMapper.map(tGsVertical.getTGsContactDetail(),
						ContactDetails.class);
				vertical.setContactDetails(contactDetails);
				//List<AddressModel> addressList = tGsVertical.getAddresses().stream().map(address -> modelMapper.map(address, AddressModel.class)).collect(Collectors.toList());
				vertical.setAddressModels(buildAddressModel(tGsVertical.getAddresses()));
//)
			}else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (DataAccessException e) {
			log.error(" eror ::  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return vertical;
	}
	@Override
	public List<Vertical> saveVerticle(List<Vertical> verticals) throws GlobalSettingsServiceException {
		log.debug("GlobalBusinessServiceImpl.saveGlobalOrg()");

		List<TGsVertical> tGsVerticals = new ArrayList<>();
		for (Vertical vertical : verticals) {
			TGsVertical tGsVertical = modelMapper.map(vertical, TGsVertical.class);
			if (Objects.nonNull(tGsVertical)) {
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper
						.map(vertical.getContactDetails(), TGsContactDetail.class);
				tGsVertical.setTGsContactDetail(tGsContactDetail);
				//List<Address> addressList = vertical.getAddressModels().stream()
						//.map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				tGsVertical.setAddresses(buildAddress(vertical.getAddressModels()));
				tGsVertical.setStatus(GsAppConstants.ACTIVE);
				tGsVertical.setCreatedAt(gsUtil.getCurrentTmeStamp());
				tGsVertical.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				Optional<TGsOrganaization> orgOpt = organaizationRepo.findById(vertical.getParentOrgId());
				if (orgOpt.isPresent()) {
					tGsVertical.setTGsOrganaization(orgOpt.get());
				}
		
			}
			tGsVerticals.add(tGsVertical);
		}

		try {
			return convetTgsVerticaltoVertical(verticalRepo.saveAll(tGsVerticals));
		} catch (DataAccessException e) {
			log.error(" eror while saving saveVerticle  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private List<Vertical> convetTgsVerticaltoVertical(List<TGsVertical> list) {
		List<Vertical> verticals = new ArrayList<>();
		
		for(TGsVertical tGsVertical : list) {
			Vertical vertical = new Vertical();
			vertical = modelMapper.map(tGsVertical, Vertical.class);
			ContactDetails contactDetails = (ContactDetails) this.modelMapper.map(tGsVertical.getTGsContactDetail(),ContactDetails.class);
			vertical.setContactDetails(contactDetails);
			vertical.setAddressModels(buildAddressModel(tGsVertical.getAddresses()));
			verticals.add(vertical);
		}
		return verticals;
	}

	public Vertical updateVerticle(Vertical vertical) throws GlobalSettingsServiceException {
		log.debug("GlobalBusinessServiceImpl.updateVerticle()");
		Vertical verticalRes = null;
		try {
			Optional<TGsVertical> tsVerticalOpt = verticalRepo.findById(vertical.getVerticalId());
			if (tsVerticalOpt.isPresent()) {
				TGsVertical tGsVertical = tsVerticalOpt.get();
				
				tGsVertical.setOrgDesc(vertical.getOrgDesc());
				tGsVertical.setOrgLogo(vertical.getOrgLogo());
				tGsVertical.setOrgName(vertical.getOrgName());
				tGsVertical.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsVertical.setUpdatedBy(vertical.getUpdatedBy());
				
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper.map(vertical.getContactDetails(), TGsContactDetail.class);
				tGsVertical.setTGsContactDetail(tGsContactDetail);
				//List<Address> addressList = vertical.getAddressModels().stream()
						//.map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				tGsVertical.setAddresses(buildAddress(vertical.getAddressModels()));
				tGsVertical.setStatus(GsAppConstants.ACTIVE);
				verticalRes = modelMapper.map(verticalRepo.save(tGsVertical), Vertical.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (DataAccessException e) {
			log.error(" eror  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return verticalRes;
	}
	

	@Override
	public Vertical deleteVerticle(Integer id) throws GlobalSettingsServiceException {
		Vertical vertical = null;
		try {

			Optional<TGsVertical> tGsVerticalOpt = verticalRepo.findById(id);

			if (tGsVerticalOpt.isPresent()) {
				//vertical = modelMapper.map(tGsVertical.get(), Vertical.class);
				//verticalRepo.deleteById(id);
				TGsVertical tGsVertical = tGsVerticalOpt.get();
				tGsVertical.setStatus(GsAppConstants.INACTIVE);
				verticalRepo.save(tGsVertical);
			}
		} catch (DataAccessException e) {
			log.error(" eror while deleting the global organization  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return vertical;
	}

	@Override
	public List<TGsVertical> getAllVerticles(int pageNo, int pageSize) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<TGsVertical> responseList = null;
		try {
			responseList = verticalRepo.findAll(pageable).toList();
		} catch (DataAccessException e) {
			log.error("Exception occured in getGlAccountData ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public List<TGsBusinessUnit> getAllBusinessUnits(int pageNo, int pageSize) throws GlobalSettingsServiceException {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<TGsBusinessUnit> responseList = null;
		try {
			responseList = businessUnityRepo.findAll(pageable).toList();
		} catch (DataAccessException e) {
			log.error("Exception occured in getGlAccountData ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseList;
	}

	@Override
	public List<BusinessUnit> saveBusinessUnit(List<BusinessUnit> businessUnits)
			throws GlobalSettingsServiceException {
		log.debug("GlobalBusinessServiceImpl.saveBusinessUnit()");

		List<TGsBusinessUnit> tGsBusinessUnits = new ArrayList<>();
		for (BusinessUnit bu : businessUnits) {
			TGsBusinessUnit tGsBusinessUnit = modelMapper.map(bu, TGsBusinessUnit.class);
			if (Objects.nonNull(tGsBusinessUnit)) {
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper.map(bu.getContactDetails(), TGsContactDetail.class);
				tGsBusinessUnit.setTGsContactDetail(tGsContactDetail);
				AddressModel addrModelList = bu.getAddressModels();
				List<TGsSubDealer> subDealerList = convertSubDealerToTGsSubDealar(bu.getSubDealarList());
				//})
				//List<Address> addressList = bu.getAddressModels().stream().map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				tGsBusinessUnit.setAddresses(buildAddress(addrModelList));
				tGsBusinessUnit.setStatus(GsAppConstants.ACTIVE);
				tGsBusinessUnit.setCreatedAt(gsUtil.getCurrentTmeStamp());
				tGsBusinessUnit.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsBusinessUnit.setSubDealerList(subDealerList);
				tGsBusinessUnit.setBuAddresses(buildAddress(bu.getBusinessUnitAddresses()));
				//tGsBusinessUnit.setBuAddresses(buildAddress(bu.getBusinessUnitAddresses());
				Optional<TGsOrganaization> orgOpt = organaizationRepo.findById(bu.getParentOrgId());
				if (orgOpt.isPresent()) {
					tGsBusinessUnit.setTGsOrganaization(orgOpt.get());
				}
				Optional<TGsVertical> verticalOpt = verticalRepo.findById(bu.getGsVerticalId());
				if (verticalOpt.isPresent()) {
					tGsBusinessUnit.setTGsVertical(verticalOpt.get());
				}
			}
			tGsBusinessUnits.add(tGsBusinessUnit);
		}

		try {
			List<TGsBusinessUnit> tgsBuLsit =  businessUnityRepo.saveAll(tGsBusinessUnits);
			List<BusinessUnit> buList = convertTgsbuToBUUnit(tgsBuLsit);
			return buList;
		} catch (DataAccessException e) {
			log.error(" eror while saving saveBusinessUnit  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private List<SubDealar> converTGsDealerToSubDealar(List<TGsSubDealer> subDealarList) {
		List<SubDealar> list = new ArrayList<>();
		for(TGsSubDealer sb: subDealarList) {
			SubDealar subDealer = new SubDealar();
			subDealer.setSubDealerCode(sb.getSubDealerCode());
			subDealer.setSubDealerName(sb.getSubDealerName());
			subDealer.setSubDealerAddress(buildAddressModel(sb.getAddresses()));
			list.add(subDealer);
		}
		return list;
	}

	private List<TGsSubDealer> convertSubDealerToTGsSubDealar(List<SubDealar> subDealarList) {
		List<TGsSubDealer> list = new ArrayList<>();
		for(SubDealar sb: subDealarList) {
			TGsSubDealer tGsSubDealer = new TGsSubDealer();
			tGsSubDealer.setSubDealerCode(sb.getSubDealerCode());
			tGsSubDealer.setSubDealerName(sb.getSubDealerName());
			tGsSubDealer.setAddresses(buildAddress(sb.getSubDealerAddress()));
			list.add(tGsSubDealer);
		}
		return list;
	}

	private List<BusinessUnit> convertTgsbuToBUUnit(List<TGsBusinessUnit> tgsBuLsit) {
		List<BusinessUnit> buList = new ArrayList<>();
		
		for(TGsBusinessUnit tGsBusinessUnit : tgsBuLsit) {
			BusinessUnit bu = modelMapper.map(tGsBusinessUnit, BusinessUnit.class);
			ContactDetails contactDetails = (ContactDetails) this.modelMapper.map(tGsBusinessUnit.getTGsContactDetail(),ContactDetails.class);
			bu.setContactDetails(contactDetails);
			bu.setAddressModels(buildAddressModel(tGsBusinessUnit.getAddresses()));
			buList.add(bu);
		}
		
		return buList;
	}

	private Address buildAddress(AddressModel addressModel) {
		List<Address> addList  = new ArrayList<>();
		//for(AddressModel addressModel :addrModelList) {
			Address address = new Address();
			address.setCity(addressModel.getCity());
			address.setCountry(addressModel.getCountry());
			address.setCorrespondenceAddCheckbox(addressModel.getCorrespondenceAddCheckbox());
			address.setCorrespondenceAddLine1(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine1());
			address.setCorrespondenceAddLine2(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine2());
			address.setCorrespondenceAddLine3(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine3());
			address.setCountry(addressModel.getCountry());
			address.setCreatedAt(gsUtil.getCurrentTmeStamp());
			address.setCreatedBy(addressModel.getCreatedBy());
			address.setLineNo1(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine1());
			address.setLineNo2(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine2());
			address.setLineNo3(addressModel.getCorrespondenceAddress().getCorrespondenceAddLine3());
			address.setLocation(addressModel.getLocation());
			address.setMandal(addressModel.getMandal());
			address.setPincode(addressModel.getPincode());
			address.setState(addressModel.getPincode());
			address.setUpdatedAt(gsUtil.getCurrentTmeStamp());
			address.setUpdatedBy(addressModel.getUpdatedBy());
			addList.add(address);
		//}
		return address;
	}
	
	private AddressModel buildAddressModel(Address address) {
		List<AddressModel> addrModelList  = new ArrayList<>();
		//for(Address address :addrList) {
			AddressModel addressModel = new AddressModel();
			addressModel.setCity(address.getCity());
			addressModel.setCountry(address.getCountry());
			addressModel.setCorrespondenceAddCheckbox(address.getCorrespondenceAddCheckbox());
			addressModel.setCountry(address.getCountry());
			addressModel.setCreatedBy(address.getCreatedBy());
			addressModel.setLocation(address.getLocation());
			addressModel.setMandal(address.getMandal());
			addressModel.setPincode(address.getPincode());
			addressModel.setState(address.getPincode());
			addressModel.setUpdatedBy(address.getUpdatedBy());
			
			CorrespondenceAddr correspondenceAddr = new CorrespondenceAddr();
			correspondenceAddr.setCorrespondenceAddLine1(address.getCorrespondenceAddLine1());
			correspondenceAddr.setCorrespondenceAddLine2(address.getCorrespondenceAddLine2());
			correspondenceAddr.setCorrespondenceAddLine3(address.getCorrespondenceAddLine3());
			addressModel.setCorrespondenceAddCheckbox(address.getCorrespondenceAddCheckbox());
			addressModel.setCorrespondenceAddress(correspondenceAddr);
			
			CommunicationAddr communicationAddr  = new CommunicationAddr();
			communicationAddr.setCommunicationAddLine1(address.getLineNo1());
			communicationAddr.setCommunicationAddLine2(address.getLineNo2());
			communicationAddr.setCommunicationAddLine3(address.getLineNo3());
			addressModel.setCommunicationAddress(communicationAddr);
			;
			//addrModelList.add(addressModel);
		//}
		return addressModel;
	}

	@Override
	public TGsBusinessUnit deleteBusinessUnit(Integer id) throws GlobalSettingsServiceException {
		TGsBusinessUnit tGsBusinessUnitres = null;
		try {

			Optional<TGsBusinessUnit> tGsBusinessUnitOpt = businessUnityRepo.findById(id);

			if (tGsBusinessUnitOpt.isPresent()) {
				TGsBusinessUnit tGsBusinessUnit = tGsBusinessUnitOpt.get();
				tGsBusinessUnit.setStatus(GsAppConstants.INACTIVE);
				
				businessUnityRepo.save(tGsBusinessUnit);
			}
		} catch (DataAccessException e) {
			log.error(" eror while deleting the business unit  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return tGsBusinessUnitres;
	}

	@Override
	public BusinessUnit getBusinessUnit(Integer id) throws GlobalSettingsServiceException {
		BusinessUnit bu = null;
		log.debug("inside getBusinessUnit,Id is:: " + id);
		try {
			bu = new BusinessUnit();
			Optional<TGsBusinessUnit> tGsBUOpt = businessUnityRepo.findById(id);
			if (tGsBUOpt.isPresent()) {
				TGsBusinessUnit tGsBusinessUnit = tGsBUOpt.get();
				bu = modelMapper.map(tGsBusinessUnit, BusinessUnit.class);
				ContactDetails contactDetails = (ContactDetails) this.modelMapper
						.map(tGsBusinessUnit.getTGsContactDetail(), ContactDetails.class);
				bu.setContactDetails(contactDetails);
				// List<AddressModel> addressList =
				// tGsBusinessUnit.getAddresses().stream().map(address ->
				// modelMapper.map(address, AddressModel.class)).collect(Collectors.toList());
				AddressModel addressList = buildAddressModel(tGsBusinessUnit.getAddresses());
				bu.setSubDealarList(converTGsDealerToSubDealar(tGsBusinessUnit.getSubDealerList()));
				;
				bu.setAddressModels(addressList);
				bu.setBusinessUnitAddresses(buildAddressModel(tGsBusinessUnit.getBuAddresses()));
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
			}

		} catch (DataAccessException e) {
			log.error(" exception in getBusinessUnit ::  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return bu;
	}

	@Override
	public BusinessUnit updateBusinessUnit(BusinessUnit businessUnit) throws GlobalSettingsServiceException {
		log.debug("GlobalBusinessServiceImpl.updateBusinessUnit()");
		BusinessUnit bu = null;
		try {
			Optional<TGsBusinessUnit> tsBUOpt = businessUnityRepo.findById(businessUnit.getBusinessUnitId());
			if (tsBUOpt.isPresent()) {
				TGsBusinessUnit tGsBU = tsBUOpt.get();
				
				tGsBU.setBusinessUnitDesc(businessUnit.getBusinessUnitDesc());
				tGsBU.setBusinessUnitName(businessUnit.getBusinessUnitName());
				tGsBU.setBusinessUnitLogo(businessUnit.getBusinessUnitLogo());
				//tGsBU.setDealerCode(businessUnit.getDealerCode());
				tGsBU.setGoogleLocation(businessUnit.getGoogleLocation());
				tGsBU.setOemType(tGsBU.getOemType());
				tGsBU.setStatus(GsAppConstants.ACTIVE);
			
				tGsBU.setWebsiteUrl(businessUnit.getWebsiteUrl());;
				tGsBU.setUpdatedAt(gsUtil.getCurrentTmeStamp());
				tGsBU.setUpdatedBy(businessUnit.getUpdatedBy());
				
				TGsContactDetail tGsContactDetail = (TGsContactDetail) this.modelMapper.map(businessUnit.getContactDetails(), TGsContactDetail.class);
				tGsBU.setTGsContactDetail(tGsContactDetail);
				tGsBU.setSubDealerList(convertSubDealerToTGsSubDealar(businessUnit.getSubDealarList()));
				//List<Address> addressList = businessUnit.getAddressModels().stream()
						//.map(address -> modelMapper.map(address, Address.class)).collect(Collectors.toList());
				tGsBU.setAddresses(buildAddress(businessUnit.getAddressModels()));
				tGsBU.setBuAddresses(buildAddress(businessUnit.getBusinessUnitAddresses()));
				tGsBU.setStatus(GsAppConstants.ACTIVE);
				bu = modelMapper.map(businessUnityRepo.save(tGsBU), BusinessUnit.class);
			} else {
				throw new GlobalSettingsServiceException(env.getProperty("BAD_REQUEST"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (DataAccessException e) {
			log.error(" Exception in updateBusinessUnit  ", e);
			throw new GlobalSettingsServiceException(env.getProperty("INTERNAL_SERVER_ERROR"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bu;
	}

}
