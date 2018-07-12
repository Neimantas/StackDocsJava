package Models.Mapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import Models.Business.Language;
import Models.DAL.LanguageTagsDAL;

public class LanguageTagsDALtoLanguage {
	public Language languageDaltoLanguage(LanguageTagsDAL languageTagDto, Language languageTag) {
		ModelMapper mapperLanguageTag = new ModelMapper();
		Converter<LanguageTagsDAL, Language> languageTagConverter = new Converter<LanguageTagsDAL, Language>() {
			public Language convert(MappingContext<LanguageTagsDAL, Language> context) {
				Language language = context.getDestination();
				LanguageTagsDAL languageDal = context.getSource();
				language.id = languageDal.languageId;
				language.title = languageDal.title;
				language.tag = languageDal.tag;
				return language;
			};
		};
		mapperLanguageTag.addConverter(languageTagConverter);
		// LanguageTagsDALtoLanguage mapll=new LanguageTagsDALtoLanguage();
		mapperLanguageTag.map(languageTagDto, languageTag);
		return languageTag;

	}

}
