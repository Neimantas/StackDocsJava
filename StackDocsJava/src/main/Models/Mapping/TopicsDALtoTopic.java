package Models.Mapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import Models.Business.Topic;
import Models.DAL.TopicsDAL;

public class TopicsDALtoTopic {
	public Topic topicInfoDaltoTopicInfo(TopicsDAL topicDal, Topic topic) {
		ModelMapper mapperTopicsInfo = new ModelMapper();
		Converter<TopicsDAL, Topic> topicsInfoConverter = new Converter<TopicsDAL, Topic>() {
			public Topic convert(MappingContext<TopicsDAL, Topic> context) {
				Topic topic = context.getDestination();
				TopicsDAL topicDal = context.getSource();
				topic.topicId = topicDal.topicId;
				topic.languageId = topicDal.languageId;
				topic.topicTitle = topicDal.title;
				if (!topicDal.introductionHtml.equals("") || !topicDal.introductionHtml.equals(null)) {
					topic.introductionHtml = topicDal.introductionHtml;
				}
				if (topicDal.introductionHtml.equals("") || topicDal.introductionHtml.equals(null)) {
					topic.introductionHtml = "<p>No Introduction info</p>";
				}
				if (!topicDal.syntaxHtml.equals("") || !topicDal.syntaxHtml.equals(null)) {
					topic.syntaxHtml = topicDal.syntaxHtml;
				}
				if (topicDal.syntaxHtml.equals("") || topicDal.syntaxHtml.equals(null)) {
					topic.syntaxHtml = "<p>No Syntax info</p>";
				}
				if (!topicDal.parametersHtml.equals("") || !topicDal.parametersHtml.equals(null)) {
					topic.parametersHtml = topicDal.parametersHtml;
				}
				if (topicDal.parametersHtml.equals("") || topicDal.parametersHtml.equals(null)) {
					topic.parametersHtml = "<p>No Parameters info</p>";
				}
				if (!topicDal.remarksHtml.equals("") || !topicDal.remarksHtml.equals(null)) {
					topic.remarksHtml = topicDal.remarksHtml;
				}
				if (topicDal.remarksHtml.equals("") || topicDal.remarksHtml.equals(null)) {
					topic.remarksHtml = "<p>No Remarks info</p>";
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
				topic.topicId = topicDal.topicId;
				topic.languageId = topicDal.languageId;
				topic.topicTitle = topicDal.title;
				return topic;
			};

		};
		mapperTopics.addConverter(topicsListConverter);
		mapperTopics.map(topicDal, topic);
		return topic;

	}

}
