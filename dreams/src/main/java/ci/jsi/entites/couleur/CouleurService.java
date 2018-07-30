package ci.jsi.entites.couleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouleurService implements Icouleur {
	
	
	@Autowired
	CouleurConvert couleurConvert;
	@Autowired
	CouleurRepository couleurRepository;

	@Override
	public List<CouleurTDO> getAllCouleurTDO() {
		return couleurConvert.getCouleurTDOs(couleurRepository.findAll());
	}

	@Override
	public CouleurTDO getOneCouleurTDO(String id) {
		Couleur couleur = new Couleur();
		CouleurTDO couleurTDO = new CouleurTDO();
		couleur = couleurRepository.getOneCouleur(id);
		if(couleur != null) {
			couleurTDO = couleurConvert.getCouleurTDO(couleur);
		}
		return couleurTDO;
	}

	@Override
	public String saveCouleurTDO(CouleurTDO couleurTDO) {
		Couleur couleur = new Couleur();
		couleur = couleurConvert.saveCouleurTDO(couleurTDO);
		couleur = couleurRepository.save(couleur);
		return "Saved id: "+couleur.getUid();
	}

	@Override
	public String updateCouleurTDO(String id, CouleurTDO couleurTDO) {
		Couleur couleur = new Couleur();
		couleur = couleurRepository.getOneCouleur(id);
		if(couleur != null) {
			couleur = couleurConvert.updateCouleurTDO(couleur, couleurTDO);
			couleurTDO = couleurConvert.getCouleurTDO(couleur);
		}
		return "Updated id: "+couleur.getUid();
	}

	@Override
	public String deleteCouleurTDO(String id) {
		Couleur couleur = new Couleur();
		couleur = couleurRepository.getOneCouleur(id);
		if(couleur != null) {
			couleurRepository.delete(couleur);
		}
		return "Success deleted";
	}

}
