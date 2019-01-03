package com.ceiba.parqueadero.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parqueadero.models.dao.IRegistroDao;
import com.ceiba.parqueadero.models.entity.Registro;
import com.ceiba.parqueadero.util.RespuestaJson;

@Service
public class RegistroServiceImpl implements IRegistroService{

	@Autowired
	private IRegistroDao registroDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Registro> findAll() {
		return (List<Registro>) registroDao.findAll();
	}


}
