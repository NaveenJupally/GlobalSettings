package com.automate.service.impl;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.automate.constants.GsAppConstants;
import com.automate.exception.GlobalSettingsServiceException;
import com.automate.service.GenericService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author srujan
 *
 */
@Service
@Slf4j
public class GenericServiceImpl implements GenericService{
	
	@Autowired
	Environment env;
	
	@Value("${app.upload.attachment.dir}")
	String attachmentUploadDir;
	
	@Value("${app.upload.attachment.size}")
	Long uploadSize;
	
	@Value("${app.upload.allowed.filetypes}")
	String uploadTypes;
	
	
	@Override
	public String uploadAttachment(MultipartFile file) throws GlobalSettingsServiceException {
		Path copyLocation=null;
		try {
			if(null!=file && file.getSize()>uploadSize) {
				throw new GlobalSettingsServiceException(env.getProperty("FILE_EXCEEDS_GIVEN_SIZE"), HttpStatus.BAD_REQUEST);
			}
			if(!validateFileType(file.getOriginalFilename())) {
				throw new GlobalSettingsServiceException(env.getProperty("INVALID_FILE_FORMAT"), HttpStatus.BAD_REQUEST);
			}
			
			
            copyLocation = Paths.get(attachmentUploadDir + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation);
        } catch (IOException e) {
            log.error("Exception in GenericServiceImpl:upload() ",e);
            throw new GlobalSettingsServiceException("Could not store file " + file.getOriginalFilename()
            + ". Please try again!",HttpStatus.INTERNAL_SERVER_ERROR);
           
        }
		return null!=copyLocation?copyLocation.toString():GsAppConstants.FILE_UPLOAD_ISSUE;
	}

	private boolean validateFileType(String originalFilename) {
	
		
		if(null!=uploadTypes) {
			return Arrays.asList(uploadTypes.split(GsAppConstants.COMMA_SEPERATOR)).stream().anyMatch(x->originalFilename.contains(x));
		}
		return false;
		
	}

	
}
