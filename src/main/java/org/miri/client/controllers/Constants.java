package org.miri.client.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.miri.beans.Coupon;

public class Constants {
	private static String COUPON_INSTANCE_PATH = "/coupon/";
	private static String BASE_PATH = "/";
	
	static URI buildUri(String path) {
		try {
			URI uri = new URI(path);
			return uri;
		}catch(URISyntaxException uriException) {
			return null;
		}
	}
	
	static URI buildCouponUri(Coupon coupon) {
		return buildUri(COUPON_INSTANCE_PATH + coupon.getId());
	}
	
	static URI buildBaseUri() {
		return buildUri(BASE_PATH);
	}
	
}
