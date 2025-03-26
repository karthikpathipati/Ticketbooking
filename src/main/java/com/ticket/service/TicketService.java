package com.ticket.service;

import com.ticket.binding.Passenger;
import com.ticket.binding.TicketInformation;

public interface TicketService {
	
	public TicketInformation  bookTicket(Passenger passenger);
	
	public TicketInformation getTrainInformation (String pnr);	
	
} 
   
	


