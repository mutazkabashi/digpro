package com.digpro.codetest.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.exception.CodeTestException;
import com.digpro.codetest.backend.repository.BaseRepository;

/**
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
@Service
public class CoordinateServiceImpl implements CoordinateService {
	
    private static final Logger LOG = LoggerFactory.getLogger(CoordinateServiceImpl.class);

	private BaseRepository repository; 

	@Autowired
	public CoordinateServiceImpl(BaseRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public List<Coordinate> getNewCoordiantes()  throws CodeTestException{
		List<Coordinate> Coordinates = new ArrayList<Coordinate>();
		try {
			LOG.info("retriving new coordinates using coordinate repository");
			Coordinates=repository.getAll();
			LOG.info("new coordinates hasbeen retived from  coordinate repository");
			return Coordinates;
		}
		catch (Exception e) {
			//this varaible has been added in case Exception message is empty
			String errorMessage ="";
			LOG.error("An exception occured while trying to get coordinates from coordiante Repsotiory",e);
			//TDOD error message should be in config file (Applications.properties)
			errorMessage = (e.getMessage()!=null && !e.getMessage().isEmpty())?e.getMessage():"Error Occured while trying to get Coordiantes data, check log file";
			throw new CodeTestException(errorMessage,e.getCause());
		}
		
	}

}
