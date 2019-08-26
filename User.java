
public class User {
	private String name;
	private int age;
	private String gender;
	private String account;
	private String password;
	private int isCommonUser;
	public User(String name, int age, 
			String gender, String account, String password, int isCommonUser) throws Exception {
		setName(name);
		setGender(gender);
		setAccount(account);
		setPassword(password);
		setAge(age);
		setCommonUser(isCommonUser);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws Exception {
		if(name.length() > 20)
			throw new Exception("当前昵称过长");
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) throws Exception {
		if(age<0 || age > 200)
			throw new Exception("年龄有误！！！"); 
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) throws Exception {
		String regex = "[1-9][0-9]{5,10}";
		if(!account.matches(regex))
			throw new Exception("账号格式错误");
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws Exception {
		if(password.length()>16)
			throw new Exception("密码过长");
		this.password = password;
	}
	public int isCommonUser() {
		return isCommonUser;
	}
	public void setCommonUser(int isCommonUser) {
		this.isCommonUser = isCommonUser;
	}
	@Override
	public String toString() {
		return name + "::" + age + "::" + gender + "::" + account + "::" + password;
	}
}
