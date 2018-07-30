package ci.jsi.entites.element;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class ElementConvertEntitie {

	@Autowired
	ElementRepository elementRepository;
	

	public UidEntitie getElement(Element element) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(element.getUid());
		return uidEntitie;
	}

	public Element setElement(UidEntitie uidEntitie) {
		Element element = new Element();
		if(uidEntitie.getId() != null)
			element = elementRepository.getOneElement(uidEntitie.getId());
		return element;
	}

	public List<UidEntitie> getElements(List<Element> elements) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < elements.size(); i++) {
			uidEntities.add(getElement(elements.get(i)));
		}
		return uidEntities;
	}

	public List<Element> setElements(List<UidEntitie> uidEntities) {
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < uidEntities.size(); i++) {
			Element element = new Element();
			element = setElement(uidEntities.get(i));
			if(element != null)
				elements.add(element);
		}
		return elements;
	}
}
