package com.github.s_pet.jliga.entity.listener;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Interceptor
public class JLigaEntityListener {
	
	public JLigaEntityListener() {
	}

	@AroundInvoke
	public Object onMethodCall(InvocationContext ctx) throws Exception {
		
		long startTime = System.currentTimeMillis();
		try {
			return ctx.proceed();
		} finally {
			long duration = System.currentTimeMillis() - startTime;
			if (duration > 100) {
			System.out.printf("Attention: %s took %d ms.\n", ctx.getMethod(), duration);
			}
		}
	}
	
	@PrePersist
	public void onPrePersist(Object entity) {
		System.out.println("Trying to persist " + entity);
	}
	
	@PostPersist
	public void onPostPersist(Object entity) {
		System.out.println("Persited " + entity);
	}
	
	@PreUpdate
	public void onPreUpdate(Object entity) {
		System.out.println("Trying to update " + entity);
	}
	
	@PostUpdate
	public void onPostUpdate(Object entity) {
		System.out.println("Updated " + entity);
	}
	
	@PreRemove
	public void onPreRemove(Object entity) {
		System.out.println("Trying to remove " + entity);
	}
	
	@PostRemove
	public void onPostRemove(Object entity) {
		System.out.println("Removed " + entity);
	}
	
	@PostLoad
	public void onPostLoad(Object entity) {
		System.out.println("Loaded " + entity);
	}

}
