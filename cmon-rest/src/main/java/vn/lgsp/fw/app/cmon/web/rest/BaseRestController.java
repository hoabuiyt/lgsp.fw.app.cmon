package vn.lgsp.fw.app.cmon.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ResourceProcessor;

import vn.lgsp.fw.core.BaseRepository;

public class BaseRestController<T> implements ResourceProcessor<RepositorySearchesResource>{

	protected BaseRepository<T, ?> repository;
	
	public BaseRestController(BaseRepository<T, ?> repo) {
		repository = repo;
	}
	
	@Autowired
	protected PagedResourcesAssembler<T> pageAssembler;
	
	/*@Autowired
	public EntityManager em;

	@Autowired
	public PlatformTransactionManager transactionManager;

	@Autowired
	public TransactionTemplate transactioner;
	
	public EntityManager getEm() {
		return em;
	}
	
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	
	public TransactionTemplate getTransactioner() {
		return transactioner;
	}*/
	
	@Override
	public RepositorySearchesResource process(RepositorySearchesResource resource) {
		return resource;
	}

}
