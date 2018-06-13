package vn.lgsp.fw.app.cmon.web.vm;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.querydsl.jpa.impl.JPAQuery;


public class LoginVM {

	@Command
	public void login(@BindingParam("email") final String email, @BindingParam("password") final String password) {
		System.out.println("email:"+email);
		System.out.println("password:"+password);
		/*NhanVien nhanVien = new JPAQuery<NhanVien>(em()).from(QNhanVien.nhanVien)
				.where(QNhanVien.nhanVien.daXoa.isFalse()).where(QNhanVien.nhanVien.trangThai.ne(core().TT_DA_XOA))
				.where(QNhanVien.nhanVien.tenDangNhap.eq(email))
				.where(QNhanVien.nhanVien.vaiTros.any().alias.ne("donvi")
						.and(QNhanVien.nhanVien.vaiTros.any().alias.ne("daibieu")))
				.fetchFirst();
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		if (nhanVien != null
				&& encryptor.checkPassword(password.trim() + nhanVien.getSalkey(), nhanVien.getMatKhau())) {
			//System.out.println("nhan vien != null");
			String cookieToken = nhanVien
					.getCookieToken(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
			Session zkSession = Sessions.getCurrent();
			zkSession.setAttribute("email", cookieToken);
			HttpServletResponse res = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
			Cookie cookie = new Cookie("email", cookieToken);
			cookie.setPath("/");
			cookie.setMaxAge(1000000000);
			res.addCookie(cookie);
			Executions.sendRedirect("/cp");
		} else {
			showNotification("Đăng nhập không thành công", "", "error");

		}*/
	}
}
