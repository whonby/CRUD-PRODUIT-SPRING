package com.whonby.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.whonby.cat.dao.ProduitRepository;
import com.whonby.cat.entite.Produit;

@SpringBootApplication
public class Cat1Application {

	public static void main(String[] args) {
		
		ApplicationContext ctx=SpringApplication.run(Cat1Application.class, args);
	    ProduitRepository produitRepository =ctx.getBean(ProduitRepository.class);
		/*
		 * produitRepository.save(new Produit("HP Book",452200.45,12));
		 * produitRepository.save(new Produit("Dell Latitude",44788,10));
		 * produitRepository.save(new Produit("IPHONE 4",480050,9));
		 * produitRepository.save(new Produit("Asur Aliene",700000,7));
		 * produitRepository.save(new Produit("Mac OS 7",755500,100));
		 * produitRepository.save(new Produit("HP Book 10",4522.45,1240));
		 */
		
	}

}
