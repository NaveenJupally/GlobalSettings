package com.automate.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.automate.entity.globalsettings.GsGLAccount;
import com.automate.entity.globalsettings.GsTaxAccountNature;
import com.automate.entity.globalsettings.GsTaxSection;
import com.automate.entity.globalsettings.GsVendor;
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
import com.automate.entity.globalsettings.TGsTaxAccount;
import com.automate.entity.globalsettings.TGsTaxGroup;
import com.automate.entity.globalsettings.TGsTaxMapping;
import com.automate.model.globalsettings.AssetGroup;
import com.automate.model.globalsettings.AssetNature;
import com.automate.model.globalsettings.CostCenter;
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

@Component
public interface GsUtil {
	
	public GsVendor buildVendorMaster(Vendor vendor);

	public TGsTaxGroup buildTaxGroup(TaxGroup taxGroup);

	public TGsTaxAccount buildTaxAccount(TaxAccount taxAccount);

	public GsTaxAccountNature buildTaxAccountNature(TaxAccountNature taxAccountNature);

	public GsTaxSection buildTaxSection(TaxSection taxSection);

	public TGsTaxMapping buildTaxMapping(TaxMapping taxMapping);

	public GsGLAccount buildGlAccount(GLAccount glAccount);

	public String convertDateToString(Date date);

	public java.util.Date covertStringToDate(String date) throws ParseException;

	public Timestamp getCurrentTmeStamp();

	public boolean isValidUserAndParenetOrg(int userId, int parentOrgId);




	public TGsCostCenter buildCostCenter(CostCenter costCenter);

	public TGsProfitCenter buildProfitCenter(ProfitCenter profitCenter);

	public TGsIncomeGlHead buildIncomeGlHead(IncomeGLHead incomeGLHead);

	public TGsExpenditureGlHead buildExpenditureglhead(ExpenditureGLHead expenditureglhead);

	public TGsAssetNature buildAssetNature(AssetNature assetNature);
	
	public TGsAssetGroup buildAssetGroup(AssetGroup assetGroup);
	
	public TGsLiabilityGlHead buildLiabilityGlHead(LiabilityGLHead liabilityGlHead);
	
	public TGsLiabilityGlSubhead buildLiabilityGlSubhead(LiabilityGLSubHead liabilityGlSubhead);
	
	public TGsGlGroup buildGlGroup(GLGroup glGroup);
	
	public TGsGlSubGroup buildGlSubGroup(GLSubGroup glSubGroup);
	
	public TGsGlAccount buildGlSubGroup(GLAccount glAccount);


}
