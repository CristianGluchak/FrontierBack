package br.com.frontier.rh_simplificado.salarySheet.domain.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculatorUtils {

    public static BigDecimal calculateINSS(BigDecimal salary)  {
        if (salary == null) return BigDecimal.ZERO;

        BigDecimal inss;
        if (salary.compareTo(new BigDecimal("1412.00")) <= 0) {
            inss = salary.multiply(new BigDecimal("0.075"));
        } else if (salary.compareTo(new BigDecimal("2666.68")) <= 0) {
            inss = salary.multiply(new BigDecimal("0.09")).subtract(new BigDecimal("21.18"));
        } else if (salary.compareTo(new BigDecimal("4000.03")) <= 0) {
            inss = salary.multiply(new BigDecimal("0.12")).subtract(new BigDecimal("101.18"));
        } else if (salary.compareTo(new BigDecimal("7786.02")) <= 0) {
            inss = salary.multiply(new BigDecimal("0.14")).subtract(new BigDecimal("181.18"));
        } else {
            inss = new BigDecimal("908.86");
        }

        return inss.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateIRRF(BigDecimal salary, BigDecimal inss) {
        if (salary == null || inss == null) return BigDecimal.ZERO;

        BigDecimal base = salary.subtract(inss);
        BigDecimal irrf;

        if (base.compareTo(new BigDecimal("2112.00")) <= 0) {
            irrf = BigDecimal.ZERO;
        } else if (base.compareTo(new BigDecimal("2826.65")) <= 0) {
            irrf = base.multiply(new BigDecimal("0.075")).subtract(new BigDecimal("158.40"));
        } else if (base.compareTo(new BigDecimal("3751.05")) <= 0) {
            irrf = base.multiply(new BigDecimal("0.15")).subtract(new BigDecimal("370.40"));
        } else if (base.compareTo(new BigDecimal("4664.68")) <= 0) {
            irrf = base.multiply(new BigDecimal("0.225")).subtract(new BigDecimal("651.73"));
        } else {
            irrf = base.multiply(new BigDecimal("0.275")).subtract(new BigDecimal("884.96"));
        }

        return irrf.max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }
}