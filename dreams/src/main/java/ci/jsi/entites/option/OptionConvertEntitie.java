package ci.jsi.entites.option;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.CompleteEntite;

@Service
public class OptionConvertEntitie {

	@Autowired
	OptionRepository optionRepository;
	

	public CompleteEntite getOption(Option option) {
		CompleteEntite completeEntite = new CompleteEntite();
		completeEntite.setId(option.getUid());
		completeEntite.setName(option.getName());
		completeEntite.setCode(option.getCode());
		return completeEntite;
	}

	public Option setOption(CompleteEntite completeEntites) {
		Option otion = new Option();
		if(completeEntites.getId() != null)
			otion = optionRepository.getOneOption(completeEntites.getId());
		return otion;
	}

	public List<CompleteEntite> getOptions(List<Option> options) {
		List<CompleteEntite> completeEntites = new ArrayList<CompleteEntite>();

		for (int i = 0; i < options.size(); i++) {
			completeEntites.add(getOption(options.get(i)));
		}
		return completeEntites;
	}

	public List<Option> setOptions(List<CompleteEntite> completeEntites) {
		List<Option> options = new ArrayList<Option>();
		for (int i = 0; i < completeEntites.size(); i++) {
			Option option = new Option();
			option = setOption(completeEntites.get(i));
			if(option.getUid() != null)
				options.add(option);
		}
		return options;
	}
}
