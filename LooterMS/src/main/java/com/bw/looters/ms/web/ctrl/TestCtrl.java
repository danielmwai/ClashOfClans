package com.bw.looters.ms.web.ctrl;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.looters.ms.web.vo.Res;
import com.bw.looters.ms.web.vo.ResUtil;

@Controller
@RequestMapping("/test")
public class TestCtrl {

	@RequestMapping("/")
	public String test() {
		return "test";
	}

	@ResponseBody
	@RequestMapping("/jsonBody")
	public Res testJsonBody() {
		return ResUtil.SUCCESS;
	}

	public static void main(String[] args)
			throws TimeoutException, InterruptedException, MemcachedException, BeansException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:conf/*.xml");
		MemcachedClient client = (MemcachedClient) ctx.getBean("cache"); // getBean("memcachedClient");
		client.set("a", 10000, "hello");
		System.out.println(client.get("a"));
	}
}
