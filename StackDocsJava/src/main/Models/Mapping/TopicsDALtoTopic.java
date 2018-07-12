package Models.Mapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import Models.Topic;
import Models.DAL.TopicsDAL;

public class TopicsDALtoTopic {
	public Topic topicInfoDaltoTopicInfo(TopicsDAL topicDal, Topic topic) {
		ModelMapper mapperTopicsInfo = new ModelMapper();
		Converter<TopicsDAL, Topic> topicsInfoConverter = new Converter<TopicsDAL, Topic>() {
			public Topic convert(MappingContext<TopicsDAL, Topic> context) {
				Topic topic = context.getDestination();
				TopicsDAL topicDal = context.getSource();
				topic._TopicId = topicDal.topicId;
				topic._LanguageId = topicDal.languageId;
				topic._TopicTitle = topicDal.title;
				if (!topicDal.introductionHtml.equals("") || !topicDal.introductionHtml.equals(null)) {
					topic._IntroductionHtml = topicDal.introductionHtml;
				}
				if (topicDal.introductionHtml.equals("") || topicDal.introductionHtml.equals(null)) {
					topic._IntroductionHtml = "<p>No Introduction info</p>";
				}
				if (!topicDal.syntaxHtml.equals("") || !topicDal.syntaxHtml.equals(null)) {
					topic._SyntaxHtml = topicDal.syntaxHtml;
				}
				if (topicDal.syntaxHtml.equals("") || topicDal.syntaxHtml.equals(null)) {
					topic._SyntaxHtml = "<p>No Syntax info</p>";
				}
				if (!topicDal.parametersHtml.equals("") || !topicDal.parametersHtml.equals(null)) {
					topic._ParametersHtml = topicDal.parametersHtml;
				}
				if (topicDal.parametersHtml.equals("") || topicDal.parametersHtml.equals(null)) {
					topic._ParametersHtml = "<p>No Parameters info</p>";
				}
				if (!topicDal.remarksHtml.equals("") || !topicDal.remarksHtml.equals(null)) {
					topic._RemarksHtml = topicDal.remarksHtml;
				}
				if (topicDal.remarksHtml.equals("") || topicDal.remarksHtml.equals(null)) {
					topic._RemarksHtml = "<p>No Remarks info</p>";
				}
				return topic;
			}
		};
		mapperTopicsInfo.addConverter(topicsInfoConverter);
		mapperTopicsInfo.map(topicDal, topic);
		
		return topic;
		
	}
	
	public Topic topicsTagsDaltoTopicTags(TopicsDAL topicDal, Topic topic) {
		ModelMapper mapperTopics = new ModelMapper();
		Converter<TopicsDAL, Topic> topicsListConverter = new Converter<TopicsDAL, Topic>() {
			public Topic convert(MappingContext<TopicsDAL, Topic> context) {
				Topic topic = context.getDestination();
				TopicsDAL topicDal = context.getSource();
				topic._TopicId = topicDal.topicId;
				topic._LanguageId = topicDal.languageId;
				topic._TopicTitle = topicDal.title;
				return topic;
			};

		};
		mapperTopics.addConverter(topicsListConverter);
		mapperTopics.map(topicDal, topic);
		return topic;
		
	}

}
