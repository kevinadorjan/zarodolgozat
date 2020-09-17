package Wrappers;

import java.util.ArrayList;
import java.util.List;

import com.example.RaktarkezeloProgram.domain.Hus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HusWrapper {

	private List<Hus> list = new ArrayList<>();
}
