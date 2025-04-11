package com.db.locking.service;

import org.springframework.stereotype.Service;

import com.db.locking.entity.Seat;

@Service
public class OptimisticSeatBookingTestService
{
	private MovieTicketBookingService movieTicketBookingService;

	public OptimisticSeatBookingTestService(MovieTicketBookingService movieTicketBookingService)
	{
		this.movieTicketBookingService = movieTicketBookingService;
	}

	public void testOptimisticLocking(Long seatId) throws InterruptedException
	{
		// 2 threads

		Thread th1 = new Thread(() ->
		{
			try
			{
				System.out.println(Thread.currentThread().getName() + " is attempting to book the seat");
				Seat seat = movieTicketBookingService.bookSeat(seatId);
				System.out.println(Thread.currentThread().getName() + " successfully booked the seat with version " + seat.getVersion());
			}
			catch(Exception ex)
			{
				System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
			}
		});

		Thread th2 = new Thread(() ->
		{
			try
			{
				System.out.println(Thread.currentThread().getName() + " is attempting to book the seat");
				Seat seat = movieTicketBookingService.bookSeat(seatId);
				System.out.println(Thread.currentThread().getName() + " successfully booked the seat with version " + seat.getVersion());
			}
			catch(Exception ex)
			{
				System.out.println(Thread.currentThread().getName() + " failed : " + ex.getMessage());
			}
		});

		th1.start();
		th2.start();
		th1.join();
		th2.join();
	}
}
