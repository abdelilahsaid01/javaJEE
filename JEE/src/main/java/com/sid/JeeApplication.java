package com.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sid.dao.ProduitRepositery;
import com.sid.etities.Produit;

@SpringBootApplication
public class JeeApplication {

	public static void main(String[] args) {
//		ApplicationContext ctx= 
				SpringApplication.run(JeeApplication.class, args);
		System.out.println("Start Projet JEE......");
//		ProduitRepositery pp = ctx.getBean(ProduitRepositery.class);
//		pp.save(new Produit("LX 123", 520, 15));
//		pp.save(new Produit("ZE 253", 269, 10));
//		pp.save(new Produit("XA 526", 148, 18));
		
//		pp.findAll().forEach(p->System.out.println(p.getDesignation()));
		
		

//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String password = "1234";
//		String encodedPassword = passwordEncoder.encode(password);
//		System.out.println();
//		System.out.println("Password is         : " + password);
//		System.out.println("Encoded Password is : " + encodedPassword);
	}

}
