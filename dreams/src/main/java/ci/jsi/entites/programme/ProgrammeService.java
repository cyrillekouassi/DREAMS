package ci.jsi.entites.programme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgrammeService implements Iprogramme {
	@Autowired
	ProgrammeConvert programmeConvert;
	@Autowired
	ProgrammeRepository programmeRepository;

	@Override
	public List<ProgrammeTDO> getAllProgrammeTDO() {
		
		return programmeConvert.getProgrammeTDOs(programmeRepository.findAll());
	}

	@Override
	public ProgrammeTDO getOneProgrammeTDO(String id) {
		Programme programme = new Programme();
		ProgrammeTDO programmeTDO = new ProgrammeTDO();
		
		programme = programmeRepository.getOneProgramme(id);
		if(programme != null)
			programmeTDO = programmeConvert.getProgrammeTDO(programme);
		
		return programmeTDO;
	}

	@Override
	public String saveProgrammeTDO(ProgrammeTDO programmeTDO) {
		Programme programme = new Programme();
		if(programmeTDO.getName() == null)
			return "fail";
		if(programmeTDO.getCode() == null)
			return "fail";
		programme = programmeRepository.findByName(programmeTDO.getName());
		if(programme != null)
			return "fail";
		programme = programmeRepository.findByCode(programmeTDO.getCode());
		if(programme != null)
			return "fail";
		programme = programmeConvert.saveProgrammeTDO(programmeTDO);
		programme = programmeRepository.save(programme);
		return "Saved id: "+programme.getUid();
		
	}

	@Override
	public String updateProgrammeTDO(String id, ProgrammeTDO programmeTDO) {
		Programme programme = new Programme();		
		programme = programmeRepository.getOneProgramme(id);
		if(programme != null) {
			programme = programmeConvert.updateProgrammeTDO(programme, programmeTDO);
			programme = programmeRepository.save(programme);
		}
			
		return "Update id: "+programme.getUid();
	}

	@Override
	public String deleteProgrammeTDO(String id) {
		Programme programme = new Programme();		
		programme = programmeRepository.getOneProgramme(id);
		if(programme != null) {
			programmeRepository.delete(programme);
		}
		return null;
	}

	@Override
	public Programme getOneProgramme(String uid) {
		return programmeRepository.getOneProgramme(uid);
	}

	@Override
	public Programme updateOneProgramme(Programme programme) {
		
		return programmeRepository.save(programme);
	}

	@Override
	public Programme getOneProgrammeByCode(String code) {
		return programmeRepository.findByCode(code);
	}

	@Override
	public void saveProgramme(String name, String code) {
		ProgrammeTDO programmeTDO = new ProgrammeTDO();
		programmeTDO.setName(name);
		programmeTDO.setCode(code);
		saveProgrammeTDO(programmeTDO);
	}

}
