package com.formsub.exam;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {

	public static List<EmployeeVO> listEmpvo = new ArrayList<EmployeeVO>();


	EmployeeDB() {
	}

	public static void addEmployee(EmployeeVO emp) {
		listEmpvo.add(emp);
	}

	public static EmployeeVO getEmployeeById(int id) {
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeId(id);
		if (listEmpvo!=null && listEmpvo.contains(employeeVO)) {
			for (EmployeeVO employee : listEmpvo) {
				if (employee.equals(employee)) {
					return employee;
				}
			}
		}
		return null;
	}
}
