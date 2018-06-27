package vn.lgsp.fw.cmon.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.lgsp.fw.cmon.domain.repository.nguoidung.NguoiDungRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	NguoiDungRepository nguoiDungRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//NguoiDung user = nguoiDungRepository.
		return null;
	}

}
