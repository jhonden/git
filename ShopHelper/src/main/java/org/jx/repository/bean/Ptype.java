package org.jx.repository.bean;

public class Ptype{
		private int id;
		private String name;
		private String createDateStr;
		private String description;
		
		public Ptype() {
		}
		
		public Ptype(int id) {
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
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setCreateDateStr(String createDateStr) {
			this.createDateStr = createDateStr;
		}
		public String getCreateDateStr() {
			return createDateStr;
		}
		@Override
		public String toString() {
			return "Ptype [id=" + id + ", name=" + name + ", createDateStr="
					+ createDateStr + ", description=" + description + "]";
		}
		
	}