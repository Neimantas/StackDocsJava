package Models.Mapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import Models.Business.Example;
import Models.DAL.ExamplesDAL;

public class ExamplesDALtoExample {

	public Example dalToExamples(ExamplesDAL dal, Example example) {
		ModelMapper mapperExamples = new ModelMapper();
		Converter<ExamplesDAL, Example> examplesConverter = new Converter<ExamplesDAL, Example>() {
			public Example convert(MappingContext<ExamplesDAL, Example> context) {
				Example exampl = context.getDestination();
				ExamplesDAL exampleDal = context.getSource();
				exampl.topicId = exampleDal.topicId;
				exampl.exampleId = exampleDal.exampleId;
				exampl.title = exampleDal.title;
				exampl.bodyHtml = exampleDal.bodyHtml;
				return exampl;
			}
		};
		mapperExamples.addConverter(examplesConverter);
		mapperExamples.map(dal, example);
		return example;

	}

}
