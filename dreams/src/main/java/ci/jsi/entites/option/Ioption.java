package ci.jsi.entites.option;

import java.util.List;

public interface Ioption {

	public List<OptionTDO> getAllOptionTDO();
	public OptionTDO getOneOptionTDO(String id);
	public String saveOptionTDO(OptionTDO optionTDO);
	public String updateOptionTDO(String id,OptionTDO optionTDO);
	public String deleteOptionTDO(String id);
	
	public Option getOneOptionbyName(String name);
	public Option getOneOptionbyUid(String uid);
	public Option saveOption(Option option);
}
