package ci.jsi.entites.ensembleOption;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.element.ElementConvertEntitie;
import ci.jsi.entites.option.OptionConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class EnsembleOptionConvert {
	@Autowired
	OptionConvertEntitie optionConvertEntitie;
	@Autowired
	ElementConvertEntitie elementConvertEntitie;
	@Autowired
	Uid uid;
	

	public EnsembleOptionTDO getEnsembleOptionTDO(EnsembleOption ensembleOption) {
		EnsembleOptionTDO ensembleOptionTDO = new EnsembleOptionTDO();
		ensembleOptionTDO.setId(ensembleOption.getUid());
		ensembleOptionTDO.setName(ensembleOption.getName());
		ensembleOptionTDO.setMultiple(ensembleOption.isMultiple());
		ensembleOptionTDO.setCode(ensembleOption.getCode());
		ensembleOptionTDO.setTypeValeur(ensembleOption.getTypeValeur());
		ensembleOptionTDO.setOptions(optionConvertEntitie.getOptions(ensembleOption.getOptions()));
		ensembleOptionTDO.setElements(elementConvertEntitie.getElements(ensembleOption.getElements()));
		return ensembleOptionTDO;
	}
	
	public List<EnsembleOptionTDO> getEnsembleOptionTDOs(List<EnsembleOption> ensembleOptions) {
		List<EnsembleOptionTDO> ensembleOptionTDOs = new ArrayList<EnsembleOptionTDO>();
		
		for(int i=0;i<ensembleOptions.size();i++) {
			ensembleOptionTDOs.add(getEnsembleOptionTDO(ensembleOptions.get(i)));
			
		}
		return ensembleOptionTDOs;
	}
	
	public EnsembleOption saveEnsembleOptionTDO(EnsembleOptionTDO ensembleOptionTDO) {
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption.setUid(uid.getUid());
		ensembleOption.setName(ensembleOptionTDO.getName());
		ensembleOption.setMultiple(ensembleOptionTDO.isMultiple());
		ensembleOption.setCode(ensembleOptionTDO.getCode());
		ensembleOption.setTypeValeur(ensembleOptionTDO.getTypeValeur());
		ensembleOption.setOptions(optionConvertEntitie.setOptions(ensembleOptionTDO.getOptions()));
		ensembleOption.setElements(elementConvertEntitie.setElements(ensembleOptionTDO.getElements()));
		return ensembleOption;
	}
	
	
	public EnsembleOption updateEnsembleOptionTDO(EnsembleOption ensembleOption,EnsembleOptionTDO ensembleOptionTDO) {
		
		//ensembleOption.setUid(uid.getUid());
		if(ensembleOptionTDO.getName() != null)
			ensembleOption.setName(ensembleOptionTDO.getName());
		//if(ensembleOptionTDO.isMultiple())
			ensembleOption.setMultiple(ensembleOptionTDO.isMultiple());
		if(ensembleOptionTDO.getCode() != null)
			ensembleOption.setCode(ensembleOptionTDO.getCode());
		if(ensembleOptionTDO.getTypeValeur() != null)
			ensembleOption.setTypeValeur(ensembleOptionTDO.getTypeValeur());
		if(ensembleOptionTDO.getOptions().size() != 0)
			ensembleOption.setOptions(optionConvertEntitie.setOptions(ensembleOptionTDO.getOptions()));
		if(ensembleOptionTDO.getElements().size() != 0)
			ensembleOption.setElements(elementConvertEntitie.setElements(ensembleOptionTDO.getElements()));
		return ensembleOption;
	}
	
	
	
	
	
}
