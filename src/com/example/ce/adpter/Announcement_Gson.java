package com.example.ce.adpter;

import java.util.ArrayList;

public class Announcement_Gson {
	private ArrayList<ALcontent> test1;

	public ArrayList<ALcontent> gettest1() {
		return test1;
	}

	public int getALsize() {
		return test1.size();
	}

	class ALcontent {
		String title;
		String phone;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

	}
}
