package zw.co.getsol.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ApiException {
    private String errorMessage;
    private Integer statusCode;
    private LocalDateTime time;
}
