package Services.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import Models.Business.Example;
import Models.Business.Language;
import Models.Business.Topic;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesDTO;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.LanguageTagFrontDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Models.DTO.UpdateTableDTO;
import Models.Mapping.ExamplesDALtoExample;
import Models.Mapping.LanguageTagsDALtoLanguage;
import Models.Mapping.TopicsDALtoTopic;
import Services.IFrontService;
import Services.IHigherService;

@Singleton
public class FrontServiceImp implements IFrontService {

	private IHigherService _higherService;

	public FrontServiceImp(HigherServiceImpl higherServiceImpl) {
		_higherService = higherServiceImpl;
	}

	@Override
	public TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId) {
		TopicsDTO tInfoDTO = _higherService.getTopicInfoByTopicId(topicId);
		if (tInfoDTO.success) {
			List<TopicsDAL> topicsListas = tInfoDTO.topics;
			if (topicsListas.size() == 0) {
				return new TopicsInfoFrontDTO(false, "Tuscias topic", null);
			} else {
				List<Topic> topicsInfoFront = new ArrayList<Topic>();
				for (TopicsDAL topicDal : topicsListas) {
					Topic topic = new Topic();
					TopicsDALtoTopic topicInfo = new TopicsDALtoTopic();
					topicInfo.topicInfoDaltoTopicInfo(topicDal, topic);
					topicsInfoFront.add(topic);
				}
				return new TopicsInfoFrontDTO(true, "success", topicsInfoFront);
			}
		}
		return new TopicsInfoFrontDTO(false, tInfoDTO.message, null);
	}

	@Override
	public TopicsFrontDTO getTopicsFromAllLanguage(String topicWord) {
		TopicsDTO allTitleDTO = _higherService.getAllTopics();
		String topicWord2 = topicWord.toLowerCase();
		if (allTitleDTO.success) {
			if (topicWord2 != "") {
				List<Topic> topicsFront = new ArrayList<Topic>();
				List<TopicsDAL> topicsListas = allTitleDTO.topics;
				for (TopicsDAL topicDal : topicsListas) {
					String textCheck = topicDal.title.toLowerCase();
					if (textCheck.contains(topicWord2)) {
						Topic topic = new Topic();
						TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
						topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
						topicsFront.add(topic);
					}
				}
				return new TopicsFrontDTO(true, "success", topicsFront);
			} else {
				List<Topic> topicsFront = new ArrayList<Topic>();
				List<TopicsDAL> topicsListas = allTitleDTO.topics;
				for (TopicsDAL topicDal : topicsListas) {
					String textCheck = topicDal.title.toLowerCase();
					Topic topic = new Topic();
					TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
					topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
					LanguageTagDTO ldto = _higherService.getLanguageTagByLanguageId(topicDal.languageId);
					// sito mebus >>>>>>>>>>>>>>>>>>>>>>>>
					if (ldto.success) {
						topic.languageTitle = ldto.languageTag.get(0).title;
					}
					// sito mebus >>>>>>>>>>>>>>>>>>>>>>>>
					topicsFront.add(topic);
				}
				return new TopicsFrontDTO(true, "success", topicsFront);
			}

		}
		return new TopicsFrontDTO(false, _higherService.getAllTopics().message, null);
	}
	@Override
	public TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord) {

		
			TopicsDTO tDTO = _higherService.getTopicsByLanguageId(languageId);
			String topicWord2 = topicWord.toLowerCase();
			if (tDTO.success) {
				List<TopicsDAL> topicsListas = tDTO.topics;
				if (topicsListas.size() == 0) {
					return new TopicsFrontDTO(false, "Nerastas topic", null);
				} else {
					if (topicWord2 != "") {
						List<Topic> topicsFront = new ArrayList<Topic>();
						for (TopicsDAL topicDal : topicsListas) {
							Topic topic = new Topic();
							TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
							topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
							// mapperTopics.map(topicDal, topic);
							String textCheck = topic.topicTitle.toLowerCase();
							if (textCheck.contains(topicWord2)) {
								topicsFront.add(topic);
							}

						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					} else {
						List<Topic> topicsFront = new ArrayList<Topic>();
						for (TopicsDAL topicDal : topicsListas) {
							Topic topic = new Topic();
							TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
							topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
							topicsFront.add(topic);
						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					}
				}
			}
			return new TopicsFrontDTO(false, tDTO.message, null);
	}

	
	@Override
	public ExamplesFrontDTO getExamplesByID(int topicId) {
		ExamplesDTO example = _higherService.getExamplesByTopicId(topicId);
		if (example.success) {
			List<Example> exampleFront = new ArrayList<Example>();
			for (ExamplesDAL ex : example.examples) {
				Example exampl = new Example();
				ExamplesDALtoExample mapEx = new ExamplesDALtoExample();
				mapEx.dalToExamples(ex, exampl);
				exampleFront.add(exampl);
			}
			return new ExamplesFrontDTO(true, "success", exampleFront);

		}

		return new ExamplesFrontDTO(false, "Stringo Front Sevice exmaple dalis", null);
	}
	@Override
	public LanguageTagFrontDTO getAllLanguageTag() {
		LanguageTagDTO alllanguagedto = _higherService.getAllLanguages();
		if (alllanguagedto.success) {
			List<Language> languageTagFront = new ArrayList<Language>();
			for (LanguageTagsDAL languageTagDto : alllanguagedto.languageTag) {
				Language languageTag = new Language();
				LanguageTagsDALtoLanguage mapll = new LanguageTagsDALtoLanguage();
				mapll.languageDaltoLanguage(languageTagDto, languageTag);
				languageTagFront.add(languageTag);
			}
			return new LanguageTagFrontDTO(true, "success", languageTagFront);
		}

		return new LanguageTagFrontDTO(false, "Stringo Front Sevice Language Tag dalis", null);
	}
	@Override
	public LanguageTagFrontDTO getLanguageTagByLanguageId(int languageId) {
		LanguageTagDTO ldto = _higherService.getLanguageTagByLanguageId(languageId);
		
		if (ldto.success) {
			List<Language> languageTagFront = new ArrayList<Language>();
			for (LanguageTagsDAL languageTagDto : ldto.languageTag) {
				Language languageTag = new Language();
				LanguageTagsDALtoLanguage mapll = new LanguageTagsDALtoLanguage();
				mapll.languageDaltoLanguage(languageTagDto, languageTag);
				languageTagFront.add(languageTag);
			}
			return new LanguageTagFrontDTO(true, "success", languageTagFront);
		}

		return new LanguageTagFrontDTO(false, "Stringo Front Sevice Language Tag dalis", null);
	}

	@Override
	public DeleteTableDTO deleteLanguage(int languageid) {

		return _higherService.delete(getLanguageTagByLanguageId(languageid).languageTag.get(0));
	}

	@Override
	public DeleteTableDTO deleteExample(int example) {
		return _higherService.delete(getExamplesByID(example).examples.get(0));
	}

	@Override
	public DeleteTableDTO deleteTopic(int topicID) {
		return _higherService.delete(getTopicInfoByTopicId(topicID).topicsInfo.get(0));
	}


	@Override
	public CreateTableDTO createTopic(List<String> params) {
		Topic ret = new Topic();

		ret.languageId = Integer.parseInt(params.get(0));
		ret.topicTitle = params.get(1);
		ret.introductionHtml = params.get(2);
		ret.syntaxHtml = params.get(3);
		ret.parametersHtml = params.get(4);
		ret.remarksHtml = params.get(5);

		return _higherService.create(ret);
	}


	@Override
	public UpdateTableDTO updateTopic(List<String> params) {
		Topic ret = new Topic();

		ret.languageId = Integer.parseInt(params.get(0));
		ret.topicTitle = params.get(1);
		ret.topicId = Integer.parseInt(params.get(2));
		ret.introductionHtml = params.get(3);
		ret.syntaxHtml = params.get(4);
		ret.parametersHtml = params.get(5);
		ret.remarksHtml = params.get(6);

		return _higherService.update(ret);
	}

	

	

}
