package com.mgz.nongzitong.controller;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.CountryRequest;
import com.mgz.nongzitong.response.CityResponse;
import com.mgz.nongzitong.response.CountryReponse;
import com.mgz.nongzitong.service.AddressService;

@Controller
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/requireCity")
	public @ResponseBody CityResponse getCityResult(@RequestBody String request) {
		CityResponse cityResponse = new CityResponse();
		cityResponse.setCitys(addressService.findCity());
		if (cityResponse.getCitys().size() > 0) {
			cityResponse.setSuccess(1);
		} else {
			cityResponse.setSuccess(-1);
		}
		return cityResponse;
	}
	
	@RequestMapping("/requireCountry")
	public @ResponseBody CountryReponse getCountryResult (@RequestBody String request) {
		CountryReponse countryReponse = new CountryReponse();
		CountryRequest countryRequest = JSON.parseObject(request, CountryRequest.class);
		countryReponse.setCountrys(addressService.findCountry(countryRequest.getCity()));
		if (countryReponse.getCountrys().size() > 0) {
			countryReponse.setSuccess(1);
		} else {
			countryReponse.setSuccess(-1);
		}
		return countryReponse;
	}

}
