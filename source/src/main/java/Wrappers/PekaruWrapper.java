package Wrappers;

import java.util.ArrayList;
import java.util.List;

import com.example.RaktarkezeloProgram.domain.Pekaru;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PekaruWrapper {
 
	private List<Pekaru> list = new ArrayList<>();
}
