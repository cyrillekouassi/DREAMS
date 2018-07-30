package ci.jsi.entites.element;

import java.util.List;

public interface Ielement {

	public List<ElementTDO> getAllElementTDO();
	public ElementTDO getOneElmentTDO(String id);
	public String saveElement(ElementTDO elementTDO);
	public String updateElement(String id,ElementTDO elementTDO);
	public String deleteElement(String id);
	
	
	public Element getOneElmentByName(String name);
	public Element getOneElmentByCode(String code);
	public Element getOneElmentById(String id);
	public Element saveOneElment(Element element);
}
