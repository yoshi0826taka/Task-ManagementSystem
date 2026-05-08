package dto;

/**
 * ユーザーの記録情報エンティティ
 */
public class Record {
	private int userId;				//ユーザID
	private String userName;		//ユーザ名
	private int redordYear;			// 記録年
	private int reccordMonth; 		// 記録月
	private int recordDay; 			// 記録日
	private double weight;			// 体重
	
  // コンストラクタ
	public Record(int userId, String userName, int redordYear, int reccordMonth, int recordDay, double weight) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.redordYear = redordYear;
		this.reccordMonth = reccordMonth;
		this.recordDay = recordDay;
		this.weight = weight;
	}
	
	// デフォルトコンストラクタ
	public Record() {
		super();
	}

// getter/setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRedordYear() {
		return redordYear;
	}

	public void setRedordYear(int redordYear) {
		this.redordYear = redordYear;
	}

	public int getReccordMonth() {
		return reccordMonth;
	}

	public void setReccordMonth(int reccordMonth) {
		this.reccordMonth = reccordMonth;
	}

	public int getRecordDay() {
		return recordDay;
	}

	public void setRecordDay(int recordDay) {
		this.recordDay = recordDay;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
