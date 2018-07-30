package ci.jsi.entites.couleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.entites.bareme.BaremeConvertEntitie;
import ci.jsi.initialisation.Uid;

@Service
public class CouleurConvert {
	@Autowired
	BaremeConvertEntitie baremeConvertEntitie;
	@Autowired
	Uid uid;

	public CouleurTDO getCouleurTDO(Couleur couleur) {
		CouleurTDO couleurTDO = new CouleurTDO();
		couleurTDO.setId(couleur.getUid());
		couleurTDO.setName(couleur.getName());
		couleurTDO.setCode(couleur.getCode());
		couleurTDO.setHtmlCouleur(couleur.getHtmlCouleur());
		couleurTDO.setBaremes(baremeConvertEntitie.getBaremes(couleur.getBaremes()));

		return couleurTDO;

	}

	public List<CouleurTDO> getCouleurTDOs(List<Couleur> couleurs) {

		List<CouleurTDO> couleurTDOs = new ArrayList<CouleurTDO>();
		for (int i = 0; i < couleurs.size(); i++) {
			couleurTDOs.add(getCouleurTDO(couleurs.get(i)));
		}
		return couleurTDOs;
	}

	public Couleur saveCouleurTDO(CouleurTDO couleurTDO) {
		Couleur couleur = new Couleur();
		couleur.setUid(uid.getUid());
		couleur.setName(couleurTDO.getName());
		couleur.setCode(couleurTDO.getCode());
		couleur.setHtmlCouleur(couleurTDO.getHtmlCouleur());
		couleur.setBaremes(baremeConvertEntitie.setBaremes(couleurTDO.getBaremes()));
		return couleur;
	}

	public Couleur updateCouleurTDO(Couleur couleur, CouleurTDO couleurTDO) {

		if (couleurTDO.getName() != null)
			couleur.setName(couleurTDO.getName());
		if (couleurTDO.getCode() != null)
			couleur.setCode(couleurTDO.getCode());
		if (couleurTDO.getHtmlCouleur() != null)
			couleur.setHtmlCouleur(couleurTDO.getHtmlCouleur());
		if(couleurTDO.getBaremes().size() != 0)
			couleur.setBaremes(baremeConvertEntitie.setBaremes(couleurTDO.getBaremes()));
		return couleur;
	}
}
