package com.automate.service;

import org.springframework.web.multipart.MultipartFile;

import com.automate.exception.GlobalSettingsServiceException;

public interface GenericService {
	
	public String uploadAttachment(MultipartFile file) throws GlobalSettingsServiceException;

}
