package com.gbis.Bank.Enums;

public enum CurrencyEnum {
	USD, EUR;

	public static int nb(CurrencyEnum type) {
		int nb;
		switch (type) {
		case EUR:
			nb = 1;
			break;
		case USD:
			nb = 2;
			break;
		default:
			nb = 1;
			break;
		}
		return nb;

	}

	public static CurrencyEnum type(int nb) {
		CurrencyEnum curEnum;
		switch (nb) {
		case 1:
			curEnum = CurrencyEnum.EUR;
			break;
		case 2:
			curEnum = CurrencyEnum.USD;
			break;
		default:
			curEnum = CurrencyEnum.EUR;
			break;
		}
		return curEnum;

	}

}
