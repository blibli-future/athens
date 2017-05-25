package com.blibli.future.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.blibli.future.service.api.ConverterService;

public class ConverterServiceImpl implements ConverterService {
	  @Autowired
	  private Mapper mapper;
	  
	  @Override
	  public <S, T> T map(S source, Class<T> destinationClass) {
	    return source == null ? null : this.mapper.map(source, destinationClass);
	  }
}
