package com.myexam.kafka.analysis;

import java.math.BigDecimal;

public class TransientSimulation {
	public enum RewardType {
		NORMAL_TRANSIENT, CUMULATIVE_WATCHER
	}

	private static final BigDecimal ARRIVAL_RATE = BigDecimal.ONE;
	private static final BigDecimal DEFAULT_TIME_STEP = new BigDecimal("0.1");
	private static final BigDecimal DEFAULT_TIME_HORIZON = new BigDecimal("250");
	private static final long DEFAULT_RUNS = 1L;

	private final int batchSize;
	private final BigDecimal timeout;
	private final int overhead;
	private final int stability;
	private final BigDecimal timeStep;
	private final BigDecimal timeHorizon;
	private final long runs;
	private final RewardType rewardType;
	

	public TransientSimulation(int batchSize, BigDecimal timeout, int overhead, int stability, BigDecimal timeStep,
			BigDecimal timeHorizon, long runs, RewardType rewardType) {
		this.batchSize = batchSize;
		this.timeout = requirePositive(timeout, "timeout");
		this.overhead = overhead;
		this.stability = stability;
		this.timeStep = requirePositive(timeStep, "timeStep");
		this.timeHorizon = requirePositive(timeHorizon, "timeHorizon");
		if (runs <= 0) {
			throw new IllegalArgumentException("runs must be greater than zero");
		}
		this.runs = runs;
		if (rewardType == null) {
			throw new IllegalArgumentException("rewardType must not be null");
		}
		this.rewardType = rewardType;
	}
	

	private static BigDecimal requirePositive(BigDecimal value, String name) {
		if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException(name + " must be greater than zero");
		}
		return value;
	}

}
