package ci.jsi.entites.element;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementService implements Ielement {
	
	@Autowired
	private ElementRepository elementRepository;
	@Autowired
	ElementConvert elementConvert;

	@Override
	public List<ElementTDO> getAllElementTDO() {
		
		return elementConvert.getElementTDOs(elementRepository.findAll());
	}

	@Override
	public ElementTDO getOneElmentTDO(String id) {
		Element element = new Element();
		element = elementRepository.getOneElement(id);
		return elementConvert.getOneElementTDO(element);
	}

	@Override
	public String saveElement(ElementTDO elementTDO) {
		Element element = new Element();
		element = elementConvert.saveElement(elementTDO);
		elementRepository.save(element);
		return "Save Succes id: "+element.getUid();
	}

	@Override
	public String updateElement(String id, ElementTDO elementTDO) {
		Element element = new Element();
		element = elementRepository.getOneElement(id);
		element = elementConvert.updateElement(element, elementTDO);
		elementRepository.save(element);
		return "Update Succes id: "+element.getUid();
	}

	@Override
	public String deleteElement(String id) {
		Element element = new Element();
		element = elementRepository.getOneElement(id);
		elementRepository.delete(element);
		return "deleted";
	}

	@Override
	public Element getOneElmentByName(String name) {
		//Element element = new Element();
		//element = elementRepository.getOneElementByName(id);
		return elementRepository.getOneElementByName(name);
	}

	@Override
	public Element saveOneElment(Element element) {
		element.setDateCreation(new Date());
		element.setDateUpdate(new Date());
		return elementRepository.save(element);
	}

	@Override
	public Element getOneElmentByCode(String code) {
		
		return elementRepository.getOneElementByCode(code);
	}

	@Override
	public Element getOneElmentById(String id) {
		return elementRepository.getOneElement(id);
	}

}
