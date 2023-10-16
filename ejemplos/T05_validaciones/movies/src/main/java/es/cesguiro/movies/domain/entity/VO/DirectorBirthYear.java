package es.cesguiro.movies.domain.entity.VO;

import es.cesguiro.movies.exception.BusinessException;

import java.util.Calendar;

public class DirectorBirthYear {

    private int year;

    public DirectorBirthYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (year < 1908 || year > currentYear) {
            throw new BusinessException("Año de nacimiento inválido. No puede ser anterior a 1908 ni posterior a la fecha actual");
        }

        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
