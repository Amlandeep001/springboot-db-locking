package com.javatechie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.service.OptimisticSeatBookingTestService;
import com.javatechie.service.PessimisticSeatBookingTestService;

@RestController
@RequestMapping("/booking")
public class BookingTestController
{
	private final OptimisticSeatBookingTestService optimisticSeatBookingTestService;
	private final PessimisticSeatBookingTestService pessimisticSeatBookingTestService;

	public BookingTestController(OptimisticSeatBookingTestService optimisticSeatBookingTestService, PessimisticSeatBookingTestService pessimisticSeatBookingTestService)
	{
		this.optimisticSeatBookingTestService = optimisticSeatBookingTestService;
		this.pessimisticSeatBookingTestService = pessimisticSeatBookingTestService;
	}

	@GetMapping("/optimistic/{seatId}")
	public String testOptimistic(@PathVariable Long seatId) throws InterruptedException
	{
		optimisticSeatBookingTestService.testOptimisticLocking(seatId);
		return "Optimistic locking test started! Check logs for results.";
	}

	@GetMapping("/pessimistic/{seatId}")
	public String testPessimistic(@PathVariable Long seatId) throws InterruptedException
	{
		pessimisticSeatBookingTestService.testPessimisticLocking(seatId);
		return "Pessimistic locking test started! Check logs for results.";
	}
}
