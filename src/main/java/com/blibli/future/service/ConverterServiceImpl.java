package com.blibli.future.service;

import com.blibli.future.service.api.ConverterService;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {
	private Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

	@Override
	  public <S, T> T map(S source, Class<T> destinationClass) {
	    return source == null ? null : this.mapper.map(source, destinationClass);
	  }
}
