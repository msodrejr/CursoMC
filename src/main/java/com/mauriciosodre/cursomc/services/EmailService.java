package com.mauriciosodre.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.mauriciosodre.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
