package Wrappers;

import java.util.ArrayList;
import java.util.List;

import com.example.RaktarkezeloProgram.domain.GyumolcsEsZoldseg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GyumolcsEsZoldsegWrapper {

	private List<GyumolcsEsZoldseg> list = new ArrayList<>();
}
