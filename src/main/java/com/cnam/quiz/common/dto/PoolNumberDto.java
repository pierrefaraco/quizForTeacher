package com.cnam.quiz.common.dto;

public class PoolNumberDto {
	private int poolNumber;

	public int getPoolNumber() {
		return poolNumber;
	}

	public void setPoolNumber(int poolNumber) {
		this.poolNumber = poolNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + poolNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoolNumberDto other = (PoolNumberDto) obj;
		if (poolNumber != other.poolNumber)
			return false;
		return true;
	}
	
	
	
}
