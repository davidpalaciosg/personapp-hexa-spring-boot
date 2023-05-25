package co.edu.javeriana.as.personapp.domain;

public enum Gender {
	MALE, FEMALE, OTHER;

	public static Gender defineSex(String sex)
	{
		if(sex=="M")
			return MALE;
		else if (sex=="F")
			return FEMALE;
		else
			return OTHER;
	}
}
