package com.sltunion.cloudy.persistent;

import com.persistent.generator.GeneratorBuiler;
import com.persistent.generator.GeneratorMapper;
import com.persistent.generator.GeneratorModel;
import com.persistent.generator.GeneratorXml;

public class GeneratorMain {  

	public static void main(String[] args) {
		GeneratorBuiler.getInstance().generatFile(new GeneratorModel());
		GeneratorBuiler.getInstance().generatFile(new GeneratorMapper());
		GeneratorBuiler.getInstance().generatFile(new GeneratorXml());
//		GeneratorBuiler.generatFile(new GeneratorJunit());
	}

}
