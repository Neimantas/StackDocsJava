package Models.Business;

public class CrudUpdate {

	public String tableName;
	public String changeValueOfColum;
	public String changeValueTO;
	public String conditionColumName;
	public String conditionChangeWhereValueIsEqual;
	public boolean whereUsed;

	public boolean getIsWhereUsed() {

		if (conditionColumName == null || conditionColumName.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
