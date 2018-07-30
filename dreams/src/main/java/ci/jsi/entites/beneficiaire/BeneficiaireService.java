package ci.jsi.entites.beneficiaire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.ResultatRequete;

@Service
@Transactional
public class BeneficiaireService implements Ibeneficiaire {
	
	@Autowired
	BeneficiaireRepository beneficiaireRepository;
	@Autowired
	BeneficiaireConvert beneficiaireConvert;
	@Autowired
	ConvertDate convertDate;
	
	
	


	@Override
	public List<BeneficiaireTDO> SearchBeneficiaireTDOByIdDreams(String idDreams,String organisation) {
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
		beneficiaires = beneficiaireRepository.findByOrganisationUidAndDreamsIdContaining(organisation, idDreams);
		if(!beneficiaires.isEmpty()) {
			beneficiaireTDOs = beneficiaireConvert.getBeneficiaireTDOs(beneficiaires);
		}
		return beneficiaireTDOs;
	}

	@Override
	public Beneficiaire getOneBeneficiaireByIdDreams(String idDreams) {
		
		return beneficiaireRepository.findByDreamsId(idDreams);
	}

	@Override
	public Beneficiaire updateOneBeneficiaire(Beneficiaire beneficiaire) {
		beneficiaire.setDateUpdate(new Date());
		return beneficiaireRepository.save(beneficiaire);
	}

	@Override
	public Beneficiaire saveOneBeneficiaire(Beneficiaire beneficiaire) {
		Beneficiaire beneficiaire2 = beneficiaireRepository.findByDreamsId(beneficiaire.getId_dreams());
		if(beneficiaire2 != null) {
			return null;
		}
		beneficiaire = beneficiaireRepository.save(beneficiaire);
		return beneficiaire;
	}

	@Override
	public Beneficiaire convertBeneficiaireTDO(BeneficiaireTDO beneficiaireTDO) {
		
		return beneficiaireConvert.saveBeneficiaire(beneficiaireTDO);
	}

	@Override
	public Beneficiaire getOneBeneficiaireByUid(String uid) {
		
		return beneficiaireRepository.findByUid(uid);
	}

	@Override
	public ResultatRequete saveBeneficiaireTDO(BeneficiaireTDO beneficiaireTDO) {
		ResultatRequete resultatRequete = new ResultatRequete();
		Beneficiaire beneficiaire = convertBeneficiaireTDO(beneficiaireTDO);
		if(beneficiaire != null) {
			beneficiaire = saveOneBeneficiaire(beneficiaire);
		}
		if(beneficiaire != null) {
			resultatRequete.setStatus("OK");
			resultatRequete.setId(beneficiaire.getUid());
			resultatRequete.setImporte(1);
		}else {
			resultatRequete.setStatus("fail");
			resultatRequete.setIgnore(1);
		}
		
		
		return resultatRequete;
	}

	@Override
	public List<BeneficiaireTDO> getBeneficiairePeriode(List<String> organisation, String debut, String fin) {
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
		Date dateDebut = convertDate.getDateParse(debut);
		Date dateFin = convertDate.getDateParse(fin);
		beneficiaires = beneficiaireRepository.findAllByOrganisationUidInAndDateEnrolementGreaterThanEqualAndDateEnrolementLessThanEqual(organisation, dateDebut,dateFin);
		if(!beneficiaires.isEmpty()) {
			beneficiaireTDOs = beneficiaireConvert.getBeneficiaireTDOs(beneficiaires);
		}
		return beneficiaireTDOs;
	}

	@Override
	public List<BeneficiaireTDO> getBeneficiairePreview(List<String> organisation, String fin) {
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
		Date dateFin = convertDate.getDateParse(fin);
		beneficiaires = beneficiaireRepository.findAllByOrganisationUidInAndDateEnrolementLessThanEqual(organisation,dateFin);
		if(!beneficiaires.isEmpty()) {
			beneficiaireTDOs = beneficiaireConvert.getBeneficiaireTDOs(beneficiaires);
		}
		return beneficiaireTDOs;
	}



}
