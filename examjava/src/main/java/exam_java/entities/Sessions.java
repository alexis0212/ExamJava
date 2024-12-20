package exam_java.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sessions {
    private int id; 
    private LocalDate date; 
    private LocalTime heureDebut; 
    private LocalTime heureFin; 
    private String salle; 
    private boolean enLigne; 
    private int coursId; 
}
