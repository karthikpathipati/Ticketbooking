package com.ticket.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ticket.binding.Passenger;
import com.ticket.binding.TicketInformation;
import com.ticket.dao.PassengerEntity;
import com.ticket.dao.PassengerRepo;
import com.ticket.dao.TicketEntity;
import com.ticket.dao.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService {
	
	private TicketRepo ticketRepo;
	private PassengerRepo passengerRepo;
	public TicketServiceImpl ( TicketRepo ticketRepo , PassengerRepo passengerRepo ) {
		this.ticketRepo = ticketRepo;
		this.passengerRepo = passengerRepo;
		
	}
	@Override
	public TicketInformation bookTicket(Passenger passenger) {
		String pnr = "";
		for(int i =1; i<=8; i++) {
			pnr = pnr+(int)(Math.random()*10);
		}
		TicketInformation ticket = new TicketInformation();
		ticket.setPnr(pnr);
		ticket.setTicketPrice(1200.00);
		ticket.setTicketStatus("confirmed");
		ticket.setPassengerName(passenger.getPassengerName());
		ticket.setFromLoc(passenger.getFromLoc());
		ticket.setToLoc(passenger.getToLoc());
		ticket.setJourneyDate(LocalDate.now());
		ticket.setTrainNum(passenger.getTrainNum());
		
		PassengerEntity passengerEntity = new PassengerEntity();
		BeanUtils.copyProperties(passenger, passengerEntity);
		passengerRepo.save(passengerEntity);
		
		TicketEntity ticketEntity = new TicketEntity();
		BeanUtils.copyProperties(ticket, ticketEntity);
		ticketRepo.save(ticketEntity);
		
		return ticket; 
		
	}

	@Override
	public TicketInformation getTrainInformation(String pnr) {
		TicketEntity byPnr = ticketRepo.findByPnr(pnr);
		TicketInformation ticketInfo = new TicketInformation();
		BeanUtils.copyProperties(byPnr, ticketInfo);
		return ticketInfo;
	}

} 
