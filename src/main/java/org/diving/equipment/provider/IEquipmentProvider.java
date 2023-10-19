package org.diving.equipment.provider;

import java.util.List;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.equipment.EquipmentNonDispo;
import org.diving.equipment.api.vo.equipment.EquipmentUtilisateur;

public interface IEquipmentProvider {
	
	public List<Equipment> getAllEquipment();
	
	public Equipment getEquipmentByTagEquipement(String tag);
	
	public Equipment getEquipmentById(Integer id);
	
	public List<EquipmentUtilisateur> getEquipmentByTagUtilisateur(String tag);
	
	public List<Equipment> getEquipmentAvailable();
	
	public List<EquipmentNonDispo> getEquipmentBorrowed();
		
	public void createEquipment(Equipment e);
	
	public void deleteEquipment(Equipment e);
	
}
