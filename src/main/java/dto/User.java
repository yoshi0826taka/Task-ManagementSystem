package dto;

/**
 * ユーザーの基本情報エンティティ
 */
public class User {
	private int userId;		// ユーザーID
	private String lName; // 苗字
	private String fName; // 名前
	private String gender; // 性別
	private int birthYear; // 誕生年
	private int birthMonth;	// 誕生月
	private int birthDay; // 誕生日
	
	// コンストラクタ
	public User(int userId, String lName, String fName, String gender, int birthYear, int birthMonth, int birthDay) {
		super();
		this.userId = userId;
		this.lName = lName;
		this.fName = fName;
		this.gender = gender;
		this.birthYear = birthYear;
		this.birthMonth = birthMonth;
		this.birthDay = birthDay;
	}
	
	// デフォルトコンストラクタ
	public User() {
		super();
	}

	// getter/setter
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

}
