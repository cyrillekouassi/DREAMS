package ci.jsi.entites.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.bareme.BaremeConvertEntitie;
import ci.jsi.entites.ensembleCritere.EnsembleCritereConvertEntitie;
import ci.jsi.entites.programme.ProgrammeConvertEntite;
import ci.jsi.initialisation.Uid;

@Service
public class ServiceConvert {
	@Autowired
	BaremeConvertEntitie baremeConvertEntitie;
	@Autowired
	ProgrammeConvertEntite programmeConvertEntite;
	@Autowired
	EnsembleCritereConvertEntitie ensembleCritereConvertEntitie;
	@Autowired
	Uid uid;

	public ServiceTDO getServiceTDO(Servicess servicess) {
		ServiceTDO serviceTDO = new ServiceTDO();
		serviceTDO.setId(servicess.getUid());
		serviceTDO.setName(servicess.getName());
		serviceTDO.setCode(servicess.getCode());
		if (servicess.getBareme().getUid() != null)
			serviceTDO.setBareme(baremeConvertEntitie.getBareme(servicess.getBareme()));
		serviceTDO.setProgrammes(programmeConvertEntite.getProgrammes(servicess.getProgrammes()));
		serviceTDO
				.setEnsembleCriteres(ensembleCritereConvertEntitie.getEnsembleCriteres(servicess.getEnsembleCriteres()));
		return serviceTDO;
	}

	public List<ServiceTDO> getServiceTDO(List<Servicess> servicess) {
		List<ServiceTDO> serviceTDOs = new ArrayList<ServiceTDO>();

		for (int i = 0; i < servicess.size(); i++) {
			serviceTDOs.add(getServiceTDO(servicess.get(i)));
		}
		return serviceTDOs;
	}

	public Servicess saveServiceTDO(ServiceTDO serviceTDO) {
		Servicess servicess = new Servicess();
		servicess.setUid(uid.getUid());
		servicess.setName(serviceTDO.getName());
		servicess.setCode(serviceTDO.getCode());
		if (serviceTDO.getBareme() != null)
			servicess.setBareme(baremeConvertEntitie.setBareme(serviceTDO.getBareme()));
		servicess.setProgrammes(programmeConvertEntite.setProgrammes(serviceTDO.getProgrammes()));
		servicess.setEnsembleCriteres(ensembleCritereConvertEntitie.setEnsembleCritere(serviceTDO.getEnsembleCriteres()));
		return servicess;
	}

	public Servicess updateServiceTDO(Servicess servicess, ServiceTDO serviceTDO) {
		if (serviceTDO.getName() != null)
			servicess.setName(serviceTDO.getName());
		if (serviceTDO.getCode() != null)
			servicess.setCode(serviceTDO.getCode());
		if (serviceTDO.getBareme() != null)
			servicess.setBareme(baremeConvertEntitie.setBareme(serviceTDO.getBareme()));
		if (serviceTDO.getProgrammes().size() != 0)
			servicess.setProgrammes(programmeConvertEntite.setProgrammes(serviceTDO.getProgrammes()));
		if (serviceTDO.getEnsembleCriteres().size() != 0)
			servicess.setEnsembleCriteres(
					ensembleCritereConvertEntitie.setEnsembleCritere(serviceTDO.getEnsembleCriteres()));
		return servicess;
	}

}
