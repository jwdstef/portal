package com.mscncn.firstsystem.menu.model;

import java.io.Serializable;

public class Attribute implements Serializable {

	private static final long serialVersionUID = -8344300145672112033L;
	/**
	 * 属性url
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Attribute [url=" + url + "]";
	}

}
