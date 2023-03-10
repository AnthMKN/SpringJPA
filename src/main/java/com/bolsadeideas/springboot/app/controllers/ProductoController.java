package com.bolsadeideas.springboot.app.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

    @Autowired
    private IClienteService clienteService;
    
    @Autowired
	private IUploadFileService uploadFileService;

    private final static String UPLOADS_FOLDER = "uploads";
    
    @GetMapping(value="/uploads/{fileName:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String fileName){
		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.load(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+ recurso.getFilename()+"\"").body(recurso);
	}
    
    @RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Formulario de Producto");
		return "producto/form";
	}
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardarProducto(@Valid @ModelAttribute Producto producto, BindingResult result, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile foto) {
    	
        if (result.hasErrors()) {
            return "/form";
        }
        
        if(!foto.isEmpty()) {

        	if(producto.getId() != null 
        			&& producto.getId()> 0 
        			&& producto.getFoto() != null
        			&& producto.getFoto().length() > 0) {

        		uploadFileService.delete(producto.getFoto());

        	}
        	String uniqueFileName = null;

        	try {
        		uniqueFileName = uploadFileService.copy(foto);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}

        	Path routePath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFileName);
        	Path routeAbsolutePath = routePath.toAbsolutePath();


        	flash.addFlashAttribute("info","Has subido correctamente " + uniqueFileName);

        	producto.setFoto(uniqueFileName);
        }else {
        	producto.setFoto("");
        }
        
        
        clienteService.saveProducto(producto);
        flash.addFlashAttribute("success", "El producto se ha guardado exitosamente.");
        return "redirect:/";
    }
    
    @GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
    	Producto producto = clienteService.findOneProduct(id);
		if(producto == null) {
			flash.addFlashAttribute("Error","Producto no encontrado");
			return "redirect:/";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Detalles del producto" +" "+ producto.getNombre());
		
		return "producto/ver";
	}
    
    @RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Producto producto = null;

		if (id > 0) {
			producto = clienteService.findOneProduct(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El ID del producto no existe en la BBDD!");
				return "redirect:/";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
			return "redirect:/";
		}
		model.put("producto", producto);
		model.put("titulo", "Editar Producto");
		return "producto/form";
	}
}
