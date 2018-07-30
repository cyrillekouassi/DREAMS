package ci.jsi.entites.bareme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.couleur.CouleurConvertEntitie;
import ci.jsi.entites.service.ServiceConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class BaremeConvert {
	@Autowired
	ServiceConvertEntitie serviceConvertEntitie;
	@Autowired
	CouleurConvertEntitie couleurConvertEntitie;
	@Autowired
	Uid uid;

	public BaremeTDO getBaremeTDO(Bareme bareme) {
		BaremeTDO baremeTDO = new BaremeTDO();
		baremeTDO.setId(bareme.getUid());
		baremeTDO.setName(bareme.getName());
		baremeTDO.setCode(bareme.getCode());
		baremeTDO.setServices(serviceConvertEntitie.getServices(bareme.getServices()));
		baremeTDO.setCouleurs(couleurConvertEntitie.getCouleurs(bareme.getCouleurs()));

		return baremeTDO;
	}

	public List<BaremeTDO> getBaremeTDOs(List<Bareme> baremes) {

		List<BaremeTDO> baremeTDOs = new ArrayList<BaremeTDO>();
		for (int i = 0; i < baremes.size(); i++) {
			baremeTDOs.add(getBaremeTDO(baremes.get(i)));
		}
		return baremeTDOs;
	}

	public Bareme saveBaremeTDO(BaremeTDO baremeTDO) {
		Bareme bareme = new Bareme();
		bareme.setUid(uid.getUid());
		bareme.setName(baremeTDO.getName());
		bareme.setCode(baremeTDO.getCode());
		bareme.setServices(serviceConvertEntitie.setServices(baremeTDO.getServices()));
		bareme.setCouleurs(couleurConvertEntitie.setCouleurs(baremeTDO.getCouleurs()));
		return bareme;
	}

	public Bareme updateBaremeTDO(Bareme bareme,BaremeTDO baremeTDO) {
		
		if(baremeTDO.getName() != null)
			bareme.setName(baremeTDO.getName());
		if(baremeTDO.getCode() != null)
			bareme.setCode(baremeTDO.getCode());
		if(baremeTDO.getServices().size() != 0)
			bareme.setServices(serviceConvertEntitie.setServices(baremeTDO.getServices()));
		if(baremeTDO.getCouleurs().size() != 0)
			bareme.setCouleurs(couleurConvertEntitie.setCouleurs(baremeTDO.getCouleurs()));
		
		return bareme;
	}
}









