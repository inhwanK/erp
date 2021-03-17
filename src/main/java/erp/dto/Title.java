package erp.dto;

public class Title {
	private int tNo;
	private String tName;

	public Title() {
		// TODO Auto-generated constructor stub
	}

	public Title(int tNo) {
		this.tNo = tNo;
	}

	public Title(int tNo, String tName) {
		this.tNo = tNo;
		this.tName = tName;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (tNo != other.tNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", tName, tNo);
	}

}
