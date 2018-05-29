package Models;

public class CrudUpdate {
	public String tableName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getChangeValueOfColum() {
		return changeValueOfColum;
	}
	public void setChangeValueOfColum(String changeValueOfColum) {
		this.changeValueOfColum = changeValueOfColum;
	}
	public String getChangeValueTO() {
		return changeValueTO;
	}
	public void setChangeValueTO(String changeValueTO) {
		this.changeValueTO = changeValueTO;
	}
	public String getConditionColumName() {
		return conditionColumName;
	}
	public void setConditionColumName(String conditionColumName) {
		this.conditionColumName = conditionColumName;
	}
	public String getConditionChangeWhereValueIsEqual() {
		return conditionChangeWhereValueIsEqual;
	}
	public void setConditionChangeWhereValueIsEqual(String conditionChangeWhereValueIsEqual) {
		this.conditionChangeWhereValueIsEqual = conditionChangeWhereValueIsEqual;
	}
	public boolean isWhereUsed() {
		return whereUsed;
	}
	public void setWhereUsed(boolean whereUsed) {
		this.whereUsed = whereUsed;
	}
	public String changeValueOfColum; 
	public String changeValueTO; 
	public String conditionColumName;
	public String conditionChangeWhereValueIsEqual; 
	public boolean whereUsed;
}
