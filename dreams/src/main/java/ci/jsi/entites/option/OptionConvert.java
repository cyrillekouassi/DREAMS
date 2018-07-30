package ci.jsi.entites.option;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.ensembleOption.EnsembleOption;
import ci.jsi.entites.ensembleOption.EnsembleOptionConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class OptionConvert {
	@Autowired
	EnsembleOptionConvertEntitie ensembleOptionConvertEntitie;
	@Autowired
	Uid uid;

	public OptionTDO getOneOptionTDO(Option option) {
		OptionTDO optionTDO = new OptionTDO();
		optionTDO.setId(option.getUid());
		optionTDO.setName(option.getName());
		optionTDO.setCode(option.getCode());
		optionTDO.setEnsembleOption(ensembleOptionConvertEntitie.getEnsembleOption(option.getEnsembleOption()));
		
		return optionTDO;
	}
	
	public List<OptionTDO> getOptionTDOs(List<Option> options) {
		List<OptionTDO> optionTDOs = new ArrayList<OptionTDO>();
		
		for(int i=0;i<options.size();i++) {
			optionTDOs.add(getOneOptionTDO(options.get(i)));
		}
		return optionTDOs;
		
	}
	
	public Option saveOption(OptionTDO optionTDO) {
		Option option = new Option();
		option.setUid(uid.getUid());
		option.setName(optionTDO.getName());
		option.setCode(optionTDO.getCode());
		
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionConvertEntitie.setEnsembleOption(optionTDO.getEnsembleOption());
		if(ensembleOption.getUid() != null)
			option.setEnsembleOption(ensembleOption);
		return option;
	}
	
	public Option updateOption(Option option,OptionTDO optionTDO) {
		
		if(optionTDO.getName() != null)
			option.setName(optionTDO.getName());
		if(optionTDO.getCode() != null)
			option.setCode(optionTDO.getCode());
		
		EnsembleOption ensembleOption = new EnsembleOption();
		ensembleOption = ensembleOptionConvertEntitie.setEnsembleOption(optionTDO.getEnsembleOption());
		if(ensembleOption.getUid() != null)
			option.setEnsembleOption(ensembleOption);
		return option;
	}
	
		
	
	
	
	
}
