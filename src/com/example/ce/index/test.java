package com.example.ce.index;

import java.util.ArrayList;

public class test {
	private ArrayList<ALcontent> test1;

	public ArrayList<ALcontent> gettest1() {
		return test1;
	}

	public int getALsize() {
		return test1.size();
	}

	class ALcontent {
		String title;
		String content;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}
}
