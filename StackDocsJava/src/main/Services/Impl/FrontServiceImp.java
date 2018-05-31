package Services.Impl;

import java.util.ArrayList;
import java.util.List;
import Models.Topic;
import Models.DAL.TopicsDAL;
import Models.DTO.TopicsDTO;
import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;

public class FrontServiceImp implements IFrontService {

	@Override
	public TopicsFrontDTO getTopicsByLanguageId(int languageId) {
		HigherServiceImpl hService = new HigherServiceImpl();
		TopicsDTO tDTO = hService.getTopicsByLanguageId(languageId);

		if (tDTO.isSuccess()) {
			List<TopicsDAL> topicsListas = tDTO.getTopics();
			if (topicsListas.size() == 0) {
				return new TopicsFrontDTO(false, "Nerastas topic", null);
			} else {
				List<Topic> topicsFront = new ArrayList<Topic>();
				for (TopicsDAL t : topicsListas) {
					Topic topic = new Topic();
					topic.set_TopicId(t.topicId);
					topic.set_LanguageId(t.languageId);
					topic.set_TopicTitle(t.title);
					topic.set_IntroductionHtml(t.introductionHtml);
					topic.set_SyntaxHtml(t.syntaxHtml);
					topic.set_ParametersHtml(t.parametersHtml);
					topic.set_RemarksHtml(t.remarksHtml);

					topicsFront.add(topic);
				}
				return new TopicsFrontDTO(true, "success", topicsFront);
			}
		}
		return new TopicsFrontDTO(false, tDTO.getMessage(), null);
	}

}
