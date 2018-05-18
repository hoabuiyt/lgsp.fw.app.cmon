package vn.lgsp.fw.app.cmon.domain.service;

import org.springframework.stereotype.Service;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

@Service
public class BaseServiceImpl<T extends BaseEntity<T>> implements BaseService<T>{

}
