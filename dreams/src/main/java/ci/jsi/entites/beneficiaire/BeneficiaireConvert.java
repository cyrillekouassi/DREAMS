package ci.jsi.entites.beneficiaire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.organisation.OrganisationConvertEntitie;
import ci.jsi.initialisation.ConvertDate;
import ci.jsi.initialisation.Uid;

@Service
public class BeneficiaireConvert {

	@Autowired
	ConvertDate convertDate;
	@Autowired
	Uid uid;

	@Autowired
	InstanceBeneficiaireConvert instanceBeneficiaireConvert;
	@Autowired
	private OrganisationConvertEntitie organisationConvertEntitie;

	public BeneficiaireTDO getBeneficiaireTDO(Beneficiaire beneficiaire) {
		BeneficiaireTDO beneficiaireTDO = new BeneficiaireTDO();
		beneficiaireTDO.setId(beneficiaire.getUid());
		beneficiaireTDO.setName(beneficiaire.getName());
		beneficiaireTDO.setFirstName(beneficiaire.getFirstName());
		beneficiaireTDO.setId_dreams(beneficiaire.getId_dreams());
		beneficiaireTDO.setCode(beneficiaire.getCode());
		beneficiaireTDO.setTelephone(beneficiaire.getTelephone());
		if (beneficiaire.getDateNaissance() != null) {
			beneficiaireTDO.setDateNaissance(convertDate.getDateString(beneficiaire.getDateNaissance()));
		}
		if (beneficiaire.getDateEnrolement() != null) {
			beneficiaireTDO.setDateEnrolement(convertDate.getDateString(beneficiaire.getDateEnrolement()));
		}

		beneficiaireTDO.setAgeEnrolement(Integer.toString(beneficiaire.getAgeEnrolement()));

		if (beneficiaire.getDateCreation() != null) {
			beneficiaireTDO.setDateCreation(convertDate.getDateString(beneficiaire.getDateCreation()));
		}
		if (beneficiaire.getDateUpdate() != null) {
			beneficiaireTDO.setDateUpdate(convertDate.getDateString(beneficiaire.getDateUpdate()));
		}
		beneficiaireTDO.setInstance(
				instanceBeneficiaireConvert.getInstanceBeneficiaires(beneficiaire.getInstanceBeneficiaires()));
		beneficiaireTDO.setOrganisation(organisationConvertEntitie.getOrganisation(beneficiaire.getOrganisation()));

		return beneficiaireTDO;
	}

	public List<BeneficiaireTDO> getBeneficiaireTDOs(List<Beneficiaire> beneficiaires) {
		List<BeneficiaireTDO> beneficiaireTDOs = new ArrayList<BeneficiaireTDO>();
		for (int l = 0; l < beneficiaires.size(); l++) {
			beneficiaireTDOs.add(getBeneficiaireTDO(beneficiaires.get(l)));
		}
		return beneficiaireTDOs;
	}

	public Beneficiaire saveBeneficiaire(BeneficiaireTDO beneficiaireTDO) {
		Date laDate = null;
		Date laDateEnrol = null;
		Beneficiaire beneficiaire = new Beneficiaire();
		if (beneficiaireTDO.getId_dreams() == null)
			return null;
		if (beneficiaireTDO.getName() == null)
			return null;
		if (beneficiaireTDO.getFirstName() == null)
			return null;
		if (beneficiaireTDO.getOrganisation() == null) {
			return null;
		}
		if (beneficiaireTDO.getDateNaissance() != null) {

				laDate = convertDate.getDateParse(beneficiaireTDO.getDateNaissance());
				if (laDate == null) {
					return null;
				}
			
		}
		if (beneficiaireTDO.getDateEnrolement() != null) {
				laDateEnrol = convertDate.getDateParse(beneficiaireTDO.getDateEnrolement());
				if (laDateEnrol == null) {
					return null;
				}
			
		}

		beneficiaire.setUid(uid.getUid());
		beneficiaire.setName(beneficiaireTDO.getName());
		beneficiaire.setFirstName(beneficiaireTDO.getFirstName());
		beneficiaire.setId_dreams(beneficiaireTDO.getId_dreams());
		beneficiaire.setCode(beneficiaireTDO.getCode());
		beneficiaire.setTelephone(beneficiaireTDO.getTelephone());

		beneficiaire.setDateNaissance(laDate);
		beneficiaire.setDateEnrolement(laDateEnrol);
		beneficiaire.setAgeEnrolement(Integer.parseInt(beneficiaireTDO.getAgeEnrolement()));
		beneficiaire.setDateCreation(new Date());
		beneficiaire.setDateUpdate(new Date());
		beneficiaire.setOrganisation(organisationConvertEntitie.setOneOrganisation(beneficiaireTDO.getOrganisation()));
		if (beneficiaireTDO.getInstance() != null) {
			beneficiaire
					.setInstanceBeneficiaires(instanceBeneficiaireConvert.getInstances(beneficiaireTDO.getInstance()));
		}

		return beneficiaire;
	}

	public Beneficiaire UpdateBeneficiaireByTDO(Beneficiaire beneficiaire, BeneficiaireTDO beneficiaireTDO) {
		Date laDate = null;
		Date laDateEnrol = null;
		beneficiaire.setName(beneficiaireTDO.getName());
		beneficiaire.setFirstName(beneficiaireTDO.getFirstName());
		beneficiaire.setId_dreams(beneficiaireTDO.getId_dreams());
		beneficiaire.setCode(beneficiaireTDO.getCode());
		beneficiaire.setTelephone(beneficiaireTDO.getTelephone());
		if (beneficiaireTDO.getDateNaissance() != null) {

			
				laDate = convertDate.getDateParse(beneficiaireTDO.getDateNaissance());
				if (laDate == null) {
					return null;
				}
		}
		if (beneficiaireTDO.getDateEnrolement() != null) {
				laDateEnrol = convertDate.getDateParse(beneficiaireTDO.getDateNaissance());
				if (laDateEnrol == null) {
					return null;
				}
			
		}
		beneficiaire.setDateEnrolement(laDateEnrol);
		beneficiaire.setDateNaissance(laDate);
		beneficiaire.setAgeEnrolement(Integer.parseInt(beneficiaireTDO.getAgeEnrolement()));
		beneficiaire.setDateUpdate(new Date());

		beneficiaire.setOrganisation(organisationConvertEntitie.setOneOrganisation(beneficiaireTDO.getOrganisation()));

		beneficiaire.setInstanceBeneficiaires(instanceBeneficiaireConvert.getInstances(beneficiaireTDO.getInstance()));
		return beneficiaire;
	}

	public Beneficiaire UpdateBeneficiaire(Beneficiaire Ancbeneficiaire, Beneficiaire Newbeneficiaire) {
		Ancbeneficiaire.setAgeEnrolement(Newbeneficiaire.getAgeEnrolement());
		Ancbeneficiaire.setCode(Newbeneficiaire.getCode());
		Ancbeneficiaire.setDateEnrolement(Newbeneficiaire.getDateEnrolement());
		Ancbeneficiaire.setDateNaissance(Newbeneficiaire.getDateNaissance());
		Ancbeneficiaire.setDateUpdate(new Date());
		Ancbeneficiaire.setFirstName(Newbeneficiaire.getFirstName());
		Ancbeneficiaire.setName(Newbeneficiaire.getName());
		Ancbeneficiaire.setOrganisation(Newbeneficiaire.getOrganisation());
		Ancbeneficiaire.setTelephone(Newbeneficiaire.getTelephone());
		Ancbeneficiaire.getInstanceBeneficiaires().addAll(Newbeneficiaire.getInstanceBeneficiaires());
		return Ancbeneficiaire;
	}

}
