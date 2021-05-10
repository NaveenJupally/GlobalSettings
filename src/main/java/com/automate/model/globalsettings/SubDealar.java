package com.automate.model.globalsettings;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubDealar {

	private String subDealerName;
	private String subDealerCode;
	private AddressModel subDealerAddress;
}
