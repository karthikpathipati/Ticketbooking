package com.ticket.ticketcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.binding.Passenger;
import com.ticket.binding.TicketInformation;
import com.ticket.service.TicketService;


@RestController
public class TicketController {
	
	private TicketService ticketService;
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
		
	}
	@PostMapping
	public ResponseEntity<TicketInformation> bookTicketFromController (@RequestBody Passenger passenger) {
		TicketInformation ticketInfo = ticketService.bookTicket(passenger);
		return new ResponseEntity<TicketInformation>(ticketInfo, HttpStatus.OK);
		
	}
	
	@GetMapping("/getTicket/{pnr}")
	public ResponseEntity<TicketInformation> getTicketDataFromRestController(@PathVariable String pnr) {
		TicketInformation trainInformation = ticketService.getTrainInformation(pnr);
		return new ResponseEntity<TicketInformation>(trainInformation, HttpStatus.OK);
		
	}

}
