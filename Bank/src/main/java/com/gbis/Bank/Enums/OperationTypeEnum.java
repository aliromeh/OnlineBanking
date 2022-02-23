package com.gbis.Bank.Enums;

public enum OperationTypeEnum {
	WITHDRAWAL, DEPOSIT;

	public static int nb(OperationTypeEnum type) {
		int nb;
		switch (type) {
		case WITHDRAWAL:
			nb = 1;
			break;
		case DEPOSIT:
			nb = 2;
			break;
		default:
			nb = 2;
			break;
		}
		return nb;

	}
	
	
	public static OperationTypeEnum type(int nb) {
		OperationTypeEnum enumOpe;
		switch (nb) {
		case 1:
			enumOpe = OperationTypeEnum.WITHDRAWAL;
			break;
		case 2:
			enumOpe = OperationTypeEnum.DEPOSIT;
			break;
		default:
			enumOpe = OperationTypeEnum.DEPOSIT;
			break;
		}
		return enumOpe;

	}
}
