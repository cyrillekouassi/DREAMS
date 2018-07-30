package ci.jsi.entites.couleur;

import java.util.List;

public interface Icouleur {

	public List<CouleurTDO> getAllCouleurTDO();
	public CouleurTDO getOneCouleurTDO(String id);
	public String saveCouleurTDO(CouleurTDO couleurTDO);
	public String updateCouleurTDO(String id,CouleurTDO couleurTDO);
	public String deleteCouleurTDO(String id);
}
