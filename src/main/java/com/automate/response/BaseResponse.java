package com.automate.response;

import com.automate.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sindh
 *
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	private Status status;
	private int responseCode;

}
