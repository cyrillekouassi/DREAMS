package ci.jsi.entites.couleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.jsi.initialisation.UidEntitie;

@Service
public class CouleurConvertEntitie {

	@Autowired
	CouleurRepository couleurRepository;
	

	public UidEntitie getCouleur(Couleur couleur) {
		UidEntitie uidEntitie = new UidEntitie();
		uidEntitie.setId(couleur.getUid());
		return uidEntitie;
	}

	public Couleur setCouleur(UidEntitie uidEntitie) {
		Couleur couleur = new Couleur();
		if(uidEntitie.getId() != null)
			couleur = couleurRepository.getOneCouleur(uidEntitie.getId());
		return couleur;
	}

	public List<UidEntitie> getCouleurs(List<Couleur> couleurs) {
		List<UidEntitie> uidEntities = new ArrayList<UidEntitie>();

		for (int i = 0; i < couleurs.size(); i++) {
			uidEntities.add(getCouleur(couleurs.get(i)));
		}
		return uidEntities;
	}

	public List<Couleur> setCouleurs(List<UidEntitie> uidEntities) {
		List<Couleur> couleurs = new ArrayList<Couleur>();
		for (int i = 0; i < uidEntities.size(); i++) {
			Couleur couleur = new Couleur();
			couleur = setCouleur(uidEntities.get(i));
			if(couleur != null)
				couleurs.add(couleur);
		}
		return couleurs;
	}
}
