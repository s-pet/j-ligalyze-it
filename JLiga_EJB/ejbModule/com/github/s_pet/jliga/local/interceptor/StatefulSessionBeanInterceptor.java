package com.github.s_pet.jliga.local.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
public class StatefulSessionBeanInterceptor {
	
	public StatefulSessionBeanInterceptor() {
	}
	
	@AroundInvoke
	public Object onMethodCall(InvocationContext ctx) throws Exception {
		
		long startTime = System.currentTimeMillis();
		try {
			return ctx.proceed();
		} finally {
			long duration = System.currentTimeMillis() - startTime;
			if (duration > 10) {
			System.out.printf("Attention: %s took %d ms.\n", ctx.getMethod(), duration);
			}
		}
	}
	
	@PostConstruct
	public void onPostConstruct(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " constructed.");
		ctx.proceed();
	}
	
	@PreDestroy
	public void onPreDestroy(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " destroyed.");
		ctx.proceed();
	}
	
	@PostActivate
	public void onPostActivate(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " activated.");
		ctx.proceed();
	}
	
	@PrePassivate
	public void onPrePassivate(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " passivated.");
		ctx.proceed();
	}
}
