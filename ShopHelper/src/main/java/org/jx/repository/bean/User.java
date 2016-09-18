package org.jx.repository.bean;

public class User{
		private int id;
		private String name;
		private String password;
		private String createDateStr;
		private String mobile;
		
		public User() {
		}
		
		public User(int id) {
			this.id = id;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
		public void setCreateDateStr(String createDateStr) {
			this.createDateStr = createDateStr;
		}
		public String getCreateDateStr() {
			return createDateStr;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", password="
					+ password + ", createDateStr=" + createDateStr
					+ ", mobile=" + mobile + "]";
		}
	}