package Services.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import Models.Example;
import Models.Topic;
import Models.DAL.ExamplesDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.ExamplesDTO;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;
import Services.IHigherService;

@Singleton
public class FrontServiceImp implements IFrontService {
	
	IHigherService hService;
	
	public FrontServiceImp(HigherServiceImpl hServiceImpl) {
		hService = hServiceImpl;
	}
	

	@Override
	public TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId) {
//		HigherServiceImpl hService = new HigherServiceImpl();
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
					if (!t.introductionHtml.equals("") || !t.introductionHtml.equals(null)) {
						topic.set_IntroductionHtml(t.introductionHtml);
					}
					if (t.introductionHtml.equals("") || t.introductionHtml.equals(null)) {
						topic.set_IntroductionHtml("<p>No Introduction info</p>");
					}
					if (!t.syntaxHtml.equals("") || !t.syntaxHtml.equals(null)) {
						topic.set_SyntaxHtml(t.syntaxHtml);
					}
					if (t.syntaxHtml.equals("") || t.syntaxHtml.equals(null)) {
						topic.set_SyntaxHtml("<p>No Syntax info</p>");
					}
					if (!t.parametersHtml.equals("") || !t.parametersHtml.equals(null)) {
						topic.set_ParametersHtml(t.parametersHtml);
					}
					if (t.parametersHtml.equals("") || t.parametersHtml.equals(null)) {
						topic.set_ParametersHtml("<p>No Parameters info</p>");
					}
					if (!t.remarksHtml.equals("") || !t.remarksHtml.equals(null)) {
						topic.set_RemarksHtml(t.remarksHtml);
					}
					if (t.remarksHtml.equals("") || t.remarksHtml.equals(null)) {
						topic.set_RemarksHtml("<p>No Remarks info</p>");
					}
					topicsInfoFront.add(topic);
				}
				return new TopicsInfoFrontDTO(true, "success", topicsInfoFront);
			}
		}
		return new TopicsInfoFrontDTO(false, tInfoDTO.getMessage(), null);
	}

	@Override
	public TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord) {
//		HigherServiceImpl hService = new HigherServiceImpl();
		if (languageId == 0) {
			TopicsDTO allTitleDTO = hService.getAllTopics();
			String topicWord2 = topicWord.toLowerCase();
			if (allTitleDTO.isSuccess()) {
				if (topicWord2 != "") {
					List<Topic> topicsFront = new ArrayList<Topic>();
					List<TopicsDAL> topicsListas = allTitleDTO.getTopics();
					for (TopicsDAL t : topicsListas) {
						Topic topic = new Topic();
						topic.set_TopicId(t.topicId);
						topic.set_LanguageId(t.languageId);
						LanguageTagDTO ldto = hService.getLanguageTagByLanguageId(t.languageId);
						if (ldto.isSuccess()) {
							topic.set_LanguageTitle(
									ldto.getLanguageTag().get(0).title);
						}
						topic.set_TopicTitle(t.title);
						String textCheck = t.title.toLowerCase();
						if (textCheck.contains(topicWord2)) {
							topicsFront.add(topic);
						}

					}
					return new TopicsFrontDTO(true, "success", topicsFront);
				} else {
					List<Topic> topicsFront = new ArrayList<Topic>();
					List<TopicsDAL> topicsListas = allTitleDTO.getTopics();
					for (TopicsDAL t : topicsListas) {
						Topic topic = new Topic();
						topic.set_TopicId(t.topicId);
						topic.set_LanguageId(t.languageId);
						LanguageTagDTO ldto = hService.getLanguageTagByLanguageId(t.languageId);
						if (ldto.isSuccess()) {
							topic.set_LanguageTitle(
									ldto.getLanguageTag().get(0).title);
						}
						topic.set_TopicTitle(t.title);
						topicsFront.add(topic);
					}
					return new TopicsFrontDTO(true, "success", topicsFront);
				}

			}
		}

		else {
			TopicsDTO tDTO = hService.getTopicsByLanguageId(languageId);
			String topicWord2 = topicWord.toLowerCase();
			if (tDTO.isSuccess()) {
				List<TopicsDAL> topicsListas = tDTO.getTopics();
				if (topicsListas.size() == 0) {
					return new TopicsFrontDTO(false, "Nerastas topic", null);
				} else {
					if (topicWord2 != "") {
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

						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					} else {
						List<Topic> topicsFront = new ArrayList<Topic>();
						for (TopicsDAL t : topicsListas) {
							Topic topic = new Topic();
							topic.set_TopicId(t.topicId);
							topic.set_LanguageId(t.languageId);
							topic.set_TopicTitle(t.title);
							topicsFront.add(topic);
						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					}
				}
			}
			return new TopicsFrontDTO(false, tDTO.getMessage(), null);
		}
		return new TopicsFrontDTO(false, "Stringo FrontService", null);

	}

	@Override
	public ExamplesFrontDTO getExamplesByID(int topicId) {
//		HigherServiceImpl hService = new HigherServiceImpl();
		ExamplesDTO example = hService.getExamplesByTopicId(topicId);
		if (example.isSuccess()) {
			List<Example> exampleFront = new ArrayList<Example>();
			for (ExamplesDAL ex : example.getExamples()) {
				Example exampl = new Example();
				exampl.setTopicId(ex.topicId);
				exampl.setExampleId(ex.exampleId);
				exampl.setTitle(ex.title);
				exampl.setBodyHtml(ex.bodyHtml);
				exampleFront.add(exampl);
			}
			return new ExamplesFrontDTO(true, "success", exampleFront);

		}

		return new ExamplesFrontDTO(false, "Stringo Front Sevice exmaple dalis", null);
	}
}
