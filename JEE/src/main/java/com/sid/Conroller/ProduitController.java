package com.sid.Conroller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sid.dao.ProduitRepositery;
import com.sid.etities.Produit;

@Controller
public class ProduitController {
	
@Autowired
ProduitRepositery pp;

@RequestMapping(value = "/") 
public String Home() {
	return"redirect:/user/index";
}

@RequestMapping("/user/index") 
public String index(Model model,
		@RequestParam(name="page",defaultValue="0")int p,
		@RequestParam(name="size",defaultValue="10")int s,
		@RequestParam(name="motCle",defaultValue="")String mc) {
	
//	List<Produit> produits=pp.findAll();
//	model.addAttribute("listProduits", produits);
//	Page<Produit> Pageproduits=pp.findAll(PageRequest.of(p, s));
	
	Page<Produit> Pageproduits=pp.chercher("%"+mc+"%", PageRequest.of(p, s));
	model.addAttribute("listProduits", Pageproduits.getContent());
	int[] pages= new int[Pageproduits.getTotalPages()];
	model.addAttribute("pages", pages);
	model.addAttribute("size", s);
	model.addAttribute("pageCourante", p);
	model.addAttribute("motCle", mc);
	return "produits";
}

@RequestMapping(value = "/admin/delete", method=RequestMethod.GET) 
	public String delete(Long id) {
		pp.deleteById(id);
		return"redirect:/user/index";
	}
@RequestMapping(value = "/admin/edit", method=RequestMethod.GET) 
public String Edit(Model model,Long id) {
	Produit p=pp.findById(id).get();
	model.addAttribute("produit", p);
	return"FormProduit";
}
@RequestMapping(value = "/admin/form", method=RequestMethod.GET) 
public String FormProduit (Model model) {
	model.addAttribute("produit", new Produit());
	return"FormProduit";
}

@RequestMapping(value = "/admin/save", method=RequestMethod.POST) 
public String save (Model model,@Valid Produit produit,
		BindingResult bindingResult) {
	if(bindingResult.hasErrors())
		return "FormProduit";
	pp.save(produit);
	return "Confirmation";
}

@RequestMapping(value = "403") 
public String error403() {
	return"error403";
}
@RequestMapping(value = "login") 
public String login() {
	return"login";
}

}
