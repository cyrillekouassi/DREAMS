package ci.jsi.entites.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService implements Ioption {
	
	@Autowired
	OptionConvert optionConvert;
	@Autowired
	OptionRepository optionRepository;
	@Override
	public List<OptionTDO> getAllOptionTDO() {
		
		return optionConvert.getOptionTDOs(optionRepository.findAll());
	}
	@Override
	public OptionTDO getOneOptionTDO(String id) {
		Option option = new Option();
		OptionTDO optionTDO = new OptionTDO();
		option = optionRepository.getOneOption(id);
		if(option != null)
			optionTDO = optionConvert.getOneOptionTDO(option);
		return optionTDO;
	}
	@Override
	public String saveOptionTDO(OptionTDO optionTDO) {
		Option option = new Option();
		option = optionConvert.saveOption(optionTDO);
		optionRepository.save(option);
		return "Saved id: ="+option.getUid();
	}
	@Override
	public String updateOptionTDO(String id, OptionTDO optionTDO) {
		Option option = new Option();
		option = optionConvert.updateOption(option, optionTDO);
		optionRepository.save(option);
		return "Updated id: ="+option.getUid();
	}
	@Override
	public String deleteOptionTDO(String id) {
		Option option = new Option();
		option = optionRepository.getOneOption(id);
		if(option != null)
			optionRepository.delete(option);
		return "Succes deleted";
	}
	@Override
	public Option getOneOptionbyName(String name) {
		return optionRepository.getOneOptionByName(name);
	}
	@Override
	public Option getOneOptionbyUid(String uid) {
		
		return optionRepository.getOneOption(uid);
	}
	@Override
	public Option saveOption(Option option) {
		Option opt = null;
		if(optionRepository.getOneOptionNameEnsemble(option.getName(), option.getEnsembleOption()) == null 
				&& optionRepository.getOneOptionCodeEnsemble(option.getCode(), option.getEnsembleOption()) == null ) {
			opt = optionRepository.save(option);
		}
		return opt;
	}

	

}
