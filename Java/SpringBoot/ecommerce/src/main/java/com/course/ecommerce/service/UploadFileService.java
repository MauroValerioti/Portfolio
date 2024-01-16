package com.course.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
*servicio de administracion de imagenes de productos
*/

@Service
public class UploadFileService {

	private String folder = "images//";

// 		este tipo de elemento que toma como parrametro es el que contendra la imagen
// 		que le pasare al metodo
	public String saveImage(MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
//		 paso la imagen a bytes para poderla enviar de un lugar a otro
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
//		si el usuario no carga ninguna imagen vamos a asignar una imagen por defecto
		return "default.jpg";
	}

//		cuando elimine un producto esto va a eliminar tambien la imagen asignada.
	public void deleteImagen(String nombre) {
		String ruta="images//";
		File file = new File(ruta+nombre);
		file.delete();
	}
}
