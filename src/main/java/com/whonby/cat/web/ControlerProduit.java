package com.whonby.cat.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whonby.cat.dao.ProduitRepository;
import com.whonby.cat.entite.Produit;

@Controller
public class ControlerProduit {

	@Autowired
	private ProduitRepository produitRepository;
	
	
	@GetMapping(value = "/")
	public String index(Model model,@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue ="6")int s) {
		
		Page<Produit> pageProduits=produitRepository.findAll(new PageRequest(p, s));
		model.addAttribute("listeProduit", pageProduits.getContent());
		int[] pages=new int[pageProduits.getTotalPages()];
		model.addAttribute("page", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		
		return "produit";
		//return "layout";
	}
	
	@GetMapping(value = "/recherche")
	public String cherche(Model model,@RequestParam(name="page",defaultValue ="0")int p,
			@RequestParam(name="size",defaultValue ="6")int s,@RequestParam(name="motCle",defaultValue ="")String mot) {
		Page<Produit> pageProduits=produitRepository.cherche("%"+mot+"%", new PageRequest(p, s));
		model.addAttribute("listeProduit", pageProduits.getContent());
		int[] pages=new int[pageProduits.getTotalPages()];
		model.addAttribute("page", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mot);
		
		return "produit";
	}
	
	@GetMapping(value = "/delete")
	public String delete(Long id) {
		produitRepository.deleteById(id);
		return "redirect:/";
		
	}
	@GetMapping(value = "/addProduit")
	public String getFormulaire(Model model) {
		model.addAttribute("produit", new Produit());
		return "/produitAdd";
	}
	@PostMapping(value="/save")
	public String add(Model model,@Valid Produit produit,BindingResult bindinResul,@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue ="6")int s) {
		
		if (bindinResul.hasErrors()) 
			return "/produitAdd";
		
			produitRepository.save(produit);
			return "confirm";	
		
		
	
	}
	
}
