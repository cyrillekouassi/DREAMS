package ci.jsi.entites.programme;

import java.util.List;

public interface Iprogramme {

	public List<ProgrammeTDO> getAllProgrammeTDO();
	public ProgrammeTDO getOneProgrammeTDO(String id);
	public String saveProgrammeTDO(ProgrammeTDO ProgrammeTDO);
	public String updateProgrammeTDO(String id, ProgrammeTDO programmeTDO);
	public String deleteProgrammeTDO(String id);
	
	public Programme getOneProgramme(String uid);
	public Programme getOneProgrammeByCode(String code);
	public Programme updateOneProgramme(Programme programme);
	public void saveProgramme(String name,String code);
}
