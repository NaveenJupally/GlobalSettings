package com.automate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.automate.exception.GlobalSettingsServiceException;
import com.automate.service.GenericService;
import com.google.common.base.Optional;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value="/common")
public class GenericController {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	Environment env;
	
	@CrossOrigin
	@PostMapping(value="/uploadAttachment")
	public ResponseEntity<String> upload(MultipartFile file) throws GlobalSettingsServiceException{
		String response = null;
		if(Optional.of(file).isPresent()) {
			response = genericService.uploadAttachment(file);
		}
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
		
	}


}
