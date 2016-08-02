package com.xyinc.helper;

import java.io.Serializable;

public class PontoInteresseHelper implements Serializable {

	private static final long serialVersionUID = -9189709159551554646L;

	private Integer coordX;

	private Integer coordY;

	private Integer maxDist;

	public Integer getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}

	public Integer getMaxDist() {
		return maxDist;
	}

	public void setMaxDist(Integer maxDist) {
		this.maxDist = maxDist;
	}

}
