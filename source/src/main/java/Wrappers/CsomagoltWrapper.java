package Wrappers;

import java.util.ArrayList;
import java.util.List;

import com.example.RaktarkezeloProgram.domain.Csomagolt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsomagoltWrapper {

	private List<Csomagolt> list = new ArrayList<>();

}
