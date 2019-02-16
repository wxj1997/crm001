package cn.itcast.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	// 不检验登陆和注册方法
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 1获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 2获得登陆标识
		User user = (User) session.get("user");
		// 3判断标识是否存在
		if (user != null) {
			// 存在 放行
			return invocation.invoke();
		} else {
			// 不存在 重定向到登陆页面
			return "toLogin";
		}

	}

}
