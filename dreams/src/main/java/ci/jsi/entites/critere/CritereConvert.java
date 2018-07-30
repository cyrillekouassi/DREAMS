package ci.jsi.entites.critere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.element.ElementConvertEntitie;
import ci.jsi.entites.ensembleCritere.EnsembleCritereConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class CritereConvert {

	@Autowired
	ElementConvertEntitie elementConvertEntitie;
	@Autowired
	EnsembleCritereConvertEntitie ensembleCritereConvertEntitie;
	@Autowired
	Uid uid;
	
	public CritereTDO getCritere(Critere critere) {
		CritereTDO critereTDO = new CritereTDO();
		critereTDO.setId(critere.getUid());
		critereTDO.setName(critere.getName());
		critereTDO.setCode(critere.getCode());
		critereTDO.setOperateur(critere.getOperateur());
		critereTDO.setAttendu(critere.getAttendu());
		if(critere.getElement() != null)
			critereTDO.setElement(elementConvertEntitie.getElement(critere.getElement()));
		critereTDO.setEnsembleCriteres(ensembleCritereConvertEntitie.getEnsembleCriteres(critere.getEnsembleCriteres()));
		
		return critereTDO;
		
	}
	public List<CritereTDO> getCriteres(List<Critere> criteres) {
		List<CritereTDO> critereTDOs = new ArrayList<CritereTDO>();
		
		for(int i = 0;i<criteres.size();i++) {
			critereTDOs.add(getCritere(criteres.get(i)));
		}
		
		return critereTDOs;
		
	}
	
	public Critere saveCritere (CritereTDO critereTDO) {
		Critere critere = new Critere();
		critere.setUid(uid.getUid());
		critere.setName(critereTDO.getName());
		critere.setCode(critereTDO.getCode());
		critere.setOperateur(critereTDO.getOperateur());
		critere.setAttendu(critereTDO.getAttendu());
		critere.setEnsembleCriteres(ensembleCritereConvertEntitie.setEnsembleCritere(critereTDO.getEnsembleCriteres()));
		if(critereTDO.getElement() != null)
			critere.setElement(elementConvertEntitie.setElement(critereTDO.getElement()));
		
		return critere;
		
	}
	
	public Critere updateCritere (Critere critere,CritereTDO critereTDO) {
		if(critereTDO.getName() != null)
			critere.setName(critereTDO.getName());
		if(critereTDO.getCode() != null)
			critere.setCode(critereTDO.getCode());
		if(critereTDO.getOperateur() != null)
			critere.setOperateur(critereTDO.getOperateur());
		if(critereTDO.getAttendu() != null)
			critere.setAttendu(critereTDO.getAttendu());
		if(critereTDO.getEnsembleCriteres().size() != 0)
			critere.setEnsembleCriteres(ensembleCritereConvertEntitie.setEnsembleCritere(critereTDO.getEnsembleCriteres()));
		if(critereTDO.getElement() != null)
			critere.setElement(elementConvertEntitie.setElement(critereTDO.getElement()));
		
		return critere;
	}
}





