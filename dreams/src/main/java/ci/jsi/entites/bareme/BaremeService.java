package ci.jsi.entites.bareme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaremeService implements Ibareme {
	@Autowired
	BaremeConvert baremeConvert;
	@Autowired
	BaremeRepository baremeRepository; 

	@Override
	public List<BaremeTDO> getAllBaremeTDO() {
		
		return baremeConvert.getBaremeTDOs(baremeRepository.findAll());
	}

	@Override
	public BaremeTDO getOneBaremeTDO(String id) {
		BaremeTDO baremeTDO = new BaremeTDO();
		Bareme bareme = new Bareme();
		bareme = baremeRepository.getOneBareme(id);
		if(bareme != null)
			baremeTDO = baremeConvert.getBaremeTDO(bareme);
		return baremeTDO;
	}

	@Override
	public String saveBaremeTDO(BaremeTDO baremeTDO) {
		Bareme bareme = new Bareme();
		bareme = baremeConvert.saveBaremeTDO(baremeTDO);
		bareme = baremeRepository.save(bareme);
		return "Saved id: "+bareme.getUid();
	}

	@Override
	public String updateBaremeTDO(String id, BaremeTDO baremeTDO) {
		Bareme bareme = new Bareme();
		bareme = baremeRepository.getOneBareme(id);
		if(bareme != null) {
			bareme = baremeConvert.updateBaremeTDO(bareme, baremeTDO);
			bareme = baremeRepository.save(bareme);
		}
			
		return "Updated id: "+bareme.getUid();
	}

	@Override
	public String deleteBaremeTDO(String id) {
		Bareme bareme = new Bareme();
		bareme = baremeRepository.getOneBareme(id);
		if(bareme != null)
			baremeRepository.delete(bareme);
		return "Succes Deleted";
	}

	

}
