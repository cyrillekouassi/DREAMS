package ci.jsi.entites.bareme;

import java.util.List;

public interface Ibareme {

	public List<BaremeTDO> getAllBaremeTDO();
	public BaremeTDO getOneBaremeTDO(String id);
	public String saveBaremeTDO(BaremeTDO baremeTDO);
	public String updateBaremeTDO(String id,BaremeTDO baremeTDO);
	public String deleteBaremeTDO(String id);
}
