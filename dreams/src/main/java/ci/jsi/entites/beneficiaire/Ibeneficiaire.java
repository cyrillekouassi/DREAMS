package ci.jsi.entites.beneficiaire;

import java.util.List;

import ci.jsi.initialisation.ResultatRequete;

public interface Ibeneficiaire {

	
	public List<BeneficiaireTDO> SearchBeneficiaireTDOByIdDreams(String idDreams,String organisation);
	
	public ResultatRequete saveBeneficiaireTDO(BeneficiaireTDO beneficiaireTDO);
	
	public Beneficiaire getOneBeneficiaireByUid(String uid);
	public Beneficiaire getOneBeneficiaireByIdDreams(String idDreams);
	public Beneficiaire updateOneBeneficiaire(Beneficiaire beneficiaire);
	public Beneficiaire saveOneBeneficiaire(Beneficiaire beneficiaire);
	public Beneficiaire convertBeneficiaireTDO(BeneficiaireTDO beneficiaireTDO);
	public List<BeneficiaireTDO> getBeneficiairePeriode(List<String> organisation,String debut,String fin);
	public List<BeneficiaireTDO> getBeneficiairePreview(List<String> organisation,String fin);
}
