package ci.jsi.importExport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import ci.jsi.importExport.traitement.DataValuesImport;
import ci.jsi.importExport.traitement.ElementsImportes;
import ci.jsi.initialisation.ResultatRequete;

@RestController
@RequestMapping("/api")
public class RestUploadController {

	@Autowired
	StorageService storageService;
	@Autowired
	ElementsImportes elementsImportes;
	@Autowired
	DataValuesImport dataValuesImport;
	
	List<String> files = new ArrayList<String>();
	
	
	@PostMapping("/uploadfile")
	public String uploadFileMulti(@RequestParam("uploadfile") MultipartFile file) throws Exception {
		System.out.println("entrer dans uploadFileMulti");
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
			System.out.println("file.getOriginalFilename() = "+file.getOriginalFilename());
			elementsImportes.lireCSV(file.getOriginalFilename());
			return "You successfully uploaded - " + file.getOriginalFilename();
		} catch(Exception e) {
			throw new Exception("FAIL! Maybe You had uploaded the file before or the file's size > 500KB");
		}
		
	}
	
	@PostMapping("/uploadElementfile")
	public ResultatRequete uploadElementFileMulti(@RequestParam("uploadfile") MultipartFile file) throws Exception {
		System.out.println("entrer dans uploadElementFileMulti");
		try {
			storageService.deleteAll();
			storageService.init();
			storageService.store(file);
			files.add(file.getOriginalFilename());
			//System.out.println("file.getOriginalFilename() = "+file.getOriginalFilename());
			//elementsImportes.lireCSV(file.getOriginalFilename());
			return elementsImportes.lireCSV(file.getOriginalFilename());
		} catch(Exception e) {
			throw new Exception("Echec de lecture du fichier");
		}
		
	}
	
	@PostMapping("/uploadEnrolDatafile")
	public ResultatRequete uploadDataFileMulti(@RequestParam("uploadfile") MultipartFile file) throws Exception {
		System.out.println("entrer dans uploadDataFileMulti");
		try {
			storageService.deleteAll();
			storageService.init();
			storageService.store(file);
			files.add(file.getOriginalFilename());
			//System.out.println("file.getOriginalFilename() = "+file.getOriginalFilename());
			//dataValuesImport.lireCSV(file.getOriginalFilename());
			//storageService.deleteAll();
			return dataValuesImport.lireCSV(file.getOriginalFilename());
			//return dataValuesImport.lireCSV(file);
		} catch(Exception e) {
			throw new Exception("Echec de lecture du fichier");
		}
		
	}
	
	@GetMapping("/getallfiles")
	public List<String> getListFiles(){
		System.out.println("entrer dans getListFiles");
		List<String> lstFiles = new ArrayList<String>();
		try {
			lstFiles = files.stream().map(fileName -> MvcUriComponentsBuilder.fromMethodName(RestUploadController.class, "getFile", fileName).build().toString()).collect(Collectors.toList());
			/*lstFiles = files.stream()
			.map(fileName -> MvcUriComponentsBuilder
					.fromMethodName(RestUploadController.class, "getFile", fileName).build().toString())
			.collect(Collectors.toList());*/
		}catch(Exception e){
			throw e;
		}
		
		return lstFiles;
		
	}
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename){
		System.out.println("entrer dans getFile");
		System.out.println("filename = "+ filename);
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"").body(file);
	}
	
	
	
}






















