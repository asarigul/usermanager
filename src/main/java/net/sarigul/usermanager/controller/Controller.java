package net.sarigul.usermanager.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.mongodb.MongoException;

public interface Controller {
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalArgumentException, UnknownHostException, MongoException, IOException;
}
