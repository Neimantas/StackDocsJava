package Services.Impl;

import java.util.ArrayList;
import java.util.List;
import Models.Topic;
import Models.DAL.TopicsDAL;
import Models.DTO.TopicsDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;

public class FrontServiceImp implements IFrontService {

	@Override
	public TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId) {
		HigherServiceImpl hService = new HigherServiceImpl();
		TopicsDTO tInfoDTO = hService.getTopicInfoByTopicId(topicId);

		if (tInfoDTO.isSuccess()) {
			List<TopicsDAL> topicsListas = tInfoDTO.getTopics();
			if (topicsListas.size() == 0) {
				return new TopicsInfoFrontDTO(false, "Tuscias topic", null);
			} else {
				List<Topic> topicsInfoFront = new ArrayList<Topic>();
				for (TopicsDAL t : topicsListas) {
					Topic topic = new Topic();
					topic.set_TopicId(t.topicId);
					topic.set_LanguageId(t.languageId);
					topic.set_TopicTitle(t.title);
					topic.set_IntroductionHtml(t.introductionHtml);
					topic.set_SyntaxHtml(t.syntaxHtml);
					topic.set_ParametersHtml(t.parametersHtml);
					topic.set_RemarksHtml(t.remarksHtml);

					topicsInfoFront.add(topic);
				}
				return new TopicsInfoFrontDTO(true, "success", topicsInfoFront);
			}
		}
		return new TopicsInfoFrontDTO(false, tInfoDTO.getMessage(), null);
	}

	@Override
	public TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord) {
		HigherServiceImpl hService = new HigherServiceImpl();
		TopicsDTO tDTO = hService.getTopicsByLanguageId(languageId);
		String topicWord2 = topicWord.toLowerCase();

		if (tDTO.isSuccess()) {
			List<TopicsDAL> topicsListas = tDTO.getTopics();
			if (topicsListas.size() == 0) {
				return new TopicsFrontDTO(false, "Nerastas topic", null);
			} else {
				if (topicWord != "") {
					List<Topic> topicsFront = new ArrayList<Topic>();
					for (TopicsDAL t : topicsListas) {
						Topic topic = new Topic();
						topic.set_TopicId(t.topicId);
						topic.set_LanguageId(t.languageId);
						topic.set_TopicTitle(t.title);
						String textCheck = t.title.toLowerCase();
						if (textCheck.contains(topicWord2)) {
							topicsFront.add(topic);
						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					}
				} else {
					List<Topic> topicsFront = new ArrayList<Topic>();
					for (TopicsDAL t : topicsListas) {
						Topic topic = new Topic();
						topic.set_TopicId(t.topicId);
						topic.set_LanguageId(t.languageId);
						topicsFront.add(topic);
					}
					return new TopicsFrontDTO(true, "success", topicsFront);
				}
			}
		}
		return new TopicsFrontDTO(false, tDTO.getMessage(), null);
	}

